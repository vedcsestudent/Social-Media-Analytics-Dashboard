--1.trigger for inserting data inside the into the followers_count
create or replace trigger update_follower_count_tr
after insert or update on accounts
for each row
begin
    insert into followers (account_id, followers_count, timestp)
    values (:new.account_id,
    :new.followers_count, :new.date_joined);
end;
/

commit;

/*

select * from accounts;
update accounts set followers_count= 75  where account_id='A1';
select * from followers_count;

*/

--2. to get total number of like a user has
create or replace function func_get_total_likes
(p_account_id accounts.account_id%type)
return number
is
total_likes number;
begin
select  sum(like_count) into total_likes
from posts where account_id=p_account_id;
return total_likes;
end;


set serveroutput on;
--3.function which give number of like in a particular range of a specific account

create or replace function get_likes_per_timeframe_func(
p_account_id accounts.account_id%type,
p_startdate date default null,
p_enddate date default null)
return number
is
v_numOfLike number:=0;
begin
if(p_startdate is not null and p_enddate is not null) then
select sum(like_count) into v_numOfLike from posts
where account_id=p_account_id and
to_char(post_time,'DD-MM-YYYY') between to_char(p_startdate, 'DD-MM-YYYY') and to_char(p_enddate, 'DD-MM-YYYY');
else
select sum(like_count) 
into v_numOfLike from posts where account_id=p_account_id;
end if;
return v_numOfLike;
end;

/*select get_likes_per_timeframe_func('AIG18',TO_DATE('18-03-2024', 'DD-MM-YYYY'), TO_DATE('18-03-2024', 'DD-MM-YYYY')) from dual;

*/
------------------------------------------------------------------------------------------------------------------

-- 4.function which gives number of likes in a particular range of date;
/
set serveroutput on;
create or replace function get_likes_per_timeframe(
  startdate date default null,
  enddate date default null
)
return number
is
  v_numoflikes number:=0;
begin
  if (startdate is not null and enddate is not null) then
    select sum(like_count)
    into v_numoflikes
    from posts
    where to_char(post_time,'DD-MM-YYYY') between 
    to_char(startdate,'DD-MM-YYYY' ) and to_char(enddate,'DD-MM-YYYY');
  else
    select sum(like_count)
    into v_numoflikes
    from posts;
  end if;


  return v_numoflikes;
end;
/

/*
SELECT get_likes_per_timeframe(TO_DATE('18-03-2024', 'DD-MM-YYYY'), TO_DATE('18-03-2024', 'DD-MM-YYYY'))
as Likes_count FROM dual;
*/

--5.function which give number of share in particular range

create or replace function get_share_per_timeframe_func
(p_account_id Accounts.account_id%type,
p_startdate  date default null,
p_enddate date default null)
return number
 is v_numOfShare number:=0;
begin
if(p_startdate is not null and p_enddate is not null) then 
select sum(share_count) into v_numOfShare
from posts where  account_id= p_account_id and to_char(post_time, 'DD-MM-YYYY')
between to_char(p_startdate,'DD-MM-YYYY') 
and to_char(p_enddate,'DD-MM-YYYY');
else
select sum(share_count)
into v_numOfShare
from posts;
end if;
return v_numOfShare;
end;

/*
select * from posts;
SELECT get_share_per_timeframe_func('AIG18' ,TO_DATE('18-03-2024', 'DD-MM-YYYY'), TO_DATE('18-03-2024', 'DD-MM-YYYY')) as shares_count FROM dual;
*/


--6.Create a function return the number of post having the keyword or account name mentioned in the search



create or replace function get_listofpost_ofparticular_acc
(
p_keyword in varchar2)
return  sys_refcursor
is
listofpost_cursor sys_refcursor;
begin
open listofpost_cursor for
select * from posts where post_text like '%'|| p_keyword||'%';
return listofpost_cursor;
end;
/


select * from posts

set serveroutput on;
declare
cur sys_refcursor;
v_cur posts % rowtype;
begin
cur:=get_listofpost_ofparticular_acc('Embrace');
loop
fetch cur into v_cur;
exit when cur%notfound;
dbms_output.put_line(v_cur.post_id);
end loop;
close cur;
end;


--7.get post containing keyword or account
create or replace function getPostsContainingKeyWordOrAccount
(
p_keyword in varchar2,
p_account_id in varchar2)
return sys_refcursor
is
v_result_cursor sys_refcursor;
begin
open v_result_cursor for
select post_id,post_text from
posts where account_id= p_account_id and
post_text like '%' ||p_keyword||'%';
return v_result_cursor;
end;
*/


select * from posts;

set serveroutput on;
declare v_cursor sys_refcursor;
v_post_id posts.post_id%type;
v_post_text posts.post_text%type;
begin
v_cursor:=GetPostsContainingKeyWordOrAccount('the', 'AIG18');
loop
fetch v_cursor into v_post_id, v_post_text;
exit when v_cursor %notfound;
dbms_output.put_line('post id'|| v_post_id || 'post text'|| v_post_text);
end loop;
close v_cursor;
end;
/
select * from posts;



--8.function get popular post
create or replace function fun_getPopularPost
return sys_refcursor
is
v_popular_cursor sys_refcursor;
begin 
open
v_popular_cursor 
for
SELECT p.post_id,p.post_text, 
       (COUNT(c.comment_id) * 0.2) + 
       (SUM(p.share_count) * 0.5) + 
       (SUM(p.like_count) * 0.3) AS score
FROM posts p
INNER JOIN comments c ON c.post_id = p.post_id
GROUP BY p.post_id ,p.post_text order by  (COUNT(c.comment_id) * 0.2) + 
       (SUM(p.share_count) * 0.5) + 
       (SUM(p.like_count) * 0.3) desc fetch next 10 rows only;
 return v_popular_cursor;
 end;

/*
/DECLARE
    post_id posts.post_id%TYPE;
    post_score NUMBER;
    post_text posts.post_text%TYPE;
    cur SYS_REFCURSOR;
BEGIN
    cur := fun_getPopularPost;
    LOOP
        FETCH cur INTO post_id, post_text, post_score;
        EXIT WHEN cur%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(post_text);
    END LOOP;
    CLOSE cur;
END;

/

*/
--9.function to find the id  it is giving all the information
create or replace function searchUser(
p_query in varchar2)
return sys_refcursor as
v_results sys_refcursor;
begin
open v_results for
select account_id, platform, username, email , followers_count, date_joined, account_url
from accounts a
where lower(username) like '%'|| lower(p_query)||'%';
return v_results;
end searchUser;

/*
ACCEPT v_query CHAR PROMPT 'Enter search query: ';

DECLARE
    v_results SYS_REFCURSOR;
    v_account_id VARCHAR2(50);
    v_platform VARCHAR2(50);
    v_username VARCHAR2(100);
    v_email VARCHAR2(100);
    v_followers_count NUMBER;
    v_date_join TIMESTAMP;
    v_account_url VARCHAR2(255);
BEGIN
    v_results := SearchUser(p_query => '&v_query');

    LOOP
        FETCH v_results INTO
            v_account_id, v_platform, v_username, v_email, v_followers_count, v_date_join, v_account_url;
        EXIT WHEN v_results%NOTFOUND;

        -- Process the retrieved data as needed (e.g., print or use in further operations)
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || v_account_id);
        DBMS_OUTPUT.PUT_LINE('Platform: ' || v_platform);
        DBMS_OUTPUT.PUT_LINE('Username: ' || v_username);
        DBMS_OUTPUT.PUT_LINE('Email: ' || v_email);
        DBMS_OUTPUT.PUT_LINE('Followers Count: ' || v_followers_count);
        DBMS_OUTPUT.PUT_LINE('Date Join: ' || TO_CHAR(v_date_join, 'YYYY-MM-DD HH24:MI:SS'));
        DBMS_OUTPUT.PUT_LINE('Account URL: ' || v_account_url);
    END LOOP;

    CLOSE v_results;
END;
*/
/


--10.procedure which gives output that on search of a particular post_id who is mention


CREATE OR REPLACE FUNCTION GetPostMentions(postid IN posts.post_id%type ) RETURN SYS_REFCURSOR AS
    mention_cursor SYS_REFCURSOR;
BEGIN
    OPEN mention_cursor FOR
    SELECT REGEXP_SUBSTR(p.post_text, '@\w+') AS mention
    FROM posts p
    WHERE p.post_id = postid;

    RETURN mention_cursor;
END;
/


set serveroutput on;
DECLARE
    mention_cursor SYS_REFCURSOR;
    v_mention VARCHAR2(100);
BEGIN
    mention_cursor := GetPostMentions('P1'); -- Replace 123 with the actual post_id value you want to pass
    LOOP
        FETCH mention_cursor INTO v_mention;
        EXIT WHEN mention_cursor%NOTFOUND;
        -- Do something with the mention value, for example, print it
        DBMS_OUTPUT.PUT_LINE('Mention: ' || v_mention);
    END LOOP;
    CLOSE mention_cursor;
END;
/


select post_text from posts where post_id='P1';
--11. procedure which gives output that on search of particular post_id who is mentioned


CREATE OR REPLACE PROCEDURE GetMentionsForPost (
    p_post_id IN VARCHAR2,
    p_mention_list OUT VARCHAR2
) AS
    v_post_text VARCHAR2(1000);
    v_username VARCHAR2(100);
    v_mentioned_users VARCHAR2(1000) := '';
    v_mentioned_username VARCHAR2(100);
BEGIN
    -- Fetch username for the specified post_id
    SELECT a.Username INTO v_username
    FROM Posts p
    JOIN Accounts a ON p.Account_Id = a.Account_Id
    WHERE p.Post_Id = p_post_id;

    -- Fetch post text for the specified post_id
    SELECT p.Post_Text INTO v_post_text
    FROM Posts p
    WHERE p.Post_Id = p_post_id;

    -- Use regular expression to find mentions (assuming mentions start with @)
    FOR r IN (
        SELECT DISTINCT REGEXP_SUBSTR(v_post_text, '@(\w+)', 1, LEVEL, NULL, 1) AS mentioned_username
        FROM dual
        CONNECT BY REGEXP_SUBSTR(v_post_text, '@(\w+)', 1, LEVEL, NULL, 1) IS NOT NULL
    )
    LOOP
        -- Check if the mentioned username exists in the Accounts table
        BEGIN
            SELECT a.Username INTO v_mentioned_username FROM Accounts a WHERE a.Username = r.mentioned_username;

            -- Build a comma-separated list of mentioned usernames
            IF v_mentioned_users IS NOT NULL THEN
                v_mentioned_users := v_mentioned_users || ', ' || v_mentioned_username;
            ELSE
                v_mentioned_users := v_mentioned_username;
            END IF;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Mentioned username not found, do nothing
                DBMS_OUTPUT.PUT_LINE('Username not found: ' || r.mentioned_username);
        END;
    END LOOP;

    -- Set the mention_list
    IF LENGTH(v_mentioned_users) > 0 THEN
        p_mention_list :=  v_username || ' mentioned in their post: ' || v_mentioned_users;
    ELSE
        p_mention_list := 'No mentions found';
    END IF;
END;
/




select * from posts;

-- Run the procedure to get mentions for a specific post ID
-- Declare the bind variable
VARIABLE mention_list VARCHAR2(1000);

-- Run the procedure to get mentions for a specific post ID
EXECUTE GetMentionsForPost('&post_id', :mention_list);

-- Display the result
PRINT mention_list;



select * from posts;

select * from accounts;





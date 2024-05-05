--Creating Users table.
create table Users
(
Email  varchar2(100) constraint pk_users primary key,
Password varchar2(255) not null
);


select * from Users;

--Creating Accounts table

create table Accounts
(
Account_Id varchar2(50) constraint pk_accounts primary key,
Platform varchar2(50) not null, 
Username varchar2(100) not null,
Email varchar2(100) constraint fk_accounts_users 
references Users(Email) on delete cascade,
Followers_Count number not null,
Date_Joined timestamp default current_timestamp not null,
Account_Url varchar2(255)
);
select * from Accounts;

--creating account sequence
CREATE   SEQUENCE account_id_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 10;


-- Create a new trigger   for inserting primary key values
CREATE OR REPLACE TRIGGER trg_accounts_auto_id
BEFORE INSERT ON Accounts
FOR EACH ROW
BEGIN
    IF :NEW.Account_Id IS NULL THEN
        :NEW.Account_Id := 'A' || TO_CHAR(account_id_seq.NEXTVAL, 'FM00000');
    END IF;
END;
/



--Creating the Posts table
create table Posts
(
Post_Id varchar2(50)  constraint pk_posts primary key,
Account_Id varchar2(50) constraint fk_posts_accounts
references Accounts(Account_Id) on delete cascade,
Post_Text varchar2(1000) not null,
Like_Count number default 0 not null,
Share_Count number default 0 not null,
Post_Time timestamp default current_timestamp not null,
Post_Score number not null
-- It will done through programming
);


--creating post sequence
CREATE SEQUENCE post_id_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 10;


-- Create a new trigger for posts
CREATE OR REPLACE TRIGGER trg_posts_auto_id
BEFORE INSERT ON posts
FOR EACH ROW
BEGIN
    IF :NEW.post_Id IS NULL THEN
        :NEW.post_id := 'P' || TO_CHAR(post_id_seq.NEXTVAL, 'FM00000');
    END IF;
END;
/



select * from Posts;

--Creating  Comments table

create table Comments
(
Comment_Id varchar2(50) constraint pk_comments Primary key,
Post_Id varchar2(50) constraint fk_comments_posts
references posts (Post_Id) on delete cascade,
Comment_Text varchar2(1000),
Comment_Score number --generated through programming
);

--creating comment sequence
CREATE SEQUENCE comment_id_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 10;



-- Create a new trigger for comment
CREATE OR REPLACE TRIGGER trg_comments_auto_id
BEFORE INSERT ON comments
FOR EACH ROW
BEGIN
    IF :NEW.comment_Id IS NULL THEN
        :NEW.comment_id := 'C' || TO_CHAR(comment_id_seq.NEXTVAL, 'FM00000');
    END IF;
END;
/


select * from Comments;









--Creating the Mentions Table
create table Mentions
(
Post_Id varchar2(50) constraint fk_mentions_posts 
references Posts (Post_Id) on delete cascade,
Mentioned_Id varchar2(50) constraint  
fk_mentions_accounts references Accounts(Account_Id) 
on delete cascade,
constraint pk_mentions primary key(Post_Id, Mentioned_Id)
);





--creating Mention_Records

create table Mention_Records
(
Account_Id varchar2(50) constraint
fk_mention_records_accounts references Accounts(Account_Id)
on delete cascade,
No_Of_Mentions number not null,
Timestp timestamp default current_timestamp not null
);

select * from mention_records;

--Creating  Competitors table
create table Competitors
(
Account_Id varchar2(50) constraint fk1_competitors_accounts
references Accounts(Account_Id) on delete cascade,
Competitor_Id varchar2(50) constraint fk2_competitors_ccounts
references Accounts(Account_Id) on delete cascade,
constraint pk_competitors primary key(Account_Id,
Competitor_Id)
);

select * from Competitors;


--creating the Followers table

create table Followers
(
Account_Id varchar2(50) 
constraint fk_followers_count_accounts
references Accounts(Account_Id) on delete cascade,
Followers_count number not null,
Timestp timestamp default current_timestamp not null
);
select * from followers;

----------------------------------------------------------------------------------------------------





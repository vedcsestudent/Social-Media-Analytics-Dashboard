--dml operations
--inserting data into Accounts table

insert all
    into Accounts(Account_id, Platform, Username, Email,Followers_count, Date_join, Account_url)
    values('1','Twitter', 'user1','user1@example.com',1000, current_timestamp,'https://twitter.com/user1')
    into Accounts(Account_id, Platform, Username, Email, Followers_count, Date_join, Account_url) 
    values('2','Facebook', 'user2','user2@example.com', 2000, current_timestamp,'https://facebook.com/user2')
    into Accounts(Account_id, Platform, Username, Email, Followers_count, Date_join, Account_url) 
    values ('3', 'Instagram', 'user3', 'user3@example.com', 3000, current_timestamp, 'https://facebook.com/user3')
    into Accounts(Account_id, Platform, Username, Email, Followers_count, Date_join, Account_url)
    values ('4', 'Twitter', 'user4', 'user4@example.com', 4000, current_timestamp,'https://twitter.com/user4')
    into Accounts(Account_id, Platform , Username, Email, Followers_count, Date_join, Account_url) 
    values('5', 'Facebook', 'user5', 'user5@example.com', 5000, current_timestamp, 'https://facebook.com/user5')

select * from dual

select * from Accounts;


--inserting data inside the Posts table
insert all
    into Posts (Post_id, Account_id, Post_text, Like_count, Share_count, comment_id, Post_time, Post_sentiment_score)
    values ('1', '1', 'Post 1', 10, 5, '1', CURRENT_TIMESTAMP, 0.75)
    into Posts (Post_id, Account_id, Post_text, Like_count, Share_count, comment_id, Post_time, Post_sentiment_score)
    values ('2', '2', 'Post 2', 20, 10, '2', CURRENT_TIMESTAMP, 0.85)
    into Posts (Post_id, Account_id, Post_text, Like_count, Share_count, comment_id, Post_time, Post_sentiment_score)
    values ('3', '3', 'Post 3', 30, 15, '3', CURRENT_TIMESTAMP, 0.65)
    into Posts (Post_id, Account_id, Post_text, Like_count, Share_count, comment_id, Post_time, Post_sentiment_score)
    values ('4', '4', 'Post 4', 40, 20, '4', CURRENT_TIMESTAMP, 0.95)
    into Posts (Post_id, Account_id, Post_text, Like_count, Share_count, comment_id, Post_time, Post_sentiment_score)
    values ('5', '5', 'Post 5', 50, 25, '5', CURRENT_TIMESTAMP, 0.55)
select * from dual;

--inserting the data inside the  mentions table

insert all
    into mentions ( post_id, mentioned)
    values ( '1', '2')
    into mentions ( post_id, mentioned)
    values ( '2', '3')
    into mentions ( post_id, mentioned)
    values ( '3', '4')
    into mentions ( post_id, mentioned)
    values ( '4', '5')
    into mentions ( post_id, mentioned)
    values ('5', '1')
select * from dual;


--inserting the data inside the comments
insert all
    into comments (comment_id, comment_description, comment_score)
    values ('1', 'Great post!', 0.8)
    into comments (comment_id, comment_description, comment_score)
    values ('2', 'Awesome!', 0.9)
    into comments (comment_id, comment_description, comment_score)
    values ('3', 'Nice!', 0.7)
    into comments (comment_id, comment_description, comment_score)
    values ('4', 'Love it!', 0.95)
    into comments (comment_id, comment_description, comment_score)
    values ('5', 'Interesting', 0.75)
select * from dual;


--inserting the data inside the  mention_records
insert all
    into mention_records (account_id, no_of_mentions, time_stamp)
    values ('1', 2, current_timestamp-4)
    into mention_records (account_id, no_of_mentions, time_stamp)
    values ('2', 1, current_timestamp-3)
    into mention_records (account_id, no_of_mentions, time_stamp)
    values ('3', 3, current_timestamp-2)
    into mention_records (account_id, no_of_mentions, time_stamp)
    values ('4', 2, current_timestamp-1)
    into mention_records (account_id, no_of_mentions, time_stamp)
    values ('5', 1, current_timestamp)
select * from dual;


--inserting the data inside the competitors
insert all
    into competitors (account_id, competitor_id)
    values ('1', '2')
    into competitors (account_id, competitor_id)
    values ('2', '3')
    into competitors (account_id, competitor_id)
    values ('3', '4')
    into competitors (account_id, competitor_id)
    values ('4', '5')
    into competitors (account_id, competitor_id)
    values ('5', '1')
select * from dual;



--inserting the data inside the followers_count

select * from followers_count;
insert all
    into followers_count(account_id, followers, time_stamp)
    values('1',1500,current_timestamp-4)
    into followers_count(account_id, followers, time_stamp)
    values('2', 2500, current_timestamp-3)
    into followers_count(account_id, followers, time_stamp)
    values('3', 3500, current_timestamp-2)
    into followers_count (account_id, followers, time_stamp)
    values('4', 4500, current_timestamp-1)
    into followers_count (account_id, followers, time_stamp)
    values('5', 5500, current_timestamp)
select * from dual;
   
commit;    


--viewing the data
select * from Accounts;
select * from Comments;
select * from Posts;
select * from Mentions;
select * from Mention_records;
select * from competitors;
select * from followers_count;
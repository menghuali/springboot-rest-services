-- This SQL script will be executed when start up.
-- The sequences are defined in the related Java entities
INSERT INTO user
values(seq_user.nextval, sysdate(), 'Joe');

INSERT INTO user
values(seq_user.nextval, sysdate(), 'Mary');

INSERT INTO user
values(seq_user.nextval, sysdate(), 'Bob');

INSERT INTO post
values(seq_post.nextval, 'Post A', 1);

INSERT INTO post
values(seq_post.nextval, 'Post B', 1);

INSERT INTO post
values(seq_post.nextval, 'Post C', 1);
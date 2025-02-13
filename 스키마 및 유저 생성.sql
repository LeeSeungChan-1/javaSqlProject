-- 유저 생성 id: user_javaSqlProject pw: 1234
create user 'user_javaSqlProject'@'localhost' identified by '1234';
-- 사용할 db 생성 
create schema db_javaSqlProject;
-- 유저에 db에 대한 권한부여
grant all on db_javaSqlProject.* to 'user_javaSqlProject'@localhost;

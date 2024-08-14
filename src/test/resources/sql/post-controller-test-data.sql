
INSERT INTO `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
VALUES (1, 'banseok@naver.com', 'bani', 'Seoul', 'aaaaaaa-aa', 'ACTIVE', 0);
insert into `posts` (`id`, `content`, `created_at`, `modified_at`, `user_id`)
VALUES (1, 'helloworld',1533L,0,1);
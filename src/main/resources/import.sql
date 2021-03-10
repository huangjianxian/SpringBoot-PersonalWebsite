INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1998', 'jontyhua@gmail.com', 'boy', '华瑾熙', '123456', '1', 'jontyhua');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1991', 'a@163.com', 'girl', 'aa', '123456', '1', 'aaa');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1992', 'b@163.com', 'boy', 'bb', '123456', '1', 'bbb');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1993', 'c@163.com', 'boy', 'cc', '123456', '1', 'ccc');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1994', 'd@163.com', 'girl', 'dd', '123456', '1', 'ddd');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1995', 'e@163.com', 'boy', 'ee', '123456', '1', 'eee');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1996', 'f@163.com', 'boy', 'ff', '123456', '1', 'fff');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1997', 'g@163.com', 'girl', 'gg', '123456', '1', 'ggg');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1998', 'h@163.com', 'girl', 'hh', '123456', '1', 'hhh');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1999', 'i@163.com', 'boy', 'ii', '123456', '1', 'iii');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('2000', 'j@163.com', 'girl', 'jj', '123456', '1', 'jjj');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('2001', 'k@163.com', 'boy', 'kk', '123456', '1', 'kkk');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('2002', 'l@163.com', 'girl', 'll', '123456', '1', 'lll');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('2003', 'm@163.com', 'boy', 'mm', '123456', '1', 'mmm');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('2004', 'n@163.com', 'boy', 'nn', '123456', '1', 'nnn');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('2005', 'o@163.com', 'boy', 'oo', '123456', '1', 'ooo');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1985', 'p@163.com', 'girl', 'pp', '123456', '1', 'ppp');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1986', 'q@163.com', 'boy', 'qq', '123456', '1', 'qqq');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1986', 'r@163.com', 'girl', 'rr', '123456', '1', 'rrr');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1987', 's@163.com', 'girl', 'ss', '123456', '0', 'sss');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1989', 't@163.com', 'boy', 'tt', '123456', '0', 'ttt');
INSERT INTO `hjxblog`.`user` (`birth`, `email`, `gender`, `nickname`, `password`, `state`, `username`) VALUES ('1990', 'u@163.com', 'girl', 'uu', '123456', '0', 'uuu');

INSERT INTO `hjxblog`.`authority` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `hjxblog`.`authority` (`id`, `name`) VALUES ('2', 'ROLE_USER');

INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('个人博客');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('Java');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('日记');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('读书笔记');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('英语学习');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('考研数学');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('spring');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('springboot');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('csdn');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('github');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('c++');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('德语学习');
INSERT INTO `hjxblog`.`tag` (`name`) VALUES ('数学建模');


INSERT INTO `hjxblog`.`notice` (`content`, `create_time`, `state`) VALUES ('网站上线', '2019-04-28', '1');
INSERT INTO `hjxblog`.`notice` (`content`, `create_time`, `state`) VALUES ('开始博客写作', '2019-04-28', '0');
INSERT INTO `hjxblog`.`notice` (`content`, `create_time`, `state`) VALUES ('完善注册功能', '2019-04-28', '1');



INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('2', '1');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('3', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('4', '1');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('5', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('6', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('7', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('8', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('9', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('10', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('11', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('12', '1');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('13', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('14', '1');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('15', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('16', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('17', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('18', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('19', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('20', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('21', '2');
INSERT INTO `hjxblog`.`user_authority` (`user_id`, `authority_id`) VALUES ('22', '2');

INSERT INTO `hjxblog`.`category` (`name`) VALUES ('Java');
INSERT INTO `hjxblog`.`category` (`name`) VALUES ('spring');
INSERT INTO `hjxblog`.`category` (`name`) VALUES ('springboot');

INSERT INTO `hjxblog`.`flag` (`name`) VALUES ('原创');
INSERT INTO `hjxblog`.`flag` (`name`) VALUES ('转载');
INSERT INTO `hjxblog`.`flag` (`name`) VALUES ('翻译');


INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');

INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题1', '0',  '1', '1', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题2', '0',  '2', '2', '2');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2019-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题3', '0',  '3', '3', '1');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('1','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题4', '0',  '1', '1', '3');
INSERT INTO `hjxblog`.`blog` (`state`,`comment_size`, `content`, `create_time`, `html_content`, `read_size`, `summary`, `title`, `vote_size`,  `category_id`, `flag_id`, `user_id`) VALUES ('0','1', '博客markdown内容', '2018-04-15', '博客markdown HTML内容', '3', '博客摘要', '标题5', '0',  '2', '2', '4');



INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('1', '1');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('2', '1');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('3', '1');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('1', '2');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('2', '2');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('3', '2');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('1', '3');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('2', '3');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('3', '3');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('4', '4');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('5', '4');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('6', '4');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('7', '5');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('8', '5');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('9', '5');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('10', '6');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('11', '6');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('12', '6');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('13', '7');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('14', '8');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('15', '9');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('1', '10');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('2', '11');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('3', '12');
INSERT INTO `hjxblog`.`blog_tag` (`blog_id`, `tag_id`) VALUES ('1', '13');

INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记1', '2019-05-01', '日记1', '1', '标题1');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记2', '2019-05-01', '日记2', '1', '标题2');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记3', '2019-05-01', '日记3', '0', '标题3');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记4', '2019-05-01', '日记4', '0', '标题4');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记5', '2019-05-01', '日记5', '1', '标题5');

INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记1', '2019-05-01', '日记1', '1', '标题1');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记2', '2019-05-01', '日记2', '1', '标题2');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记3', '2019-05-01', '日记3', '0', '标题3');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记4', '2019-05-01', '日记4', '0', '标题4');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记5', '2019-05-01', '日记5', '1', '标题5');

INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记1', '2019-05-01', '日记1', '1', '标题1');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记2', '2019-05-01', '日记2', '1', '标题2');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记3', '2019-05-01', '日记3', '0', '标题3');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记4', '2019-05-01', '日记4', '0', '标题4');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记5', '2019-05-01', '日记5', '1', '标题5');

INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记1', '2019-05-01', '日记1', '1', '标题1');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记2', '2019-05-01', '日记2', '1', '标题2');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记3', '2019-05-01', '日记3', '0', '标题3');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记4', '2019-05-01', '日记4', '0', '标题4');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记5', '2019-05-01', '日记5', '1', '标题5');

INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记1', '2019-05-01', '日记1', '1', '标题1');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记2', '2019-05-01', '日记2', '1', '标题2');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记3', '2019-05-01', '日记3', '0', '标题3');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记4', '2019-05-01', '日记4', '0', '标题4');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记5', '2019-05-01', '日记5', '1', '标题5');

INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记1', '2019-05-01', '日记1', '1', '标题1');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记2', '2019-05-01', '日记2', '1', '标题2');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记3', '2019-05-01', '日记3', '0', '标题3');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记4', '2019-05-01', '日记4', '0', '标题4');
INSERT INTO `hjxblog`.`diary` (`content`, `create_time`, `html_content`, `state`, `title`) VALUES ('日记5', '2019-05-01', '日记5', '1', '标题5');

INSERT INTO `hjxblog`.`friend_link` (`name`, `url`) VALUES ('程序员DD', 'http://blog.didispace.com/');
INSERT INTO `hjxblog`.`friend_link` (`name`, `url`) VALUES ('程序员张先生', 'https://www.zhyocean.cn/');

INSERT INTO `hjxblog`.`vote` (`create_time`, `blog_id`, `user_id`) VALUES ('2019-05-14 02:22:25', '1', '1');

INSERT INTO `hjxblog`.`comment` (`content`, `create_time`, `blog_id`, `user_id`) VALUES ('hello', '2019-05-14 02:22:25', '1', '1');
INSERT INTO `hjxblog`.`comment` (`content`, `create_time`, `blog_id`, `parent_comment_id`, `user_id`) VALUES ('你好，我回复你', '2019-05-14 02:22:25', '1', '1', '2');
INSERT INTO `hjxblog`.`comment` (`content`, `create_time`, `blog_id`, `parent_comment_id`, `user_id`) VALUES ('2楼那个你好', '2019-05-14 02:22:25', '1', '2', '3');


INSERT INTO `hjxblog`.`leave_message` (`content`, `create_time`, `user_id`) VALUES ('hello博主', '2019-05-14 02:22:25', '1');
INSERT INTO `hjxblog`.`leave_message` (`content`, `create_time`, `parent_leave_message_id`, `user_id`) VALUES ('一楼你好', '2019-05-14 02:22:25', '1', '2');
INSERT INTO `hjxblog`.`leave_message` (`content`, `create_time`, `parent_leave_message_id`, `user_id`) VALUES ('二楼你好', '2019-05-14 02:22:25', '1', '3');


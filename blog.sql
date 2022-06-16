-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-07-24 09:19:56
-- 服务器版本： 5.7.28
-- PHP 版本： 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `blog`
--

-- --------------------------------------------------------

--
-- 表的结构 `blog_article`
--

CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `author` int(11) NOT NULL,
  `classify` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `summary` longtext NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `blog_article`
--

INSERT INTO `blog_article` (`id`, `name`, `author`, `classify`, `content`, `summary`, `status`, `create_time`, `update_time`) VALUES
(1, '测试', 1, 1, '测试内容', '测试内容', 0, '2020-07-24 08:52:24', '2020-07-24 08:52:24');

-- --------------------------------------------------------

--
-- 表的结构 `blog_classify`
--

CREATE TABLE `blog_classify` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `info` text NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `blog_classify`
--

INSERT INTO `blog_classify` (`id`, `name`, `info`, `create_time`, `update_time`) VALUES
(1, '默认分类', '默认分类', '2020-07-23 16:16:17', '2020-07-23 16:16:17');

-- --------------------------------------------------------

--
-- 表的结构 `blog_user`
--

CREATE TABLE `blog_user` (
  `uid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `token` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `blog_user`
--

INSERT INTO `blog_user` (`uid`, `name`, `type`, `password`, `email`, `token`, `create_time`, `update_time`) VALUES
(1, 'test', 2, 'test', 'test@test.com', '', '2020-07-22 12:12:41', '2020-07-23 18:13:52');

--
-- 转储表的索引
--

--
-- 表的索引 `blog_article`
--
ALTER TABLE `blog_article`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_blog_article_blog_user` (`author`),
  ADD KEY `FK_blog_article_blog_classify` (`classify`);

--
-- 表的索引 `blog_classify`
--
ALTER TABLE `blog_classify`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `blog_user`
--
ALTER TABLE `blog_user`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `email` (`email`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `blog_article`
--
ALTER TABLE `blog_article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `blog_classify`
--
ALTER TABLE `blog_classify`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `blog_user`
--
ALTER TABLE `blog_user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 限制导出的表
--

--
-- 限制表 `blog_article`
--
ALTER TABLE `blog_article`
  ADD CONSTRAINT `FK_blog_article_blog_classify` FOREIGN KEY (`classify`) REFERENCES `blog_classify` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_blog_article_blog_user` FOREIGN KEY (`author`) REFERENCES `blog_user` (`uid`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

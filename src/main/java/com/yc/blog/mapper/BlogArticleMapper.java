package com.yc.blog.mapper;

import com.yc.blog.bean.BlogArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogArticle record);

    BlogArticle selectByPrimaryKey(Integer id);

    List<BlogArticle> selectByClassify(int classify, int a, int b);

    List<BlogArticle> selectAllByClassify(int classify);

    List<BlogArticle> selectByUser(int user, int a, int b);

    List<BlogArticle> search(String text, int a, int b);

    List<BlogArticle> selectAll(int a, int b);

    int updateByPrimaryKey(BlogArticle record);

    int updateToDefaultClassify(int[] ids);
}
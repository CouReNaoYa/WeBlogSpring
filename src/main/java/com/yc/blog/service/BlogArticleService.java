package com.yc.blog.service;

import com.yc.blog.bean.BlogArticle;
import com.yc.blog.mapper.BlogArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogArticleService {

    @Autowired
    BlogArticleMapper blogArticleMapper;

    public List<BlogArticle> selectAll(int quantity, int page){
        return blogArticleMapper.selectAll((page-1)*quantity, quantity);
    }

    public BlogArticle selectById(int id){
        return blogArticleMapper.selectByPrimaryKey(id);
    }

    public List<BlogArticle> selectByClassify(int classify, int quantity, int page){
        return blogArticleMapper.selectByClassify(classify, (page-1)*quantity, quantity);
    }

    public List<BlogArticle> selectByUser(int user, int quantity, int page){
        return blogArticleMapper.selectByUser(user, (page-1)*quantity, quantity);
    }

    public List<BlogArticle> search(String text, int quantity, int page){
        return blogArticleMapper.search(text, (page-1)*quantity, quantity);
    }

    public int insert(BlogArticle blogArticle){
        if(blogArticle.getId() == null){
            return blogArticleMapper.insert(blogArticle);
        }else{
            return 0;
        }
    }

    public int update(BlogArticle blogArticle){
        if(blogArticle.getId() != null){
            return blogArticleMapper.updateByPrimaryKey(blogArticle);
        }else{
            return 0;
        }
    }

    public int deleteById(int id){
        return blogArticleMapper.deleteByPrimaryKey(id);
    }

    int updateToDefaultClassify(int[] ids){
        return blogArticleMapper.updateToDefaultClassify(ids);
    }
}

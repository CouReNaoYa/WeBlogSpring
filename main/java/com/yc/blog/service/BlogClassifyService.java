package com.yc.blog.service;

import com.yc.blog.bean.BlogArticle;
import com.yc.blog.bean.BlogClassify;
import com.yc.blog.mapper.BlogArticleMapper;
import com.yc.blog.mapper.BlogClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogClassifyService {

    @Autowired
    BlogClassifyMapper blogClassifyMapper;

    @Autowired
    BlogArticleMapper blogArticleMapper;

    public List<BlogClassify> selectAll(){
        return blogClassifyMapper.selectAll();
    }

    public BlogClassify selectById(int id){
        return blogClassifyMapper.selectByPrimaryKey(id);
    }

    public int insert(BlogClassify blogClassify){
        if(blogClassify.getId() == null){
            return blogClassifyMapper.insert(blogClassify);
        }else{
            return 0;
        }
    }

    public int update(BlogClassify blogClassify){
        if(blogClassify.getId() != null){
            return blogClassifyMapper.updateByPrimaryKey(blogClassify);
        }else{
            return 0;
        }
    }

    public int deleteById(int id){
        if(id != 1){
            List<BlogArticle> l = blogArticleMapper.selectAllByClassify(id);
            if(l.size() == 0){
                return blogClassifyMapper.deleteByPrimaryKey(id);
            }
            else {
                int[] edit = new int[l.size()];
                for(int i = 0; i< l.size(); i++){
                    edit[i] = l.get(i).getId();
                }
                blogArticleMapper.updateToDefaultClassify(edit);
                return blogClassifyMapper.deleteByPrimaryKey(id);
            }
        }else{
            return 0;
        }
    }
}

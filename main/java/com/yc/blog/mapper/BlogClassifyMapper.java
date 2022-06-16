package com.yc.blog.mapper;

import com.yc.blog.bean.BlogClassify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogClassify record);

    BlogClassify selectByPrimaryKey(Integer id);

    List<BlogClassify> selectAll();

    int updateByPrimaryKey(BlogClassify record);
}
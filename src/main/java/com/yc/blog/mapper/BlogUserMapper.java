package com.yc.blog.mapper;

import com.yc.blog.bean.BlogUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(BlogUser record);

    BlogUser selectByPrimaryKey(Integer uid);

    BlogUser selectByEmail(String email);

    BlogUser selectByToken(String token);

    BlogUser getToken(Integer uid);

    List<BlogUser> selectAll();

    int updateByPrimaryKey(BlogUser record);

    int updateByToken(BlogUser record);

    int setToken(BlogUser record);
}
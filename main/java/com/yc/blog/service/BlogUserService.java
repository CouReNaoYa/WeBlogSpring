package com.yc.blog.service;

import com.yc.blog.bean.BlogUser;
import com.yc.blog.mapper.BlogUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogUserService {

    @Autowired
    BlogUserMapper blogUserMapper;

    public List<BlogUser> selectAll(){
        return blogUserMapper.selectAll();
    }

    public BlogUser selectByUid(int uid){
        return blogUserMapper.selectByPrimaryKey(uid);
    }

    public BlogUser selectByEmail(String email){
        return blogUserMapper.selectByEmail(email);
    }

    public BlogUser selectByToken(String token){
        return blogUserMapper.selectByToken(token);
    }

    public int insert(BlogUser blogUser){
        if(blogUser.getUid() == null){
            if(blogUser.getType() == null)
                blogUser.setType(0);
            return blogUserMapper.insert(blogUser);
        }else{
            return 0;
        }
    }

    public int update(BlogUser blogUser){
        if(blogUser.getUid() != null){
            if(blogUser.getType() == null)
                blogUser.setType(blogUserMapper.selectByPrimaryKey(blogUser.getUid()).getType());
            return blogUserMapper.updateByPrimaryKey(blogUser);
        }else{
            return 0;
        }
    }

    public int updateByToken(BlogUser blogUser){
        if(blogUser.getToken() != null){
            return blogUserMapper.updateByToken(blogUser);
        }else{
            return 0;
        }
    }

    public int deleteByUid(int uid){
        return blogUserMapper.deleteByPrimaryKey(uid);
    }

    public BlogUser getToken(int uid){
        return blogUserMapper.getToken(uid);
    }

    public int setToken(BlogUser blogUser){
        return blogUserMapper.setToken(blogUser);
    }

}

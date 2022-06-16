package com.yc.blog.controller;

import com.yc.blog.bean.BlogUser;
import com.yc.blog.bean.BlogUserLogin;
import com.yc.blog.common.Response;
import com.yc.blog.service.BlogUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Api(tags="用户管理")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    BlogUserService blogUserService;

    @PostMapping("/login")
    @ApiOperation(value="用户登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name="email",value="邮箱地址",dataType="string",required=true),
        @ApiImplicitParam(name="password",value="密码",dataType="string",required=true)
    })
    public Response<BlogUserLogin> login(String email, String password){
        if(email == null || password == null || email.equals("") || password.equals("")){
            return Response.error(400, "参数错误", null);
        }else{
            BlogUser u = blogUserService.selectByEmail(email);
            if(u == null || !u.getPassword().equals(password)){
                return Response.error(403, "用户名或密码错误", null);
            }
            u.setToken(UUID.randomUUID().toString());
            blogUserService.setToken(u);
            BlogUserLogin login = new BlogUserLogin(u);
            return Response.ok(login);
        }
    }

    @PostMapping("/logout")
    @ApiOperation(value="用户登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
    })
    public Response<String> logout(String token){
        if(token == null){
            return Response.error(400, "参数错误", null);
        }else{
            BlogUser u = blogUserService.selectByToken(token);
            if(u == null){
                return Response.error(400, "参数错误", null);
            }
            u.setToken("");
            blogUserService.setToken(u);
            return Response.ok("操作成功");
        }
    }

    @GetMapping("")
    @ApiOperation(value="根据用户Id获取指定用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name="uid",value="用户Id",dataType="int",required=true)
    })
    public Response<BlogUser> get(Integer uid){
        if(uid == null){
            return Response.error(400, "参数错误", null);
        }else{
            BlogUser u = blogUserService.selectByUid(uid);
            if(u == null){
                return Response.error(404, "用户不存在", null);
            }
            return Response.ok(u);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value="获取所有用户")
    public Response<List<BlogUser>> getAll(){
        List<BlogUser> ul = blogUserService.selectAll();
        return Response.ok(ul);
    }

    @PutMapping("")
    @ApiOperation(value="根据Token更新当前用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户token",dataType="string",required=true),
        @ApiImplicitParam(name="name",value="用户昵称",dataType="string",required=true),
        @ApiImplicitParam(name="avatar",value="用户头像",dataType="string",required=true),
        @ApiImplicitParam(name="background",value="用户背景",dataType="string",required=true),
        @ApiImplicitParam(name="password",value="用户密码",dataType="string",required=true),
        @ApiImplicitParam(name="email",value="用户邮箱地址",dataType="string",required=true)
    })
    public Response<String> update(BlogUser user){
        if(user == null || user.getToken() == null){
            return Response.error(400, "参数错误", null);
        }else{
            int i = blogUserService.updateByToken(user);
            if(i == 0)
                return Response.error(500, "操作失败", null);
            else
                return Response.ok("操作成功");
        }
    }

    @PostMapping("")
    @ApiOperation(value="新增用户（管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="管理员Token",dataType="string",required=true),
        @ApiImplicitParam(name="type",value="用户类型",dataType="int",required=true),
        @ApiImplicitParam(name="name",value="用户昵称",dataType="string",required=true),
        @ApiImplicitParam(name="avatar",value="用户头像",dataType="string",required=true),
        @ApiImplicitParam(name="password",value="用户密码",dataType="string",required=true),
        @ApiImplicitParam(name="email",value="用户邮箱地址",dataType="string",required=true)
    })
    public Response<String> insert(BlogUser user){
        if(user == null || user.getUid() != null || user.getToken() == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证管理员
            BlogUser admin = blogUserService.selectByToken(user.getToken());
            if(admin != null && admin.getType() == 2){
                BlogUser tmp = blogUserService.selectByEmail(user.getEmail());
                if(tmp == null){
                    user.setToken("");
                    int i = blogUserService.insert(user);
                    if(i == 0)
                        return Response.error(403, "操作失败", null);
                    else
                        return Response.ok("操作成功");
                }
                return Response.error(403, "该邮箱地址已被使用", null);
            }
            return Response.error(403, "未登录或用户权限不足", null);
        }
    }

    @PostMapping("/register")
    @ApiOperation(value="用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户昵称",dataType="string",required=true),
            @ApiImplicitParam(name="avatar",value="用户头像",dataType="string",required=true),
            @ApiImplicitParam(name="password",value="用户密码",dataType="string",required=true),
            @ApiImplicitParam(name="email",value="用户邮箱地址",dataType="string",required=true)
    })
    public Response<String> register(BlogUser user){
        if(user == null || user.getUid() != null){
            return Response.error(400, "参数错误", null);
        }else{
            BlogUser tmp = blogUserService.selectByEmail(user.getEmail());
            if(tmp == null){
                user.setToken("");
                user.setType(0);
                int i = blogUserService.insert(user);
                if(i == 0)
                    return Response.error(403, "操作失败", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "该邮箱地址已被使用", null);
        }
    }

    @DeleteMapping("")
    @ApiOperation(value="删除指定用户（管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
        @ApiImplicitParam(name="uid",value="用户Id",dataType="int",required=true)
    })
    public Response<String> delete(String token, Integer uid){
        if(uid == null || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证管理员
            BlogUser b = blogUserService.selectByToken(token);
            if(b != null && b.getType() == 2){
                int i = blogUserService.deleteByUid(uid);
                if(i == 0)
                    return Response.error(404, "目标用户不存在", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "未登录或用户权限不足", null);
        }
    }
}

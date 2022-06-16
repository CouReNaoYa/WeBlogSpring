package com.yc.blog.controller;

import com.yc.blog.bean.BlogClassify;
import com.yc.blog.bean.BlogUser;
import com.yc.blog.common.Response;
import com.yc.blog.service.BlogClassifyService;
import com.yc.blog.service.BlogUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags="分类管理")
@RequestMapping("/api/classify")
public class ClassifyController {

    @Autowired
    BlogClassifyService blogClassifyService;

    @Autowired
    BlogUserService blogUserService;

    @GetMapping("")
    @ApiOperation(value="获取指定分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="分类Id",dataType="int",required=true)
    })
    public Response<BlogClassify> get(Integer id){
        if(id == null){
            return Response.error(400, "参数错误", null);
        }else{
            BlogClassify c = blogClassifyService.selectById(id);
            if(c == null){
                return Response.error(404, "分类不存在", null);
            }
            return Response.ok(c);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value="获取所有分类")
    public Response<List<BlogClassify>> getAll(){
        List<BlogClassify> cl = blogClassifyService.selectAll();
        return Response.ok(cl);
    }

    @PutMapping("")
    @ApiOperation(value="更新指定分类（管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="分类Id",dataType="int",required=true),
        @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
        @ApiImplicitParam(name="name",value="分类名称",dataType="string",required=true),
        @ApiImplicitParam(name="info",value="分类描述",dataType="string",required=true)
    })
    public Response<String> update(String token, BlogClassify classify){
        if(classify == null || classify.getId() == null || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证管理员
            BlogUser b = blogUserService.selectByToken(token);
            if(b != null && b.getType() == 2) {
                int i = blogClassifyService.update(classify);
                if (i == 0)
                    return Response.error(404, "分类不存在", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "用户权限不足", null);
        }
    }

    @PostMapping("")
    @ApiOperation(value="新增分类（管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
        @ApiImplicitParam(name="name",value="分类名称",dataType="string",required=true),
        @ApiImplicitParam(name="info",value="分类描述",dataType="string",required=true)
    })
    public Response<String> insert(String token, BlogClassify classify){
        if(classify == null || classify.getId() != null || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证管理员
            BlogUser b = blogUserService.selectByToken(token);
            if(b != null && b.getType() == 2) {
                int i = blogClassifyService.insert(classify);
                if (i == 0)
                    return Response.error(403, "操作失败", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "用户权限不足", null);
        }
    }

    @DeleteMapping("")
    @ApiOperation(value="删除指定分类（管理员）")
    public Response<String> delete(String token, Integer id){
        if(id == null || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证管理员
            BlogUser b = blogUserService.selectByToken(token);
            if(b != null && b.getType() == 2) {
                int i = blogClassifyService.deleteById(id);
                if (i == 0)
                    return Response.error(403, "操作失败", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "用户权限不足", null);
        }
    }
}

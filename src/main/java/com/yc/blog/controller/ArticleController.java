package com.yc.blog.controller;

import com.yc.blog.bean.BlogArticle;
import com.yc.blog.bean.BlogClassify;
import com.yc.blog.bean.BlogUser;
import com.yc.blog.common.Response;
import com.yc.blog.service.BlogArticleService;
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
@Api(tags="文章管理")
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    BlogArticleService blogArticleService;

    @Autowired
    BlogUserService blogUserService;

    @Autowired
    BlogClassifyService blogClassifyService;

    @GetMapping("")
    @ApiOperation(value="获取指定文章")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="文章Id",dataType="int",required=true)
    })
    public Response<BlogArticle> get(Integer id){
        if(id == null){
            return Response.error(400, "参数错误", null);
        }else{
            BlogArticle a = blogArticleService.selectById(id);
            if(a == null){
                return Response.error(404, "文章不存在", null);
            }
            return Response.ok(a);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value="获取所有文章（流加载）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="quantity",value="获取数量",dataType="int",required=true),
            @ApiImplicitParam(name="page",value="页码",dataType="int",required=true)
    })
    public Response<List<BlogArticle>> getAll(Integer quantity, Integer page){
        if(quantity==null||page==null){
            return Response.error(400, "参数错误", null);
        }else{
            if(quantity <= 0){
                quantity = 10;
            }
            if(page <= 0){
                page = 1;
            }
            return Response.ok(blogArticleService.selectAll(quantity, page));
        }

    }

    @GetMapping("/byClassify")
    @ApiOperation(value="根据分类Id获取文章（流加载）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classify",value="分类Id",dataType="int",required=true),
            @ApiImplicitParam(name="quantity",value="获取数量",dataType="int",required=true),
            @ApiImplicitParam(name="page",value="页码",dataType="int",required=true)
    })
    public Response<List<BlogArticle>> getByClassify(Integer classify, Integer quantity, Integer page){
        if(classify==null||quantity==null){
            return Response.error(400, "参数错误", null);

        }else{
            if(quantity <= 0){
                quantity = 10;
            }
            if(page <= 0){
                page = 1;
            }
            return Response.ok(blogArticleService.selectByClassify(classify, quantity, page));
        }

    }

    @GetMapping("/byUser")
    @ApiOperation(value="根据用户Id获取文章（流加载）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="user",value="用户Id",dataType="int",required=true),
            @ApiImplicitParam(name="quantity",value="获取数量",dataType="int",required=true),
            @ApiImplicitParam(name="page",value="页码",dataType="int",required=true)
    })
    public Response<List<BlogArticle>> getByUser(Integer user, Integer quantity, Integer page){
        if(user==null||quantity==null||page==null){
            return Response.error(400, "参数错误", null);
        }else{
            if(quantity <= 0){
                quantity = 10;
            }
            if(page <= 0){
                page = 1;
            }
            return Response.ok(blogArticleService.selectByUser(user, quantity, page));
        }

    }

    @GetMapping("/search")
    @ApiOperation(value="根据文章标题或文章内容检索文章（流加载）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="text",value="关键字",dataType="string",required=true),
            @ApiImplicitParam(name="quantity",value="获取数量",dataType="int",required=true),
            @ApiImplicitParam(name="page",value="页码",dataType="int",required=true)
    })
    public Response<List<BlogArticle>> search(String text, Integer quantity, Integer page){
        if(text==null||quantity==null||page==null){
            return Response.error(400, "参数错误", null);
        }else{
            if(quantity <= 0){
                quantity = 10;
            }
            if(page <= 0){
                page = 1;
            }
            return Response.ok(blogArticleService.search(text, quantity, page));
        }
    }

    @PutMapping("")
    @ApiOperation(value="更新指定文章（文章作者/管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
        @ApiImplicitParam(name="id",value="文章Id",dataType="int",required=true),
        @ApiImplicitParam(name="classify",value="文章所属分类Id",dataType="int",required=true),
        @ApiImplicitParam(name="name",value="文章名称",dataType="string",required=true),
        @ApiImplicitParam(name="content",value="文章内容",dataType="string",required=true),

    })
    public Response<String> update(String token, BlogArticle article){
        if(article == null || article.getId() == null || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证用户身份，验证文章所有者
            BlogUser u = blogUserService.selectByToken(token);
            BlogArticle a = blogArticleService.selectById(article.getId());
            BlogClassify c = blogClassifyService.selectById(article.getClassify());
            if(c == null){
                return Response.error(404, "分类不存在", null);
            }
            if(u != null && (a.getAuthor().equals(u.getUid()) || u.getType() == 2)){
                article.setSummary(article.getContent().replaceAll("</?.+?/?>", "").replaceAll("\\s", "").replaceAll("&(lt|gt|nbsp|amp|quot);",""));
                int i = blogArticleService.update(article);
                if(i == 0)
                    return Response.error(404, "文章不存在", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "用户权限不足", null);
        }
    }

    @PostMapping("")
    @ApiOperation(value="新增文章（作者/管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
        @ApiImplicitParam(name="classify",value="文章所属分类Id",dataType="int",required=true),
        @ApiImplicitParam(name="name",value="文章名称",dataType="string",required=true),
        @ApiImplicitParam(name="content",value="文章内容",dataType="string",required=true),
            @ApiImplicitParam(name="picture",value="文章图片",dataType="string",required=true)

    })
    public Response<String> insert(String token, BlogArticle article){
        if(article == null  || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证用户身份，必须是作者以上身份
            BlogUser u = blogUserService.selectByToken(token);
            BlogClassify c = blogClassifyService.selectById(article.getClassify());
            if(c == null){
                return Response.error(404, "分类不存在", null);
            }
            if(u != null && u.getUid() >= 1){
                article.setAuthor(u.getUid());
                article.setSummary(article.getContent().replaceAll("</?.+?/?>", "").replaceAll("\\s", "").replaceAll("&(lt|gt|nbsp|amp|quot);",""));
                int i = blogArticleService.insert(article);
                if(i == 0)
                    return Response.error(403, "操作失败", null);
                else
                    return Response.ok("操作成功");
            }
            return Response.error(403, "用户权限不足", null);
        }
    }

    @DeleteMapping("")
    @ApiOperation(value="删除指定文章（文章作者/管理员）")
    @ApiImplicitParams({
        @ApiImplicitParam(name="token",value="用户Token",dataType="string",required=true),
        @ApiImplicitParam(name="id",value="文章Id",dataType="int",required=true)
    })
    public Response<String> delete(String token, Integer id){
        if(id == null || token == null){
            return Response.error(400, "参数错误", null);
        }else{
            // 验证用户身份
            BlogUser u = blogUserService.selectByToken(token);
            BlogArticle a = blogArticleService.selectById(id);
            // 目标用户必须存在，且用户必须是作者以上身份
            if(u != null && u.getUid() >= 1){
                // 当前token是管理员或是目标文章作者
                if(u.getType() == 2 || u.getUid() == a.getAuthor()){
                    int i = blogArticleService.deleteById(id);
                    if(i == 0)
                        return Response.error(403, "操作失败", null);
                    else
                        return Response.ok("操作成功");
                }
            }
            return Response.error(403, "用户权限不足", null);
        }
    }
}

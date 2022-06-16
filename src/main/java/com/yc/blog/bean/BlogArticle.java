package com.yc.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class BlogArticle {
    @ApiModelProperty(value="文章Id")
    private Integer id;

    @ApiModelProperty(value="文章作者Id")
    private Integer author;

    @ApiModelProperty(value="文章作者头像avatar")
    private String authorAvatar;



    @ApiModelProperty(value="文章作者昵称")
    private String authorName;

    @ApiModelProperty(value="文章所属分类Id")
    private Integer classify;

    @ApiModelProperty(value="文章所属分类名称")
    private String classifyName;

    @ApiModelProperty(value="文章状态")
    private Integer status;

    @ApiModelProperty(value="文章创建时间")
    private Date createTime;

    @ApiModelProperty(value="文章最后更新时间")
    private Date updateTime;

    @ApiModelProperty(value="文章名称")
    private String name;

    @ApiModelProperty(value="文章内容")
    private String content;

    @ApiModelProperty(value="文章摘要")
    private String summary;

    @ApiModelProperty(value="文章图片")
    private String picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
package com.yc.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class BlogClassify {
    @ApiModelProperty(value="分类Id")
    private Integer id;

    @ApiModelProperty(value="分类名称")
    private String name;

    @ApiModelProperty(value="分类描述")
    private String info;

    @ApiModelProperty(value="分类包含文章数")
    private Integer count;

    @ApiModelProperty(value="分类创建时间")
    private Date createTime;

    @ApiModelProperty(value="分类最后更新时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
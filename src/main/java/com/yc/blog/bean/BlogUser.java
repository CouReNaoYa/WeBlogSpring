package com.yc.blog.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class BlogUser {
    @ApiModelProperty(value="用户Id")
    private Integer uid;

    @ApiModelProperty(value="用户昵称")
    private String name;

    @ApiModelProperty(value="用户类型")
    private Integer type;

    @ApiModelProperty(value="用户头像")
    private String avatar;

    @ApiModelProperty(value="用户背景")
    private String background;

    @ApiModelProperty(value="用户发布文章数")
    private Integer count;

    @JsonIgnore
    @ApiModelProperty(value="用户密码")
    private String password;

    @JsonIgnore
    @ApiModelProperty(value="用户Token")
    private String token;

    @ApiModelProperty(value="用户邮件地址")
    private String email;

    @ApiModelProperty(value="用户创建时间")
    private Date createTime;

    @ApiModelProperty(value="用户最后更新时间")
    private Date updateTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
package com.sole.saas.supplier.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
  * @description: 系统用户信息响应实体.
  * @author: wjd
  * @date: 2022/5/10
  */
@ApiModel(value="系统用户信息响应实体")
@Data
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 7162870356199324318L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "登录账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String tel;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "登录密码")
    private String pwd;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "是否是管理员")
    private Integer isManage;

    @ApiModelProperty(value = "是否是部门负责人")
    private Integer isDeptCharge;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "所属部门")
    private Integer departId;

    @ApiModelProperty(value = "是否在职")
    private Integer isOnJob;

    @ApiModelProperty(value = "上级领导")
    private Integer topLead;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "上级领导名称")
    private String topLeadName;

    @ApiModelProperty(value = "所属部门名称")
    private String departName;

    @ApiModelProperty(value = "用户所拥有的角色ID集")
    private List<Integer> roleIdList = new ArrayList<>();

    @ApiModelProperty(value = "用户所拥有的角色名称集")
    private List<String> roleNameList = new ArrayList<>();




}
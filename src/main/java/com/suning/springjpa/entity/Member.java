package com.suning.springjpa.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @NotBlank(message = "客户名不可为空！")
    private String memberName;
    @NotBlank(message = "客户手机号不可为空！")
    private String phoneNo;
    @NotNull(message = "客户积分不可为空！")
    private Integer points;
    private Integer age;
    private Date createTime;
    private Date updateTime;

    public Long getMemberId() {
	return this.memberId;
    }

    public void setMemberId(Long memberId) {
	this.memberId = memberId;
    }

    public String getMemberName() {
	return this.memberName;
    }

    public void setMemberName(String memberName) {
	this.memberName = memberName;
    }

    public String getPhoneNo() {
	return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
    }

    public Integer getPoints() {
	return this.points;
    }

    public void setPoints(Integer points) {
	this.points = points;
    }

    public Date getCreateTime() {
	return this.createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public Date getUpdateTime() {
	return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
    }

    public Integer getAge() {
	return this.age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }
}
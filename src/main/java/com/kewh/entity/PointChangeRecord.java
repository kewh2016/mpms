package com.kewh.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_point_change_record")
public class PointChangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Date changeTime;
    private String type;
    private String reason;

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getMemberId() {
	return this.memberId;
    }

    public void setMemberId(Long memberId) {
	this.memberId = memberId;
    }

    public Date getChangeTime() {
	return this.changeTime;
    }

    public void setChangeTime(Date changeTime) {
	this.changeTime = changeTime;
    }

    public String getType() {
	return this.type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getReason() {
	return this.reason;
    }

    public void setReason(String reason) {
	this.reason = reason;
    }
}
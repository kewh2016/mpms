package com.suning.springjpa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.suning.springjpa.dao.MemberDao;
import com.suning.springjpa.dao.PointChangeRecordDao;
import com.suning.springjpa.dao.ProductDao;
import com.suning.springjpa.entity.Member;
import com.suning.springjpa.entity.PointChangeRecord;
import com.suning.springjpa.entity.Product;
import com.suning.springjpa.exception.BizException;
import com.suning.springjpa.service.MemberService;
import com.suning.springjpa.service.ProductService;
import com.suning.springjpa.tool.BakService;

@Controller
public class MemberController {
    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private PointChangeRecordDao pointChangeRecordDao;
    @Autowired
    private BakService bakService;

    @RequestMapping({ "/list" })
    public String memberList(Member member, Model model) {
	String phoneNo = member.getPhoneNo();
	String memberName = member.getMemberName();
	List<Member> members = this.memberService.findAll(phoneNo, memberName);
	model.addAttribute("members", members);
	model.addAttribute("member", member);
	model.addAttribute("products", this.productService.findProducts());
	return "memberList";
    }

    @RequestMapping({ "/pointChange" })
    @ResponseBody
    public String pointChange(String pointFlag, Long memberId, @RequestParam(defaultValue = "0") int points,
	    Long productId, String reason) {
	HashMap<String, String> map = new HashMap<>();

	try {
	    if ("1".equals(pointFlag)) {
		Product e = (Product) this.productDao.findOne(productId);
		points = e.getPoint().intValue();
		e.setStock(Integer.valueOf(e.getStock().intValue() - 1));
		e.setUpdateTime(new Date());
		this.productDao.save(e);
		this.memberService.pointIncrease(points, memberId, reason);
	    }

	    if ("-1".equals(pointFlag)) {
		this.memberService.pointDeduction(points, memberId, reason);
	    }
	} catch (BizException arg7) {
	    map.put("success", "false");
	    map.put("message", arg7.getMessage());
	    LOG.error(arg7.getMessage());
	    return JSON.toJSONString(map);
	}

	map.put("success", "true");
	map.put("message", "积分变更成功!");
	this.bakService.bak();
	LOG.info("积分变更成功!客户ID：{}", memberId);
	return JSON.toJSONString(map);
    }

    @RequestMapping({ "/memberManager" })
    public String memberManager(Model model) {
	List<Member> members = this.memberService.findAll((String) null, (String) null);
	model.addAttribute("members", members);
	return "memberManager";
    }

    @RequestMapping({ "/memberEdit" })
    public String memberEdit(Long memberId, Model model) {
	if (memberId != null) {
	    model.addAttribute("member", this.memberDao.findOne(memberId));
	}

	return "memberEdit";
    }

    @RequestMapping({ "/memberSave" })
    @ResponseBody
    public String memberSave(Long memberId, String memberName, Integer age, String phoneNo, Model model) {
	Member member = null;
	HashMap<String, String> map = new HashMap<>();

	try {
	    Date e = new Date();
	    if (memberId != null) {
		member = (Member) this.memberDao.findOne(memberId);
		member.setMemberName(memberName);
		member.setPhoneNo(phoneNo);
		member.setAge(age);
		member.setUpdateTime(e);
	    } else {
		member = new Member();
		member.setMemberName(memberName);
		member.setPhoneNo(phoneNo);
		member.setCreateTime(e);
		member.setAge(age);
		member.setPoints(Integer.valueOf(0));
	    }

	    this.memberDao.save(member);
	} catch (Exception arg9) {
	    LOG.error("保存客户信息出错", arg9);
	    map.put("success", "false");
	    map.put("message", "客户信息保存失败！");
	    return JSON.toJSONString(map);
	}

	try {
	    this.bakService.bak();
	} catch (Exception arg8) {
	    LOG.error("备份客户信息出错", arg8);
	}

	map.put("success", "true");
	map.put("message", "客户信息保存成功！");
	return JSON.toJSONString(map);
    }

    @RequestMapping({ "/viewHistory" })
    public String viewPointChangeHstory(Long memberId, Model model) throws BizException {
	if (memberId == null) {
	    throw new BizException("客户号不正确！");
	} else {
	    Member member = (Member) this.memberDao.findOne(memberId);
	    List<PointChangeRecord> list = this.pointChangeRecordDao.findAllByMemberId(memberId);
	    model.addAttribute("member", member);
	    model.addAttribute("historyList", list);
	    return "history";
	}
    }
}
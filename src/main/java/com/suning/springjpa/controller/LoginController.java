package com.suning.springjpa.controller;

import com.alibaba.fastjson.JSON;
import com.suning.springjpa.dao.AdminDao;
import com.suning.springjpa.entity.Admin;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private AdminDao adminDao;

    @RequestMapping({ "/" })
    public String index() {
	return "index";
    }

    @RequestMapping({ "/login" })
    public String login() {
	return "login";
    }

    @RequestMapping({ "/loginOut" })
    public String loginOut(HttpSession session) {
	session.removeAttribute("admin");
	return "redirect:/login";
    }

    @RequestMapping({ "/changePassword" })
    public String changePassword() {
	return "changePassword";
    }

    @RequestMapping({ "/changePasswordSubmit" })
    @ResponseBody
    public String changePassword(HttpSession session, String oldPassword, String newPassword, String reNewPassword) {
	HashMap<String, String> map = new HashMap<>();
	String password = this.getAdminPassword();
	if (password != null && password.equals(DigestUtils.md5Hex(oldPassword))) {
	    if (!StringUtils.isEmpty(newPassword) && newPassword.equals(reNewPassword)) {
		List<Admin> findAll = this.adminDao.findAll();
		Iterator<Admin> arg8 = findAll.iterator();

		while (arg8.hasNext()) {
		    Admin admin = (Admin) arg8.next();
		    admin.setPassword(DigestUtils.md5Hex(newPassword));
		    this.adminDao.saveAndFlush(admin);
		}

		map.put("type", "0");
		map.put("message", "修改密码成功！请重新登录。");
		session.removeAttribute("admin");
		return JSON.toJSONString(map);
	    } else {
		map.put("type", "1");
		map.put("message", "新密码不一致或新密码为空！请重新输入。");
		return JSON.toJSONString(map);
	    }
	} else {
	    map.put("type", "2");
	    map.put("message", "原密码错误！修改密码失败！");
	    session.removeAttribute("admin");
	    return JSON.toJSONString(map);
	}
    }

    private String getAdminPassword() {
	List<Admin> findAll = this.adminDao.findAll();
	if (CollectionUtils.isEmpty(findAll)) {
	    Admin password2 = new Admin();
	    password2.setPassword(DigestUtils.md5Hex("21232f297a57a5a743894a0e4a801fc3"));
	    findAll.add(password2);
	    this.adminDao.saveAndFlush(password2);
	}

	String password21 = ((Admin) findAll.get(0)).getPassword();
	return password21;
    }

    @RequestMapping({ "/loginSubmit" })
    @ResponseBody
    public String loginSubmit(HttpSession session, String password) {
	HashMap<String, String> map = new HashMap<>();
	String password2 = this.getAdminPassword();
	if (password2.equals(DigestUtils.md5Hex(password))) {
	    map.put("type", "0");
	    session.setAttribute("admin", "true");
	    return JSON.toJSONString(map);
	} else {
	    map.put("type", "1");
	    return JSON.toJSONString(map);
	}
    }
}
package com.SnsOa.system.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.service.CompanyService;
import com.SnsOa.entity.service.ProgramService;
@Service("SystemPermission")
public class SystemPermission {
	@Resource(name="CompanyServiceImpl")
	private CompanyService comService;
	@Resource(name="ProgramServiceImpl")
	private ProgramService proService;
	/**
	 * ÑéÖ¤ÊÇ·ñµÇÂ¼
	 * @param req
	 * @param res
	 * @return
	 */
	public boolean isLoginRequest(HttpServletRequest req,HttpServletResponse res){
		if(req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)==null){
			return false;
		}else{
			return true;
		}
	}
}

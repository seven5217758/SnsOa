package com.SnsOa.spring.controller.system;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.service.ProgramService;
import com.SnsOa.system.util.SystemPermission;
import com.SnsOa.system.util.SystemSessionAttreName;
@Controller
public class UsersAppManager {
	private ServletContext sc;
	@Resource(name="messageSource")
	private ResourceBundleMessageSource messageSource;
	private static final Logger logger = LoggerFactory
	.getLogger(SystemManagerController.class);
	@Resource(name="ProgramServiceImpl")
	private ProgramService proService;
	@Resource(name="SystemPermission")
	private SystemPermission permission;
	@RequestMapping(value="UserAppList")
	public void userAppList(HttpServletRequest req,HttpServletResponse res){
		if(permission.isLoginRequest(req, res)){
			try {
				res.setCharacterEncoding("UTF-8");
				List<ProgramItem> pro=proService.getAllProgramList();
				StringBuffer jsonbegin=new StringBuffer();
				jsonbegin.append("[");
				int forindex=1;
				long companyId=((Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getCompanyId();
				//用于判断是否已经定制
				boolean isAddEnable = false;
				for(ProgramItem i:pro){
						if(i.isEnable()){
							if(forindex>1){
								jsonbegin.append(",");
							}
								//只有在第一次循环的时候去执行。这样提升效率。
							isAddEnable=proService.isCompanyProgram(companyId, i.getProgramId());
							forindex++;
							jsonbegin.append("{name: '").append(i.getItemName()).append("',cuisine:'").append(proService.getProgramName(i.getProgramId())).append("',number:'").append(i.getId()).append("','isenable':'").append(isAddEnable).append("'}");
						}
				}
				jsonbegin.append("]");
				res.getWriter().print(jsonbegin.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				res.getWriter().print("{failure:true}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 用于用户定制应用程序
	 */
	@RequestMapping(value="addAppToUser")
	public void addAppToUser(HttpServletRequest req,HttpServletResponse res,long id){
		if(permission.isLoginRequest(req, res)){
		long pid=proService.getProgramItem(id).getProgramId();
		long companyid=((Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getCompanyId();
		try {
			if(proService.isCompanyProgram(companyid, pid)){
				res.getWriter().print("{success:"+proService.removeProgramToCompany(companyid, pid)+"}");
			}else{	
				res.getWriter().print("{success:"+proService.addProgramToCompany(companyid, pid)+"}");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			try {
				res.getWriter().print("{failure:true}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

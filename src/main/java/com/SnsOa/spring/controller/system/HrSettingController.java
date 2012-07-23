package com.SnsOa.spring.controller.system;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.SnsOa.system.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SnsOa.entity.Program;
import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.ProgramLinkRole;
import com.SnsOa.entity.Roles;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.service.CompanyService;
import com.SnsOa.entity.service.ProgramService;
import com.SnsOa.system.util.I18NStringName;
import com.SnsOa.system.util.SystemPermission;
import com.SnsOa.system.util.SystemSessionAttreName;

@Controller
public class HrSettingController {
	@Resource(name="SystemPermission")
	private SystemPermission permission;
	@Resource(name="CompanyServiceImpl")
	private CompanyService company;
	@Resource(name="messageSource")
	private ResourceBundleMessageSource messageSource;
	@Resource(name="ProgramServiceImpl")
	private ProgramService proService;
	private static final Logger logger = LoggerFactory
	.getLogger(HrSettingController.class);
	@RequestMapping(value="getCompanyUsers")
	public void getCompanyUsers(HttpServletRequest req,HttpServletResponse res){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			Users user=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			long companyid=user.getCompanyId();
			List<Users> userlist=company.getCompanyUsers(companyid);
			StringBuffer jsonbegin=new StringBuffer();
			jsonbegin.append("[");
			int forindex=1;
			for(Users i:userlist){
				if(forindex>1){
					jsonbegin.append(",");
				}
				forindex++;
				String rolesName=null;
				if(i.getRole()==0){
					//没有分配
					rolesName=messageSource.getMessage(I18NStringName.NOT_ROLES_ALLOCATION, null,Locale.CHINESE);
				}else{
					if(company.getRolesById(i.getRole())==null){
						rolesName=messageSource.getMessage(I18NStringName.NOT_ROLES_ERROR_DELETE, null,Locale.CHINESE);
					}else{
						rolesName=company.getRolesById(i.getRole()).getName();
					}
				}
				jsonbegin.append("{account: '").append(i.getAccount()).append("',password:'*',email:'").append(i.getEmail()).append("',fname:'").append(i.getFname()).append("',lname:'").append(i.getLname()).append("',number:'").append(i.getId()).append("',role:'").append(rolesName).append("'}");
			}
			jsonbegin.append("]");
			try {
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
	@RequestMapping(value="getCompanyRoles")
	public void getCompanyRoles(HttpServletRequest req,HttpServletResponse res){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			Users user=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			long companyid=user.getCompanyId();
			List<Roles> rolesList=company.getCompanyRoles(companyid);
			StringBuffer jsonbegin=new StringBuffer();
			jsonbegin.append("[");
			int forindex=1;
			for(Roles i:rolesList){
				if(forindex>1){
					jsonbegin.append(",");
				}
				jsonbegin.append("{number:'").append(i.getId()).append("',name:'").append(i.getName()).append("',marks:'").append(i.getMarks()).append("'}");
				forindex++;
			}
			jsonbegin.append("]");
			try {
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
	@RequestMapping(value="addRoles")
	public void addRoles(HttpServletRequest req,HttpServletResponse res,String rolesName,String rolesMarks){
		if(permission.isLoginRequest(req, res)){
			Users user=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			long companyid=user.getCompanyId();
			Roles roles=new Roles();
			roles.setMarks(rolesMarks);
			roles.setCompanyId(companyid);
			roles.setName(rolesName);
			try {
				res.getWriter().print("{success:"+company.addRoles(roles)+"}");
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
	@RequestMapping(value="deleteRoles")
	public void addRoles(HttpServletRequest req,HttpServletResponse res,long id){
		if(permission.isLoginRequest(req, res)){
			Users user=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			long companyid=user.getCompanyId();
			Roles roles=company.getRolesById(id);
			if(roles.getCompanyId()==companyid){
				try {
					res.getWriter().print("{success:"+company.deleteRoles(id)+"}");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}else{
				try {
					res.getWriter().print("{failure:true}");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	@RequestMapping(value="getRolesAppsItems")
	public void getRolesApps(HttpServletRequest req,HttpServletResponse res,long id){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				Users user=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
				long companyid=user.getCompanyId();
				List<ProgramLinkRole> rolesList=proService.getRolesAppList(id);
				StringBuffer jsonbegin=new StringBuffer();
				jsonbegin.append("[");
				int forindex=1;
				for(ProgramLinkRole i:rolesList){
					if(forindex>1){
						jsonbegin.append(",");
					}
					ProgramItem item=proService.getProgramItem(i.getProgramItemId());
					jsonbegin.append("{number:'").append(i.getId()).append("',name:'").append(item.getItemName()).append("'}");
					forindex++;
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
	@RequestMapping(value="getCompanyApps")
	public void getCompanyApps(HttpServletRequest req,HttpServletResponse res){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			Users user=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			long companyid=user.getCompanyId();
			List<Program> prolist=proService.getUserOfAppList(user.getId());
			StringBuffer jsonbegin=new StringBuffer();
			jsonbegin.append("[");
			int forindex=1;
			for(Program i:prolist){
				if(forindex>1){
					jsonbegin.append(",");
				}
				jsonbegin.append("{abbr:'").append(i.getId()).append("',name:'").append(i.getProgramName()).append("'}");
				forindex++;
			}
			jsonbegin.append("]");
			try {
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
	@RequestMapping(value="getAppItemsList")
	public void getCompanyApps(HttpServletRequest req,HttpServletResponse res,long id){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			List<ProgramItem> programlist=proService.getProgramAllItem(id);
			StringBuffer jsonbegin=new StringBuffer();
			jsonbegin.append("[");
			int forindex=1;
			for(ProgramItem i:programlist){
				if(forindex>1){
					jsonbegin.append(",");
				}
				jsonbegin.append("{abbr:'").append(i.getId()).append("',name:'").append(i.getItemName()).append("'}");
				forindex++;
			}
			jsonbegin.append("]");
			try {
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
	@RequestMapping(value="addRolesAppItem")
	public void addRolesAppItem(HttpServletRequest req,HttpServletResponse res,long itemid,long rolesId){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
				try {
					System.out.println(rolesId+"   "+itemid);
					res.getWriter().print("{success:"+proService.addProgramItemToRoles(rolesId, itemid)+"}");
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
	@RequestMapping(value="deleteRolesAppItem")
	public void deleteRolesAppItem(HttpServletRequest req,HttpServletResponse res,long id){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
				try {
					res.getWriter().print("{success:"+proService.deleteProgramItemFromRoles(id)+"}");
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
	 * 删除用户，不能删除注册时系统给予的主账户
	 * @param req
	 * @param res
	 * @param id
	 */
	@RequestMapping(value="deleteUser")
	public void deleteUser(HttpServletRequest req,HttpServletResponse res,long id){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				Users user=company.getUserInfo(id);
				if(!user.isMainAccount()){
					res.getWriter().print("{success:"+company.deleteUserInfo(id)+"}");
				}else{
					res.getWriter().print("{failure:true}");
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
	/**
	 * 更细用户信息
	 * @param req
	 * @param res
	 * @param id
	 * @param fname
	 * @param lname
	 * @param account
	 * @param password
	 * @param email
	 */
	@RequestMapping(value="updateUser")
	public void updateUser(HttpServletRequest req,HttpServletResponse res,long id,String fname,String lname,String account,String password,String email){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				Users loginUser=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
				Users user=company.getUserInfo(id);
					user.setEmail(email);
					user.setFname(fname);
					user.setLname(lname);
					if(!"*".equals(password)){
						user.setPassword(MD5.generatePassword(password));
					}
					user.setChangerAccount(true);
					res.getWriter().print("{success:"+company.updateUserInfo(user)+"}");
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
	 * 添加新的用户
	 * @param req
	 * @param res
	 * @param fname
	 * @param lname
	 * @param account
	 * @param password
	 * @param email
	 */
	@RequestMapping(value="addUser")
	public void updateUser(HttpServletRequest req,HttpServletResponse res,String fname,String lname,String account,String password,String email){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				Users loginUser=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
				Users user=new Users();
					user.setAccount(loginUser.getCompanyId()+"_"+account);
					user.setEmail(email);
					user.setFname(fname);
					user.setLname(lname);
					user.setCompanyId(loginUser.getCompanyId());
					user.setPassword(MD5.generatePassword(password));
					user.setChangerAccount(true);
					user.setMainAccount(false);
					res.getWriter().print("{success:"+company.addUser(user)+"}");
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
	@RequestMapping(value="addRolesToUser")
	public void updateUser(HttpServletRequest req,HttpServletResponse res,String fname,long id,long rid){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			Users user=company.getUserInfo(id);
			user.setRole(rid);
			try {
				res.getWriter().print("{success:"+company.updateUserInfo(user)+"}");
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
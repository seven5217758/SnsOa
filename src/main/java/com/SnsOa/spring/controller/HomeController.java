package com.SnsOa.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.SnsOa.entity.Company;
import com.SnsOa.entity.Program;
import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.service.CompanyService;
import com.SnsOa.entity.service.ProgramService;
import com.SnsOa.system.util.CharTransition;
import com.SnsOa.system.util.I18NStringName;
import com.SnsOa.system.util.IdentifyingCode;
import com.SnsOa.system.util.SystemInfo;
import com.SnsOa.system.util.SystemPermission;
import com.SnsOa.system.util.SystemSessionAttreName;
import com.sun.xml.bind.v2.runtime.Name;

/**
 * ��Ҫ����
 * 		�û�ע�᣺
 * 			1.�ϴ�ͼƬ��
 * 			2.�û���Ϣ��
 * 		�û���½:
 * 			1.��½��ء�
 * @author ������
 */
@Controller	
public class HomeController implements ServletContextAware{
	private long id=0;
	private ServletContext sc;
	@Resource(name="messageSource")
	private ResourceBundleMessageSource messageSource;
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Resource(name="CompanyServiceImpl")
	private CompanyService comService;
	@Resource(name="ProgramServiceImpl")
	private ProgramService proservice;
	@Resource(name="SystemPermission")
	private SystemPermission permission;
	/**
	 * ������ҳ
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");
		return "home";
	}
	@RequestMapping(value="/login")
	public String Login(HttpServletRequest req,HttpServletResponse res,Model model,String password,String account,String identifyingCode){
		identifyingCode=identifyingCode==null?"":identifyingCode;
		identifyingCode=CharTransition.toUTF_8(identifyingCode);
		IdentifyingCode I=new IdentifyingCode();
		
		if(req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)==null){
			if(I.isRight(identifyingCode, req)&&comService.Login(account, password)!=null){
				req.getSession(true).setAttribute(SystemSessionAttreName.ACCOUNT,comService.Login(account, password));
				this.setAppInfo(req, res, comService.Login(account, password).getId());
				return "userMain";
			}else{
				req.setAttribute(SystemSessionAttreName.MESSAGE_ERROR, messageSource.getMessage(I18NStringName.LOGIN_ERROR_MESSAGE,null, Locale.CHINESE));
				return "message/errorMessage";
			}
		}else{
			this.setAppInfo(req, res, ((Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getId());
			return "userMain";
		}
	}
	public void setAppInfo(HttpServletRequest req,HttpServletResponse res,long id){
		List<Program> proList=proservice.getUserOfAppList(id);
		String context=req.getContextPath();
		StringBuffer jsFile=new StringBuffer();
		StringBuffer loadClass=new StringBuffer();
		StringBuffer userMain=new StringBuffer();
		StringBuffer newObj=new StringBuffer();
		long companyId=((Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getCompanyId();
		long rolesId=((Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getRole();
		for(Program pro:proList){
			List<ProgramItem> itemList=proservice.getProgramAllItem(pro.getId());
			int index=0;
			for(ProgramItem i:itemList){
				if(proservice.isCompanyProgram(companyId, i.getProgramId())){
					if(proservice.isRolesProgram(rolesId, i.getId())){
						if(i.isEnable()){
							jsFile.append("<script type='text/javascript' src='").append(context).append("/resources/js/Ext_APP/").append(i.getAppFile()).append("'></script>");
							loadClass.append("'").append(i.getAppClass()).append("',");
							if(index!=0){
								newObj.append(",");
								userMain.append(",");
							}
							newObj.append("new ").append(i.getAppClass()).append("()");
							userMain.append("{name:'").append(i.getItemName()).append("',iconCls:'").append(i.getIcon()).append("',module:'").append(i.getAppId()).append("'}");
							index++;
						}
					}
				}
			}
		}
		req.setAttribute(SystemSessionAttreName.JSFILE, jsFile.toString());
		req.setAttribute(SystemSessionAttreName.LOADCLASS, loadClass.toString());
		req.setAttribute(SystemSessionAttreName.NEWOBJ, newObj.toString());
		req.setAttribute(SystemSessionAttreName.USERMAIN, userMain.toString());
	}
	//�������νṹ�ĸ��ڵ��ȡ���ݣ����������ӽڵ��json
	@RequestMapping(value="/companyTypeTree")
	public void companyTypeTree(HttpServletRequest req,HttpServletResponse res,Model model,long pNode){
		try {
			id++;
			res.getWriter().println("[{id:'"+id+"',text:'nihao"+id+"'},{id:'11"+id+"',text:'nihaoe"+id+"'}]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ע��
	 */
	@RequestMapping(value="/register")
	public String registerAccount(HttpServletRequest req,HttpServletResponse res,String companyType,String companyName,String corporateMan,String registerfund,String identifyingCode){
			Company company=new Company();
			company.setCompanyName(CharTransition.toUTF_8(companyName));
			company.setCompanyType(11);
			company.setCorporateMan(CharTransition.toUTF_8(corporateMan));
			company.setRegisterfund(registerfund);
			IdentifyingCode I=new IdentifyingCode();
			String account=null;
				identifyingCode=CharTransition.toUTF_8(identifyingCode);
				if(I.isRight(identifyingCode, req)){
					//ɾ������֤�롣�����ظ��ύ
					req.getSession(true).removeAttribute(SystemSessionAttreName.REGISTER_CODE);
					account=comService.registerCompany(company);
					req.setAttribute(SystemSessionAttreName.ACCOUNT, account);
					return "message/succeedMessage";
				}else{
					//ɾ������֤�롣�����ظ��ύ
					req.getSession(true).removeAttribute(SystemSessionAttreName.REGISTER_CODE);
					req.setAttribute(SystemSessionAttreName.MESSAGE_ERROR, messageSource.getMessage(I18NStringName.REGISTER_ERROR_MESSAGE,null, Locale.CHINESE));
					return "message/errorMesseage";
				}
	}
	/**
	 * �ϴ�ͼƬ��
	 */
	@RequestMapping(value="/uploadImage")
	public void uploadImage(HttpServletRequest req,HttpServletResponse res){
		if(permission.isLoginRequest(req, res)){
			try {
				MultipartHttpServletRequest mult=(MultipartHttpServletRequest)req;
				MultipartFile mfile=mult.getFile("businessLicenselmage");
				File file=new File(SystemInfo.SYSTEM_USER+"1"+SystemInfo.USER_IMAGE);
				if(!file.exists()){
					file.mkdirs();
				}
				File file2=new File(SystemInfo.SYSTEM_USER+"1"+SystemInfo.USER_IMAGE+mfile.getName());
				mfile.transferTo(file2);
				res.getWriter().print("{success:true,Msg:'http://www.baidu.com'}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				res.getWriter().print("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ������֤��ͼƬ
	 * @param req
	 * @param res
	 */
	@RequestMapping(value="/image")
	public void identifyingCode(HttpServletRequest req,HttpServletResponse res){
		IdentifyingCode I=new IdentifyingCode();
		I.setHeader(res);
		logger.debug("��ȡ��֤��");
		I.generateImage(res, req, SystemSessionAttreName.REGISTER_CODE);
	}
	/**
	 * ajax������֤ ��֤��
	 * @param req
	 * @param res
	 */
	@RequestMapping(value="/ajaxIdentifyingCode")
	public void ajaxIdentifyingCode(HttpServletRequest req,HttpServletResponse res,String identifyingCode){
		IdentifyingCode I=new IdentifyingCode();
		try{
			if(I.isRight(identifyingCode, req)){
				res.getWriter().print("true");
			}else{
				res.getWriter().print("false");
			}
		}catch(Exception ex){
			
		}
	}
	@RequestMapping(value="/logout")
	public String logOut(HttpServletRequest req,HttpServletResponse res){
		if(permission.isLoginRequest(req, res)){
			req.getSession(true).removeAttribute(SystemSessionAttreName.ACCOUNT);
			return "home";
		}else{
			return "home";
		}
	}
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		sc=servletContext;
	}

}

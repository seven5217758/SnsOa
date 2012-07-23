package com.SnsOa.spring.controller.crm;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SnsOa.entity.Users;
import com.SnsOa.entity.crm.ClientCompany;
import com.SnsOa.entity.crm.ClientSource;
import com.SnsOa.entity.crm.LinkManInfo;
import com.SnsOa.entity.crm.service.LinkManManagerService;
import com.SnsOa.entity.service.CompanyService;
import com.SnsOa.system.util.CharTransition;
import com.SnsOa.system.util.DataGridPartPageConfig;
import com.SnsOa.system.util.I18NStringName;
import com.SnsOa.system.util.SystemPermission;
import com.SnsOa.system.util.SystemSessionAttreName;

@Controller
public class LinkManController {
	@Resource(name="SystemPermission")
	private SystemPermission permission;
	@Resource(name="LinkManManagerServiceImpl")
	private LinkManManagerService linkser;
	@Resource(name="CompanyServiceImpl")
	private CompanyService comService;
	@Resource(name="messageSource")
	private ResourceBundleMessageSource messageSource;
	private static final Logger logger = LoggerFactory.getLogger(LinkManController.class);
	/**
	 * 获取全部的客户来源类型
	 * @param req
	 * @param res
	 */
	@RequestMapping(value="getAllClientSource")
	public void getAllClientSource(HttpServletRequest req,HttpServletResponse res){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				List<ClientSource> list=linkser.getAllClientSource();
				StringBuffer jsonbegin=new StringBuffer();
				jsonbegin.append("[");
				int forindex=1;
				for(ClientSource i:list){
					if(forindex>1){
						jsonbegin.append(",");
					}
					forindex++;
					jsonbegin.append("{id:'").append(i.getId()).append("',name:'").append(i.getName()).append("'}");
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
	@RequestMapping(value="getLinkManAllInfo")
	public void getLinkManAllInfo(HttpServletRequest req,HttpServletResponse res,int page,String queryValue,String query){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			Users use=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			try {
				List<LinkManInfo> list=null;
				long rewCount=0;
				if("null".equals(query)||query==null||query.equals("")){
					if("null".equals(queryValue)||queryValue==null||queryValue.equals("")){
						list=linkser.getPartPageLinkManList("from LinkManInfo where companyId="+use.getCompanyId()+" order by dataDate desc", (page-1)*DataGridPartPageConfig.CRM_LINK_MAN_JS_COUNT_PAGE, DataGridPartPageConfig.CRM_LINK_MAN_JS_COUNT_PAGE);
						rewCount=linkser.getLinkManList("from LinkManInfo where companyId="+use.getCompanyId()+" order by dataDate desc").size();
					}else{
						list=linkser.getPartPageLinkManList("from LinkManInfo where companyId="+use.getCompanyId()+" and "+queryValue+" order by dataDate desc", (page-1)*DataGridPartPageConfig.CRM_LINK_MAN_JS_COUNT_PAGE, DataGridPartPageConfig.CRM_LINK_MAN_JS_COUNT_PAGE);
						rewCount=linkser.getLinkManList("from LinkManInfo where companyId="+use.getCompanyId()+" and "+queryValue+" order by dataDate desc").size();
					}
				}else{
					list=linkser.getPartPageLinkManList("from LinkManInfo where companyId="+use.getCompanyId()+" and concat(fname,lname) like '%"+CharTransition.toUTF_8(query)+"%'"+" order by dataDate desc", (page-1)*DataGridPartPageConfig.CRM_LINK_MAN_JS_COUNT_PAGE, DataGridPartPageConfig.CRM_LINK_MAN_JS_COUNT_PAGE);
					rewCount=linkser.getLinkManList("from LinkManInfo where companyId="+use.getCompanyId()+" and concat(fname,lname) like '%"+CharTransition.toUTF_8(query)+"%'"+" order by dataDate desc").size();
				}
				StringBuffer jsonbegin=new StringBuffer();
				
				jsonbegin.append("{totalCount:'"+rewCount+"','topics':[");
				int forindex=1;
				for(LinkManInfo i:list){
					if(forindex>1){
						jsonbegin.append(",");
					}
					forindex++;
					jsonbegin.append("{id:'").append(i.getId()).append("',primaryManId:'").append(comService.getUserInfo(i.getPrimaryManId())==null?"":(comService.getUserInfo(i.getPrimaryManId()).getFname()+comService.getUserInfo(i.getPrimaryManId()).getLname())).append("',primaryManIdValue:'").append(i.getPrimaryManId()).append("',fname:'").append(i.getFname()).append("',lname:'").append(i.getLname()).append("',sex:'").append(i.getSex()==1?messageSource.getMessage(I18NStringName.SEX_MAN, null,Locale.CHINESE):messageSource.getMessage(I18NStringName.SEX_WOMAN, null,Locale.CHINESE)).append("',sexValue:'").append(i.getSex()).append("',clientName:'").append(linkser.getClientCompany(i.getClientName())==null?"":linkser.getClientCompany(i.getClientName()).getClientCompanyName()).append("',clientNameValue:'").append(i.getClientName()).append("',job:'").append(i.getJob()).append("',phone:'").append(i.getPhone()).append("',tel:'").append(i.getTel()).append("',email:'").append(i.getEmail()).append("',superior:'").append(linkser.getLinkManInfo(i.getSuperior())==null?"":(linkser.getLinkManInfo(i.getSuperior()).getFname()+linkser.getLinkManInfo(i.getSuperior()).getLname())).append("',superiorValue:'").append(i.getSuperior()).append("',sendMailAddressCountry:'").append(i.getSendMailAddressCountry()).append("',sendMailAddressCode:'").append(i.getSendMailAddressCode()).append("',sendMailAddressProvince:'").append(i.getSendMailAddressProvince()).append("',sendMailAddressCity:'").append(i.getSendMailAddressCity()).append("',sendMailAddressStreet:'").append(i.getSendMailAddressStreet()).append("',otherSendMailAddressCountry:'").append(i.getOtherSendMailAddressCountry()).append("',otherSendMailAddressCode:'").append(i.getOtherSendMailAddressCode()).append("',otherSendMailAddressProvince:'").append(i.getOtherSendMailAddressProvince()).append("',otherSendMailAddressCity:'").append(i.getOtherSendMailAddressCity()).append("',otherSendMailAddressStreet:'").append(i.getOtherSendMailAddressStreet()).append("',fax:'").append(i.getFax()).append("',HomeTel:'").append(i.getHomeTel()).append("',otherTel:'").append(i.getOtherTel()).append("',assistent:'").append(i.getAssistent()).append("',assistentTel:'").append(i.getAssistentTel()).append("',deteOfbirth:'").append(i.getDeteOfbirth().toString().split(" ")[0]).append("',marks:'").append(i.getMarks()).append("',clientSource:'").append(linkser.getClientSourceItem(i.getClientSource())==null?"":linkser.getClientSourceItem(i.getClientSource()).getName()).append("',clientSourceValue:'").append(i.getClientSource()).append("'}");
				}
				jsonbegin.append("]}");
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
	@RequestMapping("getAllClientCompany")
	public void getAllClientCompany(HttpServletRequest req,HttpServletResponse res,int page,String query){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){

			List<ClientCompany> list=null;
			long rewCount=0;
			Users use=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
			list=linkser.getClientCompany("from ClientCompany where companyId="+use.getCompanyId()+" and clientCompanyName like '%"+(query==null?"":CharTransition.toUTF_8(query))+"%'"+" order by dataDate desc", DataGridPartPageConfig.CRM_LINK_MAN_JS_CLIENT_COMPANY_COUNT_PAGE, (page-1)*DataGridPartPageConfig.CRM_LINK_MAN_JS_CLIENT_COMPANY_COUNT_PAGE);
			rewCount=linkser.getClientCompany("from ClientCompany where companyId="+use.getCompanyId()+" and clientCompanyName like '%"+(query==null?"":CharTransition.toUTF_8(query))+"%'"+" order by dataDate desc", DataGridPartPageConfig.CRM_LINK_MAN_JS_CLIENT_COMPANY_COUNT_PAGE, (page-1)*DataGridPartPageConfig.CRM_LINK_MAN_JS_CLIENT_COMPANY_COUNT_PAGE).size();
			StringBuffer jsonbegin=new StringBuffer();
			
			jsonbegin.append("{totalCount:'"+rewCount+"','topics':[");
			int forindex=1;
			for(ClientCompany i:list){
				if(forindex>1){
					jsonbegin.append(",");
				}
				forindex++;
				jsonbegin.append("{id:'").append(i.getId()).append("',name:'").append(i.getClientCompanyName()).append("'}");
			}
			jsonbegin.append("]}");
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
	@RequestMapping("addClientCompany")
	public void addClientCompany(HttpServletRequest req,HttpServletResponse res,String name){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				Users use=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
				ClientCompany clientCompany=new ClientCompany();
				clientCompany.setClientCompanyName(name);
				clientCompany.setCompanyId(use.getCompanyId());
				clientCompany.setDataDate(new Date());
				res.getWriter().print("{success:"+linkser.saveClientCompany(clientCompany)+"}");
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
	@RequestMapping("addNewLinkManInfo")
	public void addNewLinkManInfo(HttpServletRequest req,HttpServletResponse res,String fname,String lname,String sex,String clientName,String job,String phone,String tel,String email,String superior,String sendMailAddressCountry,String sendMailAddressCode,String sendMailAddressProvince,String sendMailAddressCity,String sendMailAddressStreet,String otherSendMailAddressCountry,String otherSendMailAddressCode,String otherSendMailAddressProvince,String otherSendMailAddressCity,String otherSendMailAddressStreet,String fax,String HomeTel,String otherTel,String assistent,String assistentTel,String clientSource,String deteOfbirth,String marks){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				Users use=(Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
				LinkManInfo link=new LinkManInfo();
				link.setAssistent(assistent);
				link.setAssistentTel(assistentTel);
				if(clientName!=null&&(!clientName.equals(""))){
					link.setClientName(Long.parseLong(clientName));
				}
				if(clientSource!=null&&(!clientSource.equals(""))){
					link.setClientSource(Long.parseLong(clientSource));
				}
				link.setCompanyId(use.getCompanyId());
				link.setDeteOfbirth(new Date(deteOfbirth));
				link.setEmail(email);
				link.setFax(fax);
				link.setDataDate(new Date());
				link.setFname(fname);
				link.setLname(lname);
				link.setHomeTel(HomeTel);
				link.setJob(job);
				link.setMarks(marks);
				link.setOtherSendMailAddressCity(otherSendMailAddressCity);
				link.setOtherSendMailAddressCode(otherSendMailAddressCode);
				link.setOtherSendMailAddressCountry(otherSendMailAddressCountry);
				link.setOtherSendMailAddressProvince(otherSendMailAddressProvince);
				link.setOtherSendMailAddressStreet(otherSendMailAddressStreet);
				link.setOtherTel(otherTel);
				link.setPhone(phone);
				link.setPrimaryManId(use.getId());
				link.setSendMailAddressCity(sendMailAddressCity);
				link.setSendMailAddressCode(sendMailAddressCode);
				link.setSendMailAddressCountry(sendMailAddressCountry);
				link.setSendMailAddressProvince(sendMailAddressProvince);
				link.setSendMailAddressStreet(sendMailAddressStreet);
				if(sex!=null&&(!sex.equals(""))){
					link.setSex(Integer.parseInt(sex));
				}
				if(superior!=null&&(!superior.equals(""))){
					link.setSuperior(Integer.parseInt(superior));
				}
				link.setTel(tel);
				res.getWriter().print("{success:"+linkser.addNewLinkManInfo(link)+"}");
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
	@RequestMapping("updateLinkManInfo")
	public void updateLinkManInfo(HttpServletRequest req,HttpServletResponse res,long id,String fname,String lname,String sex,String clientName,String job,String phone,String tel,String email,String superior,String sendMailAddressCountry,String sendMailAddressCode,String sendMailAddressProvince,String sendMailAddressCity,String sendMailAddressStreet,String otherSendMailAddressCountry,String otherSendMailAddressCode,String otherSendMailAddressProvince,String otherSendMailAddressCity,String otherSendMailAddressStreet,String fax,String HomeTel,String otherTel,String assistent,String assistentTel,String clientSource,String deteOfbirth,String marks){
		res.setCharacterEncoding("UTF-8");
		if(permission.isLoginRequest(req, res)){
			try {
				LinkManInfo link=linkser.getLinkManInfo(id);
				link.setAssistent(assistent);
				link.setAssistentTel(assistentTel);
				if(clientName!=null&&(!clientName.equals(""))){
					link.setClientName(Long.parseLong(clientName));
				}
				if(clientSource!=null&&(!clientSource.equals(""))){
					link.setClientSource(Long.parseLong(clientSource));
				}
				link.setDeteOfbirth(new Date(deteOfbirth));
				link.setEmail(email);
				link.setFax(fax);
				link.setFname(fname);
				link.setLname(lname);
				link.setHomeTel(HomeTel);
				link.setJob(job);
				link.setMarks(marks);
				link.setOtherSendMailAddressCity(otherSendMailAddressCity);
				link.setOtherSendMailAddressCode(otherSendMailAddressCode);
				link.setOtherSendMailAddressCountry(otherSendMailAddressCountry);
				link.setOtherSendMailAddressProvince(otherSendMailAddressProvince);
				link.setOtherSendMailAddressStreet(otherSendMailAddressStreet);
				link.setOtherTel(otherTel);
				link.setPhone(phone);
				link.setSendMailAddressCity(sendMailAddressCity);
				link.setSendMailAddressCode(sendMailAddressCode);
				link.setSendMailAddressCountry(sendMailAddressCountry);
				link.setSendMailAddressProvince(sendMailAddressProvince);
				link.setSendMailAddressStreet(sendMailAddressStreet);
				if(sex!=null&&(!sex.equals(""))){
					link.setSex(Integer.parseInt(sex));
				}
				if(superior!=null&&(!superior.equals(""))){
					link.setSuperior(Integer.parseInt(superior));
				}
				link.setTel(tel);
				res.getWriter().print("{success:"+linkser.updateLinkManInfo(link)+"}");
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
	@RequestMapping("deleteLinkMan")
	public void deleteLinkMan(HttpServletRequest req,HttpServletResponse res,long id){
		if(permission.isLoginRequest(req, res)){
			try {
				res.getWriter().print("{success:"+linkser.removeLinkManInfo(id)+"}");
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

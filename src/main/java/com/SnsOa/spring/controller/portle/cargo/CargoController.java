package com.SnsOa.spring.controller.portle.cargo;

import java.io.IOException;
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

import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.portle.PortletPage;
import com.SnsOa.entity.service.cargo.CargoService;
import com.SnsOa.system.util.I18NStringName;
import com.SnsOa.system.util.SystemPermission;
import com.SnsOa.system.util.SystemSessionAttreName;

@Controller
public class CargoController {
	private static final Logger logger = LoggerFactory.getLogger(CargoController.class);
	@Resource(name="SystemPermission")
	private SystemPermission permission;
	@Resource(name="messageSource")
	private ResourceBundleMessageSource messageSource;
	@Resource(name="CargoServiceImpl")
	private CargoService cargoSer;
	@RequestMapping(value="portle/test")
	public String test(HttpServletRequest req,HttpServletResponse res){
		Object userObj=req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
		if(userObj!=null){
			return "ExtFormView/cargoMsgAndroidIphone/cargoMag";
		}else{
			req.setAttribute(SystemSessionAttreName.MESSAGE_ERROR, messageSource.getMessage(I18NStringName.THIS_PAGE_IS_NOT_EXIST,null, Locale.CHINESE));
			return "message/errorMessage";
		}
	}
	/**
	 * portlet
	 * @param req
	 * @param res
	 */
	@RequestMapping(value="/portlet/getAppPortletList")
	public void getAppPortletList(HttpServletRequest req,HttpServletResponse res){
		Object userObj=req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT);
		if(userObj!=null){
			List<PortletPage> por=cargoSer.getPortletPageList(1, 1);
			StringBuffer jsonbegin=new StringBuffer();
			jsonbegin.append("[");
			int forindex=1;
			long companyId=((Users)req.getSession(true).getAttribute(SystemSessionAttreName.ACCOUNT)).getCompanyId();
			for(PortletPage i:por){
					if(forindex>1){
						jsonbegin.append(",");
					}
						//只有在第一次循环的时候去执行。这样提升效率。
					forindex++;
					jsonbegin.append("{id: '").append(i.getId()).append("',title:'").append(i.getName()).append("'}");
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
	@RequestMapping(value="/portlet/addAppPortlet")
	public void addAppPortlet(HttpServletRequest req,HttpServletResponse res){
		if(permission.isLoginRequest(req, res)){
			
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

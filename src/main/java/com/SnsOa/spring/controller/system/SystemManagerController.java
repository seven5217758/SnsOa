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
import org.springframework.web.context.ServletContextAware;

import com.SnsOa.entity.Program;
import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.service.ProgramService;
import com.SnsOa.system.util.SystemPermission;


@Controller
public class SystemManagerController implements ServletContextAware{
	private ServletContext sc;
	@Resource(name="messageSource")
	private ResourceBundleMessageSource messageSource;
	private static final Logger logger = LoggerFactory
	.getLogger(SystemManagerController.class);
	@Resource(name="ProgramServiceImpl")
	private ProgramService proService;
	@Resource(name="SystemPermission")
	private SystemPermission permission;
	/**
	 * 用于获取应用程序列表
	 * @param req
	 * @param res
	 */
	@RequestMapping(value="appList")
	public void appList(HttpServletRequest req,HttpServletResponse res){
		if(permission.isLoginRequest(req, res)){
			try {
				res.setCharacterEncoding("UTF-8");
				List<ProgramItem> pro=proService.getAllProgramList();
				StringBuffer jsonbegin=new StringBuffer();
				jsonbegin.append("[");
				int forindex=1;
				for(ProgramItem i:pro){
					if(forindex>1){
						jsonbegin.append(",");
					}
					forindex++;
					jsonbegin.append("{name: '").append(i.getItemName()).append("',cuisine:'").append(proService.getProgramName(i.getProgramId())).append("',number:'").append(i.getId()).append("','isenable':'").append(i.isEnable()).append("'}");
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
	@RequestMapping(value="addNewApp")
	public void addNewApp(HttpServletRequest req,HttpServletResponse res,String programName,long index,String itemName,String appFile,String appClass,String appId,String icon){
		if(permission.isLoginRequest(req, res)){
			try {
				Program pro=new Program();
				ProgramItem item=new ProgramItem();
				pro.setProgramName(programName);
				pro.setIndexs(index);
				pro.setUseNum(0);
				item.setAppClass(appClass);
				item.setAppFile(appFile);
				item.setAppId(appId);
				item.setEnable(true);
				item.setIcon(icon);
				item.setItemName(itemName);
				res.getWriter().print("{success:"+proService.addNewProgram(pro,item)+"}");
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
	@RequestMapping(value="getItemByItemInfo")
	public void getItemByItemInfo(HttpServletRequest req,HttpServletResponse res,long id){
		if(permission.isLoginRequest(req, res)){
			res.setCharacterEncoding("UTF-8");
			ProgramItem item=proService.getProgramItem(id);
			String appclass=item.getAppClass();
			String appfile=item.getAppFile();
			String appid=item.getAppId();
			String icon=item.getIcon();
			String itemname=item.getItemName();
			long programid=item.getProgramId();
			StringBuffer strbuf=new StringBuffer();
			strbuf.append("{'appclass':'").append(appclass).append("','appfile':'").append(appfile).append("','appid':'").append(appid).append("','icon':'").append(icon).append("','itemname':'").append(itemname).append("','id':").append("'").append(id).append("','programid':'").append(programid).append("'}");
			try {
				res.getWriter().print(strbuf.toString());
			} catch (IOException e) {
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
	@RequestMapping(value="updateItem")
	public void updataItem(HttpServletRequest req,HttpServletResponse res,String itemName,String appFile,String appClass,String appId,String icon,long id,long programid){
		if(permission.isLoginRequest(req, res)){
			ProgramItem item=new ProgramItem();
			item.setAppClass(appClass);
			item.setAppFile(appFile);
			item.setAppId(appId);
			item.setIcon(icon);
			item.setItemName(itemName);
			item.setId(id);
			item.setProgramId(programid);
			try {
				res.getWriter().print("{success:"+proService.updataProgramItem(item)+"}");
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
	@RequestMapping(value="enableitem")
	public void setEnableItem(HttpServletRequest req,HttpServletResponse res,long id){
		if(permission.isLoginRequest(req, res)){
			ProgramItem item=proService.getProgramItem(id);
			item.setEnable(!item.isEnable());
			try {
				res.getWriter().print("{success:"+proService.updataProgramItem(item)+"}");
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
	@RequestMapping(value="deleteitem")
	public void deleteItem(HttpServletRequest req,HttpServletResponse res,long id){
		if(permission.isLoginRequest(req, res)){
			try {
				res.getWriter().print("{success:"+proService.deleteItem(id)+"}");
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
	@RequestMapping(value="enableApp")
	public void setEnableApp(HttpServletRequest req,HttpServletResponse res,long id,boolean enable){
		if(permission.isLoginRequest(req, res)){
			try {
				List<ProgramItem> proList=proService.getProgramAllItem(id);
				boolean b=false;
				for(ProgramItem item:proList){
					if(enable){
						item.setEnable(false);
					}else{
						item.setEnable(true);
					}
					if(proService.updataProgramItem(item)){
						b=true;
					}else{
						b=false;
						break;
					}
				}
				res.getWriter().print("{success:"+b+"}");
			} catch (IOException e) {
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
	@RequestMapping(value="addnewappitem")
	public void addNewAppItem(HttpServletRequest req,HttpServletResponse res,String itemName,String appFile,String appClass,String appId,String icon,long id){
		if(permission.isLoginRequest(req, res)){
			ProgramItem dbItem=proService.getProgramItem(id);
			ProgramItem item=new ProgramItem();
			item.setAppClass(appClass);
			item.setAppFile(appFile);
			item.setAppId(appId);
			item.setIcon(icon);
			item.setItemName(itemName);
			item.setProgramId(dbItem.getProgramId());
			try {
				res.getWriter().print("{success:"+proService.addNewAppItem(item)+"}");
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
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		sc=servletContext;
	}

}

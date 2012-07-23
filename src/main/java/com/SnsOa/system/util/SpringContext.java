package com.SnsOa.system.util;

import javax.servlet.ServletConfig;

import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 用于获取spring上下文
 * @author Thinkpad
 *
 */
public class SpringContext {
	private WebApplicationContext wac;
	public Object getBean(Class name){
		return wac.getBean(name);
	}
	public void setSpringContext(ServletConfig config){
		WebApplicationContext wac= WebApplicationContextUtils.getWebApplicationContext( config.getServletContext()); 
	}
}

package com.SnsOa.system.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 主要负责生成验证码
 * @author Thinkpad
 */
public class IdentifyingCode {
	//获取随机颜色
	public Color getRandaColor(int s,int e){
		Random random =new Random();
		if(s>255)s=255;
		if(2>255)e=255;
		int r=s+random.nextInt(e-s);
		int g=s+random.nextInt(e-s);
		int b=s+random.nextInt(e-s);
		return new Color(r,g,b);
	}
	/**
	 * 设置响应头
	 * @param res response对象
	 */
	public void setHeader(HttpServletResponse res){
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "No-cache");
		res.setDateHeader("Expires", 0);
		res.setContentType("image/jpeg");
	}
	public void generateImage(HttpServletResponse res,HttpServletRequest req,String sessionAttName){
		int width=140;
		int height=30;
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		Random random=new Random();
		Font mFont=new Font("宋体",Font.ITALIC,18);
		g.setColor(getRandaColor(15,255 ));
		g.fillRect(0, 0, width, height);
		g.setFont(mFont);
		g.setColor(getRandaColor(180, 200));
		String sRand="";
		String ctmp="";
		int itmp=0;
		for(int i=0;i<4;i++){
			//生成汉字
			String rBase[]={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
			//生成第一位的区码
			int r1=random.nextInt(3)+11;
			String str_r1=rBase[r1];
			int r2;
			if(r1==13){
				r2=random.nextInt(3);
			}else{
				r2=random.nextInt(8);
			}
			String str_r2=rBase[r2];
			int r3=random.nextInt(6)+10;
			String str_r3=rBase[r3];
			int r4;
			if(r3==10){
				r4=random.nextInt(15)+1;
			}else if(r3==15){
				r4=random.nextInt(15);
			}else{
				r4=random.nextInt(16);
			}
			String str_r4=rBase[r4];
			byte[] bytes=new byte[2];
			String str_r12=str_r1+str_r2;
			int tempLow=Integer.parseInt(str_r12,16);
			bytes[0]=(byte)tempLow;
			String str_34=str_r3+str_r4;
			int tempHigh=Integer.parseInt(str_34,16);
			bytes[1]=(byte)tempHigh;
			ctmp=new String(bytes);
			sRand+=ctmp;
			Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110));
			g.setColor(color);
			Graphics2D g2d_word=(Graphics2D)g;
			AffineTransform trans=new AffineTransform();
			//trans.rotate(random.nextInt(45)*3.14/180,26*i+8,7);
			float scaleSize=random.nextFloat()+0.7f;
			if(scaleSize>1f)scaleSize=1f;
			//trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, width/6*i+23, height/2);
		}
		try {
			HttpSession session=req.getSession(true);
			session.setAttribute(sessionAttName, sRand);
			g.dispose();
			ImageIO.write(image,"JPEG", res.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 验证验证码是否正确
	 */
	public boolean isRight(String str,HttpServletRequest req){
		String sessionStr=(String) req.getSession().getAttribute(SystemSessionAttreName.REGISTER_CODE);
		return str.equals(sessionStr);
	}
	
}

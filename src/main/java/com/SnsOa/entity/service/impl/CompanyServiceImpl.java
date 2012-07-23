package com.SnsOa.entity.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SnsOa.entity.Company;
import com.SnsOa.entity.ProgramLinkRole;
import com.SnsOa.entity.Roles;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.dao.CompanyDAO;
import com.SnsOa.entity.dao.ProgramLinkRoleDAO;
import com.SnsOa.entity.dao.RolesDao;
import com.SnsOa.entity.dao.UsersDAO;
import com.SnsOa.entity.dao.jpa.impl.UsersJpaDAO;
import com.SnsOa.entity.service.CompanyService;
import com.SnsOa.system.util.MD5;
@Service("CompanyServiceImpl")
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Resource(name="CompanyJpaDAO")
	private CompanyDAO companydao;
	@Resource(name="UsersJpaDAO")
	private UsersDAO userdao;
	@Resource(name="RolesJpaDAO")
	private RolesDao roledao;
	@Resource(name="ProgramLinkRoleJpaDAO")
	private ProgramLinkRoleDAO programLinkRoledao;
	private static final Logger logger = LoggerFactory
	.getLogger(UsersJpaDAO.class);
	/**
	 * 注册公司。返回一个可注册序列号和密码。该序列号是当前数据插入时的id通过md5加密的账号和密码
	 */
	public String registerCompany(Company company) {
		// TODO Auto-generated method stub
		
		companydao.save(company);
		String md5=MD5.generatePassword(String.valueOf(company.getCompanyId()));
		Users users=new Users();
		users.setAccount(String.valueOf(company.getCompanyId()));
		users.setCompanyId(company.getCompanyId());
		users.setPassword(md5);
		users.setRole(0);
		users.setChangerAccount(false);
		users.setMainAccount(true);
		userdao.save(users);
		logger.info("注册用户成功:"+company+"----md5:"+md5);
		return String.valueOf(company.getCompanyId());
	}
	/**
	 * 登陆 如果登陆成功则返回用户的Users反之
	 */
	public Users Login(String account,String password){
		try{
			List<Users> userses=userdao.query("from Users where account='"+account+"'");
			Iterator<Users> i=userses.iterator();
			if(i.hasNext()){
				Users user=i.next();
				if(MD5.validatePassword(user.getPassword(),password)){
					return user;
				}else{
					return null;
				}
			}else{
				return null;
			}
		}catch(Exception ex){
			logger.info(ex.getMessage());
			return null;
		}
	}
	@Override
	public List<Users> getCompanyUsers(long companyId) {
		// TODO Auto-generated method stub
		return userdao.query("from Users where companyId="+companyId);
	}
	@Override
	public Roles getRolesById(long id) {
		// TODO Auto-generated method stub
		
		return roledao.getRoles(id);
	}
	@Override
	public List<Roles> getCompanyRoles(long companyId) {
		// TODO Auto-generated method stub
		return roledao.query("from Roles where companyId="+companyId);
	}
	@Override
	public boolean addRoles(Roles roles) {
		// TODO Auto-generated method stub
		return roledao.save(roles);
	}
	@Override
	public boolean deleteRoles(long rolesId) {
		// TODO Auto-generated method stub
		List<ProgramLinkRole> listPro=programLinkRoledao.query("from ProgramLinkRole where rolesId="+rolesId);
		for(ProgramLinkRole pro:listPro){
			programLinkRoledao.delete(pro.getId());
		}
		return roledao.delete(rolesId);
	}
	@Override
	public Users getUserInfo(long id) {
		// TODO Auto-generated method stub
		return userdao.getUsers(id);
	}
	@Override
	public boolean deleteUserInfo(long id) {
		// TODO Auto-generated method stub
		return userdao.delete(id);
	}
	@Override
	public boolean updateUserInfo(Users user) {
		// TODO Auto-generated method stub
		return userdao.update(user);
	}
	@Override
	public boolean addUser(Users user) {
		// TODO Auto-generated method stub
		return userdao.save(user);
	}

}

package com.SnsOa.entity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SnsOa.entity.Program;
import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.ProgramLinkCompany;
import com.SnsOa.entity.ProgramLinkRole;
import com.SnsOa.entity.Users;
import com.SnsOa.entity.dao.ProgramDAO;
import com.SnsOa.entity.dao.ProgramItemDAO;
import com.SnsOa.entity.dao.ProgramLinkCompanyDAO;
import com.SnsOa.entity.dao.ProgramLinkRoleDAO;
import com.SnsOa.entity.dao.RolesDao;
import com.SnsOa.entity.dao.UsersDAO;
import com.SnsOa.entity.service.ProgramService;
@Service("ProgramServiceImpl")
@Transactional
public class ProgramServiceImpl implements ProgramService {
	@Resource(name="ProgramJpaDAO")
	private ProgramDAO prodao;
	@Resource(name="ProgramItemJpaDAO")
	private ProgramItemDAO proitemdao;
	@Resource(name="ProgramLinkCompanyJpaDAO")
	private ProgramLinkCompanyDAO prolinkcomdao;
	@Resource(name="ProgramLinkRoleJpaDAO")
	private ProgramLinkRoleDAO programLinkRoledao;
	@Resource(name="ProgramServiceImpl")
	private ProgramService proService;
	@Resource(name="UsersJpaDAO")
	private UsersDAO userdao;
	@Resource(name="RolesJpaDAO")
	private RolesDao roledao;
	@Override
	/**
	 * 查询所有应用程序
	 */
	public List<ProgramItem> getAllProgramList() {
		// TODO Auto-generated method stub
		return proitemdao.query("from ProgramItem");
	}

	@Override
	public boolean addNewProgram(Program pro, ProgramItem item) {
			boolean prob=prodao.save(pro);
			item.setProgramId(pro.getId());
			boolean itemb=proitemdao.save(item);
			return prob&&itemb;
	}

	@Override
	public String getProgramName(long id) {
		// TODO Auto-generated method stub
		return prodao.getProgram(id).getProgramName();
	}

	@Override
	public List<Program> getUserOfAppList(long userid) {
		// TODO Auto-generated method stub
		Users us=userdao.getUsers(userid);
		long companyId=us.getCompanyId();
		List<ProgramLinkCompany> list=prolinkcomdao.query("from ProgramLinkCompany where companyId="+companyId);
		List<Program> plist=new ArrayList<Program>(); 
		for(ProgramLinkCompany i:list){
			plist.add(prodao.getProgram(i.getProgramId()));
		}
		return plist;
	}

	@Override
	public List<ProgramItem> getProgramAllItem(long programId) {
		// TODO Auto-generated method stub
		return proitemdao.query("from ProgramItem where programId="+programId);
	}

	@Override
	public ProgramItem getProgramItem(long id) {
		return proitemdao.getProgramItem(id);
	}

	@Override
	public boolean updataProgramItem(ProgramItem item) {
		// TODO Auto-generated method stub
		return proitemdao.update(item);
	}

	@Override
	public boolean deleteItem(long id) {
		// TODO Auto-generated method stub
		long proid=this.getProgramItem(id).getProgramId();
		int num=this.getProgramAllItem(proid).size();
		if(num<=1){
			this.deleteProgram(proid);
		}
		return proitemdao.delete(id);
	}

	@Override
	public boolean deleteProgram(long id) {
		// TODO Auto-generated method stub
		return prodao.delete(id);
	}
	/**
	 * 是公司的program
	 */
	@Override
	public boolean isCompanyProgram(long companyid, long programid) {
		// TODO Auto-generated method stub
		int num=prolinkcomdao.query("from ProgramLinkCompany where companyId="+companyid+" and programId="+programid).size();
		if(num==0){
			return false;
		}else{
			return true;
		}
	}
	public boolean isRolesProgram(long rolesId, long programItemId) {
		// TODO Auto-generated method stub
		int num=roledao.query("from ProgramLinkRole where rolesId="+rolesId+" and programItemId="+programItemId).size();
		if(num==0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public boolean addProgramToCompany(long companyId, long programId) {
		// TODO Auto-generated method stub
		ProgramLinkCompany pro=new ProgramLinkCompany();
		pro.setCompanyId(companyId);
		pro.setProgramId(programId);
		return prolinkcomdao.save(pro);
	}

	@Override
	public boolean removeProgramToCompany(long companyId, long programId) {
		// TODO Auto-generated method stub
		List<ProgramLinkCompany> listcom=prolinkcomdao.query("from ProgramLinkCompany where companyId="+companyId+" and programId="+programId);
		boolean b=false;
		for(ProgramLinkCompany p:listcom){
			b=prolinkcomdao.delete(p.getId());
		}
		return b;
	}

	@Override
	public boolean addNewAppItem(ProgramItem pro) {
		// TODO Auto-generated method stub
		return proitemdao.save(pro);
	}

	@Override
	public List<ProgramLinkRole> getRolesAppList(long id) {
		// TODO Auto-generated method stub
		return programLinkRoledao.query("from ProgramLinkRole where rolesId="+id);
	}

	@Override
	public boolean addProgramItemToRoles(long rolesId, long programItemId) {
		// TODO Auto-generated method stub
		int num=roledao.query("from ProgramLinkRole where rolesId="+rolesId+" and programItemId="+programItemId).size();
		if(num==0){
			ProgramLinkRole plr=new ProgramLinkRole();
			plr.setProgramItemId(programItemId);
			plr.setRolesId(rolesId);
			return programLinkRoledao.save(plr);
		}else{
			return true;
		}
	}
	public boolean deleteProgramItemFromRoles(long id) {
		return programLinkRoledao.delete(id);
	}
	
	
	
}

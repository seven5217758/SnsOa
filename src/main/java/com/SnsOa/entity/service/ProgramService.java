package com.SnsOa.entity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SnsOa.entity.Program;
import com.SnsOa.entity.ProgramItem;
import com.SnsOa.entity.ProgramLinkRole;
@Service("ProgramService")
public interface ProgramService {
	public boolean addNewProgram(Program pro,ProgramItem item);
	public List<ProgramItem> getAllProgramList();
	public String getProgramName(long id);
	public List<Program> getUserOfAppList(long userid);
	public List<ProgramItem> getProgramAllItem(long programId);
	public ProgramItem getProgramItem(long id);
	public boolean updataProgramItem(ProgramItem item);
	public boolean deleteItem(long id);
	public boolean deleteProgram(long id);
	public boolean isCompanyProgram(long companyid,long programid);
	public boolean addProgramToCompany(long companyId,long programId);
	public boolean removeProgramToCompany(long companyId,long programId);
	public boolean addNewAppItem(ProgramItem pro);
	public List<ProgramLinkRole> getRolesAppList(long id);
	public boolean addProgramItemToRoles(long rolesId,long programItemId);
	public boolean isRolesProgram(long rolesId, long programItemId);
	public boolean deleteProgramItemFromRoles(long id);
}

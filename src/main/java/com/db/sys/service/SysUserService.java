package com.db.sys.service;


import java.util.Map;


import com.db.common.vo.SysUserDeptResult;
import com.db.sys.entity.SysUser;

public interface SysUserService extends PageService<SysUserDeptResult>{
	int validById(Integer id,
			Integer valid,
			String modifiedUser);
	int saveObject(SysUser entity, Integer[] roleIds);
	Map<String, Object> findObjectById(Integer userId);
	int updateObject(SysUser entity,Integer[] roleIds);
	int updatePassword(String pwd,String newPwd,String cfgPwdId);
	void doExcelUsers();
	void doPDFUsers();
	String[] doQueryMenus(Integer id);
}

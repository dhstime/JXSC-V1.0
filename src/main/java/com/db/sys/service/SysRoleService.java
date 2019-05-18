package com.db.sys.service;

import java.util.List;

import com.db.common.vo.CheckBox;
import com.db.common.vo.SysRoleMenuResult;
import com.db.sys.entity.SysRole;

public interface SysRoleService extends PageService<SysRole>{
	/**
     * 本方法中要分页查询角色信息,并查询角色总记录数据
     * @param pageCurrent 当表要查询的当前页的页码值
     * @return 封装当前实体数据以及分页信息
     */
//	 PageObject<SysRole> findPageObjects(
//			 String name,Integer pageCurrent);
	 int deleteObject(Integer id);
	 int saveObject(SysRole entity,Integer[] menuIds) ;
	 SysRoleMenuResult findObjectById(Integer id) ;
	 int updateObject(SysRole entity,Integer[] menuIds) ;
	 List<CheckBox> findObjects();
}

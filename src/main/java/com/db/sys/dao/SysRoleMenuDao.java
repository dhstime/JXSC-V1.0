package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 此Dao用于删除sys_role_menus关系表中的数据
 * @author jiangzhengdong
 *
 */
public interface SysRoleMenuDao {
	/**
	 * 基于菜单id删除关系信息
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);
	int deleteObjectsByRoleId(Integer roleId);
	int insertObject(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);
}

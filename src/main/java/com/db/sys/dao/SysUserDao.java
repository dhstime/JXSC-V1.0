package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.common.vo.SysUserDeptResult;
import com.db.sys.entity.SysUser;

public interface SysUserDao {
	int getRowCount(@Param("username")String username);
	List<SysUserDeptResult> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	int insertObject(SysUser entity);
	SysUserDeptResult findObjectById(Integer id);
	int updateObject(SysUser entity);
	SysUser findUserByUserName(String username);
	int updatePassword(
			@Param("id")Integer id,
			@Param("password")String password,
			@Param("salt")String salt);
}

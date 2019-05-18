package com.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.common.vo.Node;
import com.db.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 查询所有的菜单
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	/**
	  * 根据菜单id统计子菜单的个数
	  * @param id
	  * @return
	  */
	 int getChildCount(Integer id);
	 /**
	  * 根据id 删除菜单
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer id);
	 /**
	  * 查询菜单对应的树节点信息,ZTree
	  * @return
	  */
	 List<Node> findZtreeMenuNodes();
	 
	 int insertObject(SysMenu entity);
	 int updateObject(SysMenu entity);
	 List<String> findPermissions(
				@Param("menuIds")
				Integer[] menuIds);
	 List<SysMenu> findObjectsByIds(@Param("ids")Object...object);
}

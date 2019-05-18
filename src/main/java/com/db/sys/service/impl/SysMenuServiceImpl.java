package com.db.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.common.annotation.RequestLog;
import com.db.common.exception.ServiceException;
import com.db.common.vo.Node;
import com.db.sys.dao.SysMenuDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.entity.SysMenu;
import com.db.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		System.out.println(list);
		if(list==null||list.size()==0)
			throw new ServiceException("没有对应菜单");
		return list;
	}
	@RequestLog("删除菜单")
	@Override
	public int deleteObject(Integer id) {
		//验证参数有效性
		if(id==null||id<1)
		throw new IllegalArgumentException("参数不对");
		//判断菜单是否有子菜单
		int childCount = sysMenuDao.getChildCount(id);
		if(childCount>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除菜单自身信息
		int rows = sysMenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("菜单可能以及不存在了");
		//4.删除菜单关系信息
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//5.返回结果
		return rows;
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@RequestLog("添加菜单")
	@Override
	public int saveObject(SysMenu entity) {
		//1.合法验证,虽然前端控制了传入的参数不能为空,但是开放接口层也可能会调用service层,所以严谨要进行验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("菜单名不能为空");
		int rows;
		//2.保存数据,防止写入中出错
		try{
		rows=sysMenuDao.insertObject(entity);
		}catch(Exception e){
		e.printStackTrace();
		throw new ServiceException("保存失败");
		}
		//3.返回数据
		return rows;
	}
	@RequestLog("修改菜单")
	@Override
	public int updateObject(SysMenu entity) {
		//1.合法验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("菜单名不能为空");
		
		//2.更新数据
		int rows=sysMenuDao.updateObject(entity);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//3.返回数据
		return rows;
	}

}

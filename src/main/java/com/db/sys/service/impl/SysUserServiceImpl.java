package com.db.sys.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.db.common.annotation.RequestLog;
import com.db.common.exception.ServiceException;
import com.db.common.util.PageUtil;
import com.db.common.vo.PageObject;
import com.db.common.vo.SysUserDeptResult;
import com.db.sys.dao.SysMenuDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.dao.SysUserDao;
import com.db.sys.dao.SysUserRoleDao;
import com.db.sys.entity.SysMenu;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserService;
import com.jx.util.Excel;
import com.jx.util.Pdf;

@Transactional(rollbackFor=RuntimeException.class,isolation=Isolation.READ_COMMITTED,timeout=-1) 
//只有出现 RuntimeException 才会回滚,不允许脏读
//readOnly() default false默认:事务不允许并发执行
@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	private SysMenuDao sysMenuDao;


	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW) // 不是没有事务,只是事务的隔离级别很低,允许并发读,相当于使用了乐观锁
	@Override
	//	@RequestLog("查询用户")
	public PageObject<SysUserDeptResult> findPageObjects(String username, Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("参数不合法");
		//2.依据条件获取总记录数
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<SysUserDeptResult> records=
				sysUserDao.findPageObjects(
						username, startIndex, pageSize);
		//5.封装数据
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}
	/**
	 * 此方法需授权才能访问
	 * 1)从登陆用户上获取它具备的权限
	 * 2)判定该用户权限中是否包含 @RequiresPermissions 注解中包含的权限
	 */
	@RequestLog("禁/启用户")
	@RequiresPermissions("sys:user:valid")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//1.合法性验证
		if(id==null||id<=0)
			throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)
			throw new ServiceException("参数不合法,valie="+valid);
		if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
		//2.执行禁用或启用操作
		int rows=0;
		try{
			rows=sysUserDao.validById(id, valid, modifiedUser);
		}catch(Throwable e){
			e.printStackTrace();
			//报警,给维护人员发短信
			throw new ServiceException("底层正在维护");
		}
		//3.判定结果,并返回
		if(rows==0)
			throw new ServiceException("此记录可能已经不存在");
		return rows;
	}

	@RequestLog("添加用户")
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		//1.验证数据合法性
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("密码不能为空");
		if(roleIds==null || roleIds.length==0)
			throw new ServiceException("至少要为用户分配角色");

		//2.将数据写入数据库
		//		        DigestUtils-spring自带的加密
		String salt=UUID.randomUUID().toString();//Java自带的类,随机产生字符串,重复几率很小
		entity.setSalt(salt);//用于登陆是加密比较
		//加密(先了解,讲shiro时再说)
		SimpleHash sHash=
				new SimpleHash("MD5",entity.getPassword(), salt);//MD5,不可解密,相同结果,计算出来相同,内容不同,基本不同
		entity.setPassword(sHash.toHex());

		int rows=sysUserDao.insertObject(entity);
		int urows = sysUserRoleDao.insertObjects(
				entity.getId(),
				roleIds);//"1,2,3,4";
		if(urows>0) throw new ServiceException("模拟回滚,关系保存失败");
		//3.返回结果
		return rows;
	}
	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		//1.合法性验证
		if(userId==null||userId<=0)
			throw new ServiceException(
					"参数数据不合法,userId="+userId);
		//2.业务查询
		SysUserDeptResult user=
				sysUserDao.findObjectById(userId);
		if(user==null)
			throw new ServiceException("此用户已经不存在");
		List<Integer> roleIds=
				sysUserRoleDao.findRoleIdsByUserId(userId);
		//3.数据封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}
	@RequestLog("修改用户")
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为其指定角色");
		//其它验证自己实现，例如用户名已经存在，密码长度，...
		//2.更新用户自身信息
		int rows=sysUserDao.updateObject(entity);
		//3.保存用户与角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	@RequestLog("修改密码")
	@Override
	public int updatePassword(String pwd,String newPwd,String cfgPwd) {
		if(StringUtils.isEmpty(pwd)||StringUtils.isEmpty(newPwd))
			throw new IllegalArgumentException("密码不能为空");
		if(!newPwd.equals(cfgPwd))
			throw new ServiceException("两次输入密码不一致");
		Subject subject=SecurityUtils.getSubject();
		SysUser user = (SysUser)subject.getPrincipal();
		UsernamePasswordToken token=
				new UsernamePasswordToken(
						user.getUsername(),//身份信息
						pwd);//凭证信息
		//2.2对用户信息进行身份认证
		subject.login(token);
		String salt=UUID.randomUUID().toString();//Java自带的类,随机产生字符串,重复几率很小
		SimpleHash sHash=
				new SimpleHash("MD5",newPwd, salt);//MD5,不可解密,相同结果,计算出来相同,内容不同,基本不同
		int rows = sysUserDao.updatePassword(user.getId(), sHash.toHex(),salt);
		if(rows==0)
			throw new ServiceException("更新失败");
		return rows;
	}


	@Override
	public void doExcelUsers() {
		new Excel().excel();
	}
	@Override
	public void doPDFUsers() {
		try {
			new Pdf();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String[] doQueryMenus(Integer id) {
		/* 1.获取对应(当前登陆用户)的角色 */
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(id);
		if(roleIds==null||roleIds.size()==0) {
			throw new AuthorizationException();
		}
		/* 2.获取对应的菜单信息 */
		Integer[] array={};
		List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		if(menuIds==null||menuIds.size()==0) {
			throw new AuthorizationException();
		}
		Set<String> set = new HashSet<>();
		List<SysMenu> list = sysMenuDao.findObjectsByIds(menuIds.toArray());
		for (int i=0;i<list.size();i++) {
			if(list.get(i).getParentId()!=null) {
				if(list.get(i).getParentId()==8) {
					//如果是大菜单,直接加入set
					set.add(list.get(i).getName());
				}else{
					// 如果是小菜单,找到他的父菜单并将其加入到list,动态改变size直至为null和8的
					List<SysMenu> lis = sysMenuDao.findObjectsByIds(list.get(i).getParentId());
					list.add(lis.get(0));
				}
			}
		}
		String[] split = set.toString().split(",");
		// 去除中括号和空格
		for (int i = 0; i < split.length; i++) {
			split[i]=split[i].replace("[", "");
			split[i]=split[i].replace("]", "");
			split[i]=split[i].trim();
		}
		return split;
	}

}

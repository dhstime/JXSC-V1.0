package com.db.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.sys.dao.SysMenuDao;
import com.db.sys.dao.SysRoleMenuDao;
import com.db.sys.dao.SysUserDao;
import com.db.sys.dao.SysUserRoleDao;
import com.db.sys.entity.SysUser;
@Service
public class ShiroUserRealm extends AuthorizingRealm {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	private SysMenuDao sysMenuDao;

	/**
	 * 设置凭证匹配器
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		// 设置凭证匹配对象
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		// 设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		// 设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}

	/**完成认证信息的获取以及封装*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.获取用户输入的信息
		UsernamePasswordToken upToken=(UsernamePasswordToken)token;
		// 2.基于用户名查找用户信息,进行判断
		SysUser sysUser = sysUserDao.findUserByUserName(upToken.getUsername());
		// 2.1判定用户是否存在
		if(sysUser==null) throw new UnknownAccountException();
		// 2.2判定是否被禁用
		if(sysUser.getValid()==0) throw new LockedAccountException();
		// 3.对用户信息进行封装,返回
		// byteSource对象是对byte数组及编码方式的封装
		ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				sysUser, // principal身份 
				sysUser.getPassword(), // hashedCredentials已加密的凭证信息
				credentialsSalt, // credentialsSalt凭证盐值
				getName()); // realmName
		return info; // 返回给调用者,SecurityManager
	}

	/**完成授权信息的获取及封装*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权...............");
		// 1.获取登陆用户信息
		SysUser user = (SysUser)principals.getPrimaryPrincipal();
		/* 2.获取对应的角色 */
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(user.getId());
		if(roleIds==null||roleIds.size()==0) {
			throw new AuthorizationException();
		}
		/* 3.获取对应的菜单信息 */
		// 传入一个泛型
		Integer[] array={};
		List<Integer> menuIds = sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		if(menuIds==null||menuIds.size()==0) {
			throw new AuthorizationException();
		}
		/* 4.获取对应的权限标识(permission) */
		List<String> permissions = sysMenuDao.findPermissions(menuIds.toArray(array));
		/* 5.对权限标识封装并返回 */
		// 放入set集合,剔除重复
		Set<String> set = new HashSet<>();
		for(String per:permissions){
			if(!StringUtils.isEmpty(per)){
				set.add(per);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		return info;// 返回给授权管理器
	}

}

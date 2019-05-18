package com.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.sys.entity.SysLog;

/**
 * spring-mybatis.xml文件中定义了MapperScannerConfigurer对象,
 * 指定扫描包,将包中的
 *接口名(首字母小写)作为key,实现了此接口的代理实例$Proxy20,作为值放入bean池
 */
public interface SysLogDao {
	
	/**
	 * 负责基于条件查询当前页数据
	 * @param username 查询条件,
	 * 此注解将自动参数放入map,以便#占位符取值;没有的话,会查找对应的get方法,然而并没有
	 * @param startIndex 起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的日志记录信息
	 */
	List<SysLog> findPageObjects(@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 负责基于条件查询总记录数
	 * @param username 查询条件:操作用户
	 * @return 总记录数
	 * 假如方法参数应用在动态SQL,两种方式:
	 * 1)使用@Param
	 * 2)使用参数_parameter
	 */
	int getRowCount(@Param("username")String username);
	/**
	 * 执行日志删除操作
	 * @param id
	 * @return
	 */
	int deleteObjects(@Param("ids")Integer... ids);
	int insertObject(SysLog entity);
}

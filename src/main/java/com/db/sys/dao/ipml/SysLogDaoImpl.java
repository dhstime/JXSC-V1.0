package com.db.sys.dao.ipml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
@Repository
public class SysLogDaoImpl implements SysLogDao {
	//类型相同,名字相同的AutoWeried,会报错
	//名字为:SysLogDaoImpl,默认会按名字找SysLogDao,不会调用此类
	//@Qualifier("A"),指定类型相同是找名字为:A的,与AutoWeried配合使用
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<SysLog> findPageObjects(String username, Integer startIndex, Integer pageSize) {
		//1.获取sqlSession对象
		SqlSession session = sqlSessionFactory.openSession();
		//2.获取Dao对象
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("username", username);
		paramMap.put("startIndex", startIndex);
		paramMap.put("pageSize", pageSize);
		String statement="com.db.sys.dao.SysLogDao.findPageObjects";//命名空间+id
		List<SysLog> records = session.selectList(statement, paramMap);
		//3.释放session
		session.close();
		return records;
	}

	@Override
	public int getRowCount(String username) {
		//1.获取sqlSession对象
		SqlSession session = sqlSessionFactory.openSession();
		//2.获取Dao对象
		SysLogDao dao = session.getMapper(SysLogDao.class);
		//3.执行sql
		int rows = dao.getRowCount(username);
		//4.释放session
		session.close();
		return rows;
	}
	
	@Autowired
	private DataSource dataSource;
	@Override
	public int deleteObjects(Integer... ids) {
//		dataSource.getConnection();
		String sql="";
		return 0;
	}

	@Override
	public int insertObject(SysLog entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}

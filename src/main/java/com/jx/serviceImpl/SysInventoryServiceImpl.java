package com.jx.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.jx.dao.SysInventoryDao;
import com.jx.dao.SysProductDao;
import com.jx.entity.SysInventory;
import com.jx.service.SysInventoryService;
@Service
public class SysInventoryServiceImpl implements SysInventoryService {
	@Autowired
	private SysInventoryDao sysInventoryDao;
	@Autowired
	private SysProductDao sysProductDao;

	@Override
	public PageObject<SysInventory> findPageObjects(Integer pageCurrent) {
		// 1.验证参数合法性
		// 1.1验证pageCurrent的合法性，
		// 不合法抛出IllegalArgumentException异常
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = sysInventoryDao.getRowCount();
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0)
			throw new ServiceException("记录不存在");
		// 3.基于条件查询当前页记录(pageSize定义为2)
		// 3.1)定义pageSize
		int pageSize = 5;
		// 3.2)计算startIndex
		int startIndex = (pageCurrent - 1) * pageSize;
		int pageCount = (int) Math.ceil((double)rowCount/(double)pageSize);
		// 3.3)执行当前数据的查询操作
		List<SysInventory> records = sysInventoryDao.findPageObjects(startIndex, pageSize);
		//System.out.println(records.toString());
		// 4.对分页信息以及当前页记录进行封装
		// 4.1)构建PageObject对象
		PageObject<SysInventory> pageObject = new PageObject<>();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount(pageCount);
		//System.out.println(pageObject.toString());
		// 5.返回封装结果。
		return pageObject;
	}
	@Override
	public int deleteObjectById(Integer id) {
		if (id == null || id < 1)
			throw new ServiceException("id的值不正确,id=" + id);
		SysInventory sysInventory = sysInventoryDao.findInventoryById(id);
		Integer productId = sysInventory.getProductId();
		int rows = sysInventoryDao.deleteObjectById(id);
		if (rows == 0)
			throw new ServiceException("数据可能已经不存在");
		sysProductDao.deleteObject(productId);
		return rows;
	}
	@Override
	public int SaveObject(SysInventory entity) {

		// 1.合法性验证
		if (entity == null)
			throw new ServiceException("保存数据不能为空");
		if(entity.getProductId()==null||entity.getProductId()<0)
			throw new ServiceException("商品编号不正确");
		SysInventory sys = sysInventoryDao.findInventoryproductById(entity.getProductId());
		if(sys!=null) 
			throw new ServiceException("商品id的值重复了");
		if(entity.getCurrentCount()==null||entity.getCurrentCount()<0)
			throw new ServiceException("商品数量不正确");
		if(entity.getSalesVolume()==null||entity.getSalesVolume()<0)
			throw new ServiceException("请输入正确的销量");
		// 2.保存数据
		int rows = sysInventoryDao.insertObject(entity);
		return rows;
	}
	@Override
	public SysInventory findInventoryById(Integer id) {
		//1.合法性验证
    	if(id==null||id<=0)
    	throw new ServiceException("id的值不合法");
    	//2.执行查询
    	SysInventory inventory = sysInventoryDao.findInventoryById(id);
    	if(inventory==null)
    	 	throw new ServiceException("此记录已经不存在");	
		return inventory;
	}
	@Override
	public int updateObject(SysInventory entity) {
		if(entity==null)
			throw new ServiceException("更新的对象不能为空");
		if(entity.getProductId()==null)
			throw new ServiceException("商品id的值不能为空");
		SysInventory sys = sysInventoryDao.findInventoryById(entity.getId());
		int rows = sysInventoryDao.updateObject(entity);
		if(rows==0)
			  throw new ServiceException("对象可能已经不存在");
		return rows;
	}

}

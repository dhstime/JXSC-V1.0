package com.jx.serviceImpl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.jx.dao.SysInventoryDao;
import com.jx.dao.SysProductDao;
import com.jx.entity.SysInventory;
import com.jx.entity.SysProduct;
import com.jx.service.SysProductService;

@Service
public class SysProductServiceImpl implements SysProductService {
	@Autowired
	private SysProductDao sysProductDao;
	@Autowired 
	private SysInventoryDao sysInventoryDao;
	@Override
	public PageObject<SysProduct> findPageObjects(String brand, Integer pageCurrent) {
		//1.验证参数合法性
		//1.1验证pageCurrent的合法性，
		//不合法抛出IllegalArgumentException异常
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		//2.基于条件查询总记录数
		//2.1) 执行查询
		int rowCount=sysProductDao.getRowCount(brand);
		//2.2) 验证查询结果，假如结果为0不再执行如下操作
		
	
		
		if(rowCount==0)
			throw new ServiceException("系统没有查到对应记录");
		//3.基于条件查询当前页记录(pageSize定义为2)
		//3.1)定义pageSize
		int pageSize=3;
		//3.2)计算startIndex
		int startIndex=(pageCurrent-1)*pageSize;
		//3.3)执行当前数据的查询操作
		List<SysProduct> records=
				sysProductDao.findPageObjects(brand, startIndex, pageSize);
		//4.对分页信息以及当前页记录进行封装
		//4.1)构建PageObject对象
		PageObject<SysProduct> productObject=new PageObject<>();
		//4.2)封装数据
		productObject.setPageCurrent(pageCurrent);
		productObject.setPageSize(pageSize);
		productObject.setRowCount(rowCount);
		productObject.setRecords(records);
		productObject.setPageCount((rowCount-1)/pageSize+1);
		//5.返回封装结果。
		return productObject;

	}



	@Override
	public int insertObject(SysProduct entity) {
		// TODO Auto-generated method stub
		SysInventory entity02 = new SysInventory();
		entity02.setId(entity.getId());
		entity02.setProductId(entity.getId());
		//默认新增100库存
		entity02.setCurrentCount(100);
		entity02.setModifiedTime(new Date());
		entity02.setCreateTime(new Date());
		entity02.setSalesVolume(0);
		
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		//默认状态为1
		entity.setStatus(1);
		int rows=sysProductDao.insertProductObject(entity);
		
		int row = sysInventoryDao.insertObject(entity02);
		System.out.println(entity02);
		System.out.println(row);
		return rows;
	}


	/**
	 * 修改之前先查询
	 */
	@Override
	public Map<String, Object> findObjectById(Integer productId) {
		// TODO Auto-generated method stub
		//1.合法性验证
		if(productId==null||productId<=0)
			throw new ServiceException(
					"参数数据不合法,userId="+productId);
		//2.业务查询
		SysProduct product=
				sysProductDao.findObjectById(productId);
		if(product==null)
			throw new ServiceException("此商品已经不存在");
		
		
		
		Map<String,Object> map=new HashMap<>();
		map.put("product", product);

		return map;

	}


	/**
	 * 修改商品
	 */
	@Override
	public int updateObject(SysProduct entity) {
		// TODO Auto-generated method stub
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		//2.更新用户自身信息
		int rows=sysProductDao.updateObject(entity);
		System.out.println("service"+entity);
		//4.返回结果
		return rows;
	}


	/**
	 * 删除商品信息
	 */
	@Override
	public int deleteObject(Integer id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		//1.验证数据的合法性
		if(id==null||id<=0)
			throw new ServiceException("请先选择");
		int rows = sysProductDao.deleteObject(id);
		/*
		 * 库存表记录也删掉
		 */
		SysInventory entity02 = new SysInventory();
		
		sysProductDao.deleteObjectByProductId(id);
		//5.返回结果
		return rows;
	}


}

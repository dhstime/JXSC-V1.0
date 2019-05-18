package com.jx.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.jx.dao.CartDao;
import com.jx.dao.JxOrderDao;
import com.jx.dao.SysProductDao;
import com.jx.entity.CartEntity;
import com.jx.entity.JxOrder;
import com.jx.entity.SysProduct;
import com.jx.service.JxOrderService;

@Service
public class JxOrderServiceImpl implements JxOrderService{
	@Autowired
	private JxOrderDao jxOrderDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private SysProductDao sysProductDao;
	
	@Override
	public PageObject<JxOrder> findPageObjects(String userName,Integer pageCurrent) {
		 //1.验证参数合法性
		  //1.1验证pageCurrent的合法性，
		  //不合法抛出IllegalArgumentException异常
		  if(pageCurrent==null||pageCurrent<1)
		  throw new IllegalArgumentException("当前页码不正确");
		  //2.基于条件查询总记录数
		  //2.1) 执行查询
		  int rowCount=jxOrderDao.getRowCount(userName);
		 // System.out.println(userName);
		 // System.out.println(rowCount);
		//2.2) 验证查询结果，假如结果为0不再执行如下操作
		  if(rowCount==0)
	          throw new ServiceException("系统没有查到对应记录");
		//3.基于条件查询当前页记录(pageSize定义为3)
		  //3.1)定义pageSize
		  int pageSize = 3;
		  //3.2)计算startIndex
		  int startIndex=(pageCurrent-1)*pageSize;
		  //3.3)执行当前数据的查询操作
		  List<JxOrder> records = jxOrderDao.findPageObjects(userName,startIndex, pageSize);
		  //4.1)构建PageObject对象
		  PageObject<JxOrder> pageObject = new PageObject<JxOrder>();
		//4.2)封装数据
		  pageObject.setPageCurrent(pageCurrent);
		  pageObject.setPageSize(pageSize);
		  pageObject.setRowCount(rowCount);
		  pageObject.setRecords(records);
		  pageObject.setPageCount((rowCount-1)/pageSize+1);
		  //5.返回封装结果
		return pageObject;
	}

	@Override
	public int deleteObjects(Integer... ids) {
		//1.判定参数合法性
				if(ids==null||ids.length==0)
			    throw new IllegalArgumentException("请选择一个");
				//2.执行删除操作
				int rows;
				try{
				rows=jxOrderDao.deleteObjects(ids);
				}catch(Throwable e){
				e.printStackTrace();
				//发出报警信息(例如给运维人员发短信)
				throw new ServiceException("系统故障，正在恢复中...");
				}
				//4.对结果进行验证
				if(rows==0)
				throw new ServiceException("记录可能已经不存在");
				//5.返回结果
				return rows;
		}

	@Override
	public int saveObject(JxOrder entity) {
		//1.验证数据合法性
		if(entity==null)throw new ServiceException("保存对象不能为空");
		
		
		
		int rows;
		//2.保存数据
		try{
			rows=jxOrderDao.insertObject(entity);
			}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("保存失败");
			}
		return rows;
	}

	@Override
	public JxOrder findObjectById(Integer id) {
		//1.合法性验证
		if(id==null||id<=0)throw new ServiceException("参数数据不合法,id="+id);
		//2.业务查询
		JxOrder order=jxOrderDao.findObjectById(id);
		//3.数据封装
		return order;
	}

	@Override
	public int updateObject(JxOrder entity) {
		//1.参数有效性验证
				if(entity==null)
					throw new IllegalArgumentException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getUserName()))
					throw new IllegalArgumentException("用户名不能为空");
				//2.更新用户自身信息
				int rows=jxOrderDao.updateObject(entity);
				//4.返回结果
				return rows;
	}

	@Override
	public int generateOrder(Integer id,String userName) {
		if(id == null || id < 0)throw new ServiceException("id错误");
		List<CartEntity> cd = cartDao.findObjects(id);
		List<JxOrder> list = new ArrayList<JxOrder>();
		for (CartEntity c : cd) {
			if(c.getChecked()==1) {
			JxOrder jo = new JxOrder();
			SysProduct sp = sysProductDao.findObjectById(c.getProductId());
			//用户名
			jo.setUserName(userName);
			//商品类型
			String type = sp.getBrand();
			jo.setType(type);
			//商品名
			String tradeName = sp.gettradeName();
			jo.setTradeName(tradeName);
			//购买数量
			Integer purchaseQuantity = c.getQuantity();
			jo.setPurchaseQuantity(purchaseQuantity);
			//商品价格
			Double price = sp.getPrice();
			jo.setPrice(price);
			//支付类型
			jo.setPayType("微支付");
			//配送(快递方式)
			jo.setDistributionLoading("寒石快递");
			//配送状态
			jo.setOrderStatus("正在配送");
			jxOrderDao.insertObject(jo);
				
			}
		}
		return 0;
	}

	
	
}

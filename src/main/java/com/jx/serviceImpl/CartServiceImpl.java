package com.jx.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.common.exception.ServiceException;
import com.db.common.vo.JsonVO;
import com.itextpdf.text.log.SysoCounter;
import com.jx.VO.CartVO;
import com.jx.dao.CartDao;
import com.jx.dao.SysInventoryDao;
import com.jx.dao.SysProductDao;
import com.jx.entity.CartEntity;
import com.jx.entity.SysInventory;
import com.jx.entity.SysProduct;
import com.jx.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartDao cartDao;
	@Autowired
	SysProductDao sysProductDao;
	@Autowired
	SysInventoryDao SysInventoryDao;

	@Override
	public JsonVO findObjects(Integer userId) {
		// 1.判断参数合法性
		if(userId==null) throw new ServiceException("请登陆");
		List<CartEntity> cart = cartDao.findObjects(userId);
		List<CartVO> clist = new ArrayList<CartVO>();
		for (CartEntity c : cart) {
			CartVO cv = new CartVO();
			System.out.println(c.getProductId());
			SysProduct pro = sysProductDao.findObjectById(c.getProductId());
			cv.setName(pro.gettradeName());
			cv.setModel(pro.getMessage()+",颜色:"+pro.getColor());
			System.out.println(c.getProductId());
			SysInventory sin = SysInventoryDao.findInventoryproductById(c.getProductId());
			if(sin==null) throw new ServiceException("有商品已没有库存,请添加");
			Integer inventy = sin.getCurrentCount();
			System.out.println("库存:"+inventy);
			cv.setInventory(inventy);
			cv.setPrice(pro.getPrice());
			cv.setSum(pro.getPrice()*c.getQuantity());
			cv.setQuantity(c.getQuantity());
			cv.setId(c.getId());
			Boolean checked = false;
			if(c.getChecked()==1) {
				checked = true;
			}
			cv.setChecked(checked);
			Boolean islike = false;
			if(c.getIslike()==1) {
				islike = true;
			}
			cv.setLike(islike);
			clist.add(cv);
		}
		System.out.println(cart);
//		cv.setName(cart.getProductId()); //根据产品id查询产品名称
	//	cv.setInventory(); //根据产品id查询产品库存?
	//	cv.setModel(model); //根据产品id查询产品型号
	//	cv.setPrice(price); //根据产品id查询产品价格
	//	Double sum = ;
	//    cv.setSum(sum); //根据产品id查询产品总价
		if(cart==null) throw new ServiceException("此用户的购物车不存在");
		JsonVO jv = new JsonVO();
		jv.setData(clist);
		return jv;
	}

	@Override
	public int doDeleteObjects(Integer... ids) {
		if(ids==null||ids.length==0)
			throw new ServiceException("请先选中");
		int rows = cartDao.doDeleteObjects(ids);
		if(rows<1)
			throw new ServiceException("删除失败");
		return rows;
	}

	@Override
	public int doEmptyAll(Integer userid) {
		if(userid==null) 
			throw new ServiceException("请先登陆");
		int rows = cartDao.doEmptyAll(userid);
		return rows;
	}

	@Override
	public int doAddLike(Integer id, Integer isLike) {
		//1.合法性验证
				if(id==null||id<=0)
					throw new ServiceException("参数不合法,id="+id);
				if(isLike!=1&&isLike!=0)
					throw new ServiceException("参数不合法,isLike="+isLike);
				//2.执行禁用或启用操作
				int rows=0;
				try{
					rows=cartDao.doAddLike(id, isLike);
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

	@Override
	public int doAddintoCart(Integer userId, Integer productId) {
		if(userId==null)
			throw new ServiceException("请先登陆");
		if(productId==null)
			throw new ServiceException("请勾选商品");
		List<CartEntity> cart = cartDao.findObjects(userId);
		//判断是否以及在购物车了
		int quantity = 0;
		int rows=0;
		for (CartEntity c : cart) {
			if(c.getProductId()==productId) {
				quantity = c.getQuantity()+1;
				rows = cartDao.updateQuantity(c.getId(),quantity);
			}
		}
		if(quantity == 0) {
			rows = cartDao.doAddintoCart(userId, productId, 1);
		}
		return rows;
	}

	@Override
	public int doupdateQuantity(Integer id, Integer quantity) {
		if(id==null)
			throw new ServiceException("请先登陆");
		if(quantity==null||quantity<0)
			throw new ServiceException("请输入正确的数量");
		int rows = cartDao.updateQuantity(id,quantity);
		return rows;
	}

	@Override
	public int doChangeChecked(Integer id, Integer checked) {
		System.out.println(id);
		System.out.println(checked);
		if(id==null)
			throw new ServiceException("请先登陆");
		if(checked!=0&&checked!=1)
			throw new ServiceException("参数错误");
		int rows = cartDao.updateChecked(id,checked);
		return rows;
	}

	@Override
	public int doAddLikes(Integer[] ids) {
		//1.合法性验证
		if(ids==null||ids.length==0)
			throw new ServiceException("参数不合法,ids="+ids);
		int rows=0;
		try{
			for (Integer id : ids) {
				int row=cartDao.doAddLike(id, 1);
				rows=rows+row;
			}
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
	
}

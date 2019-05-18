package com.jx.service;



import com.db.common.vo.JsonVO;

public interface CartService {
	JsonVO findObjects(Integer userId);
	int doDeleteObjects(Integer... ids);
	int doEmptyAll(Integer userid);
	int doAddLike(Integer id,Integer isLike);
	/*添加至购物车*/
	int doAddintoCart(Integer userId,
			Integer productId);
	int doupdateQuantity(Integer Id,
			Integer quantity);
	int doChangeChecked(Integer id, Integer checked);
	int doAddLikes(Integer[] ids);
}

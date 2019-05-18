package com.jx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.CartEntity;

public interface CartDao {
	/**根据当前登陆用户的id查询所有该用户的购物车信息*/
	List<CartEntity> findObjects(Integer userId);
	/**移除选中的购物车中的商品*/
	int doDeleteObjects(@Param("ids")Integer... ids);
	/*清空当前用户的购物车*/
	int doEmptyAll(@Param("userid")Integer userid);
	/*修改是否关注*/
	int doAddLike(@Param("id")Integer id,@Param("islike")Integer islike);
	/*添加至购物车*/
	int doAddintoCart(@Param("userId")Integer userId,
			@Param("productId")Integer productId,
			@Param("quantity")Integer quantity);
	/*如果购物车已经有了,或者改变数量时,更改数量*/
	int updateQuantity(@Param("id")Integer id,@Param("quantity")Integer quantity);
	int updateChecked(@Param("id")Integer id, @Param("checked")Integer checked);

}

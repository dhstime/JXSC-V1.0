package com.jx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.SysProduct;





public interface SysProductDao {
	/**
	 * 负责基于条件查询当前页数据 品牌 
	 */
	List<SysProduct> findPageObjects(
			@Param("brand")String brand,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 基于这个品牌手机的个数
	 */
	int getRowCount(@Param("brand") String brand);
	/**
	 * 负责将商品信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertProductObject(SysProduct entity);
	/**
	 * 修改商品信息之前  先根据id从数据库查询商品信息
	 */
	SysProduct findObjectById(Integer id);
	/**
	 * 修改之后更新商品信息
	 */
	int updateObject(SysProduct entity);
	/**
	 * 删除商品信息
	 * @param ids
	 * @return
	 */
	int deleteObject(@Param("id")Integer id);
	int deleteObjectByProductId(Integer productId);
}

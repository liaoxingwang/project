package dao;

import org.apache.ibatis.annotations.Param;

import pojo.Orders;

public interface IAllDao {
	public Orders querypage(@Param("currentpage") Integer currentpage);
	
	public void delete(@Param("orderno") String orderno);
	
	public void insert(@Param("o")Orders o);
	
	public int querySaleformCount(@Param("orderno") String orderno);
	
	public int count();
	
	
	
}

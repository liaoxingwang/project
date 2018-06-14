package biz;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.Orders;
import dao.IAllDao;


@Service()
@Transactional(propagation=Propagation.REQUIRED)
public class AllBiz {
	@Autowired
	private IAllDao dao;

	public IAllDao getDao() {
		return dao;
	}

	public void setDao(IAllDao dao) {
		this.dao = dao;
	}
	
	public Orders querypage(@Param("currentpage") Integer currentpage){
		return dao.querypage((currentpage-1)*1);
	}
	
	public void delete(@Param("orderno") String orderno){
		dao.delete(orderno);
	}
	
	public void insert(@Param("o")Orders o){
		if (dao.querySaleformCount(o.getOrderno()) != 0) {
			throw new RuntimeException("订单号不能重复！");
		}
		dao.insert(o);
	}
	
	public int count(){
		return dao.count();
	}
	
}

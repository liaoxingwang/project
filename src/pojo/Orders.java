package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Orders {
	private String orderno;
	@JSONField(format = "yyyy-MM-dd")
	private Date orderdate;
	private String suppliername;
	private String supplieraddress;
	private String department;
	private String warehouse;
	private List<OrderDetail> orderdetail = new ArrayList<OrderDetail>();
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getSupplieraddress() {
		return supplieraddress;
	}
	public void setSupplieraddress(String supplieraddress) {
		this.supplieraddress = supplieraddress;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public List<OrderDetail> getOrderdetail() {
		return orderdetail;
	}
	public void setOrderdetail(List<OrderDetail> orderdetail) {
		this.orderdetail = orderdetail;
	}
	
	
}

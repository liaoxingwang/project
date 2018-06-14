package action;




import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pojo.Orders;
import biz.AllBiz;
@Controller
public class AllAction {
	@Autowired
	private AllBiz biz;

	public AllBiz getBiz() {
		return biz;
	}

	public void setBiz(AllBiz biz) {
		this.biz = biz;
	}
	@RequestMapping("page")
	@ResponseBody
	public Map<String, Object> querypage(Integer currentpage){
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders =  biz.querypage(currentpage);
		map.put("currentpage", currentpage);
		map.put("next", currentpage+1);
		map.put("prev",currentpage<=1?1:currentpage-1);
		map.put("data",orders);
		map.put("count",biz.count());
		return map;
	}
	
	@RequestMapping("dels")
	@ResponseBody
	public Map<String, Object> delete(String orderno){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			biz.delete(orderno);
			map.put("code", 200);
			map.put("msg", "删除成功");
		} catch (Exception ex) {
			map.put("code", 500);
			map.put("msg", ex.getMessage());
		}
		return map;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders=JSON.parseObject(jsonStr,Orders.class );//反序列化
		try {
			biz.insert(orders);
			map.put("code", 200);
			map.put("msg", "新增成功");
		} catch (Exception ex) {
			map.put("code", 500);
			map.put("msg", ex.getMessage());
		}
		return map;
	}
	
}

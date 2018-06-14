<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>主页</title>
<style type="text/css">
.row:hover {
	background: #e0e0e0;
}
</style>
</head>

<body>
	<div>
		订单号:<input type="text" id="orderno" />&nbsp;&nbsp; 销售日期:<input
			type="date" id="orderdate" /><br />
			供应商:<input type="text" id="suppliername" />&nbsp;&nbsp; 销售地址:<input
			type="text" id="supplieraddress" /><br />
			部門:<input type="text" id="department" />&nbsp;&nbsp; 倉庫:<input
			type="text" id="warehouse" /><br />
		销售明细:
		<textarea id="orderdetail" rows="10" cols="50"></textarea>
	</div>
	<div>
		<button id="prev">上一条</button>
		&nbsp;&nbsp;
		<button id="next">下一条</button>
		&nbsp;&nbsp;
		<button id="save">保存</button>
		&nbsp;&nbsp;
		<button id="del">删除</button>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	var pm;
	//根据当前页码查询分页信息
	function gotoPage(currentpage) {
		//初始化
		$.post("/exam_1/page", {
			"currentpage" : currentpage
		}, function(json) {
			pm = json;//当前
			$("#orderno").val(pm.data.orderno);
			$("#orderdate").val(pm.data.orderdate);
			$("#suppliername").val(pm.data.suppliername);
			$("#supplieraddress").val(pm.data.supplieraddress);
			$("#department").val(pm.data.department);
			$("#warehouse").val(pm.data.warehouse);
			$("#orderdetail").val(JSON.stringify(pm.data.orderdetail));//序列化
		}, "json");
	}

	$(function() {
		
		//页面初始化
		gotoPage(1);
		//上一条
		$("#prev").click(function() {
			if(pm.currentpage==pm.prev){
				alert("已經是第一頁");
			}else{
				gotoPage(pm.prev);
			}
			
		});
		//下一条
		$("#next").click(function() {
			
			if(pm.next>pm.count){
				alert("已經是最後一頁啦");
			}else{
				gotoPage(pm.next);
			}
			
		});
	
		//保存
		 $("#save").click(
				function() {
					//组装json对象
					var o = {};
					o["orderno"] = $("#orderno").val();
					o["orderdate"] = $("#orderdate").val();
					o["suppliername"] = $("#suppliername").val();
					o["supplieraddress"] = $("#supplieraddress").val();
					o["department"] = $("#department").val();
					o["warehouse"] = $("#warehouse").val();
					//反序列化
					o["orderdetail"] = JSON.parse($(
							"#orderdetail").val());
					//ajax主详提交
					$.post("/exam_1/add", {
						"jsonStr" : JSON.stringify(o)
					}, function(message) {
						if (message.code == "200") {
							alert("保存成功");
							gotoPage(1);
						} else if (message.code == "500") {
							alert(message.msg);
						}
					}, "json");
				}); 

		//删除
		 $("#del").click(function() {
			if (window.confirm("是否删除?")) {
				$.post("/exam_1/dels", {
					"orderno" : $("#orderno").val()
				}, function(json) {
					if (json.code == "200") {
						alert("删除成功");
						gotoPage(1);
					}
				}, "json");
			}
		});
				
		

	})
	
</script>
</html>

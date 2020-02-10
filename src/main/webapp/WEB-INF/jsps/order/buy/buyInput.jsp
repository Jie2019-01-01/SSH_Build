<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</script>
<script type="text/javascript">
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	//修改供应商
	$(document).ready(function() {
		$('#supplier').change(function(){
			// 获取当前的供应商uuid
			var supplierUuid = $(this).val();
			$.post(
				'order_ajaxGetGtmAndGm.action', 
				{'supplierUuid': supplierUuid}, 
				function(data){
					// 商品类别
					$('.goodsType').empty();
					var gtmList = data.gtmList;
					for(var i=0; i<gtmList.length; i++){
						var gtm = gtmList[i];
						var $op = "<option value='"+gtm.uuid+"'>"+gtm.name+"</option>";
						//追加到商品类别选择框
						$('.goodsType').append($op);
					}
					// 商品信息
					$('.goods').empty();
					var gmList = data.gmList;
					for(var i=0; i<gmList.length; i++){
						var gm = gmList[i];
						var $op = "<option value='"+gm.uuid+"'>"+gm.name+"</option>";
						//追加到商品类别选择框
						$('.goods').append($op);
					}
					// 采购数量改为1
					$('.num.order_num').val(1);
					// 商品单价
					var price = data.inPriceView==null ?'': data.inPriceView;
					$('.prices.order_num').val(price);
					$('.total').html(price+'&nbsp;元');
			});
		});
		
		// 修改商品分类
		$('.goodsType').live('change', function(){
			// 获取跟随该事件改变的对象
			/*var $td = $(this).parent();
			var $gmSelect = $td.next().children('select');
			var $num = $td.next().next().children('input');
			var $price = $td.next().next().next().children('input');
			var $total = $td.next().next().next().next();*/
			var $tr = $(this).parent().parent();
			var $gmSelect = $tr.children('td:eq(1)').children('select');
			var $num = $tr.children('td:eq(2)').children('input');
			var $price = $tr.children('td:eq(3)').children('input');
			var $total = $tr.children('td:eq(4)');
			
			var goodsTypeUuid = $(this).val();
			$.post('order_ajaxGetGm.action', {'goodsTypeUuid': goodsTypeUuid}, function(data){
				$gmSelect.empty();
				var gmList = data.gmList;
				for(var i=0; i<gmList.length; i++){
					var gm = gmList[i];
					var $op = "<option value='"+gm.uuid+"'>"+gm.name+"</option>";
					$gmSelect.append($op);
				}
				// 采购数量改为1
				$num.val(1);
				// 商品单价
				var price = data.inPriceView==null ?'': data.inPriceView;
				$price.val(price);
				$total.html(price+'&nbsp;元');
			});
		});
		// 修改商品
		$('.goods').live('change', function(){
			var $tr = $(this).parent().parent();
			var $num = $tr.children('td:eq(2)').children('input');
			var $price = $tr.children('td:eq(3)').children('input');
			var $total = $tr.children('td:eq(4)');
			
			var goodsUuid = $(this).val();
			$.post('order_ajaxGetInPrice.action', {'goodsUuid':goodsUuid}, function(data){
				// 采购数量
				$num.val(1);
				// 商品单价
				var price = data.inPriceView==null ?'': data.inPriceView;
				$price.val(price);
				$total.html(price+'&nbsp;元');
			});
		});
		
		// 控制新建按钮点击间隔（每次点击要求，上次点击的数据返回之后才可再点）
		var checkFlag = true;
		// 采购订单新建
		$('#add').click(function(){
			// 将供应商设置为不可修改
			$('#supplier').attr('disabled', true);
			$('.goodsType').attr('disabled', true);
			$('.goods').attr('disabled', true);
			
			if(!checkFlag){
				alert('你慢点...');
				return;
			}
			checkFlag = false;
			
			// 判断商品是否已经新建
			var goods = $('.goods');
			var used = '';
			for(var i=0; i<goods.length; i++){
				used += "@"+goods[i].value+"@,";
			}
			$.post(
				'order_ajaxGetGtmAndGm2.action', 
				{'supplierUuid': $('#supplier').val(), 'used':used}, 
			function(data){
				// 生成tr行
				$tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
				
				// 生成第一个td
				$td = $('<td height="30"></td>');
				$select = $('<select name="" class="goodsType" style="width:200px"></select>');
				var gtmList = data.gtmList;
				for(var i=0; i<gtmList.length; i++){
					var gtm = gtmList[i];
					$op = $('<option value="'+gtm.uuid+'">'+gtm.name+'</option>');
					$select.append($op);
				}
				$td.append($select);
				$tr.append($td);
				
				// 生成第二个td
				$td = $('<td></td>');
				$select = $('<select name="" class="goods" style="width:200px"></select>');
				var gmList = data.gmList;
				for(var i=0; i<gmList.length; i++){
					var gm = gmList[i];
					$op = $('<option value="'+gm.uuid+'">'+gm.name+'</option>');
					$select.append($op);
				}
				$td.append($select);
				$tr.append($td);
				
				// 生成第三个td
				$td = $('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"></td>');
				$tr.append($td);
				
				// 生成第四个td
				var price = data.inPriceView;
				$td = $('<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="'+price+'"> 元</td>');
				$tr.append($td);
				
				// 生成第五个td
				$td = $('<td class="total" align="right">'+price+'&nbsp;元</td>');
				$tr.append($td);
				
				// 生成第六个td
				$td = $('<td><a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif"> 删除</a></td>');
				$tr.append($td);
				
				$('#finalTr').before($tr);
				
				// 判断返回的类别和商品是否都是一个，如果是则隐藏“新建”按钮
				if(gtmList.length==1 && gmList.length==1){
						// 第一种
					//$('#add').hide();
						// 第二种
					$('#add').css('display','none');
						//企业开发推荐:定义一个新的class属性，为其设置display=none，然后切换到该class
					//$('#add').toggleClass('newClass');
				}
				
				checkFlag = true;
			});
		});
		
		// 订单删除
		$('.deleteBtn').live('click',function(){
			if($('.deleteBtn').length==1){
				alert('留下一个吧！');
				return;
			}
			
			var $curTr = $(this).parent().parent();
			$curTr.remove();
			
			$('#add').css('display','inline');
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="inList.jsp" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
							<s:select id="supplier" cssClass="kuan" cssStyle="width:190px" list="supplierList" listKey="uuid" listValue="name"/>
						</td>
						<td height="30">
							<a id="add"><img src="images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<s:select cssClass="goodsType" cssStyle="width:200px" list="goodsTypeList" listKey="uuid" listValue="name"/>
						</td>
						<td>
							<s:select cssClass="goods" cssStyle="width:200px" list="goodsList" listKey="uuid" listValue="name"/>
						</td>
						<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${goodsList[0].inPriceView}"/> 元</td>
						<td class="total" align="right">${goodsList[0].inPriceView}&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">${goodsList[0].inPriceView}&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</form>
	</div>
	
	<div class="content-bbg"></div>
</div>

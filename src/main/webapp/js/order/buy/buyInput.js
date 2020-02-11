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
					
					totalAll();
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
			var gmSelects = $('.goods');
			var used = '';
			for(var i=0; i<gmSelects.length; i++){
				used += '@'+gmSelects[i].value+'@,';
			}
			
			$.post('order_ajaxGetGm.action', {'goodsTypeUuid': goodsTypeUuid, 'used': used}, function(data){
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
				
				totalAll();
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
				
				totalAll();
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
				$select = $('<select name="goodsUuids" class="goods" style="width:200px"></select>');
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
				
				totalAll();
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
			
			totalAll();
		});
		
		// 修改采购数量，价格实现联动
		// 保证数量发生变化时，价格立即也跟随改变，这里使用了keyup事件:键盘松开触发
		$('.num').live('keyup', function(){
			$(this).val($(this).val().replace(/\D/g,''));
			total($(this));
			totalAll();
		});
		
		// 修改单价
		$('.prices').live('keyup', function(){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); 
			total($(this));
			totalAll();
		});
		
		// 修改合计
		function total(obj){
			var $curTr = obj.parent().parent();
			//获取采购数量的值
			var $num = obj.parent().parent().children('td:eq(2)').children('input');
			//获取单价的值
			var $price = obj.parent().parent().children('td:eq(3)').children('input');
			//采购数量与单价相乘得到合计的值,实际开发中使用下列写法
			var $total = $num.val() * $price.val();
			$curTr.children('td:eq(4)').html(intToFloat($total)+'&nbsp;元');
		}
		
		// 计算总计
		function totalAll(){
			var $nums = $('.num');
			var $prices = $('.prices');
			var $sum = 0;
			for(var i=0; i<$nums.length; i++){
				var total = $nums[i].value * $prices[i].value;
				$sum += total;
			}
			$('.all').html(intToFloat($sum)+"&nbsp;元");
		}
		
		// 保存
		$('#submitOrder').click(function(){
			$('#supplier').attr('disabled', false);
			$('.goods').attr('disabled', false);
			$('form:first').submit();
		});
	});
	
	//小数点后保留两位
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}

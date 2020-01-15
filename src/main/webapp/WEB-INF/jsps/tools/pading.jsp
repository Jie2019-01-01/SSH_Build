<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页</title>
<s:hidden name="pageNum"/>
<s:hidden name="lastPage"/>
<script type="text/javascript">
	$(function(){
		// 控制分页按钮
		/*
			1. pageNum=1; 前两个按钮隐藏
			2. pageNum=lastPage; 后两个按钮隐藏
			3. 1< pageNum <lastPage; 全部显示
			4. lastPage=1; 全部隐藏
		*/
		var pageNum = $('[name=pageNum]').val();
		var lastPage = $('[name=lastPage]').val();
		if(lastPage==1){
			$('#fir').css('display','none');
			$('#pre').css('display','none');
			$('#next').css('display','none');
			$('#last').css('display','none');
		}else if(pageNum==lastPage){
			$('#fir').css('display','inline');
			$('#pre').css('display','inline');
			$('#next').css('display','none');
			$('#last').css('display','none');
		}else if(pageNum==1){
			$('#fir').css('display','none');
			$('#pre').css('display','none');
			$('#next').css('display','inline');
			$('#last').css('display','inline');
		}else{
			$('#fir').css('display','inline');
			$('#pre').css('display','inline');
			$('#next').css('display','inline');
			$('#last').css('display','inline');
		}
		
		
		// 上一页
		$('#pre').click(function(){
			$('[name=pageNum]').val(pageNum-1);
			$('form:first').submit();
		});
		// 下一页
		$('#next').click(function(){
			$('[name=pageNum]').val(pageNum*1+1);
			$('form:first').submit();
		});
		// 首页
		$('#fir').click(function(){
			$('[name=pageNum]').val(1);
			$('form:first').submit();
		});
		// 尾页
		$('#last').click(function(){
			$('[name=pageNum]').val(lastPage);
			$('form:first').submit();
		});
	});
</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="51%">&nbsp;</td>
			<td width="13%">共${records}条记录
			<td width="6%">
				<a id="fir" class="sye">首&nbsp;&nbsp;页</a>
			</td>
			<td width="6%">
				<a id="pre" class="sye">上一页</a>
			</td>
			<td width="6%">
				<a id="next" class="sye">下一页</a>
			</td>
			<td width="6%">
				<a id="last" class="sye">末&nbsp;&nbsp;页</a>
			</td>
			<td width="12%">当前第<span style="color:red;">${pageNum}</span>/${lastPage}页</td>
		</tr>
	</table>
</body>
</html>
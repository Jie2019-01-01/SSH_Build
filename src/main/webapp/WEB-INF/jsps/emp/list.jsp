<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	function showMsg(msg,uuid){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="actionName";
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="emp_list.action" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">用&nbsp;户&nbsp;名</td>
						<td><input type="text" size="14" /></td>
						<td>真实姓名</td>
						<td><input type="text" size="14" /></td>
						<td>电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
						<td><input type="text" size="14" /></td>
						<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td>
							<s:select cssClass="kuan" name="eqm.gender" list="@cn.itcast.erp.auth.emp.vo.EmpModel@genderMap" 
								headerKey="-1" headerValue="----请-选-择----"/>
						</td>
							
						<td width="70"><a href="emp_input.action"> <img src="images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td  height="30">电子邮件</td>
						<td><input type="text" size="14" /></td>
						<td>登录时间</td>
						<td>
							<s:textfield name="eqm.lastLoginTimeView" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							<s:hidden name="eqm.lastLoginTime"/>
						</td>
						<td>出生日期</td>
						<td>
							<s:textfield name="eqm.birthDayView" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							<s:hidden name="eqm.birthDay"/>
						</td>
						<td>部门名称</td>
						<td>
							<s:select cssClass="kuan" name="eqm.dm.uuid" list="depList" listKey="uuid" listValue="name"></s:select>
						</td>
						<td><a id="query"> <img src="images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<s:if test="#empList.size==0">
					<center>
						<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
					</center>
				</s:if>
				<s:else>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr align="center"
							style="background:url(images/table_bg.gif) repeat-x;">
							<td width="10%" height="30">用户名</td>
							<td width="10%">真实姓名</td>
							<td width="5%">性别</td>
							<td width="12%">出生日期</td>
							<td width="10%">电话</td>
							<td width="12%">电子邮件</td>
							<td width="9%">所属部门</td>
							<td width="16%">最后登录时间</td>
							<td width="16%">操作</td>
						</tr>
						<s:iterator value="empList">
							<tr align="center" bgcolor="#FFFFFF">
								<td width="13%" height="30">${userName}</td>
								<td>${name}</td>
								<td>${genderView}</td>
								<td>${birthDayView}</td>
								<td>${tele}</td>
								<td>${email}</td>
								<td>${dm.name}</td>
								<td>${lastLoginTimeView}</td>
								<td>
									<img src="images/icon_3.gif" /> 
									<span style="line-height:12px; text-align:center;"> 
										<s:a action="emp_input.action" cssClass="xiu">
											<s:param name="em.uuid" value="uuid"/>
											修改
										</s:a>
									</span> 
									<img src="images/icon_04.gif" /> 
									<span style="line-height:12px; text-align:center;"> 
										<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',318)">删除</a>
									</span>
								</td>
							</tr>
						</s:iterator>
					</table>
					<s:include value="../tools/pading.jsp"></s:include>
				</s:else>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>

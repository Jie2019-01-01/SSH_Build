<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="js/treeview/jquery.treeview.css" />
	<link rel="stylesheet" href="js/treeview/screen.css" />
	<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="js/treeview/jquery.cookie.js" type="text/javascript"></script>
	<script src="js/treeview/jquery.treeview.min.js" type="text/javascript"></script>
	<script src="js/treeview/jquery.treeview.async.js" type="text/javascript"></script>
</head>
<body>
	<ul id="black" class="filetree"></ul>
</body>
<script type="text/javascript">
	$("#black").treeview({
		url: "menu_showMenu.action"
	})
</script>
</html>
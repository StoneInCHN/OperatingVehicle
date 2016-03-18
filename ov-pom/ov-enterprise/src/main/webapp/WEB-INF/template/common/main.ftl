[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="CSH管理系统">
    <meta name="author" content="CSH">
    <link rel="icon" href="${base}/resources/images/favicon.ico">
    <title>管理中心</title>
    <link href="${base}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/resources/css/font-awesome.min.css" rel="stylesheet">
 	<link rel="stylesheet" type="text/css" href="${base}/resources/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${base}/resources/easyui/themes/icon.css">
 	<link rel="stylesheet" type="text/css" href="${base}/resources/css/common.css">
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/main.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/webuploader.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/upload-style.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/uploadPhotos_style.css" >
    <link rel="stylesheet" type="text/css" href="${base}/resources/css/evaluting.css">
	<!--[if lt IE 9]>
    <script type="text/javascript" src="${base}/resources/js/respond.1.4.2.min.js"></script>
    <![endif]-->
  </head>

  <body class="easyui-layout" >   
	<div class="header" data-options="region:'north',split:true,noheader:true,collapse:'west'">
		<div class="logo">后台管理中心</div>
		<div id="nav-wrap" class="nav-wrap"  style="width:730px">
			<ul class="nav nav-pills">
				<li><a href="#main"><i class="fa fa-home fa-1x"></i>首页</a></li>
				[@shiro.hasPermission name="systemManage"]
				<li><a href="#system"><i class="fa fa-users fa-1x"></i>系统管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="personnelManage"]
				<li><a href="#personnel"><i class="fa fa-users fa-1x"></i>${message("csh.personnel.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="vehicleManage"]
				<li><a href="#vehicle"><i class="fa fa-users fa-1x"></i>${message("csh.vehicle.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="deviceManage"]
				<li><a href="#device"><i class="fa fa-users fa-1x"></i>${message("csh.device.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="endUserManage"]
				<li><a href="#endUser"><i class="fa fa-users fa-1x"></i>${message("csh.endUser.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="reservationMange"]
				<li><a href="#reservationMange"><i class="fa fa-users fa-1x"></i>${message("csh.reservationMange.config")}</a></li>
				[/@shiro.hasPermission]
				<a href="#" id="nav-switcher" class="nav-switcher">更多<i class="fa fa-angle-down fa-1x"></i></a>
				<a id="nav-switcherset" href="#" class="router nav-switcherset off"><span class="middlehelper">设置</span><span><i class="fa fa-cog"></i></span></a>
			</ul>
		</div>
		<div class="welcome pull-right">
		<marquee scrollamount='2'>欢迎 ${tenantAccount.userName}登录！</marquee></div>
		<ul class="user-profile">
		    <li  class="dropdown" >
				  <a class="btn  dropdiown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    <i class="fa fa-cog"></i>
				   
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				     <li><a href="#" id="changePasswordHref">修改密码</a></li>
				    <li role="separator" class="divider"></li>
				    <li><a href="${base}/console/common/logout.jhtml">退出</a></li>
				  </ul>
		    </li>
		</ul>
	</div>   
    <div class="footer" data-options="region:'south',split:true,noheader:true" ></div>   
    <div class="left-content" data-options="region:'west',title:'导航菜单',split:true,width:115" >
    	<ul title="${message("csh.system.config")}" id="system">
    		[@shiro.hasPermission name="tenantAccount"]
    		<li><a href="#" data-url="${base}/console/tenantAccount/tenantAccount.jhtml">${message("csh.system.tenantAccount")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="role"]
    		<li><a href="#" data-url="${base}/console/role/role.jhtml">${message("csh.system.tenantAccount.role")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="operationLog"]
    		<li><a href="#" data-url="${base}/console/operationLog/operationLog.jhtml">${message("csh.system.tenantAccount.operationLog")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.personnel.config")}" id="personnel">
    		[@shiro.hasPermission name="department"]
    		<li><a href="#" data-url="${base}/console/department/department.jhtml">${message("csh.personnel.department")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="position"]
    		<li><a href="#" data-url="${base}/console/position/position.jhtml">${message("csh.personnel.position")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="tenantUser"]
    		<li><a href="#" data-url="${base}/console/tenantUser/tenantUser.jhtml">${message("csh.personnel.tenantuser")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.vehicle.config")}" id="vehicle">
    		[@shiro.hasPermission name="vehicle"]
    		<li><a href="#" data-url="${base}/console/vehicle/vehicle.jhtml">${message("csh.vehicle.vehicle")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleDetail"]
    		<li><a href="#" data-url="${base}/console/vehicleDetail/vehicleDetail.jhtml">${message("csh.vehicle.vehicleLine")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleMaintain"]
    		<li><a href="#" data-url="${base}/console/vehicleMaintain/vehicleMaintain.jhtml">${message("csh.vehicle.vehicleMaintain")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.device.config")}" id="device">
    		[@shiro.hasPermission name="device"]
    		<li><a href="#" data-url="${base}/console/deviceInfo/deviceInfo.jhtml">${message("csh.device.device")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.endUser.config")}" id="endUser">
    		[@shiro.hasPermission name="endUser"]
    		<li><a href="#" data-url="${base}/console/endUser/endUser.jhtml">${message("csh.endUser.endUser")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("csh.reservationManage.config")}" id="reservationMange">
    		[@shiro.hasPermission name="repareReservation"]
    		<li><a href="#" data-url="${base}/console/repareReservation/repareReservation.jhtml">${message("csh.repareReservation.repareReservation")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="maintainReservation"]
    		<li><a href="#" data-url="${base}/console/maintainReservation/maintainReservation.jhtml">${message("csh.maintainReservation.maintainReservation")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>          
    </div>
  
    <div class="main-content" data-options="region:'center'">
    	<div id="manager-tabs">   
		    <div title="起始页">
		    </div>    
		</div>  
    </div>    
    
    
    <div id = "commonMainDialog"></div>
    <!-- JavaScript-->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.serializejson.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${base}/resources/js/validator.js"></script>
	<script type="text/javascript" src="${base}/resources/js/highcharts.js"></script>
	<script type="text/javascript" src="${base}/resources/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/js/message.js"></script>
	<script type="text/javascript" src="${base}/resources/js/main.js"></script>
	<script type="text/javascript" src="${base}/resources/js/dropdown.js"></script>
	<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/resources/js/webuploader.min.js"></script>
	<script type="text/javascript" src="${base}/resources/js/kindeditor/kindeditor-min.js"></script>
	<script src="${base}/resources/js/jquery.bootstrap.newsbox.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/fileUploadCommon.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.easing.1.3.js"></script>
	<script>
		var BASE_URL = '${base}/resources' ;
	</script>
  </body>
</html>

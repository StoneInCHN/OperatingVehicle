[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="OV管理系统">
    <meta name="author" content="OV">
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
	<!--[if lt IE 9]>
    <script type="text/javascript" src="${base}/resources/js/respond.1.4.2.min.js"></script>
    <![endif]-->
   	<!-- 百度地图api -->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${message("ov.baiduMap.ak")}"></script>
    <!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
    <!-- 坐标转换-->
    <script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
    
  </head>

  <body class="easyui-layout" >   
	<div class="header" data-options="region:'north',split:true,noheader:true,collapse:'west'">
		<!--<div class="logo"><img src="${base}/resources/images/yly_logo_small.png" width="90" height="50">车辆运营管理</div>-->
		<div class="logo"><img src="${base}/resources/images/yly_logo_small.png" width="90" height="50">后台管理中心</div>
		<!--<div class="logo">车辆后台管理中心</div>-->
		<div id="nav-wrap" class="nav-wrap"  style="width:700px">
			<ul class="nav nav-pills">
				<li><a href="#main"><i class="fa fa-home fa-1x"></i>首页</a></li>
				[@shiro.hasPermission name="systemManage"]
				<li><a href="#systemManage"><i class="fa fa-users fa-1x"></i>系统管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="vehicleManagement"]
				<li><a href="#vehicleManagement"><i class="fa fa-users fa-1x"></i>${message("ov.vehicleManagement.settle")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="vehicleScheduling"]
				<li><a href="#vehicleScheduling"><i class="fa fa-users fa-1x"></i>车辆调度</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="businessManagement"]
				<li><a href="#businessManagement"><i class="fa fa-users fa-1x"></i>企业管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="statisticalReport"]
				<li><a href="#statisticalReport"><i class="fa fa-users fa-1x"></i>统计报表</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="settleCenter"]
				<li><a href="#settleCenter"><i class="fa fa-users fa-1x"></i>结算中心</a></li>
				[/@shiro.hasPermission]
				<a href="#" id="nav-switcher" title="更多" class="nav-switcher"><i class="fa fa-angle-down fa-1x">  </i></a>
			</ul>
		</div>
		<div class="welcome pull-right">
			<marquee scrollamount='2'>欢迎 ${tenantAccount.userName}登录！</marquee>
		</div>
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
    <div class="left-content" data-options="region:'west',title:'导航菜单',split:true,width:125"  style="background-color:#fbfbfb">
    	<ul title="${message("ov.system.manage")}" id="systemManage">
    		[@shiro.hasPermission name="userManage"]
    		<li><a href="#" data-url="${base}/console/tenantUser/tenantUser.jhtml">人员管理</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="accountManage"]
    		<li><a href="#" data-url="${base}/console/tenantAccount/tenantAccount.jhtml">用户管理</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="departmentManage"]
    		<li><a href="#" data-url="${base}/console/department/department.jhtml">部门管理</a></li>
    		[/@shiro.hasPermission]
     		[@shiro.hasPermission name="positionManage"]
    		<li><a href="#" data-url="${base}/console/position/position.jhtml">职位管理</a></li>
    		[/@shiro.hasPermission]  		
    		[@shiro.hasPermission name="authorityManage"]
    		<li><a href="#" data-url="${base}/console/role/role.jhtml?path=roleAuth">权限管理</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="roleManage"]
    		<li><a href="#" data-url="${base}/console/role/role.jhtml?path=role">角色管理</a></li>
    		[/@shiro.hasPermission]    		    		    		
    	</ul>
    	<ul title="${message("ov.vehicle.manage")}" id="vehicleManagement">
    		[@shiro.hasPermission name="maintenanceCharge"]
    		<li><a href="#" data-url="${base}/console/maintenanceCharge/maintenanceCharge.jhtml">保养信息</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="oilCharge"]
    		<li><a href="#" data-url="${base}/console/oilCharge/oilCharge.jhtml">加油信息</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="upkeepCharge"]
    		<li><a href="#" data-url="${base}/console/upkeepCharge/upkeepCharge.jhtml">维修信息</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicle"]
    		<li><a href="#" data-url="${base}/console/vehicle/vehicle.jhtml">${message("ov.vehicleManagement.vehicle")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="device"]
    		<li><a href="#" data-url="${base}/console/deviceInfo/deviceInfo.jhtml">${message("ov.vehicleManagement.device")}</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="electronicRail"]
    		<li><a href="#" data-url="${base}/console/electronicRail/electronicRail.jhtml">电子围栏</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="motorcade"]
    		<li><a href="#" data-url="${base}/console/motorcade/motorcade.jhtml">车队管理</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleTrack"]
    		<li><a href="#" data-url="${base}/console/vehicleTrack/track.jhtml">${message("ov.vehicleManagement.vehicleTrack")}</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    	<ul title="${message("ov.business.branch")}" id="businessManagement">
    		[@shiro.hasPermission name="branchBusinessManage"]
    		<li><a href="#" data-url="${base}/console/tenantInfo/branchBusiness.jhtml">分公司管理</a></li>
    		[/@shiro.hasPermission]  		    		    		
    	</ul>    
    	<ul title="${message("ov.vehicle.scheduling")}" id="vehicleScheduling">
    		[@shiro.hasPermission name="useCarRequest"]
    		<li><a href="#" data-url="${base}/console/vehicleScheduling/useCarRequest.jhtml">用车请求</a></li>
    		[/@shiro.hasPermission]	
    		[@shiro.hasPermission name="vehicleAssign"]
    		<li><a href="#" data-url="${base}/console/vehicleScheduling/vehicleAssign.jhtml">车辆指派</a></li>
    		[/@shiro.hasPermission]	
    	</ul>     
    	<ul title="${message("ov.statistical.report")}" id="statisticalReport">
    		[@shiro.hasPermission name="maintenanceChargeReport"]
    		<li><a href="#" data-url="${base}/console/maintenanceChargeReport/maintenanceChargeReport.jhtml">保养费用统计</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="oilChargeReport"]
    		<li><a href="#" data-url="${base}/console/oilChargeReport/oilChargeReport.jhtml">油费统计</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="upkeepChargeReport"]
    		<li><a href="#" data-url="${base}/console/upkeepChargeReport/upkeepChargeReport.jhtml">维修费统计</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="vehicleMileageReport"]
    		<li><a href="#" data-url="${base}/console/vehicleMileageReport/vehicleMileageReport.jhtml">车辆里程统计</a></li>
    		[/@shiro.hasPermission]	    		    		
    	</ul>
    	<ul title="${message("ov.vehicleScheduling.settle")}" id="settleCenter">
    		[@shiro.hasPermission name="settle:Management"]
    		<li><a href="#" data-url="${base}/console/tenantClearingRecord/clearingRecordsManagement.jhtml">结算管理</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="settle:Search"]
    		<li><a href="#" data-url="${base}/console/tenantClearingRecord/clearingRecordsView.jhtml">结算查询</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    </div>
     
     <div class="main-content" data-options="region:'center'">
    	<div id="manager-tabs">   
		    <div title="起始页" style="background-color:#f3f3f6">
		    		<div>
		    			<div class="row" style="float:right">
						<div class="col-md-6" style="width:22%">
								<div class="reportLabel" style="background:url('${base}/resources/images/tenantUser.jpg')">
								<font color="#bbbbcc"><h3  style="margin-top: 20px; margin-left: 120px">${tenantUserCount}</h3><p style="margin-left: 110px;font-size:13px">租户用户</p></font>
								</div>
						</div>
						<div class="col-md-6" style="width:22%">
								<div class="reportLabel" style="background:url('${base}/resources/images/vehicle.jpg')">
								<font color="#bbbbcc"><h3  style="margin-top: 20px; margin-left: 120px">${vehicleCount}</h3><p style="margin-left: 100px;font-size:13px">车辆总数</p></font>
								</div>
						</div>
						<div class="col-md-6" style="width:22%">
								<div class="reportLabel" style="background:url('${base}/resources/images/vehicleRequest.jpg')">
								<font color="#bbbbcc"><h3  style="margin-top: 20px; margin-left: 120px">${vehicleSchedulingCount}</h3><p style="margin-left: 100px;font-size:13px">用车请求</p></font>
								</div>
						</div>
						<div class="col-md-6" style="width:22%">
								<div class="reportLabel" style="background:url('${base}/resources/images/applySettle.jpg')">
								<font color="#bbbbcc"><h4 style="margin-top: 20px;margin-left: 100px">申请结算</h4></font>
								</div>
						</div>
				        </div>
				    </div>
					<div class="main-content-center">
					
							<div style="height:800px;width:650px;float:left;padding:0 20px">
								<div class="reportChart">
						    			<div id="maintenanceChargeReportDiv" style="height:350px;width:600px;"></div>
						    		</div>
						    		<div class="reportChart">
										<div id="upkeepChargeReportDiv" style="height:360px;width:600px;"></div>
									</div>
							</div>
							<div style="height:820px;width:600px;float:left;padding:0 20px">
									<div class="row">
										<div class="col-md-6" style="width:50%">
												<div class="reportChart" style="padding:20px 0px 5px 0px">
						    						<div id="oilChargeAmountReportDiv" style="height:300px;width:255px;"></div>
						    					</div>
										</div>
										<div class="col-md-6" style="width:50%">
												<div class="reportChart" style="padding:20px 0px 5px 0px">
						    						<div id="oilChargeCountReportDiv" style="height:300px;width:255px;"></div>
						    					</div>	
										</div>
									</div>
									<div class="row">
						    		<div class="reportChart">
						    			<div id="vehicleMileageChargeReportDiv" style="height:400px;width:550px;"></div>
						    		</div>
						    		</div>
							</div>

						    
						</div>
					</div>
		    </div>    
		</div>  
    </div>    
    <div class="footer" data-options="region:'south',split:true,noheader:true" ></div> 
    <div id = "commonMainDialog">
	    <div id = "searchRole"></div>
		<div id = "searchVehicle"></div>
	    <div id = "searchTenantUser"></div>
	    <div id = "changePassword">
    </div>
   
    <!-- JavaScript-->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.serializejson.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${base}/resources/js/validator.js"></script>
	<script type="text/javascript" src="${base}/resources/js/highcharts.js"></script>
	<script type="text/javascript" src="${base}/resources/js/exporting.js"></script>
	<script type="text/javascript" src="${base}/resources/js/highcharts-more.js"></script>
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

[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="OV车队管理平台">
    <meta name="author" content="OV">
    <link rel="icon" href="${base}/resources/images/favicon.ico">
    <title>OV车队管理平台</title>
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
		<!--<div class="logo"><img src="${base}/resources/images/yly_logo_small.png" width="90" height="50">后台管理中心</div>-->
		<div class="logo">OV车队管理平台</div>
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
			<marquee scrollamount='2'>欢迎 [#if tenantAccount.realName != null] ${tenantAccount.realName} [#else] ${tenantAccount.userName} [/#if]登录！
			本次登录时间：${tenantAccount.loginDate}，登录IP：${tenantAccount.loginIp}</marquee>
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
    <div class="left-content" data-options="region:'west',title:'导航菜单',split:true,width:105"  style="background-color:#fbfbfb">
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
    		[@shiro.hasPermission name="settleManagement"]
    		<li><a href="#" data-url="${base}/console/tenantClearingRecord/clearingRecordsManagement.jhtml">结算管理</a></li>
    		[/@shiro.hasPermission]
    		[@shiro.hasPermission name="settleSearch"]
    		<li><a href="#" data-url="${base}/console/tenantClearingRecord/clearingRecordsView.jhtml">结算查询</a></li>
    		[/@shiro.hasPermission]
    	</ul>
    </div>
     
     <div class="main-content" data-options="region:'center'">
    	<div id="manager-tabs">   
		    <div title="起始页" style="background-color:#f3f3f6">
					<div class="main-content-center">
						<table>
							<tr>
								<td>
										<div class="reportChart">
						    				<div id="maintenanceChargeReportDiv" style="height:330px;width:600px;"></div>
						    			</div>
						    			<div class="reportChart">
											<div id="upkeepChargeReportDiv" style="height:290px;width:600px;"></div>
										</div>
								</td>
								<td>
										<table style="margin-top:10px">
									    	<tr>
									    		<td onmouseover="this.style.cursor='pointer'" 
											    		onclick="shortcutNavigation('人员管理','${base}/console/tenantUser/tenantUser.jhtml')">
											    	<div class="reportLabel leftLabel tenantUserImg">
													<font color="#bbbbcc"><h3  style="margin-left: 120px;margin-top:0px">${tenantUserCount}</h3><p style="margin-left: 110px;font-size:13px">当前用户</p></font>
													</div>
									    		</td>
									    		<td onmouseover="this.style.cursor='pointer'" 
											    		onclick="shortcutNavigation('车辆信息','${base}/console/vehicle/vehicle.jhtml')">
									    			<div class="reportLabel rightLabel vehicleImg">
													<font color="#bbbbcc"><h3  style="margin-left: 120px;margin-top:0px">${vehicleCount}</h3><p style="margin-left: 100px;font-size:13px">车辆总数</p></font>
													</div>
									    		</td>
									    		<td rowspan="2">
									    			<div style="margin-top:0px;margin-bottom:12px;padding-top:0px;height:60px;width:258px;">
									    					<div style="height:60px;width:258px;border-radius: 4px 4px 0px 0px;background-color:#f16783;">
									    							<h4  style="color:#fff;margin-left: 90px;margin-top:0px;padding-top:24px">设备绑定</h4>
									    					</div>
									    				    <div style="height:70px;width:258px;border-radius: 0px 0px 4px 4px;background-color:#fff;">
									    				    		<font color="#bbbbcc">
									    				    			<span  style="float:left;margin:5px auto 15px 10px;padding-top:10px;padding-bottom:20px;font-size:20px">已绑定：${deviceCount}</span>
									    				    			<span  style="float:right;margin:5px 10px 15px auto;padding-top:10px;padding-bottom:20px;font-size:20px">未绑定：${vehicleCount-deviceCount}</span>
									    				    		</font>
									    				    </div>
													</div>
									    		</td>
									    	</tr>
									    	<tr>
									    		<td onmouseover="this.style.cursor='pointer'" 
											    		onclick="shortcutNavigation('车辆指派','${base}/console/vehicleScheduling/vehicleAssign.jhtml')">
									    			<div class="reportLabel leftLabel vehicleRequestImg">
														<font color="#bbbbcc">
																<h3  style="margin-left: 120px;margin-top:0px">${vehicleSchedulingCount}</h3>
																<p style="margin-left: 100px;font-size:13px">用车请求总数</p>
														</font>
													</div>
									    		</td>
											    	[#if isParentTenant == true]
											    <td onmouseover="this.style.cursor='pointer'" 
											    		onclick="shortcutNavigation('结算管理','${base}/console/tenantClearingRecord/clearingRecordsManagement.jhtml')">
													<div class="reportLabel rightLabel applySettleImg">
														<h4  style="color:#bbbbcc;margin-left: 100px;margin-top:0px;padding-top:20px">结算管理</h4>
													</div>
												</td>
													[#else]
												<td onmouseover="this.style.cursor='pointer'" 
														onclick="shortcutNavigation('结算查询','${base}/console/tenantClearingRecord/clearingRecordsView.jhtml')">
													<div class="reportLabel rightLabel applySettleImg">
														<h4  style="color:#bbbbcc;margin-left: 100px;margin-top:0px">结算查询</h4>
													</div>
												</td>
													[/#if]
									    	</tr>
									    	</table>
									    	<table>
									    	<tr>
													<td>
														<div style="padding:20px;height:140px;width: 680px;background-color: #fff;border-radius: 6px;">
															 <p style="font-size:13px;">欢迎您使用 ${tenantAccount.userName} 账号登录!</p>
															 <p style="font-size:13px;color:#bbbbcc">昨日有 ${vehicleSchedulingCount} 个用车请求，成功完成了 ${assignedCountYesterday} 个车辆指派！</p>
															 <p style="font-size:13px">今日，目前已有 ${vehicleSchedulingCount} 个用车请求，完成了 ${assignedCountYesterday} 个车辆指派，还剩 ${vehicleSchedulingCount - assignedCountYesterday} 个用车请求未指派，
															 		<a href="#" onclick="shortcutNavigation('车辆指派','${base}/console/vehicleScheduling/vehicleAssign.jhtml')">马上去看看</a>
															 </p>
															 <p></p>
														</div>
													</td>
											</tr>
								    	</table>
								    	<table>
								    	<tr>
								    		<td>
								    			<div class="reportChart" style="margin:10px 10px 10px 0">
								    				<div id="oilChargeAmountReportDiv" style="height:350px;width:335px;"></div>
								    			</div>
								    		</td>
								    		<td>
								    			<div class="reportChart" style="margin:10px 10px 10px 0">
								    				<div id="oilChargeCountReportDiv" style="height:350px;width:335px;"></div>
								    			</div>
								    	</tr>
								    </table>
								</td>
							</tr>
						</table>
									
						    
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

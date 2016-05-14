[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>${message("ov.main.title")}</title>
<link rel="shortcut icon" type="image/x-icon" href="${base}/resources/images/carlife.ico" media="screen" /> 
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/bootstrap-theme.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" media="screen">
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/main.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<script src="${base}/resources/js/excanvas.min.js"></script>
	<script src="${base}/resources/js/respond.min.js"></script>  
<![endif]-->   

</head>
<body>
	<div class="header">
	    <div class="container">
	      <div class="row">
	        <div class="col-xs-4 col-md-4 col-lg-4">
	          <div class="logo">
	            <h1><a href="#">车队平台<span class="bold"></span></a></h1>
	            <p class="meta">后台管理系统</p>
	          </div>
	        </div>
	        <div class="col-xs-8 col-md-8 col-lg-8">
		      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">                
			        <ul class="nav navbar-nav pull-right">
			          <li class="dropdown pull-right">            
			            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
			              <i class="fa fa-user"></i> [@shiro.principal /] <b class="caret"></b>              
			            </a>
			            <ul class="dropdown-menu">
		             	 	<li><a href="../common/logout.jhtml" target="_top"><i class="fa fa-power-off"></i>&nbsp;&nbsp;${message("ov.main.logout")}</a></li>
			            </ul>
			          </li>
			        </ul>
		      </nav>
		   </div>
	    </div>
	  </div>
    </div>
    <div class="content">
      <div class="sidebar" >
      	<ul id="nav">
           [#list ["admin:admin", "admin:role","admin:account"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#admin" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("ov.main.systemNav")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
				              [@shiro.hasPermission name="admin:admin"]
								<li >
									<a href="../admin/list.jhtml"  target="iframe"> <i class="fa fa-user"></i>${message("ov.main.admin")}</a>
								</li>
						 	 	[/@shiro.hasPermission]
				                [@shiro.hasPermission name="admin:role"]
								<li>
									<a href="../role/list.jhtml" target="iframe"><i class="fa fa-male"></i>${message("ov.main.role")}</a>
								</li>
							   [/@shiro.hasPermission]
							   [@shiro.hasPermission name="admin:account"]
								<li>
									<a href="../account/accountInfo.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.account.settingGroup")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:apply", "admin:tenantAccount","admin:tenantInfo","admin:tenantInfo4distributor"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("ov.main.tenant")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
							 	[@shiro.hasPermission name="admin:tenantAccount"]
								<li>
									<a href="../tenantAccount/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.main.tenantAccount")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:tenantInfo"]
								<li>
									<a href="../tenantInfo/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.main.tenantInfo")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:deviceType", "admin:deviceInfo" ,"admin:deviceProvide4distributor"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("ov.main.device")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
							   [@shiro.hasPermission name="admin:deviceType"]
								<li>
									<a href="../deviceType/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.main.deviceType")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:deviceInfo"]
								<li>
									<a href="../deviceInfo/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.main.deviceInfo")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:vehicle"] as permission]
			[@shiro.hasPermission name = permission]
				<li class="has_sub" >
					<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("ov.main.vehicle")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
						<ul class="sub_ul">
							 [@shiro.hasPermission name="admin:vehicle"]
								<li>
									<a href="../vehicle/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.main.vehicle")}</a>
								</li>
							 [/@shiro.hasPermission]
				       </ul>
				</li>
				[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:reportUserReg","admin:reportDeviceBind"] as permission]
			[@shiro.hasPermission name = permission]
				<li class="has_sub" >
					<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("ov.main.report")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
						<ul class="sub_ul">
							 [@shiro.hasPermission name="admin:reportUserReg"]
								<li>
									<a href="../reportUserReg/reportUserReg.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.report.reportUserReg")}</a>
								</li>
							 [/@shiro.hasPermission]
							 [@shiro.hasPermission name="admin:reportDeviceBind"]
								<li>
									<a href="../reportDeviceBind/reportDeviceBind.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("ov.report.reportDeviceBind")}</a>
								</li>
							 [/@shiro.hasPermission]
				       </ul>
				</li>
				[#break /]
			[/@shiro.hasPermission]
		[/#list]
       </ul>
      </div>
      <div class="mainbar">
		  <iframe  id="iframe" name="iframe" marginheight="0" marginwidth="0" frameborder="0" style="width:100%"scrolling="no" src="../account/accountInfo.jhtml" "></iframe>
	 </div>
</div>
<div class="modal fade" id="operationModal" tabindex="-1" role="dialog" aria-labelledby="operationModalLabel" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title"></h4>
	      </div>
	      <div class="modal-body" style="margin:0;padding:0">
	      	 <iframe  id="operationModalIframe" marginheight="0" marginwidth="0" frameborder="0" style="width:100%"scrolling="no"></iframe>
	      </div>
	      <div class="modal-footer">
	        <button id="operationModalCancle" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button id="operationModalOK" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript" src="${base}/resources/js/resize.js"></script>
<script type="text/javascript">
	function iframeRefresh(src){
		$('#iframe').attr('src',src);
	}
	$(function(){
		var $sub_li = $(".sub_ul li");
		$sub_li.click(function(){
			var $this = $(this);
			$sub_li.removeClass("active");
			$this.addClass("active");
		})
	})
</script>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.deviceInfo.details")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("ov.main.deviceInfo")}</a> 
				<span class="divider">/</span> 
				<a href="#" onclick="history.go(-1)"><i class="fa fa-list"></i>${message("ov.deviceInfo.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("ov.deviceInfo.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("ov.deviceInfo.details")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
                     		<tr>
								<th>
									${message("ov.deviceInfo.bindTime")}:
								</th>
								<td>
									[#if deviceInfo.bindTime??]
										<span title="${deviceInfo.bindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.bindTime}</span>
									[#else]
										-
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.unBindTime")}:
								</th>
								<td>
									[#if deviceInfo.unBindTime??]
											<span title="${deviceInfo.unBindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.unBindTime}</span>
									[#else]
											-
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.deviceNo")}:
								</th>
								<td>
									${deviceInfo.deviceNo}
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.simNo")}:
								</th>
								<td>
									${deviceInfo.simNo}
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.deviceStatus")}:
								</th>
								<td>
									[#if deviceInfo.deviceStatus??]
										${message("ov.deviceInfo.deviceStatus."+deviceInfo.deviceStatus)}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.bindStatus")}:
								</th>
								<td>
									[#if deviceInfo.bindStatus??]
									 ${message("ov.deviceInfo.bindStatus."+deviceInfo.bindStatus)}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.type")}:
								</th>
								<td>
									${deviceInfo.type.name}
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.deviceInfo.vehicle")}:
								</th>
								<td>
									[#if deviceInfo.vehicle ??]
										${deviceInfo.vehicle.plate}
									[/#if]
									
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.vehicle.tenantName")}:
								</th>
								<td>
										${tenantName}
								</td>
							</tr>
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="button" class="button" value="${message("ov.common.back")}" onclick="location.href='list.jhtml'" />
								</td>
							</tr>
						</table>
                  </div>
                </div>
              </div>  
            </div>
          </div>
        </div>
	   </div>
	</div>
	<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
</body>
</html>
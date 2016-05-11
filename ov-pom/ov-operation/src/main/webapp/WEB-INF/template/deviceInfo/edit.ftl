<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.deviceInfo.edit")}</title>
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
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			deviceNo: {
				required: true,
				remote: {
					url: "check_deviceNo.jhtml",
					cache: false,
					data:{
						id:${deviceInfo.id}
					}
				}
			},
			simNo: {
				required: true
			},
			typeId: {
				required: true
			}
		},
		messages: {
			deviceNo: {
				remote: "设备号已存在"
			}
		}
	});

});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("ov.main.deviceInfo")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("ov.deviceInfo.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("ov.deviceInfo.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("ov.deviceInfo.edit")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post">
						<input type="hidden" name="id" value="${deviceInfo.id}" />
						<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.deviceInfo.deviceNo")}:
								</th>
								<td>
									<input type="text" name="deviceNo" class="text" maxlength="20" value="${deviceInfo.deviceNo}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.deviceInfo.simNo")}:
								</th>
								<td>
									<input type="text" name="simNo" class="text" maxlength="20" value="${deviceInfo.simNo}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.deviceInfo.type")}:
								</th>
								<td>
									<select name="typeId">
										[#list types as type]
										<option value="${type.id}" [#if deviceInfo.type.id== type.id ] selected="selected" [/#if]>${type.name}</option>
										[/#list]
									</select>
								</td>
							</tr>
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="button" value="${message("ov.common.submit")}" />
									<input type="button" class="button" value="${message("ov.common.back")}" onclick="location.href='list.jhtml'" />
								</td>
							</tr>
						</table>
					</form>
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
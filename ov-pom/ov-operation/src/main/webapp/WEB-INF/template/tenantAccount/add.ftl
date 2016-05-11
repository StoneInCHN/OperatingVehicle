<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.tenantAccount.add")}</title>
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
			userName: {
				required: true,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "check_userName.jhtml",
					cache: false
				}
			},
			tenantID: {
				required: true
			},
			accoutStatus: {
				required: true
			}
		},
		messages: {
			userName: {
				remote: "${message("username.validate.exist")}"
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
				<a ><i class="fa fa-user"></i> ${message("ov.main.tenantAccount")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("ov.tenantAccount.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("ov.tenantAccount.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("ov.tenantAccount.add")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form">
                     	<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.tenantName")}:
								</th>
								<td>
									<select name="tenantID" class="text">
										<option value="">${message("ov.tenantAccount.tenantName.select")}</option>
										[#list tenantInfos as tenantInfo]
											<option value="${tenantInfo.id}">${tenantInfo.tenantName}</option>
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantAccount.userName")}:
								</th>
								<td>
									<input type="text" name="userName" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantAccount.accoutStatus")}:
								</th>
								<td>
									<select name="accoutStatus" class="text">
										<option value="">${message("ov.tenantAccount.accoutStatus.select")}</option>
										<option value="ACTIVED">${message("ov.tenantAccount.accoutStatus.ACTIVED")}</option>
										<option value="LOCKED">${message("ov.tenantAccount.accoutStatus.LOCKED")}</option>
										<option value="DELETE">${message("ov.tenantAccount.accoutStatus.DELETE")}</option>
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
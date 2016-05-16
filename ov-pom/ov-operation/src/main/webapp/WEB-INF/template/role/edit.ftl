<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("ov.role.edit")}</title>
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
<style type="text/css">
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
.authorities th a{
	color:#666;
	font-weight:bold;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("table").find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});
	var $selectRow = $("#inputForm .selectRow");		
	$selectRow.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find(":checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name:{
				required: true,
				remote: {
					url: "check_roleName.jhtml",
					cache: false,
					data:{
						id:${role.id}
					}
				}
			},
			authorities: "required",
			description:{
				maxlength:200
			}
		},
		messages: {
			name: {
				remote: "${message("role.name.validate.exist")}"
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
				<a><i class="fa fa-user"></i> ${message("ov.main.role")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("ov.role.list")}</a>
				<span class="divider">/</span> 
				<a class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("ov.role.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("ov.role.edit")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                  		<form id="inputForm" action="update.jhtml" method="post">
							<input type="hidden" name="id" value="${role.id}" />
							<table class="input">
								<tr>
									<th>
										<span class="requiredField">*</span>${message("ov.role.name")}:
									</th>
									<td>
										<input type="text" name="name" class="text" value="${role.name}" maxlength="200" />
									</td>
								</tr>
								<tr>
									<th>
										${message("ov.role.description")}:
									</th>
									<td>
										<textarea  name="description" class="text" maxlength="200">${role.description}</textarea>
									</td>
								</tr>
								<tr class="authorities">
									<th>
										&nbsp;&nbsp;<a href="javascript:;" class="selectAll" title="${message("ov.role.selectAll")}">${message("ov.role.selectAll")}</a>
									</th>
									<td>
									<span class="fieldSet">
										<label>
										
										</label>
									</span>
									</td>
								</tr>
								<tr class="authorities">
									<th>
										<a href="javascript:;" class="selectRow" title="${message("ov.main.systemNav")}">${message("ov.main.systemNav")}</a>
									</th>
									<td>
										<span class="fieldSet">
											<label>
												<input type="checkbox" name="authorities" value="admin:admin" [#if role.authorities?seq_contains("admin:admin")] checked="checked"[/#if]/><span>${message("ov.main.admin")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:role" [#if role.authorities?seq_contains("admin:role")] checked="checked"[/#if]/><span>${message("ov.main.role")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:account" [#if role.authorities?seq_contains("admin:account")] checked="checked"[/#if]/><span>${message("ov.account.settingGroup")}</span>
											</label>
										</span>
									</td>
								</tr>
								<tr class="authorities">
									<th>
										<a href="javascript:;" class="selectRow" title="${message("ov.main.tenant")}">${message("ov.main.tenant")}</a>
									</th>
									<td>
										<span class="fieldSet">
											<label>
												<input type="checkbox" name="authorities" value="admin:tenantAccount" [#if role.authorities?seq_contains("admin:tenantAccount")] checked="checked"[/#if]/><span>${message("ov.main.tenantAccount")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:tenantInfo" [#if role.authorities?seq_contains("admin:tenantInfo")] checked="checked"[/#if]/><span>${message("ov.main.tenantInfo")}</span>
											</label>
										</span>
									</td>
								</tr>
								<tr class="authorities">
									<th>
										<a href="javascript:;" class="selectRow" title="${message("ov.main.tenant")}">${message("ov.main.device")}</a>
									</th>
									<td>
										<span class="fieldSet">
											<label>
												<input type="checkbox" name="authorities" value="admin:deviceType" [#if role.authorities?seq_contains("admin:deviceType")] checked="checked"[/#if]/><span>${message("ov.main.deviceType")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:deviceInfo" [#if role.authorities?seq_contains("admin:deviceInfo")] checked="checked"[/#if]/><span>${message("ov.main.deviceInfo")}</span>
											</label>
										</span>
									</td>
								</tr>
								<tr class="authorities">
									<th>
										<a href="javascript:;" class="selectRow" title="${message("ov.main.vehicle")}">${message("ov.main.vehicle")}</a>
									</th>
									<td>
										<span class="fieldSet">
											<label>
												<input type="checkbox" name="authorities" value="admin:vehicle" [#if role.authorities?seq_contains("admin:vehicle")] checked="checked"[/#if]/><span>${message("ov.main.vehicle")}</span>
											</label>
										</span>
									</td>
								</tr>
								<tr class="authorities">
									<th>
										<a href="javascript:;" class="selectRow" title="${message("ov.main.report")}">${message("ov.main.report")}</a>
									</th>
									<td>
										<span class="fieldSet">
											<label>
												<input type="checkbox" name="authorities" value="admin:reportUserReg" [#if role.authorities?seq_contains("admin:reportUserReg")] checked="checked"[/#if]/><span>${message("ov.report.reportUserReg")}</span>
											</label>
											<label>
												<input type="checkbox" name="authorities" value="admin:reportDeviceBind" [#if role.authorities?seq_contains("admin:reportDeviceBind")] checked="checked"[/#if]/><span>${message("ov.report.reportDeviceBind")}</span>
											</label>
										</span>
									</td>
								</tr>
								[#if role.isSystem]
									<tr>
										<th>
											&nbsp;
										</th>
										<td>
											<span class="tips">${message("ov.role.editSystemNotAllowed")}</span>
										</td>
									</tr>
								[/#if]
								<tr>
									<th>
										&nbsp;
									</th>
									<td>
										<input type="submit" class="button" value="${message("ov.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
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

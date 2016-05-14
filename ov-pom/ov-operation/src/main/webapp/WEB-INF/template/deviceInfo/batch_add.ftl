<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.deviceInfo.batchAdd")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $batchFile = $("#batchFile");
	var $file = $("#file");
	var $submit = $(":submit");
	var $statusTr = $("#statusTr");
	

	var file;	
	var first;
	var buildCount;
	var buildTime;
	
	// 表单验证
	$inputForm.validate({
		rules: {
			file: {
				required: true
			}
		},
		submitHandler: function(form) {
				var excelFile = $("#file").val();
			   if(excelFile=='') {
			   		$.message("warn", "${message("deviceInfo.batch.excle.select")}");
			   		return false;
			   	}
		       if(excelFile.indexOf('.xls')==-1 && excelFile.indexOf('.xlsx')==-1){
		       		$.message("warn", "${message("deviceInfo.batch.excle.nullity")}");
		       		return false;
		       	}
			$submit.prop("disabled", true);
			$statusTr.show();
			form.submit();
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
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("ov.deviceInfo.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("ov.deviceInfo.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("ov.deviceInfo.add")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="batchAddSave.jhtml" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
                     	<table class="input tabContent">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="button" class="button" value="${message("ov.common.downloadTemplate")}" onclick="location.href='${base}/template/template.xlsx'" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.deviceInfo.batch.choosefile")}:
								</th>
								<td>
									<input type="file" id="file" name="file"/>
								</td>
							</tr>
							<tr id="statusTr" class="hidden">
								<th>
									&nbsp;
								</th>
								<td>
									<span class="loadingBar">&nbsp;</span>
									<div id="status">${message("ov.deviceInfo.batch.upload.processing")}</div>
								</td>
							</tr>
							[#if resMessage.content??]
							<tr>
								<th>${message("ov.deviceInfo.upload.result")}</th>
								
								<td>
									<table>
										<tr>
											<td>${message("ov.deviceInfo.upload.success")}</td>
											<td>${successCount}</td>
										</tr>
										<tr>
											<td>${message("ov.deviceInfo.upload.fail")}</td>
											<td>${faileCount}</td>
										</tr>
										[#if faileCount > 0]
											[#if dupfailDeviceIds?? && dupfailDeviceIds?size>0]
											<tr>
												<td>${message("ov.deviceInfo.upload.failimei")}</td>
												<td>${dupfailDeviceIds}</td>
											</tr>
											[/#if]
											[#if formatFailDeviceIds?? && formatFailDeviceIds?size>0]
												<tr>
												<td>${message("ov.deviceInfo.upload.failformat")}</td>
												<td>${formatFailDeviceIds}</td>
											</tr>
											[/#if]
										[/#if]
									</table>
								</td>
							</tr>
							[/#if]
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

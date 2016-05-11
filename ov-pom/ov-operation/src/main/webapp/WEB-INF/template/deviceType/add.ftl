<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.deviceType.add")}</title>
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
			
			name: {
				required: true
			},
			status: {
				required: true
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
				<a ><i class="fa fa-user"></i> ${message("ov.main.deviceType")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("ov.deviceType.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("ov.deviceType.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("ov.deviceType.add")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form">
                     	<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.deviceType.name")}:
								</th>
								<td>
									<input type="text" name="name" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.deviceType.status")}:
								</th>
								<td>
									<select name="status">
										<option value="">${message("ov.deviceType.status.select")}</option>
										<option value="ENABLE">${message("ov.deviceType.status.ENABLE")}</option>
										<option value="DISABLE">${message("ov.deviceType.status.DISABLE")}</option>
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
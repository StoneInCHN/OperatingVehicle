<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.tenantInfo.edit")}</title>
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
<script type="text/javascript" src="${base}/resources/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap-modal.js"></script>
<style  type="text/css" >
	#allmap {width: 550px;height: 400px;overflow: hidden;margin:0}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	//var $areaId = $("#areaId");
	// 地区选择
	//$areaId.lSelect({
		//url: "${base}/console/common/area.jhtml"
	//});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			tenantName: {
				required: true
			},
			contactPerson: {
				required: true
			},
			email:{
				required:true,
				email:true
			},
			accountStatus: {
				required: true
			},
			contactPhone:{
				required :true,
				isMobile:true
			},
			versionConfigId:{
				required :true
			},
			address:{
				required:true
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
				<a ><i class="fa fa-user"></i> ${message("ov.main.tenantInfo")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("ov.tenantInfo.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("ov.tenantInfo.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("ov.tenantInfo.edit")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post">
						<input type="hidden" name="id" value="${tenantInfo.id}" />
						<table class="input tabContent">
							<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.tenantName")}:
								</th>
								<td>
									<input type="text" id="tenantName" name="tenantName" class="text" maxlength="20" value="${tenantInfo.tenantName}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.contactPerson")}:
								</th>
								<td>
									<input type="text" name="contactPerson" class="text" maxlength="20" value="${tenantInfo.contactPerson}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.contactPhone")}:
								</th>
								<td>
									<input type="text" name="contactPhone" class="text" maxlength="20" value="${tenantInfo.contactPhone}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.address")}:
								</th>
								<td>
									<input type="text" name="address" class="text" maxlength="100" value="${tenantInfo.address}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.email")}:
								</th>
								<td>
									<input type="text" name="email" class="text" maxlength="60" value="${tenantInfo.email}"/>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;${message("ov.tenantInfo.description")}:
								</th>
								<td>
									<input type="text" name="description" class="text" maxlength="20" value="${tenantInfo.description}"/>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;${message("ov.tenantInfo.ownerName")}:
								</th>
								<td>
									<input type="text" name="ownerName" class="text" maxlength="20" value="${tenantInfo.ownerName}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.accoutStatus.select")}:
								</th>
								<td>
									<select name="accountStatus" class="text">
										<option value="ACTIVED"[#if tenantInfo.accountStatus == "ACTIVED"] selected="selected" [/#if] >${message("ov.tenantInfo.accoutStatus.ACTIVED")}</option>
										<option value="LOCKED" [#if tenantInfo.accountStatus == "LOCKED"] selected="selected" [/#if]>${message("ov.tenantInfo.accoutStatus.LOCKED")}</option>
										<option value="DELETE" [#if tenantInfo.accountStatus == "DELETE"] selected="selected" [/#if]>${message("ov.tenantInfo.accoutStatus.DELETE")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;${message("ov.tenantInfo.remark")}:
								</th>
								<td>
									<input type="text" name="remark" class="text" maxlength="200" value="${tenantInfo.remark}"/>
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
		<!-- Modal start -->
	<div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-labelledby="mapModalLabel" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="mapModalLabel">
	        	${message("ov.tenantInfo.area")}
	        	<input type="text" readonly class="text" id="modalTitleInput">
	        </h4>
	      </div>
	      <div class="modal-body">
	       	<div id="allmap"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- Modal end -->
	<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
	<script type="text/javascript">
		//百度地图API功能
		function loadJScript() {
			var script = document.createElement("script");
			script.type = "text/javascript";
			script.src = "http://api.map.baidu.com/api?v=2.0&ak=D767b598a9f1e43c3cadc4fe26cdb610&callback=init";
			document.body.appendChild(script);
		}
		function init() {
			// 百度地图API功能
			var $longitude = $("#longitude");
			var $latitude = $("#latitude");
			console.log($longitude.val());
			console.log($latitude.val());
			var address = $("select[name='areaId_select']  option:selected").text();
			var map = new BMap.Map("allmap");
			var point = new BMap.Point($longitude.val(),$latitude.val());
			map.centerAndZoom(point,12);
            map.addControl(new BMap.NavigationControl());
            map.enableScrollWheelZoom(true);
            map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
			map.addControl(new BMap.OverviewMapControl({isOpen:true}));   //右上角，打开
			map.addEventListener("click", showInfo);
			var marker = new BMap.Marker(new BMap.Point($longitude.val(), $latitude.val()));
			map.addOverlay(marker);    
			 function showInfo(e){
				 if(marker){
					map.removeOverlay(marker);
				 }
				$("#longitude").val(e.point.lng);
				$("#latitude").val(e.point.lat);
				marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				marker.enableDragging(); 
				marker.addEventListener("dragend", function showInfo(){
					 var p = marker.getPosition();       //获取marker的位置
					 $("#longitude").val(p.lng);
					 $("#latitude").val(p.lat); 
				});
			};
			$("#modalTitleInput").attr("placeholder",address);
		}  
		
		var $selectPositionPoint = $("#selectPositionPoint");
		$selectPositionPoint.click(function(){
			
			var val = $("#areaId").val();
			if(!val){
				alert("请先选择地区");
			}else{
				$('#mapModal').modal("show");
			}
			
			
		});
		$('#mapModal').on('show.bs.modal', function (e) {
			loadJScript();
		})
</script>
</body>
</html>
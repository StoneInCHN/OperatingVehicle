<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.tenantInfo.add")}</title>
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
	var $areaId = $("#areaId");
		
	// 地区选择
	$areaId.lSelect({
		url: "${base}/console/common/area.jhtml"
	});

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
				//isMobile:true
			},
			versionConfigId:{
				required :true
			},
			areaId:{
				required:true
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
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("ov.tenantInfo.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("ov.tenantInfo.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("ov.tenantInfo.add")}</div>
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
									<input type="text" id="tenantName" name="tenantName" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.contactPerson")}:
								</th>
								<td>
									<input type="text" name="contactPerson" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.contactPhone")}:
								</th>
								<td>
									<input type="text" name="contactPhone" class="text" maxlength="20" />
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.address")}:
								</th>
								<td>
									<input type="text" name="address" class="text" maxlength="20" />
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.email")}:
								</th>
								<td>
									<input type="text" name="email" class="text" maxlength="20" />
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.description")}:
								</th>
								<td>
									<input type="text" name="description" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.ownerName")}:
								</th>
								<td>
									<input type="text" name="ownerName" class="text" maxlength="20" />
								</td>
							</tr>
							<tr>
								<th>
									${message("ov.apply.versionConfig")}:
								</th>
								<td>
									<select name="versionConfigId">
										<option value="">${message("ov.apply.versionConfig.select")}</option>
										[#list versions as version]
										<option value="${version.id}">${version.versionName}</option>
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.accoutStatus.select")}:
								</th>
								<td>
									<select name="accountStatus" class="text">
										<option value="">${message("ov.tenantInfo.accoutStatus.select")}</option>
										<option value="ACTIVED">${message("ov.tenantInfo.accoutStatus.ACTIVED")}</option>
										<option value="LOCKED">${message("ov.tenantInfo.accoutStatus.LOCKED")}</option>
										<option value="DELETE">${message("ov.tenantInfo.accoutStatus.DELETE")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("ov.tenantInfo.remark")}:
								</th>
								<td>
									<input type="text" name="remark" class="text" maxlength="200" />
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
	<!-- Modal -->
	<div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-labelledby="mapModalLabel" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="mapModalLabel">
	        	${message("ov.apply.pisotion.point")}
	        	<input type="text" class="text" id="modalTitleInput">
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
			var address = $("select[name='areaId_select']  option:selected").text();
			var map = new BMap.Map("allmap");
			var point = new BMap.Point(116.331398,39.897445);
			map.centerAndZoom(point,12);
            map.addControl(new BMap.NavigationControl());
            map.enableScrollWheelZoom(true);
            map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
			map.addControl(new BMap.OverviewMapControl({isOpen:true}));   //右上角，打开
			map.addEventListener("click", showInfo);
			var marker ;
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
			
			var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "modalTitleInput"
				,"location" : map
			});
			
			ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
			var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
				
				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
				$("#searchResultPanel").html(str);
			});
			
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
				var	myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
				setPlace(myValue);
			});
		
			function setPlace(myValue){
				map.clearOverlays();    //清除地图上所有覆盖物
				function myFun(){
					var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
					map.centerAndZoom(pp, 14);
					marker = new BMap.Marker(new BMap.Point(pp.lng, pp.lat));  // 创建标注
					map.addOverlay(marker);              // 将标注添加到地图中
					$("#longitude").val(pp.lng);
					$("#latitude").val(pp.lat);
					marker.enableDragging(); 
					marker.addEventListener("dragend", function changePosition(){
						 var p = marker.getPosition();       //获取marker的位置
						 $("#longitude").val(p.lng);
						 $("#latitude").val(p.lat); 
					});
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
				  onSearchComplete: myFun
				});
				local.search(myValue);
			};
			 
			setPlace(address);
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
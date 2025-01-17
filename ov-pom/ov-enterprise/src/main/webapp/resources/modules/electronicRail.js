
//车辆查询
$(function(){
	var vehicleId;
	var radius = "";
	var centerLng = "";
	var centerLat = "";
	var timer = null;
	var map = null;
	var positionArray = null;
	var deviceId = "";
	//百度地图电子围栏
	var map_ip_location = "http://api.map.baidu.com/location/ip?coor=bd09ll&ak=" + message("ov.baiduMap.ak");
	var lastOverlay = null;
	var marker_back = null;
	var polyline_back = null;
	var circle = null;
	
	$("#electronicRail_add_btn").hide();
	$("#electronicRail_edit_btn").hide();
	$("#electronicRail_ok_btn").hide();
	$("#electronicRail_remove_btn").hide();
	
	//测试绘制圆
	var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 1,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.1,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
	
	
	$("#electronicRailVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			//初始化
			if (timer != null){
				clearInterval(timer);	
			}
			if (map != null){
				map.clearOverlays();
			}
			circle=null;
			positionArray = new Array();
		    if (lastOverlay != null){
		    	map.removeOverlay(lastOverlay);
		    }
		    
			$('#electronicRail_edit_btn').unbind("click");
			$('#electronicRail_add_btn').unbind("click");
			$('#electronicRail_ok_btn').unbind("click");
			$('#electronicRail_remove_btn').unbind("click");
			
			vehicleId = rowData.id;
			deviceId = rowData.deviceNo;
			
			//查询该车辆的电子围栏
			$.ajax({
				url:"../electronicRail/findElectronicRailByVehicle.jhtml",
				type:"get",
				data: jQuery.parseJSON('{"vehicleId":"' + vehicleId + '"}'),
				beforeSend:function(){
					$.messager.progress({
						text:message("ov.common.loading")
					});
				},
				success:function(result,response,status){
					$.messager.progress('close');
					if(response == "success"){
						if (result == null || result == ""){//车辆暂时没有设置电子围栏
							$("#electronicRail_edit_btn").hide();
							$("#electronicRail_add_btn").show();
							$("#electronicRail_ok_btn").show();
							$.ajax({
								url:"../electronicRail/realTimeVehicleStatus.jhtml",
								type:"get",
								data: jQuery.parseJSON('{"deviceId":"' + deviceId + '"}'),
								success:function(result,response,status){
									if(response == "success"){
										content = jQuery.parseJSON(result.content);
										var lon = content.msg.lon;
										var lat = content.msg.lat;
										drawMap(lon,lat);//以车辆当前位置为地图中心
									}else{
										alertErrorMsg();
									}
								}
							});
						}else {//车辆已设置了电子围栏
							$("#electronicRail_add_btn").hide();
							$("#electronicRail_edit_btn").show();
							$("#electronicRail_ok_btn").show();
							$("#electronicRail_remove_btn").show();
							radius = result.radius;
							centerLng = result.centerLng;
							centerLat = result.centerLat;
							var circleCenter = new BMap.Point(centerLng, centerLat);
							circle = new BMap.Circle(circleCenter, radius, styleOptions);
							drawMap(centerLng,centerLat);//以电子围栏中心点为地图中心
						}
					}else{
						alertErrorMsg();
					}
				}
			});
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : "车牌号",field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "绑定设备",field : "deviceNo",width :"47%",align : 'center',sortable : true},					
		]]
		
	});
	
	$("#electronicRail_vehicle_search_btn").click(function(){
		var _queryParams = $("#electronicRail_vehicle_search_form").serializeJSON();
		$('#electronicRailVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		$("#electronicRailVehicleSearch-table-list").datagrid('reload');			
	});
	

	function drawMap(x, y){
		map = new BMap.Map("mapContainer");          // 创建地图实例  
		var point = new BMap.Point(x, y);  // 创建点坐标  
		map.centerAndZoom(point, 11);	
		map.enableScrollWheelZoom();	//启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
		
		
		var myDrawingManagerObject = new BMapLib.DrawingManager(map, {
			isOpen: false, //是否开启绘制模式
	        enableDrawingTool: false, //是否显示工具栏
	        drawingToolOptions: {
	            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
	            offset: new BMap.Size(5, 5), //偏离值
	        },
	        circleOptions: styleOptions, //圆的样式
	        polylineOptions: styleOptions, //线的样式
	        polygonOptions: styleOptions, //多边形的样式
	        rectangleOptions: styleOptions //矩形的样式
	    });
		myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_CIRCLE);
		myDrawingManagerObject.enableCalculate();
		myDrawingManagerObject.addEventListener("circlecomplete", function(e, overlay) {
			radius = overlay.getRadius();
			centerLng = overlay.getCenter().lng;
			centerLat = overlay.getCenter().lat;
			
		    if (lastOverlay != null){
		    	map.removeOverlay(lastOverlay);
		    }
		    lastOverlay = overlay;
		});
		
		if (circle != null &&radius != "" && centerLng != "" && centerLat != ""){
			drawCircle();//画电子围栏
		}
		drawCar(radius,centerLng,centerLat);//画车辆
		$("#electronicRail_add_btn").click(function(){
			myDrawingManagerObject.open();
		});
		
		$("#electronicRail_edit_btn").click(function(){
			if (lastOverlay != null){
		    	map.removeOverlay(lastOverlay);
		    }
			myDrawingManagerObject.open();
		});
		
		$("#electronicRail_ok_btn").click(function(){
			myDrawingManagerObject.close();
			if (radius != "" && centerLng != "" && centerLat != ""){
				var saveData = '{"radius":"' + radius + '", "centerLng":"' + centerLng + '", "centerLat":"' + centerLat + '", "vehicleId":"' + vehicleId + '"}';
				$.ajax({
					url:"../electronicRail/save.jhtml",
					type:"post",
					data: jQuery.parseJSON(saveData),
					beforeSend:function(){
						$.messager.progress({
							text:message("ov.common.saving")
						});
					},
					success:function(result,response,status){
						$.messager.progress('close');
						if(response == "success"){
							showSuccessMsg(result.content);
							var circleCenter = new BMap.Point(centerLng, centerLat);
							circle = new BMap.Circle(circleCenter, radius, styleOptions);
							$("#electronicRail_remove_btn").show();
							$("#electronicRail_edit_btn").show();
							$("#electronicRail_add_btn").hide();
						}else{
							alertErrorMsg();
						}
					}
				});
			}
		});
		$("#electronicRail_remove_btn").click(function(){
			$.messager.confirm(
					message("ov.common.confirm"),
					'移除当前电子围栏？', function(r) {
						if (r) {
							$.ajax({
								url:"../electronicRail/delete.jhtml",
								type:"post",
								data: {"vehicleId":vehicleId},
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.progress")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										//去掉地图上已经画的电子围栏
										if (lastOverlay != null){
									    	map.removeOverlay(lastOverlay);
									    }
										circle=null;
										//显示添加按钮，隐藏编辑按钮
										$("#electronicRail_add_btn").show();
										$("#electronicRail_edit_btn").hide();
										$("#electronicRail_remove_btn").hide();
										
									}else{
										alertErrorMsg();
									}
								}
							});
						}
			});
		});
		
		function drawCircle(){
			var circleCenter = new BMap.Point(centerLng, centerLat);
			circle = new BMap.Circle(circleCenter, radius, styleOptions);
			map.addOverlay(circle);
			lastOverlay = circle;
			//清除当前数据，避免对其他页面造成影响
			radius = "";
			centerLng = "";
			centerLat = "";
		}
		
		function drawCar(radius,centerLng,centerLat){
			
			timer = setInterval(function(){
				$.ajax({
					url:"../electronicRail/realTimeVehicleStatus.jhtml",
					type:"get",
					data: jQuery.parseJSON('{"deviceId":"' + deviceId + '"}'),
					success:function(result,response,status){
						if(response == "success"){
						
							content = jQuery.parseJSON(result.content);
							var acc = content.msg.acc;
							var lon = content.msg.lon;
							var lat = content.msg.lat;
							var speed = content.msg.speed;
							var azimuth = content.msg.azimuth;
							
							if (acc == "-1" || lon == -1 || lat == -1){
								return;
							}
								
							var url =  "../../resources/images/car.png";
							var size = new BMap.Size(50, 50);
							var icon = new BMap.Icon(url, size);
							var label = new BMap.Label("当前车速:" + speed + "km/h");
							var GPSPoint = new BMap.Point(lon, lat);
							new BMap.Convertor.translate(GPSPoint, 0, function(position){
								if(circle != null){
									if (!BMapLib.GeoUtils.isPointInCircle(position, circle)){
										$.messager.show({
											title : "警告",
											msg : "车辆已超出电子围栏范围!",
											timeout : 2000,
											showType : 'slide'
										});
									}
								}
								
								label.setPosition(position);
								label.setStyle({
									 color : "red",
									 fontSize : "12px",
									 width: "95px",
									 maxWidth: "100px",
									 height : "20px",
									 //lineHeight : "20px",
									 fontFamily:"微软雅黑"
								 });
								label.setOffset(new BMap.Size(-33, -25));
								positionArray.push(position);
								//折线
								var polyline = new BMap.Polyline(positionArray, {
									strokeColor:"blue",
									strokeWeight:"3",
									strokeOpacity:"0.7"
								});
								//车辆
								var marker = new BMap.Marker(position);
								marker.setRotation(azimuth);
								marker.setIcon(icon);
								marker.setTop(true);
								marker.show();
								
								map.removeOverlay(marker_back);
								map.addOverlay(marker);
								
								map.removeOverlay(polyline_back);
								map.addOverlay(polyline);
								
								marker.setLabel(label);
								//备份覆盖物，为了下次remove此覆盖物
								marker_back = marker;
								polyline_back = polyline;
									
							});
							
						}else{
							alertErrorMsg();
						}
					}
				});
			}, 5000);
			
			
		}
		
		//输入控件
		function InputControl(){
		  // 默认停靠位置和偏移量
		  this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		  this.defaultOffset = new BMap.Size(80, 10);
		}

		InputControl.prototype = new BMap.Control();
		InputControl.prototype.initialize = function(map){
		  var div = document.createElement("div");
		  var input = document.createElement("input");
		  input.id = "suggestId";
		  input.type = "text";
		  div.appendChild(input);
		  div.style.cursor = "pointer";
		  div.style.border = "1px solid gray";
		  div.style.backgroundColor = "white";
		  map.getContainer().appendChild(div);
		  return div;
		}
		var myInputCtrl = new InputControl();
		map.addControl(myInputCtrl);
		
		//搜索显示控件
		function DivControl(){
		  this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		  this.defaultOffset = new BMap.Size(200, 15);
		}

		DivControl.prototype = new BMap.Control();
		DivControl.prototype.initialize = function(map){
		  var div = document.createElement("div");
		  div.id = "searchResultPanel";
		  div.style.border = "1px solid #C0C0C0";
		  div.style.width = "200px";
		  div.style.height = "25px";
		  div.style.display = "none";
		  div.style.position = "relative";
		  div.style.zIndex = "9009";
		  map.getContainer().appendChild(div);
		  return div;
		}
		var myDivCtrl = new DivControl();
		map.addControl(myDivCtrl);

		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "suggestId"
				,"location" : map
			});
		
		function G(id) {
			return document.getElementById(id);
		}
		
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
			G("searchResultPanel").innerHTML = str;
		});

		var myValue;
		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
			
			setPlace();
		});

		function setPlace(){
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
				map.centerAndZoom(pp, 18);
				map.addOverlay(new BMap.Marker(pp));    //添加标注
			}
			var local = new BMap.LocalSearch(map, { //智能搜索
			  onSearchComplete: myFun
			});
			local.search(myValue);
		}
	}
	
});



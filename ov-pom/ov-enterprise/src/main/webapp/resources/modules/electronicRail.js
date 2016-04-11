
//车辆查询
$(function(){
	var vehicleId;
	
	$("#electronicRailVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			vehicleId = rowData.id;
			createMap();
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : "车牌号",field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "品牌图标",field : "brandIcon",width :"47%",align : 'center',sortable : true},					
		]]
		
	});
	
	$("#electronicRail_vehicle_search_btn").click(function(){
		var _queryParams = $("#electronicRail_vehicle_search_form").serializeJSON();
		$('#electronicRailVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		$("#electronicRailVehicleSearch-table-list").datagrid('reload');			
	});
	
	//百度地图电子围栏
	var map_ip_location = "http://api.map.baidu.com/location/ip?coor=bd09ll&ak=" + message("ov.baiduMap.ak");
	var lastOverlay = null;
	var radius;
	var centerLng;
	var centerLat;
	
	function createMap(){
		
		$.ajax({
			url:map_ip_location,
			type:"get",
			dataType: 'JSONP',
			data: "",
			success:function(result){
				drawMap(result.content.point.x, result.content.point.y);
			}
		});

	}

	function drawMap(x, y){
		var map = new BMap.Map("mapContainer");          // 创建地图实例  
		var point = new BMap.Point(x, y);  // 创建点坐标  
		map.centerAndZoom(point, 11);	
		map.enableScrollWheelZoom();	//启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		
		//测试绘制圆
		var styleOptions = {
	        strokeColor:"red",    //边线颜色。
	        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
	        strokeWeight: 1,       //边线的宽度，以像素为单位。
	        strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
	        fillOpacity: 0.3,      //填充的透明度，取值范围0 - 1。
	        strokeStyle: 'solid' //边线的样式，solid或dashed。
	    }
		
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
			var temp = "半径：" + overlay.getRadius() + "; 圆心经度：" + overlay.getCenter().lng + "; 纬度：" + overlay.getCenter().lat;
			console.log(temp);
		    if (lastOverlay != null){
		    	map.removeOverlay(lastOverlay);
		    }
		    lastOverlay = overlay;
		});
		
		$("#electronicRail_add_btn").click(function(){
			myDrawingManagerObject.open();
		});
		
		$("#electronicRail_ok_btn").click(function(){
			myDrawingManagerObject.close();
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
					}else{
						alertErrorMsg();
					}
				}
			});
		});
		
		//输入控件
		function InputControl(){
		  // 默认停靠位置和偏移量
		  this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		  this.defaultOffset = new BMap.Size(80, 15);
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
		  div.style.width = "150px";
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
			//map.clearOverlays();    //清除地图上所有覆盖物
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


//添加电子围栏


var useCarRequest_manager_tool = {
		add:function(){
			$('#addUseCarRequest').dialog({
			    title: message("ov.useCarRequest.add"),    
			    width: 600,    
			    height: 400,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    modal:true,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addUseCarRequest_form').form('validate');
						if(validate){
								$.ajax({
									url:"../vehicleScheduling/addRequest.jhtml",
									type:"post",
									data:$("#addUseCarRequest_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addUseCarRequest').dialog("close")
											$("#addUseCarRequest_form").form("reset");
											$("#useCarRequest-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
						};
					}
				},{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addUseCarRequest').dialog("close");
						 $("#addUseCarRequest_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addUseCarRequest_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#useCarRequest-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editUseCarRequest').dialog({    
				title: message("ov.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicleScheduling/editRequest.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editUseCarRequest_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleScheduling/updateRequest.jhtml",
								type:"post",
								data:$("#editUseCarRequest_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editUseCarRequest').dialog("close");
										$("#useCarRequest-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editUseCarRequest').dialog("close").form("reset");
					}
			    }]
			});  
		}
		
};

$(function(){
	var map_ip_location = "http://api.map.baidu.com/location/ip?ak=ulsOtfMZcNc4D6aQnBwwnOTt6ZKohflO&coor=bd09ll";
	var startPoint = "";
	var endPoint = "";
	var totalDistance = "0";

	$("#useCarRequest-table-list").datagrid({
		title:message("ov.useCarRequest.list"),
		fitColumns:true,
		toolbar:"#useCarRequest_manager_tool",
		url:'../vehicleScheduling/listRequest.jhtml?childrenOrParent=children',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#useCarRequestDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../vehicleScheduling/detailsRequest.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#useCarRequestDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.useCarRequest.title"),field:"title",width:80,sortable:true},
		      {title:message("ov.useCarRequest.startDate"),field:"startDate",width:50,sortable:true, 
		    	  formatter:function(value,row,index){
		    		  return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
		    	  }
		      },
		      {title:message("ov.useCarRequest.startPositionDetails"),field:"startPositionDetails",width:100},
		      {title:message("ov.useCarRequest.endPositionDetails"),field:"endPositionDetails",width:100},
		      {title:message("ov.useCarRequest.status"),field:"status",width:30,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "TO_CONFIRM"){
			    		  return  message("ov.useCarRequest.to_confirm");
			    	  }else if (value == "DISTRIBUTED"){
			    		  return  message("ov.useCarRequest.distributed");
			    	  }else if (value == "FINISHED"){
			    		  return  message("ov.useCarRequest.finished");
			    	  }else if (value == "CANCELLED"){
			    		  return  message("ov.useCarRequest.cancelled");
			    	  }else if (value == "REJECTED"){
			    		  return  message("ov.useCarRequest.rejected");
			    	  }else if (value == "BREAK_CONTRACT"){
			    		  return  message("ov.useCarRequest.break_contract");
			    	  }else if (value == "CLEARED"){
			    		  return  message("ov.useCarRequest.cleared");
			    	  }
		      	  }  
		      },
		   ]
		]
	});
	
	$("#useCarRequest-search-btn").click(function(){
	  var _queryParams = $("#useCarRequest-search-form").serializeJSON();
	  $('#useCarRequest-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#useCarRequest-table-list").datagrid('reload');
	});
	
	$("input:text").focus(function(){
		console.log('selected');
		this.select();
	});
	
	$("#startPositionButton").click(function(){
		
		$.ajax({
			url:map_ip_location,
			type:"get",
			dataType: 'JSONP',
			data: "",
			success:function(result){

				createMap(result.content.point.x, result.content.point.y, message("ov.useCarRequest.select_start_position"),
						"#startLongitude", "#startLatitude", "#startPositionDetails", "start");
				
			}
		});
			
	})
	
	$("#endPositionButton").click(function(){
		$.ajax({
			url:map_ip_location,
			type:"get",
			dataType: 'JSONP',
			data: "",
			success:function(result){
				
				createMap(result.content.point.x, result.content.point.y, message("ov.useCarRequest.select_end_position"),
						"#endLongitude", "#endLatitude", "#endPositionDetails", "end");
				
			}
		});
	})
	
	function createMap(x, y, title, lng, lat, details, start_end){
		$('#mapContainer').dialog({    
			title: title,     
		    width: 800,    
		    height: 500,    
		    modal: true,
		    iconCls:'icon-mini-add',
		    onOpen:function(){}
		});
		
		var map = new BMap.Map("mapContainer");          // 创建地图实例  
		var point = new BMap.Point(x, y);  // 创建点坐标  
		map.centerAndZoom(point, 11);	
		map.enableScrollWheelZoom();	//启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
		map.addControl(new BMap.OverviewMapControl({ 
			isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
//		map.addEventListener("click", showInfo);
		var geoc = new BMap.Geocoder();  //地理编码
		
		//获取距离
		var searchComplete = function (results){
			if (transit.getStatus() != BMAP_STATUS_SUCCESS){
				return ;
			}
			var plan = results.getPlan(0);
			totalDistance = plan.getDistance(true);
			//eg. totalDistance: 22.3公里
			$("#totalDistance").val(totalDistance.split("公里")[0]);
		}
		var transit = new BMap.DrivingRoute(map, 
			{
				renderOptions: {map: map}, onSearchComplete: searchComplete
			}
		);

		//获取点击地图信息
		function showInfo(e){
			var pt = e.point;
			if (start_end == "start"){
				startPoint = pt;
			}
			if (start_end == "end"){
				endPoint = pt;
			}
			$(lng).val(pt.lng);
			$(lat).val(pt.lat);
			geoc.getLocation(pt, function(rs){
				var addComp = rs.addressComponents;
				var addressDetails = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
				$(details).textbox('setValue', addressDetails);
				if (startPoint != "" && endPoint != ""){
					transit.search(startPoint, endPoint);
				}
				$('#mapContainer').dialog("close");
			});
		}
		
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
			map.clearOverlays();    //清除地图上所有覆盖物
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
			isOpen: true, //是否开启绘制模式
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
		myDrawingManagerObject.addEventListener("overlaycomplete", function(e) {
		    alert(e.calculate);
		});
		
	}
	
})

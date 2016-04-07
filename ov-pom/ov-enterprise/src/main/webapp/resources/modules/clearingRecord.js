var clearingRecord_manager_tool = {
	add:function(){
		
		$.ajax({
			url:"../tenantClearingRecord/listBranchBusiness.jhtml",
			type:"post",
			data:"",
			success:function(result,response,status){
				if(response == "success"){
					console.log(result);
					$("#branchBusinessId").combobox("loadData", result);
				}else{
					alertErrorMsg();
				}
			}
		});
		
		$('#addClearingRecord').dialog({
		    title: message("ov.clearingRecord.add"),    
		    width: 600,    
		    height: 400,
		    iconCls:'icon-mini-add',
		    cache: false, 
		    modal:true,
		    buttons:[{
		    	text:message("ov.common.save"),
		    	iconCls:'icon-save',
				handler:function(){
					var validate = $('#addClearingRecord_form').form('validate');
					if(validate){
							$.ajax({
								url:"../tenantClearingRecord/addClearingRecord.jhtml",
								type:"post",
								data:$("#addClearingRecord_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#addClearingRecord').dialog("close")
										$("#addClearingRecord_form").form("reset");
										$("#clearingRecord-table-list").datagrid('reload');
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
					 $('#addClearingRecord').dialog("close");
					 $("#addClearingRecord_form").form("reset");
				}
		    }],
		    onOpen:function(){
		    	$('#addClearingRecord_form').show();
		    },
		});  
	}
};

$(function(){

	$("#clearingRecord-table-list").datagrid({
		title:message("ov.clearingRecord.list"),
		fitColumns:true,
		toolbar:"#clearingRecord_manager_tool",
		url:'../tenantClearingRecord/listClearingRecord.jhtml?childrenOrParent=parent',  
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
		      {title:message("ov.clearingRecord.clearingSn"),field:"clearingSn",width:100,sortable:true},
		      {title:message("ov.clearingRecord.unitPrice"),field:"unitPrice",width:50,sortable:true},
		      {title:message("ov.clearingRecord.amountOfCurrent"),field:"amountOfCurrent",width:50},
		      {title:message("ov.clearingRecord.reduce"),field:"reduce",width:50},
		      {title:message("ov.clearingRecord.clearingStatus"),field:"clearingStatus",width:30,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "SUCCESS"){
			    		  return  message("ov.clearingRecord.success");
			    	  }else if (value = "FAILURE"){
			    		  return  message("ov.clearingRecord.failure");
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
						"#startLongitude", "#startLatitude", "#startPositionDetails");
				
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
						"#endLongitude", "#endLatitude", "#endPositionDetails");
				
			}
		});
	})
	
	function createMap(x, y, title, lng, lat, details){
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
		map.addEventListener("click", showInfo);
		var geoc = new BMap.Geocoder();  //地理编码

		function showInfo(e){
			var pt = e.point;
			$(lng).val(pt.lng);
			$(lat).val(pt.lat);
			geoc.getLocation(pt, function(rs){
				var addComp = rs.addressComponents;
				var addressDetails = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
//				alert(addressDetails);
				$(details).textbox('setValue', addressDetails);
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
	}
	
})

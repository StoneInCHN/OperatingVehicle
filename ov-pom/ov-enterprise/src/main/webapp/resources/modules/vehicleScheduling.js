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

	$("#useCarRequest-table-list").datagrid({
		title:message("ov.useCarRequest.list"),
		fitColumns:true,
		toolbar:"#useCarRequest_manager_tool",
		url:'../vehicleScheduling/listRequest.jhtml',  
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
			    	  }else if (value = "DISTRIBUTED"){
			    		  return  message("ov.useCarRequest.distributed");
			    	  }else if (value = "FINISHED"){
			    		  return  message("ov.useCarRequest.finished");
			    	  }else if (value = "CANCELLED"){
			    		  return  message("ov.useCarRequest.cancelled");
			    	  }else if (value = "REJECTED"){
			    		  return  message("ov.useCarRequest.rejected");
			    	  }else if (value = "BREAK_CONTRACT"){
			    		  return  message("ov.useCarRequest.break_contract");
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
	
	//百度地图api
	function G(id) {
		return document.getElementById(id);
	}
	
	
	$("#startPositionButton").click(function(){
		
		var map_ip_location = "http://api.map.baidu.com/location/ip?ak=ulsOtfMZcNc4D6aQnBwwnOTt6ZKohflO&coor=bd09ll";
		$.ajax({
			url:map_ip_location,
			type:"get",
			dataType: 'JSONP',
			data: "",
			success:function(result){

				$('#mapContainer').dialog({    
					title: message("ov.useCarRequest.select_start_position"),     
				    width: 800,    
				    height: 600,    
				    modal: true,
				    iconCls:'icon-mini-add',
				    onOpen:function(){
						
				    }
				});			
				
				var map = new BMap.Map("mapContainer");          // 创建地图实例  
				var point = new BMap.Point(result.content.point.x, result.content.point.y);  // 创建点坐标  
				map.centerAndZoom(point, 11);	

				// 定义一个控件类,即function
				function InputControl(){
				  // 默认停靠位置和偏移量
				  this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
				  this.defaultOffset = new BMap.Size(15, 15);
				}

				InputControl.prototype = new BMap.Control();
				InputControl.prototype.initialize = function(map){
				  
					  var div = document.createElement("div");
					  var input = document.createElement("input");
					  input.id = "suggestId";
					  input.type = "text";
					  div.appendChild(input);
					  // 设置样式
					  div.style.cursor = "pointer";
					  div.style.border = "1px solid gray";
					  div.style.backgroundColor = "white";
					  // 添加DOM元素到地图中
					  map.getContainer().appendChild(div);
					  // 将DOM元素返回
					  return div;
  
				}
				var myInputCtrl = new InputControl();
				// 添加到地图当中
				map.addControl(myInputCtrl);
				
				
				function DivControl(){
				  // 默认停靠位置和偏移量
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
				// 添加到地图当中
				map.addControl(myDivCtrl);

				var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
						{"input" : "suggestId"
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
		});
		
		
	})
	
	 
})

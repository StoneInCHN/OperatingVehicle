var oldVehicleListStatus = []
var allVehicleListStatus = []

/**
 * 
 * @param map 地图
 * @param arrPois 行驶轨迹上的点
 */
function driveVehicle(map,arrPois,content){
    
    var lushu = new BMapLib.LuShu(map,arrPois,{
    	defaultContent:content,
    	autoView:false,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
    	icon  : new BMap.Icon("../../resources/images/carrun.png", new BMap.Size(39,20)),
    	speed: 80,
    	enableRotation:true,//是否设置marker随着道路的走向进行旋转
		landmarkPois: []
    });
    lushu.start();
}

function drawMutPoint(map,allVehicleListStatus){
	var points = [];
	var myIcon = new BMap.Icon("../../resources/images/carrun.png", new BMap.Size(39,20));
	
	function addMarker(point){
		var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
		map.addOverlay(marker);
	}
	function deletePoint(point){
		debugger;
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length -1; i++){
			if(allOverlay[i].getPosition()!= null && allOverlay[i].getPosition().lon == point.lon
					&& allOverlay[i].getPosition().lat == point.lat){
				map.removeOverlay(allOverlay[i]);
				return false;
			}
		}
	}
	debugger;
	map.clearOverlays();
	for(var i=0;i<allVehicleListStatus.length;i++){
		var point = new BMap.Point(allVehicleListStatus[i].lon,allVehicleListStatus[i].lat);
		
		if(oldVehicleListStatus.length ==0){
//			deletePoint(point);
			addMarker(point);
		}else{
//			if(oldVehicleListStatus[i].lon == allVehicleListStatus[i].lon &&
//					oldVehicleListStatus[i].lat == allVehicleListStatus[i].lat){
//				continue;
//			}
			var pointsArrs = [];
			for(var k=0;k<oldVehicleListStatus.length;k++){
				if(oldVehicleListStatus[k].plate == allVehicleListStatus[i].plate){
					var spoint = new BMap.Point(oldVehicleListStatus[k].lon,oldVehicleListStatus[k].lat);
					var epoint = new BMap.Point(allVehicleListStatus[i].lon,allVehicleListStatus[i].lat);
					pointsArrs.push(spoint);
					pointsArrs.push(epoint);
					driveVehicle(map,pointsArrs,oldVehicleListStatus[k].plate);
				}
			}
			
		}
		console.log('车牌号：'+allVehicleListStatus[i].plate+' lon:'+allVehicleListStatus[i].lon,"; lat:"+allVehicleListStatus[i].lat)
	}
	
	
	
}
var index = 0;
function loadAllVehicleStatus(map){
	var local = new Object();
	$.ajax({
		url:"../vehicle/allVehicleStatus.jhtml",
		type:"post",
		success:function(result,response,status){
			if(result.length>0 ){
				oldVehicleListStatus = allVehicleListStatus;
				allVehicleListStatus = new Array();
				for(var i = 0;i<result.length;i++){
					if(result[i].lat != null && result[i].lon != null){
						var vehicleStatus = new Object();
						vehicleStatus.lat = result[i].lat; 
						vehicleStatus.lon = result[i].lon;
						vehicleStatus.plate = result[i].plate;
						allVehicleListStatus.push(vehicleStatus);
					}
				}
				if(allVehicleListStatus.length>0){
					local.lat = allVehicleListStatus[0].lat; 
					local.lon = allVehicleListStatus[0].lon;
					var point = new BMap.Point(local.lon,local.lat);
			    	map.centerAndZoom(point,13);
				}
			}else{
				
			}
			drawMutPoint(map,allVehicleListStatus);
		}
	});

}
var vehicle_manager_tool = {
		add:function(){
			$('#addVehicle').dialog({
			    title: message("ov.vehicle.add"),    
			    width: 700,    
			    height: 550,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicle_form').form('validate');
						if(validate){
								$.ajax({
									url:"../vehicle/add.jhtml",
									type:"post",
									data:$("#addVehicle_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicle').dialog("close")
											$("#addVehicle_form").form("reset");
											$("#vehicle-table-list").datagrid('reload');
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
						 $('#addVehicle').dialog("close");
						 $("#addVehicle_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addVehicle_form').show();
			    	$("#addVehicleMotorcade").combobox({
			    		url:'../motorcade/findAllMotorcadeUnderTenant.jhtml',
					    valueField:'id',
					    method:"get",
					    textField:'motorcadeDesc',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editVehicle').dialog({    
				title: message("ov.common.edit"),     
			    width: 700,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicle/detail.jhtml?id='+_edit_row.id+'&handle=edit',
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicle_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicle/update.jhtml",
								type:"post",
								data:$("#editVehicle_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicle').dialog("close");
										$("#vehicle-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicle').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$('#editVehicle_form').show();
			    	$("#editVehicleMotorcade").combobox({
			    		url:'../motorcade/findAllMotorcadeUnderTenant.jhtml',
					    valueField:'id',
					    method:"get",
					    textField:'motorcadeDesc',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	$("#editVehicleMotorcade").combobox("setValue",$("#editVehicleMotorcade").attr("data-value"));
			    }
			});  
		},
		remove:function(){
			listRemove('vehicle-table-list','../vehicle/delete.jhtml');
		},
		vehicleDailyReport:function(){
			var _select_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _select_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var params=new Object();
				params.vehicleId=_select_row.id;
			
			if($('#queryReportDate').length){
				params.date = $('#queryReportDate').datebox('getValue');
			}
			$('#vehicleDailyReport').dialog({
			    title: message("ov.vehicle.vehicleDailyReport"),    
			    width: 600,    
			    height: 350,
			    href:'../vehicle/vehicleDailyReport.jhtml?vehicleId='+_select_row.id,
			    method:"get",
			    queryParams:params,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleDailyReport').dialog("close");
						 $("#vehicleDailyReport_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$('#reportVehicleId').val(_select_row.id);
			    	$('#queryReportDate').datebox({
			    	    onSelect: function(date){
			    	    	$.ajax({
								url:"../vehicle/getVehicleDailyData.jhtml",
								type:"post",
								data:{
									vehicleId:$('#reportVehicleId').val(),
									date:date
								},
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										$('reportDailyMileage').textbox('setValue',result.dailyMileage);
										$('reportAverageFuelConsumption').textbox('setValue',result.averageFuelConsumption);
										$('reportFuelConsumption').textbox('setValue',result.fuelConsumption);
										$('reportCost').textbox('setValue',result.cost);
										$('reportAverageSpeed').textbox('setValue',result.averageSpeed);
										$('reportEmergencybrakecount').textbox('setValue',result.emergencybrakecount);
										$('reportSuddenturncount').textbox('setValue',result.suddenturncount);
										$('reportRapidlyspeedupcount').textbox('setValue',result.rapidlyspeedupcount);
									}else{
										alertErrorMsg();
									}
								}
							});
			    	    }
			    	});


			    	$('#vehicleDailyReport_form').show();
			    },
			});  
		},
		showAllVehicle:function(){
			var timer;
			$('#allVehicleStatus').dialog({
			    title: message("ov.vehicle.allVehicleStatus"),    
			    width: document.body.clientWidth,    
			    height: document.body.clientHeight,
			    iconCls:'icon-mini-add',
			    cache: false,
			    href:'../vehicle/allVehicleStatus.jhtml',
			    buttons:[{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#allVehicleStatus').dialog("close");
						 $("#allVehicleStatus_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#allVehicleStatusMap").css("height", document.body.clientHeight-100);
			    	$('#allVehicleStatus_form').show();
			    	
			    	var map = new BMap.Map("allVehicleStatusMap"); 
			    	debugger;
			    	var local = new BMap.Point($('#tenantInfoLon').val(),$('#tenantInfoLat').val())
			    	//map.centerAndZoom(local,13);// 初始化地图,设置中心点坐标和地图级别。
			    	map.enableScrollWheelZoom();//启用滚轮放大缩小
			    	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
			    	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
			    	map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
			    	loadAllVehicleStatus(map);
			    	
			    	timer = setInterval(function () { loadAllVehicleStatus(map); },"20000")
			    	
			    },
			    onClose:function(){
			    	clearInterval(timer);
			    }
			});
		}
		
};

$(function(){
	$("#vehicle-table-list").datagrid({
		title:message("ov.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicle_manager_tool",
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 700,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../vehicle/detail.jhtml?id='+rowData.id+'&handle=details',
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleDetail').dialog("close");
					}
			    }],
			    onLoad:function(){		
			    	$("#vehicleDetail_form").find("input").attr("disabled","disabled");
			    	$("#vehicleMotorcadeDetail").combobox({
			    		url:'../motorcade/findAllMotorcadeUnderTenant.jhtml',
					    valueField:'id',
					    method:"get",
					    textField:'motorcadeDesc',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	$("#vehicleMotorcadeDetail").combobox("setValue",$("#vehicleMotorcadeDetail").attr("data-value"));
			    }
			    
			});   
		},
		onLoadSuccess:function(data){
			$("#totalRecord").val(data.total);
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.vehicle.motorcade"),field:"motorcade",width:50,align:'center',sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.motorcadeDesc;
			    	  }
		      	  }},
		      {title:message("ov.vehicle.plate"),field:"plate",width:50,align:'center',sortable:true},
		     
		      {title:message("ov.vehicle.vehicleNo"),field:"vehicleNo",width:50,align:'center',sortable:true},
	      	  {title:message("ov.vehicle.dashboardMileage"),field:"dashboardMileage",width:50,align:'center',sortable:true},
	      	  {title:message("ov.vehicle.vehicleFullBrand"),field:"vehicleFullBrand",width:50,align:'center',sortable:true},
		      {title:message("ov.vehicle.device"),field:"deviceNo",width:50,align:'center',sortable:false},
		      {title:message("ov.vehicle.plateDate"),field:"plateDate",width:50,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("ov.vehicle.produceDate"),field:"produceDate",width:50,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:"是否在线",field:"isOnline",width:40,align:'center',sortable:false,formatter: function(value,row,index){
					if(value){
						return  '是';
					}else{
						return  '否';
					}
				}
		      },
		      {title:'操作',field:'opt',width:30,align:'center',  
		            formatter:function(value,row,index){
		            	var btn =  '<div class="linkbtn_orange"  onclick="showOfflineLog(\''+ row.id +'\');" href="javascript:void(0)">离线记录</div>';  
		                return btn;  
		            }  
		       } 
		   ]
		]
	});

	
	$("#vehicle-search-btn").click(function(){
	  var _queryParams = $("#vehicle-search-form").serializeJSON();
	  $('#vehicle-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicle-table-list").datagrid('reload');
	})
})
function showOfflineLog(id){
	$('#offlineLogDiv').dialog({    
		title: '车辆离线记录',     
	    width: 700,    
	    height: 450,    
	    modal: true,
	    href:'../vehicle/offlineLog.jhtml?id='+id,
	    buttons:[{
			text:message("ov.common.close"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#offlineLogDiv').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	$('#offlineLog_form').show();
	    }
	});  
}
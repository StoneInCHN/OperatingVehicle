var globalTrackList=[];//轨迹点集合（全局变量）
var mapByTime = null;//根据时间段显示的地图（全局变量）
var index = 0;//轨迹点下标（全局变量）
var timer = null;//定时器（全局变量）
var oldPoint = [null,null];//上一个轨迹点（全局变量）
	
//按天查询
$("#track_search_btn").click(function(){
	  var _queryParams = $("#track_search_form").serializeJSON();
	  if(_queryParams["vehicleID"] == "" || _queryParams["searchDate"] == ""){
			showSuccessMsg(message("ov.vehicle.miss.param"));
			return;
	  }
	  
	  $("#track-table-list").datagrid({
			title:message("ov.vehicle.track"),
			fitColumns:true,
			pagination:true,
			striped:true,
			type:"post",
			queryParams:_queryParams,
			url:"../vehicleTrack/drawVehicleTrackMultiple.jhtml",
			beforeSend:function(){
				$.messager.progress({
					text:message("ov.common.progress")
				});
			},
			onDblClickRow : function (rowIndex, rowData){
				if(rowData.tracks.length == 0){
					$("#vehicleTrackMap").html(message("ov.vehicle.no.track"));
				}else{
					$('#vehicleTrackMap').dialog({    
					    title: message("ov.common.detail"),    
					    width: 900,    
					    height: 700, 
					    cache: false,
					    modal: true,
					    buttons:[{
							text:message("ov.common.close"),
							iconCls:'icon-cancel',
							handler:function(){
								 $('#vehicleTrackMap').dialog("close");
							}
					    }]
					}); 
					createMap("vehicleTrackMap",rowData.tracks,15);
				}
			},
			columns:[
			   [
			      {title : message("ov.vehicle.plate"),field : "plate"},
			      {title:message("ov.vehicleTrack.startTime"),field:"from",formatter: function(value,row,index){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					    }
			      },
			      {title:message("ov.vehicleTrack.endTime"),field:"to",formatter: function(value,row,index){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
						}
			      },
			      {title:message("ov.vehicleTrack.time"),field:"runTime",formatter: function(value,row,index){
			    	  		return formatSeconds(value);
			      		}  
			      },
			      {title:message("ov.vehicleTrack.startPoint"),field:"startAddr"},
			      {title:message("ov.vehicleTrack.endPoint"),field:"endAddr"},
			      {title:message("ov.vehicleTrack.mile"),field:"mileage"},
			   ]
			]
		});
});
//按时间段查询
$("#track_byTime_search_btn").click(function(){
	  if($("#track_byTime_vehicleID").val() == ""){
		  $.messager.alert(message("jlr.common.prompt"),'请在左侧先选择车辆!','info');
		  return false;
	  }
	  var validate = $('#track_byTime_search_form').form('validate');
	  var _queryParams = null;
		if(validate){
			_queryParams = $("#track_byTime_search_form").serializeJSON();
			var start = new Date(_queryParams["startDate"]);
			var end = new Date(_queryParams["endDate"]);
			var diff =end.getTime()-start.getTime()  //时间差的毫秒数
			if(diff <= 0){
				$.messager.alert(message("ov.common.prompt"),'开始时间应该小于结束时间!','info');
				return false;
			}
			if(diff > (5* 24 * 60 * 60 * 1000)){//时间段 > 5天 (考虑到时间区间太大，轨迹点就会太多太多，系统反应迟钝)
				$.messager.alert(message("ov.common.prompt"),'最多查询5天!','info');
				return false;
			}  
		}
	  $("#track-byTime-table-list").datagrid({
			title:message("ov.vehicle.track"),
			fitColumns:true,
			pagination:true,
			striped:true,
			type:"post",
			queryParams:_queryParams,
			url:"../vehicleTrack/drawVehicleTrackByTime.jhtml",
			beforeSend:function(){
				$.messager.progress({
					text:message("ov.common.progress")
				});
			},
			onDblClickRow : function (rowIndex, rowData){
				if(rowData.tracks.length == 0){
					$("#vehicleTrackByTimeMap").html(message("ov.vehicle.no.track"));
				}else{
					$('#vehicleTrackByTimeMap').dialog({    
					    title: message("ov.common.detail"),    
					    width: '100%',    
					    height: '100%', 
					    cache: false,
					    modal: true,
					    buttons:[
						{
							text:'轨迹回放',
							iconCls:'icon-ok',
							handler:function(){
								 //初始化
								mapByTime.clearOverlays();//清空已有的轨迹曲线								
								oldPoint = [null,null];
								index = 0;
								
								console.info("回放开始");
								
								var s_point = globalTrackList[0];
								var startPoint = new BMap.Point(s_point["x"], s_point["y"]);
								createMarker(startPoint,"../../resources/images/start.png",mapByTime,false);//起始点
								
								var e_point = globalTrackList[globalTrackList.length-1];
								var endPoint = new BMap.Point(e_point["x"], e_point["y"]);
								createMarker(endPoint,"../../resources/images/end.png",mapByTime,true);//终点
								
								timer = setInterval(splitTrack,10); //每10毫秒画 一段最小单元的轨迹折线
							}
						},
					     {text:message("ov.common.close"),
							iconCls:'icon-cancel',
							handler:function(){
								 $('#vehicleTrackByTimeMap').dialog("close");
							}
					    }]
					}); 
					createMapByTime("vehicleTrackByTimeMap",rowData.tracks,10);
				}
			},
			columns:[
			   [
			      {title : message("ov.vehicle.plate"),field : "plate"},
			      {title:message("ov.vehicleTrack.startTime"),field:"from",formatter: function(value,row,index){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					    }
			      },
			      {title:message("ov.vehicleTrack.endTime"),field:"to",formatter: function(value,row,index){
							return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
						}
			      },
			      {title:message("ov.vehicleTrack.time"),field:"runTime",formatter: function(value,row,index){
			    	  		return formatSeconds(value);
			      		}  
			      },
			      {title:message("ov.vehicleTrack.startPoint"),field:"startAddr"},
			      {title:message("ov.vehicleTrack.endPoint"),field:"endAddr"},
			      {title:message("ov.vehicleTrack.mile"),field:"mileage"},
			   ]
			]
		});
});

//以下方法为公共方法
function createMarker(point, icon,map){  // 创建图标对象   
	var myIcon = new BMap.Icon(icon, new BMap.Size(30, 90), {    
	// 指定定位位置。   
	// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
	// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
	   // 图标中央下端的尖角位置。    
	   //offset: new BMap.Size(15, -25),    
	   // 设置图片偏移。   
	   // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
	   // 需要指定大图的偏移位置，此做法与css sprites技术类似。    
	   //imageOffset: new BMap.Size(0, -25)   // 设置图片偏移    
	 });      
	// 创建标注对象并添加到地图   
	 var marker = new BMap.Marker(point, {icon: myIcon});  
	 map.addOverlay(marker);
}  


function createMap(id,trackList,level){
	var map = new BMap.Map(id);  
	
	var trackMap=[];
		
	var s_point = trackList[0];
	var e_point = trackList[trackList.length-1];
	
	var startPoint = new BMap.Point(s_point["x"], s_point["y"]);
	var endPoint = new BMap.Point(e_point["x"], e_point["y"]);
	
	createMarker(startPoint,"../../resources/images/start.png",map);
	createMarker(endPoint,"../../resources/images/end.png",map);
	
	var flag = 0;
	for(var j=0;j<trackList.length;j++){
		var m = new BMap.Point(trackList[j]["x"], trackList[j]["y"]);
		trackMap.push(m);
	}
	map.centerAndZoom(startPoint,level);// 初始化地图,设置中心点坐标和地图级别。level越大，地图比例越小
	
	map.enableScrollWheelZoom();//启用滚轮放大缩小
	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
	
	
	var polyline = new BMap.Polyline(trackMap,{strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});    
	map.addOverlay(polyline);
	
	map.addEventListener("click",function(e){
		console.log(e.point.lng + "," + e.point.lat);
	});
	
}
function createMapByTime(id,trackList,level){
	mapByTime = new BMap.Map(id);  
		
	var s_point = trackList[0];
	var e_point = trackList[trackList.length-1];
	
	var startPoint = new BMap.Point(s_point["x"], s_point["y"]);
	var endPoint = new BMap.Point(e_point["x"], e_point["y"]);
	
	createMarker(startPoint,"../../resources/images/start.png",mapByTime,false);
	createMarker(endPoint,"../../resources/images/end.png",mapByTime,true);
	
	globalTrackList = trackList;

	mapByTime.centerAndZoom(startPoint,level);// 初始化地图,设置中心点坐标和地图级别。
	
	mapByTime.enableScrollWheelZoom();//启用滚轮放大缩小
	mapByTime.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	mapByTime.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	mapByTime.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
	var point = [];
	for(var i= 0; i < globalTrackList.length; i ++){
		point.push(new BMap.Point(globalTrackList[i]["x"], globalTrackList[i]["y"]));
	}
	var polyline = new BMap.Polyline(point,{strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});    
	mapByTime.addOverlay(polyline);
}
//每次画 最小单元的轨迹折线
function splitTrack(){
	if(index == globalTrackList.length-1){//已经画到最后一个轨迹点了
		clearInterval(timer);//停止timer
		console.info("回放完毕");
		return false;
	}
	if(oldPoint[0] == null || oldPoint[1]  == null){//初始化上一个坐标点
		oldPoint[0] = globalTrackList[index]["x"];
		oldPoint[1] = globalTrackList[index]["y"];
	}else{
		//如果上一个轨迹点和当前轨迹点坐标一样，则继续调到下一个轨迹点，直到找到下一个不同坐标的轨迹点
		while(globalTrackList[index]["x"] == oldPoint[0] && globalTrackList[index]["y"] == oldPoint[1]){
			oldPoint[0] = globalTrackList[index]["x"];
			oldPoint[1] = globalTrackList[index]["y"];
			index++;
		}
	}
	var point = [];//一段最小单元的轨迹折线 (两个点连接的小线段)
	for(var i= 0; i < 2; i ++){
		point.push(new BMap.Point(globalTrackList[index+i]["x"], globalTrackList[index+i]["y"]));
	}
	var polyline = new BMap.Polyline(point,{strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});    
	mapByTime.addOverlay(polyline);//地图上画 一段最小单元的轨迹折线
	index++;//轨迹点下标+1,移到下一个轨迹点
}

//车辆查询
$(function(){
	$("#trackVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			$('#track_vehicleID').val(rowData.id);
			$('#track_byTime_vehicleID').val(rowData.id);
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : message("ov.vehicle.plate"),field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "品牌图标",field : "brandIcon",width :"47%",align : 'center',sortable : true},					
		]]
});
$("#track_vehicle_search_btn").click(function(){
		  var _queryParams = $("#track_vehicle_search_form").serializeJSON();
		  $('#trackVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#trackVehicleSearch-table-list").datagrid('reload');			
		});

});
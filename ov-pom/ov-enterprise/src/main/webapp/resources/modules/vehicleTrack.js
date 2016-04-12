
$("#track_search_btn").click(function(){
	  var _queryParams = $("#track_search_form").serializeJSON();
	  if(_queryParams["vehicleID"] == "" || _queryParams["searchDate"] == ""){
			showSuccessMsg(message("ov.vehicle.miss.param"));
			return;
	  }
	  
	  $.ajax({
			url:"../vehicleTrack/drawVehicleTrack.jhtml",
			type:"post",
			data:_queryParams,
			beforeSend:function(){
				$.messager.progress({
					text:message("ov.common.progress")
				});
			},
			success:function(result,response,status){
				$.messager.progress('close');
				console.log(result);
				
				if(result.length == 0){
					$("#vehicleTrackMap").html(message("ov.vehicle.no.track"));
				}else{
					createMap(result);
				}
				
				//$("#vehicleTrackMap")
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
				alertErrorMsg();
			}
		});
});

function createMap(track){
	var point = track[0];
	console.log(point["lat"]);
	var map = new BMap.Map("mapContainer");   
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
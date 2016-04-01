var vehicle_manager_tool = {
	add:function(){
		var _rows = $('#vehicle-table-list').datagrid('getSelections');
		if (_rows.length == 0) {
			$.messager.alert(message("ov.common.prompt"),
					"请选择要添加的记录", 'warning');
		}else{
			for (var i = 0; i < _rows.length; i++) {
				
				$('#selected_vehicle_table').datagrid("appendRow", { 
					vehicle_id: _rows[i].id,
					plate: _rows[i].plate, 
					vehicleLine: _rows[i].vehicleLine, 
					motorcade: _rows[i].motorcade, 
					oilPerHundred: _rows[i].oilPerHundred
				});
			}
		}
		
	}
		
};

var selected_manager_tool = {
	remove: function(){
		var _rows = $('#selected_vehicle_table').datagrid('getSelections');
		if (_rows.length == 0) {
			$.messager.alert(message("ov.common.prompt"),
					"请选择要删除的记录", 'warning');
		}else{
			for (var i = 0; i < _rows.length; i++) {
				 var rowIndex = $('#selected_vehicle_table').datagrid('getRowIndex', _rows[i]);
		         $('#selected_vehicle_table').datagrid('deleteRow', rowIndex);
			}
		}
	}
		
}

$(function(){

	$("#vehicle-table-list").datagrid({
		title:message("ov.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicle_manager_tool",
		url:'../vehicleScheduling/listVehicle.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
//			$('#vehicleDetails').dialog({    
//			    title: message("ov.common.detail"),    
//			    width: 400,    
//			    height: 350, 
//			    cache: false,
//			    modal: true,
//			    href:'../vehicleScheduling/detailsVehicle.jhtml?id='+rowData.id,
//			    buttons:[{
//					text:message("ov.common.close"),
//					iconCls:'icon-cancel',
//					handler:function(){
//						 $('#vehicleDetails').dialog("close");
//					}
//			    }]
//			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.vehicle.plate"),field:"plate",width:40,sortable:true},
		      {title:message("ov.vehicle.line"),field:"vehicleLine",width:40},
		      {title:message("ov.vehicle.motorcade"),field:"motorcade",width:80},
		      {title:message("ov.vehicle.oilPerHundred"),field:"oilPerHundred",width:30},
		   ]
		]
	});
	
	$("#vehicle-search-btn").click(function(){
	  var _queryParams = $("#vehicle-search-form").serializeJSON();
	  $('#vehicle-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicle-table-list").datagrid('reload');
	});
	
	$("input:text").focus(function(){
		console.log('selected');
		this.select();
	});
	
	/**
	 * 已选车辆table
	 */
	$("#selected_vehicle_table").datagrid({
		title: "已选车辆",
		fitColumns:true,
		toolbar:"#selected_manager_tool",
		url:'',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {field:"vehicle_id",width:0,hidden:true},
		      {title:message("ov.vehicle.plate"),field:"plate",width:40,sortable:true},
		      {title:message("ov.vehicle.line"),field:"vehicleLine",width:40},
		      {title:message("ov.vehicle.motorcade"),field:"motorcade",width:80},
		      {title:message("ov.vehicle.oilPerHundred"),field:"oilPerHundred",width:30},
		   ]
		]
	});
	
})

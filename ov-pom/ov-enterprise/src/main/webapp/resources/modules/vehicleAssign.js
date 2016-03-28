var vehicleAssign_manager_tool = {
		assign:function(){
			var _edit_row = $('#useCarRequest-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#assignVehicleView').dialog({    
				title: message("ov.common.assign"),     
			    width: 800,    
			    height: 500,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicleScheduling/assignVehicleView.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#assignVehicleView_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicleScheduling/assignVehicle.jhtml",
								type:"post",
								data:$("#assignVehicleView_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#assignUseCarRequest').dialog("close");
										$("#useCarRequest-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#assignUseCarRequest').dialog("close").form("reset");
					}
			    }]
			});  
		}
		
};

$(function(){
	var map_ip_location = "http://api.map.baidu.com/location/ip?ak=ulsOtfMZcNc4D6aQnBwwnOTt6ZKohflO&coor=bd09ll";

	$("#useCarRequest-table-list").datagrid({
		title:message("ov.useCarRequest.list"),
		fitColumns:true,
		toolbar:"#vehicleAssign_manager_tool",
		url:'../vehicleScheduling/listRequest.jhtml?childrenOrParent=parent',  
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
	
})

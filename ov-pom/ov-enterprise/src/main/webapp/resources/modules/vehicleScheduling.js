var useCarRequest_manager_tool = {
		add:function(){
			$('#addUseCarRequest').dialog({
			    title: message("ov.useCarRequest.add"),    
			    width: 400,    
			    height: 350,
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
	 
})

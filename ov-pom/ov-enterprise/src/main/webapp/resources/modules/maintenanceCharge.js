var maintenanceCharge_manager_tool = {
		add:function(){
			$('#addMaintenanceCharge').dialog({
			    title: message("ov.maintenanceCharge.add"),    
			    width: 500,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    modal:true,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addMaintenanceCharge_form').form('validate');
						if(validate){
								$.ajax({
									url:"../maintenanceCharge/add.jhtml",
									type:"post",
									data:$("#addMaintenanceCharge_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addMaintenanceCharge').dialog("close")
											$("#addMaintenanceCharge_form").form("reset");
											$("#maintenanceCharge-table-list").datagrid('reload');
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
						 $('#addMaintenanceCharge').dialog("close");
						 $("#addMaintenanceCharge_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addMaintenanceCharge_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#maintenanceCharge-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editMaintenanceCharge').dialog({    
				title: message("ov.common.edit"),     
			    width: 500,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../maintenanceCharge/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editMaintenanceCharge_form').form('validate');
						if(validate){
							$.ajax({
								url:"../maintenanceCharge/update.jhtml",
								type:"post",
								data:$("#editMaintenanceCharge_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editMaintenanceCharge').dialog("close");
										$("#maintenanceCharge-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editMaintenanceCharge').dialog("close").form("reset");
					}
			    }]
			});  
		},
		remove:function(){
			listRemove('maintenanceCharge-table-list','../maintenanceCharge/delete.jhtml');
		}
};

$(function(){
	$("#maintenanceCharge-table-list").datagrid({
		title:message("ov.maintenanceCharge.list"),
		fitColumns:true,
		toolbar:"#maintenanceCharge_manager_tool",
		url:'../maintenanceCharge/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#maintenanceChargeDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../maintenanceCharge/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#maintenanceChargeDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [							
		      {field:'ck',checkbox:true},
		      {title:message("ov.vehicle.plate"),field:"vehiclePlate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(row !=null){
		    		  return  row.vehicle.plate;
		    	  }
		      }},
		      {title:message("ov.maintenanceCharge.date"),field:"maintenanceDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format(message("ov.date.China.format.yyyyMMdd"));
			  }},
		      {title:message("ov.maintenanceCharge.amount")+"/元",field:"maintenanceAmount",width:100,sortable:true},
		      {title:message("ov.maintenanceCharge.mileage")+"/km",field:"maintenanceMileage",width:100,sortable:true},
		      {title:message("ov.maintenanceCharge.maintenanceCompany"),field:"maintenanceCompany",width:100,sortable:true},

		      {title:message("ov.common.remark"),field:"remark",width:100,sortable:true}
		   ]
		]
	});

	
	$("#maintenanceCharge-search-btn").click(function(){
	  var _queryParams = $("#maintenanceCharge-search-form").serializeJSON();
	  $('#maintenanceCharge-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#maintenanceCharge-table-list").datagrid('reload');
	})
	
	$("input:text").focus(function(){
		console.log('selected');
		  this.select();
	});
	 
})

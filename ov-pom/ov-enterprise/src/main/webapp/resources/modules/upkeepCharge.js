var upkeepCharge_manager_tool = {
		add:function(){
			$('#addUpkeepCharge').dialog({
			    title: message("ov.upkeepCharge.add"),    
			    width: 500,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    modal:true,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addUpkeepCharge_form').form('validate');
						if(validate){
								$.ajax({
									url:"../upkeepCharge/add.jhtml",
									type:"post",
									data:$("#addUpkeepCharge_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addUpkeepCharge').dialog("close")
											$("#addUpkeepCharge_form").form("reset");
											$("#upkeepCharge-table-list").datagrid('reload');
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
						 $('#addUpkeepCharge').dialog("close");
						 $("#addUpkeepCharge_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addUpkeepCharge_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#upkeepCharge-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editUpkeepCharge').dialog({    
				title: message("ov.common.edit"),     
			    width: 500,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../upkeepCharge/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editUpkeepCharge_form').form('validate');
						if(validate){
							$.ajax({
								url:"../upkeepCharge/update.jhtml",
								type:"post",
								data:$("#editUpkeepCharge_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editUpkeepCharge').dialog("close");
										$("#upkeepCharge-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editUpkeepCharge').dialog("close").form("reset");
					}
			    }]
			});  
		},
		remove:function(){
			listRemove('upkeepCharge-table-list','../upkeepCharge/delete.jhtml');
		}
};

$(function(){
	$("#upkeepCharge-table-list").datagrid({
		title:message("ov.upkeepCharge.list"),
		fitColumns:true,
		toolbar:"#upkeepCharge_manager_tool",
		url:'../upkeepCharge/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#upkeepChargeDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 500,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../upkeepCharge/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#upkeepChargeDetail').dialog("close");
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
		      {title:message("ov.upkeepCharge.date"),field:"upkeepDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format(message("ov.date.China.format.yyyyMMdd"));
			  }},
		      {title:message("ov.upkeepCharge.amount")+message("ov.common.unitPrice"),field:"upkeepAmount",width:100,sortable:true},
		      {title:message("ov.upkeepCharge.upkeepCompany"),field:"upkeepCompany",width:100,sortable:true},

		      {title:message("ov.common.remark"),field:"remark",width:100,sortable:true}
		   ]
		]
	});

	
	$("#upkeepCharge-search-btn").click(function(){
	  var _queryParams = $("#upkeepCharge-search-form").serializeJSON();
	  $('#upkeepCharge-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#upkeepCharge-table-list").datagrid('reload');
	})
	
	$("input:text").focus(function(){
		console.log('selected');
		  this.select();
	});
	 
})

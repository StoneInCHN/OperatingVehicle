var oilCharge_manager_tool = {
		add:function(){
			$('#addOilCharge').dialog({
			    title: message("ov.oilCharge.add"),    
			    width: 500,    
			    height: 500,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    modal:true,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addOilCharge_form').form('validate');
						if(validate){
								$.ajax({
									url:"../oilCharge/add.jhtml",
									type:"post",
									data:$("#addOilCharge_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addOilCharge').dialog("close")
											$("#addOilCharge_form").form("reset");
											$("#oilCharge-table-list").datagrid('reload');
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
						 $('#addOilCharge').dialog("close");
						 $("#addOilCharge_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addOilCharge_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#oilCharge-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editOilCharge').dialog({    
				title: message("ov.common.edit"),     
			    width: 500,    
			    height: 500,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../oilCharge/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editOilCharge_form').form('validate');
						if(validate){
							$.ajax({
								url:"../oilCharge/update.jhtml",
								type:"post",
								data:$("#editOilCharge_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editOilCharge').dialog("close");
										$("#oilCharge-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editOilCharge').dialog("close").form("reset");
					}
			    }]
			});  
		},
		remove:function(){
			listRemove('oilCharge-table-list','../oilCharge/delete.jhtml');
		}
};

$(function(){
	$("#oilCharge-table-list").datagrid({
		title:message("ov.oilCharge.list"),
		fitColumns:true,
		toolbar:"#oilCharge_manager_tool",
		url:'../oilCharge/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#oilChargeDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 500,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../oilCharge/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#oilChargeDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [							
		      {field:'ck',checkbox:true},
		      {title:message("ov.vehicle.plate"),field:"vehiclePlate",width:"10%",sortable:true,formatter: function(value,row,index){
		    	  if(row !=null){
		    		  return  row.vehicle.plate;
		    	  }
		      }},
		      {title:message("ov.oilCharge.date"),field:"oilDate",width:"10%",sortable:true,formatter: function(value,row,index){
					return new Date(value).Format(message("ov.date.China.format.yyyyMMdd"));
			  }},
		      {title:message("ov.oilCharge.amount")+message("ov.common.unitPrice"),field:"oilAmount",width:"8%",sortable:true},
		      {title:message("ov.oilCharge.discountAmount")+message("ov.common.unitPrice"),field:"discountAmount",width:"8%",sortable:true},
		      {title:message("ov.oilCharge.finalAmount")+message("ov.common.unitPrice"),field:"oilFinalAmount",width:"8%",sortable:true},
		      {title:message("ov.oilCharge.oilCount")+message("ov.common.unitVolume"),field:"oilCount",width:"8%",sortable:true},
		      {title:message("ov.oilCharge.oilPrice")+message("ov.common.unitPrice"),field:"oilPrice",width:"8%",sortable:true},
		      {title:message("ov.oilCharge.oilPrice"),field:"oilPrice",width:"10%",sortable:true},
		      {title:message("ov.oilCharge.invoiceNumber"),field:"invoiceNumber",width:"10%",sortable:true,formatter: function(value,row,index){
					if(value != null){
						return value;
					}else{
						return message("ov.oilCharge.not.provideInvoice");
					}
			  }},
		      {title:message("ov.common.remark"),field:"remark",width:"20%",sortable:true}
		   ]
		]
	});

	
	$("#oilCharge-search-btn").click(function(){
	  var _queryParams = $("#oilCharge-search-form").serializeJSON();
	  $('#oilCharge-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#oilCharge-table-list").datagrid('reload');
	})
	
	$("input:text").focus(function(){
		console.log('selected');
		  this.select();
	});
	 
})

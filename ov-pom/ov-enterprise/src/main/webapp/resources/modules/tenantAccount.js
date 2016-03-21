var tenantAccount_manager_tool = {
		add:function(){
			$('#addTenantAccount').dialog({
			    title: message("ov.tenantAccount.add"),    
			    width: 400,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addTenantAccount_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../tenantAccount/add.jhtml",
									type:"post",
									data:$("#addTenantAccount_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addTenantAccount').dialog("close")
											$("#addTenantAccount_form").form("reset");
											$("#tenantAccount-table-list").datagrid('reload');
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
						 $('#addTenantAccount').dialog("close");
						 $("#addTenantAccount_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addTenantAccount_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#tenantAccount-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editTenantAccount').dialog({    
				title: message("ov.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../tenantAccount/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editTenantAccount_form').form('validate');
						if(validate){
							$.ajax({
								url:"../tenantAccount/update.jhtml",
								type:"post",
								data:$("#editTenantAccount_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editTenantAccount').dialog("close");
										$("#tenantAccount-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editTenantAccount').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('tenantAccount-table-list','../tenantAccount/delete.jhtml');
		}
};

$(function(){
	$("#tenantAccount-table-list").datagrid({
		title:message("ov.tenantAccount.list"),
		fitColumns:true,
		toolbar:"#tenantAccount_manager_tool",
		url:'../tenantAccount/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#tenantAccountDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../tenantAccount/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#tenantAccountDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.tenantAccount.userName"),field:"userName",width:100,sortable:true},
		      {title:message("ov.tenantAccount.realName"),field:"tenantUser",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value !=null){
			    		  return  value.realName;
			    	  }else {
			    		  return value; 
			    	  }
		      	  }    
		      },
		      {title:message("ov.tenantAccount.isSystem"),field:"isSystem",width:100,sortable:true},
		      {title:message("ov.tenantAccount.accoutStatus"),field:"accoutStatus",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "ACTIVED"){
			    		  return  message("ov.tenantAccount.active");
			    	  }else if (value = "LOCKED"){
			    		  return  message("ov.tenantAccount.locked");
			    	  }
		      	  }  
		      },
		      {title:message("ov.tenantAccount.loginDate"),field:"loginDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		      {title:message("ov.tenantAccount.loginIp"),field:"loginIp",width:100,sortable:true},
		   ]
		]
	});

	
	$("#tenantAccount-search-btn").click(function(){
	  var _queryParams = $("#tenantAccount-search-form").serializeJSON();
	  $('#tenantAccount-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#tenantAccount-table-list").datagrid('reload');
	})
	
	$("input:text").focus(function(){
		console.log('selected');
		  this.select();
	});
	 
})

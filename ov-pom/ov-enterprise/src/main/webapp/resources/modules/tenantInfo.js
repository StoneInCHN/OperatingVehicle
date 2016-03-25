var branchBusiness_manager_tool = {
		add:function(){
			$('#addBranchBusiness').dialog({
			    title: message("ov.branchBusiness.add"),    
			    width: 400,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    modal:true,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addBranchBusiness_form').form('validate');
						if(validate){
								$.ajax({
									url:"../tenantInfo/addBranch.jhtml",
									type:"post",
									data:$("#addBranchBusiness_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addBranchBusiness').dialog("close")
											$("#addBranchBusiness_form").form("reset");
											$("#branchBusiness-table-list").datagrid('reload');
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
						 $('#addBranchBusiness').dialog("close");
						 $("#addBranchBusiness_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addBranchBusiness_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#branchBusiness-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editBranchBusiness').dialog({    
				title: message("ov.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../tenantInfo/editBranch.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editBranchBusiness_form').form('validate');
						if(validate){
							$.ajax({
								url:"../tenantInfo/updateBranch.jhtml",
								type:"post",
								data:$("#editBranchBusiness_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editBranchBusiness').dialog("close");
										$("#branchBusiness-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editBranchBusiness').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('branchBusiness-table-list','../tenantInfo/deleteBranch.jhtml');
		}
};
var tenantBranchAdmin_manager_tool = {

		add:function(){
			$('#addTenantBranchAdmin').dialog({
			    title: message("ov.tenantInfo.branch.admin.add"),    
			    width: 700,    
			    height: 700,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    modal:true,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addTenantBranchAdmin_form').form('validate');
						if(validate){
								$.ajax({
									url:"../tenantInfo/addAdmin.jhtml",
									type:"post",
									data:$("#addTenantBranchAdmin_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addTenantBranchAdmin').dialog("close")
											$("#addTenantBranchAdmin_form").form("reset");
											$("#tenantBranchAdmin-table-list").datagrid('reload');
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
						 $('#addTenantBranchAdmin').dialog("close");
						 $("#addTenantBranchAdmin_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addTenantBranchAdmin_form').show();
			    	$("#addTenantBranchAdmin_selectTenant").combotree({    
					    url: '../tenantInfo/list.jhtml?findListForAdmin=true',    
					    method:"get",
					    animate:true,
					    lines:true,
					    required:true,
					    prompt:message("ov.common.please.select"),
					    formatter:function(node){
					    	node.text = node.tenantName;
							return node.tenantName;
						},								    
						onSelect: function(tenantInfo){    
							if(tenantInfo.children.length > 0){
								$.messager.alert(message("ov.common.prompt"),message("ov.tenantInfo.select.branch"),'warning');  
								$('#addTenantBranchAdmin_selectTenant').combotree('clear');
							}
				        }
					});			    	
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#tenantBranchAdmin-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editTenantBranchAdmin').dialog({    
				title: message("ov.common.edit"),     
			    width: 700,    
			    height: 700,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../tenantInfo/detailsAdmin.jhtml?path=editAdmin&id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editTenantBranchAdmin_form').form('validate');
						if(validate){
							$.ajax({
								url:"../tenantInfo/updateAdmin.jhtml",
								type:"post",
								data:$("#editTenantBranchAdmin_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editTenantBranchAdmin').dialog("close");
										//$("#editTenantBranchAdmin_form").form("reset");
										$("#tenantBranchAdmin-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editTenantBranchAdmin').dialog("close");
						 $("#editTenantBranchAdmin_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('tenantBranchAdmin-table-list','../tenantInfo/deleteAdmin.jhtml');
		}
};

$(function(){
	$("#branchBusiness-table-list").datagrid({
		title:message("ov.branchBusiness.list"),
		fitColumns:true,
		toolbar:"#branchBusiness_manager_tool",
		url:'../tenantInfo/listBranch.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#branchBusinessDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../tenantInfo/detailsBranch.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#branchBusinessDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.tenantInfo.tenantName"),field:"tenantName",width:"25%",sortable:true},
		      {title:message("ov.tenantInfo.contactPhone"),field:"contactPhone",width:"25%",sortable:true},
		      {title:message("ov.tenantInfo.contactPerson"),field:"contactPerson",width:"25%",sortable:true},
		      {title:message("ov.tenantInfo.accountStatus"),field:"accountStatus",width:"22%",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "ACTIVED"){
			    		  return  message("ov.tenantAccount.active");
			    	  }else if (value = "LOCKED"){
			    		  return  message("ov.tenantAccount.locked");
			    	  }
		      	  }  
		      },
		   ]
		]
	});
	
	$("#branchBusiness-search-btn").click(function(){
	  var _queryParams = $("#branchBusiness-search-form").serializeJSON();
	  $('#branchBusiness-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#branchBusiness-table-list").datagrid('reload');
	});
	
	$("input:text").focus(function(){
		console.log('selected');
		  this.select();
	});
	 
});
	$(function(){
		$("#tenantBranchAdmin-table-list").datagrid({
			title:message("ov.tenantInfo.branch.admin.list"),
			fitColumns:true,
			toolbar:"#tenantBranchAdmin_manager_tool",
			url:'../tenantInfo/listTenantBranchAdmin.jhtml',  
			pagination:true,
			loadMsg:message("ov.common.loading"),
			striped:true,
			onDblClickRow : function (rowIndex, rowData){
				$('#viewTenantBranchAdmin').dialog({    
				    title: message("ov.common.detail"),    
				    width: 700,    
				    height: 700, 
				    cache: false,
				    modal: true,
				    href:'../tenantInfo/detailsAdmin.jhtml?path=viewAdmin&id='+rowData.id,
				    buttons:[{
						text:message("ov.common.close"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#viewTenantBranchAdmin').dialog("close");
						}
				    }]
				});   
			},
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("ov.tenantAccount.userName"),field:"userName",width:"10%",sortable:true},
			      {title:message("ov.tenantInfo.tenantName"),field:"tenantName",width:"10%",sortable:true,formatter: function(value,row,index){
			    	  if(row !=null){
			    		  return  row.tenantUser.tenantName;
			    	  }
			      }},
			      {title:message("ov.tenantAccount.realName"),field:"tenantUser",width:"15%",sortable:true,formatter: function(value,row,index){
				    	  if(value !=null){
				    		  return  value.realName;
				    	  }else {
				    		  return value; 
				    	  }
			      }},
			      {title:message("ov.tenantAccount.isSystem"),field:"isSystem",width:"15%",sortable:true,formatter: function(value,row,index){
				    	  if(value == false){
				    		  return  message("ov.common.no");
				    	  }else if (value = true){
				    		  return  message("ov.common.yes");
				    	  }
					} },
			      {title:message("ov.tenantAccount.accoutStatus"),field:"accoutStatus",width:"15%",sortable:true,formatter: function(value,row,index){
				    	  if(value == "ACTIVED"){
				    		  return  message("ov.tenantAccount.active");
				    	  }else if (value = "LOCKED"){
				    		  return  message("ov.tenantAccount.locked");
				    	  }
			      }},
			      {title:message("ov.tenantAccount.loginDate"),field:"loginDate",width:"15%",sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				  }},
			      {title:message("ov.tenantAccount.loginIp"),field:"loginIp",width:"15%",sortable:true},
			   ]
			]
		});
		$("#tenantBranchAdmin-search-btn").click(function(){
			  var _queryParams = $("#tenantBranchAdmin-search-form").serializeJSON();
			  $('#tenantBranchAdmin-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#tenantBranchAdmin-table-list").datagrid('reload');
			});
	});


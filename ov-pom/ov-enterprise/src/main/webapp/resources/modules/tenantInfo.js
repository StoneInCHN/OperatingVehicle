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
				$.messager.alert(message("ov.common.select.editRow"));  
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
		      {title:message("ov.tenantInfo.tenantName"),field:"tenantName",width:100,sortable:true},
		      {title:message("ov.tenantInfo.contactPhone"),field:"contactPhone",width:100,sortable:true},
		      {title:message("ov.tenantInfo.contactPerson"),field:"contactPerson",width:100,sortable:true},
		      {title:message("ov.tenantInfo.accountStatus"),field:"accountStatus",width:100,sortable:true,
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
	})
	
	$("input:text").focus(function(){
		console.log('selected');
		  this.select();
	});
	 
})

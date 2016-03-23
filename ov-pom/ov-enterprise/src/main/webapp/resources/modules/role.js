var role_manager_tool = {
		add:function(){		
			$('#addRole').dialog({    
			    title: message("ov.role.add"),    
			    width: 370,    
			    height: 370,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						alert($('#addRoleName').val());
						var validate = $('#addRole_form').form('validate');
						if(validate){
							$.ajax({
								url:"../role/add.jhtml",
								type:"post",
								data:$("#addRole_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#addRole_form').form('reset');
									$('#addRole').dialog("close");
									$("#role-table-list").datagrid('reload');
									
								},
								error:function (XMLHttpRequest, textStatus, errorThrown) {
									$.messager.progress('close');
									alertErrorMsg();
								}
							});
						}
						else{
							alert("validate fail");
						};
					}
				},{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addRole').dialog("close");
					}
			    }]
			});  
			 $('#addRole_form').show();
		},
		edit:function(){
			var _edit_row = $('#role-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.notice"),message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editRole').dialog({
			    title: message("ov.common.edit"),     
			    width: 370,    
			    height: 370,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../role/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editRole_form').form('validate');
						if(validate){
							$.ajax({
								url:"../role/update.jhtml",
								type:"post",
								data:$("#editRole_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#editRole').dialog("close");
									$("#role-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editRole').dialog("close");
					}
			    }]
			});  
			$('#editRole_form').show();
		},
		remove:function(){
			var _edit_row = $('#role-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.notice"),message("ov.common.select.deleteRow"));  
				return false;
			}
			listRemove('role-table-list','../role/delete.jhtml');
		}
}

$(function(){
	
	$("#role-table-list").datagrid({
		title:message("ov.role.record"),
		fitColumns:true,
		toolbar:"#role_manager_tool",
		url:'../role/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#roleDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 500,    
			    height: 510, 
			    cache: false,   
			    href:'../role/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#roleDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.role.name"),field:"name",width:20,align:'center',formatter:function(value,row,index){
		    	  return row.name;
		      }},
		      {title:message("ov.role.description"),field:"description",width:80,align:'center',formatter:function(value,row,index){
		    	  return row.description;
		      }},
		      {title:message("ov.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      }
		   ]
		]

	});
	
	
	$("#role_search_btn").click(function(){
	  var _queryParams = $("#role_search_form").serializeJSON();
	  $('#role-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#role-table-list").datagrid('reload');
	})
	
	$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }
	})
	
	
	
})


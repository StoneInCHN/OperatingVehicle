var role_auth_manager_tool = {
		auth:function(){		
			//授权树形列表
			var _edit_row = $('#role-auth-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.notice"),message("ov.common.select.editRow"));  
				return false;
			}
			$('#role-dialog-auth').dialog({    
			    title: message("ov.role.auth.manange"),    
			    width: 450,    
			    height: 500,    
			    closed: false,    
			    cache: false,    
			    modal: true ,
			    onOpen:function(){
			    	$('#roleTreeAuth').tree({
			    		url:'../role/listAuth.jhtml?id='+_edit_row.id,  
			    		cache:false,
			    	    animate:true,
			    	    lines:true
			    	});
			    	$('#roleTreeAuth').tree('collapseAll');

			    },
				buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
							var selectedList = $('#roleTreeAuth').tree('getChecked', ['checked','indeterminate']);
							var _ids = [];
							for(var i=0; i< selectedList.length; i++){
								_ids[i] = selectedList[i].id;
							}
							$.ajax({
								url:"../role/addAuth.jhtml",
								type:"post",
								traditional : true,
								data:{
									"id":_edit_row.id,
									"authIds": _ids
								},
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#role-dialog-auth').dialog("close");
									$('#roleTreeAuth').tree("reload");
								},
								error:function (XMLHttpRequest, textStatus, errorThrown) {
									$.messager.progress('close');
									alertErrorMsg();
								}
							});
					}
				},{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#role-dialog-auth').dialog("close");
					}
			    }]
			}); 
			
		}
}

$(function(){
	$("#role-auth-table-list").datagrid({
		title:message("ov.role.record"),
		fitColumns:true,
		toolbar:"#role_auth_manager_tool",
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
})


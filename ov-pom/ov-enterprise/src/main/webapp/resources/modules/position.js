$(function(){
		$("#position-table-list").datagrid({
			title:message("ov.position.list"),
			fitColumns:true,
			url:'../position/list.jhtml',  
			pagination: true,
			loadMsg:message("ov.common.loading"),
			striped:true,
			toolbar: [{
				text:message("ov.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addPosition").dialog({    
					    title:message("ov.position.add"),   
					    width: 350,    
					    height: 220,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("ov.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addPosition_form').form('validate');
								if(validate){
									$.ajax({
										url:"../position/add.jhtml",
										type:"post",
										data:$("#addPosition_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("ov.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addPosition_form').form('reset');
											$('#addPosition').dialog("close");
											$("#position-table-list").datagrid('reload');
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
								};
							}
						},{
							text:message("ov.common.cancel"),
							iconCls:'icon-cancel',
							handler:function(){
								 $('#addPosition').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addPosition_form').show();
						    	$("#addPosition_form_departmentId").combotree({    
						    	    url: '../department/list.jhtml',    
						    	    method:"get",
						    	    animate:true,
						    	    lines:true,
						    	    prompt:message("ov.common.please.select"),
						    	    formatter:function(node){
						    	    	node.text = node.name;
						    			return node.name;
						    		}
									
						    	});  
						},
						onClose:function(){
						    	$('#addPosition_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("ov.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#position-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');    
						return false;
					}
					$("#editPosition").dialog({
						width:300,
						height:200,
						iconCls:'icon-mini-edit',
						title:"部门编辑",
						href:'../position/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("ov.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editPosition_form').form('validate');
								if(validate){
									$.ajax({
										url:"../position/update.jhtml",
										type:"post",
										data:$("#editPosition_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("ov.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editPosition_form').form('reset');
											$('#editPosition').dialog("close");
											$("#position-table-list").datagrid('reload');
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
								};
							}
						},{
							text:message("ov.common.cancel"),
							iconCls:'icon-cancel',
							handler:function(){
								 $('#editPosition').dialog("close");
							}
						}],
						onLoad:function(){
				    	$("#editPosition_form_departmentId").combotree({    
				    	    url: '../department/list.jhtml',    
				    	    method:"get",
				    	    animate:true,
				    	    lines:true,
				    	    prompt:message("ov.common.please.select"),
				    	    formatter:function(node){
				    	    	node.text = node.name;
				    			return node.name;
				    		},
				    		onLoadSuccess:function(){
								$('#editPosition_form_departmentId').combotree("setValue",$("#editPosition_form_departmentId").attr("data-value"));
							},
				    	});  
				},
					})
				}
			},'-',{
				text:message("ov.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					listRemove('position-table-list','../position/delete.jhtml');
				}
			}],
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("ov.position.name"),field:"name",width:100,sortable:true},
			      {title:message("ov.position.department"),field:"department",width:100,sortable:true,formatter: function(value,row,index){
			    	  if(value){
			    		  return value.name;
			    	  }else{
			    		  return "";
			    	  }
				  }},
			      {title:message("ov.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
				  }},
			      {title:message("ov.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
				  }}
			   ]
			]
	
		});
})
	
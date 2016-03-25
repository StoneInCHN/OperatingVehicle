$(function(){
		$("#department-table-list").treegrid({
			title:message("ov.department.list"),
			fitColumns:true,
			url:'../department/list.jhtml',  
			rownumbers: true,
			method:"get",
			idField:'id',
    	    lines:true,
		    treeField:'name', 
			loadMsg:message("ov.common.loading"),
			striped:true,
			toolbar: [{
				text:message("ov.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addDepartment").dialog({    
					    title:message("ov.department.add"),   
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
								var validate = $('#addDepartment_form').form('validate');
								if(validate){
									$.ajax({
										url:"../department/add.jhtml",
										type:"post",
										data:$("#addDepartment_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("ov.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addDepartment_form').form('reset');
											$('#addDepartment').dialog("close");
											$("#department-table-list").treegrid('reload');
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
								 $('#addDepartment').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addDepartment_form').show();
						    	$("#addDepartment_parentName").combotree({    
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
						    	$("#addDepartment_tenantInfo").combotree({    
								    url: '../tenantInfo/list.jhtml',    
								    method:"get",
								    animate:true,
								    lines:true,
								    required:true,
								    prompt:message("ov.common.please.select"),
								    formatter:function(node){
								    	node.text = node.tenantName;
										return node.tenantName;
									}
								});		
						},
						onClose:function(){
						    	$('#addDepartment_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("ov.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#department-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');    
						return false;
					}
					$("#editDepartment").dialog({
						width:300,
						height:200,
						iconCls:'icon-mini-edit',
						title:message("ov.department.edit"),
						href:'../department/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("ov.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editDepartment_form').form('validate');
								if(validate){
									$.ajax({
										url:"../department/update.jhtml",
										type:"post",
										data:$("#editDepartment_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("ov.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editDepartment_form').form('reset');
											$('#editDepartment').dialog("close");
											$("#department-table-list").treegrid('reload');
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
								 $('#editDepartment').dialog("close");
							}
						}],
						onLoad:function(){
				    	$("#editDepartment_form_parentName").combotree({    
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
								$('#editDepartment_form_parentName').combotree("setValue",$("#editDepartment_form_parentName").attr("data-value"));
							},
				    	});  
				},
					})
				}
			},'-',{
				text:message("ov.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#department-table-list").treegrid('getSelections');
					if (_rows.length == 0) {
						$.messager.alert(message("ov.common.prompt"), message("ov.common.select.deleteRow"),'warning');
					} else {
						var _ids = [];
						for (var i = 0; i < _rows.length; i++) {
							_ids.push(_rows[i].id);
						}
						if (_ids.length > 0) {
							$.messager.confirm(message("ov.common.confirm"), message("ov.common.delete.confirm"), function(r) {
								if (r) {
									$.ajax({
										url : "../department/delete.jhtml",
										type : "post",
										traditional : true,
										data : {
											"ids" : _ids
										},
										beforeSend : function() {
											$.messager.progress({
												text : message("ov.common.progress")
											});
										},
										success : function(result, response, status) {
											$.messager.progress('close');
											var resultMsg = result.content;
											if (response == "success") {
												showSuccessMsg(resultMsg);
												$("#department-table-list").treegrid('reload');
											} else {
												alertErrorMsg();
											}
										}
									});
								}
							})
						}

					}
				}
			}],
			columns:[
			   [
			      {title:message("ov.department.name"),field:"name",width:100,sortable:true},
			      {title:message("ov.department.grade"),field:"grade",width:100,sortable:true},
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
	
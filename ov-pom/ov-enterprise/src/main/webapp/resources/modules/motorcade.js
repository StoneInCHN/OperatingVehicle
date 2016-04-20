var motorcade_manager_tool = {
		add:function(){
			$('#addMotorcade').dialog({
			    title: message("ov.motorcade.add"),    
			    width: 350,    
			    height: 150,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addMotorcade_form').form('validate');
						if(validate){
								$.ajax({
									url:"../motorcade/save.jhtml",
									type:"post",
									data:$("#addMotorcade_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addMotorcade').dialog("close")
											$("#addMotorcade_form").form("reset");
											$("#motorcade-table-list").datagrid('reload');
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
						 $('#addMotorcade').dialog("close");
						 $("#addMotorcade_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addMotorcade_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#motorcade-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editMotorcade').dialog({    
				title: message("ov.common.edit"),     
			    width: 700,    
			    height: 300,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../motorcade/edit.jhtml?id=' + _edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editMotorcade_form').form('validate');
						if(validate){
							$.ajax({
								url:"../motorcade/update.jhtml",
								type:"post",
								data:$("#editMotorcade_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editMotorcade').dialog("close");
										$("#motorcade-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editMotorcade').dialog("close");
					}
			    }]
			});  
		}
		
};

$(function(){
	$("#motorcade-table-list").datagrid({
		title:message("ov.motorcade.list"),
		fitColumns:true,
		toolbar:"#motorcade_manager_tool",
		url:'../motorcade/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			 
		},
		onLoadSuccess:function(data){
			$("#totalRecord").val(data.total);
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.motorcade.desc"),field:"motorcadeDesc", width: 200,sortable:true}
		   ]
		]
	});

	$("#motorcade-search-btn").click(function(){
	  var _queryParams = $("#motorcade-search-form").serializeJSON();
	  $('#motorcade-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#motorcade-table-list").datagrid('reload');
	})
})

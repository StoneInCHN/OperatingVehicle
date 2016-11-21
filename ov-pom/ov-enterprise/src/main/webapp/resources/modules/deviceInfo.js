var deviceInfo_manager_tool = {
		//解绑车辆
		unBind:function(){
			var _edit_row = $('#deviceInfo-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			if(_edit_row.bindStatus=="UNBINDED"){
				$.messager.alert(message("ov.deviceInfo.bindStatus"),message("ov.deviceInfo.bindStatus.unBinded"),'warning');  
				return false;
			}
			$.messager.confirm('确认解绑', '确实解绑？', function(r){
				if (!r){
					return false;
				}else{
					$.ajax({
						url:"../deviceInfo/unBind.jhtml?id="+_edit_row.id,
						type:"get",
						beforeSend:function(){
							$.messager.progress({
								text:message("ov.common.saving")
							});
						},
						success:function(result,response,status){
							$.messager.progress('close');
							showSuccessMsg(result.content);
							$("#deviceInfo-table-list").datagrid('reload');
						},
						error:function (XMLHttpRequest, textStatus, errorThrown) {
							$.messager.progress('close');
							alertErrorMsg();
						}
					});
				}
			});
			
		},
		//绑定车辆
		bind:function(){
				var _edit_row = $('#deviceInfo-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
					return false;
				}
				if(_edit_row.bindStatus=="BINDED"){
					$.messager.alert(message("ov.deviceInfo.bindStatus"),message("ov.deviceInfo.bindStatus.binded"),'warning');  
					return false;
				}
				$('#bindDevice').dialog({
				    title: message("ov.deviceInfo.bindVehicle"),    
				    width: 600,    
				    height: 350,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("ov.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							debugger;
							var validate = $('#bindDevice_form').form('validate');
							var _edit_row = $('#deviceInfo-table-list').datagrid('getSelected');
							
							//$('#bindDevice_form').append('<input type="hidden" name="deviceId" value="'+_edit_row.id+'"/>')
							$('#bindDevice_ID').val(_edit_row.id);
							if(validate){
									$.ajax({
										url:"../deviceInfo/bind.jhtml",
										type:"post",
										data:$("#bindDevice_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("ov.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											if(response == "success"){
												showSuccessMsg(result.content);
												$('#bindDevice').dialog("close")
												$("#bindDevice_form").form("reset");
												$("#deviceInfo-table-list").datagrid('reload');
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
							 $('#addVehicle').dialog("close");
							 $("#addVehicle_form").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#bindDevice_form').show();
				    },
				});  
		}
};

$(function(){
	$("#deviceInfo-table-list").datagrid({
		title:message("ov.deviceInfo.list"),
		fitColumns:true,
		toolbar:"#deviceInfo_manager_tool",
		url:'../deviceInfo/list.jhtml',  
		pagination:true,
		singleSelect:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#deviceInfoDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../deviceInfo/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#deviceInfoDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.deviceInfo.deviceNO"),field:"deviceNo",width:100,sortable:true},
		      {title:message("ov.deviceInfo.deviceType"),field:"type",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }
		      	  }},
		      {title:message("ov.deviceInfo.bindStatus"),field:"bindStatus",width:100,sortable:true,
		      		styler: function(value,row,index){
		      			if(value == "BINDED"){
		      				return 'color:green;';
						}else if (value == "UNBINDED" || value == null){
							return 'color:red;';
						}
					},
		    	  formatter: function(value,row,index){
			    	  if(value == "BINDED"){
			    		  return  message("ov.deviceInfo.bindStatus.BINDED");
			    	  }else if (value == "UNBINDED" || value == null){
			    		  return  message("ov.deviceInfo.bindStatus.UNBINDED");
			    	  }
		      	  }  
		      },
		      {title:message("ov.deviceInfo.bindVehicle"),field:"vehicle",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.plate;
			    	  }else {
			    		  return null;
			    	  }
		      	  }  
		      },
		      {title:message("ov.deviceInfo.bindTime"),field:"bindTime",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null)
		    	  		return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
		    	  	else
		    	  		return null;
				}
		      },
		      {title:message("ov.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#deviceInfo-search-btn").click(function(){
	  var _queryParams = $("#deviceInfo-search-form").serializeJSON();
	  $('#deviceInfo-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#deviceInfo-table-list").datagrid('reload');
	})
})

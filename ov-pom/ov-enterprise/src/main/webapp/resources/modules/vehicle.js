var vehicle_manager_tool = {
		add:function(){
			$('#addVehicle').dialog({
			    title: message("ov.vehicle.add"),    
			    width: 700,    
			    height: 550,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicle_form').form('validate');
						if(validate){
								$.ajax({
									url:"../vehicle/add.jhtml",
									type:"post",
									data:$("#addVehicle_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicle').dialog("close")
											$("#addVehicle_form").form("reset");
											$("#vehicle-table-list").datagrid('reload');
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
			    	$('#addVehicle_form').show();
			    	$("#addVehicleMotorcade").combobox({
			    		url:'../motorcade/findAllMotorcadeUnderTenant.jhtml',
					    valueField:'id',
					    method:"get",
					    textField:'motorcadeDesc',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editVehicle').dialog({    
				title: message("ov.common.edit"),     
			    width: 700,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicle/detail.jhtml?id='+_edit_row.id+'&handle=edit',
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicle_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicle/update.jhtml",
								type:"post",
								data:$("#editVehicle_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicle').dialog("close");
										$("#vehicle-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicle').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$('#editVehicle_form').show();
			    	$("#editVehicleMotorcade").combobox({
			    		url:'../motorcade/findAllMotorcadeUnderTenant.jhtml',
					    valueField:'id',
					    method:"get",
					    textField:'motorcadeDesc',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	$("#editVehicleMotorcade").combobox("setValue",$("#editVehicleMotorcade").attr("data-value"));
			    }
			});  
		},
		remove:function(){
			listRemove('vehicle-table-list','../vehicle/delete.jhtml');
		}
};

$(function(){
	$("#vehicle-table-list").datagrid({
		title:message("ov.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicle_manager_tool",
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 700,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../vehicle/detail.jhtml?id='+rowData.id+'&handle=details',
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleDetail').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$('#editVehicle_form').show();
			    	$("#vehicleMotorcadeDetail").combobox({
			    		url:'../motorcade/findAllMotorcadeUnderTenant.jhtml',
					    valueField:'id',
					    method:"get",
					    textField:'motorcadeDesc',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	$("#vehicleMotorcadeDetail").combobox("setValue",$("#vehicleMotorcadeDetail").attr("data-value"));
			    }
			    
			});   
		},
		onLoadSuccess:function(data){
			$("#totalRecord").val(data.total);
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.vehicle.motorcade"),field:"motorcade",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.motorcadeDesc;
			    	  }
		      	  }},
		      {title:message("ov.vehicle.plate"),field:"plate",sortable:true},
		     
		      {title:message("ov.vehicle.vehicleNo"),field:"vehicleNo",sortable:true},
	      	  {title:message("ov.vehicle.dashboardMileage"),field:"dashboardMileage",sortable:true},
	      	  {title:message("ov.vehicle.vehicleFullBrand"),field:"vehicleFullBrand",sortable:true},
		      {title:message("ov.vehicle.device"),field:"deviceNo",width:100,sortable:false},
		      {title:message("ov.vehicle.plateDate"),field:"plateDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		      {title:message("ov.vehicle.produceDate"),field:"produceDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      }
		   ]
		]
	});

	
	$("#vehicle-search-btn").click(function(){
	  var _queryParams = $("#vehicle-search-form").serializeJSON();
	  $('#vehicle-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicle-table-list").datagrid('reload');
	})
})

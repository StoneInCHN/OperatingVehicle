var clearingRecord_manager_tool = {
	add:function(){
		
		$.ajax({
			url:"../tenantClearingRecord/listBranchBusiness.jhtml",
			type:"post",
			data:"",
			success:function(result,response,status){
				if(response == "success"){
					console.log(result);
					$("#branchBusinessId").combobox("loadData", result);
				}else{
					alertErrorMsg();
				}
			}
		});
		
		$('#addClearingRecord').dialog({
		    title: message("ov.clearingRecord.add"),    
		    width: 600,    
		    height: 400,
		    iconCls:'icon-mini-add',
		    cache: false, 
		    modal:true,
		    buttons:[{
		    	text:message("ov.common.save"),
		    	iconCls:'icon-save',
				handler:function(){
					var validate = $('#addClearingRecord_form').form('validate');
					if(validate){
							$.ajax({
								url:"../tenantClearingRecord/addClearingRecord.jhtml",
								type:"post",
								data:$("#addClearingRecord_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#addClearingRecord').dialog("close")
										$("#addClearingRecord_form").form("reset");
										$("#clearingRecord-table-list").datagrid('reload');
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
					 $('#addClearingRecord').dialog("close");
					 $("#addClearingRecord_form").form("reset");
				}
		    }],
		    onOpen:function(){
		    	$('#addClearingRecord_form').show();
		    },
		});  
	}
};

$(function(){

	$("#clearingRecord-table-list").datagrid({
		title:message("ov.clearingRecord.list"),
		fitColumns:true,
		toolbar:"#clearingRecord_manager_tool",
		url:'../tenantClearingRecord/listClearingRecord.jhtml?childrenOrParent=parent',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
//			$('#useCarRequestDetail').dialog({    
//			    title: message("ov.common.detail"),    
//			    width: 400,    
//			    height: 350, 
//			    cache: false,
//			    modal: true,
//			    href:'../vehicleScheduling/detailsRequest.jhtml?id='+rowData.id,
//			    buttons:[{
//					text:message("ov.common.close"),
//					iconCls:'icon-cancel',
//					handler:function(){
//						 $('#useCarRequestDetail').dialog("close");
//					}
//			    }]
//			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.clearingRecord.clearingSn"),field:"clearingSn",width:100,sortable:true},
		      {title:message("ov.clearingRecord.unitPrice"),field:"unitPrice",width:50,sortable:true},
		      {title:message("ov.clearingRecord.amountOfCurrent"),field:"amountOfCurrent",width:50},
		      {title:message("ov.clearingRecord.reduce"),field:"reduce",width:50},
		      {title:message("ov.clearingRecord.clearingStatus"),field:"clearingStatus",width:30,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "SUCCESS"){
			    		  return  message("ov.clearingRecord.success");
			    	  }else if (value == "FAILURE"){
			    		  return  message("ov.clearingRecord.failure");
			    	  }
		      	  }  
		      },
		   ]
		]
	});
	
	$("#useCarRequest-search-btn").click(function(){
	  var _queryParams = $("#useCarRequest-search-form").serializeJSON();
	  $('#useCarRequest-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#useCarRequest-table-list").datagrid('reload');
	});
	
	$("input:text").focus(function(){
		console.log('selected');
		this.select();
	});
	
	
})

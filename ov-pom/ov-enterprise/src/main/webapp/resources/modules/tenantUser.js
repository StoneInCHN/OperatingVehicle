var tenantUser_manager_tool = {
		add:function(){
			$('#addTenantUser').dialog({
			    title: message("ov.tenantUser.add"),    
			    width: 700,    
			    height: 550,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addTenantUser_form').form('validate');
						var $photoLi = $("#tenantUserUploader-add ul.filelist li");
						
						if(validate){
							if($photoLi.length >0){
								$("#tenantUserUploader-add .uploadBtn").trigger("upload");
							}else{
								$.ajax({
									url:"../tenantUser/add.jhtml",
									type:"post",
									data:$("#addTenantUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addTenantUser').dialog("close");
											$('#addTenantUser_form').form("reset");
											$("#tenantUser-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							}
						};
					}
				},{
					text:message("ov.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addTenantUser').dialog("close").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addTenantUser_form').show();
//			    	$("#tenantUserTenantInfo-add").combotree({    
//					    url: '../tenantInfo/list.jhtml',    
//					    method:"get",
//					    animate:true,
//					    lines:true,
//					    required:true,
//					    prompt:message("ov.common.please.select"),
//					    formatter:function(node){
//					    	node.text = node.tenantName;
//							return node.tenantName;
//						},
//					    onSelect: function(tenantInfo){    
//					    	if(tenantInfo != null){
//						    	$("#tenantUserDepartment-add").combotree({    
//								    url: '../department/listByTenantID.jhtml?tenantID='+tenantInfo.id,    
//								    method:"get",
//								    animate:true,
//								    lines:true,
//								    required:true,
//								    prompt:message("ov.common.please.select"),
//								    formatter:function(node){
//								    	node.text = node.name;
//										return node.name;
//									},
//									onLoadSuccess: function(node, department){
//										if(department.length > 0){
//											$("#tenantUser_department").css('visibility','visible');
//										}else{
//											$("#tenantUser_department").css('visibility','hidden');
//											 $('#tenantUserPosition-add').combobox('clear');
//										}
//									},
//								    onSelect: function(department){    
//							            var url = '../position/findPositions.jhtml?id='+department.id;    
//							            $('#tenantUserPosition-add').combobox('clear');
//							            $('#tenantUserPosition-add').combobox('reload', url);    
//							        }
//								});
//					    	}
//				        }
//					});		
			    	$("#tenantUserDepartment-add").combotree({    
				    url: '../department/list.jhtml',    
				    method:"get",
				    animate:true,
				    lines:true,
				    required:true,
				    prompt:message("ov.common.please.select"),
				    formatter:function(node){
				    	node.text = node.name;
						return node.name;
					},
					onLoadSuccess: function(node, department){
//						if(department.length > 0){
//							$("#tenantUser_department").css('visibility','visible');
//						}else{
//							$("#tenantUser_department").css('visibility','hidden');
//							 $('#tenantUserPosition-add').combobox('clear');
//						}
					},
				    onSelect: function(department){    
			            var url = '../position/findPositions.jhtml?id='+department.id;    
			            $('#tenantUserPosition-add').combobox('clear');
			            $('#tenantUserPosition-add').combobox('reload', url);    
			        }
				});
			    	$("#tenantUserDepartment-add").combotree({    
					    method:"get",
					    animate:true,
					    lines:true,
					    required:true,
					    prompt:message("ov.common.please.select")
					});
			    	$("#tenantUserPosition-add").combobox({    
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select"),
					});
			    	//头像上传
			     	var options ={
			     			createOption:{
			     				pick: {
					                 id: '#tenantUserFilePicker-add',
					                 label: '',
					                 multiple :false
					             },
					             dnd: '#tenantUserUploader-add .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             //缩略图
					             thumb:{
					            	    width: 110,
					            	    height: 110,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					              },
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../file/uploadProfilePhoto.jhtml',
					             fileNumLimit: 1,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
					             //图片裁剪
					             compress:{
					            	 width: 110,
					            	 height: 110,
					            	 // 图片质量，只有type为`image/jpeg`的时候才有效。
					            	 quality: 90,
					            	 // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
					            	 allowMagnify: false,
					            	 // 是否允许裁剪。
					            	 crop: false,
					            	 // 是否保留头部meta信息。
					            	 preserveHeaders: true,
					            	 // 如果发现压缩后文件大小比原来还大，则使用原来图片
					            	 // 此属性可能会影响图片自动纠正功能
					            	 noCompressIfLarger: false,
					            	 // 单位字节，如果图片大小小于此值，不会采用压缩。
					            	 compressSize: 0
					             }
			     			},
			     			//包裹上传组件的div id
			     			warp :"addTenantUser_form",
			     			uploadBeforeSend:function(object, data, headers){
			     				 //在参数中增加一个员工编号字段 staffID
			     				 data.staffID =$("#staffID").val();
			     			},
			     			uploadSuccess:function(file, response){
			     				//将返回的图片路径放到隐藏的input中，用于表单保存
			     				$("#addTenantUser_form_file_input").val(response.content);
			     				$.ajax({
									url:"../tenantUser/add.jhtml",
									type:"post",
									data:$("#addTenantUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("ov.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addTenantUser_form').form('reset');
										$('#addTenantUser').dialog("close");
										$("#tenantUser-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
			     			}
			     	};
			     	
			     	singleUpload(options);
			    },
			    onClose:function(){
			    	$("#tenantUserUploader-add .uploadBtn").trigger("clearFileQuene");
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#tenantUser-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("ov.common.prompt"),message("ov.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#editTenantUser').dialog({    
				title: message("ov.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../tenantUser/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("ov.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editTenantUser_form').form('validate');
						if(validate){
							$.ajax({
								url:"../tenantUser/update.jhtml",
								type:"post",
								data:$("#editTenantUser_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("ov.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editTenantUser').dialog("close");
										$("#tenantUser-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editTenantUser').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
//			    	$("#tenantUserDepartment-edit").combobox({   
//			    		method:"get",
//			    		animate:true,
//					    lines:true,
//					    prompt:message("ov.common.please.select"),
//					    cache: true,
//					    url:'../department/list.jhtml',
//					    formatter:function(node){
//					    	node.text = node.name;
//							return node.name;
//						},
//					    	onLoadSuccess:function(){
//						    	$("#tenantUserDepartment-edit").combobox("setValue",$("#tenantUserDepartment-edit").attr("data-value"))
////					            var url = '../position/findPositions.jhtml?id='+department.id;    
////					            $('#tenantUserPosition-edit').combobox('clear');
////					            $('#tenantUserPosition-edit').combobox('reload', url);    
//						    }
//					});

//			    	$("#tenantUserPosition-edit").combobox({    
//					    valueField:'id',    
//					    textField:'name',
//					    cache: true,
//					    editable : false,
//					    url:'../position/findPositions.jhtml',
//					    onLoadSuccess:function(){
//					    	$("#tenantUserPosition-edit").combobox("setValue",$("#tenantUserPosition-edit").attr("data-value"))
//					    }
//					});
			    	$("#tenantUserDepartment-edit").combotree({    
					    url: '../department/list.jhtml',    
					    method:"get",
					    animate:true,
					    lines:true,
					    required:true,
					    formatter:function(node){
					    	node.text = node.name;
							return node.name;
						},
						onLoadSuccess: function(node, departments){
							var departmentId = $("#tenantUserDepartment-edit").attr("data-value");
							$("#tenantUserDepartment-edit").combotree("setValue",departmentId);
				            var positionId = $("#tenantUserPosition-edit").attr("data-value");
				            var positionName = $("#tenantUserPosition-edit").attr("data-Text");
				            $("#tenantUserPosition-edit").combobox("setValue",positionId);
				            $("#tenantUserPosition-edit").combobox("setText",positionName);
						},
					    onSelect: function(department){    
				            var url = '../position/findPositions.jhtml?id='+department.id;    
				            $('#tenantUserPosition-edit').combobox('clear');
				            $('#tenantUserPosition-edit').combobox('reload', url);    
				        }
					});
			    	$("#tenantUserPosition-edit").combobox({    
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("ov.common.please.select"),
					});
			    	var editOptions ={
			     			createOption:{
			     				pick: {
					                 id: '#tenantUserFilePicker-edit',
					                 innerHTML :'',
					                 multiple :true
					             },
					             dnd: '#tenantUserUploader-edit .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             thumb:{
					            	    width: 150,
					            	    height: 150,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					            	},
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../tenantUser/uploadPhoto.jhtml',
					             fileNumLimit: 100,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
					             compress:{
					            	 width: 110,
					            	    height: 110,
					            	    // 图片质量，只有type为`image/jpeg`的时候才有效。
					            	    quality: 90,
					            	    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
					            	    allowMagnify: false,
					            	    // 是否允许裁剪。
					            	    crop: false,
					            	    // 是否保留头部meta信息。
					            	    preserveHeaders: true,
					            	    // 如果发现压缩后文件大小比原来还大，则使用原来图片
					            	    // 此属性可能会影响图片自动纠正功能
					            	    noCompressIfLarger: false,
					            	    // 单位字节，如果图片大小小于此值，不会采用压缩。
					            	    compressSize: 0
					             }
			     			},
			     			warp :"editTenantUser_form",
			     			uploadImageType:"edit",
			     			addButton:{
			     				id: '#tenantUserFilePicker-edit2',
			     				innerHTML: '替换头像'
			     			},
			     			uploadBeforeSend:function(object, data, headers){
			     				 //
			     				 data.staffID =$("#editTenantUser_form").find("input[name='staffID']").val();
			     				 data.tenantUserId=$("#editTenantUser_form").find("input[name='id']").val();;
			     			}
			     	};
			     	
			     	singleUpload(editOptions);
			     	$("#editTenantUser_form").find(".savePhoto").on("click",function(){
			     		$.messager.confirm('确认','头像保存后将直接修改当前用户的头像，确认要上传吗？',function(res){    
			     		    if (res){
			     		    	$("#tenantUserUploader-edit .uploadBtn").trigger("upload");
			     		    }    
			     		}); 
			     		//alert("保存头像");
			     	})
			    }
			});  
		},
		remove:function(){
			listRemove('tenantUser-table-list','../tenantUser/delete.jhtml');
		}
};

$(function(){	
	$("#tenantUserDepartment-search").combotree({    
		 url: '../department/list.jhtml',    
		 method:"get",
		 animate:true,
		 lines:true,
		 prompt:message("ov.common.please.select"),
	     formatter:function(node){
	    	node.text = node.name;
			return node.name;
		 },
		 onSelect: function(rec){    
	        var url = '../position/findPositions.jhtml?id='+rec.id;    
	        $('#tenantUserPosition-search').combobox('clear');
	        $('#tenantUserPosition-search').combobox('reload', url);    
	     }
		
	});
	
	$("#tenantUserPosition-search").combobox({    
	    valueField:'id',    
	    textField:'name',
	    cache: true,
	    prompt:message("ov.common.please.select")
	});
	$("#tenantUser-table-list").datagrid({
		title:message("ov.tenantUser.list"),
		fitColumns:true,
		toolbar:"#tenantUser_manager_tool",
		url:'../tenantUser/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#tenantUserDetail').dialog({    
			    title: message("ov.common.detail"),    
			    width: 660,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../tenantUser/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("ov.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#tenantUserDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("ov.tenantUser.realName"),field:"realName",width:100,sortable:true},
		      {title:message("ov.common.gender"),field:"gender",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "MALE"){
			    		  return  message("ov.common.male");
			    	  }else if (value = "FEMALE"){
			    		  return  message("ov.common.female");
			    	  }
		      	  }  },
		      {title:message("ov.common.age"),field:"age",width:100,sortable:true},
		      {title:message("ov.tenantUser.staffID"),field:"staffID",width:100,sortable:true},
		      {title:message("ov.tenantUser.staffStatus"),field:"staffStatus",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "INSERVICE"){
			    		  return  message("ov.tenantUser.staffStatus.inService");
			    	  }else if (value = "OUTSERVICE"){
			    		  return  message("ov.tenantUser.staffStatus.outService");
			    	  }
		      	  }  
		      },
	    	  
		      {title:message("ov.tenantUser.department"),field:"department",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:message("ov.tenantUser.position"),field:"position",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:message("ov.tenantUser.hireDate"),field:"hireDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	
	$("#tenantUser-search-btn").click(function(){
	  var _queryParams = $("#tenantUser-search-form").serializeJSON();
	  $('#tenantUser-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#tenantUser-table-list").datagrid('reload');
	})
	
	 
	 
})

<form id="editTenantBranchAdmin_form" method="post">  
	<input type="hidden"  name="id" value= "${tenantAccount.id}"> 
	<input type="hidden"  name="tenantUser.id" value= "${tenantAccount.tenantUser.id}">
	<input type="hidden" id="editTenantUser_form_file_input" name="photo"> 
	<input type="hidden" name="enPassword" value= "${tenantAccount.password}"/>
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantUser.realName")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.realName}" name="tenantUser.realName"  data-options="required:true"/>   
	    		</td>
	    		<th>${message("ov.tenantUser.photo")}:</th>
	    		<td  rowspan="6">
	    			<div title="头像上传" class="easyui-tooltip headWarp">
	    				<div id="tenantUserUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="tenantUserFilePicker-add" ></div>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
	    			</div>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.gender")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'MALE',
				      value: '${message("ov.gender.male")}'
				     },{
				      label: 'FEMALE',
				      value: '${message("ov.gender.female")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  value="${tenantAccount.tenantUser.gender}" name="tenantUser.gender" style="width:110px;" />
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.email")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.email}" name="tenantUser.email"  data-options="required:true"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.staffID")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.staffID}" name="tenantUser.staffID" data-options="required:true"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.staffStatus")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '${message("ov.tenantUser.staffStatus.inService")}'
				     },{
				      label: 'OUTSERVICE',
				      value: '${message("ov.tenantUser.staffStatus.outService")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100" value="${tenantAccount.tenantUser.staffStatus}"  name="tenantUser.staffStatus" style="width:110px;"  />
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.address")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.address}" name="tenantUser.address"  data-options="required:true"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.phoneNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.telephone}" name="tenantUser.telephone" />   
	    		</td>
	    	
	    		<th>${message("ov.mobile")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.mobile}" name="tenantUser.mobile" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.hireDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" value="${tenantAccount.tenantUser.hireDate}" name="tenantUser.hireDate"  />   
	    		</td>
	    		<th>${message("ov.tenantUser.age")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${tenantAccount.tenantUser.age}" name="tenantUser.age"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.IDCard")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.IDCard}" name="tenantUser.IDCard" data-options="required:true"/>
	    		</td>
	    		<th>${message("ov.tenantUser.workingYear")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.workingYear}" name="tenantUser.workingYear" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.birthDay")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" value="${tenantAccount.tenantUser.birthDay}" name="tenantUser.birthDay"  />
	    		</td>
	    		<th>${message("ov.tenantUser.zipCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.zipCode}" name="tenantUser.zipCode"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    </table>
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantAccount.userName")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value="${tenantAccount.userName}" name="userName" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantAccount.accoutStatus")}:</th>
	    		<td colspan='2'>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     required:true,
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("ov.tenantAccount.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("ov.tenantAccount.accoutStatus.locked")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"   value="${tenantAccount.accoutStatus}" name="accoutStatus"  style="width:110px;" />
	    		</td>
	    		<tr>
	    		<th>${message("ov.tenantAccount.password")}:</th>
	    		<td colspan='2'>
	    			 <input type="password" class="easyui-textbox" value="${tenantAccount.password}" name="password"  data-options="required:true"/>
	    		</td>
	    		<th>${message("ov.tenantUser.tenantInfo")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" value = "${tenantAccount.tenantUser.tenantName}" name="tenantName"  readonly="readonly"/>   
	    		</td>
	    	</tr>
	    	</tr>
	    	<tr>
	    </table>
	</form>






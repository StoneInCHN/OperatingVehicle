<form id="addTenantBranchAdmin_form" method="post">  
	<input type="hidden" id="addTenantUser_form_file_input" name="photo"> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantUser.realName")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.realName}" name="realName" disabled="disabled" />   
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
				     }],panelMaxHeight:100"  value="${tenantAccount.tenantUser.gender}" name="gender" style="width:110px;" disabled="disabled"/>
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.email")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.email}" name="email" disabled="disabled" />
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.staffID")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.staffID}" name="staffID"  disabled="disabled"/>   
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
				     }],panelMaxHeight:100" value="${tenantAccount.tenantUser.staffStatus}"  name="staffStatus" style="width:110px;"  disabled="disabled"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.address")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.address}" name="address"  disabled="disabled"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.phoneNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.telephone}" name="telephone" disabled="disabled"/>   
	    		</td>
	    	
	    		<th>${message("ov.mobile")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.mobile}" name="mobile" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.hireDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" value="${tenantAccount.tenantUser.hireDate}" name="hireDate"  disabled="disabled"/>   
	    		</td>
	    		<th>${message("ov.tenantUser.age")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" value="${tenantAccount.tenantUser.age}" name="age" disabled="disabled" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.IDCard")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.IDCard}" name="IDCard" disabled="disabled"/>
	    		</td>
	    		<th>${message("ov.tenantUser.workingYear")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.workingYear}" name="workingYear" disabled="disabled" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.birthDay")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" value="${tenantAccount.tenantUser.birthDay}" name="birthDay"  disabled="disabled"/>
	    		</td>
	    		<th>${message("ov.tenantUser.zipCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${tenantAccount.tenantUser.zipCode}" name="zipCode" disabled="disabled" />
	    		</td>
	    	</tr>
	    </table>
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantAccount.userName")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value="${tenantAccount.userName}" name="userName" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantAccount.accoutStatus")}:</th>
	    		<td colspan='2'>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("ov.tenantAccount.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("ov.tenantAccount.accoutStatus.locked")}'
				     }],panelMaxHeight:100"  value="${tenantAccount.accoutStatus}" name="accoutStatus" style="width:110px;" disabled="disabled"/>
	    		</td>
	    		<tr>
	    		<th>${message("ov.tenantUser.tenantInfo")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" value = "${tenantAccount.tenantUser.tenantName}" name="tenantInfoId" disabled="disabled"/>   
	    		</td>
	    	</tr>
	    	</tr>
	    	<tr>
	    </table>
	</form>






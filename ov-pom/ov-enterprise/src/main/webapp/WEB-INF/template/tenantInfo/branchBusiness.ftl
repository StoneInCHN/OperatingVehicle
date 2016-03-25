<script src="${base}/resources/modules/tenantInfo.js"></script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
			<div class="easyui-panel" style="height:400px;background:#fafafa;" data-options="border:false">
						  <fieldset>
						    <legend>${message("ov.tenantInfo.branch.search")}</legend>
						    <form id="branchBusiness-search-form" class="search-form">
						    	<div class="search-item">
								    <label> ${message("ov.tenantInfo.branch.tenantName")}:</label>
								    <input type="text" class="easyui-textbox" id="tenantNameSearch" name="tenantNameSearch" validtype="length[0,20]"/>
								</div>
								<div class="search-item">
								    <label> ${message("ov.tenantInfo.contactPhone")}:</label>
								    <input type="text" class="easyui-textbox" id="contactPhoneSearch" name="contactPhoneSearch" validtype="length[0,20]"/>
								</div>
								<div class="search-item">
								    <label> ${message("ov.tenantInfo.contactPerson")}:</label>
								    <input type="text" class="easyui-textbox" id="contactPersonSearch" name="contactPersonSearch" validtype="length[0,20]"/>
								</div>
								<div class="search-item">
								    <label> ${message("ov.common.status")}:</label>
								    <input class="easyui-combobox" data-options="
									     valueField: 'label',
									     textField: 'value',
									     data: [{
									      label: 'ACTIVED',
									      value: '${message("ov.tenantAccount.accoutStatus.active")}'
									     },{
									      label: 'LOCKED',
									      value: '${message("ov.tenantAccount.accoutStatus.locked")}'
									     }],
									     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="accountStatusSearch" id="accountStatusSearch" style="width:110px;"/>
								</div>
							</form>
							<div class="search-item">
						  	  <button id="branchBusiness-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
						    </div>
						  </fieldset>
							<table id="branchBusiness-table-list"></table>
							<div id="branchBusiness_manager_tool">
									<div class="tool-button">
											<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="branchBusiness_manager_tool.add();">${message("ov.button.add")}</a>
											<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="branchBusiness_manager_tool.edit();">${message("ov.button.update")}</a>
											<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="branchBusiness_manager_tool.remove();">${message("ov.button.delete")}</a>
									</div>
									<div class="tool-filter"></div>
							</div>
			</div>
			<div class="easyui-panel" style="height:400px;background:#fafafa;" data-options="border:false">
									<fieldset>
								    <legend>${message("ov.tenantInfo.branch.admin.search")}</legend>
								    <form id="tenantBranchAdmin-search-form" class="search-form">
								    	<div class="search-item">
										    <label>${message("ov.tenantInfo.branch.admin.name")}:</label>
										    <input type="text" class="easyui-textbox" id="adminNameSearch" name="adminNameSearch" validtype="length[0,20]"/>
										</div>
										<div class="search-item">
										    <label>${message("ov.tenantInfo.branch.admin.status")}:</label>
										    <input class="easyui-combobox" data-options="
											     valueField: 'label',
											     textField: 'value',
											     data: [{
											      label: 'ACTIVED',
											      value: '${message("ov.tenantAccount.accoutStatus.active")}'
											     },{
											      label: 'LOCKED',
											      value: '${message("ov.tenantAccount.accoutStatus.locked")}'
											     }],
											     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="adminAccountStatusSearch" id="adminAccountStatusSearch" style="width:110px;"/>
										</div>
									</form>
									<div class="search-item">
								  	  <button id="tenantBranchAdmin-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
								    </div>
								  </fieldset>
									<table id="tenantBranchAdmin-table-list"></table>
									<div id="tenantBranchAdmin_manager_tool">
											<div class="tool-button">
													<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantBranchAdmin_manager_tool.add();">${message("ov.button.add")}</a>
													<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="tenantBranchAdmin_manager_tool.edit();">${message("ov.button.update")}</a>
											</div>
											<div class="tool-filter"></div>
									</div>
			</div>
	</div>
</div>


<div id="addBranchBusiness"> 
	<form id="addBranchBusiness_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantInfo.tenantName")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="tenantName" id= "tenantName"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.contactPhone")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="contactPhone" id= "contactPhone"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.contactPerson")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="contactPerson" id= "contactPerson"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.email")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="email" id= "email"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.zipCode")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="zipCode" id= "zipCode" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.address")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="address" id= "address" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.remark")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="remark" id= "remark"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.accountStatus")}:</th>
	    		<td colspan="2">
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("ov.tenantAccount.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("ov.tenantAccount.accoutStatus.locked")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100" id="editAccountStatus" value="ACTIVED" name="accountStatus" style="width:110px;"/>
				     
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editBranchBusiness"></div>
<div id="branchBusinessDetail"></div>

<div id="addTenantBranchAdmin">
<form id="addTenantBranchAdmin_form" method="post" class="form-table">  
	<input type="hidden" id="addTenantUser_form_file_input" name="photo"> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantUser.realName")} :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "realName" name="realName" data-options="required:true" />   
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
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="gender" style="width:110px;"/>
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.email")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="email" data-options="required:true" />
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.staffID")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="staffID" data-options="required:true" validtype="length[0,30]"/>   
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
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="staffStatus" style="width:110px;"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.address")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="address" data-options="required:true" validtype="length[0,200]" />   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("ov.phoneNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="telephone" />   
	    		</td>
	    	
	    		<th>${message("ov.mobile")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="mobile" data-options="required:true" validtype="mobile"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.hireDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="hireDate"  data-options="required:true,editable:false"/>   
	    		</td>
	    		<th>${message("ov.tenantUser.age")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="age" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.IDCard")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="IDCard" data-options="required:true" validtype="length[0,30]"/>
	    		</td>
	    		<th>${message("ov.tenantUser.workingYear")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="workingYear" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantUser.birthDay")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="birthDay"  data-options="required:true,editable:false"/>
	    		</td>
	    		<th>${message("ov.tenantUser.zipCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="zipCode" data-options="required:true" validtype="length[0,20]"/>
	    		</td>
	    	</tr>
	    </table>
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantAccount.userName")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="userName" id= "userName"  data-options="required:true"/>
	    		</td>
	    		<th>${message("ov.tenantAccount.password")}:</th>
	    		<td colspan='2'>
	    			 <input type="password" class="easyui-textbox" name="password" id= "password"  data-options="required:true"/>
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
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="accoutStatus" style="width:110px;"/>
	    		</td>
	    		<tr>
	    		<th>${message("ov.tenantUser.tenantInfo")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="addTenantBranchAdmin_selectTenant" name="tenantInfoId" />   
	    		</td>
	    	</tr>
	    	</tr>
	    	<tr>
	    </table>
	</form>
</div>
<div id="editTenantBranchAdmin"></div>
<div id="viewTenantBranchAdmin"></div>





<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantUser.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("ov.tenantUser.search")}</legend>
	    <form id="tenantUser-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.tenantUser.search.realName")}:</label>
			    <input type="text" class="easyui-textbox" id="realName" name="realNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.tenantUser.search.department")}:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserDepartment-search" name="departmentSearchId" data-options="prompt:'${message("ov.common.please.select")}'"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.tenantUser.search.position")}:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserPosition-search" name="positionSearchId"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.tenantUser.staffStatus")}:</label>
			    
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
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="staffStatusSearch" style="width:110px;"/>
			    
			</div>
		</form>
		<div class="search-item">
	  	  <button id="tenantUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="tenantUser-table-list"></table>
<div id="tenantUser_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantUser_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="tenantUser_manager_tool.edit();">${message("ov.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="tenantUser_manager_tool.remove();">${message("ov.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addTenantUser"> 
	<form id="addTenantUser_form" method="post" class="form-table">  
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
	    		<th>${message("ov.tenantUser.tenantInfo")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="tenantUserTenantInfo-add" name="tenantInfoId" />   
	    		</td>
	    	</tr>
	    	<tr id="tenantUser_department" style="visibility:hidden">
	    		<th>${message("ov.tenantUser.department")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="tenantUserDepartment-add" name="departmentId" />   
	    		</td>
	    	
	    		<th>${message("ov.tenantUser.position")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" type="text" id="tenantUserPosition-add" name="positionId" />
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
	</form>
</div>
<div id="editTenantUser"></div>
<div id="tenantUserDetail"></div>





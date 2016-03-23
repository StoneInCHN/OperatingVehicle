<script src="${base}/resources/modules/tenantInfo.js"></script>
<div>
	  <fieldset>
	    <legend>${message("ov.tenantInfo.search")}</legend>
	    <form id="branchBusiness-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.tenantInfo.tenantName")}:</label>
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
			    <label> ${message("ov.tenantInfo.accountStatus")}:</label>
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
</div>
<table id="branchBusiness-table-list"></table>
<div id="branchBusiness_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="branchBusiness_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="branchBusiness_manager_tool.edit();">${message("ov.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="branchBusiness_manager_tool.remove();">${message("ov.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
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





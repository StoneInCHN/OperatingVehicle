<form id="editBranchBusiness_form" method="post">   
		<input type="hidden" name="id" value= "${tenantInfo.id}"/> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.tenantInfo.tenantName")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" value="${tenantInfo.tenantName}" name="tenantName" id= "tenantName"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.contactPhone")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="contactPhone" value="${tenantInfo.contactPhone}" id= "contactPhone"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.contactPerson")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="contactPerson" value="${tenantInfo.contactPerson}" id= "contactPerson"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.email")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="email" value="${tenantInfo.email}" id= "email"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.zipCode")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" value="${tenantInfo.zipCode}" name="zipCode" id= "zipCode" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.remark")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="remark" value="${tenantInfo.remark}" id= "remark"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.tenantInfo.accoutStatus")}:</th>
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
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100" id="editAccountStatus"  name="accountStatus" style="width:110px;" data-value="${tenantInfo.accountStatus}"/>
				     
	    		</td>
	    	</tr>
	    </table>
</form>




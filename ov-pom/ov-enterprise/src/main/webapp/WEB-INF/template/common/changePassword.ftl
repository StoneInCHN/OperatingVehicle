 <form id="changePassword_form" method="post" >
 <input type="hidden" id="newEnPassword">
	<table class="table table-striped">
	<tr>
		<th>${message("ov.tenantAccount.oldPassword")}:</th>
		<td >
			 <input type="password" class="easyui-textbox" name="oldPassword" data-options="required:true"/>
		</td>
	</tr>
	<tr>
		<th>${message("ov.tenantAccount.newPassword")}:</th>
		<td>
			 <input id="newPassword" type="password" class="easyui-textbox" name="newPassword" 
			 	data-options="required:true" validType="minLength[6]" />
		</td>
	</tr>
	<tr>
		<th>${message("ov.tenantAccount.confirmPassword")}:</th>
		<td>
			 <input type="password" class="easyui-textbox" name="confirmPassword"  
			 	data-options="required:true" validType="passwordEequals['#newPassword']" />
		</td>
	</tr>
</form>





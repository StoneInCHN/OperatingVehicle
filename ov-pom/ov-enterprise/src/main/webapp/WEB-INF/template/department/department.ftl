<script type="text/javascript"  src="${base}/resources/modules/department.js"></script>
<table id="department-table-list"></table>
<div id="addDepartment">
	<form id="addDepartment_form" method="post" class="form-table">
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("ov.tenantUser.tenantInfo")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="addDepartment_tenantInfo" name="tenantID" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.department.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.department.parent")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="addDepartment_parentName" name="parentId" type="text" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDepartment"></div>  
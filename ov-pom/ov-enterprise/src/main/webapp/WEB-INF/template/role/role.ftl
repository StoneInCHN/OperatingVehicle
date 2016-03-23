<script src="${base}/resources/modules/role.js"></script>
<div>
	  <fieldset>
	    <legend>角色查询</legend>
	    <form id="role_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 角色:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name_roleSearch" validtype="length[0,20]" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate_roleSearch" name="beginDate_roleSearch"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate_roleSearch\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate_roleSearch"  name="endDate_roleSearch" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate_roleSearch\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="role_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="role-table-list"></table>
<div id="role_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="role_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="role_manager_tool.edit();">${message("ov.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="role_manager_tool.remove();">${message("ov.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addRole">
	<form id="addRole_form" method="post" class="form-table">  
	   <table class="table table-striped">
	    	<tr>
	    		<th>${message("ov.role.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" id="addRoleName" name="name" validtype="length[0,150]" data-options="required:true,multiline:true,height:90,width:260"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.role.description")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="description" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editRole"></div> 






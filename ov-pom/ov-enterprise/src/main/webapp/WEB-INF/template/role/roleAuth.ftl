<script src="${base}/resources/modules/roleAuth.js"></script>
<table id="role-auth-table-list"></table>
<div id="role_auth_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-man" plain=true onclick="role_auth_manager_tool.auth();">${message("ov.role.auth")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="role-dialog-auth">
	<ul id="roleTreeAuth" class="easyui-tree" checkbox="true"></ul>  
</div>







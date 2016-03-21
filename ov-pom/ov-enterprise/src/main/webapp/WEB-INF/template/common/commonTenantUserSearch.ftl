<div>
	  <fieldset>
	    <legend>${message("ov.tenantUser.search")}</legend>
	    <form id="common-tenantUser-search-form" class="search-form">
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
	  	  <button id="common-tenantUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="common-tenantUser-table-list"></table>





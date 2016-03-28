<script src="${base}/resources/modules/vehicleAssign.js"></script>

<div>
	  <fieldset>
	    <legend>${message("ov.useCarRequest.search")}</legend>
	    <form id="useCarRequest-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.useCarRequest.title")}:</label>
			    <input type="text" class="easyui-textbox" id="titleSearch" name="titlesSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.useCarRequest.startPositionDetails")}:</label>
			    <input type="text" class="easyui-textbox" id="startPositionDetailsSearch" name="startPositionDetailsSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.useCarRequest.endPositionDetails")}:</label>
			    <input type="text" class="easyui-textbox" id="endPositionDetailsSearch" name="endPositionDetailsSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.useCarRequest.status")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'TO_CONFIRM',
				      value: '${message("ov.useCarRequest.to_confirm")}'
				     },{
				      label: 'DISTRIBUTED',
				      value: '${message("ov.useCarRequest.distributed")}'
				     },{
				      label: 'FINISHED',
				      value: '${message("ov.useCarRequest.finished")}'
				     },{
				      label: 'CANCELLED',
				      value: '${message("ov.useCarRequest.cancelled")}'
				     },{
				      label: 'REJECTED',
				      value: '${message("ov.useCarRequest.rejected")}'
				     },{
				      label: 'BREAK_CONTRACT',
				      value: '${message("ov.useCarRequest.break_contract")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="statusSearch" id="statusSearch" style="width:110px;"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="useCarRequest-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="useCarRequest-table-list"></table>
<div id="vehicleAssign_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicleAssign_manager_tool.assign();">${message("ov.button.assign")}</a>
	</div>
	<div class="tool-filter"></div>
</div>

<div id="assignUseCarRequest"></div>
<div id="useCarRequestDetail"></div>




<style>
.add_request_table tr td input{
	width: 400px;
}
.tangram-suggestion-main {
	z-index:9999;
}
</style>

<script src="${base}/resources/modules/vehicleScheduling.js"></script>

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
<div id="useCarRequest_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="useCarRequest_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="useCarRequest_manager_tool.edit();">${message("ov.button.update")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addUseCarRequest"> 
	<form id="addUseCarRequest_form" method="post" class="form-table">
	    <table class="table table-striped add_request_table"  border="0">
	    	<tr>
	    		<th>${message("ov.useCarRequest.title")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="title" id= "title"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.useCarRequest.startDate")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-datetimebox" name="startDate" id= "startDate"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.useCarRequest.personNum")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="personNum" id= "personNum"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.useCarRequest.startPositionDetails")}:</th>
	    		<td colspan="2">
	    			<input type="text" class="easyui-textbox" name="startPositionDetails" id= "startPositionDetails"  data-options="required:true"/>
	    			<input type="button" class="easyui-linkbutton" value="${message("ov.select")}" id="startPositionButton" style="width: 60px"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.useCarRequest.endPositionDetails")}:</th>
	    		<td colspan="2">
	    			<input type="text" class="easyui-textbox" name="endPositionDetails" id= "endPositionDetails" data-options="required:true" />
	    			<input type="button" class="easyui-linkbutton" value="${message("ov.select")}" id="endPositionButton" style="width: 60px"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.useCarRequest.remark")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="remark" id= "remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:400" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editUseCarRequest"></div>
<div id="useCarRequestDetail"></div>
<div id="mapContainer"></div>




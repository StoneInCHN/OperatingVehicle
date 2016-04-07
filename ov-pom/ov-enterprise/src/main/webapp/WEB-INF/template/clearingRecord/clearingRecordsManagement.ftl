
<script src="${base}/resources/modules/clearingRecord.js"></script>

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
<table id="clearingRecord-table-list"></table>
<div id="clearingRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="clearingRecord_manager_tool.add();">${message("ov.button.add")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addClearingRecord"> 
	<form id="addClearingRecord_form" method="post" class="form-table">
	    <table class="table table-striped add_request_table"  border="0">
	    	<tr>
	    		<th>${message("ov.clearingRecord.branchBusiness")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-combobox" name="branchBusinessId" id= "branchBusinessId"  data-options="required:true, valueField:'id', textField:'tenantName'"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.clearingRecord.dateRange")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-datetimebox" name="startDate" id= "startDate"  />
	    			 至
	    			 <input type="text" class="easyui-datetimebox" name="endDate" id= "endDate"  />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.clearingRecord.unitPrice")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="unitPrice" id= "unitPrice"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.clearingRecord.reduce")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="reduce" id= "reduce"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.clearingRecord.comments")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="comments" id= "comments" validtype="length[0,150]" data-options="multiline:true,height:90,width:400" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="clearingRecordDetail"></div>


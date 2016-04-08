
<script src="${base}/resources/modules/clearingRecord.js"></script>

<div>
	  <fieldset>
	    <legend>${message("ov.clearingRecord.search")}</legend>
	    <form id="clearingRecord-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.clearingRecord.clearingSn")}:</label>
			    <input type="text" class="easyui-textbox" id="snSearch" name="snSearch" validtype="length[0, 30]"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="clearingRecord-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
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


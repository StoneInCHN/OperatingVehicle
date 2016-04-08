
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
		
	</div>
	<div class="tool-filter"></div>
</div>

<div id="clearingRecordDetail"></div>


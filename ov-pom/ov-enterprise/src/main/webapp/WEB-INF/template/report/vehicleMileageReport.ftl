<script src="${base}/resources/modules/vehicleMileageReport.js"></script>
<div>
	  <fieldset>
	  	<legend>条件查询</legend>
	    <form id="vehicleMileageReport_search_form" class="search-form">
			<div class="search-item">
			    <label> 筛选时间:</label>
			    <input type="text" class="Wdate" id="vehicleMileageReport_beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'vehicleMileageReport_endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("ov.to")}:</label>
			   	<input type="text" class="Wdate" id="vehicleMileageReport_endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'vehicleMileageReport_beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="vehicleMileageReport_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="vehicleMileageReport">
	<div id="vehicleMileageReportDivId" style="height:400px;width: 880px;">
	</div>
	<table id = "vehicleMileageReport-table-list" class="table table-striped" >   
	</table>
</div>
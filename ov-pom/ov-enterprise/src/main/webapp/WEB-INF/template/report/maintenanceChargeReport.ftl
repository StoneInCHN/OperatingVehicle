<script src="${base}/resources/modules/maintenanceChargeReport.js"></script>
<div>
	  <fieldset>
	    <form id="maintenanceChargeReport_search_form" class="search-form">
			<div class="search-item">
			    <label> 筛选时间:</label>
			    <input type="text" class="Wdate" id="maintenanceChargeReport_beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'maintenanceChargeReport_endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("ov.to")}:</label>
			   	<input type="text" class="Wdate" id="maintenanceChargeReport_endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'maintenanceChargeReport_beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="maintenanceChargeReport_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="maintenanceChargeReport">
	<div id="maintenanceChargeReportDivId" style="height:400px;width: 880px;">
	</div>
	<table id = "maintenanceChargeReport-table-list" class="table table-striped" >   
	</table>
</div>
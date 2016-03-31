<script src="${base}/resources/modules/upkeepChargeReport.js"></script>
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:250px" title="车辆查询">
			<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="upkeepCharge_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>车牌:</label>
						   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="vehiclePlateSearch" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="upkeepCharge_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("ov.search")}</button>
				    </div>
				</fieldset>
                <table id="upkeepChargeVehicleSearch-table-list"></table>
		</div>
		<div data-options="region:'center'" >
				<div class="easyui-panel" style="height:800px;background:#fafafa;" data-options="border:false">
						  <fieldset>
						  	<legend>条件查询</legend>
						    <form id="upkeepChargeReport_search_form" class="search-form">
								<div class="search-item">
								    <label> 筛选时间:</label>
								    <input type="text" class="Wdate" id="upkeepChargeReport_beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'upkeepChargeReport_endDate\')}'});" />
								</div>
								<div class="search-item">
								    <label>${message("ov.to")}:</label>
								   	<input type="text" class="Wdate" id="upkeepChargeReport_endDate"  name="endDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'upkeepChargeReport_beginDate\')}'});"/>
								</div>
								<input type="hidden" name="vehicleID" id="upkeepCharge_vehicleID" value="1">
							</form>
							<div class="search-item">
						  	  <button id="upkeepChargeReport_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
						    </div>
						  </fieldset>
						<div id="upkeepChargeReport">
							<div id="upkeepChargeReportDivId" style="height:400px;width:99%;">
							</div>
							<table id = "upkeepChargeReport-table-list" class="table table-striped" >   
							</table>
						</div>
				</div>
		</div>
</div>
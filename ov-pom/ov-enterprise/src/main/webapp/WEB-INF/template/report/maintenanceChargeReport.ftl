<script src="${base}/resources/modules/maintenanceChargeReport.js"></script>
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:250px" title="车辆查询">
			<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="maintenanceCharge_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>车牌:</label>
						   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="vehiclePlateSearch" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="maintenanceCharge_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("ov.search")}</button>
				    </div>
				</fieldset>
                <table id="maintenanceChargeVehicleSearch-table-list"></table>
		</div>
		<div data-options="region:'center'" >
				<div class="easyui-panel" style="height:800px;background:#fafafa;" data-options="border:false">
						  <fieldset>
							  	<legend>条件查询</legend>
							    <form id="maintenanceChargeReport_search_form" class="search-form">
									<div class="search-item">
									    <label> 筛选时间:</label>
									    <input type="text" class="Wdate" id="maintenanceChargeReport_beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'maintenanceChargeReport_endDate\')}'});" />
									</div>
									<div class="search-item">
									    <label>${message("ov.to")}:</label>
									   	<input type="text" class="Wdate" id="maintenanceChargeReport_endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'maintenanceChargeReport_beginDate\')}'});"/>
									</div>
									<input type="hidden" name="vehicleID" id="maintenanceCharge_vehicleID">
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
				</div>
		</div>
</div>
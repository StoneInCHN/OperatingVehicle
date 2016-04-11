<script src="${base}/resources/modules/vehicleMileageReport.js"></script>
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:250px" title="车辆查询">
			<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="vehicleMileage_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>车牌:</label>
						   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="vehiclePlateSearch" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="vehicleMileage_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("ov.search")}</button>
				    </div>
				</fieldset>
                <table id="vehicleMileageVehicleSearch-table-list"></table>
		</div>
		<div data-options="region:'center'" >
				<div class="easyui-panel" style="height:800px;background:#fafafa;" data-options="border:false">
						  <fieldset>
								  	<legend>条件查询</legend>
								    <form id="vehicleMileageReport_search_form" class="search-form">
										<div class="search-item">
										    <label> 选择月份:</label>
										    <input class="easyui-combobox" data-options="valueField: 'label',textField: 'value',
												data: [{label: 1,value: '1'},{label:2,value: 2},{label:3,value: 3},{label:4,value: 4},
														{label:5,value: 5},{label:6,value: 6},{label:7,value: 7},{label:8,value: 8}
														,{label:9,value: 9},{label:10,value: 10},{label:11,value: 11},{label:12,value: 12}],
														prompt:'${message("ov.common.please.select")}',panelMaxHeight:200"  id="vehicleStatusMonthID"  style="width:100px;"/>
										</div>										
										<input type="hidden" name="vehicleID" id="vehicleMileage_deviceNo" value="1">
									</form>
									<div class="search-item">
								  	  <button id="vehicleMileageReport_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
								    </div>
								  </fieldset>
							<div id="vehicleMileageReport">
								<div class="topTabNav">
									<ul class="topTabNavBox">
										<li><a  href="javascript:void(0);" id = "report_dailyMileageID" class="topTabActive"><span>行驶里程</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_averageFuelConsumptionID"><span>平均油耗</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_fuelConsumptionID"><span>油耗量</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_costID"><span>油费金额</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_averageSpeedID"><span>平均速度</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_emergencybrakecountID"><span>驾驶行为</span></a></li>
									</ul>
								</div>									
								<div id="vehicleMileageReportDivId" style="height:400px;width:99%;">
								</div>
								<table id = "vehicleMileageReport-table-list" class="table table-striped" >   
								</table>
						</div>
				</div>
		</div>
</div>
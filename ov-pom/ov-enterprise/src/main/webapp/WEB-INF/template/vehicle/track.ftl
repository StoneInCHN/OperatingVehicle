<script src="${base}/resources/modules/vehicleTrack.js"></script>
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:250px" title="${message("ov.vehicle.search")}">
			<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="track_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>${message("ov.vehicle.plate")}:</label>
						   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="vehiclePlateSearch" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="track_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("ov.search")}</button>
				    </div>
				</fieldset>
                <table id="trackVehicleSearch-table-list"></table>
		</div>
		<div data-options="region:'center'" >
				<!-- 按天查询车辆轨迹分段 模块start-->
				<div class="easyui-panel" title="按天查询" style="height:50%;background:#fafafa;" data-options="border:false">
						        <fieldset>
								  	<legend>${message("ov.common.condition.search")}</legend>
								    <form id="track_search_form" class="search-form">
										<div class="search-item">
											<label> 选择日期(天):</label>
										    <input type="text" class="Wdate" id="trackSearchDate" name="searchDate" readonly="readonly" onclick="WdatePicker({});" />
										</div>
										<input type="hidden" name="vehicleID" id="track_vehicleID">
									</form>
									<div class="search-item">
								  	  <button id="track_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
								    </div>
								  </fieldset>
								<div id="vehicleTrack" style="height:50%">
									<table id="track-table-list"></table>
									<div id="vehicleTrackMap"/>
								</div>
				</div>
				<!-- 按天查询车辆轨迹分段 模块end-->
				<!-- 按时间段查询车辆轨迹 模块start-->
				<div  class="easyui-panel" title="按时间段查询"  style="height:50%;background:#fafafa;"  data-options="border:false">
								<fieldset>
								  	<legend>${message("ov.common.condition.search")}</legend>
								    <form id="track_byTime_search_form" class="search-form">
										<div class="search-item">
											<label> 开始时间:</label>
										    <input type="text" class="easyui-datetimebox" name="startDate"  data-options="required:true" style="width:150px"></input>
										</div>
										<div class="search-item">
											<label> 结束时间:</label>
										    <input type="text" class="easyui-datetimebox" name="endDate"  data-options="required:true" style="width:150px"></input>
										</div>
										<input type="hidden" name="vehicleID" id="track_byTime_vehicleID">
									</form>
									<div class="search-item">
								  	  <button id="track_byTime_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
								    </div>
								  </fieldset>
								<div id="vehicleTrackByTime" style="height:50%">
									<table id="track-byTime-table-list"></table>
									<div id="vehicleTrackByTimeMap"/>
								</div>
				</div>
				<!-- 按时间段查询车辆轨迹 模块end-->
		</div>
</div>
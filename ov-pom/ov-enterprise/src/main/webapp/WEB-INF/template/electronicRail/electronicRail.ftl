<script src="${base}/resources/modules/electronicRail.js"></script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true" style="width:250px" title="车辆查询">
		<fieldset style="padding:5px 0px 4px 4px;margin:0px">
	        <form id="electronicRail_vehicle_search_form" class="search-form">
				<div class="search-item">
				   <label>${message("ov.vehicle.plate")}:</label>
				   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="vehiclePlateSearch" validtype="length[0,10]" style="width:60px;"/>
				</div>
			</form>
			<div class="search-item">
		  	  <button id="electronicRail_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("ov.search")}</button>
		    </div>
		</fieldset>
        <table id="electronicRailVehicleSearch-table-list"></table>
	</div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" style="height:700px;background:#fafafa;" data-options="border:false">
		  	<fieldset>
			  	<legend></legend>
				<div class="search-item">
			  	  <button id="electronicRail_add_btn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">${message("ov.button.add")}</button>
			  	  <button id="electronicRail_ok_btn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">${message("ov.button.ok")}</button>
			    </div>
			  </fieldset>
			<div id="mapContainer" style="height:660px; width:100%;"></div>
		</div>
	</div>
</div>

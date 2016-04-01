<script src="${base}/resources/modules/assignVehicleView.js"></script>

<div>
  <fieldset>
    <legend>${message("ov.vehicle.search")}</legend>
    <form id="vehicle-search-form" class="search-form">
    	<!--
    	<div class="search-item">
		    <label> ${message("ov.vehicle.motorcade")}:</label>
		    <input type="text" class="easyui-textbox" id="motorcadeSearch" name="motorcadeSearch" validtype="length[0,20]"/>
		</div>
		-->
		<div class="search-item">
		    <label> ${message("ov.vehicle.plate")}:</label>
		    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
		</div>
	</form>
	<div class="search-item">
  	  <button id="vehicle-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
    </div>
  </fieldset>
</div>
<table id="vehicle-table-list"></table>
<div id="vehicle_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="vehicle_manager_tool.add();">${message("ov.button.add")}</a>
	</div>
	<div class="tool-filter"></div>
</div>

<div id="vehicleDetails"></div>

<form id="assignVehicleView_form" method="post">   
	<input type="hidden" name="vehicleSchedulingId" value= "${vehicleSchedulingId}" id="vehicleSchedulingId"/>
	
	<table id="selected_vehicle_table"></table>
	<div id="selected_manager_tool">
		<div class="tool-button">
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="selected_manager_tool.remove();">${message("ov.button.delete")}</a>
		</div>
		<div class="tool-filter"></div>
	</div>

</form>


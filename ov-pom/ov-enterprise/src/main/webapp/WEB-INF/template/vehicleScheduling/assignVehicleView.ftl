<div>
  <fieldset>
    <legend>${message("ov.vehicle.search")}</legend>
    <form id="vehicle-search-form" class="search-form">
    	<div class="search-item">
		    <label> ${message("ov.vehicle.motorcade")}:</label>
		    <input type="text" class="easyui-textbox" id="titleSearch" name="titlesSearch" validtype="length[0,20]"/>
		</div>
		<div class="search-item">
		    <label> ${message("ov.vehicle.plate")}:</label>
		    <input type="text" class="easyui-textbox" id="plate" name="plate" validtype="length[0,20]"/>
		</div>
	</form>
	<div class="search-item">
  	  <button id="useCarRequest-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
    </div>
  </fieldset>
</div>
<table id="useCarRequest-table-list"></table>
<div id="vehicle_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.add();">${message("ov.button.add")}</a>
	</div>
	<div class="tool-filter"></div>
</div>

<div id="vehicleDetails"></div>




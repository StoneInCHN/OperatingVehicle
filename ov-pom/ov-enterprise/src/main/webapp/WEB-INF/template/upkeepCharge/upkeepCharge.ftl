<script src="${base}/resources/modules/upkeepCharge.js"></script>
<div>
	  <fieldset>
	    <legend>${message("ov.upkeepCharge.search")}</legend>
	    <form id="upkeepCharge-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="vehiclePlateSearch_upkeepAdd" name="vehiclePlateSearch" validtype="length[0,20]"/>
			</div>		
			<div class="search-item">
				    <label>${message("ov.upkeepCharge.dateFrom")}:</label>
				    <input type="text" class="Wdate" id="upkeepCharge_beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'upkeepCharge_endDate\')}'});" />
				</div>
				<div class="search-item">
				    <label>${message("ov.to")}:</label>
				   	<input type="text" class="Wdate" id="upkeepCharge_endDate"  name="endDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'upkeepCharge_beginDate\')}'});"/>
			</div>	
		</form>
		<div class="search-item">
	  	  <button id="upkeepCharge-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="upkeepCharge-table-list"></table>
<div id="upkeepCharge_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="upkeepCharge_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="upkeepCharge_manager_tool.edit();">${message("ov.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="upkeepCharge_manager_tool.remove();">${message("ov.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addUpkeepCharge"> 
	<form id="addUpkeepCharge_form" method="post" class="form-table">
		<input type="hidden"name="vehicleID" id= "vehicle_upkeepAddID" />
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.upkeepCharge.select.vehicle")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox"  id= "vehicle_upkeepAdd"   data-options="required:true"/>
	    			<a href="#" id="vehicle_search_btn" class="easyui-linkbutton" onclick="searchVehicle('vehicle_upkeepAdd')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.date")}:</th>
	    		<td >
	    			<input type="text" class="easyui-datebox"  name="upkeepDate" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.amount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="upkeepAmount" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.upkeepCompany")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="upkeepCompany" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" name="remark"   validtype="length[0,150]" data-options="multiline:true,height:110,width:280"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editUpkeepCharge"></div>
<div id="upkeepChargeDetail"></div>





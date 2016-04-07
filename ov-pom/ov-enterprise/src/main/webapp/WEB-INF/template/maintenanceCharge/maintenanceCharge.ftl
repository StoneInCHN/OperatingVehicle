<script src="${base}/resources/modules/maintenanceCharge.js"></script>
<div>
	  <fieldset>
	    <legend>${message("ov.maintenanceCharge.search")}</legend>
	    <form id="maintenanceCharge-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="vehiclePlateSearch_maintenanceAdd" name="vehiclePlateSearch" validtype="length[0,20]"/>
			</div>		
			<div class="search-item">
				    <label>${message("ov.maintenanceCharge.dateFrom")}:</label>
				    <input type="text" class="Wdate" id="maintenanceCharge_beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'maintenanceCharge_endDate\')}'});" />
				</div>
				<div class="search-item">
				    <label>${message("ov.to")}:</label>
				   	<input type="text" class="Wdate" id="maintenanceCharge_endDate"  name="endDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'maintenanceCharge_beginDate\')}'});"/>
			</div>	
		</form>
		<div class="search-item">
	  	  <button id="maintenanceCharge-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="maintenanceCharge-table-list"></table>
<div id="maintenanceCharge_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="maintenanceCharge_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="maintenanceCharge_manager_tool.edit();">${message("ov.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="maintenanceCharge_manager_tool.remove();">${message("ov.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addMaintenanceCharge"> 
	<form id="addMaintenanceCharge_form" method="post" class="form-table">
		<input type="hidden"name="vehicleID" id= "vehicle_maintenanceAddID" />
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.select.vehicle")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox"  id= "vehicle_maintenanceAdd"   data-options="required:true"/>
	    			<a href="#" id="vehicle_search_btn" class="easyui-linkbutton" onclick="searchVehicle('vehicle_maintenanceAdd')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.date")}:</th>
	    		<td >
	    			<input type="text" class="easyui-datebox"  name="maintenanceDate" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.amount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="maintenanceAmount" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.mileage")}:</th>
	    		<td >
	    		<input type="text"  class="easyui-numberbox" name="maintenanceMileage" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.maintenanceCompany")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="maintenanceCompany" validtype="length[0,50]"/>
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
<div id="editMaintenanceCharge"></div>
<div id="maintenanceChargeDetail"></div>





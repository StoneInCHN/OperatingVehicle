<script src="${base}/resources/modules/oilCharge.js"></script>
<div>
	  <fieldset>
	    <legend>${message("ov.oilCharge.search")}</legend>
	    <form id="oilCharge-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="vehiclePlateSearch_oilAdd" name="vehiclePlateSearch" validtype="length[0,20]"/>
			</div>		
			<div class="search-item">
				    <label>${message("ov.oilCharge.dateFrom")}:</label>
				    <input type="text" class="Wdate" id="oilCharge_beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'oilCharge_endDate\')}'});" />
				</div>
				<div class="search-item">
				    <label>${message("ov.to")}:</label>
				   	<input type="text" class="Wdate" id="oilCharge_endDate"  name="endDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'oilCharge_beginDate\')}'});"/>
			</div>	
		</form>
		<div class="search-item">
	  	  <button id="oilCharge-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("ov.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="oilCharge-table-list"></table>
<div id="oilCharge_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="oilCharge_manager_tool.add();">${message("ov.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="oilCharge_manager_tool.edit();">${message("ov.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="oilCharge_manager_tool.remove();">${message("ov.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addOilCharge"> 
	<form id="addOilCharge_form" method="post" class="form-table">
		<input type="hidden"name="vehicleID" id= "vehicle_oilAddID" />
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.oilCharge.select.vehicle")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox"  id= "vehicle_oilAdd"   data-options="required:true"/>
	    			<a href="#" id="vehicle_search_btn" class="easyui-linkbutton" onclick="searchVehicle('vehicle_oilAdd')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.date")}:</th>
	    		<td >
	    			<input type="text" class="easyui-datebox"  name="oilDate" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.amount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilAmount" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.discountAmount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="discountAmount" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.finalAmount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilFinalAmount" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>	 
	    	<tr>
	    		<th>${message("ov.oilCharge.oilCount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilCount" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>	
	    	<tr>
	    		<th>${message("ov.oilCharge.oilPrice")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilPrice" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>	 
	    	<tr>
	    		<th>${message("ov.oilCharge.oilLabel")}:</th>
	    		<td >
	    			<input type="text" class="easyui-textbox"  name= "oilLabel"   data-options="required:true"/>   
	    		</td>
	    	</tr>	 	
	    	<tr>
	    		<th>${message("ov.oilCharge.invoiceNumber")}:</th>
	    		<td >
	    			<input type="text" class="easyui-textbox"  name= "invoiceNumber"/>   
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
<div id="editOilCharge"></div>
<div id="oilChargeDetail"></div>





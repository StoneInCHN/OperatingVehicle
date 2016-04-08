<form id="editUpkeepCharge_form" method="post">
		<input type="hidden" name="id" value= "${upkeepCharge.id}"/>
		<input type="hidden" name="vehicleID"  id= "vehicle_upkeepEditID" value= "${upkeepCharge.vehicleID}"/>
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.upkeepCharge.select.vehicle")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="vehicleID"  value= "${upkeepCharge.vehicle.plate}"  id= "vehicle_upkeepEdit"   data-options="required:true"/>
	    		</td>
	    		<td>
	    			<a href="#" id="vehicle_search_btn" class="easyui-linkbutton" onclick="searchVehicle('vehicle_upkeepEdit')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.date")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-datebox" name="upkeepDate"  value= "${upkeepCharge.upkeepDate}" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.amount")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox" name="upkeepAmount"  value= "${upkeepCharge.upkeepAmount}" data-options="min:0,precision:2,required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.upkeepCompany")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="upkeepCompany" value= "${upkeepCharge.upkeepCompany}" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="remark"  value= "${upkeepCharge.remark}"  validtype="length[0,150]" data-options="multiline:true,height:110,width:320"/>
	    		</td>
	    	</tr>
	    </table>
	</form>




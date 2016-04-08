<form id="editMaintenanceCharge_form" method="post">
		<input type="hidden" name="id" value= "${maintenanceCharge.id}"/>
		<input type="hidden" name="vehicleID"  id= "vehicle_maintenanceEditID" />
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.select.vehicle")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="vehicleID"  value= "${maintenanceCharge.vehicle.plate}"  id= "vehicle_maintenanceEdit"   data-options="required:true"/>
	    		</td>
	    		<td>
	    			<a href="#" id="vehicle_search_btn" class="easyui-linkbutton" onclick="searchVehicle('vehicle_maintenanceEdit')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.date")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-datebox" name="maintenanceDate"  value= "${maintenanceCharge.maintenanceDate}" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.amount")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox" name="maintenanceAmount"  value= "${maintenanceCharge.maintenanceAmount}" data-options="min:0,precision:2,required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.mileage")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox" name="maintenanceMileage" value= "${maintenanceCharge.maintenanceMileage}"  data-options="min:0,required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.maintenanceCompany")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="maintenanceCompany" value= "${maintenanceCharge.maintenanceCompany}" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="remark"  value= "${maintenanceCharge.remark}"  validtype="length[0,150]" data-options="multiline:true,height:110,width:320"/>
	    		</td>
	    	</tr>
	    </table>
	</form>




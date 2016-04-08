<form id="viewMaintenanceCharge_form" method="post">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.select.vehicle")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox"  value= "${maintenanceCharge.vehicle.plate}"disabled="disabled" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.date")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-datebox"   value= "${maintenanceCharge.maintenanceDate}"  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.amount")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox"   value= "${maintenanceCharge.maintenanceAmount}" data-options="min:0,precision:2" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.mileage")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox"  value= "${maintenanceCharge.maintenanceMileage}"  data-options="min:0" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.maintenanceCharge.maintenanceCompany")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" value= "${maintenanceCharge.maintenanceCompany}" validtype="length[0,50]" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value= "${maintenanceCharge.remark}"   data-options="multiline:true,height:110,width:320" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
	</form>




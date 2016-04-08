<form id="editOilCharge_form" method="post">
		<input type="hidden" name="id" value= "${oilCharge.id}"/>
		<input type="hidden" name="vehicleID"  id= "vehicle_oilEditID" value= "${oilCharge.vehicleID}"/>
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.oilCharge.select.vehicle")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="vehicleID"  value= "${oilCharge.vehicle.plate}"  id= "vehicle_oilEdit"   data-options="required:true"/>
	    		</td>
	    		<td>
	    			<a href="#" id="vehicle_search_btn" class="easyui-linkbutton" onclick="searchVehicle('vehicle_oilEdit')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.date")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-datebox" name="oilDate"  value= "${oilCharge.oilDate}" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.amount")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox" name="oilAmount"  value= "${oilCharge.oilAmount}" data-options="min:0,precision:2,required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.discountAmount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="discountAmount"  value= "${oilCharge.discountAmount}" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.finalAmount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilFinalAmount" value= "${oilCharge.oilFinalAmount}" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>	
	    	<tr>
	    		<th>${message("ov.oilCharge.oilCount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilCount" value= "${oilCharge.oilCount}" data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>	
	    	<tr>
	    		<th>${message("ov.oilCharge.oilPrice")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox" name="oilPrice"  value= "${oilCharge.oilPrice}"data-options="required:true,min:0,precision:2">
	    		</td>
	    	</tr>	 
	    	<tr>
	    		<th>${message("ov.oilCharge.oilLabel")}:</th>
	    		<td >
	    			<input type="text" class="easyui-textbox"  name= "oilLabel"  value= "${oilCharge.oilLabel}"  data-options="required:true"/>   
	    		</td>
	    	</tr>	 	
	    	<tr>
	    		<th>${message("ov.oilCharge.invoiceNumber")}:</th>
	    		<td >
	    			<input type="text" class="easyui-textbox"  name= "invoiceNumber" value= "${oilCharge.invoiceNumber}"/>   
	    		</td>
	    	</tr>	
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="remark"  value= "${oilCharge.remark}"  validtype="length[0,150]" data-options="multiline:true,height:110,width:320"/>
	    		</td>
	    	</tr>
	    </table>
	</form>




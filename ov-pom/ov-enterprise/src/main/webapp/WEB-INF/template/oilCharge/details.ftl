<form id="viewOilCharge_form" method="post">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.oilCharge.select.vehicle")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox"  value= "${oilCharge.vehicle.plate}"disabled="disabled" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.date")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-datebox"   value= "${oilCharge.oilDate}"  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.amount")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox"   value= "${oilCharge.oilAmount}" data-options="min:0,precision:2" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.discountAmount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox"  value= "${oilCharge.discountAmount}" data-options="required:true,min:0,precision:2" disabled="disabled">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.oilCharge.finalAmount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox"  value= "${oilCharge.oilFinalAmount}" data-options="required:true,min:0,precision:2" disabled="disabled">
	    		</td>
	    	</tr>	
	    		    	<tr>
	    		<th>${message("ov.oilCharge.oilCount")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox"  value= "${oilCharge.oilCount}" data-options="required:true,min:0,precision:2" disabled="disabled">
	    		</td>
	    	</tr>	
	    	<tr>
	    		<th>${message("ov.oilCharge.oilPrice")}:</th>
	    		<td >
	    			 <input type="text" class="easyui-numberbox"   value= "${oilCharge.oilPrice}"data-options="required:true,min:0,precision:2" disabled="disabled">
	    		</td>
	    	</tr>	 
	    	<tr>
	    		<th>${message("ov.oilCharge.oilLabel")}:</th>
	    		<td >
	    			<input type="text" class="easyui-textbox"   value= "${oilCharge.oilLabel}"  data-options="required:true" disabled="disabled"/>   
	    		</td>
	    	</tr>	 	
	    	<tr>
	    		<th>${message("ov.oilCharge.invoiceNumber")}:</th>
	    		<td >
	    			<input type="text" class="easyui-textbox"   value= "${oilCharge.invoiceNumber}" disabled="disabled"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value= "${oilCharge.remark}"   data-options="multiline:true,height:110,width:320" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
	</form>




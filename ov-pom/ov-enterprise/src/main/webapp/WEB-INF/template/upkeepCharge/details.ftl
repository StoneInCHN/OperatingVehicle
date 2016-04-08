<form id="viewUpkeepCharge_form" method="post">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("ov.upkeepCharge.select.vehicle")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox"  value= "${upkeepCharge.vehicle.plate}"disabled="disabled" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.date")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-datebox"   value= "${upkeepCharge.upkeepDate}"  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.amount")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-numberbox"   value= "${upkeepCharge.upkeepAmount}" data-options="min:0,precision:2" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("ov.upkeepCharge.upkeepCompany")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" value= "${upkeepCharge.upkeepCompany}" validtype="length[0,50]" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    	<tr>
	    	<th>${message("ov.remark")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value= "${upkeepCharge.remark}"   data-options="multiline:true,height:110,width:320" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
	</form>




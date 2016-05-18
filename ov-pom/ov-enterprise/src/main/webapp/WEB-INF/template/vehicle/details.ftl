<form id="vehicleDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message('ov.vehicle.plate')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox"  value="${vehicle.plate}"/>
	    		</td>
	    		<th>${message('ov.vehicle.color')}:</th>
	    		<td >
	    			 <input class="easyui-textbox"  value="${vehicle.color}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.deviceInfo.deviceNO')}:</th>
	    		<td >
	    			 <input class="easyui-textbox"  value="${vehicle.device.deviceNo}"/>
	    		</td>
	    		<th>${message('ov.vehicle.motorcade')}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicle.motorcade.motorcadeDesc}"/>
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >${message('ov.vehicle.vehicleFullBrand')}:</th>
	    		<td>
	    		<input  class="easyui-textbox"  value="${vehicle.vehicleFullBrand}"/>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message('ov.vehicle.dashboardMileage')}:</th>
	    		<td >
	    			 <input  class="easyui-numberbox"  value="${vehicle.dashboardMileage}"  data-options="min:0,precision:1"/>
	    		</td>
	    		<th>${message('ov.vehicle.dashboardBV')}:</th>
	    		<td >
	    			 <input class="easyui-numberbox"  value="${vehicle.dashboardBV}"  data-options="min:0,precision:1"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.vehicle.dashboradOil')}:</th>
	    		<td >
	    			 <input  class="easyui-numberbox"  value="${vehicle.dashboradOil}"  data-options="min:0,precision:1"/>
	    		</td>
	    		<th>${message('ov.vehicle.plateDate')}:</th>
	    		<td >
	    			 <input class="easyui-datebox" name="plateDate" value="${vehicle.plateDate}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.vehicle.vehicleNo')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.vehicleNo}"/>
	    		</td>
	    		<th>${message('ov.vehicle.produceDate')}:</th>
	    		<td >
	    			 <input class="easyui-datebox"  value="${vehicle.produceDate}"/>
	    		</td>
	    	</tr>
	    </table>
</form>




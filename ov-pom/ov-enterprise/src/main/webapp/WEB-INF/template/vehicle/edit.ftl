<form id="editVehicle_form" method="post">   
		<input type="hidden" name="id" value= "${vehicle.id}"/>
	   <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message('ov.vehicle.plate')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.plate}" name="plate" id= "plate"  data-options="required:true"/>
	    		</td>
	    		<th>${message('ov.vehicle.color')}:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.color}" name="color" id= "color" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.vehicle.bindedDevice')}:</th>
	    		<td >
	    			 <input class="easyui-textbox" disabled="disabled" value="${vehicle.device.deviceNo}" id="editVehicle_deviceInfo" panelHeight="150px"" />
	    		</td>
	    		<th>${message('ov.vehicle.motorcade')}:</th>
	    		<td>
	    			 <input class="easyui-textbox" data-value="${vehicle.motorcade.id}"  id="editVehicleMotorcade" name="vehicleMotorcadeId" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("ov.common.please.select")}'" />
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >${message('ov.vehicle.vehicleFullBrand')}:</th>
	    		<td>
	    		<input  class="easyui-textbox" value="${vehicle.vehicleFullBrand}" name="vehicleFullBrand" id= "vehicleFullBrand" />
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message('ov.vehicle.dashboardMileage')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.dashboardMileage}" name="dashboardMileage" id= "dashboardMileage" />
	    		</td>
	    		<th>${message('ov.vehicle.dashboardBV')}:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.dashboardBV}" name="dashboardBV" id= "dashboardBV" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.vehicle.dashboradOil')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.dashboradOil}" name="dashboradOil" id= "dashboradOil"  />
	    		</td>
	    		<th>${message('ov.vehicle.plateDate')}:</th>
	    		<td >
	    			 <input type="text" class="easyui-datebox" name="plateDate" data-options="editable:false" value="${vehicle.plateDate}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.vehicle.vehicleNo')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.vehicleNo}" name="vehicleNo" id= "vehicleNo"  data-options="required:true"/>
	    		</td>
	    		<th>${message('ov.vehicle.produceDate')}:</th>
	    		<td >
	    			 <input type="text" class="easyui-datebox" name="produceDate" data-options="editable:false" value="${vehicle.produceDate}"/>
	    		</td>
	    	</tr>
	    </table>
</form>




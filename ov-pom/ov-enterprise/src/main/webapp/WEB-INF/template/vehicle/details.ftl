<form id="vehicleDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>车牌号:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.plate}" name="plate" id= "plate"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>车辆颜色:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.color}" name="color" id= "color" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>绑定设备:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.device.deviceNo}" id="editVehicle_deviceInfo" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("ov.common.please.select")}'" disabled="disabled"/>
	    		</td>
	    		<th>车辆所有者:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicle.endUser.userName}"  id="editVehicle_endUser" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("ov.common.please.select")}'" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >车型:</th>
	    		<td colspan="3">
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrand-detail" data-value="${vehicle.vehicleBrandDetail.vehicleLine.vehicleBrand.id}" disabled="disabled"/>
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleLine-detail" data-value="${vehicle.vehicleBrandDetail.vehicleLine.id}" disabled="disabled"/>
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrandDetail-detail" name="vehicleBrandDetailId" data-value="${vehicle.vehicleBrandDetail.id}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>仪表盘里程（公里）:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.dashboardMileage}" name="dashboardMileage" id= "dashboardMileage" disabled="disabled"/>
	    		</td>
	    		<th>电瓶电压（V）:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.dashboardBV}" name="dashboardBV" id= "dashboardBV" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>油量(L):</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.dashboradOil}" name="dashboradOil" id= "dashboradOil"  disabled="disabled"/>
	    		</td>
	    		<th>上牌时间:</th>
	    		<td >
	    			 <input type="text" class="easyui-datebox" name="plateDate" data-options="editable:false" value="${vehicle.plateDate}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>车架号:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.vehicleNo}" name="vehicleNo" id= "vehicleNo"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>生产日期:</th>
	    		<td >
	    			 <input type="text" class="easyui-datebox" name="produceDate" data-options="editable:false" value="${vehicle.produceDate}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
</form>




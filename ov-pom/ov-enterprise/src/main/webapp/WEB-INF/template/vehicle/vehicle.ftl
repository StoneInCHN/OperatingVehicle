<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/vehicle.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("ov.vehicle.search")}</legend>
	    <form id="vehicle-search-form" class="search-form">
	    <input type="hidden" name="totalRecord" id="totalRecord"/>
	    	<div class="search-item">
			    <label> ${message("ov.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" name="vehiclePlateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.vehicle.vehicleFullBrandSearch")}:</label>
			    <input type="text" class="easyui-textbox" name="vehicleFullBrandSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message('ov.vehicle.motorcade')}:</label>
			    <input type="text" class="easyui-textbox" name="motorcadeSearch" validtype="length[0,20]"/>
			</div>
			
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="vehicle-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="vehicle-table-list"></table>
<div id="vehicle_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="vehicle_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="vehicle_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="vehicle_manager_tool.remove();">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="exportExcel('vehicle','vehicle-search-form','totalRecord');">导出</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addVehicle"> 
	<form id="addVehicle_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message('ov.vehicle.plate')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="plate" id= "plate"  data-options="required:true"/>
	    		</td>
	    		<th>${message('ov.vehicle.color')}:</th>
	    		<td >
	    			 <input class="easyui-textbox" name="color" id= "color" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th >${message('ov.vehicle.vehicleNo')}:</th>
	    		<td >
	    			 <input class="easyui-textbox" name="vehicleNo"/>
	    		</td>
	    		<th >${message('ov.vehicle.vehicleFullBrand')}:</th>
	    		<td >
	    			 <input class="easyui-textbox" name="vehicleFullBrand" />
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >${message('ov.vehicle.motorcade')}:</th>
	    		<td >
	    			 <input class="easyui-combobox" name="motorcadeId" id= "addVehicleMotorcade" />
	    		</td>
	    		<th >${message('ov.vehicle.vehicleStatus')}:</th>
	    		<td >
		    			 <input class="easyui-combobox" data-options="
					     valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'ENABLE',
					      value: '${message("ov.vehicle.vehicleStatus.ENABLE")}'
					     },{
					      label: 'DISABLE',
					      value: '${message("ov.vehicle.vehicleStatus.DISABLE")}'
					     },{
					      label: 'WORKING',
					      value: '${message("ov.vehicle.vehicleStatus.WORKING")}'
					     }],
					     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="vehicleStatus" style="width:110px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message('ov.vehicle.produceDate')}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="produceDate" onclick="WdatePicker();"/>
	    		</td>
	    		<th>${message('ov.vehicle.plateDate')}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="plateDate" onclick="WdatePicker();"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editVehicle"></div>
<div id="vehicleDetail"></div>





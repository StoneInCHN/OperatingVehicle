<div>
<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="common_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>${message("ov.vehicle.plate")}:</label>
						   <input type="text" class="easyui-textbox"  id="common_vehiclePlateSearch" name="vehiclePlateSearch" validtype="length[0,10]" style="width:100px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="common_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:10px;margin-right:2px"data-options="iconCls:'icon-search'">${message("ov.search")}</button>
				    </div>
				</fieldset>               
</div>
<table id="common-vehicles-table-list"></table>










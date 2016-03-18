<div>
	  <fieldset>
	    <legend>${message("ov.deviceInfo.search")}</legend>
	    <form id="common_deviceInfo_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.deviceInfo.deviceNO")}:</label>
			    <input type="text" class="easyui-textbox" id="deviceNoSearch" name="deviceNoSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.device.device.deviceTpye")}:</label>
			    <input type="text" class="easyui-textbox" id="deviceTpyeSearch" name="deviceTpyeSearch" validtype="length[0,20]"/>
			</div>
			
			<div class="search-item">
			    <label> ${message("ov.device.deviceStatus")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INITED',
				      value: '${message("ov.deviceInfo.deviceStatus.INITED")}'
				     },{
				      label: 'SENDOUT',
				      value: '${message("ov.deviceInfo.deviceStatus.SENDOUT")}'
				     },{
				      label: 'STORAGEOUT',
				      value: '${message("ov.deviceInfo.deviceStatus.STORAGEOUT")}'
				     },{
				      label: 'BINDED',
				      value: '${message("ov.deviceInfo.deviceStatus.BINDED")}'
				     },{
				      label: 'REFUNDED',
				      value: '${message("ov.deviceInfo.deviceStatus.REFUNDED")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="accountStatusSearch" id="accountStatusSearch" style="width:110px;"/>
			</div>
			
		</form>
		<div class="search-item">
	  	  <button id="common_deviceInfo_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="common-deviceInfo-table-list"></table>





 <fieldset>
	    <legend>${message("ov.endUser.search")}</legend>
	    <form id="endUser-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("ov.endUser.userName")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("ov.endUser.staffStatus")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("ov.endUser.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("ov.endUser.accoutStatus.locked")}'
				     }],
				     prompt:'${message("ov.common.please.select")}',panelMaxHeight:100"  name="accountStatusSearch" id="accountStatusSearch" style="width:110px;"/>
			</div>
			
		</form>
		<div class="search-item">
	  	  <button id="endUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
<table id="common-endUser-table-list"></table>  





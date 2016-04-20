<script src="${base}/resources/modules/motorcade.js"></script>

<div>
	  <fieldset>
	    <legend>${message("ov.motorcade.search")}</legend>
	    <form id="motorcade-search-form" class="search-form">
	    <input type="hidden" name="totalRecord" id="totalRecord"/>
	    	<div class="search-item">
			    <label> ${message("ov.motorcade.desc")}:</label>
			    <input type="text" class="easyui-textbox" name="motorcadeDescSearch" validtype="length[0,20]"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="motorcade-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="motorcade-table-list"></table>
<div id="motorcade_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="motorcade_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="motorcade_manager_tool.edit();">修改</a>
		<!--
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="motorcade_manager_tool.remove();">删除</a>
		-->
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addMotorcade"> 
	<form id="addMotorcade_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message('ov.motorcade.desc')}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="motorcadeDesc" id= "motorcadeDesc"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editMotorcade"></div>





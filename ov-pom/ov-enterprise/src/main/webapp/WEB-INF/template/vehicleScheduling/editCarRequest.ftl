<form id="editUseCarRequest_form" method="post">   
	<input type="hidden" name="id" value= "${vehicleScheduling.id}"/>
	<input type="hidden" name="status" value= "${vehicleScheduling.status}"/> 
    <table class="table table-striped"  border="0">
    	<tr>
    		<th>${message("ov.useCarRequest.title")}:</th>
    		<td colspan="2">
    			 <input type="text" class="easyui-textbox" name="title" id= "title" value="${vehicleScheduling.title}" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("ov.useCarRequest.startDate")}:</th>
    		<td colspan="2">
    			 <input type="text" class="easyui-datetimebox" name="startDate" id= "startDate" value="${vehicleScheduling.startDate}"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("ov.useCarRequest.personNum")}:</th>
    		<td colspan="2">
    			 <input type="text" class="easyui-textbox" name="personNum" id= "personNum" value="${vehicleScheduling.personNum}"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("ov.useCarRequest.startPositionDetails")}:</th>
    		<td colspan="2">
    			 <input type="text" class="easyui-textbox" name="startPositionDetails" id="startPositionDetails" value="${vehicleScheduling.startPositionDetails}"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("ov.useCarRequest.endPositionDetails")}:</th>
    		<td colspan="2">
    			 <input type="text" class="easyui-textbox" name="endPositionDetails" id="endPositionDetails" value="${vehicleScheduling.endPositionDetails}" data-options="required:true" />
    		</td>
    	</tr>
    	<tr>
    		<th>${message("ov.useCarRequest.remark")}:</th>
    		<td colspan="2">
    			 <input type="text" class="easyui-textbox" name="remark" id= "remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" value="${vehicleScheduling.remark}" />
    		</td>
    	</tr>
    </table>
</form>




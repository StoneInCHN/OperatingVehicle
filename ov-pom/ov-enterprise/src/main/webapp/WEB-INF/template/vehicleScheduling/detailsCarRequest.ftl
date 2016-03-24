<table class="table table-striped"  border="0">
	<tr>
		<th>${message("ov.useCarRequest.title")}:</th>
		<td colspan="2">
			<input class="easyui-textbox" disabled="disabled" type="text" data-options="required:true" value="${vehicleScheduling.title}"/>
		</td>
	</tr>
	<tr>
		<th>${message("ov.useCarRequest.startDate")}:</th>
		<td colspan="2">
			 <input type="text" class="easyui-datetimebox" readonly="readonly" value="${vehicleScheduling.startDate}"/>
		</td>
	</tr>
	<tr>
		<th>${message("ov.useCarRequest.personNum")}:</th>
		<td colspan="2">
			 <input class="easyui-textbox" disabled="disabled" type="text" data-options="required:true" value="${vehicleScheduling.personNum}"/>
		</td>
	</tr>
	<tr>
		<th>${message("ov.useCarRequest.startPositionDetails")}:</th>
		<td colspan="2">
			 <input class="easyui-textbox" disabled="disabled" type="text" data-options="required:true" value="${vehicleScheduling.startPositionDetails}"/>
		</td>
	</tr>
	<tr>
		<th>${message("ov.useCarRequest.endPositionDetails")}:</th>
		<td colspan="2">
			 <input class="easyui-textbox" disabled="disabled" type="text" data-options="required:true" value="${vehicleScheduling.endPositionDetails}"/>
		</td>
	</tr>
	<tr>
		<th>${message("ov.useCarRequest.status")}:</th>
		<td colspan="2">
			<select class="easyui-combobox" disabled="disabled" style="width:100px;">   
		  		<option value="TO_CONFIRM" [#if vehicleScheduling.status == "TO_CONFIRM"] selected="selected" [/#if]>${message("ov.useCarRequest.to_confirm")}</option>
				<option value="DISTRIBUTED" [#if vehicleScheduling.status == "DISTRIBUTED"] selected="selected" [/#if]>${message("ov.useCarRequest.distributed")}</option>
				<option value="FINISHED" [#if vehicleScheduling.status == "FINISHED"] selected="selected" [/#if]>${message("ov.useCarRequest.finished")}</option>
				<option value="CANCELLED" [#if vehicleScheduling.status == "CANCELLED"] selected="selected" [/#if]>${message("ov.useCarRequest.cancelled")}</option>
				<option value="REJECTED" [#if vehicleScheduling.status == "REJECTED"] selected="selected" [/#if]>${message("ov.useCarRequest.rejected")}</option>
				<option value="BREAK_CONTRACT" [#if vehicleScheduling.status == "BREAK_CONTRACT"] selected="selected" [/#if]>${message("ov.useCarRequest.break_contract")}</option>
		  	</select>
		</td>
	</tr>
	<tr>
		<th>${message("ov.useCarRequest.remark")}:</th>
		<td colspan="2">
			<input type="text" class="easyui-textbox" disabled="disabled" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" value="${vehicleScheduling.remark}"/>
		</td>
	</tr>
</table>





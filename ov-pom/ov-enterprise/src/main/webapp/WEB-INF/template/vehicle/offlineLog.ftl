<form id="offlineLog_form" method="post">   
	 <table  class="table table-bordered tr-center">
			<tr>
				<td>车辆</td>
				<td>绑定设备</td>
				<td>离线时间</td>
			</tr>
			[#if offlineLog != null && offlineLog.size() > 0]
			[#list offlineLog as log]
			<tr>
				<td>${log.vehicle.plate}</td>
				<td>${log.deviceNo}</td>
				<td>${log.offlineDate?string("yyyy-MM-dd HH:mm:ss")}</td>
			</tr>																	
			[/#list]
			[#else]
			<tr>
				<td colspan="3">该车辆暂无离线记录</td>
			</tr>
			[/#if]
	</table>
</form>




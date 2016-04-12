<form id="editMotorcade_form" method="post">   
	<input type="hidden" name="id" value= "${motorcade.id}"/>
   <table class="table table-striped"  border="0">
    	<tr>
    		<th>${message('ov.motorcade.desc')}:</th>
    		<td >
    			 <input  class="easyui-textbox" value="${motorcade.motorcadeDesc}" name="motorcadeDesc" id= "motorcadeDesc"  data-options="required:true"/>
    		</td>
    	</tr>
    </table>
</form>




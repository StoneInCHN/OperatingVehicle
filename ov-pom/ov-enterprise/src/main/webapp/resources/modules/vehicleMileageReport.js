

$("#vehicleMileageReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/vehicleMileageReport/reportSingleVehicle.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"车牌号",field:"vehicleTitle",width:"33%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(row != null){return row.vehicle.plate;}
			    	 }
		     },
		     {title:"行程",field:"mileage",width:"33%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return value+"km";}
		    	 }
		     },
		     {title:"统计时间",field:"vehicleMileageStatisticsDate",width:"33%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format(message("ov.date.China.format.yyyyMM"));}
		    	  }
		     }
		 ]
	],
	onLoadSuccess:function(data){
		//车辆行程统计
		var vehicleMileageReportOptions = {
				 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
			        title: {text: '<a href="#" style="color: #222222"}>车辆行程统计</a>',margin:50,useHTML:true},
			        subtitle: {text: '周期：按月统计'},
					credits : {enabled : false},
			        xAxis: {},
			        yAxis: {
			        	min: 0,
			            title: {text: '行程/千米'},
			            labels: {formatter: function() {return this.value +'km'}}
			        },
			        tooltip: {crosshairs: true,shared: true,valueSuffix : '千米'},
			        plotOptions: {
			            spline: {
			                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
			            }
			        },
			        series: [{name: '行程',
			            marker: {symbol: 'square'},
			            data: []
			        }]
		};
		refreshLine(vehicleMileageReportOptions,data.rows,'vehicleMileageStatisticsDate','mileage');
	}

});
$("#vehicleMileageReport_search_btn").click(function(){
	  var _queryParams = $("#vehicleMileageReport_search_form").serializeJSON();
	  $('#vehicleMileageReport-table-list').datagrid('options').queryParams = _queryParams;
	  $("#vehicleMileageReport-table-list").datagrid('reload');
});
	//车辆查询
$(function(){
	$("#vehicleMileageVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			$('#vehicleMileage_vehicleID').val(rowData.id);
			  var _queryParams = $("#vehicleMileageReport_search_form").serializeJSON();
			  $('#vehicleMileageReport-table-list').datagrid('options').queryParams = _queryParams;
			  $("#vehicleMileageReport-table-list").datagrid('reload');
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : "车牌号",field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "品牌图标",field : "brandIcon",width :"47%",align : 'center',sortable : true},					
		]]
});
$("#upkeepCharge_vehicle_search_btn").click(function(){
		  var _queryParams = $("#upkeepCharge_vehicle_search_form").serializeJSON();
		  $('#upkeepChargeVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#upkeepChargeVehicleSearch-table-list").datagrid('reload');			
		});
});
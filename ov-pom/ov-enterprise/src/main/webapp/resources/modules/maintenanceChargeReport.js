
$("#maintenanceChargeReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/maintenanceChargeReport/reportSingleVehicle.jhtml",
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
		     {title:"保养费用",field:"maintenanceAmount",width:"33%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"￥";}
			    	 }
		     },
		     {title:"统计时间",field:"maintenanceChargeStatisticsDate",width:"33%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format("yyyy年");}
		    	  }
		     }
		 ]
	],
	onLoadSuccess:function(data){
		//保养费统计
		var maintenanceChargeReportOptions = {
				 	chart: {type: 'spline', renderTo : "maintenanceChargeReportDivId"},
			        title: {text: '<a href="#" style="color: #222222"}>车辆保养费统计</a>',margin:50,useHTML:true},
			        subtitle: {text: '周期：按年统计'},
					credits : {enabled : false},
			        xAxis: {},
			        yAxis: {
			        	min: 0,
			            title: {text: '费用/元'},
			            labels: {formatter: function() {return this.value +'￥'}}
			        },
			        tooltip: {crosshairs: true,shared: true,valueSuffix : '元'},
			        plotOptions: {
			            spline: {
			                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
			            }
			        },
			        series: [{name: '费用',
			            marker: {symbol: 'square'},
			            data: []
			        }]
		};
		refreshLine(maintenanceChargeReportOptions,data.rows,'maintenanceChargeStatisticsDate','maintenanceAmount');
	}

});
$("#maintenanceChargeReport_search_btn").click(function(){
	  var _queryParams = $("#maintenanceChargeReport_search_form").serializeJSON();
	  $('#maintenanceChargeReport-table-list').datagrid('options').queryParams = _queryParams;
	  $("#maintenanceChargeReport-table-list").datagrid('reload');
	});
//车辆查询
$(function(){
	$("#maintenanceChargeVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			$('#maintenanceCharge_vehicleID').val(rowData.id);
			  var _queryParams = $("#maintenanceChargeReport_search_form").serializeJSON();
			  $('#maintenanceChargeReport-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#maintenanceChargeReport-table-list").datagrid('reload');
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : "车牌号",field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "品牌图标",field : "brandIcon",width :"47%",align : 'center',sortable : true},					
		]]
});
$("#maintenanceCharge_vehicle_search_btn").click(function(){
		  var _queryParams = $("#maintenanceCharge_vehicle_search_form").serializeJSON();
		  $('#maintenanceChargeVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#maintenanceChargeVehicleSearch-table-list").datagrid('reload');			
		});
});
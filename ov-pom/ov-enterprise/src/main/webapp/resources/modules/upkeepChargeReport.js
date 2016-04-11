
$("#upkeepChargeReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/upkeepChargeReport/reportSingleVehicle.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"车牌号",field:"vehicleTitle",width:"25%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(row != null){return row.vehicle.plate;}
			    	 }
		     },
		     {title:"维修费用",field:"upkeepAmount",width:"25%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"￥";}
			    	 }
		     },
		     {title:"维修次数",field:"upkeepNumber",width:"25%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"次";}
			    	 }
		     },
		     {title:"统计时间",field:"upkeepChargeStatisticsDate",width:"25%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format(message("ov.date.China.format.yyyyMM"));}
		    	  }
		     }
		 ]
	],
	onLoadSuccess:function(data){
		//维修费统计
		var upkeepChargeReportOptions = {
				 	chart: {type: 'spline', renderTo : "upkeepChargeReportDivId"},
			        title: {text: '<a href="#" style="color: #222222"}>车辆维修费统计</a>',margin:50,useHTML:true},
			        subtitle: {text: '周期：按月统计'},
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

		refreshLine(upkeepChargeReportOptions,data.rows,'upkeepChargeStatisticsDate','upkeepAmount');
	}

});
$("#upkeepChargeReport_search_btn").click(function(){
	  var _queryParams = $("#upkeepChargeReport_search_form").serializeJSON();
	  $('#upkeepChargeReport-table-list').datagrid('options').queryParams = _queryParams;
	  $("#upkeepChargeReport-table-list").datagrid('reload');
});
//车辆查询
$(function(){
	$("#upkeepChargeVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			$('#upkeepCharge_vehicleID').val(rowData.id);
			  var _queryParams = $("#upkeepChargeReport_search_form").serializeJSON();
			  $('#upkeepChargeReport-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#upkeepChargeReport-table-list").datagrid('reload');
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : "车牌号",field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "车辆车型",field : "vehicleFullBrand",width :"47%",align : 'center',sortable : true},						
		]]
});
$("#upkeepCharge_vehicle_search_btn").click(function(){
		  var _queryParams = $("#upkeepCharge_vehicle_search_form").serializeJSON();
		  $('#upkeepChargeVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#upkeepChargeVehicleSearch-table-list").datagrid('reload');			
		});
});
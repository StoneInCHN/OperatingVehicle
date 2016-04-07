

$("#oilChargeReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/oilChargeReport/reportSingleVehicle.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"车牌号",field:"vehicleTitle",width:"20%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(row != null){return row.vehicle.plate;}
			    	 }
		     },
		     {title:"油费/元",field:"oilFinalAmount",width:"20%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"￥";}
			    	 }
		     },
		     {title:"加油量/升",field:"oilCount",width:"20%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"L";}
			  }},
		     {title:"加油次数",field:"oilNumber",width:"20%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"次";}
			  }},
		     {title:"统计时间",field:"oilChargeReportStatisticsDate",width:"20%",align: 'center',sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format(message("ov.date.China.format.yyyyMM"));}
		    	  }
		     }
		 ]
	],
	onLoadSuccess:function(data){
		//油费统计
		var oilChargeReportOptions = {
				 	chart: {type: 'spline', renderTo : "oilChargeReportDivId"},
			        title: {text: '<a href="#" style="color: #222222"}>车辆油费统计</a>',margin:50,useHTML:true},
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
		refreshLine(oilChargeReportOptions,data.rows,'oilChargeReportStatisticsDate','oilFinalAmount');
	}

});
$("#oilChargeReport_search_btn").click(function(){
	  var _queryParams = $("#oilChargeReport_search_form").serializeJSON();
	  $('#oilChargeReport-table-list').datagrid('options').queryParams = _queryParams;
	  $("#oilChargeReport-table-list").datagrid('reload');
	});
//车辆查询
$(function(){
	$("#oilChargeVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("ov.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			$('#oilCharge_vehicleID').val(rowData.id);
			  var _queryParams = $("#oilChargeReport_search_form").serializeJSON();
			  $('#oilChargeReport-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#oilChargeReport-table-list").datagrid('reload');
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : "车牌号",field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "品牌图标",field : "brandIcon",width :"47%",align : 'center',sortable : true},					
		]]
});
$("#oilCharge_vehicle_search_btn").click(function(){
		  var _queryParams = $("#oilCharge_vehicle_search_form").serializeJSON();
		  $('#oilChargeVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#oilChargeVehicleSearch-table-list").datagrid('reload');			
		});
});
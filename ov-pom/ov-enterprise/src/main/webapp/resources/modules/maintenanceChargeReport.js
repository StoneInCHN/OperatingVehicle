//保养费统计
		var maintenanceChargeReportOptions = {
				 	chart: {type: 'spline', renderTo : "maintenanceChargeReportDivId"},
			        title: {text: '<a href="#" style="color: #222222"}>车辆保养费统计</a>',margin:50,useHTML:true},
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

$("#maintenanceChargeReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/maintenanceChargeReport/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"保养费用",field:"maintenanceAmount",width:100,sortable:true},
		     {title:"统计时间",field:"maintenanceChargeStatisticsDate",width:100,sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format("yyyy年MM月");}
		    	  }
		     }
		 ]
	],
	onLoadSuccess:function(data){
		refreshLine(maintenanceChargeReportOptions,data.rows,'maintenanceChargeStatisticsDate','maintenanceAmount');
	}

});
$("#maintenanceChargeReport_search_btn").click(function(){
	  var _queryParams = $("#maintenanceChargeReport_search_form").serializeJSON();
	  $('#maintenanceChargeReport-table-list').datagrid('options').queryParams = _queryParams;
	  $("#maintenanceChargeReport-table-list").datagrid('reload');
	})
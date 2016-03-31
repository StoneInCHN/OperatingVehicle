
$("#upkeepChargeReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/upkeepChargeReport/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"维修费用",field:"upkeepAmount",width:100,sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"￥";}
			    	 }
		     },
		     {title:"统计时间",field:"upkeepChargeStatisticsDate",width:100,sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format("yyyy年MM月");}
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
	})
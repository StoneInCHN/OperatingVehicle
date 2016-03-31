

$("#oilChargeReport-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/oilChargeReport/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"油费",field:"oilFinalAmount",width:100,sortable:true,
		    	 formatter: function(value,row,index){
		    			if(value != null){return value+"￥";}
			    	 }
		     },
		     {title:"加油量L",field:"oilCount",width:100,sortable:true},
		     {title:"统计时间",field:"oilChargeReportStatisticsDate",width:100,sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){return new Date(value).Format("yyyy年MM月");}
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
	})
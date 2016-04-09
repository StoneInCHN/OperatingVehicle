	var now = new Date();
    var year = now.getFullYear();
    var month = (now.getMonth()+1);
    var date = new Date(year, month, 0);  //指定的年月
    var daysCount = date.getDate();            //本月天数 
    var  deviceId = '8801001667';
    var  fromDate = year + "-" + month + "-1";
    var  toDate =  year + "-" + month + "-" + daysCount;

	var createdate = []; //日期
	var dailyMileage = []; //每天的行驶公里数
	var averageFuelConsumption = []; //平均油耗
	var fuelConsumption = []; //当日油耗
	var cost = []; //当日油费
	var averageSpeed = []; //平均速度
	var emergencybrakecount = []; //急刹车次数
	var suddenturncount = []; //急转弯次数
	var rapidlyspeedupcount = []; //急加速次数
	
	//车辆行程统计
	var vehicleMileageReportOptions = {
			 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
		        title: {text: '<a href="#" style="color: #222222"}>车辆行程统计</a>',margin:50,useHTML:true},
		        subtitle: {text: '周期：按天统计'},
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
	}

$("#vehicleMileageReport_search_btn").click(function(){
		var date = new Date( new Date().getFullYear(),$("#vehicleStatusMonthID").combobox("getValue"), 0); 
	    var daysCount = date.getDate();            //本月天数 
	    fromDate =  new Date().getFullYear() + "-" + $("#vehicleStatusMonthID").combobox("getValue") + "-1";
	    toDate =   new Date().getFullYear() + "-" + $("#vehicleStatusMonthID").combobox("getValue") + "-" + daysCount;
	    loadReportDate();//调用接口，加载数据	
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
	loadReportDate();//调用接口，加载数据	
});
function loadReportDate(){
	$.ajax({
		url : "../common/monthlyVehicleStatus.jhtml",
		type : "post",
		traditional : true,
		data : {
			"deviceId" : deviceId,
			"fromDate" : fromDate,
			"toDate" : toDate
		},
		dataType: "json",
		beforeSend : function() {},
		success : function(result, response, status) {
			if (response == "success" && result !="") {
				var msgJson = result.msg;
				//初始化数据
				createdate = []; //日期
				dailyMileage = []; //每天的行驶公里数
				averageFuelConsumption = []; //平均油耗
				fuelConsumption = []; //当日油耗
				cost = []; //当日油费
				averageSpeed = []; //平均速度
				emergencybrakecount = []; //急刹车次数
				suddenturncount = []; //急转弯次数
				rapidlyspeedupcount = []; //急加速次数
				for(var i=1; i<= daysCount; i++){
					createdate.push(year+"年"+month+"月"+ (i) +"日");
					dailyMileage.push(0);
					averageFuelConsumption.push(0);
					fuelConsumption.push(0);
					cost.push(null);
					averageSpeed.push(0);
					emergencybrakecount.push(0);
					suddenturncount.push(0);
					rapidlyspeedupcount.push(0);
				}
				//将接口返回的数据进行填充
				for(var i=0; i< msgJson.length; i++){
					if(msgJson[i].day !=0){
						var d = msgJson[i].day - 1;
						dailyMileage[d] = msgJson[i].dailyMileage;
						averageFuelConsumption[d] = msgJson[i].averageFuelConsumption;
						fuelConsumption[d] = msgJson[i].fuelConsumption;
						cost[d] = msgJson[i].cost;
						averageSpeed[d] = msgJson[i].averageSpeed;
						emergencybrakecount[d] = msgJson[i].emergencybrakecount;
						suddenturncount[d] = msgJson[i].suddenturncount;
						rapidlyspeedupcount[d] = msgJson[i].rapidlyspeedupcount;
					}
				}
				//默认显示每日车辆里程
				vehicleMileageReportOptions.xAxis.categories = [];
				vehicleMileageReportOptions.series[0].data = [];
				for (var i = 0; i < dailyMileage.length; i++) {
					vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
				   vehicleMileageReportOptions.series[0].data.push(dailyMileage[i]);
				}
				var chart = new Highcharts.Chart(vehicleMileageReportOptions);
			} else {
				alertErrorMsg();
			}
		}
	});
}
function highLightTab(id){
	$("#report_dailyMileageID").removeClass();
	$("#report_averageFuelConsumptionID").removeClass();
	$("#report_fuelConsumptionID").removeClass();
	$("#report_costID").removeClass();
	$("#report_averageSpeedID").removeClass();
	$("#report_emergencybrakecountID").removeClass();
	$("#"+id).attr("class", "topTabActive"); 
}
$("#report_dailyMileageID").click(function(){
	if(dailyMileage.length > 0){
		highLightTab("report_dailyMileageID");
		vehicleMileageReportOptions.xAxis.categories = [];
		vehicleMileageReportOptions.series[0].data = [];
		for (var i = 0; i < dailyMileage.length; i++) {
			vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
		   vehicleMileageReportOptions.series[0].data.push(dailyMileage[i]);
		}
		var chart = new Highcharts.Chart(vehicleMileageReportOptions);
	}
});
$("#report_averageFuelConsumptionID").click(function(){
	if(averageFuelConsumption.length > 0){
		highLightTab("report_averageFuelConsumptionID");
		vehicleMileageReportOptions.xAxis.categories = [];
		vehicleMileageReportOptions.series[0].data = [];
		for (var i = 0; i < averageFuelConsumption.length; i++) {
			vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
		   vehicleMileageReportOptions.series[0].data.push(averageFuelConsumption[i]);
		}
		var chart = new Highcharts.Chart(vehicleMileageReportOptions);
	}
});
$("#report_fuelConsumptionID").click(function(){
	if(fuelConsumption.length > 0){
		highLightTab("report_fuelConsumptionID");
		vehicleMileageReportOptions.xAxis.categories = [];
		vehicleMileageReportOptions.series[0].data = [];
		for (var i = 0; i < fuelConsumption.length; i++) {
			vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
		   vehicleMileageReportOptions.series[0].data.push(fuelConsumption[i]);
		}
		var chart = new Highcharts.Chart(vehicleMileageReportOptions);
	}
});
$("#report_costID").click(function(){
	if(cost.length > 0){
		highLightTab("report_costID");
		vehicleMileageReportOptions.xAxis.categories = [];
		vehicleMileageReportOptions.series[0].data = [];
		for (var i = 0; i < cost.length; i++) {
			vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
		   vehicleMileageReportOptions.series[0].data.push(cost[i]);
		}
		var chart = new Highcharts.Chart(vehicleMileageReportOptions);
	}
});
$("#report_averageSpeedID").click(function(){
	if(averageSpeed.length > 0){
		highLightTab("report_averageSpeedID");
		vehicleMileageReportOptions.xAxis.categories = [];
		vehicleMileageReportOptions.series[0].data = [];
		for (var i = 0; i < averageSpeed.length; i++) {
			vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
		   vehicleMileageReportOptions.series[0].data.push(averageSpeed[i]);
		}
		var chart = new Highcharts.Chart(vehicleMileageReportOptions);
	}
});
$("#report_emergencybrakecountID").click(function(){
	if(emergencybrakecount.length > 0){
		highLightTab("report_emergencybrakecountID");
		vehicleMileageReportOptions.xAxis.categories = [];
		vehicleMileageReportOptions.series[0].data = [];
		for (var i = 0; i < emergencybrakecount.length; i++) {
			vehicleMileageReportOptions.xAxis.categories.push(createdate[i]);
		   vehicleMileageReportOptions.series[0].data.push(emergencybrakecount[i]);
		}
		var chart = new Highcharts.Chart(vehicleMileageReportOptions);
	}
});


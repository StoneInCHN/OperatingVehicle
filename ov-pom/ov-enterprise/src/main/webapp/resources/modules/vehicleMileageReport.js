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
	
	//每天的行驶公里数
	var dailyMileageOptions = {
			 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
		        title: {text: '<a href="#" style="color: #222222"}>车辆每日里程统计</a>',margin:50,useHTML:true},
		        subtitle: {text: '周期：按天统计'},
				credits : {enabled : false},
		        xAxis: {},
		        yAxis: {
		        	min: 0,
		            title: {text: '里程(KM)'},
		            labels: {formatter: function() {return this.value +'km'}}
		        },
		        tooltip: {crosshairs: true,shared: true,valueSuffix : '千米'},
		        plotOptions: {
		            spline: {
		                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
		            }
		        },
		        series: [{name: '里程',
		            marker: {symbol: 'square'},
		            data: []
		        }]
	}
	//平均油耗
	var averageFuelConsumptionOptions = {
		 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
	        title: {text: '<a href="#" style="color: #222222"}>车辆每日平均耗油统计</a>',margin:50,useHTML:true},
	        subtitle: {text: '周期：按天统计'},
			credits : {enabled : false},
	        xAxis: {},
	        yAxis: {
	        	min: 0,
	            title: {text: '平均耗油(L/100KM)'},
	            labels: {formatter: function() {return this.value +'(L/100KM)'}}
	        },
	        tooltip: {crosshairs: true,shared: true,valueSuffix : '(L/100KM)'},
	        plotOptions: {
	            spline: {
	                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
	            }
	        },
	        series: [{name: '平均耗油',
	            marker: {symbol: 'square'},
	            data: []
	        }]
}
	//当日油耗
	var fuelConsumptionOptions = {
		 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
	        title: {text: '<a href="#" style="color: #222222"}>车辆每日耗油量统计</a>',margin:50,useHTML:true},
	        subtitle: {text: '周期：按天统计'},
			credits : {enabled : false},
	        xAxis: {},
	        yAxis: {
	        	min: 0,
	            title: {text: '耗油量(L)'},
	            labels: {formatter: function() {return this.value +'L'}}
	        },
	        tooltip: {crosshairs: true,shared: true,valueSuffix : '升'},
	        plotOptions: {
	            spline: {
	                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
	            }
	        },
	        series: [{name: '耗油量',
	            marker: {symbol: 'square'},
	            data: []
	        }]
}
	//每日油费
	var costOptions = {
		 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
	        title: {text: '<a href="#" style="color: #222222"}>车辆每日油费统计</a>',margin:50,useHTML:true},
	        subtitle: {text: '周期：按天统计'},
			credits : {enabled : false},
	        xAxis: {},
	        yAxis: {
	        	min: 0,
	            title: {text: '每日油费(￥)'},
	            labels: {formatter: function() {return this.value +'￥'}}
	        },
	        tooltip: {crosshairs: true,shared: true,valueSuffix : '￥'},
	        plotOptions: {
	            spline: {
	                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
	            }
	        },
	        series: [{name: '每日油费',
	            marker: {symbol: 'square'},
	            data: []
	        }]
}
	//平均速度
	var averageSpeedOptions = {
		 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
	        title: {text: '<a href="#" style="color: #222222"}>车辆每日平均速度统计</a>',margin:50,useHTML:true},
	        subtitle: {text: '周期：按天统计'},
			credits : {enabled : false},
	        xAxis: {},
	        yAxis: {
	        	min: 0,
	            title: {text: '平均速度(KM/H)'},
	            labels: {formatter: function() {return this.value +'KM/H'}}
	        },
	        tooltip: {crosshairs: true,shared: true,valueSuffix : 'KM/H'},
	        plotOptions: {
	            spline: {
	                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
	            }
	        },
	        series: [{name: '平均速度',
	            marker: {symbol: 'square'},
	            data: []
	        }]
}
	//驾驶行为（急刹车次数,急转弯次数,急加速次数）
	var drivingBehaviorOptions = {
			 	chart: {type: 'spline', renderTo : "vehicleMileageReportDivId"},
		        title: {text: '<a href="#" style="color: #222222"}>车辆行程统计</a>',margin:50,useHTML:true},
		        subtitle: {text: '周期：按天统计'},
				credits : {enabled : false},
		        xAxis: {},
		        yAxis: {
		        	min: 0,
		            title: {text: '次数'},
		            labels: {formatter: function() {return this.value +'次'}}
		        },
		        tooltip: {crosshairs: true,shared: true,valueSuffix : '次数'},
		        plotOptions: {
		            spline: {
		                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
		            }
		        },
		        series: [{name: '急刹车次数',
		            marker: {symbol: 'square'},
		            data: []
		        },
		        {name: '急转弯次数',
		            marker: {symbol: 'square'},
		            data: []
		        },
		        {name: '急加速次数',
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
			if(rowData.deviceNo != null){
				$('#vehicleMileage_deviceNo').val(rowData.deviceNo);
				deviceId = $('#vehicleMileage_deviceNo').val();
				loadReportDate();//调用接口，加载数据	
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : message("ov.vehicle.plate"),field : "plate",width :"47%",align : 'center',sortable : true},
			{title : message("ov.vehicle.vehicleFullBrand"),field : "vehicleFullBrand",width :"47%",align : 'center',sortable : true},					
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
				highLightTab("report_dailyMileageID");
				dailyMileageOptions.xAxis.categories = [];
				dailyMileageOptions.series[0].data = [];
				for (var i = 0; i < dailyMileage.length; i++) {
					dailyMileageOptions.xAxis.categories.push(createdate[i]);
				   dailyMileageOptions.series[0].data.push(dailyMileage[i]);
				}
				var chart = new Highcharts.Chart(dailyMileageOptions);
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
		dailyMileageOptions.xAxis.categories = [];
		dailyMileageOptions.series[0].data = [];
		for (var i = 0; i < dailyMileage.length; i++) {
			dailyMileageOptions.xAxis.categories.push(createdate[i]);
		   dailyMileageOptions.series[0].data.push(dailyMileage[i]);
		}
		var chart = new Highcharts.Chart(dailyMileageOptions);
	}
});
$("#report_averageFuelConsumptionID").click(function(){
	if(averageFuelConsumption.length > 0){
		highLightTab("report_averageFuelConsumptionID");
		averageFuelConsumptionOptions.xAxis.categories = [];
		averageFuelConsumptionOptions.series[0].data = [];
		for (var i = 0; i < averageFuelConsumption.length; i++) {
			averageFuelConsumptionOptions.xAxis.categories.push(createdate[i]);
			averageFuelConsumptionOptions.series[0].data.push(averageFuelConsumption[i]);
		}
		var chart = new Highcharts.Chart(averageFuelConsumptionOptions);
	}
});
$("#report_fuelConsumptionID").click(function(){
	if(fuelConsumption.length > 0){
		highLightTab("report_fuelConsumptionID");
		fuelConsumptionOptions.xAxis.categories = [];
		fuelConsumptionOptions.series[0].data = [];
		for (var i = 0; i < fuelConsumption.length; i++) {
			fuelConsumptionOptions.xAxis.categories.push(createdate[i]);
			fuelConsumptionOptions.series[0].data.push(fuelConsumption[i]);
		}
		var chart = new Highcharts.Chart(fuelConsumptionOptions);
	}
});
$("#report_costID").click(function(){
	if(cost.length > 0){
		highLightTab("report_costID");
		costOptions.xAxis.categories = [];
		costOptions.series[0].data = [];
		for (var i = 0; i < cost.length; i++) {
			costOptions.xAxis.categories.push(createdate[i]);
			costOptions.series[0].data.push(cost[i]);
		}
		var chart = new Highcharts.Chart(costOptions);
	}
});
$("#report_averageSpeedID").click(function(){
	if(averageSpeed.length > 0){
		highLightTab("report_averageSpeedID");
		averageSpeedOptions.xAxis.categories = [];
		averageSpeedOptions.series[0].data = [];
		for (var i = 0; i < averageSpeed.length; i++) {
			averageSpeedOptions.xAxis.categories.push(createdate[i]);
			averageSpeedOptions.series[0].data.push(averageSpeed[i]);
		}
		var chart = new Highcharts.Chart(averageSpeedOptions);
	}
});
$("#report_emergencybrakecountID").click(function(){
	if(emergencybrakecount.length > 0){
		highLightTab("report_emergencybrakecountID");
		drivingBehaviorOptions.xAxis.categories = [];
		drivingBehaviorOptions.series[0].data = [];
		drivingBehaviorOptions.series[1].data = [];
		drivingBehaviorOptions.series[2].data = [];
		for (var i = 0; i < emergencybrakecount.length; i++) {
		   drivingBehaviorOptions.xAxis.categories.push(createdate[i]);
		   drivingBehaviorOptions.series[0].data.push(emergencybrakecount[i]);
		   drivingBehaviorOptions.series[1].data.push(suddenturncount[i]);
		   drivingBehaviorOptions.series[2].data.push(rapidlyspeedupcount[i]);
		}
		var chart = new Highcharts.Chart(drivingBehaviorOptions);
	}
});


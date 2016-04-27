/**
 *绑定右侧点击事件 
 */
function clickNotificationNews(event) {
	var _this = $(event.target);
	var _url = _this.attr("data-url");
	if($('#manager-tabs').tabs("exists","通知消息")){
		$('#manager-tabs').tabs("select","通知消息");

		// 调用 'refresh' 方法更新选项卡面板的内容
		var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
		tab.panel('refresh', _url);
	}else{
		$('#manager-tabs').tabs('add',{    
		    title:"通知消息",    
		    href:_url,    
		    closable:true      
		}); 
	}
};

/**
 *绑定流程点击事件 
 */
function shortcutNavigation(title,data_url){
	if(title){
		if($('#manager-tabs').tabs("exists",title)){
			$('#manager-tabs').tabs("select",title)
		}else{
			$('#manager-tabs').tabs('add',{    
			    title:title,    
			    href:data_url,    
			    closable:true      
			}); 
		}
	}
};
function changePassword(){
	$('#changePassword').dialog({
	    title: "修改密码",//message("yly.drugsInfo.add"),    
	    width: 500,    
	    height: 480,
	    iconCls:'icon-mini-add',
	    href:'../common/changePassword.jhtml',
	    cache: false, 
	    buttons:[{
	    	text:message("ov.common.save"),
	    	iconCls:'icon-save',
			handler:function(){
				debugger;
				var validate = $('#changePassword_form').form('validate');
				if(validate){
					$.ajax({
						url:"../common/savePassword.jhtml",
						type:"post",
						data:$("#changePassword_form").serialize(),
						beforeSend:function(){
							$.messager.progress({
								text:message("ov.common.saving")
							});
						},
						success:function(result,response,status){
							$.messager.progress('close');
							if(response == "success"){
								showSuccessMsg(result.content);
								$('#changePassword').dialog("close");
							}else{
								alertErrorMsg();
							}
						}
					});
				};
			}
		},{
			text:message("ov.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#changePassword').dialog("close");
			}
	    }],
	    onOpen:function(){
	    	$('#changePassword').show();
	    },
	
	});
}
$(function(){
	/**
	 *初始化右侧的选项卡
	 */
	$("#manager-tabs").tabs({
		fit:true,
		border:false
	});
	
	
	$("#nav-switcher").mouseover(function(){
		$(".nav-wrap").addClass("nav-silde");
		$("#nav-switcherset").show();
	})
	$("#nav-wrap ul").mouseleave(function(){
		$("#nav-wrap").removeClass("nav-silde");
		$("#nav-switcherset").hide();
	})
	
	$("#dropdownMenu1").dropdown();
	//初始化显示首页，隐藏菜单栏
	//$('.easyui-layout').layout('collapse','west');
	
	var westLayour = $('.easyui-layout').layout('panel','west');
	$("#nav-wrap > ul >li >a").click(function(){
		
		var $this = $(this);
		$(".left-content > ul").hide();
		if($this.text()=="首页"){
			$('.easyui-layout').layout('collapse','west');
			$('#manager-tabs').tabs("select",'起始页');
			
			westLayour.hide();
		}else{
			
			$('.easyui-layout').layout('expand','west');
			westLayour.show();
		}
		
		$($this.attr("href")).show();
	})
	
	/**
	 *绑定左侧导航条的点击事件 
	 */
	$(".left-content a").click(function(){
		var _this = $(this);
		var _url = _this.attr("data-url");
		if(_this.text()){
			if($('#manager-tabs').tabs("exists",_this.text())){
				$('#manager-tabs').tabs("select",_this.text())
			}else{
				$('#manager-tabs').tabs('add',{    
				    title:_this.text(),    
				    href:_url,    
				    closable:true      
				}); 
			}
		}
	});

		if($("#useCarRequestMain-table-list")){
		$("#useCarRequestMain-table-list").datagrid({
			title:message("ov.useCarRequest.list"),
			fitColumns:true,
			toolbar:"#useCarRequest_manager_tool",
			url:'../vehicleScheduling/listRequest.jhtml?childrenOrParent=children&rows=20',  
			pagination:true,
			loadMsg:message("ov.common.loading"),
			striped:true,
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("ov.useCarRequest.title"),field:"title",width:80,sortable:true},
			      {title:message("ov.useCarRequest.startDate"),field:"startDate",width:50,sortable:true, 
			    	  formatter:function(value,row,index){
			    		  return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
			    	  }
			      },
			      {title:message("ov.useCarRequest.startPositionDetails"),field:"startPositionDetails",width:100},
			      {title:message("ov.useCarRequest.endPositionDetails"),field:"endPositionDetails",width:100},
			      {title:message("ov.useCarRequest.status"),field:"status",width:30,sortable:true,
			    	  formatter: function(value,row,index){
				    	  if(value == "TO_CONFIRM"){
				    		  return  message("ov.useCarRequest.to_confirm");
				    	  }else if (value == "DISTRIBUTED"){
				    		  return  message("ov.useCarRequest.distributed");
				    	  }else if (value == "FINISHED"){
				    		  return  message("ov.useCarRequest.finished");
				    	  }else if (value == "CANCELLED"){
				    		  return  message("ov.useCarRequest.cancelled");
				    	  }else if (value == "REJECTED"){
				    		  return  message("ov.useCarRequest.rejected");
				    	  }else if (value == "BREAK_CONTRACT"){
				    		  return  message("ov.useCarRequest.break_contract");
				    	  }else if (value == "CLEARED"){
				    		  return  message("ov.useCarRequest.cleared");
				    	  }
			      	  }  
			      },
			   ]
			]
		});
		}
		
		//初始化Highcharts
		function resetHighcharts(){
			var upkeepChargeReportDiv = $('#upkeepChargeReportDiv').highcharts();
			upkeepChargeReportDiv.reflow();
			var maintenanceChargeReportDiv = $('#maintenanceChargeReportDiv').highcharts();
			maintenanceChargeReportDiv.reflow();
			var oilChargeReportDiv = $('#oilChargeReportDiv').highcharts();
			oilChargeReportDiv.reflow();
			var vehicleMileageChargeReportDiv = $('#vehicleMileageChargeReportDiv').highcharts();
			vehicleMileageChargeReportDiv.reflow();
		}
		//车辆维修费报表
		var upkeepChargeReportOptions = {
				 	chart: {type: 'spline', renderTo : "upkeepChargeReportDiv"},
			        title: {text: '<a href="#" style="color: #222222"}>车辆维修次数统计(总)</a>',margin:20,useHTML:true},
			        subtitle: {text: '周期：按月统计'},
					credits : {enabled : false},
			        xAxis: {},
			        yAxis: {
			        	min: 0,
			            title: {text: '费用/次数'},
			            labels: {formatter: function() {return this.value +'次'}}
			        },
			        tooltip: {crosshairs: true,shared: true,valueSuffix : '次'},
			        plotOptions: {
			            spline: {
			                marker: {radius: 4,lineColor: '#666666',lineWidth: 1}
			            }
			        },
			        series: [{name: '次数',
			            marker: {symbol: 'square'},
			            data: []
			        }]
		};
	    loadDataLine(upkeepChargeReportOptions,"../../console/upkeepChargeReport/reportAll.jhtml",null,
		"upkeepChargeStatisticsDate","upkeepNumber");
	    
		  //车辆行程统计报表
	    var vehicleMileageChargeReportOptions = {
	    		chart: {type: 'column',margin: [ 50, 50, 100, 80], renderTo : "vehicleMileageChargeReportDiv"},
		        title: {text: '<a href="#" style="color: #222222">车辆行程统计(总)</a>',margin:20,useHTML:true},
		        subtitle: {text: '周期：按月统计'},
		        xAxis: {},
		        yAxis: {
		            min: 0,
		            title: {text: '行程/千米'},
		            labels: {formatter: function() {return this.value +'km'}}
		        },
		        credits : {enabled : false},
		        legend: {enabled: false},
		        tooltip: {crosshairs: true,shared: true,valueSuffix : '千米'},
		        series: [{
		            name: '行程',
		            data: [],
		            dataLabels: { enabled: true,rotation: -90,	color: '#FFFFFF',align: 'right',x: 4,y: 10,
		                	style: {fontSize: '11px',fontFamily: 'Verdana, sans-serif',textShadow: '0 0 1px black'}
		            }
		        }]
	    };
	    loadDataLine(vehicleMileageChargeReportOptions,"../../console/vehicleMileageReport/reportAll.jhtml",null,
		"vehicleMileageStatisticsDate","mileage");
	    
		  //车辆保养费报表
	    var maintenanceChargeReportOptions = {
		        chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false,renderTo : "maintenanceChargeReportDiv"},
		        title: {text: '<a href="#" style="color: #222222">车辆保养次数统计(总)</a>',margin:20,useHTML:true},
		        subtitle: {text: '周期：按年统计'},
		        credits : {enabled : false},
		        tooltip: {pointFormat: '{series.name}: <b>共计:{point.y} 次 占百分比{point.percentage:.1f}%</b>'},
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#222222',
		                    connectorColor: '#222222',
		                    format: '<b>{point.name}</b>: {point.y} 次数'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '保养次数',
		            data: []
		        }]
		    
	    };
	    loadDataPie(maintenanceChargeReportOptions,"../../console/maintenanceChargeReport/reportAll.jhtml",null,
		"maintenanceChargeStatisticsDate","maintenanceNumber");
	    
	    //油费统计表
	    var oilChargeAmountReportDivOptions = {
			    chart: {type: 'bubble',zoomType: 'xy',renderTo:"oilChargeAmountReportDiv"},
		        xAxis: {},
		        yAxis: {
		            min: 0,
		            title: {text: '次数/次'},
		            labels: {formatter: function() {return this.value +'次'}}
		        },
			    title: {text: '<a href="#" style="color: #222222">每月车辆加油次数统计(总)</a>',margin:20,useHTML:true},
			    subtitle: {text: '周期：按月统计'},
			    credits : {enabled : false},
		        tooltip: {
		            pointFormat: '<b>{point.y} 次</b>',
		        },
			    series: [{
			    	name: '次数',
			        data: []
			    }]
	    };
	    var oilChargeCountReportDivOptions = {
			    chart: {type: 'bubble',zoomType: 'xy',renderTo:"oilChargeCountReportDiv"},
		        xAxis: {},
		        yAxis: {
		            min: 0,
		            title: {text: '用量/升'},
		            labels: {formatter: function() {return this.value +'L'}}
		        },
			    title: {text: '<a href="#" style="color: #222222">每月车辆用油量统计(总)</a>',margin:20,useHTML:true},
			    subtitle: {text: '周期：按月统计'},
			    credits : {enabled : false},
		        tooltip: {
		            pointFormat: '<b>{point.y:.1f} 升</b>',
		        },
			    series: [{
			    	name: '用油量',
			        data: []
			    }]
	    };
	    loadDataBubble(oilChargeAmountReportDivOptions, oilChargeCountReportDivOptions,"../../console/oilChargeReport/reportAll.jhtml",null,
	    		"oilChargeReportStatisticsDate","oilNumber","oilCount");
})


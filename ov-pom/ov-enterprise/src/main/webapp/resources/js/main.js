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
	$('.easyui-layout').layout('collapse','west');
	
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
		/**
		 * 修改密码事件
		 */
		$("#changePasswordHref").click(function(){
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
		});
		
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
				colors : [ '#004B97' ],
				chart : {
					renderTo : 'upkeepChargeReportDiv',
					backgroundColor: {
	        			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	        			stops: [
	            				[0, 'rgb(250, 250, 250)'],
	            				[1, 'rgb(221, 221, 221)']
	            		]
	        		},
					plotBackgroundColor : 'rgba(255, 255, 255, .9)',
					plotBorderWidth : 1
				},
				title : {
					text : '每月车辆维修费统计',
					x : -20
				//center
				},
				credits : {
					enabled : false
				// 禁用版权信息
				},
				xAxis : {
					gridLineWidth : 1,
					lineColor : '#000',
					categories : []
				},
				yAxis : {
					minorTickInterval : 'auto',
					lineColor : '#000',
					lineWidth : 1,
					tickWidth : 1,
					tickColor : '#000',
					title : {
						text : '费用/元'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					valueSuffix : '元'
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : '费用',
					data : []
				} ]
			};
		    loadDataLine(upkeepChargeReportOptions,"../../console/upkeepChargeReport/report.jhtml",null,
				"upkeepChargeStatisticsDate","upkeepAmount");
		  //车辆保养费报表
			var maintenanceChargeReportOptions = {
					colors : [ '#004B97' ],
					chart : {
						renderTo : 'maintenanceChargeReportDiv',
						backgroundColor: {
		        			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		        			stops: [
		            				[0, 'rgb(250, 250, 250)'],
		            				[1, 'rgb(221, 221, 221)']
		            		]
		        		},
						plotBackgroundColor : 'rgba(255, 255, 255, .9)',
						plotBorderWidth : 1
					},
					title : {
						text : '每月车辆保养费统计',
						x : -20
					//center
					},
					credits : {
						enabled : false
					// 禁用版权信息
					},
					xAxis : {
						gridLineWidth : 1,
						lineColor : '#000',
						categories : []
					},
					yAxis : {
						minorTickInterval : 'auto',
						lineColor : '#000',
						lineWidth : 1,
						tickWidth : 1,
						tickColor : '#000',
						title : {
							text : '费用/元'
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					},
					tooltip : {
						valueSuffix : '元'
					},
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'middle',
						borderWidth : 0
					},
					series : [ {
						name : '费用',
						data : []
					} ]
				};
			    loadDataLine(maintenanceChargeReportOptions,"../../console/maintenanceChargeReport/report.jhtml",null,
					"maintenanceChargeStatisticsDate","maintenanceAmount");
				  //车辆保养费报表
				var oilChargeReportDivOptions = {
						colors : [ '#004B97' ],
						chart : {
							renderTo : 'oilChargeReportDiv',
							backgroundColor: {
			        			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
			        			stops: [
			            				[0, 'rgb(250, 250, 250)'],
			            				[1, 'rgb(221, 221, 221)']
			            		]
			        		},
							plotBackgroundColor : 'rgba(255, 255, 255, .9)',
							plotBorderWidth : 1
						},
						title : {
							text : '每月车辆油费统计',
							x : -20
						//center
						},
						credits : {
							enabled : false
						// 禁用版权信息
						},
						xAxis : {
							gridLineWidth : 1,
							lineColor : '#000',
							categories : []
						},
						yAxis : {
							minorTickInterval : 'auto',
							lineColor : '#000',
							lineWidth : 1,
							tickWidth : 1,
							tickColor : '#000',
							title : {
								text : '费用/元'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						},
						tooltip : {
							valueSuffix : '元'
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'middle',
							borderWidth : 0
						},
						series : [ {
							name : '费用',
							data : []
						} ]
					};
				    loadDataLine(oilChargeReportDivOptions,"../../console/oilChargeReport/report.jhtml",null,
						"oilChargeReportStatisticsDate","oilFinalAmount");
					  //车辆保养费报表
					var vehicleMileageChargeReportOptions = {
							colors : [ '#004B97' ],
							chart : {
								renderTo : 'vehicleMileageChargeReportDiv',
								backgroundColor: {
				        			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
				        			stops: [
				            				[0, 'rgb(250, 250, 250)'],
				            				[1, 'rgb(221, 221, 221)']
				            		]
				        		},
								plotBackgroundColor : 'rgba(255, 255, 255, .9)',
								plotBorderWidth : 1
							},
							title : {
								text : '每月车辆行程统计',
								x : -20
							//center
							},
							credits : {
								enabled : false
							// 禁用版权信息
							},
							xAxis : {
								gridLineWidth : 1,
								lineColor : '#000',
								categories : []
							},
							yAxis : {
								minorTickInterval : 'auto',
								lineColor : '#000',
								lineWidth : 1,
								tickWidth : 1,
								tickColor : '#000',
								title : {
									text : '费用/元'
								},
								plotLines : [ {
									value : 0,
									width : 1,
									color : '#808080'
								} ]
							},
							tooltip : {
								valueSuffix : '元'
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'middle',
								borderWidth : 0
							},
							series : [ {
								name : '费用',
								data : []
							} ]
						};
					    loadDataLine(vehicleMileageChargeReportOptions,"../../console/vehicleMileageReport/report.jhtml",null,
							"vehicleMileageStatisticsDate","mileage");
})


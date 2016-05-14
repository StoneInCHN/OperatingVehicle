<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.main.reportUserReg")}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/bootstrap-theme.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/dialog.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/list.css" rel="stylesheet" type="text/css" />
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="${base}/resources/js/html5shim.js"></script>
  <![endif]-->
</head>
<body>
<div class="mainbar">
				<div class="page-head">
					<div class="bread-crumb">
						<a ><i class="fa fa-user"></i> ${message("ov.main.report")}</a> 
						<span class="divider">/</span> 
						<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("ov.report.reportUserReg")}</a>
					</div>
					<div class="clearfix"></div>
				</div>
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-12 col-md-12 col-lg-12">
						  		<ul class="nav">
									  <li class="dateClass">
											${message("ov.operate.log.date")}: <input type="text" id="beginDate" name="beginDate" class="text Wdate" value="${(beginDate?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
											~~ <input type="text"  id="endDate" name="endDate" class="text Wdate" value="${(endDate?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});" />
									  	  <button id="reportSearch" class="btn btn-default"style="margin-left:30px" >查询</button>
									  </li>
								</ul>
						  </div>
					</div>
				</div>
				
				<div class="matter">
					<div class="container">
						<div class="row">
			              <div class="col-md-12">
			                <div class="widget">
									 <div class="widget-head">
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("ov.report.reportUserReg")}</div>
						                  <div class="widget-icons pull-right">
						                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
						                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
						                  </div>  
						                  <div class="clearfix"></div>
						              </div>
						              <div class="widget-content">
											<div id="userRegReport" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
									</div>
								</div>
							</div>
						</div>
					 </div>
				</div>
</div>
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${base}/resources/js/highcharts/exporting.js"></script>
<script type="text/javascript">
		$(function () {
			var $reportSearch = $("#reportSearch");
			var $userRegReport = $("#userRegReport");
			var $beginDate = $("#beginDate");
			var $endDate = $("#endDate");
			
		    $userRegReport.highcharts({
		        title: {
		            text: '用户注册报表',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '来源: 车队',
		            x: -20
		        },
		        xAxis: {
		            categories: []
		        },
		        yAxis: {
		            title: {
		                text: '人数'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: '人'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: '注册人数',
		            data: []
		        }]
		    });
		    
		   $reportSearch.click(function(){
		   		var datas ={
		   			"beginDate":$beginDate.val(),
		   			"endDate":$endDate.val()
		   		}
		   		$.get("getUserRegReport.jhtml",datas,function(result){
		    		console.log(result);
		    		var seriesData = new Array();
		    		var xAxisData = new Array();
		    		if(result){
		    			for(index in result){
		    				seriesData.push(result[index].regNum);
		    				xAxisData.push(result[index].statisticsDate);
		    			}
		    		}
		    	var chart = $('#userRegReport').highcharts();
		   		chart.series[0].setData(seriesData);
		   		chart.xAxis[0].setCategories(xAxisData);
		 	  });
		   });
		   
		   var initDatas ={
		   			
		   		}
		   $.get("getUserRegReport.jhtml",initDatas,function(result){
		    		var seriesData = new Array();
		    		var xAxisData = new Array();
		    		if(result){
		    			for(index in result){
		    				seriesData.push(result[index].regNum);
		    				xAxisData.push(result[index].statisticsDate);
		    			}
		    		}
		    	var chart = $('#userRegReport').highcharts();
		   		chart.series[0].setData(seriesData);
		   		chart.xAxis[0].setCategories(xAxisData);
		 	  });
		    
		});
</script>
</body>
</html>
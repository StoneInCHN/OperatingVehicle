<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("ov.deviceInfo.list")}</title>
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
						<a ><i class="fa fa-user"></i> ${message("ov.main.deviceInfo")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("ov.main.deviceInfo.list4distributor")}(${message("ov.page.total", page.total)})</span>
					</div>
					<div class="clearfix"></div>
				</div>
			
			<form id="listForm" action="list4distributor.jhtml" method="get">
				<input type="hidden" id="deviceStatus" name="deviceStatus" value="${deviceStatus}" />
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-9 col-md-9 col-lg-9">
						  		<ul class="nav">
									 <li class="pull-left">
										<div class="btn-group operationButton">
										  <button type="button" id="refreshButton" class="btn btn-default"><i class="fa fa-refresh"></i>&nbsp;&nbsp;${message("ov.common.refresh")}</button>
										</div>
										<div class="btn-group operationButton">
											<button type="button" id="deviceProvide"  class="btn btn-default disabled"><i class="fa fa-wrench"></i>${message("ov.deviceInfo.deviceProvide")}</button>
										</div>
									</li>
									  <li role="presentation" class="dropdown pull-right">
										    <a id="pageSizeSelect" aria-expanded="false" role="button" aria-haspopup="true" data-toggle="dropdown" class="dropdown-toggle" href="#">
										      ${message("ov.page.pageSize")} <span class="caret"></span>
										    </a>
										    <ul id="pageSizeOption" class="dropdown-menu" role="menu" aria-labelledby="pageSizeSelect">
										     	<li>
													<a href="javascript:;"[#if page.pageSize == 10] class="active"[/#if] val="10">10</a>
												</li>
												<li>
													<a href="javascript:;"[#if page.pageSize == 20] class="active"[/#if] val="20">20</a>
												</li>
												<li>
													<a href="javascript:;"[#if page.pageSize == 50] class="active"[/#if] val="50">50</a>
												</li>
												<li>
													<a href="javascript:;"[#if page.pageSize == 100] class="active"[/#if] val="100">100</a>
												</li>
										    </ul>
									  </li>
										<li role="presentation" class="dropdown pull-right ">
											<a href="javascript:;" id="filterSelect" aria-expanded="false" role="button" aria-haspopup="true" data-toggle="dropdown" class="dropdown-toggle" href="#">
													${message("ov.deviceInfo.deviceStatus.filter")}<span class="caret"></span>
											</a>
											<ul id="filterOption" class="dropdown-menu" role="menu" aria-labelledby="filterSelect">
												<li>
													<a href="javascript:;" name="deviceStatus" val="" [#if deviceStatus == null] class="checked"[/#if]>${message("ov.deviceInfo.deviceStatus.all")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="SENDOUT" [#if deviceStatus == "SENDOUT"] class="checked"[/#if]>${message("ov.deviceInfo.deviceStatus.SENDOUT")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="STORAGEOUT" [#if deviceStatus == "STORAGEOUT"] class="checked"[/#if]>${message("ov.deviceInfo.deviceStatus.STORAGEOUT")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="BINDED" [#if deviceStatus == "BINDED"] class="checked"[/#if]>${message("ov.deviceInfo.deviceStatus.BINDED")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="REFUNDED" [#if deviceStatus == "REFUNDED"] class="checked"[/#if]>${message("ov.deviceInfo.deviceStatus.REFUNDED")}</a>
												</li>
											</ul>
										</li>
									</ul>
						  </div>
						  <div class="col-xs-3 col-md-3 col-lg-3">
						  		<div class="input-group">
								      <div class="input-group-btn">
								        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">${message("ov.common.choose")} <span class="caret"></span></button>
								        <ul class="dropdown-menu menuWrap" id="searchPropertyOption" role="menu">
								          <li [#if page.searchProperty == "deviceNo" || page.searchProperty ==null] selected="selected" class="active" [/#if] title="deviceNo"><a href="#">${message("ov.deviceInfo.deviceNo")}</a></li>
								        </ul>
								      </div>
								      <input type="text" class="form-control" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
							    </div>
						  </div>
					</div>
				</div>
				
				<div class="matter">
					<div class="container">
						<div class="row">
			              <div class="col-md-12">
			                <div class="widget">
									 <div class="widget-head">
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("ov.main.deviceInfo")}</div>
						                  <div class="clearfix"></div>
						              </div>
						              <div class="widget-content">
										<table id="listTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="check">
														<input type="checkbox" id="selectAll" />
													</th>
													<th>
														<a href="javascript:;" class="sort" name="bindTime">${message("ov.deviceInfo.bindTime")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="unBindTime">${message("ov.deviceInfo.unBindTime")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="deviceNo">${message("ov.deviceInfo.deviceNo")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="simNo">${message("ov.deviceInfo.simNo")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="type">${message("ov.deviceInfo.type")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="deviceStatus">${message("ov.deviceInfo.deviceStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="bindStatus">${message("ov.deviceInfo.bindStatus")}</a>
													</th>
													<th>
														<span>${message("ov.common.handle")}</span>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as deviceInfo]
												<tr>
													<td>
														[#if deviceInfo.deviceStatus?? && deviceInfo.deviceStatus == "SENDOUT"]
														  <input type="checkbox"  name="ids"   value="${deviceInfo.id}" />
														[#else]
															<input type="checkbox"  name="ids"  title="${message("ov.role.deleteSystemNotAllowed")}" disabled="disabled" value="${deviceInfo.id}" />
														[/#if]
													</td>
													<td>
														[#if deviceInfo.bindTime??]
															<span title="${deviceInfo.bindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.bindTime}</span>
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if deviceInfo.unBindTime??]
															<span title="${deviceInfo.unBindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.unBindTime}</span>
														[#else]
															-
														[/#if]
													</td>
													<td>
														${deviceInfo.deviceNo}
													</td>
													<td>
														${deviceInfo.simNo}
													</td>
													<td>
														${deviceInfo.type.name}
													</td>
													<td>
														${message("ov.deviceInfo.deviceStatus."+deviceInfo.deviceStatus)}
													</td>
													<td>
														${message("ov.deviceInfo.bindStatus."+deviceInfo.bindStatus)}
													</td>
													<td>
														<a href="details.jhtml?id=${deviceInfo.id}" title="${message("ov.common.details")}"><i class="fa fa-eye"></i></a>
													</td>
												</tr>
											[/#list]
											</tbody>
										</table>
										<div class="widget-foot">
					                       [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
												[#include "/include/pagination.ftl"]
										   [/@pagination]
				                   		 </div>
									</div>
								</div>
							</div>
						</div>
					 </div>
				</div>
			</form>
</div>

<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $deviceProvide = $("#deviceProvide");
	var $deviceStatus = $("#deviceStatus");
	
	$deviceProvide.click(function(){
		var $deviceBinding = window.parent.$('#operationModal');
		var $operationModalIframe= window.parent.$('#operationModalIframe');
		$deviceBinding.find(".modal-title").text("设备发放");
		$deviceBinding.modal("show");
		$deviceBinding.attr("data-ids","&"+$("#listTable input[name='ids']:checked").serialize());
		$operationModalIframe.attr("src","${base}/console/deviceInfo/tenantInfoList4distributor.jhtml");
		$operationModalIframe.css("height",380);
	
	})
	
	// 筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left - 20, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});

});
</script>
</body>
</html>
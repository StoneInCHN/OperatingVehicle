<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("csh.resourceNotFound.title")}[#if systemShowPowered] - Powered By DaZhong Mall[/#if]</title>
<link href="${base}/resources/shop/css/shopcom.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	
    var $sort = $(".sort");
    $sort.css("display", "none");	

});
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="container error">
		<div class="span24">
			<div class="main">
				<dl>
					${message("csh.resourceNotFound.message")}
					<dd>
						<a href="javascript:;" onclick="window.history.back(); return false;">&gt;&gt; ${message("csh.resourceNotFound.back")}</a>
					</dd>
					<dd>
						<a href="${base}/">&gt;&gt; ${message("csh.resourceNotFound.home")}</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>
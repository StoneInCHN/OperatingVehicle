var messages = {
		/**
		 * common
		 */
		"ov.common.createDate" : "创建时间",
		"ov.common.modifyDate" : "修改时间",
		"ov.common.title" : "标题",
		"ov.common.loading" : "加载中...",
		"ov.common.save" : "保存",
		"ov.common.saving" : "保存中...",
		"ov.common.add" : "添加",
		"ov.common.edit" : "编辑",
		"ov.common.remove" : "删除",
		"ov.common.cancel" : "取消",
	    "ov.common.close" : "关闭",
		"ov.common.progress" : "正在处理中...",
		"ov.common.prompt" : "操作提示",
		"ov.common.success" : "操作成功",
		"ov.common.fail" : "操作失败",
		"ov.common.unknow.error" : "未知错误",
		"ov.common.select.editRow" : "请选择要编辑的记录",
		"ov.common.select.editRow.unique" : "只能选择一条记录修改",
		"ov.common.select.deleteRow" : "请选择要删除的内容",
		"ov.common.confirm" : "确认",
		"ov.common.delete.confirm" : "您确认想要删除记录吗？",
		"ov.common.gender": "性别",
		"ov.common.name":"姓名",
		"ov.common.age":"年龄",
		"ov.common.birthday":"出生日期",
		"ov.common.infoChannel":"信息来源",
		"ov.common.remark":"备注",
		"ov.common.phonenumber":"电话号码",
		"ov.common.male":"男",
		"ov.common.female":"女",
		"ov.common.other":"其它",
		"ov.common.yes":"是",
		"ov.common.no":"否",
		"ov.common.detail":"详情",
		"ov.common.idcard":"身份证号码",
		"ov.common.disable":"禁用",
		"ov.common.enable":"启用",
		"ov.common.bedRoom":"房间",
		"ov.common.please.select":"请选择...",
		"ov.common.insertDate": "录入时间",
		"ov.common.notice":"注意",
		"ov.common.notice.current_condition_no_export_data":"当前条件无可导出的数据。",
		"ov.common.notice.comfirm_export_data":"确定导出 {0} 条记录？",
		"ov.common.notice.export_data_too_much_advice_use_filter":"导出数据超过 {0} 条数据，建议搜索查询条件以缩小查询范围，再导出。",
		"ov.common.notice.need_wait_export_too_much_data":"导出共有 {0} 条数据，导出超过 {1} 条数据可能需要您耐心等待，仍需操作请确定继续。",
		"ov.common.address":"地址",
		"ov.common.print":"打印",
		"ov.common.action":"操作",
		"ov.common.dateFormatChina":"yyyy年MM月dd日",
		


		//租户用户
		"ov.tenantUser.search":"用户查询",
		"ov.tenantUser.list":"用户列表",
		"ov.tenantUser.realName":"姓名",
		"ov.tenantUser.gender":"性别",
		"ov.tenantUser.age":"年龄",
		"ov.tenantUser.staffID":"员工编号",
		"ov.tenantUser.staffStatus":"员工状态",
		"ov.tenantUser.staffStatus.inService":"在职",
		"ov.tenantUser.staffStatus.outService":"离职",
		"ov.tenantUser.department":"所在部门",
		"ov.tenantUser.position":"担任职务",
		"ov.tenantUser.hireDate":"入职时间",
		
		//department
		"ov.department.name":"部门名称",
		"ov.department.grade":"部门层级",
		"ov.department.parent":"上级部门",
		"ov.department.add":"添加部门",
		
		//position
		"ov.position.add":"添加职位",
		"ov.position.name":"职位名称",
		"ov.position.department":"隶属部门",

		//权限管理
		"ov.role.record":"角色管理",
		"ov.role.name":"角色名称",
		"ov.role.description":"描述",
		"ov.role.add":"添加角色",
		"ov.role.auth":"授权",
		"ov.role.auth.manange":"权限分配",
		"ov.role.auth.name":"菜单名称",
		"ov.role.auth.status":"权限状态",
		// 租户用户
		"ov.tenantAccount.add":"添加用户",
		"ov.tenantAccount.userName":"用户名",
		"ov.tenantAccount.realName":"真实姓名",
		"ov.tenantAccount.isSystem":"是否内置",
		"ov.tenantAccount.accoutStatus":"账号状态",
		"ov.tenantAccount.loginDate":"最后登录时间",
		"ov.tenantAccount.loginIp":"最后登录IP",
		"ov.tenantAccount.active":"账号正常",
		"ov.tenantAccount.list":"用户列表",
		"ov.tenantAccount.locked":"账号锁定",
		
		//数据字典
		"ov.systemConfig.list":"数据字典",
		"ov.systemConfig.configValue":"配置项值",
		"ov.systemConfig.isEnabled":"是否启用",
		"ov.systemConfig.add":"添数据字典",
		"ov.systemConfig.edit":"编辑数据字典",
			
};
//多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(
							new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1),
						function(i, n) {
							content = content.replace(new RegExp("\\{" + i
									+ "\\}", "g"), n);
						});
				return content;
			}
		}
	}
}

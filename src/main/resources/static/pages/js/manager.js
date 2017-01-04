var initTable = function() {
	$.post("/manager/initTable").done(function(data) {
		if (data == "success") {
			$.messager.alert('提示', '数据初始化成功!');
		} else {
			$.messager.alert('错误', '数据初始化失败!');
		}
	}).fail(function(data) {
		$.messager.alert('错误', '数据初始化失败!');
	})

}
var expExcel = function() {
	$.post("/manager/expExcel").done(function(data) {
		if (data== "success") {
			$.messager.alert('提示', '数据初始化成功!');
		} else {
			$.messager.alert('错误', '数据初始化失败!');
		}
	}).fail(function(data) {
		$.messager.alert('错误', '生成文件失败!');
	})

}
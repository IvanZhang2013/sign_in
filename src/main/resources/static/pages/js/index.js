var query = function() {
	var signCode = $("#signCode").textbox('getValue');
	if(signCode==null||signCode==""){
		$.messager.alert('错误','请填写验证码！');
	}else{
		$.post("/index/query",{signCode:signCode}).done(function(data){
			if(data.signCode!=null||data.signCode!=""){
				$('#custName').textbox('setValue',data.custName);
				$('#planNum').textbox('setValue',data.planNum);
				$('#actualNum').textbox('setValue',data.actualNum);
				$('#qiandaoNum').textbox('setValue',"");
			}else{
				$.messager.alert('错误','验证码不存在！');
			}
		}).fail(function(data){
			$.messager.alert('错误','数据请求失败');
		})
	}
}

var update = function() {
	var signCode = $("#signCode").textbox('getValue');
	var actualNum = $("#qiandaoNum").textbox('getValue');
	if(actualNum==""||actualNum==null){
		$.messager.alert('错误','签到人数不能为空！！');
		return;
	}
	if(signCode==null||signCode==""){
		$.messager.alert('错误','请填写验证码！');
	}else{
		$.post("/index/update",{signCode:signCode,actualNum:actualNum}).done(function(){
			$('#custName').textbox('setValue',"");
			$('#planNum').textbox('setValue',"");
			$('#actualNum').textbox('setValue',"");
			$('#qiandaoNum').textbox('setValue',"");
			$.messager.alert('提示','签到成功！');
		}).fail(function(data){
			$.messager.alert('错误','数据请求失败！');
		})
	}
	
}
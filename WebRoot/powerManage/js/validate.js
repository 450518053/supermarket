$(function(){
	$.extend($.fn.validatebox.defaults.rules, {
		//长度
		length: { validator: function (value, param) {
			var len = $.trim(value).length;
			return len >= param[0] && len <= param[1];
		},
		message: "输入内容长度必须介于{0}和{1}位之间."
		},
		//正整数
		interger: { validator: function (value, param) {

			var rule=/^[1-9]\d*$/;
			return  rule.test(value); 
		}, 
		message : '请输入正整数' 
		},
		mobile : {// 验证手机号码 
			validator : function(value) { 
				return /^(13|15|18)\d{9}$/i.test(value); 
			}, 
			message : '请输入正确的手机号' 
		}, 
		//身份证待用
		idcard: { validator: function (value, param) {

			var rule=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
			return  rule.test(value); 
		}, 
		message : '请输入正确的身份证号码' 
		}, 
		//非负数
		nonnegative: { validator: function (value, param) {

			var rule=/^\d+(\.{0,1}\d+){0,1}$/;
			return  rule.test(value); 
		}, 
		message : '请输入非负数数字' 
		}, 
		//网站
		web_address: { validator: function (value, param) {

			var rule=/^((https?|ftp|news):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
			return  rule.test(value); 
		}, 
		message : '请输入正确的网站' 
		}, 
		tel_area:{
			//电话区号
			validator : function(value) { 
				var rule=/(0[1-9]{2,3})/;
				return  rule.test(value); 
			}, 
			message : '请输入正确的电话区号' 
		},
		tel_number:{
			//电话区号
			validator : function(value) { 
				var rule=/([2-9][0-9]{6,7})/;
				return  rule.test(value); 
			}, 
			message : '请输入正确的电话号码' 
		},
		fax_number:{
			//传真号码
			validator : function(value) { 
				var rule=/^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
				return  rule.test(value); 
			}, 
			message : '请输入正确的传真号码' 
		},
		post_code:{
			//邮政编码
			validator : function(value) { 
				var rule=/^[1-9]\d{5}$/;
				return  rule.test(value); 
			}, 
			message : '请输入正确的邮政编码' 
		},
		month:{
			//月份
			validator : function(value) { 
				var rule=value;
				return  rule<=11&&rule>=1; 
			}, 
			message : '请输入正确的月份' 
		},
		
		year:{
			//年龄
			validator : function(value) { 
				var fromtime=value;
				var totime=$("#toTime").combobox("getValue");
				return fromtime<totime;
			}, 
			message : '起始日期不能大于截止日期' 
		},
		
		//下拉选框
		selectValueRequired: { 
			validator: function(value,param){ 
				if(value !=null|| value !=""){

					return true;

				}
				else{

					return false;
				}
			}, 
			message: '请选择' 
		},
		//验证用户注册确认密码是否和密码一致
		currentPwd: { 
			validator: function(value){ 
				 var pwd=$("#user_pwd").val();
				 return pwd==value;
			}, 
			message: '两次密码输入不一致' 
		},
		//验证用户修改确认密码是否和密码一致
		modifypwd: { 
			validator: function(value){ 
				 var pwd=$('#newpwd').val()
				 return pwd==value;
			}, 
			message: '两次密码输入不一致' 
		}
	});	
});

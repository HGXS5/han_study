(function($) {
	var div_html = '<div>键：<input type="text" style="width:100px" name="keyValue.key"/>'
		+ '&nbsp;值：<input type="text" style="width:300px" name="keyValue.value"/>'
		+ '<input type="button" style="margin-left:5px"/></div>';
	
	$.fn.extend({
		keyValue : function(_function, _param) {
			if (typeof _function == "string") {
				return kv.methods[_function](this, _param);
			}
			_function = _function || {};
			// 不带参数则调setValue初始化,url=null
			var options = $.extend({}, kv.defaults, _function);
			this.data('options', options);
			return kv.methods["setValue"](this);
		},
	});
	
	$.fn.keyValue.defaults = {
		validLength : 100,
		separator : '##'
	};

	$.fn.keyValue.methods = {
		// 获取option参数
		options : function(jq){
			var options = jq.data('options');
			if(!options){
				options = kv.defaults;
			}
			return options;
		},
		// 初始化设置key value值
		setValue : function(jq, url) {
			var option = kv.methods['options'](jq);
			jq.children().remove();
			var ss;
			if(url){
				ss = url.split(option.separator);
			}
			if(ss && ss.length>0){
				for ( var i = 0; i < ss.length; i++) {
					var value = ss[i].split("#");
					if (i == 0) { //第一个为增加
						var div = kv.methods.addKeyValue(jq, {key:value[0],value:value[1],buttonType:'add'});
						//div.append('<font>&nbsp;键值长度不超过'+option.validLength+'个字符</font>');
					} else { //后续为删除
						kv.methods.addKeyValue(jq, {key:value[0],value:value[1],buttonType:'remove'});
					}
				}
			}else{
				var div = kv.methods.addKeyValue(jq, {key:'',value:'',buttonType:'add'});
				//div.append('<font>&nbsp;键值长度不超过'+option.validLength+'个字符</font>');
			}
			return jq;
		},
		// 获取返回结果
		getValue : function(jq) {
			var option = kv.methods['options'](jq);
			// 处理动态参数
			var keys = new Array();
			var values = new Array();
			var flag = true;
			
			/*jq.find('input[name="keyValue.key"]').each(function(i) {
				keys[i] = $(this).val();
			});
			jq.find('input[name="keyValue.value"]').each(function(i) {
				values[i] = $(this).val();
			});*/
			
			
			for(var i =0; i < jq.find('input[name="keyValue.key"]').length; i++){
					var key = jq.find('input[name="keyValue.key"]:eq('+ i+')').val().trim();
					keys[i] = key;					
					values[i] = jq.find('input[name="keyValue.value"]:eq('+ i+')').val().trim();
			}
			
			//key value 验证
			var s = keys.join(",")+",";
			for(var i=0;i<keys.length;i++) {
				if(s.replace(keys[i]+",","").indexOf(keys[i]+",")>-1&&keys[i]!= "") {
							try { 
							throw new Error("自定义参数:key 值不能重复 key=" + keys[i]) 
							} 
							catch (e) 
							{ 
							alert(e.message);
							flag = false;
							break;
							}
					
					
				}
			}
									
			var param = "";
			for ( var i = 0; i < keys.length; i++) {
				if (keys[i] != "" && values[i] != "") {
					param += option.separator + keys[i] + '#' + values[i];
				}else if(keys[i] != "" && values[i] == ""){
					flag = false;
					alert("自定义参数:键不为空，值不能为空") 
					break;					
				}else if(keys[i] == "" && values[i] != ""){
					flag = false;
					alert("自定义参数:值不为空，键不能为空") 
					break;					
				}
			}
			if (param.length > 0) {
				param = param.substr(2);
			}
			if(flag){
				return param;
			}else{
				return false;
			}
			
		},
		// param={key:'',value:'',buttonType:'remove|add'}
		addKeyValue : function(jq, param) {
			var option = kv.methods['options'](jq);
			var len = jq.children('div').length;
			jq.append(div_html);
			var div = jq.children().last();
			div.attr('id',jq.attr('id')+'_keyvalue_'+len);
			//div.children('input:eq(0)').val(param.key).attr('id',jq.attr('id')+'_key_'+len).validatebox({validType:'length[0,'+option.validLength+']'});
			//div.children('input:eq(1)').val(param.value).attr('id',jq.attr('id')+'_value_'+len).validatebox({validType:'length[0,'+option.validLength+']'});
			div.children('input:eq(0)').val(param.key).attr('id',jq.attr('id')+'_key_'+len);
			div.children('input:eq(1)').val(param.value).attr('id',jq.attr('id')+'_value_'+len);
			var button = div.children('input:eq(2)');
			if (param.buttonType == 'add') { //给按钮赋text，并绑定增加事件
				button.val('增加').bind('click', function() {
					$('#'+jq.attr('id')).keyValue('addKeyValue',{key:'',value:'',buttonType:'remove'});
				});
			} else if (param.buttonType == 'remove') { //给按钮赋text，并绑定删除事件
				button.val('删除').bind('click', function() {
					$(this).parent().remove();
				});
			}
			return div;
		},
	}

	var kv = $.fn.keyValue;

})(jQuery);
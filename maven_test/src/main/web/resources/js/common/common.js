var common = {};
(function(){
	
	//enter事件
	common.enterEvent = function(eventName){
		 document.onkeydown = function(e){
	    	    if(!e) e = window.event;//火狐中是 window.event
		        if((e.keyCode || e.which) == 13){
		        	eval(eventName)();
		        }
	        }
	};
	
	//*********************对话框页面控制函数**********************//
	//通用对话框
	common.tipMessager = function(type,msg,delay){
		$.messager.show({
			title:'错误',
			msg:'<center>'+msg+'</center>',
			timeout:undefined==delay?1000:delay,
			showType:type,
			width:300,
			height:150
		});
	}
	
	//通用对话框
	common.fadeMessager = function(datagridId,msg,delay,width,height,hShift){
		//根据反馈结果，弹出对话框，显示操作结果(2秒自动关闭或手动关闭，触发当前页面刷新)
		if(undefined==hShift){
			hShift = 200;
		}
		$.messager.show({
			showType:'fade',
			//showSpeed： 定义消息窗口完成显示所需的以毫秒为单位的时间。默认是 600。
			width:undefined==width?300:width,
			height:undefined==height?150:height,
			msg:"<center>"+msg+"</center>",
			title:'提示',
			timeout:undefined==delay?1500:delay,
			onDestroy:function(){
				$("#"+datagridId).datagrid('reload');
			}
		});
	}
	
	//通用对话，一般用于错误显示，不刷新页面
	common.fadeMessagerNotClose = function(msg,delay,width,height,hShift){
		//根据反馈结果，弹出对话框，显示操作结果(2秒自动关闭或手动关闭，触发当前页面刷新)
		if(undefined==hShift){
			hShift = 200;
		}
		$.messager.show({
			showType:'fade',
			//showSpeed： 定义消息窗口完成显示所需的以毫秒为单位的时间。默认是 600。
			width:undefined==width?300:width,
			height:undefined==height?150:height,
			msg:"<center>"+msg+"</center>",
			title:'提示',
			timeout:undefined==delay?3000:delay
		});
	}
	
	common.showWarning = function(message, title) {
		if (title == undefined) {
			title = "提示";
		}
		$.messager.alert(title, message, "warning");
	}
	
	
	// 显示提示
	common.showTip = function(target, msg){
		 $(target).tooltip('destroy');
		 $(target).tooltip({
			    position: 'right',
			    content: '<span style="color:red">'+msg+'</span>',
			    showEvent:'mouseover',
			    onShow: function(){
			        $(this).tooltip('tip').css({
			            backgroundColor: '#FFFFCC',
			            borderColor: '#CC9933',
			            color:"#000"
			        });
			    }
		 }).tooltip('show');
	}
	
	// 隐藏提示
	common.hideTip = function(target) {
		$(target).tooltip('destroy');
	}
	
	//*********************tab页面控制函数**********************//
	//添加tab方法
	common.addTabs = function(subtitle, url, icon) {
			var jq = parent.jQuery;
			var createFrame = function(url) {
				var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
				return s;
			}
			if (!jq('#tabs').tabs('exists', subtitle)) {
				jq('#tabs').tabs('add', {
					title : subtitle,
					content : createFrame(url),
					closable : true,
					icon : icon
				});
			} else {
				$.messager.confirm('提示', subtitle+'页面已经存在，确认覆盖吗?', function(r){
					if (r){
						jq('#tabs').tabs('close', subtitle);
						jq('#tabs').tabs('add', {
							title : subtitle,
							content : createFrame(url),
							closable : true,
							icon : icon
						});
					}
				});
			
	    }
	};
	
	common.refreshTabByFunc = function(title, function_name){  
		var jq = parent.jQuery;
	    var refresh_tab = title?jq('#tabs').tabs('getTab',title):jq('#tabs').tabs('getSelected');  
	    
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		    var _refresh_ifram = refresh_tab.find('iframe')[0];  
		    _refresh_ifram.contentWindow[function_name]();
	    }  
	};
	
	common.refreshTabByUrl = function(title, url){  
		var jq = parent.jQuery;
	    var refresh_tab = title?jq('#tabs').tabs('getTab',title):jq('#tabs').tabs('getSelected');  
	    
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		    var _refresh_ifram = refresh_tab.find('iframe')[0];  
		 	var refresh_url = url?url:_refresh_ifram.src;  
		 	_refresh_ifram.contentWindow.location.href=refresh_url;  
	    }  
	};
	
	common.tabClose = function(){
		var jq = parent.jQuery;
		var tab = jq('#tabs').tabs('getSelected');
		var index = jq('#tabs').tabs('getTabIndex',tab);
		console.log(index);
		jq('#tabs').tabs('close', index);
	}
   
	//*********************日期控制函数**********************//
	common.dateUtils = function(date){
		var now = date||new Date();                 //当前日期   
		this.nowDayOfWeek = now.getDay();         //今天本周的第几天   
		this.nowDay = now.getDate();              //当前日   
		this.nowMonth = now.getMonth();           //当前月   
		this.nowYear = now.getFullYear();             //当前年   
		//nowYear += (nowYear < 2000) ? 1900 : 0;  //
		
	}

			//获得本周的开始日期   
	common.dateUtils.prototype.getWeekStartDate =  function(){
				var dayofweek = this.nowDayOfWeek == 0 ? this.nowDay - this.nowDayOfWeek-6 : this.nowDay - this.nowDayOfWeek+1;
			    var weekStartDate = new Date(this.nowYear, this.nowMonth, dayofweek);    
			    return weekStartDate;   
	};
	//获得本周的结束日期 
	common.dateUtils.prototype.getWeekEndDate = function(){
		  var dayofweek = this.nowDayOfWeek == 0 ? this.nowDay  :  this.nowDay + (7 - this.nowDayOfWeek);
		  var weekEndDate = new Date(this.nowYear, this.nowMonth, this.nowDay + (7 - dayofweek));    
		  return weekEndDate; 
	},
	common.dateUtils.prototype.formatDate = function(date){
		    var myyear = date.getFullYear();   
		    var mymonth = date.getMonth()+1;   
		    var myweekday = date.getDate();    
		       
		    if(mymonth < 10){   
		        mymonth = "0" + mymonth;   
		    }    
		    if(myweekday < 10){   
		        myweekday = "0" + myweekday;   
		    }   
		    return (myyear+"-"+mymonth + "-" + myweekday); 
	},
	common.dateUtils.prototype.string2Date = function(dateStr){
		var date = dateStr.split("-");
		var result = new Date();
		result.setFullYear(date[0], parseInt(date[1])-1, date[2]);
/*		result.setYear(date[0]);
		result.setMonth(parseInt(date[1])-1);
		result.setDate(date[2]);*/
		return result;
	};
	//获得本月的开始日期   
	common.dateUtils.prototype.getMonthStartDate = function(){   
	    var monthStartDate = new Date(this.nowYear, this.nowMonth, 1);    
	    return this.formatDate(monthStartDate);   
	};  
	  
	//获得本月的结束日期   
	common.dateUtils.prototype.getMonthEndDate = function(){   
	    var monthEndDate = new Date(this.nowYear, this.nowMonth, this.getMonthDays(nowMonth));    
	    return this.formatDate(monthEndDate);   
	};   
	  
	//获得本季度的开始日期   
	common.dateUtils.prototype.getQuarterStartDate = function(){   
	    var quarterStartDate = new Date(this.nowYear, this.getQuarterStartMonth(), 1);    
	    return this.formatDate(quarterStartDate);   
	};   
	  
	//或的本季度的结束日期   
	common.dateUtils.prototype.getQuarterEndDate = function(){   
	    var quarterEndMonth = getQuarterStartMonth() + 2;   
	    var quarterStartDate = new Date(this.nowYear, quarterEndMonth, this.getMonthDays(quarterEndMonth));    
	    return this.formatDate(quarterStartDate);   
	};
	//获得本周的数组
	common.dateUtils.prototype.getThisWeekArray = function(){
		var array = [];
		var startDate = this.getWeekStartDate();
		array.push(this.formatDate(startDate));
		for(var i = 1;i<7;i++){
				var d = new Date();
/*				d.setYear(startDate.getFullYear());
				d.setMonth(startDate.getMonth());
				d.setDate(startDate.getDate()+ i);*/
				d.setFullYear(
						startDate.getFullYear(), 
						startDate.getMonth(), 
						startDate.getDate()+ i);
				array.push(this.formatDate(d));
		}
		return array;
	};
	//获取下一周的时间数组
	common.dateUtils.prototype.getNextWeekArray = function(date){
		var array = [];
		var time = this.string2Date(date);
		var firstdayofNextweek = time;
		firstdayofNextweek.setDate(time.getDate()+1);
		array.push(this.formatDate(firstdayofNextweek));
		for(var i = 1;i<7;i++){
				var d = new Date();
/*				d.setYear(firstdayofNextweek.getFullYear());
				d.setMonth(firstdayofNextweek.getMonth());
				d.setDate(firstdayofNextweek.getDate()+ i);*/
				d.setFullYear(
						firstdayofNextweek.getFullYear(), 
						firstdayofNextweek.getMonth(), 
						firstdayofNextweek.getDate()+ i);
				array.push(this.formatDate(d));
		}
		return array;
		
		
	};
	common.dateUtils.prototype.getPreWeekArray = function(date){
		var array = [];
		var time = this.string2Date(date);
		var lastdayofPreWeek = time;
		lastdayofPreWeek.setDate(time.getDate()-1);
		this.nowDayOfWeek = lastdayofPreWeek.getDay();         //今天本周的第几天   
		this.nowDay = lastdayofPreWeek.getDate();              //当前日   
		this.nowMonth = lastdayofPreWeek.getMonth();           //当前月   
		this.nowYear = lastdayofPreWeek.getYear();
		for(var i = 6;i>0;i--){
				var d = new Date();
/*				d.setYear(lastdayofPreWeek.getFullYear());
				d.setMonth(lastdayofPreWeek.getMonth());
				d.setDate(lastdayofPreWeek.getDate()- i);*/
				d.setFullYear(
						lastdayofPreWeek.getFullYear(), 
						lastdayofPreWeek.getMonth(), 
						lastdayofPreWeek.getDate()- i);
				array.push(this.formatDate(d));
		}
		array.push(this.formatDate(lastdayofPreWeek));
		return array;
	};
	
	
	//*********************???????**********************//
	//获取Json格式map中Key对应的值，例如用于数据库查处的状态值找到对应的状态页面显示
	common.getJsonMapsValue=function(jsonMaps,key){
		 var value = '';
		 $(jsonMaps).each(function(index,item){
			 if(item.key == key){
				 value = item.value;
				 return false;
			 }
		 });
		 return value;   
	};
	
	
	//*********************通用基础变量判断是否为空函数**********************//
	//判断是否非空
	String.prototype.isEmpty = function() {
		var source = this;
	 	if(source==undefined||source==''||source=='null'||/^\s*$/.test(source))
			return true;
	 	return false;
	}
	
	//判断数组中是否包含某个元素
	common.arrayContains = function(array, value){
		if(array && array.length>0){
			for(var i=0;i<array.length;i++){
				if(array[i] == value){
					return true;
				}
			}
		}
		return false;
	}
	//*********************无状态批量提交控制函数**********************//
	//ajax批量提交多条选中记录到后台
	common.ajaxBatchSubmit=function(url,ids,tableId){
		//提交后台
	  	$.post(url,{ids:ids},function(data){
	  		//var data = eval('(' + data + ')');  // change the JSON string to javascript object
			if(data.success){
				common.fadeMessager(tableId, data.message, 1000);
			} else{
				common.fadeMessagerNotClose(data.message, 3000);
			}
	    });
	};
	
	
	// ajax批量删除操作
	common.ajaxBatchDelete = function (datagridId, actionUrl) {
		//删除时先获取选择行
		var rows = $("#"+datagridId).datagrid("getSelections");
		//选择要删除的行
		if (rows.length > 0) {
			$.messager.confirm("提示", "你确定要删除吗?", function (r) {
				if (r) {
					var ids = "";
			        for(var i=0,len=rows.length; i<len; i++){
						if(i>0){
							ids+=",";
						}
						ids += rows[i].id;
			        }
					$.post(actionUrl, {ids:ids}, function(data){
						if(data.success){
							common.fadeMessager(datagridId, data.message, 1000);
						} else{
							common.fadeMessagerNotClose(data.message, 3000);
						}
					});
				}
			});
        } else {
        	common.showWarning("请选择记录");
        }
	}
	
	
	// ajax批量取消关联操作
	common.ajaxBatchRelate = function (datagridId, actionUrl,infoTitle) {
		//删除时先获取选择行
		var rows = $("#"+datagridId).datagrid("getSelections");
		//选择要删除的行
		if (rows.length > 0) {
			$.messager.confirm("提示", "你确定要"+infoTitle+"吗?", function (r) {
				if (r) {
					var ids = "";
			        for(var i=0,len=rows.length; i<len; i++){
						if(i>0){
							ids+=",";
						}
						ids += rows[i].id;
			        }
					$.post(actionUrl, {ids:ids}, function(data){
						if(data.success){
							common.fadeMessager(datagridId, data.message, 1000);
						} else{
							common.fadeMessagerNotClose(data.message, 3000);
						}
					});
				}
			});
        } else {
        	common.showWarning("请选择记录");
        }
	}
	
	
	//*********************通用链接控制函数**********************//
	// 返回超链接
	common.htmlLink = function(text, func, isDetail){
		if (text == "--") {
			return text;
		}
		else {
			if (func == undefined && isDetail == undefined) {
				return "<a style='text-decoration:underline;color:#999;'>" + text +"</a>";
			}
			if (!isDetail) {
				return "<a href='#' onclick='" + func + "' style='color:blue; text-decoration:none;'>" + text + "</a>";
			}
			else {
				return "<a href='#' onclick='" + func + "' style='color:#999; text-decoration:underline;'>" + text + "</a>";
			}
		}	
	}

	/**
	 * 将json对象转化为用于查询下拉列表初始化json数组对象
	 * 
	 * 如：{"1":"test1", "2":"test2"} 
	 * 可以转化为
	 * [{"id":"","text":"请选择","selected":true}, {"id":"1","text":"test1"},{"id":"2","text":"test2"}]
	 * 
	 * obj			为待转化的json对象
	 * isInitNull	是否初始化“请选择”
	 * id			下拉列表初始化对象属性ID值变量名，默认为“id”
	 * text			下拉列表初始化对象属性显示值变量名，默认为“text”
	 */
	common.queryList = function(obj, isInitNull, id, text) {
		var queryJson = [];
		if (id == undefined) {
			id = "id";
		}
		if (text == undefined) {
			text = "text";
		}
		if (isInitNull) {//初始化“请选择”
			var isNullSelect = '{"'+ id + '":"","'+ text + '":"请选择","selected":true}';
			var queryIsNullObj = eval('(' + isNullSelect + ')');
			queryJson.push(queryIsNullObj);
		}
		for ( var p in obj ){ // 方法
			if ( typeof ( obj[p]) != "function" ){
				// p 为属性名称，obj[p]为对应属性的值
				var select = '{"'+ id + '":"' + p + '","'+ text + '":"'+ obj[p] + '"}';
				var selectObj = eval('(' + select + ')');
				queryJson.push(selectObj);
			} 
		}
		return queryJson;
	}
	
	/**
	 * 拼接ID字符串
	 * target		目标列表target
	 * separator	连接符，默认为","
	 * prop			目标列属性
	 * 
	 * 例:concatIds('#list_data', ';', 'ID')
	 * 	结果：'{ID1};{ID2};{ID3}'
	 * 例:concatIds('#list_data')
	 * 	结果：'{ID1},{ID2},{ID3}'
	 */
	common.concatIds = function(target, prop, separator) {
		var rows = $(target).datagrid("getSelections");
		
		// 未选中记录返回空字符串
    	if(rows.length == 0) {
			return "";
        }
    	
    	// 默认连接符
    	var DEFAULT_PROP = "id";
    	if (prop == undefined) {
    		prop = DEFAULT_PROP;
    	}
    	
    	// 默认连接符
    	var DEFAULT_SEPARATOR = ",";
    	if (separator == undefined) {
    		separator = DEFAULT_SEPARATOR;
    	}
    	
    	// 拼接字符串
    	var ids = "";
        for(var i=0, len=rows.length; i <len; i++) {
			if(i > 0) {
				ids += separator;
			}
			var obj = rows[i];
			for ( var p in obj ){ // 方法
				if (p == prop) {
					ids += obj[p];
				} 
			}
        }
        return ids;
	}
	
	/**
	 * 拼接ID字符串
	 * json			json数组
	 * separator	连接符，默认为","
	 * prop			目标列属性
	 */
	common.concatIdsJson = function(data, prop, separator) {
		// 默认连接符
    	var DEFAULT_PROP = "id";
    	if (prop == undefined) {
    		prop = DEFAULT_PROP;
    	}
    	
    	// 默认连接符
    	var DEFAULT_SEPARATOR = ",";
    	if (separator == undefined) {
    		separator = DEFAULT_SEPARATOR;
    	}
    	
		// 未选中记录返回空字符串
    	if(data.length == 0) {
			return "";
        }
    	
    	// 拼接字符串
    	// 拼接字符串
    	var ids = "";
        for(var i=0, len=data.length; i <len; i++) {
			if(i > 0) {
				ids += separator;
			}
			var obj = data[i];
			for ( var p in obj ){ // 方法
				if (p == prop) {
					ids += obj[p];
				} 
			}
        }
        return ids;
	}
	
	/**
	 * 支持多个值的字符串翻译
	 * str 多个值拼接的字符串 如"dvb;ott"
	 * enChJson 中英文json数据 如'{"dvb":"dvb";"ott":"ott"}'
	 * separator 拼接字符串解析的分隔符 默认为";"
	 */
	common.str2CH = function(str, enChJson, separator) {
		if (str == null) {
			return "";
		}
		// 默认连接符
    	var DEFAULT_SEPARATOR = ";";
    	if (separator == undefined) {
    		separator = DEFAULT_SEPARATOR;
    	}
    	var arr = str.split(separator);
    	var result = "";
		for (var index=0; index < arr.length; index++) {
			var key = arr[index];
			result += separator + enChJson[key];
		}
		if (result != "") {
			result = result.substring(1);
		}
		return result;
	}
	
	/**
	 * 判断所选记录中记录中是否包含指定状态的记录
	 * 
	 * target		目标列表target
	 * status		记录状态
	 * 
	 * 所选记录含状态为{status}的记录，返回true；否则返回false
	 */
	common.includeStatus = function(target, status) {		
		var rows = $(target).datagrid("getSelections");
        if(rows.length > 0){
        	for(var i=0, len=rows.length; i <len; i++){
            	if(rows[i].status == status) {
            		return true;
            	}
            }
        }
		return false;
	}
	
	/**
	 * 判断所选记录中记录中是否包含指定发布状态的记录
	 * 
	 * target		目标列表target
	 * state		记录发布状态
	 * 
	 * 所选记录含状态为{pubState}的记录，返回true；否则返回false
	 */
	common.includePubState = function(target, pubState) {	
		var rows = $(target).datagrid("getSelections");
        if(rows.length > 0){
        	for(var i=0, len=rows.length; i <len; i++){
            	if(rows[i].pubState == pubState) {
            		return true;
            	}
            }
        }
		return false;
	}
	
	/**
	 * 获取表单json数据供datagrid刷新
	 * 
	 * formId		目标form ID
	 * 
	 * 返回json object
	 */
	common.getFormJson = function(formId) {	
		var jsonObject = null;
		var form = $("#"+formId);
		if(form){
			var params = form.serializeArray();
			if(params.length > 0){
				var s = "{";
				$(params).each(function(index, element){
					s = s+'"'+element.name+'":"'+element.value+'",';
				});
				s=s.substr(s,s.length-1)+"}";
				jsonObject = $.parseJSON(s);
			}	
		}
		return jsonObject;
	}
	
	
	
	/**
	 * 显示新增/编辑弹出框
	 * dlgId   		弹出框ID
	 * formId  	弹出框表单ID
	 * title		弹出框标题
	 * selRow		选择datagrid的记录行
	 * 
	 */
	common.showDlg=function(dlgId,formId,title,selRow){
		$('#'+dlgId).dialog('open').dialog('setTitle',title);
	    $('#'+formId).form('clear');
	    if(selRow!=undefined)
	    	$('#'+formId).form('load',selRow);
	}
	
	
	
	
	/**
	 * 新增/编辑保存方法
	 * 
	 * dlgId		弹出框Id
	 * formId 		保存的表单ID
	 * url			请求的URL
	 * datagridId	需要刷新datagrid的id
	 * 
	 * 
	 */
	common.doSave=function(dlgId,formId,url,datagridId){
	    $('#'+formId).form('submit',{
	    	url: url,
	        onSubmit: function(){
	            return $('#'+formId).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if (result.success==true){
	            	$('#'+dlgId).dialog('close');
	                $('#'+datagridId).datagrid('reload');
                } else {
                    $.messager.show({
                        title: '错误',
                        msg: result.message
                    });
                }
	        }
	    });
	}
	
	
	/**
	 * 删除记录
	 * datagridId		操作datagridId表格Id
	 * titleType		操作标题 如影片信息
	 * url				请求的url
	 */
	common.doDelete=function(datagridId,titleType,url){
	    var row = $('#'+datagridId).datagrid('getSelected');
		if(row == null){
			$.messager.show({title:'错误',msg:'请选择一个'+titleType,timeout:1000});
			return;
		}
	    if (row){
	        $.messager.confirm('删除','确认删除'+titleType+"?",function(r){
	            if (r){
	                $.post(url,{id:row.id},function(result){
	                    if (true==result.success){
	                        $('#'+datagridId).datagrid('reload');
	                    } else {
	                        $.messager.show({
	                            title: '错误',
	                            msg: result.message
	                        });
	                    }
	                },'json');
	            }
	        });
	    }
	}
	
	/**
	 * 根据property属性字段合并行
	 * datagridId		操作datagridId表格Id
	 * columnNames		数组，合并columnNames列
	 * property		属性字段，默认为id
	 */
	common.mergeCell=function(datagridId, columnNames, property){
		var prop='id';
		if(property){
			prop = property;
		}
		
		// 生成mergeOption
	    var rows = $('#'+datagridId).datagrid('getData').rows;
	    var mergeOption = new Array();
		if(rows && rows.length>0){
			for(var i=0;i<rows.length;){
				var row = rows[i];
				var id = eval('row.'+prop);
				var j = i;
				for(;j<rows.length-1;j++){
					var rownext = rows[j+1];
					var idnext = eval('rownext.'+prop);
					if(id!=idnext){
						break;
					}
				}
				if(j>i){
					var option = {'index':i,'rowspan':j-i+1};
					mergeOption.push(option);
				}
				i=j+1;
			}
		}
		
		// 表格合并
		if(mergeOption){
			for(var i=0;i<mergeOption.length;i++){
				if(columnNames.length>0){
					for(var k=0;k<columnNames.length;k++){
						$('#'+datagridId).datagrid('mergeCells',{index: mergeOption[i].index,field:columnNames[k],rowspan:mergeOption[i].rowspan});
					}
				}
			}
		}
	}
	
	//同步
	common.sync=function(datagridId,url){
		var rows = $("#"+datagridId).datagrid("getSelections");
		//选择要的行
		if(rows.length<1){
			$.messager.alert('提示', '请选择记录行', "info");
			return;
		}
		var ids="";
		for(var i=0;i<rows.length;i++){
			ids+=rows[i].id+",";
		}
		$.post(url, {ids:ids}, function(result){
			if (true==result.success){
				$.messager.show({title:'提示',msg:result.message,timeout:1000});
				$('#'+datagridId).datagrid('reload');
			}else{
				 $.messager.show({
                     title: '提示',
                     msg: result.message
                 });
			}
		});
	}
	
		
	
	/**
	 * 创建checkBox选择框
	 * divId checkBox的divId
	 * checkBoxName checkBox的名称
	 * dataArray 展示数据，满足key|value格式
	 * 
	 */
	common.checkBoxDivCreate = function (divId,checkBoxName,dataArray){
		$('#'+divId).empty();
		$.each(dataArray,function(key,value){
		    var checkboxDiv = '<div style="width:15%;float:left">';
		    checkboxDiv += '<input type="checkbox" class="checkbox"  name="'+checkBoxName+'" value="'+key+'"/>'+value;
		    checkboxDiv +='</div>';
		    $('#'+divId).append(checkboxDiv);
		});
    }	
	
	/**
	 * 选中checkBox选择框
	 * divId checkBox的divId
	 * dataArray 需要选中的数据，满足key|value格式
	 * 
	 */
	 common.checkBoxDivSelect = function (divId,dataArray){
		$("#"+divId).find("input[type=checkbox]").each(function(index,item){
//			alert("$(item).val()="+$(item).val()+" ??? dataArray[key]="+dataArray[key] +" 是否相等结果 " + ($(item).val()==dataArray[key]));
			for(var key in dataArray){
				if($(item).val()==dataArray[key]){
					$(item).attr('checked','true');
					break;
				}	
			}
		});
    }	
	
	 //同步ajax调用
	 common.ajaxGetSync = function(url,success,method,dataType,cache){
		 common.baseAjaxSync(url,success,'GET','json',true);
	 }
	 
	 common.baseAjaxSync = function(url,success,method,dataType,cache){
			$.ajax({
				url:url,
				method:method||'GET',
				dataType:dataType||'json',
				success:success,
			    async:false,
			    cache:cache||false,
			    timeout:30000,//30秒超时
			    error:function(){
					$.messager.show({
						showType:'fade',
						width:undefined==width?300:width,
						height:undefined==height?150:height,
						msg:"<center>请求服务器超时</center>",
						title:'info',
						timeout:undefined==delay?3000:delay,
						onDestroy:function(){
						}
					});
			    }
			});
		 }
	
	 //销毁提示框
	 common.destroyTooltip=function(id){
		$('#'+id).tooltip('destroy');
	 }
	 
	//验证排序 
	common.validateRank=function(newValue,oldValue){
		var reg = /^-?[0-9]\d*$/;
		if(newValue>1000000000||!(reg.test(newValue))){
			newValue=oldValue;
		}
		return newValue;
	}
	
	//清空查询条件
	common.clearSearch = function(target) {
		$(target).find('input').each(function(){
			 if($(this).hasClass('combo-text')){
				 var $formBox = $(this).parent().prev();
				 if($formBox.hasClass('easyui-datebox')){
					 $formBox.datebox('clear');
				 }else if($formBox.hasClass('easyui-datetimebox')){
					 $formBox.datetimebox('clear')
				 }else{
					 $formBox.combobox('reset');
				 }
			 }else{
				 $(this).val('');
			 }
		});
	}
	
	$.extend($.fn.datagrid.defaults,{pageList: [20,50,100]});
})();

$(document).keydown(function(e) {
	e = e || window.event;
	var code = e.keyCode ? e.keyCode : e.which;
	if (code == 27) {
		var dialog = $('.easyui-dialog:visible:last');
		if(dialog.length > 0) {			
			dialog.dialog('close');
		}
	}
	if (code == 13) {
		var dialog = $('.easyui-dialog:visible:last');
		if(dialog.length > 0) {			
			var funcName = dialog.attr("searchFunc");
			if (funcName != undefined && funcName) {
				eval(funcName+"()");
			}
		} else {
			if (window.doSearch != undefined) {
				//doSearch();
			}
		}
	}
});  

$(document).ajaxComplete(function(event, xhr, settings) {
	 if (xhr.responseText.indexOf('<head id="login.jsp">') > -1) {
		 document.write(xhr.responseText);
	 }
});

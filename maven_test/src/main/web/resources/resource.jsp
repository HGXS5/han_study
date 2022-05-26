<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.wasu.cp.cpam.client.UrmClient" %>
<% 
String path = request.getContextPath();

%>
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/icon.css">
<script type="text/javascript" src="<%=path%>/easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" >
	var path="<%=path%>";
</script>
<%
UrmClient urmClient = UrmClient.get(session);
if(urmClient!=null){
	out.print(urmClient.createAuthScript());
}
%>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/dateformatter.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/common.js"></script>
<script type="text/javascript">
/* $(document).ajaxComplete(function(event, xhr, settings) {  
    if(xhr.getResponseHeader("sessionstatus")=="timeOut"){  
        if(xhr.getResponseHeader("loginPath")){
          
            window.location.replace(xhr.getResponseHeader("loginPath"));  
        }else{  
          
        }  
    }  
}); */


//加粗表头
/* $("document").ready(function() {
	  $(".datagrid-header-row td div span").each(function(i,th){
	        var val = $(th).text();
	         $(th).html("<label style='font-weight: bolder;'>"+val+"</label>");
	    });

}); */
</script>


<style>
body{
	overflow:hidden;
}
table.input{
	border-collapse:collapse;width:100%;font-size:12px;
}
table.input input{
	border:1px solid #AAA;
}
table.input td,table.input th{
	border:1px solid #96b7e0;padding:3px;
}
table.input th{
	background-color:#E6F0F6;white-space:nowrap;
}
table.input td.color{
	background-color:#F7FAFF;
}
div.frame-content{
	margin:10px 0px;height:100%;
}
a{
	text-decoration:none;color:blue;
}
.loading{
	display:block;width:100%;height:100%;opacity:0.4;filter:alpha(opacity=40); 
	background:#333;position:absolute;top:0;left:0;z-index:10000;text-align:center;
	line-height:300px;color:#FFF;vertical-align:middle;font:15px;
} 
.sep{height:25;border-left:1px solid #ccc;border-right:1px solid #fff;display:inline;margin: 0px 6px}
.tip_input{color: #777;	padding: 0 3px;}

.span_require_tip{
	color:red;
}
.divborder {
	border: 1px solid #DBDBDB;
	margin: 0px 0px 3px 0px; 
	padding:5px 2px 5px 8px;
	height:auto;
	line-height:22px;
}
</style>


<div id="loading" class="loading" style="display:none">请求处理中,请稍后...</div> 
<script>
$(document).ready(function(){
	
  $("input").blur(function(){
	var str=$(this).val().replace(/^\s+|\s+$/g,""); 
	$(this).val(str);
  });
  
  $("textarea").blur(function(){
		var str=$(this).val().replace(/^\s+|\s+$/g,""); 
		$(this).val(str);
	  });

});



$(document).keydown(function(e) {
	var code = e.keyCode ? e.keyCode : e.which;
	if (code == 27) {
		var dialog = $('.easyui-dialog:visible:last');
		if(dialog.length > 0){			
			dialog.dialog('close');
		}
	}
	if (code == 13 && window.doSearch != undefined) {
//		doSearch();
	}
});





var dialogOnbeforeOpen = {
			resizable:true,
			onBeforeOpen:function(){
				var tarWidth = $(this).panel('options').width;
				var tarHeight = $(this).panel('options').height;
				var tarTop = $(this).panel('options').top;
				var compareHeight = document.body.clientHeight * 0.8;
				if("auto" == tarHeight){
					tarHeight = compareHeight;
				}
				if(compareHeight < tarHeight ){
					$(this).dialog("resize",{ width: this.width, height: compareHeight, top:tarTop });
				}else{
					$(this).dialog("resize",{ width: this.width, height: tarHeight, top:tarTop });
				}
				$(this).find(".panel-body").css("height",tarHeight-75);
	 		}
	 	};
	 $.extend($.fn.dialog.defaults,dialogOnbeforeOpen);
		
	// session 失效
	var sessionstate = '<%= session.getAttribute("sessionstate")%>';
	var loginUrl = '<%= session.getAttribute("loginUrl")%>';
	if(sessionstate =='timeOut'){
		location.href= loginUrl;
	}
</script>
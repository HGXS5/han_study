document.onkeydown = keyDown;
function keyDown(){
	var srcElem = document.activeElement; //取到当前焦点控件
	var tagName = srcElem.tagName.toUpperCase(); //取到当前控件名并转大写
	 if(srcElem.type=='button'||srcElem.type=='submit'){//如果是button类型的
		 if(window.event.keyCode==13 || window.event.keyCode==32){//enter的KEYCODE的值是13，通过监听可以控制。
			 window.event.returnValue=false;
		 }
	 }else if(tagName=='TEXTAREA' || tagName =='INPUT'){
		 return;
	 }
	 else{
		 if(window.event.keyCode==13 || window.event.keyCode==32){//enter的KEYCODE的值是13，通过监听可以控制。
			 window.event.returnValue=false;
		 }
	 }
}
function showText(dialogTitle,textCnt,dialogW,dialogH,imgNum,showTipImg,hiddenButton)
{
	if(showTipImg == true)
	{
		var  img_select;
		if(imgNum == 1) {
			img_select = "<img src='../../resources/images/bg_tipsuccess.png'/>";
		}else if(imgNum == 2) {
			img_select = "<img src='../../resources/images/bg_tipsuccess.png'/>";
		}else {
			img_select = "<img src='../../resources/images/bg_tipfailth.png'/>";
		}
	}
	else
	{
		var  img_select = "";
	}
	
	textCnt = decodeURI( textCnt ); 
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><div id='cnt_list'><table wborder='0' cellspacing='0' cellpadding='0'><tr><td valign='middle' id='text_color'>"+img_select+textCnt+"</td></tr></table></div><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	(imgNum == 1 || imgNum == 2)?$("#text_color").css({color:"#333"}):$("#text_color").css({color:"#C40000"});
	$("body input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-77;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW-5});
	$("#cnt_list table").css({width:DW-20});
	$("#cnt_list,#cnt_list table").css({height:DH});
	
	if(hiddenButton)
	{ 
		$("#btn_closedialog").hide();
		$("#btn_dsure").hide();
	}
	
	
	
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		
		if(hiddenButton)
		{ 
			return;
		}
		
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
		
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	
	moveWindow("dialog","dialog_title");
}
function showConfirmAndCancel(dialogTitle,textCnt,url,flag)
{
	 
	var  img_select = "<img src='../../../resources/images/ic_help.gif'/>";
	textCnt = decodeURI( textCnt ); 
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><div id='cnt_list'><table wborder='0' cellspacing='0' cellpadding='0'><tr><td valign='middle' id='text_color'>"+img_select+textCnt+"</td></tr></table></div><div id='btn_list'><input type='button' value='确定' id='btn_deleteSure'/><input style='margin-left=10px;' type='button' value='取消'   id='btn_deleteCancel'/></div></div>");
	$("#text_color").css({color:"#C40000"});
	// $("#text_color").css({color:"#C40000"});
	$("body input,body button").blur();
	$("#dialog").css({width:400});
	$("#dialog").css({height:200});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-77;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW-5});
	
	$("#cnt_list table").css({width:DW-20});
	$("#cnt_list,#cnt_list table").css({height:DH});
	$("#btn_closedialog,#btn_dsure,#btn_deleteCancel").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
		
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	$("#btn_deleteSure").click(function(){
		$(this).remove();
		$("#btn_deleteCancel").remove(); 
		if(url == "up"){
			onUpShelfBtnClick(1);
			$("#dialogbg,#dialog").remove();
		}else if(url == "down"){
			onDownShelfBtnClick();
			$("#dialogbg,#dialog").remove();
		}else if(url == "up2"){
			onUpShelfBtnClick(2);
			$("#dialogbg,#dialog").remove();
		}else if(url == "deleteChannelResource"){
			deleteChannelResource();
		}else if(url == "removeLocalAllAjax"){   
			frame_content.lFrame.removeLocalAllAjax(); 
			$("#dialogbg,#dialog").remove();
		}
		else{
			if(((typeof url)=="string"))
			{
				if(flag!=null&&flag==true) {
					frame_content.mFrame.mainResourceFrame.location = url; 
					$("#dialogbg,#dialog").remove();
				}else{
					window.location = url;
				}
			}
			else
			{
				url.submit();
			}
		}}
		);
	
	moveWindow("dialog","dialog_title");
}

function showConfirmAndCancel_b(dialogTitle,textCnt,url)
{
	var  img_select = "<img src='../../../resources/images/ic_help.gif'/>";
	textCnt = decodeURI( textCnt ); 
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><div id='cnt_list'><table wborder='0' cellspacing='0' cellpadding='0'><tr><td valign='middle' id='text_color'>"+img_select+textCnt+"</td></tr></table></div><div id='btn_list'><input type='button' value='确定' id='btn_deleteSure'/><input style='margin-left=10px;' type='button' value='取消'   id='btn_deleteCancel'/></div></div>");
	$("#text_color").css({color:"#C40000"});
	// $("#text_color").css({color:"#C40000"});
	$("body input,body button").blur();
	$("#dialog").css({width:400});
	$("#dialog").css({height:200});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-77;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW-5});
	
	$("#cnt_list table").css({width:DW-20});
	$("#cnt_list,#cnt_list table").css({height:DH});
	$("#btn_closedialog,#btn_dsure,#btn_deleteCancel").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
		
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	$("#btn_deleteSure").click(function(){
			$("#dialogbg,#dialog").remove();
			frame_content.lFrame.removeNodes();
		}
	);
	moveWindow("dialog","dialog_title");
}
function showConfirmAndCancelSite(dialogTitle,textCnt,url)
{
	var  img_select = "<img src='../../../resources/images/ic_help.gif'/>";
	textCnt = decodeURI( textCnt ); 
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><div id='cnt_list'><table wborder='0' cellspacing='0' cellpadding='0'><tr><td valign='middle' id='text_color'>"+img_select+textCnt+"</td></tr></table></div><div id='btn_list'><input type='button' value='确定' id='btn_deleteSure'/><input style='margin-left=10px;' type='button' value='取消'   id='btn_deleteCancel'/></div></div>");
	$("#text_color").css({color:"#C40000"});
	// $("#text_color").css({color:"#C40000"});
	$("body input,body button").blur();
	$("#dialog").css({width:400});
	$("#dialog").css({height:200});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-77;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW-5});
	
	$("#cnt_list table").css({width:DW-20});
	$("#cnt_list,#cnt_list table").css({height:DH});
	$("#btn_closedialog,#btn_dsure,#btn_deleteCancel").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
		
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	$("#btn_deleteSure").click(function(){
		parent.document.getElementById("showSuccessDialog").value = 1;
			parent.window.location = url;
			
	});
	
	moveWindow("dialog","dialog_title");
}
function showConfirmAndCancelFunc(dialogTitle,textCnt,func)
{
	
	var  img_select = "<img src='../../../resources/images/ic_help.gif'/>";
	textCnt = decodeURI( textCnt ); 
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><div id='cnt_list'><table wborder='0' cellspacing='0' cellpadding='0'><tr><td valign='middle' id='text_color'>"+img_select+textCnt+"</td></tr></table></div><div id='btn_list'><input type='button' value='确定' id='btn_deleteSure' onclick='"+func+";'/><input style='margin-left=10px;' type='button' value='取消'   id='btn_deleteCancel'/></div></div>");
	$("#text_color").css({color:"#C40000"});
	// $("#text_color").css({color:"#C40000"});
	$("body input,body button").blur();
	$("#dialog").css({width:400});
	$("#dialog").css({height:200});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-77;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW-5});
	$("#cnt_list table").css({width:DW-20});
	$("#cnt_list,#cnt_list table").css({height:DH});
	$("#btn_closedialog,#btn_dsure,#btn_deleteCancel").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
		
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
}


// delete data
function showPage(dialogTitle,pageName,dialogW,dialogH)
{
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	$("#dialog").css({'z-index':10000});

	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({padding:"5px",overflow:"auto"});
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {  	
		if($("endTime").value||$("startTime").value)
		{
			WdatePicker.hide;
		}
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}

//克隆
function showClonePage(dialogTitle,pageName,dialogW,dialogH)
{
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list' style='background:#e9f0f9;'><input type='button' value='确认' id='btn_dclone'/>    <input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({overflow:"auto"});
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	
	$("#btn_dclone").click(function(){
		window.frames["framelist"].multipleClone();
	});
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}

//delete data
function showResourcePage(dialogTitle,pageName,dialogW,dialogH)
{
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist'  id='framelist' frameborder='0' src="+pageName+" ></iframe><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({padding:"0px",overflow:"auto",height:"90%"});
	//$("#framelist").attr("width",DW-10);
	//$("#framelist").attr("height",DW-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}


function showPageTask(dialogTitle,pageName,dialogW,dialogH,suburl)
{
	
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({padding:"5px",overflow:"auto"});
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		window.location = suburl;
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}

function showPageTask2(dialogTitle,pageName,dialogW,dialogH,suburl)
{
	
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({padding:"5px",overflow:"auto"});
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		window.location = suburl;
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}


function showPagePublishCode(dialogTitle,pageName,dialogW,dialogH,suburl)
{
	
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({padding:"5px",overflow:"auto"});
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		
		var queryString = document.getElementById("hostsId").value;

		window.location = suburl+"&"+queryString;
		
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}
function showPageByWithScroll(dialogTitle,pageName,dialogW,dialogH)
{
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list'><input type='button' value='关闭' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	var getua = navigator.userAgent;
	if(/Gecko/.test(getua))
	{
		$("#framelist").css({padding:"5px",overflow:"scroll","overflow-x":"hidden"});
	}
	else
	{
		$("#framelist").css({padding:"5px",overflow:"hidden"});
	}
	
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	moveWindow("dialog","dialog_title");
	
	return false;
}


function moveWindow(windowID,windowHandleID)
{
		var box = document.getElementById(windowID);	
		var boxHandle = document.getElementById(windowHandleID);
		var mx, my, ox, oy; 
		function e(event){ 
		   if( ! event){ 
			  event = window.event;
			  event.target = event.srcElement;
			  event.layerX = event.offsetX;
			  event.layerY = event.offsetY;
		   }
		   event.mx = event.pageX || event.clientX + document.body.scrollLeft; 
		   event.my = event.pageY || event.clientY + document.body.scrollTop; 	
		   return event; 	
		}
		boxHandle.onmousedown = moveEvent;
		function moveEvent(event){ 	
		   event = e(event); 	
		   ox = parseInt(box.offsetLeft); 	
		   oy = parseInt(box.offsetTop); 	
		   mx = event.mx; 	
		   my = event.my; 	
		   document.onmousemove = moveBox; 	
		   document.onmouseup = stopBox; 	
		}
		function moveBox(event){ 	
		   event = e(event);
		   box.style.left = ox + event.mx - mx  + "px";	
		   box.style.top = oy + event.my - my + "px";	
		}
		function stopBox(event){ 	
		   // alert('');
		   event = e(event);
		   ox = parseInt(box.offsetLeft); 	
		   oy = parseInt(box.offsetTop); 	
		   mx = event.mx ; 	
		   my = event.my ; 	
		   document.onmousemove = null;
		   document.onmouseup = null; 
		}
		
}

/** 去掉字符串前后的空格 */
String.prototype.trim = function(){  
    // 用正则表达式将前后空格
    // 用空字符串替代。
    return this.replace(/(^\s*)|(\s*$)/g, "");  
};

/** 显示成功的窗口 */
function showSuccessWindow( successMessageContent, failuredMessageContent ) {
	var dialog = $( "#showSuccessDialog" );
	var show = dialog.val();
	if ( show != '0' && show != '2' ) {
		showText('提示信息', successMessageContent,400,200,1,true);
		$("#dialogbg,#dialog").fadeOut(5000,function(){
			$(this).remove();
			dialog.val( '2' );
			$("select").css({visibility:"visible"});
			$("#dialog select").css({visibility:"visible"});
		}); 
	} 
	
	if ( show != '1' && show != '2' ) {
		showText('提示信息', failuredMessageContent,400,200,0,true);
		
	}
}
/** 显示成功的窗口 */
function showSuccessWindowLogin( successMessageContent, failuredMessageContent ) {
	var dialog = $( "#showSuccessDialog" );
	var show = dialog.val();
	
	if(typeof show != "undefined" && show.length != 0){
	if ( show != '0' && show != '2' ) {
		showText('提示信息', successMessageContent,400,200,2,true);
		$("#dialogbg,#dialog").fadeOut(3000,function(){dialog.val( '2' );$(this).remove();}); 
	} 
	
	if ( show != '1' && show != '2' ) {
		showText('提示信息', failuredMessageContent,400,200,0,true);
		$("#dialogbg,#dialog").fadeOut(3000,function(){dialog.val( '2' );$(this).remove();}); 
	}
	}
}

/** 在父页面显示成功的窗口 
 * num 表示父页面的层级数量
 * */
function showSuccessWindowByParent( successMessageContent, failuredMessageContent ,num) {
	var dialog = $( "#showSuccessDialog" );
	var show = dialog.val();
	var father;
	if(num==2)
	father = parent.parent;
	else if(num==3)
	father = parent.parent.parent;
	else
	father = parent;
	if ( show != '0' && show != '2' ) {
		//直接执行后面的一句会报错
			father.showText('提示信息', successMessageContent,400,200,1,true);
			var dialogbg =father.document.getElementById('dialogbg');
			var dialog1 =father.document.getElementById('dialog');
			$(dialog1).fadeOut(3000,function(){
				$(dialog1).remove();
			});
			$(dialogbg).fadeOut(5000,function(){
				$(dialogbg).remove();
			});
			dialog.val('2');
			$("select").css({visibility:"visible"});
			$("#dialog select").css({visibility:"visible"});
	} 
	if ( show != '1' && show != '2' ) {
		father.showText('提示信息', failuredMessageContent,400,200,0,true);
	}
}

/** 显示失败的窗口 */
function showFailuredWindow( failuredMessageContent ) {
	var dialog = $( "#showSuccessDialog" );
	var show = dialog.val();
	if ( show != '1' && show != '2' ) {
		showText('提示信息', failuredMessageContent,400,200,0,true);
	} 
}

/** 对&gt;解码成>，然后在用encodeURI进行统一编码。 */
function decode( str ) {
	if ( str == null ) {
		return null;
	}
	
	str = str.replace(/\&gt;/g, ">");
	str = str.replace(/\&lt;/g, "<");
	str = str.replace(/\&amp;/g, "&");
	
	return str;
}

/** 弹出角色详情的对话框 */
function showRoleDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "roleList" ).rows[ id ];
	var roleId = table.cells[3].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "role.roleId=" );
	buffer.append( roleId );
	buffer.append( "&noPrivilegeOnlyText=true" );
	var queryString = encodeURI( buffer.toString());
	showPage( messageTip,'../RoleManage/BrowseRole.action?'+queryString, 610, 260 );
}

/** 弹出用户详情的对话框 */
function showAdminDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "adminsList" ).rows[ id ];
	var loginName       = table.cells[1].innerHTML.trim();
	var userName        = link.innerHTML.trim();
	var status  		= table.cells[3].innerHTML.trim();
	var roleList  		= table.cells[4].innerHTML.trim();
	var email			= table.cells[5].innerHTML.trim();
	var phone 			= table.cells[6].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "admin.loginName=" );
	buffer.append( loginName );
	buffer.append( "&admin.username=" );
	buffer.append( decode(userName) );
	buffer.append( "&admin.status=" );
	buffer.append( status );
	buffer.append( "&admin.role=" );
	buffer.append( roleList );
	buffer.append( "&admin.email=" );
	buffer.append( email );
	buffer.append( "&admin.phone=" );
	buffer.append( phone );
	buffer.append( "&noPrivilegeOnlyText=true" );
	
	var queryString = encodeURI( buffer.toString() );
	
	showPage(messageTip,'../admin/adminView.action?'+queryString, 610, 300 );
}

/** 弹出消息详情的对话框 */
function showMessageDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "messagesList" ).rows[ id ];
	var messageId 			= table.cells[8].innerHTML.trim();
	var url="message.id="+messageId+"&noPrivilegeOnlyText=true"
	var queryString = encodeURI(url );
	showPage(messageTip,'../messageManage/findMessageById.action?'+queryString, 610, 380 );
}

/** 弹出频点详情的对话框 */
function showRegionFreqDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "regionList" ).rows[ id ];
	var freqId  = table.cells[6].innerHTML.trim();
	var buff = new StringBuffer();
	buff.append( "reqionFreq.freqId=" );
	buff.append( freqId );
	buff.append( "&noPrivilegeOnlyText=true" );
	buff.append( "&errorPageWindow=true" );
	
	var queryString = encodeURI( buff.toString() );
	// alert( queryString );
	showPage( messageTip,'../SysManage/QueryRegionDetail.action?' + queryString, 610, 280);
}

/** 弹出IEPG参数详情的对话框 */
function showIepgParameterDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "iepgParameterList" ).rows[ id ];
	var id  = table.cells[7].innerHTML.trim();
	var buff = new StringBuffer();
	buff.append( "iepgParameter.Id=" );
	buff.append( id );
	buff.append( "&noPrivilegeOnlyText=true" );
	buff.append( "&errorPageWindow=true" );
	
	var queryString = encodeURI( buff.toString() );
	showPage( messageTip,'../SysManage/QueryIepgParameterDetail.action?' + queryString, 610, 280);
}

/** 弹出插件详情的对话框 */
function showPluginDetailDialog( link ) {
	var id = link.id;
	link.href = "###";
	var table        = document.getElementById( "plugin" ).rows[ id ];
	var name         = table.cells[0].innerText.trim();
	var alias        = table.cells[1].innerHTML.trim();
	var description  = table.cells[2].innerHTML.trim();
	var type         = table.cells[3].innerHTML.trim();
	var className    = table.cells[4].innerHTML.trim();
	
	var buffer = new StringBuffer();
	buffer.append( "plugin.name=" );
	buffer.append( name );
	buffer.append( "&plugin.alias=" );
	buffer.append( alias );
	buffer.append( "&plugin.type=" );
	buffer.append( type );
	buffer.append( "&plugin.className=" );
	buffer.append( className );
	buffer.append( "&plugin.description=" );
	buffer.append( description );
	buffer.append( "&noPrivilegeOnlyText=true" );
	
	var queryString = encodeURI( buffer.toString() );
	
	showPage('插件详情','../Plugin/findPluginById.action?' + queryString, 630, 360);
}


/** 弹出站点详情的对话框 */
function showSiteDetailDialog( link, messageTip,id ) {
	 
	 
	var buffer = new StringBuffer(); 
	buffer.append( "iepgSiteVO.siteID=" );
	buffer.append( id );
	buffer.append( "&noPrivilegeOnlyText=true" );
	
	var queryString = encodeURI( buffer.toString() );
	showPage(messageTip,'../Site/siteView.action?'+queryString, 610, 430 );
}

/** 弹出媒资详情的对话框 */
function showMediaDetailDialog( link, messageTip, resourceId, categoryId ) {
	 var buffer;
	if(categoryId&&categoryId!='')
	{
	   buffer = "asset.resourceID="+resourceId+"&asset.categoryId="+categoryId+"&noPrivilegeOnlyText=true"
	}else{
	   buffer = "asset.resourceID="+resourceId+"&noPrivilegeOnlyText=true"	
	} 
	var queryString = encodeURI( buffer );
	showPage(messageTip,'../media/detailMedia.action?'+queryString, 800, 600 );
}

/** 弹出服务域详情的对话框 */
function showPublishDomainDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "publishDomainList" ).rows[ id ];
	var domainId = table.cells[6].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "publishDomain.domainId=" );
	buffer.append( domainId );
	buffer.append( "&noPrivilegeOnlyText=true" );
	var queryString = encodeURI( buffer.toString() );
	
	showPage(messageTip,'../SysManage/getPublishDomainById.action?'+queryString, 620, 330 );
}

/** 弹出服务域组详情的对话框 */
function showPublishGroupDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "publishGroupList" ).rows[ id ];
	var groupId = table.cells[4].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "publishGroup.groupId=" );
	buffer.append( groupId );
	buffer.append( "&noPrivilegeOnlyText=true" );
	var queryString = encodeURI( buffer.toString() );
	
	showPage(messageTip,'../SysManage/getPublishGroupById.action?'+queryString, 610, 450 );
}

/**弹出终端适配能力信息详情*/
function showTerminalDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "terminalList" ).rows[ id ];
	var id = table.cells[6].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "terminal.terminalId=" );
	buffer.append( id );
	buffer.append( "&noPrivilegeOnlyText=true" );
	var queryString = encodeURI( buffer.toString() );
	
	showPage(messageTip,'../terminal/QueryTerminalDetail.action?'+queryString, 610, 400 );
	
}

/** firefox 不支持innerText 方法 ,要支持要加一段脚本 */
function isIE(){
	if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
	    return true; 
	else 
	    return false; 
	} 

	if(!isIE()){
	    HTMLElement.prototype.__defineGetter__("innerText", 
	    function(){
	        var anyString = "";
	        var childS = this.childNodes;
	        for(var i=0; i<childS.length; i++) { 
	            if(childS[i].nodeType==1)
	                // anyString += childS[i].tagName=="BR" ? "\n" :
					// childS[i].innerText;
	                anyString += childS[i].innerText;
	            else if(childS[i].nodeType==3)
	                anyString += childS[i].nodeValue;
	        }
	        return anyString;
	    } 
	    ); 
	    HTMLElement.prototype.__defineSetter__("innerText", 
	    function(sText){
	        this.textContent=sText; 
	    } 
	    ); 
	}
   
function showInfoDetailDialog( link ){
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "infosList" ).rows[ id ];
	var infoTitle       	= table.cells[1].innerHTML.trim();
	var infoStatus  		= table.cells[3].innerHTML.trim();
	var infoCreateTime  	= table.cells[4].innerHTML.trim();
	var position  			= table.cells[6].innerHTML.trim();
	var infoDerive  		= table.cells[2].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "info.infoTitle=" );
	buffer.append( infoTitle );
	buffer.append( "&info.infoStatus=" );
	buffer.append( infoStatus );
	buffer.append( "&info.infoCreateTime=" );
	buffer.append( infoCreateTime );
	buffer.append( "&info.position=" );
	buffer.append( position );
	buffer.append( "&info.infoDerive=" );
	buffer.append( infoDerive );
	buffer.append( "&noPrivilegeOnlyText=true" );
	
	var queryString = encodeURI( buffer.toString() );
	
	showPage('资讯详情','../infoManage/findInfoById.action?action=viewInfo&'+queryString, 610, 360 );
}

function showInfoAudit( link,status ){
	var reasontype = $("#auditTypeValue").val();
	var id = link.id;
	var table = document.getElementById( "infoAuditList" ).rows[ id ];
	var auditComment  		= table.cells[6].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "info.auditComment=" );
	buffer.append( auditComment );
	buffer.append( "&noPrivilegeOnlyText=true" );
	
	var queryString = encodeURI( buffer.toString() );
	if(status==4){
	showPage('审核不通过原因','../infoManage/findInfoAuditDetailById.action?'+queryString, 400, 260 );
	}
	if(status==5){
		showPage('撤销审核的原因','../infoManage/findInfoAuditDetailById.action?'+queryString, 400, 260 );
		}
	}

function showInfoDetail( link ){
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "infoAuditList" ).rows[ id ];
	var infoTitle       	= table.cells[1].innerText.trim();	
	var infoStatus  		= table.cells[3].innerText.trim();
	var infoCreateTime  	= table.cells[4].innerHTML.trim();
	var infoDerive  		= table.cells[2].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "info.infoTitle=" );
	buffer.append( infoTitle );
	buffer.append( "&info.infoStatus=" );
	buffer.append( infoStatus );
	buffer.append( "&info.infoCreateTime=" );
	buffer.append( infoCreateTime );
	buffer.append( "&info.infoDerive=" );
	buffer.append( infoDerive );
	buffer.append( "&noPrivilegeOnlyText=true" );
	
	var queryString = encodeURI( buffer.toString() );
	
	showPage('资讯详情','../infoManage/findInfoById.action?action=viewInfo&'+queryString, 610, 360 );
}


function showAuditDetailDialog( link, messageTip ) {
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "auditResultList" ).rows[ id ];
	var auditContent = table.cells[0].innerHTML.trim();
	auditContent = encodeURI(auditContent);
	showText( messageTip, auditContent, 500, 200, 0, false);
}

function showInfoImagePage(dialogTitle,pageName,dialogW,dialogH)
{
	$("body").append("<div id='dialogbg'></div><div id='dialog'><h2 id='dialog_title'>"+dialogTitle+"<p id='btn_closedialog'></p></h2><iframe name='framelist' id='framelist' frameborder='0' src="+pageName+" height='auto'></iframe><div id='btn_list'><input type='button' value='确定' id='btn_dsure'/></div></div>");
	// var gH = top.frames["framelist"].document.body.scrollHeight;
	// alert(gH);
	$("input,body button").blur();
	$("#dialog").css({width:dialogW});
	$("#dialog").css({height:dialogH});
	var DW = $("#dialog").width();
	var DH = $("#dialog").height()-75;
	var webH = document.body.scrollHeight;
	var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	var getH = (webH > webvH)?webH:webvH;
	$("#dialogbg").height(getH + "px");
	document.getElementById("dialog").style.left = (document.body.offsetWidth-$("#dialog").width()-2)/2 + "px";
	document.getElementById("dialog").style.top = (document.documentElement.clientHeight-$("#dialog").height()-2)/2 + document.documentElement.scrollTop + "px";
	$("#btn_list").css({width:DW});
	$("#cnt_list").css({width:DW});
	$("#framelist").css({padding:"5px",overflow:"hidden"});
	$("#framelist").attr("width",DW-10);
	$("#framelist").attr("height",DH-10);
	// $("#cnt_list iframe").attr("width",DW);
	$("#btn_closedialog,#btn_dsure").click(function()
	 {
		getCBox();
		var dialog = $( "#showSuccessDialog" );
		dialog.val( '2' );
		$("#dialogbg,#dialog").remove();
		// 如果是IE6浏览器，select标签才需要隐藏
		if ( $.browser.msie && $.browser.version == '6.0' ) {
			$("select").css({visibility:"visible"});
		}
	 });
	
	// 如果是IE6浏览器，select标签才需要隐藏
	if ( $.browser.msie && $.browser.version == '6.0' ) {
		$("select").css({visibility:"hidden"});
		$("#dialog select").css({visibility:"visible"});
	}
	
	moveWindow("dialog","dialog_title");
	
	return false;
}
function getCBox()
{
	 var con = window.frames[1].document.getElementById("box");
	 var cboxNum = con.getElementsByTagName("input");
	 var getValue = "";
	 for(var tt=0;tt<cboxNum.length;tt++)
	 {
		 if(cboxNum[tt].checked == true)
		 {
			 getValue += cboxNum[tt].getAttribute("value") + ",";
		 }
	 }
	 
	 getValue = getValue.substring(0,getValue.length-1);
	if(getValue != ""){
		$("#info1").val(getValue);
	}
	
}
function loadDatePick() {
	$( "#startTime" ).click( function() {
		WdatePicker({el:'startTime',readOnly:true});
	});

	$( "#startTimeImg" ).click( function() {
		WdatePicker({el:'startTime',readOnly:true});
	});
	
	$( "#endTime" ).click( function() {
		WdatePicker({el:'endTime',readOnly:true});
	});

	$( "#endTimeImg" ).click( function() {
		WdatePicker({el:'endTime',readOnly:true});
	});
}

function showPublishTaskDetailDialog(link, messageTip){
	var id = link.id;
	link.href = "###";
	var table = document.getElementById( "publishTaskTableList" ).rows[ id ];
	var id = table.cells[7].innerHTML.trim();
	var buffer = new StringBuffer();
	buffer.append( "publishTask.taskID=" );
	buffer.append( id );
	buffer.append( "&noPrivilegeOnlyText=true" );
	var queryString = encodeURI( buffer.toString() );
	
	showPage(messageTip,'../publishTask/browsePublishTaskDetail.action?'+queryString, 700, 600 );

}
function showReviewPage(pageType,portalVersionId){
	showPage('预览','../review/toViewPage.action?pageType='+pageType + "&portalVersionId=" + portalVersionId, 690, 590 );
}


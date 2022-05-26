var Dailog={
	result:'close',
	ret_ok:'ok',
	ret_cancel:'cancel',
	ret_close:'close',
	dailog:{},
	init:function(dW,dH,obj){
	    document.getElementById("ok").focus();
		var headH = 61;
		var webH = document.documentElement.clientHeight;
		var webSH = document.documentElement.scrollHeight;
		var webW = document.documentElement.clientWidth;
		var leftpx = (webW-dW)/2;
		
		var toppx = (webH-dH-headH)/2;
		
		$("#dialog").css({height:(dH)+"px",width:dW+"px",left:leftpx+"px",top:toppx+"px"});//设置弹出对话框大小及显示位置
		
		$("#dialogFrame").css({height:(dH-85)+"px",width:dW+"px",margin:"2px 0 0 0"});//设置对容框中内容样式
		
		$("#dialog_bg").css({height:webSH+"px"});//设置白影高度
		
		$("#dialog_btnlist").css({width:"100%","text-align":"center"});//设置按钮样式
		
		$("#ok").css(
			{ 
				width:'120px',
				height:'30px'
			}
		)
		
		$("#cancel").css(
			{ 
				width:'120px',
				height:'30px'
			}
		)		
		//$(obj).blur();
		
		this.MoveWindow('dialog_title','dialog');
		
		$("#dialog_close").click(
			function(){
						result=Dailog.ret_close;
						Dailog.dailog.onClose();
						$("#dialog").remove();
						$("#dialog_bg").remove();
			}
		);
		
		$("#ok").click(
			function(dailog){
						result=Dailog.ret_ok;
						Dailog.dailog.onOK();
						$("#dialog").remove();
						$("#dialogbg").remove();
						$("#dialog_bg").remove();
			}
		);
		
		$("#cancel").click(
			function(){
					result=Dailog.ret_cancel;
					Dailog.dailog.onCancel();
					$("#dialog").remove();
					$("#dialog_bg").remove();						
			}
		);
	},	
	openWindow:function(dT,dW,dH,dData,dstyle,tipstyle,obj){
		if(dstyle == 0)
		{
				if(tipstyle == 0)
				{
					var html=[];
					html.push("<div id='dialog_bg'></div><div id='dialog'>");
					html.push("<div id='dialog_title'>");
					html.push(dT);//标题
	 				html.push("<div id='dialog_close'>");
	 				html.push("</div>");
	 				html.push("</div>");
					html.push("<table id='dialogFrame' width='100%' border='0' cellspacing='0' cellpadding='0'>");
					html.push("<tr>");
					html.push("<td scope='row' align='center' valign='middle' style='color:#F00'>");
					html.push("<img src='../images/bg_tipfailth.png' width='15' height='13' />");
					html.push(dData);
					html.push("</td>");
					html.push("</tr>");
					html.push("</table>");
					html.push("<div id='dialog_btnlist'>");
					html.push("<input id='ok' class='inputstyle' type='button' value='确 定'/>");
					html.push("</div>");
					html.push("</div>");
					$("body").append(html.join(""));	
					$("#dialog img").css({margin:"0 5px 0 0"});
				}
				else if(tipstyle == 1)
				{
					var html=[];
					html.push("<div id='dialog_bg'></div><div id='dialog'>");
					html.push("<div id='dialog_title'>");
					html.push(dT);
					html.push("<div id='dialog_close'>");
					html.push("</div>");
					html.push("</div>");
					html.push("<table id='dialogFrame' width='100%' border='0' cellspacing='0' cellpadding='0'>");
					html.push("<tr>");
					html.push("<td scope='row' align='center' valign='middle'>");
					html.push("<img src='../../resources/images/bg_tipsuccess.png' width='15' height='13' />");
					html.push(dData);
					html.push("</td></tr></table>");
					html.push("<div id='dialog_btnlist'><input id='ok' align='center' class='inputstyle' type='button' value='确 定'/></div></div>");
					$("body").append(html.join("")); 
					$("#dialog img").css({margin:"0 5px 0 0"});
				}
				else if(tipstyle == 2)
				{
					var html=[];
					html.push("<div id='dialog_bg'></div><div id='dialog'>");
					html.push("<div id='dialog_title'>");
					html.push(dT);
					html.push("<div id='dialog_close'>");
					html.push("</div>");
					html.push("</div>");
					html.push("<table id='dialogFrame' width='100%' border='0' cellspacing='0' cellpadding='0'>");
					html.push("<tr>");
					html.push("<td scope='row' align='center' valign='middle'>");
					html.push(dData);
					html.push("</td></tr></table>");
					html.push("<div id='dialog_btnlist'><input id='ok' class='inputstyle' type='button' value='确 定'/></div></div>");
					$("body").append(html.join("")); 
					$("#dialog img").css({margin:"0 5px 0 0"});
				}else 
				{
					var html=[];
					html.push("<div id='dialog_bg'></div><div id='dialog'>");
					html.push("<div id='dialog_title'>");
					html.push(dT);
					html.push("<div id='dialog_close'>");
					html.push("</div>");
					html.push("</div>");
					html.push("<table id='dialogFrame' width='100%' border='0' cellspacing='0' cellpadding='0'>");
					html.push("<tr><td scope='row' align='center' valign='middle'>");
					html.push(dData);
					html.push("</td></tr>");	
					html.push("</table>");
					html.push("<div id='dialog_btnlist'>");	
					html.push("<input id='ok' class='inputstyle' type='button' value='确 定'/>&nbsp;&nbsp;&nbsp;&nbsp;");		
					html.push("<input id='cancel' class='inputstyle' type='button' value='取 消'/>");
					html.push("</div>");
					html.push("</div>");
					$("body").append(html.join(""));
				}	
		}
		else
		{
					var html=[];
					html.push("<div id='dialog_bg'></div><div id='dialog'>");
					html.push("<div id='dialog_title'>");
					html.push(dT);
					html.push("<div id='dialog_close'>");
					html.push("</div>");
					html.push("</div>");
					html.push("<iframe scrolling='auto' frameborder='0' width='100%' height='auto' id='dialogFrame' name='dialogFrame' src='"+dData+"' ></iframe>");
					html.push("<div id='dialog_btnlist'>");				
					html.push("<input class='inputstyle' id='ok' type='button' value='确 定'/>&nbsp;&nbsp;&nbsp;&nbsp;");
					html.push("<input class='inputstyle' id='cancel' type='button' value='取 消'/>");
					html.push("</div>");
					html.push("</div>");
					$("body").append(html.join(""));	
		}
		this.init(dW,dH,obj);	
		this.dailog=this;	
		return this;
	},
	MoveWindow:function(hanldID,windowID)
	{
		var posx,posy,posx1,posx1,posx2,posx2,mbx,mby;
		var handle = document.getElementById(hanldID);
		var moveWindow = document.getElementById(windowID);
		function mdown(event)
		{
			event = window.event || event;
			posx = event.clientX;
			posy = event.clientY;
			mbx = event.clientX - moveWindow.offsetLeft;
			mby = event.clientY - moveWindow.offsetTop;
			//moveWindow.innerHTML = posx +" " + posy;
			moveWindow.onmousemove = mmove;
			moveWindow.onmouseup = mup;
			moveWindow.onmouseout = mout;//*/
		}
		var mmove = function(event)
		{
			//alert('');
			event = window.event || event;
			posx1 = event.clientX;
			posy1 = event.clientY;
			//moveWindow.innerHTML = posx1 +" " + posy1;
			moveWindow.style.left = posx1 - mbx + "px";
			moveWindow.style.top = posy1 - mby + "px";
		}
		function mup(event)
		{
			event = window.event || event;
			posx2 = event.clientX;
			posy2 = event.clientY;
			//moveWindow.innerHTML = posx2 +" " + posy2;
			moveWindow.onmousemove = "";
		}
		function mout(event)
		{
			event = window.event || event;
			moveWindow.onmousemove = "";
		}
		handle.onmousedown = mdown;
	},
	onCancel:function(){//子类实现
		
	},
	onClose:function(){//子类实现
	
	},
	onOK:function(){//子类实现

	}
	
}

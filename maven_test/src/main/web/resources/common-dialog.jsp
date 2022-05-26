
<% 
  String path = request.getContextPath();
%>

<script type="text/javascript">
     var  globalWebAppURL = "<%=path%>";


  	//$.extend($.fn.dialog.defaults,dialogParams);
 	
 	var dialogOnbeforeOpen = {
 			resizable:true,
 			onBeforeOpen:function(){
 				var tarWidth = $(this).panel('options').width;
 				var tarHeight = $(this).panel('options').height;
 				var tarTop = $(this).panel('options').top;
 				//console.log("--top---"+tarTop);
 				var compareHeight = document.body.clientHeight * 0.75;
 				if("auto" == tarHeight){
 					tarHeight = compareHeight;
 				}
 				console.log(" ==999**compareHeight**999== " + compareHeight);
 				console.log(" ==888**tarHeight**888== " + tarHeight);
 				console.log(" ==777**tarTop**777== " + tarTop);

 				var calibrValue = 75;
 				if(compareHeight < tarHeight ){
 	 				$(this).find(".panel").css("overflow-y","auto");
 	 				$(this).find(".panel").css("overflow-x","hidden");
 	 				$(this).find(".panel").css("margin","3px");
 	 				
 					console.log(" ==use compareHeight == ");
 					$(this).dialog("resize",{ width: this.width, height: compareHeight, top:tarTop + calibrValue });
 	 				console.log("---panel-body height--"+$(this).find(".panel-body").height());

 				}else{console.log(" ==use tarHeight == ");
 					$(this).dialog("resize",{ width: this.width, height: tarHeight, top:tarTop });
 				}
 
 				$(this).find(".panel-body").css("height",$(this).find(".panel-body").height()-25);
 	 		}
 	 	};
 	 $.extend($.fn.dialog.defaults,dialogOnbeforeOpen);
</script>

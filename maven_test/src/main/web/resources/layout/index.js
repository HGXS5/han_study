(function(window){
//    var version = getQueryStr(location.href, "version");
//    var areaCode = getQueryStr(location.href, "areaCode");
//    var terminalType = getQueryStr(location.href, "terminalType");
//    var appType = getQueryStr(location.href, "appType");
//    var iepgUrl = getQueryStr(location.href, "iepgUrl");


    jQuery("input").bind("click", function(){
    jQuery("#mqList").empty();
    if(this.name === "1"){
    getData(1);
    type = 1;
    }else if(this.name === "0"){
    getData(0);
    type = 0;
    }else if(this.name === "2"){
		getData(2);
		type = 2;	
	}else{
    window.location.reload();
    type = 0;
    }
    })

function getData(_type,next){
    var url = iepgUrl + "/iepg/getMarquee?version=" + version +"&areaCode=" + areaCode +"&terminalType="+ terminalType +"&appType="+appType + "&type=" + _type;
    jQuery.ajax({
    url : url,
    dataType : "JSON",
    type : "GET",
    complete : function(dataJson){
	type = _type;	
    var data = window.JSON.parse(dataJson.responseText);
    for(var i = 0,length = data.marqueeList.length; i < length; i++){
    showMarquee($("#mqList"),data.marqueeList[i],i);
    };
	if(next&& next<=2){
		getData(next,next+1)
	}
}
})
};

function showMarquee(_obj,_data,num){
   /* var imgString = "", textString = "";*/
    var allString = "";
    if(_data.bgImage!=null){
        var divHTML = '<div class="m" style="position:absolute;left:'+ _data.position[0] +'px;top:'+ _data.position[1] +'px;width:'+ _data.position[2]
            +'px;height:'+ _data.position[3] +'px;background-color:'+ _data.bgColor +';background:url('+ _data.bgImage +') no-repeat;opacity:'+ parseFloat(_data.bgAlpha / 100) +';"></div>';
    }else{
        var divHTML = '<div class="m" style="position:absolute;left:'+ _data.position[0] +'px;top:'+ _data.position[1] +'px;width:'+ _data.position[2]
            +'px;height:'+ _data.position[3] +'px;background-color:'+ _data.bgColor +';opacity:'+ parseFloat(_data.bgAlpha / 100) +';"></div>';
    }

    $(_obj).append(divHTML);
    if(type == 0){
    for(var i = 0; i<_data.infoList.length; i++){
    if(_data.infoList[i].type == "image"){
        if(_data.direction=="right" || _data.direction=="down"){
            allString = '<li><img src="'+_data.infoList[i].image.url+ '"></li>'+allString;
        }else{
            allString += '<li><img src="'+_data.infoList[i].image.url+ '"></li>';
        }
    }
    if(_data.infoList[i].type == "font"){
         var rollString = _data.infoList[i].font.text;

    if(_data.direction=="right" ){
        var splitext = rollString.split("");
        var lastString = "";
        var temString ="";
        for(var j=splitext.length-1;j>=0; j--){
            /*var Regx = /^[A-Za-z0-9]*$/*/
            /*var Regx = /[\w\.\_\-]/*/
            var Regx = /[ -~]/
            if (Regx.test(splitext[j])) {
                temString=  splitext[j] + temString;
            }
            else {
                lastString = lastString +temString + splitext[j];
                temString ="";
            }
            if(j == 0){
                lastString = lastString +temString ;
                temString ="";
            }
            rollString = lastString;
        }
        allString = '<li style="line-height:'+ _data.position[3] +'px;height:'+ _data.position[3] +'px;font-size:'+_data.infoList[i].font.size+'px;color:'+ _data.infoList[i].font.color +';opacity:'+ parseFloat(_data.infoList[i].font.alpha / 100) +'">'+rollString + '&nbsp;&nbsp;&nbsp;</li>'+allString;
    }else if(_data.direction=="down"){
        allString = '<li style="line-height:'+ _data.position[3] +'px;height:'+ _data.position[3] +'px;font-size:'+_data.infoList[i].font.size+'px;color:'+ _data.infoList[i].font.color +';opacity:'+ parseFloat(_data.infoList[i].font.alpha / 100) +'">'+rollString + '&nbsp;&nbsp;&nbsp;</li>'+allString;
    }else{
        allString += '<li style="line-height:'+ _data.position[3] +'px;height:'+ _data.position[3] +'px;font-size:'+_data.infoList[i].font.size+'px;color:'+ _data.infoList[i].font.color +';opacity:'+ parseFloat(_data.infoList[i].font.alpha / 100) +'">'+rollString + '&nbsp;&nbsp;&nbsp;</li>';

    }

    }
}
var marHtml = '<marquee direction="'+ _data.direction +'" scrollamount="'+ _data.stepDistance +'" scrolldelay="'+ _data.stepInterval + '" onMouseOver="this.stop()" onMouseOut="this.start()"><ul>'+ allString +'</ul></marquee>';
jQuery(".m:eq("+ num +")").html(marHtml);

        if(_data.direction=="down" || _data.direction=="up")
            $("ul li").css('display','block');
        }
}

//获取url中param参数的值  例子：var serviceCode = getQueryStr(location.href, "serviceCode");
function getQueryStr(_url, _param){
    var rs = new RegExp("(^|)" + _param + "=([^\&]*)(\&|$)", "g").exec(_url), tmp;
    if( tmp = rs){
    return tmp[2];
    }
return "";
}

document.getElementById("btn").addEventListener("dragstart", function(e){
    var offsetX = e.pageX - $("#btn").offset().left;
    var offsetY = e.pageY - $("#btn").offset().top;
    offsetObj = {x:offsetX,y:offsetY};
}, false);

var contentObj = document.getElementById("con");
contentObj.ondragover = function(e){

    e.preventDefault();
    };
contentObj.ondrop = function(e){
    var offsetX = offsetObj.x;
    var offsetY = offsetObj.y;
    $("#btn").css({"top":e.pageY - offsetY,"left": e.pageX - offsetX})
};
getData(0,1);
//setTimeout(function(){getData(1);type = 1},500);
//setTimeout(function(){getData(2);type = 2},500);
})(window);

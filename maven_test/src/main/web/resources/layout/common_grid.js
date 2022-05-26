/**
 * Created by 905112 on 14-5-21.
 */
if(!(navigator.userAgent.indexOf("WebKit") > 0 || navigator.userAgent.indexOf("webkit") > 0 || navigator.userAgent.indexOf("Chrome") > 0)) {
	alert("Sorry!!\n您的浏览器不支持html5拖拽事件，建议使用Chrome浏览器浏览此功能。");
}

//切换角色
function changeLayout() {
    var areaId = jQuery("#areaId").val();
    var planId = jQuery("#planId").val();
    var roleId = jQuery("#roleId").val();
    var url = "changeLayout.do?areaId=" + areaId + "&planId=" + planId + "&roleId=" + roleId;
    showPage('切换布局', url, 600, 220);
}

//获取url中param参数的值  例子：var serviceCode = getQueryStr(location.href, "serviceCode");
function getQueryStr(_url, _param){
    var rs = new RegExp("(^|)" + _param + "=([^\&]*)(\&|$)", "g").exec(_url), tmp;
    if( tmp = rs){
        return tmp[2];
    }
    return "";
}
/*
 后台提供数据
 lay=titleAndLayoutInfo				:已布局数据
 sou=resourceCenter					:资源
 type=resourceCenterType			:资源分类
 */

var lay,sou,type;
//子布局页面类型
var pageType = getQueryStr(location.href, "pageType");
//获取分辨率 resolution 0为720p 1为1080p 2为4k 3为8k
var resolution = jQuery("#resolution").val();
//返回分辨率换算倍数
var pNum = setSize(resolution);

//进入子布局页面
function goToChildLayout(){
    var titleId = jQuery("#titleId").val();
    var terminalType = jQuery("#terminalType").val();
    var resolution = jQuery("#resolution").val();
    var planId = jQuery("#planId").val();
    var cellId = actionOpt.myId;
    if(cellId == ""){
        if(confirm("未保存布局，确认保存")){
            saveLayout();
        }
    }else{
        window.location.href = '/portalMS/titleManage/titleAndLayoutInfo_child.jsp?titleId='+ titleId +'&cellId='+ cellId + '&terminalType='+ terminalType + '&resolution='+ resolution + '&planId=' + planId + '&pageType=child';
    }
}

//isFirstChange解决改变资源标签时，点两次返回键问题
var isFirstChange = false;
//从子布局返回布局页面
function goBack(){
    if(isFirstChange){
        history.go(-2);
    }else{
        history.go(-1);
    }
}

//子布局请求layout布局页面数据
function getChildLayoutAjax(){
    var titleId = getQueryStr(location.href, "titleId");
    var cellId = getQueryStr(location.href, "cellId");
    var terminalType = getQueryStr(location.href, "terminalType");
    var resolution = getQueryStr(location.href, "resolution");
    var planId = getQueryStr(location.href, "planId");
    //在子布局页面获取分辨率转换倍数
    pNum = setSize(resolution);

    jQuery("#titleId").val(titleId);
    jQuery("#cellId").val(cellId);
    jQuery("#terminalType").val(terminalType);
    jQuery("#resolution").val(resolution);
    jQuery("#planId").val(planId);
    var url = portalMSHost + "/titleManage/querySubLayout.do?titleId=" + titleId + "&cellId=" + cellId + "&terminalType=" + terminalType + "&resolution=" + resolution;
    $.ajax({
        type : "POST",
        url : url,
        async : false,
        success : function(data) {
            if(data) {
                if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                    document.write(data);
                }else{
                    lay = eval(data);
                }
            }
        }
    });
}

//子布局保存
function saveChildLayout(){
    var url = portalMSHost + "/titleManage/saveSubLayoutInfo.do?";
    $.ajax({
        cache: true,
        type : "POST",
        url : url,
        data : $('#saveForm').serialize(),// 你的formid
        async: false,
        success : function(data) {
            if(data == "success"){
                alert("操作成功");
            }else if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                document.write(data);
            }
        },
        error : function(data) {
            alert("操作失败");
        }
    });
}

//请求标题及layout布局数据
function getTitleAndLayoutInfoAjax() {
    var planRoleId = jQuery("#planRoleId").val();
    var titleId = jQuery("#titleId").val();
    var terminalType = jQuery("#terminalType").val();
    var resolution = jQuery("#resolution").val();
    var url = portalMSHost + "/titleManage/queryTitleAndLayoutInfoList.do?planRoleId=" + planRoleId + "&titleId=" + titleId + "&terminalType=" + terminalType + "&resolution=" + resolution;
    $.ajax({
        type : "POST",
        url : url,
        async : false,
        success : function(data) {
            if(data) {
                if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                    document.write(data);
                }else{
                    lay = eval(data);
                }
            }
        }
    });
}

//请求资源布局的数据
function getResourceCenterAjax() {
    var planId = jQuery("#planId").val();
    var titleId = jQuery("#titleId").val();
    var terminalType = jQuery("#terminalType").val();
    var resolution = jQuery("#resolution").val();
    if(!titleId && lay.length > 0) {
        titleId = lay[0].id;
    }
    var url = portalMSHost + "/titleManage/queryResourceCenterList.do?planId=" + planId + "&titleId=" + titleId + "&terminalType=" + terminalType + "&resolution=" + resolution;
    $.ajax({
        type : "POST",
        url : url,
        async : false,
        success : function(data) {
            if(data) {
                if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                    document.write(data);
                }else{
                    sou = eval(data);
                }
            }
        }
    });
}

//请求资源类型的数据
function getResourceCenterTypeAjax() {
    var url = portalMSHost + "/titleManage/queryResourceCenterType.do";
    $.ajax({
        type : "POST",
        url : url,
        async : false,
        success : function(data) {
            if(data) {
                if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                    document.write(data);
                }else{
                    type = eval(data);
                }
            }
        }
    });
}

//切换页签前显示的下标
var tagIndex = 0;
//正在修改参数的对象
var actionOpt = {};
//每次加载五十条数据
var amountOfPage = 50;
//模块模板
var module = "<div id='{id}' title='{description}'  draggable='true' class='m' style='left:{left}px;top:{top}px;width:{width}px;height:{height}px;background-color:{bgcolor}'><div murl='{url}' style='position:relative;width:100%;height:100%;top:0px;left:0px;'></div><span style='position:absolute;font-size:13px;line-height:15px;text-align:right;z-index:101;top:0px;'>{title}</span></div>";

var moveModule = "<div class='rRightDown' '></div>";

var zoomStartX = 0, zoomStartY = 0, zoomEndX = 0, zoomEndY = 0, zoom = false, zoomObj, widthBeforeZoom, heightBeforeZoom;
function zoomMouseDown(e) {
	zoom = true;
	e.preventDefault();
	zoomObj = jQuery(this).parent();
	var data = zoomObj.data("data");
	console.log(data.cellX, data.cellY);
	widthBeforeZoom = data.celX;
	heightBeforeZoom = data.celY;
	console.log(data);
	zoomObj.attr("draggable", false);
	zoomEndX = zoomStartX = e.pageX;
	zoomEndY = zoomStartY = e.pageY;
}

function zoomMouseMove(e) {
	if(!zoom)
		return;
	e.preventDefault();
	var w = zoomObj.width();
	var h = zoomObj.height();
	var mx = e.pageX;
	var mh = e.pageY;
	var data = zoomObj.data("data");
	var orginX = Math.round((data.ofsX - 5) / 40);
	var orginY = Math.round((data.ofsY - 5) / 40);

	var ownid = data.id;

	var new_w = w + mx - zoomEndX;
	var new_h = h + mh - zoomEndY;
	cellX = new_w < 30 ? orginX : Math.floor((new_w + 10) / 40);
	cellY = new_h < 30 ? orginY : Math.floor((new_h + 10) / 40);

	if(new_w < 30)
		zoomObj.width(30);
	else if(zoomCheckCellY(orginX, orginY, orginX + cellX, orginY + cellY, ownid)) {
		zoomObj.width(new_w);
	}
	if(new_h < 30)
		zoomObj.height(30);
	else if(zoomCheckCellX(orginX, orginY, orginX + cellX, orginY + cellY, ownid)) {
		zoomObj.height(new_h);
	}
	zoomEndY = mh;
	zoomEndX = mx;
}

function zoomCheckCellY(originX, originY, cellX, cellY, ownid) {
	var x = cellX - originX;
	if(x < 0)
		return true;
	for(var j = originX; j < cellX; j++) {
		for(var i = originY; i < cellY; i++) {
			var obj = jQuery("#newLayout>li:eq(" + (j + 1) + ")>div:eq(" + i + ")");
			if(obj.attr("ownid") != ownid && !obj.attr("fill")) {
				return false;
			}
		}
	}
	return true;
}

function zoomCheckCellX(originX, originY, cellX, cellY, ownid) {
	var y = cellY - originY;
	if(y < 0)
		return true;
	for(var j = originY; j < cellY; j++) {
		for(var i = originX; i < cellX; i++) {
			var obj = jQuery("#newLayout>li:eq(" + i + ")>div:eq(" + (j + 1) + ")");
			if(obj.attr("ownid") != ownid && !obj.attr("fill")) {
				return false;
			}
		}
	}
	return true;
}

function zoomMouseEnd(e) {
	if(!zoom) {
		return;
	}
	var w = zoomObj.width();
	var h = zoomObj.height();
	var w = Math.round((w + 10) / 40);
	var h = Math.round((h + 10) / 40);
	var data = zoomObj.data("data");
	var orginX = Math.round((data.ofsX - 5) / 40);
	var orginY = Math.round((data.ofsY - 5) / 40);
	zoomObj.width(w * 40 - 10);
	zoomObj.height(h * 40 - 10);
	console.log(orginX, orginY, widthBeforeZoom, heightBeforeZoom);
	refreshArea(orginX, orginY, widthBeforeZoom, heightBeforeZoom, "newLayout");
	setArea(orginX, orginY, data.celX = w, data.celY = h, data.id);
	data.newMove = 1;
	zoom = false;
	zoomObj.attr("draggable", true);
	isEdit = true;
}

function setZoomEvent(obj) {
	obj.addEventListener("mousedown", zoomMouseDown, false);
	document.addEventListener("mousemove", zoomMouseMove, false);
	document.addEventListener("mouseup", zoomMouseEnd, false);
}

//子布局添加资源标签及布局数据
function setLayoutTitle() {
    //默认页签ID
    var titleId = jQuery("#titleId").val();
    //生成资源分类标签
    for(var i = 0; i < type.length; i++) {
        jQuery("#tab_lay_x ul").append("<li><a href='#' onclick='filterModule(" + i + ",\"" + type[i].keyCode + "\")'>" + type[i].keyValue + "</a></li>");
    }
    jQuery("#tab_lay_x ul").append("<li><a href='#' onclick='filterModule(-1)'>全部</a></li>");
    //生成某一页签下已保存的布局
    setLayout(0, titleId);
    //显示资源第一个分类
    jQuery("#tab_lay_x>ul>li:first-child>a").click();
}

//添加页签
function setTitle() {
    if(!lay) {
        jQuery("#tab_switchdraw_x ul:first-child").append("<li>请在首页标签管理给标签关联角色！</li>");
        return;
    }
    //默认页签ID
    var titleId = jQuery("#titleId").val();
    var index;
    //生成角色下的所有标签
    for(var i = 0; i < lay.length; i++) {
        //所要显示数据页签的下标
        if(lay[i].id == titleId)
            tagIndex = index = i;
        jQuery("#tab_switchdraw_x ul:first-child").append("<li><a href='#' onclick='turnPage(" + i + ",\"" + lay[i].id + "\")' ondblclick='showTitleData("+ lay[i].id +")'>" + lay[i].name + "</a></li>");
    }
    if(!index) {
        index = 0;
				if(!titleId && lay.length>0){
					titleId=lay[0].id;
					jQuery("#titleId").val(titleId);
				}	
    }
    //生成资源分类标签
    for(var i = 0; i < type.length; i++) {
        jQuery("#tab_lay_x ul").append("<li><a href='#' onclick='filterModule(" + i + ",\"" + type[i].keyCode + "\")'>" + type[i].keyValue + "</a></li>");
    }
    jQuery("#tab_lay_x ul").append("<li><a href='#' onclick='filterModule(-1)'>全部</a></li>");
    //生成某一页签下已保存的布局
    setLayout(index, titleId);
    //显示资源第一个分类
    jQuery("#tab_lay_x>ul>li:first-child>a").click();
}

//打开新的窗口展示对应titleId的数据
function showTitleData(_id){
	var planId = jQuery("#planId").val();
    var url = iepgUrl + "/iepg/getTabCell?tabId=" + _id + "&planId="+planId;
    window.open(url);
}

//点击页签
function turnPage(index, id) {
    if(isEdit) {
        if(!confirm("布局或模块属性有修改，是否要保存？")) {
            jQuery("#titleId").val(id);
            getTitleAndLayoutInfoAjax();
            getResourceCenterAjax();
            setLayout(index, id);
            jQuery("#tab_lay_x>ul>li:first-child>a").click();
        } else {
            jQuery("#action").val('turnPage');
            jQuery("#turnTitleId").val(id);
            saveLayout();
        }
    } else {
        jQuery("#titleId").val(id);
        getTitleAndLayoutInfoAjax();
        getResourceCenterAjax();
        setLayout(index, id);
        jQuery("#tab_lay_x>ul>li:first-child>a").click();
    }
    //重置是否修改
    isEdit = false;
}

//添加已保存的布局 index标签下标 id标签id
function setLayout(index, id) {
	//刷新刷局格子
	refreshAll(".layout");
	//数据
	if(titleId && lay.length>0){
		var datas=lay[index].titleLayoutList;
	}	
	//页签id=submitId提交时要用的参数
	submitId = id;
	//给页签添加选中样式
	tagFocus(index);
	//遍历数据 生成html
	if(datas){
	for(var i = 0; i < datas.length; i++) {
		var d = datas[i];
		//海报地址
		var url = d.posterServerHttp + d.localPath;
		var bgColor = d.cellColor || cellDefaultColor;
		bgColor = getRgbaColor(bgColor, d.cellAlpha);
		//生成html串
		var html=module.replace("{id}",d.categoryResourceId)
					   .replace("{left}",40*d.cellX+5)
					   .replace("{top}",40*d.cellY+5)
					   .replace("{width}",40*d.spanX-10)
					   .replace("{height}",40*d.spanY-10)
					   .replace("{bgcolor}",bgColor)
					   .replace("{title}",d.resourceName)
					   .replace("{description}",d.description)
					   .replace("{url}",url);
		//存在到html模板里的数据
		var moveObj = {
			id : d.categoryResourceId, //模块ID
			myId : d.id, //cell id
			newMove : "0", //是否移动过
			x : 0, //x坐标
			y : 0, //y坐标
			celX : d.spanX, //x轴占用格子数
			celY : d.spanY, //y轴占用格子数
			ofsX : (40 * d.cellX + 5), //模板在页面的x轴偏移
			ofsY : (40 * d.cellY + 5), //模板在页面的y轴偏移
			overlay : true, //是否叠加
			overMove : false, //是否移动
			localPath : d.localPath, //图片服务器上的相对地址
			posterServerHttp : d.posterServerHttp, //服务地址加端口
			container : "newLayout", //所在布局的html id
			cellState : d.cellState, //模板状态
			cellColor : d.cellColor, //背景颜色
			cellAlpha : d.cellAlpha, //透明度
			posterList : d.posterList, //多张海报的参数(缩放使用到)
			//isAuth : d.isAuth,
			minSoftVer : d.minSoftVer,
            productCode : d.productCode,
            margin : d.margin,
            resourceType : d.resourceType //图片的资源类型
		};
		//找到模块起点坐标的格子
		var poit = jQuery("#newLayout>li:eq(" + d.cellX + ")>div:eq(" + d.cellY + ")");
		//ownid格子所属模块id 所果格子未被占用所为undefined
		var ownid = poit.attr("ownid");
		//叠加时样式修正
		var df_style = {
			"top" : "-1px",
			"left" : "-1px"
		};
		//如果格子未被占(ownid==undefined)用则正常添加模板，如果已被占用则叠加
		if(!!ownid) {
			//取出模块父层
			var parent = jQuery("#" + ownid).parent("div[ownid='" + ownid + "'][class='wrap']");
			//如果已经添加有模块，则会有一个div[class='wrap']包裹所有模块
			if(!!parent && parent.length > 0) {//已有叠加，直接添加到div[class='wrap']里面
				var obj = jQuery(html);
				//直接添加到父层里
				parent.append(obj.css(df_style).data("data", moveObj));
				//添加多模块切换显示
				setpage(parent);
			} else {//如果还没有叠加，则先包裹到div[class='wrap']再叠加
				//生成父层且添加样式
				var wrap_m = "<div class='wrap' ownid='" + ownid + "'></div>";
				var ownObj = jQuery("#" + ownid);
				deleteZoom(ownObj);
				var style = {
					"top" : ownObj.css("top"),
					"left" : ownObj.css("left"),
					"width" : ownObj.width(),
					"height" : ownObj.height()
				};
				//添加到容器,且添加样式
				var wrapObj = jQuery(wrap_m).css(style);
				jQuery(".layout").append(wrapObj);
				//包裹原来的模块，且修改正样式
				wrapObj.append(ownObj.css(df_style));
				//把新模块添加到div[class='wrap']里面，且添加样式
				var obj = jQuery(html);
				wrapObj.append(obj.css(df_style).data("data", moveObj));
				//把原来的模块删除掉
				jQuery(".layout>#" + ownid).remove();
				//添加多模块切换显示
				setpage(wrapObj);
			}
		} else {
			//直接添加到容器里
			jQuery(".layout").append(jQuery(html).data("data", moveObj).append(moveModule));
			//标记格子已被占用
			setArea(d.cellX, d.cellY, d.spanX, d.spanY, d.categoryResourceId);
		}
	}
	}
	//给模块事件
	jQuery(".m").each(function(i) {
		//给模块添加双击事件，提供参数的修改页面
		jQuery(this).dblclick(function() {
			var data = jQuery(this).data("data");
			//显示参数修改页面
			showModuleOpt(data);
		});
		//给模块添加拖拽事件
		this.addEventListener("dragstart", drag, false);
		this.addEventListener("dragend", dragend, false);
	});
	var zooms = document.getElementsByClassName("rRightDown");
	for(var i = 0; i < zooms.length; i++) {
		setZoomEvent(zooms[i]);
	}
    setTimeout(function() {
        loadImg();
    }, 100);
}

function submitCheck() {
	var minSoftVer = jQuery("#minSoftVer_op").val();
    var productCode = jQuery("#productCode").val();
    var offsetPos = jQuery("#offsetPos").val();
	var reg1 = /^\d+$/;
    var reg2 = /^[0-9a-zA-Z_]+$/;
    var reg3 = /^(-)?\d+,(-)?\d+,\d+,\d+$/;
	if(minSoftVer) {
		if(minSoftVer.match(reg1) == null) {
			alert("版本号必须是正整数");
			return false;
		}
	}
    if(productCode){
        if(productCode.match(reg2) == null) {
            alert("产品编码必须为数字、字母、下划线");
            return false;
        }
    }
    if(offsetPos){
        if(offsetPos.match(reg3) == null) {
            alert("偏移量格式有误");
            return false;
        }
    }
	return true;
}

//保存布局
function saveLayout() {
    //当前页面ID添加到表单元素里
    jQuery("#titleId").val(submitId);
    if(pageType == "child"){
        submitId = "";
    }
    var str = "";
    //最后显示的页面，也就是要保存的页签布局数据(点击保存时只保存当前页签下的布局)
    var l = lastShowLayout = jQuery(".layout div[class='m']");
    for(var i = 0; i < l.length; i++) {
        var inTheContainer = 0;
        var o = jQuery(l[i]).data("data");
        if(jQuery(l[i]).parent().attr("class") == "wrap") {
            inTheContainer = 1;
        }
        var cellX = Math.round((o.ofsX - 5) / 40);
        var cellY = Math.round((o.ofsY - 5) / 40);
        //后台要用的数据串
        if(o.minSoftVer == "" || o.minSoftVer == null) {
            o.minSoftVer = " ";
        }
        if(o.productCode == "" || o.productCode == null){
            o.productCode = " ";
        }
        if(o.margin == "" || o.margin == null){
            o.margin = " ";
        }
        str += o.myId + "^" + submitId + "^" + o.id + "^" + cellX + "^" + cellY + "^" + o.celX + "^" + o.celY + "^" + o.cellState + "^" + o.cellColor + "^" + o.cellAlpha + "^" + inTheContainer + "^" + o.newMove + "^" + o.minSoftVer + "^" + o.productCode + "^" + o.margin + "^" + o.resourceType + ";";
    }
    str = str.substring(0, str.lastIndexOf(";"));
    console.log(str);
    //把数据串添加到表单元素里
    jQuery("#layoutInfo").val(str);
    str = "";
    //文件夹(所有的叠加模块的父层)
    var container = jQuery(".layout>div[class='wrap']");
    for(var i = 0; i < container.length; i++) {
        var o = jQuery(container[i]).children().eq(0).data("data");
        var cellX = Math.round((o.ofsX - 5) / 40);
        var cellY = Math.round((o.ofsY - 5) / 40);
        //后台要用的数据串
        str += submitId + "^^" + cellX + "^"+cellY + "^" + o.celX + "^" + o.celY + "^^^^0;";
    }
    str = str.substring(0, str.lastIndexOf(";"));
    //把数据串添加到表单元素里
    jQuery("#containerInfo").val(str);
    //表单提交
    if(pageType == "child"){
        saveChildLayout();
    }else{
        document.forms["saveForm"].submit();
    }
}

// 发布
function publishTitle() {
	var terminalType = jQuery("#terminalType").val();
	var resolution = jQuery("#resolution").val();
	var url = portalMSHost + "/titleManage/publishTitle.do?titleId="+submitId+"&terminalType="+terminalType+"&resolution="+resolution;
	$.ajax({
		type : "POST",
		url : url,
        success : function(_data) {
            if(_data == "success"){
                alert("操作成功");
            }else if(_data.indexOf("<script") >= 0 || _data.indexOf("<html") >= 0){
                document.write(_data);
            }
        },
        error : function(_data) {
            alert("操作失败");
        }
	});
}

// 保存并发布
function saveLayoutAndPublish() {
	jQuery("#action").val('saveAndPublish');
	saveLayout();
}

//返回
		function goToBack(){
			var rs = new RegExp("(^|)" + "areaId" + "=([^\&]*)(\&|$)", "g").exec(location.href);
			var tmp = rs[2];
			var backURL = "http://"+ window.location.host + "/portalMS/titleManage/queryAreaList.action?areaId="+tmp;
			window.location.href=backURL;
		}

		//预发布 按钮
		function preSyncPlan(){
			if(isEdit){
				alert("布局或模块属性有修改，请先保存再预发布！")
				return false;
			}else{
				yfbSubmit();
			}
			//重置是否修改
			isEdit = false;

	}
	
	//预发布提交
	function yfbSubmit(){
		if(!confirm("您确认要预发布当前布局吗？"))
		{
			return;
		}
		// 遮盖背景，禁止继续操作 begin
	    $("body").append("<div id='dialogbg'></div>");
	    var webH = document.body.scrollHeight;
	    var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	    var getH = (webH > webvH)?webH:webvH;
	    $("#dialogbg").height(getH + "px");
	    // 遮盖背景，禁止继续操作end	
		var planId = jQuery("#planId").val();
		var dataStr = encodeURI("ids="+planId);
		var url=portalMSHost +"/titleManage/preSyncPlan.action";
		$.ajax({
			type: "POST",
			url: url,
			data: dataStr,
			success: function(data){	
				if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                    document.write(data);
                }else{
					var res = eval("("+data+")");
			  		var msg = res.result;
			  		var confirmDailog = jQuery.extend(Dailog,{
					    onOK:function(){			
			    			reset();
					    },
					  	 onClose:function(){
					    	reset();
					 }			
			   		});
					
			  		if(msg=="success"){											
						//window.frames.item('other').location.reload()
			  			confirmDailog.openWindow('操作提示',400,200,'操作成功,请继续',0,1,0);					
			  		}else if(msg=="unready"){
			  			var tips = res.tips;
			  			confirmDailog.openWindow('操作提示',400,200,tips,0,0,0);	
				    }else if(msg=="error"){ 
				    	confirmDailog.openWindow('操作提示',400,200,'数据库操作异常',0,0,0);
				    }else{ 	
				    	confirmDailog.openWindow('操作提示',400,200,'未知异常,请刷新页面',0,0,0);
				    }
					
					$("#dialog").css("z-index","11000");
                }
			}
		});
	
	
	}
	//重置页面
	function reset(){	
		window.location.href =window.location.href;			
	}
	//提交发布
	function commitPlan(){
		if(isEdit){
				alert("布局或模块属性有修改，请先保存！")
				return false;
		}
		var planId = jQuery("#planId").val();
		var status = jQuery("#planStatus").val();
		
		if(status!=2){
			showText('<s:text name="page.message.tip"/>','请选择预发布状态的布局进行提交！',400,200,0,true);
			$("#dialog").css("z-index","11000");
			return false;
		}
		var url =portalMSHost +"/titleManage/plan_edit_comment.jsp?fromPage=titlelayout&planId="+planId;	
		showPage('提交发布',url,700,280);
		
	}
	//发布
	function syncPlan(){
		if(isEdit){
				alert("布局或模块属性有修改，请先保存！")
				return false;
		}
		if(!confirm("您确认要发布选择的布局吗？")){
				return;
		}
		// 遮盖背景，禁止继续操作 begin
	    $("body").append("<div id='dialogbg'></div>");
	    var webH = document.body.scrollHeight;
	    var webvH =document.documentElement.clientHeight + document.documentElement.scrollTop ;
	    var getH = (webH > webvH)?webH:webvH;
	    $("#dialogbg").height(getH + "px");
	    // 遮盖背景，禁止继续操作end
		var planId = jQuery("#planId").val();
		var dataStr = encodeURI("ids="+planId);
		var url=portalMSHost +"/titleManage/syncPlan.action";
		$.ajax({
			type: "POST",
			url: url,
			data: dataStr,
			success: function(data){
				if(data.indexOf("<html") >= 0 || data.indexOf("<script") >= 0){
                    document.write(data);
                }else{
					var res = eval("("+data+")");
			  		var msg = res.result;
			  		var confirmDailog = jQuery.extend(Dailog,{
					    onOK:function(){			
			    			reset();
					    },
					  	 onClose:function(){
					    	reset();
					 }			
			   		});
			  		if(msg=="success"){
			  			confirmDailog.openWindow('操作提示',400,200,'操作成功,请继续',0,1,0);	
			  		}else if(msg=="unready"){
			  			var tips = res.tips;
			  			confirmDailog.openWindow('操作提示',400,200,tips,0,0,0);	
				    }else if(msg=="error"){ 
				    	confirmDailog.openWindow('操作提示',400,200,'数据库操作异常',0,0,0);
				    }else{ 	
				    	confirmDailog.openWindow('操作提示',400,200,'未知异常,请刷新页面',0,0,0);
				    }
                }
			}
		});
  }
  
  //onReady 监听切换 select 
  $(document).ready(function(){ 		
		var areaId = jQuery("#areaId").val();
		var planId = jQuery("#planId").val();
		var roleId = jQuery("#roleId").val();
		var status = jQuery("#planStatus").val();
		var titleId = jQuery("#titleId").val();
		var url=portalMSHost +"/titleManage/queryChangeLayout.do?areaId="+areaId+"&planId="+planId;
			$.ajax({
				type: "POST",
				url: url,
				async: false,
				success: function(data){
					if(data){
						for(var i=0; i<data.length ; i++){							
							$("#sel_change").append("<option value="+ data[i].id +">"+ data[i].roleName+"</option>");
						}	
						$("#sel_change  option[value="+ roleId +"] ").attr("selected",true)		
					}					
				}
			});

		//设置 切换select onChange
		$('#sel_change').change(function(){						
			var id = $(this).children('option:selected').val()
			$("#roleId").val(id);
			$("#titleId").val('');
			$("#saveForm").attr("action","manageTitleLayout.do")			
			$("#saveForm").submit();
			
		});
 		
 		if(titleId=="" || lay.length==0){
			$("#saveLayout").hide();
			$("#qk").hide();
			$("#prePublishPlan").hide();
			$("#commitPlan").hide();
			$("#publishPlan").hide();
			
		}else{
			if(status==1){
				//$("#prePublishPlan").attr("disabled", true);
				$("#prePublishPlan").hide();
			}
			if(status!=3){
				$("#publishPlan").hide();
			}
			if(status!=2){
				$("#commitPlan").hide();
			}
		}
		
	}) 			
// 重置
function resetLayout() {
	if(confirm("确定要重新布局吗？")) {
		jQuery(".layout div.m").each(function() {
			dragId = this.id;
			content_obj.ondrop();
		});
		jQuery(".layout>div.wrap").each(function() {
			jQuery(this).remove();
		});
	}
}

//生成布局格子opt数据,target格子生成所在容器id
function buildMContent(opt, target) {
	var html = "";
	var x = opt.x;
	var y = opt.y;
	for(var i = 0; i < x; i++) {
		html += "<li>";
		for(var j = 0; j < y; j++) {
			html += "<div fill='true'></div>";
		}
		html += "</li>";
	}
	var num = jQuery("#" + target + ">li").length;
	jQuery("#" + target).append(html).width((num + x) * 40).height(40 * y + 5);
}

//生成资源模板
function setM(data) {
	if(!data)
		return;
	var datas = data;
	for(var i = 0; i < datas.length; i++) {
		var d = datas[i];
		//没有海报/宽高为0的模块直接不显示
		if(d.localPath == "" && d.width == 0 && d.height == 0) {
			continue;
		}
		var celw = Math.round((d.width + 10 * pNum) / (75 * pNum));
		var celh = Math.round((d.height + 10 * pNum) / (75 * pNum));
        if(celw < 1){
            celw = 1;
        }
        if(celh < 1){
            celh = 1;
        }
		if(celh > 6) {
			celw = Math.round(celw * 7 / celh);
			celh = 7;
		}
		setImg(d.categoryResourceId, celw, celh, i, d.posterServerHttp, d.localPath, d.posterList, d.resourceName, d.description, d.resourceType);
	}
	//绑定拖拖拽事件
	jQuery(".m").each(function(i) {
		this.addEventListener("dragstart", drag, false);
		this.addEventListener("dragend", dragend, false);
	});
	setTimeout(function() {
		loadImg();
	}, 100);
}

function setSize(_p){
    var n;
    switch (_p){
        case "0": // 720P;
            n = 1;
            break;
        case "1": // 1080P;
            n = 1.5;
            break;
        case "2": // 4K;
            n = 3;
            break;
        case "3": // 8K;
            n = 6;
            break;
        default :
            n = 1;
            break;
    }
    return n;
}

//资源布局算法
function setImg(id, celX, celY, m, host, local, posterList, resourceName, description, _resourceType) {
	//取出所有空置的单元格
	//if(celY>6)
	//return;
	var mDiv = jQuery("#content div[fill='true']");
	var length = mDiv.length;
	var totalLi = jQuery("#content>li").length;
	for(var i = 0; i < length; i++) {
		var m = mDiv[i];
		var thisObj = jQuery(m);
		var index = thisObj.index();
		var thisParent = thisObj.parent();
		var liIndex = thisParent.index();
		if(index == -1 && liIndex == -1) {
			return;
		}
		//位置不够
		if(totalLi - liIndex < celX || i == (length - 1)) {
			addCell();
			mDiv = jQuery("#content div[fill='true']");
			length = mDiv.length;
			totalLi = jQuery("#content>li").length;
		}
		//列格子的位置不够
		if(content.y - index < celY) {
			continue;
		}
		//如果起点格子没有被占用，check此格子能否放下此模块
		if(check_xy(liIndex, index, celX, celY)) {
			//生成html串
			var html=module.replace("{id}",id)
						   .replace("{left}",40*liIndex+5)
						   .replace("{top}",40*index+5)
						   .replace("{width}",40*celX-10)
						   .replace("{height}",40*celY-10)
						   .replace("{bgcolor}",cellDefaultColor)
						   .replace("{title}",resourceName)
						   .replace("{description}",description)
						   .replace("{url}",host+local);
			//给html串添加的数据
			var moveObj = {
				id : id,
				myId : "",
				newMove : "0", //是否移动过
				x : liIndex,
				y : index,
				celX : celX,
				celY : celY,
				ofsX : 40 * liIndex + 5,
				ofsY : 40 * index + 5,
				overlay : true,
				overMove : false,
				localPath : local,
				posterServerHttp : host,
				container : "content",
				cellState : '24',
				cellColor : "",
				cellAlpha : "100",
				posterList : posterList,
				//isAuth : 0,
				minSoftVer : "",
                productCode : "",
                margin : "",
                resourceType : _resourceType
			};
			//添加到容器里
			jQuery(".all_content").append(jQuery(html).data("data", moveObj));
			return;
		}
	}
}

//check格子能否放下模块
function check_xy(x, y, celX, celY) {
	var a = [];
	for(var i = 0; i < celX; i++) {//i为列
		for(var j = 0; j < celY; j++) {//j为行
			if(!checkTheCell(x + i, y + j)) {
				return false;
			}
			var o = {
				"x" : x + i,
				"y" : y + j
			};
			a.push(o);
		}
	}
	setEmpty(a);
	return true;
}

//置空
function setEmpty(a) {
	if(!a.length)
		return;
	var l = a.length;
	for(var i = 0; i < l; i++) {
		jQuery("#content>li:eq(" + a[i].x + ")>div:eq(" + a[i].y + ")").removeAttr("fill");
	}
}

//页签焦点
function tagFocus(index) {
	jQuery("#tab_switchdraw_x>ul>li:eq(" + index + ")>a").attr("id", "this_datact").parent().siblings().find("a").removeAttr("id");
}

//资源页签焦点
function ModuleFocus(index) {
    var oldIndex = jQuery("#tab_lay_x ul li a[id='this_datact']").parent().index();
    if(oldIndex != -1){
        isFirstChange = true;
    }else{
        isFirstChange = false;
    }
	if(index == -1)
		jQuery("#tab_lay_x>ul>li:last-child>a").attr("id", "this_datact").parent().siblings().find("a").removeAttr("id");
	else
		jQuery("#tab_lay_x>ul>li:eq(" + index + ")>a").attr("id", "this_datact").parent().siblings().find("a").removeAttr("id");
}

//清空布局
function refreshAll(target) {
	jQuery(target + ">div").remove();
	jQuery(target + ">ul>li>div").attr("fill", "true").removeAttr("celX").removeAttr("celY").removeAttr("ownId");
}

//检测单元格是否被占用
function checkTheCell(x, y) {
	return jQuery("#content>li:eq(" + x + ")>div:eq(" + y + ")").attr("fill") ? true : false;
}

//块拖放到一个区域后，标记块对应下的单元格
function fillCell(x, y, ownId) {
	jQuery("#newLayout>li:eq(" + x + ")>div:eq(" + y + ")").removeAttr("fill").attr("ownId", ownId);
}

//块拖放到一个区域后，原点单元格添加对应块所占用单元格长度
function setCellArea(x, y, celX, celY) {
	jQuery("#newLayout>li:eq(" + x + ")>div:eq(" + y + ")").attr("celX", celX).attr("celY", celY);
}

//删除缩放格子
function deleteZoom(obj) {
	jQuery(".rRightDown,.rLeftDown,.rRightUp,.rLeftUp,.rRight,.rLeft,.rUp,.rDown", obj).remove();
}

//块拖放到一个区域后，原点单元格添加对应块所占用单元格长度
function setArea(x, y, w, h, id) {
	for(var i = 0; i < w; i++) {
		for(var j = 0; j < h; j++) {
			if(i == 0 && j == 0)
				setCellArea(i + x, j + y, w, h);
			fillCell(i + x, j + y, id);
		}
	}
}

//块拖走后清空原来块占用单元格的所有标记
function reArea(x, y, targetId) {
	var div = jQuery("#" + targetId + ">li:eq(" + x + ")>div:eq(" + y + ")");
	div.attr("fill", "true").removeAttr("celX").removeAttr("celY").removeAttr("ownId");
}

//块拖走后清空原来块占用单元格的所有标记
function refreshArea(x, y, spanX, spanY, container) {
	for(var i = 0; i < spanX; i++) {
		for(var j = 0; j < spanY; j++) {
			reArea(i + x, j + y, container);
		}
	}
}

//返回单元格占用div id
function getOwnId(x, y, targetId) {
	return jQuery("#" + targetId + ">li:eq(" + x + ")>div:eq(" + y + ")").attr("ownid");
}

//检测块拖拽到的区域能否容纳块
function checkArea(x, y, targetid, ownId) {
	var ownObj = jQuery("#" + ownId);
	var parentClass = ownObj.parent(".wrap");
	var data = ownObj.data("data");
	for(var i = 0; i < data.celX; i++) {
		for(var j = 0; j < data.celY; j++) {
			var obj = jQuery("#" + targetid + ">li:eq(" + (x + i) + ")>div:eq(" + (y + j) + ")");
			var _celX = obj.attr("celX");
			var _celY = obj.attr("celY");
			if(obj.attr("ownId") != ownId && _celX == data.celX && _celY == data.celY && i == 0 && j == 0) {
				//能容纳但不覆盖格子，也就是要叠加
				console.log("T_T");
				data.overlay = false;
				return true;
			} else if(!(obj.attr("ownId") == ownId || !!obj.attr("fill"))) {
				data.overlay = true;
				return false;
			} else if(parentClass.length > 0 && obj.attr("ownId") == ownId && !obj.attr("fill")) {
				data.overlay = true;
				return false;
			}
		}
	}
	data.overlay = true;
	//能容纳且覆盖格子
	return true;
}

//拖拽开始
var beforeMoveOwnId;
function drag(e) {
	//e.dataTransfer.setData("Text",this.id);
	//服务器上/e.dataTransfer.setData存储不了数据，不知道为什么，所以用全部变量代替
	//当前拖拽的模块id
	dragId = this.id;
	var data = jQuery(this).data("data");
	//*1  字符转数字
	//layerX,layerY模块在页面的偏移量
	data.x = e.layerX * 1;
	data.y = e.layerY * 1;
	//x列 y行
	var x = Math.round(data.ofsX / 40);
	var y = Math.round((data.ofsY - 5) / 40);
	//container 模块当前所在窗口的id newLayout:动态布局区域 content:资源区域
	if(data.container == "newLayout") {
		//如果是newLayout,移动的时候要判断叠加的情况
		//取出模块占用的格子，查看格子所属id(ownId)是否和自己的id匹配
		var obj = jQuery("#newLayout>li:eq(" + x + ")>div:eq(" + y + ")");
		var id = obj ? obj.attr("ownId") : false;
		beforeMoveOwnId = id;
		//如果不匹配，移动后，原来占用的格子不用重置;反则重置
		if(id && this.id != id) {
			data.overMove = true;
		} else {
			data.overMove = false;
		}
	}
}

//拖拽结束
function dragend(e) {
	//console.log(e.dataTransfer.getData("Text"));
}

//拖到目标上时判断是否能落在目标区域上
var layout_obj = document.getElementsByClassName("layout")[0];
var layout_j = jQuery(layout_obj).offset();
layout_obj.ondragover = function(e) {
	//var id=e.dataTransfer.getData("Text");
	var id = dragId;
	var obj = jQuery("#" + id);
	var data = obj.data("data");
	var _x = e.pageX - layout_j.left - data.x + this.scrollLeft;
	var _y = e.pageY - layout_j.top - data.y - 5 + this.scrollTop;
	var x = Math.round(_x / 40);
	var y = Math.round(_y / 40);
	if(_x >= -5 && _y >= -5 && checkArea(x, y, 'newLayout', id)) {
		e.preventDefault();
		return true;
	}
};
//拖拽结束后目标落入布局区域
layout_obj.ondrop = function(e) {
	//var id=e.dataTransfer.getData("Text");
	//服务器上/e.dataTransfer.setData存储不了数据，不知道为什么，所以用全部变量代替
	//当前拖拽的模块id
	var id = dragId;
	var obj = jQuery("#" + id);
    //防止元素在布局区域拖动时多次绑定双击事件
    obj.unbind("dblclick");
	//拖进布局区域后添加双击事件
	obj.dblclick(function() {
		var data = jQuery(this).data("data");
		showModuleOpt(data);
	});
	//取出模板里存储的数据
	var data = obj.data("data");
	//console.log(data.overlay);
	data.newMove = "1";
	isEdit = true;
	//添加进布局区
	jQuery(this).append(obj);
	//重置单元区域
	if(data.container == 'newLayout' && !data.overMove) {
		var parent = jQuery("div[ownid='" + id + "'][class='wrap']");
		//判断是否是叠加
		//如果是叠加parent.length>0
		if(parent[0] && parent.length > 0) {
			//添加的话取出第一个包裹节点，把格子给第一个模块占用
			var nextDiv = parent.find("div").eq(0);
			//取出数据
			var n = nextDivData = nextDiv.data("data");
			//计算出liIndex 列;divIndex行
			var liIndex = Math.round(n.ofsX / 40);
			var divIndex = Math.round((n.ofsY - 5) / 40);
			//标记区域为此模块占用
			setArea(liIndex, divIndex, n.celX, n.celY, n.id);
			parent.attr("ownid", n.id);
			//重置文件夹
			setpage(parent);
		} else {
			//liIndex例 divIndex行
			var liIndex = Math.round(data.ofsX / 40);
			var divIndex = Math.round((data.ofsY - 5) / 40);
			//重置占用格子
			refreshArea(liIndex, divIndex, data.celX, data.celY, 'newLayout');
		}
	} else if(data.container == 'newLayout' && data.overMove) {
		//如果拖拽的不是文件夹里点用格子的模块，直接重置文件夹，不用重置格子
		setpage(jQuery(".layout>div[ownid='" + beforeMoveOwnId + "'][class='wrap']"));
	} else if(data.container == 'content') {
		//如果拖拽的不是文件夹，直接重置格式
		var liIndex = Math.round(data.ofsX / 40);
		var divIndex = Math.round((data.ofsY - 5) / 40);
		//重置占用格子
		refreshArea(liIndex, divIndex, data.celX, data.celY, 'content');
	}
	//修改模块容器
	data.container = 'newLayout';
	//计算模块模块所以容器偏移量
	var x = e.pageX - layout_j.left - data.x + this.scrollLeft;
	var y = e.pageY - layout_j.top - data.y + this.scrollTop;
	//通过偏移量计算出周边格子(让模块自动落入格子上，有一种自动吸附的效果)
	x = x < 0 ? 0 : x;
	y = y < 0 ? 0 : y;
	x = x > (layout.x - data.celX) * 40 ? (layout.x - data.celX) * 40 : x;
	y = y > (layout.y - data.celY) * 40 + 5 ? (layout.y - data.celY) * 40 + 5 : y;
	x = Math.round(x / 40) * 40 + 5;
	y = Math.round((y - 5) / 40) * 40 + 5;
	obj.css({
		"left" : x + "px",
		"top" : y + "px"
	});
	data.ofsX = x;
	data.ofsY = y;
	var liIndex = Math.round(x / 40);
	var divIndex = Math.round((y - 5) / 40);
	if(data.overlay) {
		//落入的格子没有叠加，直接标记格子，占为自有
		setArea(liIndex, divIndex, data.celX, data.celY, data.id);
		var tag = jQuery(".rRightDown", obj);
		if(tag.length == 0) {
			obj.append(moveModule);
			tag = jQuery(".rRightDown", obj);
			setZoomEvent(tag[0]);
		}
	} else {
		//落入格子已被占用

		//ownid找出格子目前被谁占用
		var ownid = getOwnId(Math.round((x - 5) / 40), Math.round((y - 5) / 40), 'newLayout');
		//找出格子占用对象
		var ownObj = jQuery("#" + ownid);
		//判断是否已有文件夹
		if(ownObj.parent().attr("ownid") == ownid) {
			//如果已存在文件夹，直接添加
			//添加到文件夹，且修正样式
			deleteZoom(obj);
			ownObj.parent().append(obj.css({
				"top" : "-1px",
				"left" : "-1px"
			}));
			//把原来的模块删除
			jQuery(".layout>#" + id).remove();
			//重置文件夹
			setpage(ownObj.parent());
		} else {
			//如果还没有文件夹则添加文件夹
			var html = "<div class='wrap' ownid='" + ownid + "'></div>";
			var wrapObj = jQuery(html).css({
				"top" : ownObj.css("top"),
				"left" : ownObj.css("left"),
				"width" : ownObj.width(),
				"height" : ownObj.height()
			});
			jQuery(this).append(wrapObj);
			wrapObj.append(ownObj.css({
				"top" : "-1px",
				"left" : "-1px"
			}));
			wrapObj.append(obj.css({
				"top" : "-1px",
				"left" : "-1px"
			}));
			deleteZoom(obj);
			deleteZoom(ownObj);
			//jQuery(".layout>#"+ownid).remove();
			//jQuery(".layout>#"+id).remove();
			setpage(wrapObj);
		}
	}
	data.overlay = true;
};
//重置文件夹
function setpage(obj) {
	//把pagelist删除掉，也就是显示12345那个列表，后面会重新生成
	jQuery(".pagelist", obj).remove();
	//把子div全部查找出来(每一个div都是一个模块)
	var divs = obj.find(">div");
	var ownid = obj.attr("ownid");
	var html = "";
	//divs.length==0刚把文件夹去除(只行剩下一个模块,没必要用文件夹了)
	if(divs.length > 1) {
		//添加显示的页数和事件
		jQuery(divs).each(function(i) {
            html += "<li onclick='showpages(" + i + "," + ownid + ")'>" + (divs.length - i) + "</li>";
		});
		html = "<ul class='pagelist' >" + html + "</ul>";
		obj.append(html);
		showpages(divs.length - 1, ownid);
	} else if(divs.length == 1) {
		//只剩下一个模块了，把文件夹去掉只剩下模块
		var own = jQuery("#" + ownid);
		own.unwrap();
		own.append(moveModule);
		tag = jQuery(".rRightDown", own);
		setZoomEvent(tag[0]);
		var data = own.data("data");
		//重置样式
		own.css({
			"top" : data.ofsY + "px",
			"left" : data.ofsX + "px"
		});
	}
}

//点击文件夹上的列表数字，显示对应的模块
function showpages(index, ownid) {
	jQuery(".layout>div[ownid='" + ownid + "']>ul>li:eq(" + index + ")").addClass("foc").siblings().removeClass("foc");
	jQuery(".layout>div[ownid='" + ownid + "']").find(">div:eq(" + index + ")").css("z-index", 100).siblings("div").css("z-index", 1);
}

//拖到目标上时判断是否能落在目标区域上
var content_obj = document.getElementsByClassName("all_content")[0];
content_obj.ondragover = function(e) {
	//var id=e.dataTransfer.getData("Text");
	var id = dragId;
	var obj = jQuery("#" + id);
	var data = obj.data("data");
	if(data.container == "newLayout") {
		e.preventDefault();
		return true;
	}
};
//这里是从布局区域拖拽到资源区域，也就是删除操作，思路和添加点不多，不过逻辑没那么多
content_obj.ondrop = function(e) {
	//var id=e.dataTransfer.getData("Text");
	var id = dragId;
	var obj = jQuery("#" + id);
	var moveObjData = findTheRemoveObj(id);
	//把删除的模块数据添加到资源数据中
	if(!!moveObjData && jQuery.inArray(moveObjData, sou) == -1) {
		sou.push(moveObjData);
	}
	//资源模块数据没有双击事件
	obj.unbind("dblclick");
	var data = obj.data("data");
	var container_bak = data.container;
	var liIndex = Math.round(data.ofsX / 40);
	var divIndex = Math.round((data.ofsY - 5) / 40);
	jQuery(this).append(obj);
	deleteZoom(obj);
	//删除时也要重置文件夹
	if(beforeMoveOwnId && data.container == 'newLayout' && data.overMove) {
		setpage(jQuery(".layout>div[ownid='" + beforeMoveOwnId + "']"));
	} else if(beforeMoveOwnId && data.container == 'newLayout' && !data.overMove) {
		var parent = jQuery("div[ownid='" + id + "'][class='wrap']");
		if(parent.length > 0) {
			var nextDiv = parent.find("div").eq(0);
			var n = nextDivData = nextDiv.data("data");
			var liIndex = Math.round(n.ofsX / 40);
			var divIndex = Math.round((n.ofsY - 5) / 40);
			setArea(liIndex, divIndex, n.celX, n.celY, n.id);
			parent.attr("ownid", n.id);
			setpage(parent);
		} else {
			refreshArea(liIndex, divIndex, data.celX, data.celY, 'newLayout');
		}
	} else {
		refreshArea(liIndex, divIndex, data.celX, data.celY, 'newLayout');
	}
	var firstEmtypDiv = jQuery("#content div[fill='true']");
	var totalLi = jQuery("#content>li").length;
	for(var i = 0; i < firstEmtypDiv.length; i++) {
		var index = jQuery(firstEmtypDiv[i]).index();
		var liIndex = jQuery(firstEmtypDiv[i]).parent().index();
		if(totalLi - liIndex < data.celX || i == (firstEmtypDiv.length - 1)) {
			addCell();
			firstEmtypDiv = jQuery("#content div[fill='true']");
			totalLi = jQuery("#content>li").length;
		}
		if(index == -1 && liIndex == -1) {
			return;
		}
		if(!checkTheCell(liIndex, index))
			continue;
		if(check_xy(liIndex, index, data.celX, data.celY)) {
			obj.css({
				"top" : (40 * index + 5) + 'px',
				"left" : (40 * liIndex + 5) + 'px'
			});
			//x,y:鼠标对拖动目标的偏移量;celX,celY:模块长宽占用单位;ofsX,ofsY:模块所在位置偏移量;overlay:是否重叠;overMove:覆盖中移除;container:所在容器
			var moveObj = {
				id : data.id,
				myId : data.myId,
				newMove : "1", //是否移动过
				x : liIndex,
				y : index,
				celX : data.celX,
				celY : data.celY,
				ofsX : 40 * liIndex + 5,
				ofsY : 40 * index + 5,
				overlay : true,
				overMove : false,
				localPath : data.localPath,
				posterServerHttp : data.posterServerHttp,
				container : "content",
				cellState : data.cellState,
				cellColor : data.cellColor,
				cellAlpha : data.cellAlpha,
				posterList : data.posterList,
				//isAuth : data.isAuth,
				minSoftVer : data.minSoftVer,
                productCode : data.productCode,
                margin : data.margin,
                resourceType : data.resourceType
			};
			isEdit = true;
			jQuery(".all_content").append(obj.data("data", moveObj));
			return;
		}
	}
};
//属性框
function showModuleOpt(data) {
    if(data.resourceType == "17"){
        jQuery("#childLayout").show();
    }else{
        jQuery("#childLayout").hide();
    }
	actionOpt = data;
	jQuery("#colorValue").val(data.cellColor);
	jQuery("#color_check").val(data.cellColor);
	jQuery("#color_op").val(data.cellAlpha);
	if(!!data.cellColor) {
		jQuery("#colorType").removeAttr("disabled")[0].checked = false;
	} else {
		jQuery("#colorType").attr("disable","disable")[0].checked = true;
	}
	jQuery("#defaultColor").val(cellDefaultColor);
    getNum(parseInt(data.cellState != "" ? data.cellState : 0));
	//jQuery("input[name='isAuth'][value='"+data.isAuth+"']")[0].checked = true;

	jQuery("#minSoftVer_op").val(data.minSoftVer == null ? "" : data.minSoftVer);
    jQuery("#productCode").val(data.productCode == null ? "" : data.productCode);
    jQuery("#offsetPos").val(data.margin == null ? "" : data.margin);
	var pictures = data.posterList;
	jQuery("#imageView").empty();

	var img_url = portalMSHost + "/titleManage/findPosterList.action?categoryResourceId=" + data.id;
	jQuery.get(img_url, {}, function(imgs) {
		for(var i = 0; i < imgs.length; i++) {
			var p = imgs[i];
			var url = p.posterServerHttp + p.localPath;
			var _w = Math.round((p.width * 1 + 10 * pNum) / (75 * pNum));
			var _h = Math.round((p.height * 1 + 10 * pNum) / (75 * pNum));

			var w = _w * 40 + 5;
			var h = _h * 40 + 5;
			var bgColor = data.cellColor || cellDefaultColor;
			bgColor = getRgbaColor(bgColor, data.cellAlpha);
			var spanHtml = "<span style='text-align:center;display:block;width:" + w + "px'>" + p.strTerminalType + "-" + p.strResolution + "-" + p.width + "*" + p.height + "</span>";
			jQuery("#imageView").append("<div><div style='background-color:" + bgColor + "'><img onclick='openUrl(\"" + url + "\")' width='" + w + "px' height='" + h + "px' src='" + url + "'></img></div>" + spanHtml + "</div>");
		}
	});
	jQuery("#bg").show();
}

function getNum(_num){
    jQuery("input[name='scale'][value='"+ (_num & 1) +"']")[0].checked = true;
    jQuery("input[name='drage'][value='"+ (_num & 2)/2 +"']")[0].checked = true;
    jQuery("input[name='delete'][value='"+ (_num & 4)/4 +"']")[0].checked = true;
    jQuery("input[name='focus'][value='"+ (_num & 8)/8 +"']")[0].checked = true;
    jQuery("input[name='click'][value='"+ (_num & 16)/16 +"']")[0].checked = true;
    jQuery("input[name='showname'][value='"+ (_num & 32)/32 +"']")[0].checked = true;
    jQuery("input[name='isTop'][value='"+ (_num & 64)/64 +"']")[0].checked = true;
    jQuery("input[name='cellTitle'][value='"+ (_num & 128)/128 +"']")[0].checked = true;
    jQuery("input[name='isImok'][value='"+ (_num & 256)/256 +"']")[0].checked = true;
    jQuery("input[name='isConDIY'][value='"+ (_num & 512)/512 +"']")[0].checked = true;
    jQuery("input[name='isAuth'][value='"+ (_num & 1024)/1024 +"']")[0].checked = true;
    //console.log(_num,(_num & 1),(_num & 2)/2,(_num & 4)/4,(_num & 8)/8,(_num & 16)/16,(_num & 32)/32,(_num & 64)/64,(_num & 128)/128,(_num & 256)/256);
}

//打开预览图片
function openUrl(url) {
	window.open(url);
}

//保存模块参数
function saveModuleOpt() {
	if(!submitCheck()) {
		return void (0);
	}
	actionOpt.cellColor = jQuery("#colorValue").val();
	var color = actionOpt.cellColor || cellDefaultColor;
	color = getRgbaColor(color, jQuery("#color_op").val());
	jQuery("#" + actionOpt.id).css("background-color", color);
	actionOpt.cellAlpha = jQuery("#color_op").val();
	var scale = jQuery("input[name='scale']:checked").val();
	var drage = jQuery("input[name='drage']:checked").val();
	var del = jQuery("input[name='delete']:checked").val();
	var focus = jQuery("input[name='focus']:checked").val();
	var click = jQuery("input[name='click']:checked").val();
	var showname = jQuery("input[name='showname']:checked").val();
    var isTop = jQuery("input[name='isTop']:checked").val();
    var cellTitle = jQuery("input[name='cellTitle']:checked").val();
    var isImok = jQuery("input[name='isImok']:checked").val();
    var isConDIY = jQuery("input[name='isConDIY']:checked").val();
    var isAuth = jQuery("input[name='isAuth']:checked").val();
    var num=""+isAuth+isConDIY+isImok+cellTitle+isTop+showname+click+focus+del+drage+scale;
	actionOpt.cellState = parseInt(num, 2).toString(10);
	actionOpt.newMove = "1";
	//actionOpt.isAuth = jQuery("input[name='isAuth']:checked").val();
	actionOpt.minSoftVer = jQuery("#minSoftVer_op").val();
    actionOpt.productCode = jQuery("#productCode").val();
    actionOpt.margin = jQuery("#offsetPos").val();
	isEdit = true;
	hideDialog();
}

//返回rgba色值
function getRgbaColor(color, opacity) {
	if(color.length < 7)
		return;
	var one = transTheNum(color.substring(1, 3), 16, 10);
	var two = transTheNum(color.substring(3, 5), 16, 10);
	var three = transTheNum(color.substring(5, 7), 16, 10);
	var opa = opacity * 1 / 100;
	return "rgba(" + one + "," + two + "," + three + "," + opa + ")";
}

//进制转换 num要转换的值 oldType值原类型 newType要转成的类型
function transTheNum(num, oldType, newType) {
	return parseInt(num, oldType).toString(newType);
}

function hideDialog() {
	jQuery("#bg").hide();
}

//重新生成格子
function reSetCell() {
	jQuery("#content").empty();
	jQuery("#content").siblings().remove();
	jQuery(".all_content").scrollLeft(0);
	buildMContent(content, "content");
}

//添加格子
function addCell() {
	var opt = {
		x : 15,
		y : 8
	};
	buildMContent(opt, "content");
}

//加载下一页数据
function loadNextPage() {
	if(array_tag.length) {
		var arr = array_tag.splice(0, amountOfPage);
		setM(arr);
		array_tag.length ? addColor() : removeColor();
	}
}

function removeColor() {
	jQuery(".getmore input").css("color", "#928E8E");
}

function addColor() {
	jQuery(".getmore input").css("color", "#006ec1");
}

//全部加载
function loadAll() {
	if(array_tag.length) {
		var arr = array_tag.splice(0, array_tag.length);
		setM(arr);
		array_tag.length ? addColor() : removeColor();
	}
}

//资源过滤
function filterModule(index, type) {
	//var a=new Date();
	reSetCell();
	ModuleFocus(index);
	var arr = [];
	for(var i = 0; i < sou.length; i++) {
		var id = sou[i].categoryResourceId;
		var o = jQuery(".layout #" + id);
		if(o.length == 0) {
			if(!type) {
				arr.push(sou[i]);
			} else if(sou[i].resourceType == type) {
				arr.push(sou[i]);
			}
		}
	}
	array_tag = arr;
	loadNextPage();
}

//颜色选择
jQuery("#color_check").bind("change", function() {
	jQuery("#colorValue").val(this.value);
	jQuery("#colorType").removeAttr("disabled")[0].checked = false;
	var color = this.value;
	color = getRgbaColor(color, jQuery("#color_op").val());
	jQuery("#imageView>div>div").css("background-color", color);
});
//填写颜色
jQuery("#colorValue").bind("blur", function() {
	var exg = /\#[a-f0-9]{6}/;
	if(exg.test(this.value)) {
		jQuery("#color_check").val(this.value);
		jQuery("#colorType").removeAttr("disabled")[0].checked = false;
		jQuery("#imageView>div>div").css("background-color", jQuery("#color_check").val());
	} else {
		this.value = "";
		//alert("对不起，输入的颜色值格式不对！");
	}
});
//修改透明度
jQuery("#color_op").bind("change", changeOpa);
jQuery("#color_op").bind("input", changeOpa);
function changeOpa() {
	var v = this.value * 1;
	if(v > 100)
		this.value = 100;
	else if(v < 0)
		this.value = 0;
	var color = jQuery("#colorValue").val() || cellDefaultColor;
	var rbga = getRgbaColor(color, this.value);
	jQuery("#imageView>div>div").css("background-color", rbga);
}

//默认颜色选项
jQuery("#colorType").bind("change", function() {
	if(this.checked) {
		jQuery("#colorValue").val("");
		var color = getRgbaColor(cellDefaultColor, jQuery("#color_op").val());
		jQuery("#imageView>div>div").css("background-color", color);
	} else {
		jQuery("#colorValue").val(jQuery("#color_check").val());
		var color = getRgbaColor(jQuery("#color_check").val(), jQuery("#color_op").val());
		jQuery("#imageView>div>div").css("background-color", color);
	}
});
//查找删除的模块
function findTheRemoveObj(id) {
    if(pageType == "child"){
        //子布局区域没有标签，默认第一条数据。
        var tagIndex = 0;
    }else{
        var tagIndex = jQuery("#this_datact").parent().index();
    }
	var data = lay[tagIndex].titleLayoutList;
	for(var i = 0; i < data.length; i++) {
		if(data[i].categoryResourceId == id) {
			data[i].width = data[i].spanX * (75 * pNum) - 10 * pNum;
			data[i].height = data[i].spanY * (75 * pNum) - 10 * pNum;
			return data[i];
		}
	}
}

//搜索
function search() {
	var name = jQuery("#searchName").val();
	if(!name)
		return;
	jQuery("#tab_lay_x ul li a").removeAttr("id");
	reSetCell();
	var arr = [];
	for(var i = 0, length = sou.length; i < length; i++) {
		if(sou[i].resourceName.indexOf(name) >= 0) {
			arr.push(sou[i]);
		}
	}
	//console.log(arr.length);
	array_tag = arr;
	loadNextPage();
}

//回车搜索
function enterSearch() {
	if(event.keyCode == 13) {
		search();
	}
}

//延时加载图片
function loadImg() {
	jQuery("div[murl]").each(function() {
		var _t = this;
		var img = new Image();
		img.src = jQuery(_t).attr("murl");
		jQuery(img).load(function() {
			jQuery(_t).css({
				"background" : "url(" + this.src + ")",
				"-webkit-background-size" : "100%"
			}).removeAttr("murl");
		});
		img.onerror = function() {
			jQuery(_t).removeAttr("murl");
		};
	});
	jQuery("img").each(function(i) {
		this.addEventListener("dragstart", function() {
			e.preventDefault();
		}, false);
	});
}
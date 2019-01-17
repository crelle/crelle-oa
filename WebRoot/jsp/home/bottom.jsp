<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Bottom</title>
    <%@ include file="/jsp/public/header.jspf" %>
	<link type="text/css" rel="stylesheet" href="style/blue/statusbar.css" />
</head>
<body style="margin:0"> 

<div id="StatusBar">
    <div id="Online">
    	在线人员：共 <span class="OnlineUser" id="onlineUserNum"></span> 人
        <span class="OnlineView"><a href="javascript:void(0)">[查看在线名单]</a></span>
    </div>
    <div id="Info">
    	<a href="http://user.qzone.qq.com/383362161/infocenter?ptsig=shaI7eHfUzYRD-wp2Ifj1Vigvtbv4aUwrSk02jz09V0_" title = "传智播客首页" target="_blank">伊莱吻的空间</a> |
        <a href="http://www.baidu.com" title = "传智播客BBS" target="_blank">百度</a> 
    </div>
    <div id="DesktopText">
        <a href="javascript:void(0)"><img border="0" src="style/images/top/text.gif"/>便笺</a>
        <span id=TryoutInfo></span>
        <span id="Version">
            <a href="javascript:void(0)">
            	<img border="0" width="11" height="11" src="style/images/top/help.gif" /> 
                <img border="0" width="40" height="11" src="style/blue/images/top/version.gif" />
            </a>
        </span>
    </div>
</div>

</body>
</html>
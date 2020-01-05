(function($){
    function ajaxFunction(){
        var xmlHttp;
        try { // Firefox, Opera 8.0+, Safari
            xmlHttp = new XMLHttpRequest();
        } 
        catch (e) {
            try {// Internet Explorer
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } 
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } 
                catch (e) {
                }
            }
        }
        
        return xmlHttp;
    }
    $.mypost = function(ajaxJSON){
        		$.post(ajaxJSON.url,ajaxJSON.data,function(data){
        			//msg有值
        			if(data.msg){//经过错误处理
        				alert(data.msg);	
        			}else{//后台是正确的
        				ajaxJSON.callback(data);
        			}
        		});
//    	var xmlhttp = ajaxFunction();
//		
//		xmlhttp.onreadystatechange = function(){
//			if(xmlhttp.readyState==4){
//				if(xmlhttp.status==200){
//					 var str = xmlhttp.responseText;
//					 var jsonObj = eval("("+str+")");
//					 ajaxJSON.callback(jsonObj);
//				}
//			}
//		}
//		
//		xmlhttp.open("post",ajaxJSON.url,true);
//		
//		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//		
//		var username = document.getElementById("username").value;
//		
//		xmlhttp.send("username="+username);
    }
})($);

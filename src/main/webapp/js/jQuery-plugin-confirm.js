jQuery.extend({
    oaconfirm: function(){
        //		$("a").each(function(e){
        //			var a = this;
        //			alert(a);
        //		});
        //		if(window.confirm("您确认要删除吗")){
        //			return true;
        //		}else{
        //			return false;
        //		}
        $("a").forEach(function(e){
            if ($(e).text() == "删除") {
                $(e).unbind("click");
                $(e).bind("click", function(){
                    if (window.confirm("您确认要删除吗?")) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
                
            }
        });
    }
});

var menuitem = {
	setting: {
        isSimpleData: true,
        treeNodeKey: "mid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        }
	},
	loadMenuitemTree:function(){
		$.post("/menu/showMenusByUid.do",null,function(data){
			$("#menuTree").zTree(menuitem.setting, data.menuitemList);
		});
	}
};

$().ready(function(){
	menuitem.loadMenuitemTree();
});

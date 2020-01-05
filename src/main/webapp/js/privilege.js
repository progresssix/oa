/**
 * 权限设置的对象
 */
var privilege = {
    /**
     * 初始化的工作
     */
    init: {
        /**
         * 初始化事件
         */
        initEvent: function(){
            /**
             * 声明设置权限的事件
             */
            $("a").each(function(){
                if ($(this).text() == "设置权限") {
                    $(this).unbind("click");
                    $(this).bind("click", function(){
                        /*
                          1、显示div
                         2、给username和uid赋值
                         2、用户的动态的显示
                         
                            把加载树之前等待的动画显示，当树加载出来以后，把动画隐藏
                         
                         3、加载权限树
                         4、全选复选框的功能
                         5、回显
                         6、保存(建立用户和权限之间的关系)
                         */
                        //显示div
                        privilege.option.divOption.showDiv();
                        //给用户赋值
                        privilege.init.initData.call(this);
                        //显示用户
                        privilege.option.userOption.showUser();
						//显示加载树的动态，隐藏树
						privilege.option.privilegeTreeOption.controlIsShowPrivilegeTreeOrLoading({
							loading:true,
							privilegeTree:false
						});
						//加载权限树
						privilege.option.privilegeTreeOption.loadPrivilegeTree();
                        return false;
                    });
                }
            });
            
            /**
             * 声明全选复选框的事件
             */
            $("#allchecked").unbind("change");
            $("#allchecked").bind("change", function(){
            	privilege.option.privilegeTreeOption.isAllChecked.call(this);
            });
            
            /**
             * 保存事件的声明
             */
            $("#savePrivilege").unbind("click");
            $("#savePrivilege").bind("click", function(){
            	privilege.option.privilegeTreeOption.savePrivilege();
            });
        },
        /**
         * 初始化数据
         */
        initData: function(){
            /**
             * 给user赋值
             * 	this代表当前的设置权限的超级链接
             */
            privilege.data.user.username = $(this).parent().siblings("td:first").text();
            privilege.data.user.uid = $(this).parent().siblings("input[type='hidden']").val();
        }
    },
    /**
     * 数据
     */
    data: {
        user: {
            username: '',
            uid: ''
        }
    },
    /**
     * 功能
     */
    option: {
        /**
         * div的操作
         */
        divOption: {
            /**
             * 显示DIV
             */
            showDiv: function(){
                //				$("#userTitle").show();
                //				$("#privilegeTitle").show();
                //				$("#privilegeContent").show();
                $("div:hidden").show();
            }
        },
        /**
         * 用户的操作
         */
        userOption: {
            /**
             *  显示用户信息
             */
            showUser: function(){
                $("#userImage").text("用户:" + privilege.data.user.username);
            }
        },
        /**
         * 权限树的操作
         */
        privilegeTreeOption: {
			zTree:'',
            setting: {
                isSimpleData: true,
                treeNodeKey: "mid",
                treeNodeParentKey: "pid",
                showLine: true,
                root: {
                    isRoot: true,
                    nodes: []
                },
				checkable:true,
				callback:{
					beforeChange:function(){
						/**
						 * 在点击复选框之前改变checkType的规则
						 */
						privilege.option.privilegeTreeOption.changeCheckType({
							"Y":"p",
							"N":'s'
						});
					},
					change:function(event, treeId, treeNode){
						if(privilege.option.privilegeTreeOption.isAllCheckNodes()){
							$("#allchecked").attr("checked",true);
						}else{
							$("#allchecked").attr("checked",false);
						}
					}
				}
            },
            /**
             * 加载权限树
             */
            loadPrivilegeTree: function(){
            	$.post("/usermenu/showMenus.do",{
					uid:privilege.data.user.uid
				},function(data){
                        privilege.option.privilegeTreeOption.zTree =  $("#privilegeTree").zTree(privilege.option.privilegeTreeOption.setting,data.menuitemList);
                        /**
                         * 当执行完上述代码以后,权限树已经加载出来了,这个时候应该隐藏动画,显示树
                         */
                        privilege.option.privilegeTreeOption.controlIsShowPrivilegeTreeOrLoading({
                            loading:false,
                            privilegeTree:true
                        });
                        /**
                         * 全选复选框的功能生效
                         */
                        $("#allchecked").attr("disabled","");

                        /**
                         * 根据树上的全选复选框是否为全部选中，设置全选复选框的初始化状态
                         */
                        if(privilege.option.privilegeTreeOption.isAllCheckNodes()){//全部被选中
                            $("#allchecked").attr("checked",true);
                        }else{//没有被全部选中
                            $("#allchecked").attr("checked",false);
                        }
				});
            },
            /**
             * 保存
             */
            savePrivilege: function(){
            	/**
            	 * 1、获取uid
            	 * 2、获取所有的被选中的mids(mids是一个字符串)
            	 * 3、提交ajax请求，把uid和mids传递到后台
            	 */
				/**
				 * 获取被选中的所有的复选框 
				 */
				var checkedNodes = privilege.option.privilegeTreeOption.zTree.getCheckedNodes(true);
				var mids = "";
				for(var i=0;i<checkedNodes.length;i++){
					if(i<checkedNodes.length-1){
						mids = mids+checkedNodes[i].mid+",";
					}else{
						mids = mids+checkedNodes[i].mid;
					}
				}
				$.post("/usermenu/save.do",{
					uid:privilege.data.user.uid,
                    mids:mids
				},function(data){
					alert(data.message);
				});
            },
			/**
			 * 全选复选框的控制
			 */
			isAllChecked:function(){
				/**
				 * 在点击全选复选框的时候改变checkType的规则
				 */
				privilege.option.privilegeTreeOption.changeCheckType({
					"Y":'ps',
					"N":'ps'
				});
				
				if($(this).attr("checked")){//全选复选框被全部选中
					privilege.option.privilegeTreeOption.zTree.checkAllNodes(true);
				}else{
					privilege.option.privilegeTreeOption.zTree.checkAllNodes(false);
				}
			},
			/**
			 * 控制权限树和loading图片的隐藏和显示
			 */
			controlIsShowPrivilegeTreeOrLoading:function(json){
				if(json.loading){
					$("#allchecked").attr("disabled","disabled");
					$("#loading").show();
				}else{
					$("#allchecked").attr("disabled","");
					$("#loading").hide();
				}
				
				if(json.privilegeTree){
					$("#allchecked").attr("disabled","");	
					$("#privilegeTree").show();
				}else{
					$("#allchecked").attr("disabled","disabled");
					$("#privilegeTree").hide();
				}
			},
			/**
			 * 判断树上的复选框是否被全部选中
			 */
			isAllCheckNodes:function(){
				var uncheckedNodes = privilege.option.privilegeTreeOption.zTree.getCheckedNodes(false);
				if(uncheckedNodes.length==0){
					return true;
				}else{
					return false;
				}
			},
			/**
			 * 改变setting中checkType规则的方法
			 */
			changeCheckType:function(checkType){
				var setting = privilege.option.privilegeTreeOption.zTree.getSetting();
				/**
				 * 把传递进来的checktype赋值到setting中
				 */
				setting.checkType = checkType;
				privilege.option.privilegeTreeOption.zTree.updateSetting(setting);
			}
        }
    }
};

$().ready(function(){
    privilege.init.initEvent();
});


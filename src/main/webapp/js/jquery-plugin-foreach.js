/**
 * 插件
 * 		目标是为了遍历jQuery数组中的每一个对象
 */
(function($){
	/**
	 * 遍历jQuery对象中的每一个元素
	 */
	$.fn.forEach = function(callback){
		var array = $(this);
		for(var i=0;i<array.length;i++){
			/**
			 * array[i]正在遍历中的jQuery数组中每一个对象
			 */
			callback.call(array[i],array[i]);//array[i].callback(array[i]);
		}
	}
})($);

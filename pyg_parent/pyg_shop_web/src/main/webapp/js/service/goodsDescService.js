//服务层
app.service('goodsDescService',function($http){
	this.save=function(entity){
		return  $http.post('../goods/save',entity );
	}

});

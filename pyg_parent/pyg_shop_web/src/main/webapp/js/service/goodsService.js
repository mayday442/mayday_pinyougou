//服务层
app.service('goodsService',function($http){
    this.updateIsMarketable = function (isMarketable, selectIds) {
        return $http.get('../goods/updateIsMarketable/' + isMarketable + "/" + selectIds);
    };

	//修改 
	this.update=function(entity){
		return  $http.post('../goods/update.do',entity );
	};
	//删除
	this.dele=function(ids){
		return $http.get('../goods/delete/'+ids);
	};
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../goods/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

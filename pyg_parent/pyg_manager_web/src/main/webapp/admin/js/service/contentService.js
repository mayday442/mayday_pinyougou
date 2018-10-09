//服务层
app.service('contentService',function($http){


	this.findAllContentCategory = function () {
		return $http.get('../content/findAllContentCategory')
    };

	this.findAll = function () {
        return $http.get('../content/findAll')
    };

	//查询实体
	this.findOne=function(id){
		return $http.get('../content/findOne/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../content/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../content/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../content/delete/'+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../content/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

app.service('contentService',function($http){



	this.findAllContent = function (categoryId) {
        return $http.get('./content/findAllContent/' + categoryId)
    };

	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('./content/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

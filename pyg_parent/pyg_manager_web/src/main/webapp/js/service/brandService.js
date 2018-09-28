//服务层
app.service('brandService',function($http){

	//查询实体
	this.findOne=function(id){
		return $http.get('../brand/findBrandById/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../brand/saveBrand',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../brand/updateBrand',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../brand/deleteBrands/'+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../brand/searchBrandList/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

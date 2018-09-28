//服务层
app.service('provincesService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../provinces/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNum,pageSize){
		return $http.get('../provinces/findPage/'+pageNum+"/"+pageSize);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../provinces/findOne/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../provinces/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../provinces/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../provinces/delete/'+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../provinces/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

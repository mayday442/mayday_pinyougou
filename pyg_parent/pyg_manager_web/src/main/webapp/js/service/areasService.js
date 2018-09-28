//服务层
app.service('areasService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../areas/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNum,pageSize){
		return $http.get('../areas/findPage/'+pageNum+"/"+pageSize);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../areas/findOne/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../areas/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../areas/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../areas/delete/'+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../areas/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

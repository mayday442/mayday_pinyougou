//服务层
app.service('payLogService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../payLog/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNum,pageSize){
		return $http.get('../payLog/findPage/'+pageNum+"/"+pageSize);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../payLog/findOne/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../payLog/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../payLog/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../payLog/delete/'+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../payLog/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

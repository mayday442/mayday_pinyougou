//服务层
app.service('orderService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../order/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNum,pageSize){
		return $http.get('../order/findPage/'+pageNum+"/"+pageSize);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../order/findOne/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../order/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../order/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../order/delete/'+ids);
	}
	//搜索
	this.search=function(pageNum,pageSize,searchEntity){
		return $http.post('../order/search/'+pageNum+"/"+pageSize, searchEntity);
	}    	
});

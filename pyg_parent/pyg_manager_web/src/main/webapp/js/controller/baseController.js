 //品牌控制层 
app.controller('baseController' ,function($scope){	
	
    //重新加载列表 数据
    $scope.reloadList=function(){
    	//切换页码  
    	$scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);	   	
    }
    
	//分页控件配置 
	$scope.paginationConf = {
         currentPage: 1,
         totalItems: 10,
         itemsPerPage: 10,
         perPageOptions: [10, 20, 30, 40, 50],
         onChange: function(){
        	 $scope.reloadList();//重新加载
     	 }
	}; 
	
	$scope.selectIds=[];//选中的ID集合 

	//更新复选
	$scope.updateSelection = function($event, id) {		
		if($event.target.checked){//如果是被选中,则增加到数组
			$scope.selectIds.push( id);			
		}else{
			var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除 
		}
	}
    $scope.list = [];
    $scope.isAllChecked = function () {
        for (var i = 0; i < $scope.list.length; i++) {
            if (!$scope.isChecked($scope.list[i].id)) {
                return false;
            }
        }
        return true;
    };

    $scope.isChecked = function (id) {
        var idx = $scope.selectIds.indexOf(id);
        return idx != -1;
    };

    $scope.selectAll = function ($event) {
        if ($event.target.checked) {
            for (var i = 0; i < $scope.list.length; i++) {
                // 添加之前想判读id是否存在数组中
                if (!$scope.isChecked($scope.list[i].id)) {
                    $scope.selectIds.push($scope.list[i].id);
                }
            }
        } else {
            for (var i = 0; i < $scope.list.length; i++) {
                // 添加之前想判读id是否存在数组中
                var index = $scope.selectIds.indexOf($scope.list[i].id)
                $scope.selectIds.splice(index, 1);
            }
        }
    }
	
});	
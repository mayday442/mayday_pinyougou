//控制层
app.controller('sellerController', function ($scope, $controller, sellerService) {

    $controller('baseController', {$scope: $scope});


    $scope.entity = {};

    //查询实体
    $scope.findOne = function (id) {
        sellerService.findOne(id).success(
            function (response) {
                $scope.entity = response.data;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.sellerId != null) {//如果有ID
            serviceObject = sellerService.update($scope.entity); //修改
        } else {
            serviceObject = sellerService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        sellerService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, pageSize) {
        sellerService.search(page, pageSize, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

});	

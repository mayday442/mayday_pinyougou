app.controller('goodsController', function ($scope, $controller, goodsService, itemCatService) {

    $controller('baseController', {$scope: $scope});//继承


    //查询实体
    $scope.findOne = function (id) {
        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    $scope.updateAuditStatus = function (auditStatus, info) {
        if ($scope.selectIds.length === 0) {
            return;
        }

        var flag = window.confirm("确定要" + info + "您选择的数据吗?");
        if (flag) {
            goodsService.updateAuditStatus(auditStatus, $scope.selectIds).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.selectIds = [];
                } else {
                    alert(response.message);
                }
            })
        }

    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        goodsService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    };

    $scope.itemCat = {};

    $scope.findAllCategory = function () {
        itemCatService.findAllCategory().success(function (response) {
            for (var i = 0; i < response.data.length; i++) {
                $scope.itemCat[response.data[i].id]  = response.data[i].name;
            }
        })
    };


});	

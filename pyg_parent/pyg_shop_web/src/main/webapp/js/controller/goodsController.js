app.controller('goodsController', function ($scope, $controller, goodsService, itemCatService) {

    $controller('baseController', {$scope: $scope});//继承


    //查询实体
    $scope.findOne = function (id) {
        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = goodsService.update($scope.entity); //修改
        } else {
            serviceObject = goodsService.add($scope.entity);//增加
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
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        goodsService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;
                findCategory($scope.list);
            }
        );
    };

    $scope.findAllCategory = function () {
        itemCatService.findAllCategory().success(function (response) {
            $scope.itemCatList = response.data
        })
    };

    function findCategory(list){
        var itemCatList = $scope.itemCatList

        for (var i = 0; i < list.length; i++) {
            for (var j = 0; j < itemCatList.length; j++) {
                if (list[i].category1Id === itemCatList[j].id) {
                    list[i].category1Id = itemCatList[j].name
                }

                if (list[i].category2Id === itemCatList[j].id) {
                    list[i].category2Id = itemCatList[j].name
                }

                if (list[i].category3Id === itemCatList[j].id) {
                    list[i].category3Id = itemCatList[j].name
                }
            }
        }
    }


    $scope.updateIsMarketable = function (isMarketable, info) {
        if ($scope.selectIds.length === 0) {
            return;
        }

        var flag = window.confirm("确定要" + info + "您选择的数据吗?");
        if (flag) {
            goodsService.updateIsMarketable(isMarketable, $scope.selectIds).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.selectIds = [];
                } else {
                    alert(response.message);
                }
            })
        }

    }

});	

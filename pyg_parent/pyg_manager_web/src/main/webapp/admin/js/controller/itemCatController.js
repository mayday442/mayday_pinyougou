//控制层
app.controller('itemCatController', function ($scope, $controller, itemCatService, typeTemplateService) {

    $controller('baseController', {$scope: $scope});//继承


    $scope.list = {};
    $scope.grade = 1;
    $scope.itemCat = {};
    $scope.searchItemCat = {};

    $scope.setGrade = function (value) {
        $scope.grade = value;
    };

    //分页
    $scope.findPage = function (page, rows, id, name) {
        $scope.searchItemCat.id = id;
        $scope.selectList(id, name);
        itemCatService.findPage(page, rows, id).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };

    $scope.selectList = function(id, name){
        if ($scope.grade === 1){
            $scope.itemCat_1 = null;
            $scope.itemCat_2 = null;
            return;
        }

        if ($scope.grade === 2) {
            $scope.itemCat_1 = {'name' : name, 'id' : id};
            $scope.itemCat_2 = null;
            return;
        }
        $scope.itemCat_2 = {'name' : name, 'id' : id};

    };

    //查询实体
    $scope.findOne = function (id) {
        itemCatService.findOne(id).success(
            function (response) {
                $scope.itemCat = response.data;
            }
        );
    };

    //保存
    $scope.save = function () {
        var serviceObject;

        if ($scope.itemCat.id == null) {

            if ($scope.searchItemCat.id == null){
                $scope.searchItemCat.id = 0;
            }
            $scope.itemCat.parentId = $scope.searchItemCat.id;

            serviceObject = itemCatService.add($scope.itemCat);
        } else {
            serviceObject = itemCatService.update($scope.itemCat);

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
    };


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        var isDelete = window.confirm('确认删除吗')
        if (isDelete) {
            itemCatService.dele($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        $scope.reloadList();//刷新列表
                    }
                }
            );
        }
    };

    //搜索
    $scope.search = function (page, rows) {
        $scope.findAllTypeTemplate();
        itemCatService.search(page, rows, $scope.searchItemCat, $scope.searchItemCat.id).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };

    $scope.typeTemplateList = {data:[]};
    $scope.findAllTypeTemplate = function () {
        typeTemplateService.findAll().success(function (response) {
            $scope.typeTemplateList = {data: response.data}
        });


    };

});	

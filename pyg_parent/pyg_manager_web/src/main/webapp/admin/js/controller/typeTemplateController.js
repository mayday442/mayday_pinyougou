app.controller('typeTemplateController', function ($scope, $controller, typeTemplateService, brandService, specificationService) {

    $controller('baseController', {$scope: $scope});//继承


    $scope.typeTemplate = {};

    $scope.findOne = function (id) {

        typeTemplateService.findOne(id).success(
            function (response) {
                $scope.typeTemplate = response.data;
                $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
                $scope.typeTemplate.specIds = JSON.parse($scope.typeTemplate.specIds);
                $scope.typeTemplate.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
            }
        );


    };

    //保存
    $scope.save = function () {

        var serviceObject;
        if ($scope.typeTemplate.id != null) {
            serviceObject = typeTemplateService.update($scope.typeTemplate);
        } else {
            serviceObject = typeTemplateService.add($scope.typeTemplate);
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    };


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        var isDelete = window.confirm("确认删除吗?")
        if (isDelete) {
            typeTemplateService.dele($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        $scope.reloadList();//刷新列表
                    }
                }
            );
        }

    };

    $scope.searchTypeTemplate = {};//定义搜索对象

    //搜索

    $scope.search = function (page, rows) {
        $scope.findAllBrand();
        $scope.findAllSpecification();
        typeTemplateService.search(page, rows, $scope.searchTypeTemplate).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };

    $scope.brandList = {data: []};

    $scope.findAllBrand = function () {

        brandService.findAll().success(function (response) {

            $scope.brandList = {data: response.data};
        })
    };

    $scope.specList = {data: []};

    $scope.findAllSpecification = function () {

        specificationService.findAll().success(function (response) {

            $scope.specList = {data: response.data};
        })
    };

    $scope.jsonToString = function (jsonString, key) {
        //将 json 字符串转换为 json 对象
        var value = "";

        var json = JSON.parse(jsonString);
        for (var i = 0; i < json.length; i++) {
            if (i > 0) {
                value += ","
            }
            value += json[i][key];
        }

        return value;
    };

    $scope.addRows = function () {
        $scope.typeTemplate.customAttributeItems.push({})
    };

    $scope.deleteRow = function (index) {
        $scope.typeTemplate.customAttributeItems.splice(index, 1)
    }




});

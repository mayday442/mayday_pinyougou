app.controller("specificationController", function ($scope, $controller, specificationService) {

    $controller('baseController', {$scope:$scope});

    $scope.spec = {};
    $scope.searchSpec = {};

    // 分页搜索
    $scope.search = function (pageNum, pageSize) {

        specificationService.search(pageNum, pageSize, $scope.searchSpec).success(function (response) {
            $scope.list = response.list;
            $scope.paginationConf.totalItems = response.total;
        })
    };

    $scope.deleteRow = function (specOption) {
        var specOptionIndex = $scope.spec.specOptionList.indexOf(specOption)
        $scope.spec.specOptionList.splice(specOptionIndex, 1)
    };

    $scope.addRow = function () {
        $scope.spec.specOptionList.push({})
    };

    $scope.clear = function(){
        $scope.spec = {};
        $scope.spec.specOptionList = [];
    };

    $scope.findSpecById = function (spec) {
        specificationService.findSpecById(spec).success(function (response) {
            spec.specOptionList = response
            $scope.spec = spec
        })
    };

    $scope.save = function () {
        specificationService.save($scope.spec.id, $scope.spec).success(function (response) {
            if (response.success){
                $scope.searchSpec = {}
                $scope.reloadList()
            } else {
                alert(response.message)
            }
        })
    };

    $scope.deleteSpec = function () {
        var isDelete = window.confirm("确认删除吗?");
        if (isDelete) {
            specificationService.deleteSpec($scope.selectIds).success(function (response) {
                if (response.success) {
                    $scope.reloadList()
                }else {
                    alert(response.message)
                }
            })
        }
    }
});
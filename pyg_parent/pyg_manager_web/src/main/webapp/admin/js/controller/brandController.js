app.controller("brandController", function ($scope, brandService) {
    $scope.reloadList = function () {
        //切换页码
        if ($scope.searchBrand == null) {
            $scope.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        } else {
            $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        }

    };

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();
        }
    };

    $scope.findPage = function (pageNum, pageSize) {
        brandService.findPage(pageNum, pageSize).success(function (response) {
                $scope.brandList = response.list;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    };

    $scope.save = function () {

        brandService.save($scope).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    };

    $scope.selectIds = [];

    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id);

        } else {

            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);
        }
    };

    $scope.delete = function () {

        brandService.delete().success(function (response) {
            if (response.success) {
                $scope.reloadList()
            } else {
                alert(response.message)
            }
        })
    };


    $scope.findBrandById = function (id) {
        brandService.findBrandById(id).success(function (response) {
            $scope.brand = response
        })
    };

    $scope.search = function (pageNum, pageSize) {
        brandService.search(pageNum, pageSize, $scope).success(function (response) {
            $scope.brandList = response.list;
            $scope.paginationConf.totalItems = response.total;
        })
    }

})
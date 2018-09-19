app.controller("brandController", function ($scope, $http) {
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
        $http.get('../brand/listAllBrand?pageNum=' + pageNum + '&pageSize=' + pageSize).success(function (response) {
                $scope.brandList = response.list;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    };

    $scope.save = function () {

        $scope.method = "saveBrand";
        if ($scope.brand.id != null) {
            $scope.method = "updateBrand";
        }

         $http.post('../brand/' + $scope.method, $scope.brand).success(function (response) {
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

        $http.get('../brand/deleteBrands?ids=' + $scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList()
            } else {
                alert(response.message)
            }
        })
    };


    $scope.findBrandById = function (id) {
        $http.get('../brand/findBrandById/' + id).success(function (response) {
            $scope.brand = response
        })
    };

    $scope.search = function (pageNum, pageSize) {
        pageNum = $scope.paginationConf.currentPage;
        pageSize = $scope.paginationConf.itemsPerPage;

        return $http.post('../brand/searchBrandList?pageNum=' + pageNum + '&pageSize=' + pageSize, $scope.searchBrand).success(function (response) {
            $scope.brandList = response.list;
            $scope.paginationConf.totalItems = response.total;
        })
    }

})
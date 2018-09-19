app.service("brandService", function ($http) {

    this.findPage = function (pageNum, pageSize) {
        return $http.get('../brand/listAllBrand?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };

    this.save = function ($scope) {
        $scope.method = "saveBrand";
        if ($scope.brand.id != null) {
            $scope.method = "updateBrand";
        }

        return $http.post('../brand/' + $scope.method, $scope.brand);
    };

    this.delete = function () {
        return $http.get('../brand/deleteBrands?ids=' + $scope.selectIds);
    };

    this.findBrandById = function (id) {
        return $http.get('../brand/findBrandById/' + id);
    };

    this.search = function (pageNum, pageSize, $scope) {
        if (pageNum == null){
            pageNum = 1
        }
        if (pageSize == null) {
            pageSize = 10
        }

        return $http.post('../brand/searchBrandList?pageNum=' + pageNum + '&pageSize=' + pageSize, $scope.searchBrand)
    };

})
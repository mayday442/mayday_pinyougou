app.controller("brandController", function ($scope, $controller, brandService) {

        $controller('baseController', {$scope: $scope});

        $scope.searchBrand = {}
        $scope.search = function (pageNum, pageSize) {

            brandService.search(pageNum, pageSize, $scope.searchBrand).success(function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;
            })
        }

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

        $scope.delete = function () {

            var isDelete = window.confirm('确认要删除吗?')
            if (isDelete) {
                brandService.delete($scope).success(function (response) {
                    if (response.success) {
                        $scope.reloadList()
                    } else {
                        alert(response.message)
                    }
                })
            }

        };


        $scope.findBrandById = function (id) {
            brandService.findBrandById(id).success(function (response) {
                $scope.brand = response
            })
        };



    }
);
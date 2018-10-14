app.controller("baseController", function ($scope) {

        $scope.reloadList = function () {
            $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
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

        $scope.selectIds = [];

        $scope.updateSelection = function ($event, id) {
            if (!$event.target.checked) {
                var idx = $scope.selectIds.indexOf(id);
                $scope.selectIds.splice(idx, 1);
            } else {
                $scope.selectIds.push(id);
            }

        };

        $scope.isAllChecked = function () {
            for (var i = 0; i < $scope.list.length; i++) {
                if (!$scope.isChecked($scope.list[i].id)) {
                    return false;
                }
            }
            return true;
        };

        $scope.isChecked = function (id) {
            var idx = $scope.selectIds.indexOf(id);
            return idx != -1;
        };

        $scope.selectAll = function ($event) {
            if ($event.target.checked) {
                for (var i = 0; i < $scope.list.length; i++) {
                    // 添加之前想判读id是否存在数组中
                    if (!$scope.isChecked($scope.list[i].id)) {
                        $scope.selectIds.push($scope.list[i].id);
                    }
                }
            } else {
                for (var i = 0; i < $scope.list.length; i++) {
                    // 添加之前想判读id是否存在数组中
                    var index = $scope.selectIds.indexOf($scope.list[i].id)
                    $scope.selectIds.splice(index, 1);
                }
            }
        }
    }
);
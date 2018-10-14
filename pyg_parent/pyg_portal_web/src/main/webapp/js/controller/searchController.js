app.controller('searchController', function ($scope, $controller, searchService) {

    $controller('baseController', {$scope: $scope});//继承

    $scope.searchMap = {
        "category" : "",
        "brand" : "",
        "spec" : {},
        "price" : "",
        "sort" : "ASC",
        "sortField" : "price",
        "page" : 1,
        "pageSize" : 30
    };

    //搜索
    $scope.initSearch = function () {
        contentService.findAll()
    };


    $scope.search = function () {
        searchService.search($scope.searchMap).success(function (response) {
            $scope.resultMap = response.data;
        })
    }


});	

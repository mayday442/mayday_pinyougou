app.controller('contentController', function ($scope, $controller, contentService) {

    $controller('baseController', {$scope: $scope});//继承

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        contentService.findAll()
    };


    $scope.contentList = {};

    $scope.findAllContent = function (categoryId) {
        contentService.findAllContent(categoryId).success(function (response) {
            $scope.contentList[categoryId] = response.data
        })
    }

});	

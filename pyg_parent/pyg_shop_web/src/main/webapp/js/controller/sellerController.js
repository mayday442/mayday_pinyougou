app.controller('sellerController',
    function ($scope, $controller, sellerService) {

        $controller('baseController', {
            $scope: $scope
        });

        $scope.entity = {};

        // 查询实体
        $scope.findOne = function (id) {
            sellerService.findOne(id).success(function (response) {
                $scope.entity = response;
            });
        };


        $scope.save = function () {
            sellerService.add($scope.entity).success(function (response) {
                if (response.success) {
                    alert("注册成功,将在二十四小时之内完成审核,请耐心等待")
                    location.href = "shoplogin.html";
                } else {
                    alert(response.message);
                }
            });
        };


    });

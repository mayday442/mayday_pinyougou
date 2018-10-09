//控制层
app.controller('contentController', function ($scope, $controller, contentService, uploadService) {

    $controller('baseController', {$scope: $scope});//继承



    $scope.findOne = function(id){
        contentService.findOne(id).success(function (response) {
            $scope.entity = response.data
        })
    };

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = contentService.update($scope.entity); //修改
        } else {
            serviceObject = contentService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    window.location.reload()
                } else {
                    alert(response.message);
                }
            }
        );
    }

    $scope.findAllContentCategory = function(){

        contentService.findAllContentCategory().success(function (response) {
            $scope.contentCategoryList = response.data;
        })


    };
    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        var isDelete = window.confirm("确认要删除数据吗?")

        if (isDelete){
            contentService.dele($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        window.location.reload()
                    }
                }
            );
        }
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        contentService.findAll()
    };



    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (response) {
            if (response.success) {
                $scope.entity.pic = response.message;
            } else {
                alert(response.message)
            }
        })
    };

    $scope.findAllContent = function () {
        contentService.findAll().success(function (response) {
            $scope.list = response.data
        })
    }

});	

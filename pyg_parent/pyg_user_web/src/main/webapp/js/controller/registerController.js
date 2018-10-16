app.controller("registerController", function ($scope, registerService) {
    $scope.entity = {};
    $scope.toSendSms = function () {
        if (checkPhone($scope.entity.phone)) {
            return;
        }
        registerService.sendSms($scope.entity.phone).success(function (response) {
            if (response.success) {
                $scope.code = response.message;
                alert("发送成功")
            }else {
                alert(response.message)
            }
        })
    }

    $scope.toRegister = function () {
        if ($scope.code !== $scope.userCode) {
            alert("验证码输入错误")
            return;
        }
        registerService.toRegister($scope.entity).success(function (response) {
            if (response.success) {
                alert("跳转登录页面")
            } else {
                alert(response.message)
            }
        })



    };


    function checkPhone(phone){
        if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
            alert("手机号码有误，请重填");
            return true;
        }
    }

    }
);
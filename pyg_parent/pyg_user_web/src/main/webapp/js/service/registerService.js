app.service('registerService',function($http){

	this.sendSms = function (phone) {
		return $http.get('./user/sendSms/' + phone)
    }

    this.toRegister = function (entity) {
		return $http.post('./user/userRegister', entity)
    }

});

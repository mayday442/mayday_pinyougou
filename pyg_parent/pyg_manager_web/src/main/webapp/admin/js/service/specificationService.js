app.service("specificationService", function ($http) {

    this.search = function (pageNum, pageSize, specification) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10
        }

        return $http.post('../specification/searchSpecificationList?pageNum=' +
            pageNum + '&pageSize=' + pageSize, specification)
    };

    this.findSpecById = function (spec) {
        return $http.get('../specification/findSpecById/' + spec.id)
    };

    this.save = function (id, spec) {
        var method = 'saveSpecification';
        if (id != null) {
            method = 'updateSpecification'
        }

        return $http.post('../specification/' + method, spec)
    };
    
    this.deleteSpec = function (selectIds) {
        return $http.get('../specification/deleteSpecification?ids=' + selectIds)
    };

    this.findAll = function () {
        return $http.get('../specification/findAll')
    }

});
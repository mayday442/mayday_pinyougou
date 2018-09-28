//服务层
app.service('sellerService', function ($http) {


    this.findOne = function (id) {
        return $http.get('../seller/findOne/' + id);
    }


    this.add = function (entity) {
        return $http.post('../seller/add', entity);
    }

    this.update = function (entity) {
        return $http.post('../seller/update', entity);
    }
    //删除
    this.dele = function (ids) {
        return $http.get('../seller/delete/' + ids);
    }

    this.search = function (pageNum, pageSize, searchEntity) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }


        return $http.post('../seller/search/' + pageNum + "/" + pageSize, searchEntity);
    }
});

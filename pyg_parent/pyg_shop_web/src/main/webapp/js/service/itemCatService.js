//服务层
app.service('itemCatService', function ($http) {

    //分页
    this.findAll = function (parentId) {
        return $http.get('../itemCat/findAll/' + parentId);
    };
    //查询实体
    this.findOne = function (id) {
        return $http.get('../itemCat/findOne/' + id);
    };

    this.findAllCategory = function () {
        return $http.get('../itemCat/findAll')
    }
});

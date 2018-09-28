//服务层
app.service('itemCatService', function ($http) {

    //分页
    this.findPage = function (pageNum, pageSize, id) {
        return $http.get('../itemCat/findPage/' + pageNum + "/" + pageSize + "/" + id);
    };
    //查询实体
    this.findOne = function (id) {
        return $http.get('../itemCat/findOne/' + id);
    };
    //增加
    this.add = function (entity) {
        return $http.post('../itemCat/add', entity);
    };
    //修改
    this.update = function (entity) {
        return $http.post('../itemCat/update', entity);
    };
    //删除
    this.dele = function (ids) {
        return $http.get('../itemCat/delete/' + ids);
    };
    //搜索
    this.search = function (pageNum, pageSize, searchItemCat, id) {
        if (id == null) {
            id = 0
        }

        return $http.post('../itemCat/search/' + pageNum + "/" + pageSize + "/" + id, searchItemCat);
    }
});

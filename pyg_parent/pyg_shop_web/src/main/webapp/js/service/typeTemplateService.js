app.service('typeTemplateService', function ($http) {



    this.findSpecList = function (typeId) {
        return $http.get('../typeTemplate/findSpecList/' + typeId)
    }

    //查询实体
    this.findOne = function (id) {
        return $http.get('../typeTemplate/findOne/' + id);
    }
    //增加
    this.add = function (entity) {
        return $http.post('../typeTemplate/add', entity);
    }
    //修改
    this.update = function (entity) {
        return $http.post('../typeTemplate/update', entity);
    }
    //删除
    this.dele = function (ids) {
        return $http.get('../typeTemplate/delete/' + ids);
    }
    //搜索
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../typeTemplate/search/' + pageNum + "/" + pageSize, searchEntity);
    }
});

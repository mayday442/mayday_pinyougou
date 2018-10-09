//服务层
app.service('goodsService', function ($http) {

    this.updateAuditStatus = function (auditStatus, selectedIds) {
        return $http.get('../goods/updateAuditStatus/' + auditStatus + "/" + selectedIds);
    };

    //搜索
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../goods/search/' + pageNum + "/" + pageSize, searchEntity);
    }


    this.updateIsMarketable = function (isMarketable, selectIds) {
        return $http.get('../goods/updateIsMarketable/' + isMarketable + "/" + selectIds);
    }
});

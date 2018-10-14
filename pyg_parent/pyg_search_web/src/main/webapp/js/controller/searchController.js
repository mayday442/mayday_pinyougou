app.controller('searchController', function ($scope, $location, $controller, searchService) {

    $controller('baseController', {$scope: $scope});//继承


    $scope.categoryList = [];
    $scope.categoryShow = [];

    $scope.searchMap = {
        "category": "",
        "brand": "",
        "spec": {},
        "price": "",
        "sort": "ASC",
        "sortField": "price",
        "page": 1,
        "pageSize": 30
    };

    //搜索
    $scope.initSearch = function () {
        var keywords = $location.search()["keywords"]

        if (keywords != null) {
            $scope.searchMap.item_keywords = keywords;
        }

        $scope.search();
    };


    $scope.search = function () {
        searchService.search($scope.searchMap).success(function (response) {
            $scope.resultMap = response.data;
            buildPageLabel()
        });
    };

    $scope.addCategorySpecList = function(key, value) {

        $scope.searchMap.page = 1;

        $scope.searchMap.spec[key] = value;
        $scope.categoryList[key] = value;
        $scope.categoryShow.push({key : key , value : value});

        $scope.search();
    };


    $scope.addCategoryList = function (key, value) {

        $scope.searchMap.page = 1;

        $scope.searchMap[key] = value;
        $scope.categoryList[key] = value;
        $scope.categoryShow.push({key : key , value : value});

        $scope.search()
    };


    $scope.addSearchToMap = function(key, value) {
        $scope.searchMap[key] = value;
        $scope.search()
    };


    $scope.deleCategoryList = function (key) {
        $scope.searchMap.page = 1;
        $scope.searchMap.spec[key] = null;
        $scope.searchMap[key] = null;

        for (var i = 0; i < $scope.categoryShow.length; i++) {
            if (key === $scope.categoryShow[i].key){
                $scope.categoryShow.splice(i, 1);
            }
        }
        $scope.categoryList[key] = null;

        $scope.search()
    };

    function buildPageLabel() {
        //新增分页栏属性
        $scope.totalPages = [];

        //得到最后页码
        var maxPageNo = $scope.resultMap.totalPages;

        //开始页码
        var firstPage = 1;
        //截止页码
        var lastPage = maxPageNo;

        //前面有点
        $scope.firstDot = true;

        //后边有点
        $scope.lastDot = true;


        //如果总页数大于 5 页,显示部分页码
        if ($scope.resultMap.totalPages > 5) {

            //如果当前页小于等于 3
            if ($scope.searchMap.page <= 3) {

                //前 5 页
                lastPage = 5;

                //前面没点
                $scope.firstDot = false;

                //如果当前页大于等于最大页码-2
            } else if ($scope.searchMap.page >= lastPage - 2) {
                //后 5 页
                firstPage = maxPageNo - 4;
                $scope.lastDot = false;//后边没点

                //显示当前页为中心的 5 页
            } else {
                firstPage = $scope.searchMap.page - 2;
                lastPage = $scope.searchMap.page + 2;
            }
        } else {
            //前面无点
            $scope.firstDot = false;

            //后边无点
            $scope.lastDot = false;
        }
        //循环产生页码标签
        for (var i = firstPage; i <= lastPage; i++) {
            $scope.totalPages.push(i);
        }
    }

    $scope.toItemHtml = function(id) {
        location.href = "http://item.pinyougou.com/" + id + ".html"
    }


});

app.controller('goodsEditController', function ($scope, goodsDescService, itemCatService,
                                                typeTemplateService) {

    $scope.entity = {
        goods: {isEnableSpec :0},
        goodsDesc: {itemImages : [], specificationItems : [], customAttributeItems : []},
        itemList: []
    };

    $scope.itemCat = {};

    $scope.list1 = [];
    $scope.list2 = [];
    $scope.list3 = [];

    $scope.findTypeTemplateId = function (newValue) {

        for (var i = 0; i < $scope.list3.length; i++) {
            if (newValue === $scope.list3[i].id) {
                $scope.itemCat = $scope.list3[i];
            }
        }
    };

    $scope.findItemsCat = function (parentId) {
        itemCatService.findAll(parentId).success(function (response) {
            $scope.list1 = response
        })
    };

    $scope.categoryId1Change = function (parentId) {
        itemCatService.findAll(parentId).success(function (response) {
            $scope.list3 = [];
            $scope.list2 = response
        })
    };

    $scope.categoryId2Change = function (parentId) {
        itemCatService.findAll(parentId).success(function (response) {
            $scope.list3 = response;
        })
    };

    $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
        $scope.categoryId1Change(newValue)
    });

    $scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {
        $scope.categoryId2Change(newValue)
    });

    $scope.$watch('entity.goods.category3Id', function (newValue, oldValue) {
        $scope.findTypeTemplateId(newValue);
        listBrands($scope.itemCat.typeId)
    });

    function listBrands(typeId) {
        typeTemplateService.findOne(typeId).success(function (response) {
            $scope.entity.goods.typeTemplateId = typeId
            $scope.brandList = JSON.parse(response.data.brandIds);
            $scope.entity.goodsDesc.customAttributeItems = JSON.parse(response.data.customAttributeItems)
        })

        findSpecificationOption(typeId)
    }

    function findSpecificationOption(typeId){
        typeTemplateService.findSpecList(typeId).success(function (response) {
            $scope.specIds = response.data
        })
    }


});
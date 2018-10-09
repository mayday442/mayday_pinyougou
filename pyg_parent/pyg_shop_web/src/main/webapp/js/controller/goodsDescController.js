app.controller('goodsDescController', function ($scope, $controller, uploadService, goodsDescService) {


    $controller('goodsEditController', {
        $scope: $scope
    });

    $scope.save = function () {
        $scope.entity.goodsDesc.introduction = editor.html();

        goodsDescService.save($scope.entity).success(function (response) {
            if (response.success){
                location.href = "goods.html";
            }else {
                alert('保存失败');
            }
        });


    };


    $scope.upload = function () {
        uploadService.uploadFile().success(function (response) {
            if (response.success) {
                $scope.entity_image.url = response.message;
            } else {
                alert(response.message)
            }
        })
    };

    $scope.addItemImage = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.entity_image)
    };

    $scope.deleImage = function ($index) {
        $scope.entity.goodsDesc.itemImages.splice($index, 1)
    };

    $scope.isHidden = function (event) {
        if (event.target.checked) {
            $scope.optionsIsHidden = {}
        } else {
            $scope.optionsIsHidden = null
        }


    };


    $scope.add = function (option, pojo, envent) {
        addSpecificationItems(option, pojo, envent);
    };

    function addSpecificationItems(option, pojo, envent) {
        var specItems = $scope.entity.goodsDesc.specificationItems;

        var specItemsIsPush = true;
        if (envent.target.checked) {
            for (var i = 0; i < specItems.length; i++) {
                if (pojo.text === specItems[i].attributeName) {
                    specItems[i].attributeValue.push(option.optionName);
                    specItemsIsPush = false;
                    break;
                }
            }

            if (specItemsIsPush) {
                specItems.push({attributeName: pojo.text, attributeValue: [option.optionName]})
            }

        } else {
            for (var j = 0; j < specItems.length; j++) {
                if (pojo.text === specItems[j].attributeName) {
                    var index = specItems[j].attributeValue.indexOf(option.optionName);
                    specItems[j].attributeValue.splice(index, 1);
                    if (specItems[j].attributeValue.length === 0) {
                        specItems.splice(specItems.indexOf(specItems[j]), 1)
                    }
                    return;
                }
            }
        }


    }

    $scope.createItemList = function () {
        $scope.entity.itemList = [{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}];
        var items = $scope.entity.goodsDesc.specificationItems;
        for (var i = 0; i < items.length; i++) {
            $scope.entity.itemList =
                addColumn($scope.entity.itemList, items[i].attributeName, items[i].attributeValue);
        }
    };

    function addColumn(list, columnName, columnValues) {
        //新的集合
        var newList = [];

        for (var i = 0; i < list.length; i++) {
            var oldRow = list[i];
            for (var j = 0; j < columnValues.length; j++) {
                var newRow = JSON.parse(JSON.stringify(oldRow));//深克隆
                newRow.spec[columnName] = columnValues[j];
                newList.push(newRow);
            }

        }
        return newList;
    }


})
;

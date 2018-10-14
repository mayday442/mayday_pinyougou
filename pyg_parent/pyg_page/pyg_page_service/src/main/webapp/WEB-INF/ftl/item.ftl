<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>产品详情页</title>
    <link rel="icon" href="assets/img/favicon.ico">

    <link rel="stylesheet" type="text/css" href="css/webbase.css"/>
    <link rel="stylesheet" type="text/css" href="css/pages-item.css"/>
    <link rel="stylesheet" type="text/css" href="css/pages-zoom.css"/>
    <link rel="stylesheet" type="text/css" href="css/widget-cartPanelView.css"/>

    <script src="./plugins/angularjs/angular.min.js"></script>

    <script src="./js/base.js"></script>

    <script>
        var itemList = [
            <#list good.itemList as item>
        {
            "id":${item.id?c},
            "title": "${item.title!''}",
            "price":${item.price?c},
            "spec": ${item.spec}
        },</#list>
        ];

        var item = {
            "spec" : ${item.spec}
        }

    </script>
    <script src="./js/controller/itemController.js"></script>
</head>

<body ng-app="pyg" ng-controller="itemController">
<!-- 头部栏位 -->
<!--页面顶部-->
   <#include "head.ftl"/>

<div class="py-container" >
    <div id="item">
        <div class="crumb-wrap">
            <ul class="sui-breadcrumb">
                <li>
                    <a href="#">手机、数码、通讯</a>
                </li>
                <li>
                    <a href="#">手机</a>
                </li>
                <li>
                    <a href="#">Apple苹果</a>
                </li>
                <li class="active">${good.goods.goodsName}</li>
            </ul>
        </div>
        <!--product-info-->
        <div class="product-info">
            <div class="fl preview-wrap">
                <!--放大镜效果-->
                <div class="zoom">
                    <!--默认第一个预览-->
                    <div id="preview" class="spec-preview">

                            <#assign spec = item.spec?eval>
                            <#assign itemImages = good.goodsDesc.itemImages? eval>
							<#if (itemImages?size > 0)>
							<span class="jqzoom"><img jqimg="${itemImages[0].url}" style="width: 400px; height: 400px"
                                                      src="${itemImages[0].url}"/></span>
                            </#if>
                    </div>
                    <!--下方的缩略图-->
                    <div class="spec-scroll">
                        <a class="prev">&lt;</a>
                        <!--左右按钮-->
                        <div class="items">
                            <ul>
                                    <#list itemImages as image>
                                        <li><img src="${image.url}" bimg="${image.url}" onmousemove="preview(this)"/>
                                        </li>
                                    </#list>
                            </ul>
                        </div>
                        <a class="next">&gt;</a>
                    </div>
                </div>
            </div>
            <div class="fr itemInfo-wrap">
                <div class="sku-name">
                    <h4>${item.title}</h4>
                </div>
                <div class="news"><span>${good.goods.caption}</span></div>
                <div class="summary">
                    <div class="summary-wrap">
                        <div class="fl title">
                            <i>价　　格</i>
                        </div>
                        <div class="fl price">
                            <i>¥</i>
                            <em>${item.price}</em>
                            <span>降价通知</span>
                        </div>
                        <div class="fr remark">
                            <i>累计评价</i><em>612188</em>
                        </div>
                    </div>
                    <div class="summary-wrap">
                        <div class="fl title">
                            <i>促　　销</i>
                        </div>
                        <div class="fl fix-width">
                            <i class="red-bg">加价购</i>
                            <em class="t-gray">满999.00另加20.00元，或满1999.00另加30.00元，或满2999.00另加40.00元，即可在购物车换
                                购热销商品</em>
                        </div>
                    </div>
                </div>
                <div class="support">
                    <#if good.goodsDesc.customAttributeItems ??>
                        <#assign customAttributeItems = good.goodsDesc.customAttributeItems?eval>

                        <#list customAttributeItems as cai>
						<div class="summary-wrap">
                            <div class="fl title">
                                <i>${cai.text}</i>
                            </div>
                            <div class="fl fix-width">
                                <em class="t-gray">${cai.value}</em>
                            </div>
                        </div>
                        </#list>
                    </#if>
                </div>
                <div class="clearfix choose">
                    <div id="specification" class="summary-wrap clearfix">
                        <#if good.goodsDesc.specificationItems ??>
                        <#assign  specificationItems = good.goodsDesc.specificationItems?eval>
                        <#list specificationItems as spec>

                        <dl>
                            <dt>
                                <div class="fl title">
                                    <i>${spec.attributeName}</i>
                                </div>
                            </dt>
                            <#--selected-->
                            <#list spec.attributeValue as value>
                            <dd ng-click="updateSpec('${spec.attributeName}','${value}' )"><a href="javascript:void(0)"
                                   class="{{isSelected('${spec.attributeName}', '${value}')?'selected':''}}">${value}
                                 <span title="点击取消选择">&nbsp;</span>

                            </a></dd>
                            </#list>
                        </dl>
                        </#list>
                        </#if>
                    </div>
                    <div class="summary-wrap">
                        <div class="fl title">
                            <div class="control-group">
                                <div class="controls">
                                    <input autocomplete="off" type="text" value="1" minnum="1" class="itxt"
                                           ng-model="shopNum"/>
                                    <a href="javascript:void(0)" class="increment plus"
                                       ng-click="updateShopNum(shopNum + 1)">+</a>
                                    <a href="javascript:void(0)" class="increment mins"
                                       ng-click="updateShopNum(shopNum - 1)">-</a>
                                </div>
                            </div>
                        </div>
                        <div class="fl">
                            <ul class="btn-choose unstyled">
                                <li>
                                    <a href="cart.html" target="_blank" class="sui-btn  btn-danger addshopcar">加入购物车</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--product-detail-->
			<#include "left.ftl"/>
        <!--like-->
        <div class="clearfix"></div>
			<#include "like.ftl"/>
    </div>
</div>
<!-- 底部栏位 -->
<!--页面底部-->
 <#include "foot.ftl"/>
<!--页面底部END-->

<!--侧栏面板开始-->
<div class="J-global-toolbar">
    <div class="toolbar-wrap J-wrap">
        <div class="toolbar">
            <div class="toolbar-panels J-panel">

                <!-- 购物车 -->
                <div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">
                    <h3 class="tbar-panel-header J-panel-header">
                        <a href="" class="title"><i></i><em class="title">购物车</em></a>
                        <span class="close-panel J-close" onclick="cartPanelView.tbar_panel_close('cart');"></span>
                    </h3>
                    <div class="tbar-panel-main">
                        <div class="tbar-panel-content J-panel-content">
                            <div id="J-cart-tips" class="tbar-tipbox hide">
                                <div class="tip-inner">
                                    <span class="tip-text">还没有登录，登录后商品将被保存</span>
                                    <a href="#none" class="tip-btn J-login">登录</a>
                                </div>
                            </div>
                            <div id="J-cart-render">
                                <!-- 列表 -->
                                <div id="cart-list" class="tbar-cart-list">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 小计 -->
                    <div id="cart-footer" class="tbar-panel-footer J-panel-footer">
                        <div class="tbar-checkout">
                            <div class="jtc-number"><strong class="J-count" id="cart-number">0</strong>件商品</div>
                            <div class="jtc-sum"> 共计：<strong class="J-total" id="cart-sum">¥0</strong></div>
                            <a class="jtc-btn J-btn" href="#none" target="_blank">去购物车结算</a>
                        </div>
                    </div>
                </div>

                <!-- 我的关注 -->
                <div style="visibility: hidden;" data-name="follow" class="J-content toolbar-panel tbar-panel-follow">
                    <h3 class="tbar-panel-header J-panel-header">
                        <a href="#" target="_blank" class="title"> <i></i> <em class="title">我的关注</em> </a>
                        <span class="close-panel J-close" onclick="cartPanelView.tbar_panel_close('follow');"></span>
                    </h3>
                    <div class="tbar-panel-main">
                        <div class="tbar-panel-content J-panel-content">
                            <div class="tbar-tipbox2">
                                <div class="tip-inner"><i class="i-loading"></i></div>
                            </div>
                        </div>
                    </div>
                    <div class="tbar-panel-footer J-panel-footer"></div>
                </div>

                <!-- 我的足迹 -->
                <div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-history toolbar-animate-in">
                    <h3 class="tbar-panel-header J-panel-header">
                        <a href="#" target="_blank" class="title"> <i></i> <em class="title">我的足迹</em> </a>
                        <span class="close-panel J-close" onclick="cartPanelView.tbar_panel_close('history');"></span>
                    </h3>
                    <div class="tbar-panel-main">
                        <div class="tbar-panel-content J-panel-content">
                            <div class="jt-history-wrap">
                                <ul>
                                    <!--<li class="jth-item">
                                        <a href="#" class="img-wrap"> <img src=".portal/img/like_03.png" height="100" width="100" /> </a>
                                        <a class="add-cart-button" href="#" target="_blank">加入购物车</a>
                                        <a href="#" target="_blank" class="price">￥498.00</a>
                                    </li>
                                    <li class="jth-item">
                                        <a href="#" class="img-wrap"> <img src="portal/img/like_02.png" height="100" width="100" /></a>
                                        <a class="add-cart-button" href="#" target="_blank">加入购物车</a>
                                        <a href="#" target="_blank" class="price">￥498.00</a>
                                    </li>-->
                                </ul>
                                <a href="#" class="history-bottom-more" target="_blank">查看更多足迹商品 &gt;&gt;</a>
                            </div>
                        </div>
                    </div>
                    <div class="tbar-panel-footer J-panel-footer"></div>
                </div>

            </div>

            <div class="toolbar-header"></div>

            <!-- 侧栏按钮 -->
            <div class="toolbar-tabs J-tab">
                <div onclick="cartPanelView.tabItemClick('cart')" class="toolbar-tab tbar-tab-cart" data="购物车"
                     tag="cart">
                    <i class="tab-ico"></i>
                    <em class="tab-text"></em>
                    <span class="tab-sub J-count " id="tab-sub-cart-count">0</span>
                </div>
                <div onclick="cartPanelView.tabItemClick('follow')" class="toolbar-tab tbar-tab-follow" data="我的关注"
                     tag="follow">
                    <i class="tab-ico"></i>
                    <em class="tab-text"></em>
                    <span class="tab-sub J-count hide">0</span>
                </div>
                <div onclick="cartPanelView.tabItemClick('history')" class="toolbar-tab tbar-tab-history" data="我的足迹"
                     tag="history">
                    <i class="tab-ico"></i>
                    <em class="tab-text"></em>
                    <span class="tab-sub J-count hide">0</span>
                </div>
            </div>

            <div class="toolbar-footer">
                <div class="toolbar-tab tbar-tab-top"><a href="#"> <i class="tab-ico  "></i> <em
                        class="footer-tab-text">顶部</em> </a></div>
                <div class="toolbar-tab tbar-tab-feedback"><a href="#" target="_blank"> <i class="tab-ico"></i> <em
                        class="footer-tab-text ">反馈</em> </a></div>
            </div>

            <div class="toolbar-mini"></div>

        </div>

        <div id="J-toolbar-load-hook"></div>

    </div>
</div>
<!--购物车单元格 模板-->
<script type="text/template" id="tbar-cart-item-template">
    <div class="tbar-cart-item">
        <div class="jtc-item-promo">
            <em class="promo-tag promo-mz">满赠<i class="arrow"></i></em>
            <div class="promo-text">已购满600元，您可领赠品</div>
        </div>
        <div class="jtc-item-goods">
            <span class="p-img"><a href="#" target="_blank"><img src="{2}" alt="{1}" height="50" width="50"/></a></span>
            <div class="p-name">
                <a href="#">{1}</a>
            </div>
            <div class="p-price"><strong>¥{3}</strong>×{4}</div>
            <a href="#none" class="p-del J-del">删除</a>
        </div>
    </div>
</script>
<!--侧栏面板结束-->


<script type="text/javascript" src="js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#service").hover(function () {
            $(".service").show();
        }, function () {
            $(".service").hide();
        });
        $("#shopcar").hover(function () {
            $("#shopcarlist").show();
        }, function () {
            $("#shopcarlist").hide();
        });

    })
</script>
<script type="text/javascript" src="js/model/cartModel.js"></script>
<script type="text/javascript" src="js/plugins/jquery.easing/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/plugins/sui/sui.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.jqzoom/jquery.jqzoom.js"></script>
<script type="text/javascript" src="js/plugins/jquery.jqzoom/zoom.js"></script>
<script type="text/javascript" src="index/index.js"></script>
</body>

</html>
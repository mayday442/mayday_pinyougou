package entity;

import com.pinyougou.pojo.*;

import java.util.List;

/**
 * @author mayday
 */
public class GoodsEditBean {

    private TbGoods goods;

    private TbGoodsDesc goodsDesc;

    private List<TbItem> itemList;

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GoodsEditBean{");
        sb.append("goods=").append(goods);
        sb.append(", goodsDesc=").append(goodsDesc);
        sb.append(", itemList=").append(itemList);
        sb.append('}');
        return sb.toString();
    }
}

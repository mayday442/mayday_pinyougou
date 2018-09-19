package com.pyg.pojo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author mayday
 */
public class PageResult<T> extends PageInfo<T> implements Serializable {

    private int start;
    private int end;


    public PageResult(List<T> list) {
        super(list);
    }

    private void dynamicPaging() {

        if (getPages() < 10) {
            start = 1;
            end = getPages();
        } else {

            start = getPageNum() - 4;
            end = getPageNum() + 5;

            if (start < 1) {
                start = 1;
                end = 10;
            }
            if (end > getPages()) {
                end = getPages();
                start = getPages() - 9;
            }
        }

    }

    public int getStart() {
        dynamicPaging();
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        dynamicPaging();
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageResult{");
        sb.append("start=").append(start);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }
}

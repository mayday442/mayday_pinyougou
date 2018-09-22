package entity;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author mayday
 */
public class PageResult<T> extends PageInfo<T> implements Serializable {




    public PageResult(List<T> list) {
        super(list);
    }

    public PageResult(Long total, List<T> list) {
        super(list);
    }

}

package com.pyg.pojo;

import java.io.Serializable;

/**
 * @author mayday
 */
public class TbBrand implements Serializable {

    private long id;
    private String name;
    private String firstChar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TbBrand{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", firstChar='").append(firstChar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

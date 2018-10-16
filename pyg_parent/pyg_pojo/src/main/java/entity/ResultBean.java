package entity;

import java.io.Serializable;

/**
 * @author mayday
 */
public class ResultBean<T> implements Serializable {


    private boolean success;


    private String message;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultBean(T data) {

        this.data = data;
    }


    public ResultBean(boolean success) {
        this.success = success;
    }

    public ResultBean(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    public ResultBean(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


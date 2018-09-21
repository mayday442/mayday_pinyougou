package com.pyg.entity;

import java.io.Serializable;

/**
 * @author mayday
 */
public class PygResult implements Serializable {


    private boolean success;


    private String message;

    public PygResult(boolean success) {
        this.success = success;
    }

    public PygResult(boolean success, String message) {
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


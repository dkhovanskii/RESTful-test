package com.silverrailtech.entity.request;

/**
 * Created by d on 6/01/17.
 */
public class AddSessionRequest {
    private String broserId;
    private String state;

    public String getBroserId() {
        return broserId;
    }

    public void setBroserId(String broserId) {
        this.broserId = broserId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

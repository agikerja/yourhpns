package com.ta.yourhpns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileModel {
    @SerializedName("user")
    @Expose
    private Boolean user;
    @SerializedName("status")
    @Expose
    private String status;

    public UserProfileModel(Boolean user, String status) {
        this.user = user;
        this.status = status;
    }

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

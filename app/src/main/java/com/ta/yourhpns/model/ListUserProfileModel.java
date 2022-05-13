package com.ta.yourhpns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUserProfileModel {
    @SerializedName("result")
    @Expose
    private List<ListUserProfile> listUserProfile = null;
    @SerializedName("status")
    @Expose
    private String status;

    public ListUserProfileModel(List<ListUserProfile> listUserProfile, String status) {
        this.listUserProfile = listUserProfile;
        this.status = status;
    }

    public List<ListUserProfile> getListUserProfile() {
        return listUserProfile;
    }

    public void setListUserProfile(List<ListUserProfile> listUserProfile) {
        this.listUserProfile = listUserProfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

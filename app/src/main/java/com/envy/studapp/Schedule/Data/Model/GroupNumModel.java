package com.envy.studapp.Schedule.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ENVY on 07.06.2017.
 */

public class GroupNumModel {

    @SerializedName("groupId")
    String groupId;

    @SerializedName("groupName")
    String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

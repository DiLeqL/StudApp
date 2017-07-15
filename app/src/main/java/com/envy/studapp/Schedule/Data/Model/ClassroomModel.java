package com.envy.studapp.Schedule.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ENVY on 07.06.2017.
 */

public class ClassroomModel {

    @SerializedName("roomId")
    int roomId;

    @SerializedName("roomNum")
    String roomNum;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}

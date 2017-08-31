package com.envy.studapp.FirstLaunch;

/**
 * Created by ENVY on 31.08.2017.
 */

public class GroupSelectEvent {

    private String selectedGroup;

    public GroupSelectEvent(String selectedGroup){
        this.selectedGroup = selectedGroup;
    }

    public String getSelectedGroup(){
        return selectedGroup;
    }
}

package org.changken.careapp.datamodels;

import java.util.List;

public class Division {

    private Integer div_id = null;
    private String div_name;
    private List<String> subDiv_id;

    public Division(String div_name, List<String> subDiv_id) {
        this.div_name = div_name;
        this.subDiv_id = subDiv_id;
    }

    public Division() {
        //不做事
    }

    public int getDiv_id() {
        return div_id;
    }

    public String getDiv_name() {
        return div_name;
    }

    public void setDiv_name(String div_name) {
        this.div_name = div_name;
    }

    public List<String> getSubDiv_id() {
        return subDiv_id;
    }

    public void setSubDiv_id(List<String> subDiv_id) {
        this.subDiv_id = subDiv_id;
    }
}

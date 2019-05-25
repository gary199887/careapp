package org.changken.careapp.datamodels;

import java.util.List;

public class SubDivision {

    private String subDiv_name;
    private int subDiv_id;
    private List<String> div_id;
    private List<String> doc_id;

    public SubDivision(String subDiv_name, List<String> div_id, List<String> doc_id) {
        this.subDiv_name = subDiv_name;
        this.div_id = div_id;
        this.doc_id = doc_id;
    }

    public SubDivision() {
        //不做事
    }

    public String getSubDiv_name() {
        return subDiv_name;
    }

    public void setSubDiv_name(String subDiv_name) {
        this.subDiv_name = subDiv_name;
    }

    public int getSubDiv_id() {
        return subDiv_id;
    }

    public List<String> getDiv_id() {
        return div_id;
    }

    public void setDiv_id(List<String> div_id) {
        this.div_id = div_id;
    }

    public List<String> getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(List<String> doc_id) {
        this.doc_id = doc_id;
    }
}

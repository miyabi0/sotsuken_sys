package com.example.sotsuken_sys.entity;

public class TagBean {
    private int tag_id;
    private String tag_name;
    private int cnt_tag;
    public int getTag_id() {
        return tag_id;
    }
    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
    public String getTag_name() {
        return tag_name;
    }
    public void settTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
    public int getCnt_tag() {
        return cnt_tag;
    }
    public void setCnt_tag(int cnt_tag) {
        this.cnt_tag = cnt_tag;
    }
}

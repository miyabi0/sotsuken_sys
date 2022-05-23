package com.example.sotsuken_sys.entity;

public class SPBean {
    private int sp_id;
    private String sp_theme;
    private String sp_year;
    private  String sp_overview;
    private String sp_file;

    public int getSp_id() {
        return sp_id;
    }

    public void setSp_id(int sp_id) {
        this.sp_id = sp_id;
    }

    public String getSp_theme() {
        return sp_theme;
    }

    public void setSp_theme(String sp_theme) {
        this.sp_theme = sp_theme;
    }

    public String getSp_year() {
        return sp_year;
    }

    public void setSp_year(String sp_year) {
        this.sp_year = sp_year;
    }

    public String getSp_overview() {
        return sp_overview;
    }

    public void setSp_overview(String sp_overview) {
        this.sp_overview = sp_overview;
    }

    public String getSp_file() {
        return sp_file;
    }

    public void setSp_file(String sp_file) {
        this.sp_file = sp_file;
    }
}

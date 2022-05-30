package com.example.sotsuken_sys.dao;

import com.example.sotsuken_sys.entity.SPBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SPDAO {
    private Connection con = null;
    /**
     * 指定したcheck_tagsの卒研を返す。
     *
     * @param check_tags
     * @return List<SPBean>
     */
    public List<SPBean> research(String[] check_tags){
        List<SPBean> spList = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        ConnectionManager cm = new ConnectionManager();
        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();
            for (String check_tag : check_tags) {

                // SELECT文の準備
                String sql = "SELECT seniorproject.sp_id,sp_theme,sp_overview " +
                        "FROM seniorproject,tagmap WHERE seniorproject.sp_id = tagmap.sp_id AND tag_id = ?;";
                pStmt = con.prepareStatement(sql);

                pStmt.setString(1, check_tag);

                // SELECTを実行
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    integers.add(rs.getInt("sp_id"));
                    int cnt = Collections.frequency(integers, rs.getInt("sp_id"));
                    if (cnt == check_tags.length) {
                        SPBean spBean = new SPBean();
                        spBean.setSp_id(rs.getInt("sp_id"));
                        spBean.setSp_theme(rs.getString("sp_theme"));
                        spBean.setSp_overview(rs.getString("sp_overview"));
                        spList.add(spBean);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        cm.finallyBlock(pStmt);
    }
        return spList;
    }
    /**
     * 指定したsp_idの卒研情報を返す。
     *
     * @param sp_id
     * @return List<String>
     */
    public SPBean details(int sp_id){
        SPBean spBean = new SPBean();
        ConnectionManager cm = new ConnectionManager();
        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();
            // SELECT文の準備
            String sql = "SELECT * FROM seniorproject WHERE sp_id = ?;";
            pStmt = con.prepareStatement(sql);

            pStmt.setInt(1, sp_id);

            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                spBean.setSp_id(sp_id);
                spBean.setSp_theme(rs.getString("sp_theme"));
                spBean.setSp_year(rs.getString("sp_year"));
                spBean.setSp_overview(rs.getString("sp_overview"));
                spBean.setSp_file(rs.getString("sp_file"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cm.finallyBlock(pStmt);
    }
        return spBean;
    }
    public boolean add(String theme,String year,String overview,String filename){
        boolean is_success = true;
        ConnectionManager cm = new ConnectionManager();

        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();

            // SELECT文の準備
            String sql = "INSERT INTO seniorproject(sp_theme,sp_year,sp_overview,sp_file) VALUES (?,?,?,?);";
            pStmt = con.prepareStatement(sql);

            pStmt.setString(1, theme);
            pStmt.setString(2, year);
            pStmt.setString(3, overview);
            pStmt.setString(4, filename);

            pStmt.executeUpdate();
        } catch (SQLException e) {
            is_success = false;
            e.printStackTrace();
        }
        finally {
            cm.finallyBlock(pStmt);
        }
        return is_success;
    }
}
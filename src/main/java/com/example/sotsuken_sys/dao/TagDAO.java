package com.example.sotsuken_sys.dao;

import com.example.sotsuken_sys.entity.TagBean;
import com.example.sotsuken_sys.entity.UserBean;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {
    private Connection con = null;


    /**
     * タグ一覧を返す。
     *
     * @return List<String>
     */
    public List<TagBean> show() {
        List<TagBean> tagList = new ArrayList<TagBean>();
        ConnectionManager cm = new ConnectionManager();
        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();
            // SELECT文の準備
            String sql = "SELECT tag.tag_id,tag.tag_name,count(*) AS cnt " +
                    "FROM tag JOIN tagmap ON tag.tag_id = tagmap.tag_id " +
                    "GROUP BY tag_id " +
                    "ORDER BY cnt DESC;";
            pStmt = con.prepareStatement(sql);
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                TagBean tagBean = new TagBean();
                tagBean.setTag_id(rs.getInt("tag_id"));
                tagBean.settTag_name(rs.getString("tag_name"));
                tagBean.setCnt_tag(rs.getInt("cnt"));
                tagList.add(tagBean);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tagList;

    }
    /**
     * 指定したsp_idのカラムのタグ名を調べ、タグのリストを返す。
     *
     * @param sp_id
     * @return List<String>
     */
    public List<String> tagName(int sp_id) {

        List<String> tagNameList = new ArrayList<String>();

        ConnectionManager cm = new ConnectionManager();

        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();

            // SELECT文の準備
            String sql = "SELECT tag.tag_name FROM tagmap,tag  WHERE tagmap.tag_id = tag.tag_id AND sp_id = ?;";
            pStmt = con.prepareStatement(sql);

            pStmt.setInt(1, sp_id);

            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            // SELECT文の結果をArrayListに格納
            while (rs.next()) {
                tagNameList.add(rs.getString("tag_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tagNameList;
    }
}
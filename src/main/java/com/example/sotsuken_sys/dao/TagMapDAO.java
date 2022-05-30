package com.example.sotsuken_sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagMapDAO {
    private Connection con = null;
    public boolean add(int tag_id) {
        boolean is_success = true;
        ConnectionManager cm = new ConnectionManager();

        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();

            // SELECT文の準備
            String sql = "INSERT INTO tagmap(sp_id,tag_id) VALUES (LAST_INSERT_ID(),?);";
            pStmt = con.prepareStatement(sql);

            pStmt.setInt(1, tag_id);

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
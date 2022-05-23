package com.example.sotsuken_sys.dao;

import com.example.sotsuken_sys.entity.UserBean;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    //コネクション情報を格納する
    private Connection con = null;

    /**
     * DBからuser_idとuser_pwdが一致してたらtrueを返すログインメソッド
     *
     * @param user_id, user_pwd
     * @return true or false : boolean
     */
    public boolean login(String user_id, String user_pwd) {

        UserBean ubean = new UserBean();

        boolean is_success = false;

        ConnectionManager cm = new ConnectionManager();
        PreparedStatement pStmt = null;

        try {
            con = cm.getConnection();

            // SELECT文の準備
            String sql = "SELECT user_id,user_pwd FROM sotsuken_system.loginuser where user_id = ?";
            pStmt = con.prepareStatement(sql);

            pStmt.setString(1, user_id);

            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            // SELECT文の結果をArrayListに格納
            if (rs.next()) { // 万が一複数件取得できても、最初の一件を返却する
                ubean.setUser_id(rs.getString("user_id"));
                ubean.setUser_pwd(rs.getString("user_pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
        // 画面で入力されたパスワードと、DB内のパスワードが一致しているか判定
        String sha256_pass = DigestUtils.sha256Hex(user_pwd);
        if (ubean.getUser_id() != null || ubean.getUser_pwd() != null) {
            if (ubean.getUser_id().equals(user_id) && ubean.getUser_pwd().equals(sha256_pass)) {
                is_success = true;
            }
        }
        return is_success;
    }
    /**
     * 指定したuser_idのpermissionを調べて1だったらtrueを返す。
     *
     * @param user_id
     * @return true or false : boolean
     */
    public boolean permission_check(String user_id) {
        ConnectionManager cm = new ConnectionManager();
        boolean admin_flag = false;
        int permission_num = 0;

        try {
            con = cm.getConnection();

            // SELECT文の準備
            String sql = "SELECT permission FROM sotsuken_system.loginuser where user_id = ?;";
            PreparedStatement pStmt = con.prepareStatement(sql);

            pStmt.setString(1, user_id);

            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                permission_num = rs.getInt("permission");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if (permission_num == 1){
            admin_flag = true;
        }
        return admin_flag;
    }
}

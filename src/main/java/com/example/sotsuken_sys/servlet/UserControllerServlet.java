package com.example.sotsuken_sys.servlet;

import com.example.sotsuken_sys.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;

import static com.example.sotsuken_sys.util.ValidationCheck.*;


@WebServlet(name = "UserControllerServlet", value = "/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {

    /**
     * ログイン処理
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //エラーメッセージ変数
        String errorMessage = "";

        //セッション情報
        HttpSession session = request.getSession();

        //ログインしたかどうかを保持する変数
        boolean login_success = false;

        //ログイン入力情報をパラメータから変数に保持
        String user_id = request.getParameter("user_id");
        String user_pwd = request.getParameter("user_pwd");

        if (emptyCheck(user_id)||emptyCheck(user_pwd)) {
            errorMessage = "IDまたはパスワードが未入力です。";
        } else if (strCheck(user_id)||strCheck(user_pwd)) {
            errorMessage = "IDまたはパスワードが不正です。";
        } else {
            UserDAO udao = new UserDAO();
            if (udao.login(user_id, user_pwd)) {

                //ログイン成功時
                login_success = true;

                session.setAttribute("user_id", user_id);

            } else {
                errorMessage = "認証に失敗しました。<br>ID、パスワードを確認してください。";
            }
        }
        if (!login_success) {
            user_id = null;
            session.setAttribute("user_id", user_id);
        }
        //セッションにlogin_session変数を保持する
        session.setAttribute("login_session", login_success);
        if (errorMessage != null && errorMessage.length() > 0) {
            //セッションにerrorMessage変数を保持する
            session.setAttribute("errorMessage", errorMessage);
        }
        return login_success;
    }


    /**
     * ユーザーの権限を確認する
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean  permission_check(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //管理者かどうかを保持する変数
        boolean permission_flag = false;

        String user_id = request.getParameter("user_id");

        UserDAO udao = new UserDAO();

        if (udao.permission_check(user_id)) {
            permission_flag = true;
        }
        return permission_flag;
    }

    /**
     * ログアウト処理
     * @param request
     * @param response
     * @return
     */
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        boolean logout_success = false;
        //requestからセッションを取得する
        HttpSession session = request.getSession();
        //セッションにある全ての要素名を取得する
        Enumeration<String> vals = session.getAttributeNames();
        //取得した要素名をループ処理で全て削除する
        while (vals.hasMoreElements()) {
            String nm = (String) vals.nextElement();
            session.removeAttribute(nm);
        }
        return true;
    }
}

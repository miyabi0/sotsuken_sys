package com.example.sotsuken_sys.servlet;

import com.example.sotsuken_sys.dao.TagDAO;
import com.example.sotsuken_sys.entity.TagBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TagControllerServlet", value = "/TagControllerServlet")
public class TagControllerServlet extends HttpServlet {
    /**
     * タグの一覧表示
     * @param request
     * @param response
     * @return
     */
    public boolean show(HttpServletRequest request, HttpServletResponse response) {
        //エラー画面に表示するメッセージ変数
        String errorMessage = "";

        //セッション情報
        HttpSession session = request.getSession();

        // 処理が成功したかを示す値
        boolean is_success = false;
        TagDAO tDAO = new TagDAO();
        List<TagBean> tagList = tDAO.show();
        if (tagList != null) {
            request.setAttribute("tagList", tagList);
            is_success = true;
        } else {
            errorMessage = "タグ情報の取得に失敗しました。";
        }
        if (errorMessage != null && errorMessage.length() > 0) {
            session.setAttribute("errorMessage", errorMessage);
        }
        return is_success;
    }

    /**
     * タグ名取得
     * @param request
     * @param response
     * @return
     */
    public boolean tagName(HttpServletRequest request, HttpServletResponse response) {
        //エラー画面に表示するメッセージ変数
        String errorMessage = "";

        //セッション情報
        HttpSession session = request.getSession();

        int sp_id = Integer.parseInt(request.getParameter("sp_id"));

        // 処理が成功したかを示す値
        boolean is_success = false;
        TagDAO tDAO = new TagDAO();
        List<String> tagNameList = tDAO.tagName(sp_id);
        if (tagNameList != null) {
            request.setAttribute("tagNameList", tagNameList);
            is_success = true;
        } else {
            errorMessage = "タグ名の取得に失敗しました。";
        }
        if (errorMessage != null && errorMessage.length() > 0) {
            session.setAttribute("errorMessage", errorMessage);
        }
        return is_success;
    }
}

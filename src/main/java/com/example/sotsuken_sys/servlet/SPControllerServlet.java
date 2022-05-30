package com.example.sotsuken_sys.servlet;

import com.example.sotsuken_sys.dao.SPDAO;
import com.example.sotsuken_sys.dao.TagDAO;
import com.example.sotsuken_sys.dao.TagMapDAO;
import com.example.sotsuken_sys.entity.SPBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SPControllerServlet", value = "/SPControllerServlet")
public class SPControllerServlet extends HttpServlet {
    /**
     * 卒研情報を表示する
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean result(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //エラーメッセージ変数
        String errorMessage = "";

        //セッション情報
        HttpSession session = request.getSession();

        // 処理が成功したかを示す値
        boolean is_success = false;
        String check_tags[] = request.getParameterValues("check_tags");
        if (check_tags != null) {
            SPDAO spdao = new SPDAO();
            List<SPBean> spList = spdao.research(check_tags);
            if (spList != null ) {
                System.out.println(spList);
                request.setAttribute("spList", spList);
                is_success = true;
            }
            else {
                errorMessage = "卒研情報の取得に失敗しました。";
            }

        }
        else {
            errorMessage = "タグを選択してください。";
        }
        if (errorMessage != null && errorMessage.length() > 0) {
            session.setAttribute("errorMessage", errorMessage);
        }
        return is_success;

    }

    /**
     * 卒研の詳細を表示する
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean details(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //エラーメッセージ変数
        String errorMessage = "";

        //セッション情報
        HttpSession session = request.getSession();

        // 処理が成功したかを示す値
        boolean is_success = false;

        int sp_id = Integer.parseInt(request.getParameter("sp_id"));

        SPDAO spdao = new SPDAO();
        SPBean spBean = spdao.details(sp_id);
        if (spBean != null) {
            request.setAttribute("spBean", spBean);
            is_success = true;
        } else {
            errorMessage = "卒研情報の取得に失敗しました。";
        }
        if (errorMessage != null && errorMessage.length() > 0) {
            session.setAttribute("errorMessage", errorMessage);
        }
        return is_success;

    }
    public boolean add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //エラーメッセージ変数
        String errorMessage = "";

        //セッション情報
        HttpSession session = request.getSession();

        // 処理が成功したかを示す値
        boolean is_success = false;
        String theme = request.getParameter("theme");
        String year = request.getParameter("year");
        String[] checkedTags = request.getParameterValues("checkbox");
        String overview = request.getParameter("overview");
        Part part = request.getPart("file");
        String fileName = this.getFileName(part);
        part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + fileName);
        SPDAO spdao = new SPDAO();
        if (spdao.add(theme,year,overview,fileName)){
            for (String tag :checkedTags) {
                TagMapDAO tagMapDAO = new TagMapDAO();
                if (tagMapDAO.add(Integer.parseInt(tag))){
                    is_success = true;
                }else{
                    errorMessage="タグ情報の登録に失敗しました。";
                }

            }
        }else {
            errorMessage="卒業研究の登録に失敗しました。";
        }
        if (errorMessage != null && errorMessage.length() > 0) {
            session.setAttribute("errorMessage", errorMessage);
        }
        return is_success;
    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}

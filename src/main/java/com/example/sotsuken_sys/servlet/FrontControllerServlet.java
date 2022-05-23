package com.example.sotsuken_sys.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.sotsuken_sys.util.ValidationCheck.emptyCheck;

@WebServlet(name = "FrontController", value = "/FrontController")
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストのエンコーディング指定
        request.setCharacterEncoding("utf-8");

        //画面遷移する場所を入れる
        String rdstr = "error.jsp";

        String operation = request.getParameter("operation");

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        boolean islogin = true;
        if (!emptyCheck(user_id)) {
            if (operation.equals("search")) {
                SPControllerServlet spc = new SPControllerServlet();
                if (spc.result(request, response)) {
                    rdstr = "result.jsp";
                }

            } else if (operation.equals("details")) {
                SPControllerServlet spc = new SPControllerServlet();
                TagControllerServlet tc = new TagControllerServlet();
                if (spc.details(request, response)&&tc.tagName(request, response)) {
                    rdstr = "details.jsp";
                }

            } else if (operation.equals("logout")) {
                UserControllerServlet uc = new UserControllerServlet();
                if (uc.logout(request, response)) {
                    islogin = false;
                    rdstr = "index.jsp";
                }
            }
        }
        else {
            if (operation.equals("login")) {
                UserControllerServlet uc = new UserControllerServlet();
                /** ログイン処理 */
                if (uc.login(request, response)) {
                    if (uc.permission_check(request, response)) {
                        rdstr = "menu.jsp";
                    } else {
                        TagControllerServlet tc = new TagControllerServlet();
                        if (tc.show(request, response)) {
                            rdstr = "search.jsp";
                        }
                    }
                } else {
                    islogin = false;
                    rdstr = "index.jsp";
                }
            }
        }
        if (islogin){
            request.getRequestDispatcher(rdstr).forward(request, response);
        } else {
            response.sendRedirect(rdstr);
        }
    }
}

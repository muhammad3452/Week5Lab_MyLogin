/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;

/**
 *
 * @author muham
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //String login = request.getParameter("login");
        String logout = (String) request.getParameter("logout");

        if (logout != null) {
            session.invalidate();
            request.setAttribute("message", "The user has successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        session.setAttribute("password", password);
        AccountService account = new AccountService();

        if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute("message", "Pleaase enter the username and password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

        }   
        else if (account.login(username, password) != null) {             
                session.setAttribute("username", username);
               response.sendRedirect("home");

            
                
        }
        else {
           request.setAttribute("message", "invalid username or password");
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

           
        }
                
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Neel
 */
public class AddQ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession se=request.getSession(false);
            if(se==null || !request.isRequestedSessionIdValid() )
            {   
                out.println("<div class=\"alert alert-warning alert-dismissible\">Invalid Session. Please Login First</div>");
                RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
                requestdispatcher.include(request,response);
            }
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"    <head> \n" +
"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"		<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
"                <link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
"		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>\n" +
"		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>\n" +
"                <link rel=\"stylesheet\" href=\"./Project_Web/bootstrap/css/main_1.css\" type=\"text/css\"/>\n" +
"		<title>Add</title>\n" +
"	</head>\n" +
"	<body>\n" +
                    "<div class=\"alert alert-warning alert-dismissible\">Welcome, Admin.</div>"+
"            <a href=\"../Servlet/Logout\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a>\n" +
"            <div class=\"container\">\n" +
"            <div class=\"panel-heading\">\n" +
"                <div class=\"panel-title text-center\">\n" +
"	            <h1>Add Question</h1>\n" +
"	            <hr />\n" +
"	        </div>\n" +
"	    </div>             \n" +
"            <div class=\"main-login main-center\">\n" +
"		<form class=\"form-horizontal\" method=\"post\" action=\"../Servlet/Add\">\n" +
"                    <div class=\"form-group\">\n" +
"			<label for=\"name\" class=\"cols-sm-2 control-label\">&nbsp;&nbsp;&nbsp;&nbsp;Subject : </label>\n" +
"				<select name=\"subject\">\n" +
"                                    <option value=\"j1\">Java</option>\n" +
"                                    <option value=\"c1\">C</option>\n" +
"                                    <option value=\"c2\">C++</option>\n" +
"                                </select>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"			<label for=\"question\" class=\"cols-sm-2 control-label\">&nbsp;&nbsp;&nbsp;&nbsp;Question : </label>\n" +
"			<div class=\"cols-sm-10\">\n" +
"                            <div class=\"input-group\">\n" +
"                                &nbsp;<textarea class=\"form-control\" name=\"question\" placeholder=\"Enter your Question\" style=\"left: 15px\"></textarea>\n" +
"			</div>\n" +
"			</div>\n" +
"                    </div>\n" +
"                    <label class=\"cols-sm-2 control-label\">Options : (select the correct option) </label>\n" +
"                         <div class=\"funkyradio\">\n" +
"				<div class=\"funkyradio-success\">\n" +
"                                    <input type=\"radio\" name=\"opc\" id=\"radio1\" value=\"op1\"/>\n" +
"                                    <label for=\"radio1\"><input type=\"text\" class=\"form-control\" name=\"option1\" placeholder=\"Option 1\" style=\"text-indent: 30px\"/></label>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        <div class=\"funkyradio\">\n" +
"				<div class=\"funkyradio-success\">\n" +
"                                    <input type=\"radio\" name=\"opc\" id=\"radio2\" value=\"op2\"/>\n" +
"                                    <label for=\"radio2\"><input type=\"text\" class=\"form-control\" name=\"option2\" placeholder=\"Option 2\" style=\"text-indent: 30px\"/></label>\n" +
"                                </div>\n" +
"                        </div>\n" +
"                        <div class=\"funkyradio\">\n" +
"				<div class=\"funkyradio-success\">\n" +
"                                    <input type=\"radio\" name=\"opc\" id=\"radio3\" value=\"op3\"/>\n" +
"                                    <label for=\"radio3\"><input type=\"text\" class=\"form-control\" name=\"option3\" placeholder=\"Option 3\" style=\"text-indent: 30px\"/></label>\n" +
"                                </div>\n" +
"                         </div>\n" +
"                        <div class=\"funkyradio\">\n" +
"				<div class=\"funkyradio-success\">\n" +
"                                    <input type=\"radio\" name=\"opc\" id=\"radio4\" value=\"op4\"/>\n" +
"                                    <label for=\"radio4\"><input type=\"text\" class=\"form-control\" name=\"option4\" placeholder=\"Option 4\" style=\"text-indent: 30px\"/></label>\n" +
"                                </div>\n" +
"                        </div>\n" +
"                    <div class=\"form-group \">\n" +
"			<button type=\"submit\" class=\"btn btn-primary btn-lg btn-block login-button\">Add</button>\n" +
"                        <button type=\"reset\" class=\"btn btn-primary btn-lg btn-block login-button\">Reset</button>\n" +
"                    </div>\n" +
"		</form>\n" +
"            </div>\n" +
"		<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.js\"></script>\n" +
"                </div>\n" +
"	</body>\n" +
"</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


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
public class QuizSelection extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession se=request.getSession(false);           
            if(se==null || !request.isRequestedSessionIdValid() )
            {   
                out.println("<div class=\"alert alert-warning\">Invalid Session. Please Login First</div>");
                RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
                requestdispatcher.include(request,response);
            }
            else
            {  
                out.println("<div class=\"alert alert-warning nopadding\">Welcome, "+(String)se.getAttribute("uname")+"</div>");
            }
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"    <head> \n" +
"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"		<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
"                <link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
"		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>\n" +
"		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>\n" +
"                <link rel=\"stylesheet\" href=\"./Project_Web/bootstrap/css/main.css\" type=\"text/css\"/>\n" +
"                <title>Quize Selection</title>\n" +
"	</head>\n" +
"	<body>\n" +
"            <a href=\"../Servlet/Logout\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a>\n" +
"            <div class=\"container col-lg-8 col-lg-offset-2\"> \n" +
"            \n" +
"            <div class=\"panel-heading\">\n" +
"                <div class=\"panel-title text-center center-block \">\n" +
"	            <h1>Quiz</h1>\n" +
"	            <hr />\n" +
"	        </div>\n" +
"	    </div> \n" +
"            <div class=\"main-login main-center\">\n" +
"                <form class=\"form-horizontal\" method=\"post\" action=\"../Servlet/QuizPage\">                 \n" +
"                    <div class=\"form-group \">\n" +
"                        <input type=\"submit\" class=\"btn btn-primary btn-lg btn-block login-button\" name=\"j1\" value=\"Java\">\n" +
"                        <input type=\"submit\" class=\"btn btn-primary btn-lg btn-block login-button\" name=\"c1\" value=\"C\">\n" +
"                        <input type=\"submit\" class=\"btn btn-primary btn-lg btn-block login-button\" name=\"c2\" value=\"C++\">\n" +
"                    </div>\n" +
"		</form>\n" +
"            </div>\n" +
"            </div>\n" +
"		<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.js\"></script>\n" +
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


package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class viewprev extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession se=request.getSession(false);           
            if(se==null || !request.isRequestedSessionIdValid() )
            {   
                out.println("<div class=\"alert alert-warning alert-dismissible\">Invalid Session. Please Login First</div>");
                RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
                requestdispatcher.include(request,response);
            }
            else
            {  
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");  
            PreparedStatement pstmtq=con.prepareStatement("select * from track where username=?");
            pstmtq.setString(1,(String)se.getAttribute("uname"));
            ResultSet rt=pstmtq.executeQuery();  
            String subject = null;   
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"    <head> \n" +
"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"		<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
"                <link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
"		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>\n" +
"		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>\n" +
"                <link rel=\"stylesheet\" href=\"./Project_Web/bootstrap/css/main.css\" type=\"text/css\"/>\n" +
"                <title>View</title>\n" +
"	</head>\n" +
"	<body>\n" +
"            <div class=\"row\"><a href=\"../Servlet/Logout\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a></div><div class=\"row\"><a href=\"../Servlet/QuizSelection\"><button class='btn btn-primary pull-right col-lg-1'>Back</button></a></div>\n" +
"            <div class=\"container col-lg-8 col-lg-offset-2\"> \n" +
"            \n" +
"            <div class=\"panel-heading\">\n" +
"                <div class=\"panel-title text-center center-block \">\n" +
"	            <h1>"+(String)se.getAttribute("uname")+"'s previous results.</h1>\n" +
"	            <hr />\n" +
"	        </div>\n" +
"	    </div> \n" +
"            <div class=\"main-login main-center\">\n" +
"               <table class=\"table table-striped\">\n" +
                        "<thead>\n" +
                        "<tr>\n" +
                        "<th>Score</td>\n" +
                        "<th>Attempted</td>\n" +
                        "<th>Subject</td>\n" +
                        "<th>Date</td>\n" +
                        "</tr>\n" +
                        "</thead>\n" +
                        "<tbody>\n");
                        while(rt.next())
                        {     
                            if(rt.getString("subject").equals("j1"))
                            {
                                subject="Java";
                            }
                            else if(rt.getString("subject").equals("j1"))
                            { 
                                subject="C";
                            }
                            else if(rt.getString("subject").equals("j1"))
                            {
                            subject="C++";
                            } 
                        out.println("<tr>\n" +
                        "<td>"+rt.getString("correct")+"</td>\n" +
                        "<td>"+rt.getString("attempt")+"/5</td>\n" +
                        "<td>"+subject+"</td>\n" +
                        "<td>"+rt.getString("date")+"</td>\n" +
                        "</tr>\n");
                                }
                        out.println("</tbody>\n" +
                        "</table>\n"+
"            </div>\n" +
"            </div>\n" +
"           <script src=\"./Project_Web/bootstrap/js/jquery.js\"></script>\n" +
"            <script type=\"text/javascript\" src=\"./Project_Web/bootstrap/js/bootstrap.js\"></script>\n" +
"	</body>\n" +
"</html>");
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(viewprev.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(viewprev.class.getName()).log(Level.SEVERE, null, ex);
        }
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

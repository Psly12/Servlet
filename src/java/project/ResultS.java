
package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultS extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String QnAns[][];
        QnAns=new String[5][2];
        int score = 0;
        int attempt = 5;
        for(int i=0;i<5;i++)
        {   
            String qn=request.getParameter("qn"+i);
            String opn=request.getParameter("op"+i);
            QnAns[i][0]=qn;
            QnAns[i][1]=opn;
            if(opn!=null)
                QnAns[i][1]=opn;
            else
                QnAns[i][1]="";
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");
            for(int i=0;i<5;i++)
            {
                if(QnAns[i][1].equals(""))
                {
                    attempt--;
                }
                else
                {
                    PreparedStatement pstmta=con.prepareStatement("select * from ans where qid=? and opid=?");
                    pstmta.setString(1,QnAns[i][0]);
                    pstmta.setString(2,QnAns[i][1]);
                    ResultSet rsa=pstmta.executeQuery();
                    if(rsa.next())
                    {
                        score++;
                    }
                }
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main.css\">\n");
            out.println("<title>Servlet ResultS</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"../Servlet/LoginCaptcha\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a>");
            out.println("<div class=\"container\">");
            out.println("Score :"+score+"<br>");
            out.println("Attempted :"+attempt);
            out.println("</div>");
            out.println("<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ResultS.class.getName()).log(Level.SEVERE, null, ex);
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

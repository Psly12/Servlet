/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String sub = request.getParameter("subject");
            String quest = request.getParameter("question");
            String op1=request.getParameter("option1");
            String op2=request.getParameter("option2");
            String op3=request.getParameter("option3");
            String op4=request.getParameter("option4");
            String correct=request.getParameter("opc");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main_1.css\">\n");
            /*if(sub.equals("")&&quest.equals("")&&op1.equals("")&&op2.equals("")&&op3.equals("")&&op4.equals("")&&correct.equals(""))
            {
               out.println("<div class=\"alert alert-warning alert-dismissible\">Please fill everything.</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("./Project_Web/AddQ.html");
               requestdispatcher.include(request,response);
            }
            else
            {
            */   
            out.println(sub+quest+op1+op2+op3+op4+correct);
            int pkey = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz", "root", "");
            PreparedStatement pstmtqn=con.prepareStatement("SELECT MAX(CAST((SUBSTRING(qid , 3)) as UNSIGNED)) AS max FROM `submast` where sid='"+sub+"'");        
            ResultSet rsq=pstmtqn.executeQuery();
            while(rsq.next()){
            pkey = Integer.parseInt(rsq.getString("max"));}           
            out.println(pkey);
            /*PreparedStatement stmtq;
            stmtq = con.prepareStatement("insert into questmast values (?,?)");
            stmtq.setString(1, name);
            stmtq.setString(2, email);
            int result=stmt.executeUpdate();
            if(result==1)
            {
               out.println("<div class=\"alert alert-warning alert-dismissible\">Registration Successfull, Please Login in using corrcect credentials</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
               requestdispatcher.include(request,response);
            }
            else
            {
                out.println("<div class=\"alert alert-warning alert-dismissible\">Registration Failed, please try again</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
               requestdispatcher.include(request,response);
            }*/
            con.close();
            /*}*/        
            out.println("<script src=\"./Project_Web/bootstrap/js/jquery.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"./Project_Web/bootstrap/js/bootstrap.js\"></script>");
        }
                catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);           
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

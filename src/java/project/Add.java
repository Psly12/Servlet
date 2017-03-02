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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String sub = request.getParameter("subject");
            String quest = request.getParameter("question");
            String[] abcd={"A - ","B - ","C - ","D - "};
            List<String> optList = new ArrayList<>();
            for (int i = 1; i <=4; i++) {
                if(!((request.getParameter("option"+i)).equals("")))
                {
                    optList.add(new String(abcd[i-1]+request.getParameter("option"+i)));
                }              
            }
            String correct=request.getParameter("opc");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main_1.css\">\n");
            if(optList.size()!=4||sub.equals("")||quest.equals("")||correct.equals(""))
            {
               out.println("<div class=\"alert alert-warning alert-dismissible\">Please fill everything.</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("AddQ");
               requestdispatcher.include(request,response);
            }
            else
            {
            Integer pkey = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz", "root", "");
            PreparedStatement max=con.prepareStatement("SELECT MAX(CAST((SUBSTRING(qid , 3)) as UNSIGNED)) AS max FROM `submast` where sid='"+sub+"'");        
            ResultSet mx=max.executeQuery();
            while(mx.next()){
            pkey = mx.getInt("max");}
            pkey++;
            String spkey=null;
            if(sub.equals("j1"))
            {
                spkey="jq"+pkey;
            }
            else if(sub.equals("c1"))
            {
                spkey="cq"+pkey;
            }
            else
            {
                spkey="c2q"+pkey;
            }
            PreparedStatement submast = con.prepareStatement("insert into submast values (?,?)");
            submast.setString(1,sub);
            submast.setString(2,spkey);
            int result1=submast.executeUpdate();
            if(result1==1)
            {
               PreparedStatement questmast = con.prepareStatement("insert into questmast values (?,?)");
               questmast.setString(1,spkey);
               questmast.setString(2,quest);
               int result2=questmast.executeUpdate();
               if(result2==1)
               {
                    int result3=0;
                    for (int i = 0; i <optList.size(); i++) {
                    PreparedStatement optmast = con.prepareStatement("insert into optmast values (?,?,?)");
                    optmast.setString(1,spkey);
                    optmast.setString(2,"op"+i);
                    optmast.setString(3,optList.get(i));           
                    result3=optmast.executeUpdate();
                   }
                   if(result3==1)
                   {
                        PreparedStatement ans = con.prepareStatement("insert into ans values (?,?)");
                        ans.setString(1,spkey);
                        ans.setString(2,correct);
                        int result4=ans.executeUpdate();
                        if(result4==1)
                        {
                            con.close();
                            out.println("<div class=\"alert alert-success alert-dismissible\">Addition Successfull!</div>");
                            RequestDispatcher requestdispatcher=request.getRequestDispatcher("AddQ");
                            requestdispatcher.include(request,response);
                        }
                   }                   
               }
            }
            else
            {
                out.println("<div class=\"alert alert-warning alert-dismissible\">Addition Failed</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("AddQ");
               requestdispatcher.include(request,response);
            }
            con.close();
            }        
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

package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subj = null;
        String subject = null;
        if(null != request.getParameter("j1"))
        {
            subj="j1";
            subject="Java";
        }
        else if(null != request.getParameter("c1"))
        { 
            subj="c1";
            subject="C";
        }
        else if(null != request.getParameter("c2"))
        {
            subj="c2";
            subject="C++";
        }       
        Integer arr[]={1,2,3,4,5};
        Collections.shuffle(Arrays.asList(arr));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");  
            PreparedStatement pstmtq=con.prepareStatement("select * from submast where sid=?");
            pstmtq.setString(1,subj);
            ResultSet rsq=pstmtq.executeQuery();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>"+subject+"</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            for(int i=0;i<5;i++)
            {
                rsq.absolute(arr[i]);
                PreparedStatement pstmtqn=con.prepareStatement("select * from questmast where qid=?");
                pstmtqn.setString(1,rsq.getString("qid"));
                ResultSet rsqn=pstmtqn.executeQuery();
                while(rsqn.next())
                {
                    out.println("Q"+(i+1)+") "+rsqn.getString("question")+"<br>");
                    PreparedStatement pstmtopt=con.prepareStatement("select * from optmast where qid=?");
                    pstmtopt.setString(1,rsq.getString("qid"));
                    ResultSet rsopt=pstmtopt.executeQuery();
                    while(rsopt.next())
                    {                       
                        out.println("<input type=\"radio\" name=\"q"+i+"\" value=\""+rsq.getString("qid")+rsopt.getString("opid")+"\">");
                        out.println(rsopt.getString("options")+"<br>");
                    }
                }
            }
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("<input type=\"reset\" value=\"Reset\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuizPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuizPage.class.getName()).log(Level.SEVERE, null, ex);
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

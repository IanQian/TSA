/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author qianshenglan
 */

public class SentenceServlet extends HttpServlet {
    
    int FILE_MAX_NUM=6;
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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session=request.getSession();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SentenceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SentenceServlet at " + session.getAttribute("Filename1") + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
      //processRequest(request,response);
      PrintWriter out = response.getWriter();
      
      try{
          
      String []path=new String[FILE_MAX_NUM];
       HttpSession session=request.getSession();
        for(Integer i=0;i<FILE_MAX_NUM;i++){
            path[i]="D:/tmp/"+session.getAttribute("Filename"+i.toString()).toString(); 
        }

        combine_file2json(path);
 
      }
        catch (Exception ex) {
            ex.printStackTrace();
        }
      
     // response.sendRedirect("specific_perf.html");
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

          doGet(request,response);
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
    
    private void combine_file2json(String[] path){
        try{
            
            BufferedReader[] br=new BufferedReader[path.length];
            for(int i=0;i<path.length;i++){
                        FileReader fr=new FileReader(path[i]);
                        br[i]=new BufferedReader(fr);
            }
            String[] tmp=new String[FILE_MAX_NUM];
            FileWriter  f=new FileWriter("d:/tmp/sentence.json");
            BufferedWriter bw=new BufferedWriter(f);
            StringBuilder strb=new StringBuilder();
            strb.append("[\r\n");
            /*bw.write("[\r\n");
            while((tmp[0]=br[0].readLine())!=null&&(tmp[1]=br[1].readLine())!=null){
            //在文件中写入数据
            bw.write("{'"+"source"+"':'"+tmp[0]+"',"+"'"+"target"+"':'"+tmp[1]+"'},\r\n");
            //关闭文件流
            bw.flush();
            }
            bw.write("]");
            bw.close();
*/          while((tmp[2]=br[2].readLine())!=null&&(tmp[3]=br[3].readLine())!=null){
            //在文件中写入数据
                strb.append("{'"+"source"+"':'"+tmp[0]+"',"+"'"+"target"+"':'"+tmp[1]+"'},\r\n");
                //关闭文件流
            }
            String str=strb.subSequence(0, strb.length()-1).toString();
            bw.write(str);
            bw.write("]");
            bw.flush();
            bw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
         
    }

}

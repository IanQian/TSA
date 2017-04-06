/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author qianshenglan
 */
@WebServlet(name = "figureServlet", urlPatterns = {"/figureServlet"})
public class figureServlet extends HttpServlet {

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
          response.setContentType("UTF-8");
             try {
                 String par=request.getParameter("line");
                 String[] par_arr=par.split("-");
                 int line=Integer.parseInt(par_arr[0]);
                 int type=Integer.parseInt(par_arr[1]);                     
                 String path_x="d:/tmp/"+request.getSession().getAttribute("Filename3").toString();
                 String path_y;
                 String path_data;
                 if(type==1){
                    path_y="d:/tmp/"+request.getSession().getAttribute("Filename1").toString();
                    path_data="d:/tmp/"+request.getSession().getAttribute("Filename5").toString();
                 }
                 else{
                     path_y="d:/tmp/"+request.getSession().getAttribute("Filename2").toString();
                     path_data="d:/tmp/"+request.getSession().getAttribute("Filename6").toString();
                 }
                 File fx = new File(path_x);
                 File fy = new File(path_y);
                 InputStreamReader read_x = new InputStreamReader(new FileInputStream(fx),"UTF-8");
                 InputStreamReader read_y = new InputStreamReader(new FileInputStream(fy),"UTF-8");
                 BufferedReader br_x = new BufferedReader(read_x);
                 BufferedReader br_y = new BufferedReader(read_y);
                 String tmp_x="";
                 String tmp_y="";
                 for(int i=0;i<line-1;i++){
                    br_x.readLine();
                    br_y.readLine();
                 }
                 tmp_x=br_x.readLine();
                 tmp_y=br_y.readLine();
                br_x.close();
                br_y.close();
                String[] hours = tmp_y.split(" ");//{"12a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a", "10a", "11a", "12p", "1p", "2p", "3p", "4p", "5p", "6p", "7p", "8p", "9p", "10p", "11p"};
                String[] days =tmp_x.split(" ");// {"Saturday", "Friday", "Thursday", "Wednesday", "Tuesday", "Monday", "Sunday"};
                //String data = "[[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]]";
                String data = get_data(path_data,line);
                Map<String, Object> json = new HashMap<String, Object>();
                json.put("hours", hours);
                json.put("days", days);
                json.put("data", data);
                json.put("source", tmp_x);
                json.put("target", tmp_y);
                JsonUtils.writeJson(json, request, response);

            }
            catch (Exception ex) {
                ex.printStackTrace();
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
    public String get_data(String path,int line) throws FileNotFoundException{
        try{
               
               File f = new File(path);
               InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
               BufferedReader br = new BufferedReader(read);
               String tmp="";
               for(int i=0;i<line-1;i++){
                    br.readLine();
               }
               tmp=br.readLine();
               String str="";
               StringBuilder strb=new StringBuilder();
               strb.append("[");
               if(!tmp.contains("[")){
                   String[] data=tmp.split(" ");
                  
                     for(int i=0;i<data.length;i++){
                         String[] strd=data[i].split("-");
                         Integer x=Integer.parseInt(strd[0]);
                         Integer y=Integer.parseInt(strd[1]);
                         strb.append("["+x.toString()+","+y.toString()+",10],");
                     }
                  
               }
               else{
                   
                     String[] data=tmp.split("],"); 
                     for(Integer i=0;i<data.length;i++){
                        data[i]=data[i].replace("[", "");
                        data[i]=data[i].replace("]", "");
                        String[] strd=data[i].split(",");
                        Integer j=0;
                        for( j=0;j<strd.length;j++){
                            Float p=Float.parseFloat(strd[j]);
                            p=p*10;
                            strb.append("["+i.toString()+","+j.toString()+","+p.toString()+"],");
                        }
                    }
              }  
               str=strb.subSequence(0, strb.length()-1).toString();
               str+="]";  
               return str;
               
              
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
}

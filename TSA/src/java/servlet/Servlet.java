package servlet;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author qianshenglan
 */
@MultipartConfig(location = "D:/tmp/", maxFileSize = 1024 * 1024 * 20)
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    int FILE_MAX_NUM=6;
    
    public Servlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

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
        PrintWriter out = response.getWriter();
        out.write(request.getSession().getAttribute("UNK1").toString() + "#" + request.getSession().getAttribute("UNK2"));
        out.flush();
        out.close();
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
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Integer i = 1;
        String [] path=new String[FILE_MAX_NUM];
        for (Part part : request.getParts()) {
            if (part != null && part.getName().startsWith("filename")) {
                String filename = getFilename(part);
                //因为上传框有多个，为了避免有的上传框没有选择文件导致出错，这里需要判断filename是否为null或为空
                if (filename != null && !"".equals(filename)) {
                    part.write(filename);
                }
                session.setAttribute("Filename" + i.toString(), filename);
                path[i-1]="D:/tmp/"+filename;
                i++;
            }
        }
        
          
        
        combine_file2json(path);

       
        //中文乱码
         response.setContentType("text/html;charset=gbk");
        int UNK1 = find_UNK_file("D:/tmp/" + session.getAttribute("Filename1").toString());
        int UNK2 = find_UNK_file("D:/tmp/" + session.getAttribute("Filename2").toString());
        session.setAttribute("UNK1", UNK1);
        session.setAttribute("UNK2", UNK2);
        response.sendRedirect("chart.html");

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

    private String getFilename(Part part) {
        if (part == null) {
            return null;
        }
        String fileName = part.getHeader("content-disposition");
        if (isBlank(fileName)) {
            return null;
        }
        return substringBetween(fileName, "filename=\"", "\"");
    }

    private boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    @Override
    public void init() throws ServletException {

    }


    public int find_UNK_sen(String str) {
        int result = 0;
        String[] tmp = str.split("UNK");
        result = tmp.length - 1;
        return result;
    }

    public int find_UNK_file(String file_path) {
        int result = 0;
        String str;
        try {
            FileReader f1 = new FileReader(file_path);
            BufferedReader br = new BufferedReader(f1);
            while ((str = br.readLine()) != null) {
                result += find_UNK_sen(str);
            }
            br.close();
            f1.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
     private void combine_file2json(String[] path){
         
        try{
            
            BufferedReader[] br=new BufferedReader[path.length];
            for(int i=0;i<path.length;i++){
                        File fr=new File(path[i]);
                        InputStreamReader read = new InputStreamReader(new FileInputStream(fr),"UTF-8");
                        br[i]=new BufferedReader(read);
            }
            String[] tmp=new String[FILE_MAX_NUM];
            File  f=new File("d:/TSA/web/data/sentence.json");
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
            BufferedWriter bw=new BufferedWriter(write);
            StringBuilder strb=new StringBuilder();
            strb.append("[\r\n");
  
            while((tmp[2]=br[2].readLine())!=null&&(tmp[3]=br[3].readLine())!=null){
            //在文件中写入数据
                tmp[2]=tmp[2].replace("\"", "'");
         
                tmp[3]=tmp[3].replace("\"", "'");
                Random random=new Random();
                int score1=random.nextInt(10)+30;
                int score2=random.nextInt(10)+30;
                strb.append("{\""+"source"+"\":\""+tmp[2]+"\","+"\""+"target"+"\":\""+tmp[3]+"\","+"\""+"score1"+"\":\""+score1+"\","+"\""+"score2"+"\":\""+score2+"\"},\r\n");
                //关闭文件流
            }
            String str=strb.subSequence(0, strb.length()-3).toString();
            bw.write(str);
            bw.write("\r\n]");
            bw.flush();
            bw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
     }

}

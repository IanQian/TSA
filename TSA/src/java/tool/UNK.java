/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.io.BufferedReader;
import java.io.FileReader;



/**
 *
 * @author qianshenglan
 */
public class UNK {
    public int find_UNK_sen(String str){
        int result=0;
        String[] tmp=str.split("UNK");
        result=tmp.length-1;
        return result;
    }
    
    public int find_UNK_file(String file_path){
        int result=0;
        String str;
        try{
            FileReader  f1=new FileReader(file_path);         
            BufferedReader br=new BufferedReader(f1);
            while((str=br.readLine())!=null){
                result+=find_UNK_sen(str);
            }
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
         return result;
    }
}

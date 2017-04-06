/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author qianshenglan
 */
import java.io.File;

public class File_list {

    private File LSTM;
    private File SMT;
    private File target;
    private File align;
    private File source;
    public File getLSTM() {
        return LSTM;
    }
    public void setLSTM(File LSTM) {
        this.LSTM = LSTM;
    }
    public File getSMT() {
        return SMT;
    }
    public void setSMT(File SMT) {
        this.SMT = SMT;
    }
    public File gettarget() {
        return target;
    }
    public void settarget(File target) {
        this.target = target;
    }
    public File getsource() {
        return source;
    }
    public void setsource(File source) {
        this.source = source;
    }
    public File getalign() {
        return align;
    }
    public void setalign(File align) {
        this.align = align;
    }
    

    
}

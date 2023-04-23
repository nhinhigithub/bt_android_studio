package com.example.ql_tkb;

public class MyCHUYENDE {
    String maCD;
    String tenCD;
    String Lythuyet;
    String Thuchanh;

    //ham khoi tao
    public  MyCHUYENDE(String macd, String tencd, String lythuyet, String thuchanh){
        this.maCD=macd;
        this.tenCD=tencd;
        this.Lythuyet=lythuyet;
        this.Thuchanh=thuchanh;
    }

    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Mã chuyên đề: "+ this.maCD + "\n";
        msg+="Tên chuyên đề: "+ this.tenCD+ "\n";
        //msg+="Lý thuyết: "+ this.Lythuyet + "\n";
        //msg+="Thực hành: "+ this.Thuchanh;
        return msg;
    }
}

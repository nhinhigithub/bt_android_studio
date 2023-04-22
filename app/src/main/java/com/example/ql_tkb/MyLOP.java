package com.example.ql_tkb;

public class MyLOP {
    String maLOP;
    String tenLOP;
    String tsSV;
    String NTS;
    String tenNGANH;

    //ham khoi tao
    public  MyLOP(String malop, String tenlop, String tssv, String nts,String tennganh){
        this.maLOP=malop;
        this.tenLOP=tenlop;
        this.tsSV=tssv;
        this.NTS=nts;
        this.tenNGANH=tennganh;
    }

    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Mã lớp: "+ this.maLOP + "\n";
        msg+="Tên lớp: "+ this.tenLOP + "\n";
        //msg+="TSSV: "+ this.tsSV + "\n";
        //msg+="NTS: "+ this.NTS + "\n";
        msg+="Tên ngành: "+ this.tenNGANH;
        return msg;
    }
}

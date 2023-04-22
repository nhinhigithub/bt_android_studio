package com.example.ql_tkb;

public class MyNGANH {
    String maNGANH;
    String tenNGANH;
    String soCD;
    String tenKhoa;

    //ham khoi tao
    public  MyNGANH(String manganh, String tennganh, String socd,String tenkhoa){
        this.maNGANH=manganh;
        this.tenNGANH=tennganh;
        this.soCD=socd;
        this.tenKhoa=tenkhoa;
    }

    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Mã ngành: "+ this.maNGANH + "\n";
        msg+="Tên ngành: "+ this.tenNGANH + "\n";
        //msg+="SOCD: "+ this.soCD + "\n";
        msg+="Tên khoa: "+ this.tenKhoa;
        return msg;
    }
}

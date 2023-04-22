package com.example.ql_tkb;

public class MyCHUYENDE {
    String maKhoa;
    String tenKhoa;
    String Lythuyet;
    String Thuchanh;

    //ham khoi tao
    public  MyCHUYENDE(String makhoa, String tenkhoa){
        this.maKhoa=makhoa;
        this.tenKhoa=tenkhoa;
    }

    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Mã khoa: "+ this.maKhoa + "\n";
        msg+="Tên khoa: "+ this.tenKhoa;
        return msg;
    }
}

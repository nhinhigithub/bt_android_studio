package com.example.ql_tkb;

public class MyTKB {
    String namHoc;
    String hocKy;
    String Ngay;
    String Buoi;
    String tenLOP;
    String tenGV;
    String tenCD;

    //ham khoi tao
    public  MyTKB(String namhoc, String hocky,String ngay, String buoi, String tenlop, String tengv, String tencd){
        this.namHoc=namhoc;
        this.hocKy=hocky;
        this.Ngay=ngay;
        this.Buoi=buoi;
        this.tenLOP=tenlop;
        this.tenGV=tengv;
        this.tenCD=tencd;
    }


    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Năm học: "+ this.namHoc + "\n";
        msg+="Học kỳ: "+ this.hocKy + "\n";
        msg+="Ngày: "+ this.Ngay + "\n";
        msg+="Buổi: "+ this.Buoi + "\n";
        msg+="Mã lớp: "+ this.tenLOP + "\n";
        msg+="Mã giảng viên: "+ this.tenGV + "\n";
        msg+="Tên chuyên đề: "+ this.tenCD + "\n";

        return msg;
    }
}

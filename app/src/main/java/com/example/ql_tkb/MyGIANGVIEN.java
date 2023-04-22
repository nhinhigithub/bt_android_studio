package com.example.ql_tkb;

public class MyGIANGVIEN {
    String maGV;
    String tenGV;
    String Phai;
    String Ngaysinh;
    String Diachi;
    String tenKhoa;

    //ham khoi tao
    public  MyGIANGVIEN(String magv, String tengv, String phai,String ngaysinh, String diachi,String tenkhoa){
        this.maGV=magv;
        this.tenGV=tengv;
        this.Phai=phai;
        this.Ngaysinh=ngaysinh;
        this.Diachi=diachi;
        this.tenKhoa=tenkhoa;
    }


    //thu tuc hien thi du lieu
    public String toString(){
        String msg="";
        msg+="Mã giảng viên: "+ this.maGV + "\n";
        msg+="Tên giảng viên: "+ this.tenGV + "\n";

        //msg+="Phái: "+ this.Phai + "\n";

        //msg+="Ngày sinh: "+ this.Ngaysinh + "\n";
        //msg+="Địa chỉ: "+ this.Diachi + "\n";

        msg+="Tên khoa: "+ this.tenKhoa;
        return msg;
    }
}

package com.example.ql_tkb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CSDLile extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    Button btnCreateDB, btnDeleteDB, btnCreateBANG, btnTblKHOA, btnTblGIANGVIEN, btnTblNGANH, btnTblLOP, btnTblCHUYENDE, btnKHOA, btnGIANGVIEN, btnNGANH, btnLOP, btnCHUYENDE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdlile);

        btnCreateDB = (Button) findViewById(R.id.btnCreateDB);
        btnDeleteDB = (Button) findViewById(R.id.btnDeleteDB);

        btnCreateBANG = (Button) findViewById(R.id.btnCreateBANG);

        //btnTblKHOA = (Button) findViewById(R.id.btnTblKHOA);
        //btnTblGIANGVIEN = (Button) findViewById(R.id.btnTblGIANGVIEN);
        //btnTblNGANH = (Button) findViewById(R.id.btnTblNGANH);
        //btnTblLOP = (Button) findViewById(R.id.btnTblLOP);

        btnKHOA = (Button) findViewById(R.id.btnKHOA);
        btnGIANGVIEN = (Button) findViewById(R.id.btnGIANGVIEN);
        btnNGANH = (Button) findViewById(R.id.btnNGANH);
        btnLOP = (Button) findViewById(R.id.btnLOP);

        //Gọi hàm tạo Database
        btnCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {doCreateDB(nameDB);}
        });

        //Gọi hàm xóa Database
        btnDeleteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDeleteBD(nameDB);
            }
        });

        //Gọi hàm tạo Database các BẢNG
        btnCreateBANG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTblKHOA();
                createTblGIANGVIEN();
                createTblNGANH();
                createTblLOP();
                createTblCHUYENDE();
                createTblTKB();
            }
        });

        //sang trang
        btnKHOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSDLile.this, KHOAActivity.class);
                startActivity(intent);
            }
        });

        btnGIANGVIEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSDLile.this, GIANGVIENActivity.class);
                startActivity(intent);
            }
        });

        btnNGANH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSDLile.this, NGANHActivity.class);
                startActivity(intent);
            }
        });

        btnLOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSDLile.this, LOPActivity.class);
                startActivity(intent);
            }
        });



    }


    //Tạo Table KHOA
    public void createTblKHOA(){
        String sql = "CREATE TABLE tblKHOA (MAKHOA TEXT PRIMARY KEY,TENKHOA TEXT);";
        if (doAction(sql) == true) {
            Toast.makeText(CSDLile.this, "Tạo Table [KHOA] thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CSDLile.this, "Tạo Table [KHOA] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
        }
    }

    //Tạo Table GV
    public void createTblGIANGVIEN(){
            String sql = "CREATE TABLE tblGIANGVIEN (MAGV TEXT PRIMARY KEY,TENGV TEXT, PHAI TEXT,NGAYSINH TEXT, DIACHI TEXT,MAKHOA TEXT, FOREIGN KEY (MAKHOA) REFERENCES tblKHOA(MAKHOA));";
            if (doAction(sql) == true) {
                Toast.makeText(CSDLile.this, "Tạo Table [GIẢNG VIÊN] thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CSDLile.this, "Tạo Table [GIẢNG VIÊN] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
            }
        }

    //Tạo Table NGANH
    public void createTblNGANH(){
            String sql = "CREATE TABLE tblNGANH (MANGANH TEXT PRIMARY KEY,TENNGANH TEXT, SOCD TEXT,MAKHOA TEXT, FOREIGN KEY (MAKHOA) REFERENCES tblKHOA(MAKHOA));";
            if (doAction(sql) == true) {
                Toast.makeText(CSDLile.this, "Tạo Table [NGÀNH] thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CSDLile.this, "Tạo Table [NGÀNH] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
            }
        }

    //Tạo Table LOP
    public void createTblLOP(){
            String sql = "CREATE TABLE tblLOP (MALOP TEXT PRIMARY KEY,TENLOP TEXT, TSSV TEXT, NTS TEXT,MANGANH TEXT, FOREIGN KEY (MANGANH) REFERENCES tblNGANH(MANGANH));";
            if (doAction(sql) == true) {
                Toast.makeText(CSDLile.this, "Tạo Table [LỚP] thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CSDLile.this, "Tạo Table [LỚP] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
            }
        }

    //Tạo Table CHUYENDE
    public void createTblCHUYENDE(){
        String sql = "CREATE TABLE tblCHUYENDE (MACHUYENDE TEXT PRIMARY KEY,TENCHUYENDE TEXT,LYTHUYET TEXT,THUCHANH TEXT)";
        if (doAction(sql) == true) {
            Toast.makeText(CSDLile.this, "Tạo Table [CHUYÊN ĐỀ] thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CSDLile.this, "Tạo Table [CHUYÊN ĐỀ] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
        }
    }

    //Tạo Table TKB
    public void createTblTKB(){
        String sql ="CREATE TABLE TKB ("+
                "MALOP TEXT NOT NULL,"+
                "MAGV TEXT NOT NULL,"+
                "MACD TEXT NOT NULL,"+
                "PRIMARY KEY(MALOP, MAGV, MACD),"+
                "FOREIGN KEY (MALOP) REFERENCES LOP(MALOP),";
        if (doAction(sql) == true) {
            Toast.makeText(CSDLile.this, "Tạo Table [TKB] thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CSDLile.this, "Tạo Table [TKB] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
        }
    }


    //Tạo hàm sử dụng cho Insert, Update, Delete, Create Table
    public boolean doAction (String sql)
    {
        try
        {
            database = openOrCreateDatabase(nameDB, MODE_PRIVATE,null);
            database.execSQL(sql);
            return true;
        }
        catch(Exception ex){
            return false;
        }
        finally {
            database.close();
        }
    }

    //Tạo Database
    public void doCreateDB(String nameDB){
        try {

            database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
            Toast.makeText(CSDLile.this,"Tạo [Database] thành công",Toast.LENGTH_SHORT).show();
        }

        catch (Exception ex)
        {
            Toast.makeText(CSDLile.this,"Tạo [Database] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
        }
    }


    //Xóa Database
    public void doDeleteBD(String nameDB) {
        String msg = "";
        try {
            if (deleteDatabase(nameDB) == true) {
                msg = "Xóa [Database] thành công";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

            }
        } catch (Exception ex) {
            Toast.makeText(this, "Xóa [Database] [KHÔNG] thành công", Toast.LENGTH_LONG).show();
        }
    }

}
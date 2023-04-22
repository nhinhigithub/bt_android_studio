package com.example.ql_tkb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GIANGVIENActivity extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listKHOA, listGIANGVIEN;
    EditText edtMAGV,edtTENGV, edtPHAI,edtNGAYSINH, edtDIACHI;
    Button btnThemGV,btnSuaGV,btnXoaGV,btnBack;
    ListView lvGIANGVIEN;
    String maGV, tenGV, Phai, Ngaysinh, Diachi, tenKhoa;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giangvienactivity);
        edtMAGV= (EditText) findViewById(R.id.edtMAGV);
        edtTENGV = (EditText) findViewById(R.id.edtTENGV);
        edtPHAI = (EditText) findViewById(R.id.edtPHAI);
        edtNGAYSINH = (EditText) findViewById(R.id.edtNGAYSINH);
        edtDIACHI = (EditText) findViewById(R.id.edtDIACHI);

        spinner = (Spinner) findViewById(R.id.spinner);
        lvGIANGVIEN = (ListView) findViewById(R.id.lvGIANGVIEN);

        btnThemGV = (Button) findViewById(R.id.btnThemGV);
        btnSuaGV = (Button) findViewById(R.id.btnSuaGV);
        btnXoaGV = (Button) findViewById(R.id.btnXoaGV);
        btnBack = (Button) findViewById(R.id.btnBack);
        hienthiSpinnerTENKHOA();
        hienthi();

        lvGIANGVIEN.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                MyGIANGVIEN GIANGVIEN = (MyGIANGVIEN) listGIANGVIEN.get(i);
                edtMAGV.setText(GIANGVIEN.maGV);
                edtTENGV.setText(GIANGVIEN.tenGV);
                edtPHAI.setText(GIANGVIEN.Phai);
                edtNGAYSINH.setText(GIANGVIEN.Ngaysinh);
                edtDIACHI.setText(GIANGVIEN.Diachi);

                return false;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                MyKHOA KHOA=(MyKHOA) listKHOA.get(i);
                tenKhoa=KHOA.tenKhoa;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(GIANGVIENActivity.this, "Hãy chọn một Khoa", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maGV=edtMAGV.getText().toString();
                tenGV=edtTENGV.getText().toString();
                Phai=edtPHAI.getText().toString();
                Ngaysinh=edtNGAYSINH.getText().toString();
                Diachi=edtDIACHI.getText().toString();
                String sql ="INSERT INTO tblGIANGVIEN VALUES('"+maGV+"', '"+tenGV+"', '"+Phai+"', '"+Ngaysinh+"', '"+Diachi+"', '"+tenKhoa+"')";
                if(doAction(sql)==true){
                    Toast.makeText(GIANGVIENActivity.this, "Thêm [GIẢNG VIÊN] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GIANGVIENActivity.this, "Thêm [GIẢNG VIÊN] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnSuaGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maGV=edtMAGV.getText().toString();
                tenGV=edtTENGV.getText().toString();
                Phai=edtPHAI.getText().toString();
                Ngaysinh=edtNGAYSINH.getText().toString();
                Diachi=edtDIACHI.getText().toString();
                String sql ="UPDATE tblGIANGVIEN SET TENGV = '"+tenGV+"',PHAI = '"+Phai+"',NGAYSINH = '"+Ngaysinh+"',DIACHI = '"+Diachi+"',MAKHOA = '"+tenKhoa+"' WHERE MAGV = '"+maGV+"'";
                if(doAction(sql)==true){
                    Toast.makeText(GIANGVIENActivity.this, "Sửa [GIẢNG VIÊN] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GIANGVIENActivity.this, "Sửa [GIẢNG VIÊN] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnXoaGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maGV=edtMAGV.getText().toString();
                String sql ="DELETE FROM tblGIANGVIEN WHERE MAGV = '"+maGV+"'";
                if(doAction(sql)==true){
                    Toast.makeText(GIANGVIENActivity.this, "Xóa [GIẢNG VIÊN] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GIANGVIENActivity.this, "Xóa [GIẢNG VIÊN] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GIANGVIENActivity.this, CSDLile.class);
                startActivity(intent);
            }
        });
    }

    public boolean doAction(String sql)
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
    public void hienthi()
    {
        listGIANGVIEN = new ArrayList();
        String sql = "SELECT * FROM tblGIANGVIEN";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listGIANGVIEN.add(new MyGIANGVIEN(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listGIANGVIEN);
        lvGIANGVIEN.setAdapter(adapter);
    }
    public void hienthiSpinnerTENKHOA(){

        listKHOA=new ArrayList();

        String sql="Select * From tblKHOA Order By TENKHOA";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listKHOA.add(new MyKHOA(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listKHOA);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
    }



    public void doClear()
    {
        edtMAGV.setText("");
        edtTENGV.setText("");
        edtPHAI.setText("");
        edtNGAYSINH.setText("");
        edtDIACHI.setText("");
        edtMAGV.findFocus();
    }

}
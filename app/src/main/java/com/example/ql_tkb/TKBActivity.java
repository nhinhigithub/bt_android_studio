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

public class TKBActivity extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listTKB, listLOP, listGIANGVIEN, listCD;
    EditText edtNH,edtHK, edtNGAY,edtBUOI;
    Button btnThemTKB,btnSuaTKB,btnXoaTKB,btnBack;
    ListView lvTKB;
    String tenLOP, tenGV, tenCD,  namHoc, hocKy, Ngay, Buoi;
    Spinner spinnerLOP, spinnerGV, spinnerCD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkbactivity);

        edtNH= (EditText) findViewById(R.id.edtNH);
        edtHK = (EditText) findViewById(R.id.edtHK);
        edtNGAY = (EditText) findViewById(R.id.edtNGAY);
        edtBUOI = (EditText) findViewById(R.id.edtBUOI);

        spinnerLOP = (Spinner) findViewById(R.id.spinnerLOP);
        spinnerGV = (Spinner) findViewById(R.id.spinnerGV);
        spinnerCD = (Spinner) findViewById(R.id.spinnerCD);

        lvTKB = (ListView) findViewById(R.id.lvTKB);

        btnThemTKB = (Button) findViewById(R.id.btnThemTKB);
        btnSuaTKB = (Button) findViewById(R.id.btnSuaTKB);
        btnXoaTKB = (Button) findViewById(R.id.btnXoaTKB);
        btnBack = (Button) findViewById(R.id.btnBack);
        hienthiSpinnerTENLOP();
        hienthiSpinnerTENGV();
        hienthiSpinnerTENCD();
        hienthi();


        lvTKB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                MyTKB TKB = (MyTKB) listTKB.get(i);
                edtNH.setText(TKB.namHoc);
                edtHK.setText(TKB.hocKy);
                edtNGAY.setText(TKB.Ngay);
                edtBUOI.setText(TKB.Buoi);

                return false;
            }
        });


        spinnerLOP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                MyLOP LOP=(MyLOP) listLOP.get(i);
                tenLOP=LOP.tenLOP;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(TKBActivity.this, "Hãy chọn một Lớp", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerGV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                MyGIANGVIEN GIANGVIEN=(MyGIANGVIEN) listGIANGVIEN.get(i);
                tenGV=GIANGVIEN.tenGV;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(TKBActivity.this, "Hãy chọn một Giảng viên", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerCD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                MyCHUYENDE CHUYENDE=(MyCHUYENDE) listCD.get(i);
                tenCD=CHUYENDE.tenCD;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(TKBActivity.this, "Hãy chọn một Chuyên đề", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemTKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namHoc=edtNH.getText().toString();
                hocKy=edtHK.getText().toString();
                Ngay=edtNGAY.getText().toString();
                Buoi=edtBUOI.getText().toString();
                String sql ="INSERT INTO tblTKB VALUES('"+namHoc+"', '"+hocKy+"', '"+Ngay+"', '"+Buoi+"', '"+tenLOP+"', '"+tenGV+"', '"+tenCD+"')";
                if(doAction(sql)==true){
                    Toast.makeText(TKBActivity.this, "Thêm [TKB] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TKBActivity.this, "Thêm [TKB] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnSuaTKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namHoc=edtNH.getText().toString();
                hocKy=edtHK.getText().toString();
                Ngay=edtNGAY.getText().toString();
                Buoi=edtBUOI.getText().toString();
                String sql ="UPDATE tblTKB SET MAGV = '"+tenGV+"', MACHUYENDE = '"+tenCD+"' WHERE NAMHOC = '"+namHoc+"',HOCKY = '"+hocKy+"',NGAY = '"+Ngay+"',BUOI = '"+Buoi+"', MALOP = '"+tenLOP+"'";
                if(doAction(sql)==true){
                    Toast.makeText(TKBActivity.this, "Sửa [TKB] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TKBActivity.this, "Sửa [TKB] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnXoaTKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namHoc=edtNH.getText().toString();
                String sql ="DELETE FROM tblTKB WHERE NAMHOC = '"+namHoc+"'";
                if(doAction(sql)==true){
                    Toast.makeText(TKBActivity.this, "Xóa [TKB] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TKBActivity.this, "Xóa [TKB] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TKBActivity.this, CSDLile.class);
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
        listTKB = new ArrayList();
        String sql = "SELECT * FROM tblTKB";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listTKB.add(new MyTKB(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listTKB);
        lvTKB.setAdapter(adapter);
    }
    public void hienthiSpinnerTENLOP(){

        listLOP=new ArrayList();

        String sql="Select * From tblLOP Order By TENLOP";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listLOP.add(new MyLOP(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listLOP);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerLOP.setAdapter(adapter);
    }

    public void hienthiSpinnerTENGV(){

        listGIANGVIEN=new ArrayList();

        String sql="Select * From tblGIANGVIEN Order By TENGV";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listGIANGVIEN.add(new MyGIANGVIEN(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listGIANGVIEN);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerGV.setAdapter(adapter);
    }

    public void hienthiSpinnerTENCD(){

        listCD=new ArrayList();

        String sql="Select * From tblCHUYENDE Order By TENCHUYENDE";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listCD.add(new MyCHUYENDE(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listCD);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerCD.setAdapter(adapter);
    }


    public void doClear()
    {
        edtNH.setText("");
        edtHK.setText("");
        edtNGAY.setText("");
        edtBUOI.setText("");
        edtNH.findFocus();
    }

}
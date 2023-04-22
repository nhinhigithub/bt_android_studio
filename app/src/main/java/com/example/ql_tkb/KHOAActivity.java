package com.example.ql_tkb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class KHOAActivity extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listKHOA;
    EditText edtMAKHOA,edtTENKHOA;
    Button btnThemKHOA,btnSuaKHOA,btnXoaKHOA,btnBack;
    ListView lvKHOA;
    String maKhoa, tenKhoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoaactivity);

        edtMAKHOA = (EditText) findViewById(R.id.edtMAKHOA);
        edtTENKHOA = (EditText) findViewById(R.id.edtTENKHOA);
        btnThemKHOA = (Button) findViewById(R.id.btnThemKHOA);
        btnSuaKHOA = (Button) findViewById(R.id.btnSuaKHOA);
        btnXoaKHOA = (Button) findViewById(R.id.btnXoaKHOA);
        btnBack = (Button) findViewById(R.id.btnBack);
        lvKHOA = (ListView) findViewById(R.id.lvKHOA);
        hienthi();


        btnThemKHOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhoa = edtMAKHOA.getText().toString();
                tenKhoa = edtTENKHOA.getText().toString();
                String sql = "INSERT INTO tblKHOA VALUES('"+maKhoa+"','"+tenKhoa+"')";
                if(doAction(sql)==true){
                    Toast.makeText(KHOAActivity.this,"Thêm [KHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KHOAActivity.this,"Thêm [KHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });
        btnSuaKHOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhoa = edtMAKHOA.getText().toString();
                tenKhoa = edtTENKHOA.getText().toString();
                String sql = "UPDATE tblKHOA SET TENKHOA = '"+tenKhoa+"' WHERE MAKHOA = '"+maKhoa+"'";
                if(doAction(sql)==true){
                    Toast.makeText(KHOAActivity.this,"Sửa [KHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KHOAActivity.this,"Sửa [KHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });
        btnXoaKHOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maKhoa = edtMAKHOA.getText().toString();
                String sql = "DELETE FROM tblKHOA WHERE MAKHOA = '"+maKhoa+"'";
                if(doAction(sql)==true){
                    Toast.makeText(KHOAActivity.this,"Xóa [KHOA] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(KHOAActivity.this,"Xóa [KHOA] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KHOAActivity.this, CSDLile.class);
                startActivity(intent);
            }
        });

        lvKHOA.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyKHOA khoa = (MyKHOA) listKHOA.get(i);
                edtMAKHOA.setText(khoa.maKhoa);
                edtTENKHOA.setText(khoa.tenKhoa);
                return false;
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
        listKHOA = new ArrayList();
        String sql = "SELECT * FROM tblKHOA";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listKHOA.add(new MyKHOA(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listKHOA);
        lvKHOA.setAdapter(adapter);
    }
    public void doClear()
    {
        edtMAKHOA.setText("");
        edtTENKHOA.setText("");
        edtMAKHOA.findFocus();
    }
}
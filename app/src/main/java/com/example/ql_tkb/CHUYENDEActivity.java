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

public class CHUYENDEActivity extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listCD;
    EditText edtMACD,edtTENCD,edtLT,edtTH;
    Button btnThemCD,btnSuaCD,btnXoaCD,btnBack;
    ListView lvCD;
    String maCD, tenCD, Lythuyet, Thuchanh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyendeactivity);

        edtMACD = (EditText) findViewById(R.id.edtMACD);
        edtTENCD = (EditText) findViewById(R.id.edtTENCD);
        edtLT = (EditText) findViewById(R.id.edtLT);
        edtTH = (EditText) findViewById(R.id.edtTH);

        btnThemCD = (Button) findViewById(R.id.btnThemCD);
        btnSuaCD = (Button) findViewById(R.id.btnSuaCD);
        btnXoaCD = (Button) findViewById(R.id.btnXoaCD);
        btnBack = (Button) findViewById(R.id.btnBack);
        lvCD = (ListView) findViewById(R.id.lvCD);
        hienthi();

        btnThemCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maCD = edtMACD.getText().toString();
                tenCD = edtTENCD.getText().toString();
                Lythuyet = edtLT.getText().toString();
                Thuchanh = edtTH.getText().toString();
                String sql = "INSERT INTO tblCHUYENDE VALUES('"+maCD+"','"+tenCD+"','"+Lythuyet+"','"+Thuchanh+"')";
                if(doAction(sql)==true){
                    Toast.makeText(CHUYENDEActivity.this,"Thêm [CHUYÊN ĐỀ] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CHUYENDEActivity.this,"Thêm [CHUYÊN ĐỀ] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnSuaCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maCD = edtMACD.getText().toString();
                tenCD = edtTENCD.getText().toString();
                Lythuyet = edtLT.getText().toString();
                Thuchanh = edtTH.getText().toString();
                String sql = "UPDATE tblCHUYENDE SET TENCHUYENDE = '"+tenCD+"',LYTHUYET = '"+Lythuyet+"',THUCHANH = '"+Thuchanh+"' WHERE MACHUYENDE = '"+maCD+"'";
                if(doAction(sql)==true){
                    Toast.makeText(CHUYENDEActivity.this,"Sửa [CHUYÊN ĐỀ] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CHUYENDEActivity.this,"Sửa [CHUYÊN ĐỀ] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnXoaCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maCD = edtMACD.getText().toString();
                String sql = "DELETE FROM tblCHUYENDE WHERE MACHUYENDE = '"+maCD+"'";
                if(doAction(sql)==true){
                    Toast.makeText(CHUYENDEActivity.this,"Xóa [CHUYÊN ĐỀ] thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CHUYENDEActivity.this,"Xóa [CHUYÊN ĐỀ] [KHÔNG] thành công",Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CHUYENDEActivity.this, CSDLile.class);
                startActivity(intent);
            }
        });

        lvCD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyCHUYENDE CHUYENDE = (MyCHUYENDE) listCD.get(i);
                edtMACD.setText(CHUYENDE.maCD);
                edtTENCD.setText(CHUYENDE.tenCD);
                edtLT.setText(CHUYENDE.Lythuyet);
                edtTH.setText(CHUYENDE.Thuchanh);
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
        listCD = new ArrayList();
        String sql = "SELECT * FROM tblCHUYENDE";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listCD.add(new MyCHUYENDE(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listCD);
        lvCD.setAdapter(adapter);
    }
    public void doClear()
    {
        edtMACD.setText("");
        edtTENCD.setText("");
        edtLT.setText("");
        edtTH.setText("");
        edtMACD.findFocus();
    }


}
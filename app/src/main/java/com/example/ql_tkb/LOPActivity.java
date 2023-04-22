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

public class LOPActivity extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listNGANH, listLOP;
    EditText edtMALOP,edtTENLOP, edtTSSV,edtNTS;
    Button btnThemLOP,btnSuaLOP,btnXoaLOP,btnBack;
    ListView lvLOP;
    String maLOP, tenLOP, tsSV, NTS, tenNGANH;
    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lopactivity);

        edtMALOP= (EditText) findViewById(R.id.edtMALOP);
        edtTENLOP = (EditText) findViewById(R.id.edtTENLOP);
        edtTSSV = (EditText) findViewById(R.id.edtTSSV);
        edtNTS = (EditText) findViewById(R.id.edtNTS);

        spinner = (Spinner) findViewById(R.id.spinner);
        lvLOP = (ListView) findViewById(R.id.lvLOP);

        btnThemLOP = (Button) findViewById(R.id.btnThemLOP);
        btnSuaLOP = (Button) findViewById(R.id.btnSuaLOP);
        btnXoaLOP = (Button) findViewById(R.id.btnXoaLOP);
        btnBack = (Button) findViewById(R.id.btnBack);
        hienthiSpinnerTENNGANH();
        hienthi();


        lvLOP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                MyLOP LOP = (MyLOP) listLOP.get(i);
                edtMALOP.setText(LOP.maLOP);
                edtTENLOP.setText(LOP.tenLOP);
                edtTSSV.setText(LOP.tsSV);
                edtNTS.setText(LOP.NTS);

                return false;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                MyNGANH NGANH=(MyNGANH) listNGANH.get(i);
                tenNGANH=NGANH.tenNGANH;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(LOPActivity.this, "Hãy chọn một Ngành", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemLOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maLOP=edtMALOP.getText().toString();
                tenLOP=edtTENLOP.getText().toString();
                tsSV=edtTSSV.getText().toString();
                NTS=edtNTS.getText().toString();
                String sql ="INSERT INTO tblLOP VALUES('"+maLOP+"', '"+tenLOP+"', '"+tsSV+"', '"+NTS+"', '"+tenNGANH+"')";
                if(doAction(sql)==true){
                    Toast.makeText(LOPActivity.this, "Thêm [LỚP] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LOPActivity.this, "Thêm [LỚP] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnSuaLOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maLOP=edtMALOP.getText().toString();
                tenLOP=edtTENLOP.getText().toString();
                tsSV=edtTSSV.getText().toString();
                NTS=edtNTS.getText().toString();
                String sql ="UPDATE tblLOP SET TENLOP = '"+tenLOP+"',TSSV = '"+tsSV+"',NTS = '"+NTS+"',MANGANH = '"+tenNGANH+"' WHERE MALOP = '"+maLOP+"'";
                if(doAction(sql)==true){
                    Toast.makeText(LOPActivity.this, "Sửa [LỚP] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LOPActivity.this, "Sửa [LỚP] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnXoaLOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maLOP=edtMALOP.getText().toString();
                String sql ="DELETE FROM tblLOP WHERE MALOP = '"+maLOP+"'";
                if(doAction(sql)==true){
                    Toast.makeText(LOPActivity.this, "Xóa [LỚP] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LOPActivity.this, "Xóa [LỚP] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LOPActivity.this, CSDLile.class);
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
        listLOP = new ArrayList();
        String sql = "SELECT * FROM tblLOP";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listLOP.add(new MyLOP(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listLOP);
        lvLOP.setAdapter(adapter);
    }
    public void hienthiSpinnerTENNGANH(){

        listNGANH=new ArrayList();

        String sql="Select * From tblNGANH Order By TENNGANH";
        database=openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                listNGANH.add(new MyNGANH(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listNGANH);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
    }



    public void doClear()
    {
        edtMALOP.setText("");
        edtTENLOP.setText("");
        edtTSSV.setText("");
        edtNTS.setText("");
        edtMALOP.findFocus();
    }
}
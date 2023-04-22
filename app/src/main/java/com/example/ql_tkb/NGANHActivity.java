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

public class NGANHActivity extends AppCompatActivity {

    String nameDB = "qltkb.db"; //Khai báo tên Database
    SQLiteDatabase database;
    ArrayList listKHOA, listNGANH;
    EditText edtMANGANH,edtTENNGANH, edtSOCD;
    Button btnThemNGANH,btnSuaNGANH,btnXoaNGANH,btnBack;
    ListView lvNGANH;
    String maNGANH, tenNGANH, soCD, tenKhoa;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nganhactivity);

        edtMANGANH= (EditText) findViewById(R.id.edtMANGANH);
        edtTENNGANH = (EditText) findViewById(R.id.edtTENNGANH);
        edtSOCD = (EditText) findViewById(R.id.edtSOCD);

        spinner = (Spinner) findViewById(R.id.spinner);
        lvNGANH = (ListView) findViewById(R.id.lvNGANH);

        btnThemNGANH = (Button) findViewById(R.id.btnThemNGANH);
        btnSuaNGANH = (Button) findViewById(R.id.btnSuaNGANH);
        btnXoaNGANH = (Button) findViewById(R.id.btnXoaNGANH);
        btnBack = (Button) findViewById(R.id.btnBack);
        hienthiSpinnerTENKHOA();
        hienthi();

        lvNGANH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                MyNGANH NGANH = (MyNGANH) listNGANH.get(i);
                edtMANGANH.setText(NGANH.maNGANH);
                edtTENNGANH.setText(NGANH.tenNGANH);
                edtSOCD.setText(NGANH.soCD);

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
                Toast.makeText(NGANHActivity.this, "Hãy chọn một Khoa", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemNGANH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maNGANH=edtMANGANH.getText().toString();
                tenNGANH=edtTENNGANH.getText().toString();
                soCD=edtSOCD.getText().toString();
                String sql ="INSERT INTO tblNGANH VALUES('"+maNGANH+"', '"+tenNGANH+"', '"+soCD+"', '"+tenKhoa+"')";
                if(doAction(sql)==true){
                    Toast.makeText(NGANHActivity.this, "Thêm [NGÀNH] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NGANHActivity.this, "Thêm [NGÀNH] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnSuaNGANH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maNGANH=edtMANGANH.getText().toString();
                tenNGANH=edtTENNGANH.getText().toString();
                soCD=edtSOCD.getText().toString();
                String sql ="UPDATE tblNGANH SET TENNGANH = '"+tenNGANH+"',SOCD = '"+soCD+"',MAKHOA = '"+tenKhoa+"' WHERE MANGANH = '"+maNGANH+"'";
                if(doAction(sql)==true){
                    Toast.makeText(NGANHActivity.this, "Sửa [NGÀNH] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NGANHActivity.this, "Sửa [NGÀNH] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnXoaNGANH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maNGANH=edtMANGANH.getText().toString();
                String sql ="DELETE FROM tblNGANH WHERE MANGANH = '"+maNGANH+"'";
                if(doAction(sql)==true){
                    Toast.makeText(NGANHActivity.this, "Xóa [NGÀNH] thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NGANHActivity.this, "Xóa [NGÀNH] [KHÔNG] thành công", Toast.LENGTH_SHORT).show();
                }
                hienthi();
                doClear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NGANHActivity.this, CSDLile.class);
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
        listNGANH = new ArrayList();
        String sql = "SELECT * FROM tblNGANH";
        database = openOrCreateDatabase(nameDB,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                listNGANH.add(new MyNGANH(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listNGANH);
        lvNGANH.setAdapter(adapter);
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
        edtMANGANH.setText("");
        edtTENNGANH.setText("");
        edtSOCD.setText("");
        edtMANGANH.findFocus();
    }
}
package com.example.csdlmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnEdit;
    private Button btnDel;
    private EditText txtName;
    private EditText txtClass;
    private EditText txtPhone;
    private EditText txtEmail;
    private DBManager db;
    private ListView lv_st;
    private CustomAdapter customAdapter;
    private List<Student> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager db = new DBManager(this);
        initWidget();
        students = db.getAll();
        setAdapter();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createSt();
                if (student != null){
                    db.addStudent(student);
                    Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private Student createSt(){
        String name = txtName.getText().toString();
        String lop = String.valueOf(txtClass.getText());
        String phone = txtPhone.getText() +"";
        String email = txtEmail.getText().toString();

        Student student = new Student(name,lop,phone,email);
        return student;
    }

    private void initWidget(){
        txtName = findViewById(R.id.txt_name);
        txtClass = findViewById(R.id.txt_class);
        txtPhone = findViewById(R.id.txt_phone);
        txtEmail = findViewById(R.id.txt_email);
        btnAdd = findViewById(R.id.btn_add);
        lv_st = findViewById(R.id.lv_students);
    }
    public void setAdapter(){
        if(customAdapter == null){
            customAdapter = new CustomAdapter(this, R.layout.item_student,students);
        }
        lv_st.setAdapter(customAdapter);
    }


}
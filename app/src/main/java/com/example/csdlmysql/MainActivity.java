package com.example.csdlmysql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnEdit;
    private Button btnDel;
    private EditText txtId;
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
                if (student != null) {
                    db.addStudent(student);
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                students.clear();
                students.addAll(db.getAll());
                setAdapter();
            }
        });
        lv_st.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);
                txtId.setText(student.getId() + "");
                txtName.setText(student.getName());
                txtClass.setText(student.getlop());
                txtPhone.setText(student.getPhone());
                txtEmail.setText(student.getemail());
                btnAdd.setEnabled(false);
                btnEdit.setEnabled(true);
                btnDel.setEnabled(true);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setId(Integer.parseInt(txtId.getText().toString()));
                student.setName(txtName.getText().toString());
                student.setlop(txtClass.getText().toString());
                student.setPhone(txtPhone.getText().toString());
                student.setemail(txtEmail.getText().toString());
                int result = db.editStudent(student);
                if (result > 0) {
                    btnAdd.setEnabled(true);
                    btnEdit.setEnabled(false);
                    btnDel.setEnabled(false);
                    students.clear();
                    students.addAll(db.getAll());
                    customAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    btnEdit.setEnabled(true);
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.icon_del);
                builder.setTitle("Xóa sinh viên");
                builder.setMessage("Bạn muốn xóa sinh viên này?");
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Student student = new Student();
                        int id = Integer.parseInt(txtId.getText().toString());
                        int result = db.delStudent(id);
                        if (result > 0) {
                            btnAdd.setEnabled(true);
                            btnEdit.setEnabled(false);
                            btnDel.setEnabled(false);
                            Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            students.clear();
                            students.addAll(db.getAll());
                            customAdapter.notifyDataSetChanged();
                        } else {
                            btnAdd.setEnabled(false);
                            btnEdit.setEnabled(true);
                            btnDel.setEnabled(true);
                            Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }

    private Student createSt() {
        String name = txtName.getText().toString();
        String lop = String.valueOf(txtClass.getText());
        String phone = txtPhone.getText() + "";
        String email = txtEmail.getText().toString();

        Student student = new Student(name, lop, phone, email);
        return student;
    }

    private void initWidget() {
        txtName = findViewById(R.id.txt_name);
        txtClass = findViewById(R.id.txt_class);
        txtPhone = findViewById(R.id.txt_phone);
        txtEmail = findViewById(R.id.txt_email);
        txtId = findViewById(R.id.txt_id);
        btnEdit = findViewById(R.id.btn_edit);
        btnAdd = findViewById(R.id.btn_add);
        btnDel = findViewById(R.id.btn_del);
        lv_st = findViewById(R.id.lv_students);
    }

    public void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_student, students);
            lv_st.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lv_st.setSelection(customAdapter.getCount() - 1);
        }
    }


}
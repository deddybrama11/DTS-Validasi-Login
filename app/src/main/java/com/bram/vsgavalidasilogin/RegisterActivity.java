package com.bram.vsgavalidasilogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername, editPassword, editEmail, editNamaLengkap, editAsalSekolah, editAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Register");

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editNamaLengkap = findViewById(R.id.editNamaLengkap);
        editAsalSekolah = findViewById(R.id.editAsalSekolah);
        editAlamat = findViewById(R.id.editAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()){
                    simpanFileData();
                }else{
                    Toast.makeText(RegisterActivity.this, "Mohon lengkapi seluruh data !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    boolean isValidation(){
        if (editUsername.getText().toString().equals("")){
            editUsername.setError("Harap isi field berikut");
            return false;
        }else if (editPassword.getText().toString().equals("")){
            editPassword.setError("Harap isi field berikut");
            return false;
        }else if (editNamaLengkap.getText().toString().equals("")){
            editNamaLengkap.setError("Harap isi field berikut");
            return false;
        }else if (editAsalSekolah.getText().toString().equals("")){
            editAsalSekolah.setError("Harap isi field berikut");
            return false;
        }else if (editAlamat.getText().toString().equals("")){
            editAlamat.setError("Harap isi field berikut");
            return false;
        }else {
            return true;
        }
    }

    void simpanFileData(){
        String isiFile = editUsername.getText().toString()+";"+
                editPassword.getText().toString()+";"+
                editEmail.getText().toString()+";"+
                editNamaLengkap.getText().toString()+";"+
                editAsalSekolah.getText().toString()+";"+
                editAlamat.getText().toString()+";";
        File file = new File(getFilesDir(), editUsername.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
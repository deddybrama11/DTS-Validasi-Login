package com.bram.vsgavalidasilogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    EditText editUsername, editPassword, editEmail, editNamaLengkap,
    editAsalSekolah, editAlamat;
    Button btnSimpan;
    TextView textViewPassword;

    public static final String FILENAME = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Halaman Depan");

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editNamaLengkap = findViewById(R.id.editNamaLengkap);
        editAsalSekolah = findViewById(R.id.editAsalSekolah);
        editAlamat = findViewById(R.id.editAlamat);
        textViewPassword = findViewById(R.id.textViewPassword);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setVisibility(View.GONE);
        textViewPassword.setVisibility(View.GONE);
        editUsername.setEnabled(false);
        editPassword.setVisibility(View.GONE);
        editEmail.setEnabled(false);
        editNamaLengkap.setEnabled(false);
        editAsalSekolah.setEnabled(false);
        editAlamat.setEnabled(false);

        bacaFileLogin();
    }
    void bacaFileLogin(){
        File sdcard = getFilesDir();
        File file = new File(sdcard,FILENAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line !=null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser(dataUser[0]);
        }
    }
    void bacaDataUser(String fileName){
        File sdcard = getFilesDir();
        File file = new File(sdcard,fileName);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String data= text.toString();
            String[] dataUser= data.split(";");

            editUsername.setText(dataUser[0]);
            editEmail.setText(dataUser[2]);
            editNamaLengkap.setText(dataUser[3]);
            editAsalSekolah.setText(dataUser[4]);
            editAlamat.setText(dataUser[5]);
        }else {
            Toast.makeText(this, "User Tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
        }
    }
    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                hapusFile();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                .setNegativeButton(android.R.string.no,null).show();
    }
}
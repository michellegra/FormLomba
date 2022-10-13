package com.if31.formlombaproggramming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TextView tvNama,tvJk,tvNoWhatsapp,tvAlamat,tvTanggal;
    Button btnTanggal,btnKonfirmasi;

    String nama,jk,noWhatsapp,alamat,choosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvNama=findViewById(R.id.tv_nama);
        tvJk=findViewById(R.id.tv_jk);
        tvNoWhatsapp=findViewById(R.id.tv_no_wa);
        tvAlamat=findViewById(R.id.tv_alamat);
        tvTanggal=findViewById(R.id.tv_tanggal);

        btnTanggal=findViewById(R.id.btn_tanggal);
        btnKonfirmasi=findViewById(R.id.btn_konfirmasi);

        //ambil Intent yang dikirim oleh MainActivity
        Intent terima =getIntent();
        nama = terima.getStringExtra("varNama");
        jk = terima.getStringExtra("varJenisKelamin");
        noWhatsapp = terima.getStringExtra("varNoWa");
        alamat = terima.getStringExtra("varAlamat");
        //set variabel
        tvNama.setText(nama);
        tvJk.setText(jk);
        tvNoWhatsapp.setText(noWhatsapp);
        tvAlamat.setText(alamat);

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Calendar newCalender = Calendar.getInstance();
                datePickerDialog=new DatePickerDialog(ConfirmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
                        String tahun =Integer.toString(year);
                        String bulan = Integer.toString(month + 1);
                        String tanggal = Integer.toString(dayofMonth);
                        choosenDate=tanggal + "/" + bulan + "/" + tahun ;
                        tvTanggal.setText(choosenDate);
                    }
                }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                //tampilkan datepickerDialog
                datePickerDialog.show();
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ConfirmActivity.this);
                dialog.setTitle("Perhatian");
                dialog.setMessage("Apakah data Anda yang Anda input telah benar?");

                //button positif
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ConfirmActivity.this, "TerimaKasih, Pendaftaran Anda Berhasil.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                //Button negatif
                dialog.setNegativeButton("TIdak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                //tampilkan dialog
                dialog.show();
            }
        });

    }
}
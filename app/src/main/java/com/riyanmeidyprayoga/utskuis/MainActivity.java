package com.riyanmeidyprayoga.utskuis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.riyanmeidyprayoga.utskuis.api.RestApi;
import com.riyanmeidyprayoga.utskuis.api.RetroServer;
import com.riyanmeidyprayoga.utskuis.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText nik, nim, nama, alamat, no;
    Button btnsave, btnTampildata, btnupdate,btndelete;
    ProgressBar pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd= (ProgressBar)findViewById(R.id.pd);
        pd.setIndeterminate(true);
        pd.setVisibility(View.GONE);

        nik = (EditText) findViewById(R.id.edt_nik);
        nim = (EditText) findViewById(R.id.edt_nim);
        nama = (EditText) findViewById(R.id.edt_nama);
        alamat = (EditText) findViewById(R.id.edt_alamat);
        no = (EditText) findViewById(R.id.edt_no);

        btnTampildata = (Button) findViewById(R.id.btntampildata);
        btnupdate =(Button) findViewById(R.id.btnUpdate);
        btnsave = (Button) findViewById(R.id.btn_insertdata);
        btndelete=(Button) findViewById(R.id.btnhapus);

        //kondisi perubahan btn save > btn delete dan btn update
        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            btnupdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);

            nik.setText(data.getStringExtra("nik"));
            nim.setText(data.getStringExtra("nim"));
            nama.setText(data.getStringExtra("nama"));
            alamat.setText(data.getStringExtra("alamat"));
            no.setText(data.getStringExtra("no"));
        }

        //btn update
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setVisibility(View.VISIBLE);
                RestApi api =
                        RetroServer.getClient().create(RestApi.class);
                Call<ResponseModel> update =
                        api.updateData(iddata,
                                nik.getText().toString(),
                                nim.getText().toString(),
                                nama.getText().toString(),
                                alamat.getText().toString(),
                                no.getText().toString());

                update.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call,
                                           Response<ResponseModel> response) {
                        Log.d("Retro", "Response");

                        Toast.makeText(MainActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, TampilData.class));
                        pd.setVisibility(View.GONE);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call,
                                          Throwable t) {
                        pd.setVisibility(View.GONE);
                        Log.d("Retro", "OnFailure");
                    }
                });
            }
        });
        //btn delete
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setVisibility(View.VISIBLE);
                RestApi api =
                        RetroServer.getClient().create(RestApi.class);
                Call<ResponseModel> del = api.deleteData(iddata);
                del.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call,
                                           Response<ResponseModel> response) {
                        pd.setVisibility(View.GONE);
                        Log.d("Retro", "onResponse");
                        Toast.makeText(MainActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent gotampil = new Intent(MainActivity.this,TampilData.class);
                        startActivity(gotampil);
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.setVisibility(View.GONE);
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });

        //btn tampil data
        btnTampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new
                        Intent(MainActivity.this,TampilData.class));
            }
        });

        //button insert
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String snik = nik.getText().toString();
                String snim = nim.getText().toString();
                String snama = nama.getText().toString();
                String salamat = alamat.getText().toString();
                String sno = no.getText().toString();
                if (snik.isEmpty() ) {
                    nik.setError("nik perlu di isi");
                }else if (snim.isEmpty() ) {
                    nim.setError("nim perlu di isi");
                }else if (snama.isEmpty() ) {
                    nama.setError("nama perlu di isi");
                }else if (salamat.isEmpty()){
                    alamat.setError("alamat perlu di isi");
                }else if (sno.isEmpty()){
                    no.setError("no hp perlu di isi");
                } else {
                    RestApi api = RetroServer.getClient().create(RestApi.class);
                    Call<ResponseModel> sendbio = api.sendBiodata(snik, snim, snama, salamat, sno);
                    sendbio.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            Log.d("RETRO", "response : " + response.body().toString());
                            String kode = response.body().getKode();
                            if(kode.equals("1"))
                            {
                                Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, TampilData.class));
                                nik.getText().clear();
                                nim.getText().clear();
                                nama.getText().clear();
                                alamat.getText().clear();
                                no.getText().clear();
                            }else
                            {
                                Toast.makeText(MainActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                        }
                    });
                }}
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("warnig");
        alert.setMessage("do you wan to exit");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                });
        alert.setNegativeButton("no", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int
                            i) {
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}

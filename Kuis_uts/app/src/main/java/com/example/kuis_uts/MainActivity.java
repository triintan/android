package com.example.kuis_uts;

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
import com.example.kuis_uts.api.RestApi;
import com.example.kuis_uts.api.RetroServer;
import com.example.kuis_uts.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {
    EditText kode, nama, kategori;
    Button btnsave, btnTampildata;
    ProgressBar pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd= (ProgressBar)findViewById(R.id.pd);
        pd.setIndeterminate(true);
        pd.setVisibility(View.GONE);
        kode = (EditText) findViewById(R.id.et_kode);
        nama = (EditText) findViewById(R.id.et_nama);
        kategori = (EditText) findViewById(R.id.et_kategori);
        btnTampildata = (Button) findViewById(R.id.btntampildata);
        btnsave = (Button) findViewById(R.id.btn_insertdata);


        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            kode.setText(data.getStringExtra("kode"));
            nama.setText(data.getStringExtra("nama"));
            kategori.setText(data.getStringExtra("kategori"));
        }
        //btn tampil
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
                String skode = kode.getText().toString();
                String snama = nama.getText().toString();
                String skategori = kategori.getText().toString();
                if (skode.isEmpty() ) {
                    nama.setError("kode perlu di isi");
                }else if (snama.isEmpty()){
                    nama.setError("nama harus di isi");}
                else if (skategori.isEmpty()){
                    kategori.setError("kategori perlu di isi");
                } else {
                    RestApi api =
                            RetroServer.getClient().create(RestApi.class);
                    Call<ResponseModel> sendbio =
                            api.sendBiodata(skode,snama,skategori);
                    sendbio.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call,
                                               Response<ResponseModel> response) {

                            Log.d("RETRO", "response : " +
                                    response.body().toString());
                            String kode = response.body().getKode();
                            if(kode.equals("1"))
                            {
                                Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                        startActivity(new
                                                Intent(MainActivity.this, TampilData.class));
                                kode.getBytes().toString();
                                nama.getText().clear();
                                kategori.getText().clear();
                            }else
                            {
                                Toast.makeText(MainActivity.this, "Data Error tidak dapat disimpan", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseModel> call,
                                              Throwable t) {

                            Log.d("RETRO", "Falure : " + "Gagal Mengirim data");
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
        alert.setPositiveButton("yes", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int
                            i) {
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
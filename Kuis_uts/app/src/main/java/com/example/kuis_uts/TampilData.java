package com.example.kuis_uts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.kuis_uts.adapter.RecylerAdapter;
import com.example.kuis_uts.api.RestApi;
import com.example.kuis_uts.api.RetroServer;
import com.example.kuis_uts.model.DataModel;
import com.example.kuis_uts.model.ResponseModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class TampilData extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressBar pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);
        pd = (ProgressBar) findViewById(R.id.pd);
        pd.setIndeterminate(true);
        pd.setVisibility(View.VISIBLE);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mManager);

        RestApi api = RetroServer.getClient().create(RestApi.class);
        Call<ResponseModel> getdata = api.getBiodata();
        getdata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call,
                                   Response<ResponseModel> response) {
                pd.setVisibility(View.GONE);
                Log.d("RETRO", "RESPONSE : " +
                        response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new
                        RecylerAdapter(TampilData.this, mItems);
                mRecycler.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable
                    t) {
                pd.setVisibility(View.GONE);

                Log.d("RETRO", "FAILED : respon gagal");
            }
        });
    }
}

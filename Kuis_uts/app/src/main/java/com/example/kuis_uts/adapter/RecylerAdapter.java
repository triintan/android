package com.example.kuis_uts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuis_uts.MainActivity;
import com.example.kuis_uts.R;
import com.example.kuis_uts.model.DataModel;
import java.util.List;

public class RecylerAdapter extends
        RecyclerView.Adapter<RecylerAdapter.MyHolder> {
    List<DataModel> mList ;
    Context ctx;
    public RecylerAdapter(Context ctx, List<DataModel> mList) {
        this.mList = mList;
        this.ctx = ctx;
    }
    @Override
    public RecylerAdapter.MyHolder onCreateViewHolder(ViewGroup
                                                              parent, int viewType) {
        View layout =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,
                        parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecylerAdapter.MyHolder holder,
                                 final int position) {
        holder.kode.setText(mList.get(position).getkode());
        holder.nama.setText(mList.get(position).getNama());
        holder.kategori.setText(mList.get(position).getKategori());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx,MainActivity.class);
                try {
                    goInput.putExtra("kode",
                            mList.get(position).getkode());
                    goInput.putExtra("nama",
                            mList.get(position).getNama());
                    goInput.putExtra("kategori",
                            mList.get(position).getKategori());
                    ctx.startActivity(goInput);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ctx, "Error data " +e,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public int getItemCount()
    {
        return mList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView kode, nama, kategori;
        DataModel dataModel;
        public MyHolder(View v)
        {
            super(v);
            kode = (TextView) v.findViewById(R.id.tvKode);
            nama = (TextView) v.findViewById(R.id.tvNama);
            kategori = (TextView) v.findViewById(R.id.tvKategori);
        }
    }




}

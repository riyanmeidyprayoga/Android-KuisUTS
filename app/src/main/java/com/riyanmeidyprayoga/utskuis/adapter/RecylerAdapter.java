package com.riyanmeidyprayoga.utskuis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.riyanmeidyprayoga.utskuis.MainActivity;
import com.riyanmeidyprayoga.utskuis.R;
import com.riyanmeidyprayoga.utskuis.model.DataModel;

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
    public RecylerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecylerAdapter.MyHolder holder, final int position) {
        holder.nim.setText(mList.get(position).getNim());
        holder.nik.setText(mList.get(position).getNik());
        holder.nama.setText(mList.get(position).getNama());
        holder.alamat.setText(mList.get(position).getAlamat());
        holder.no.setText(mList.get(position).getNo());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, MainActivity.class);
                try {
                    goInput.putExtra("id",
                            mList.get(position).getId());
                    goInput.putExtra("nim",
                            mList.get(position).getNim());
                    goInput.putExtra("nik",
                            mList.get(position).getNik());
                    goInput.putExtra("nama",
                            mList.get(position).getNama());
                    goInput.putExtra("alamat",
                            mList.get(position).getAlamat());
                    goInput.putExtra("no",
                            mList.get(position).getNo());
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
        TextView nik, nim, nama, alamat, no;
        DataModel dataModel;
        public MyHolder(View v)
        {
            super(v);
            nik = (TextView) v.findViewById(R.id.tvNik);
            nim = (TextView) v.findViewById(R.id.tvNim);
            nama = (TextView) v.findViewById(R.id.tvNama);
            alamat = (TextView) v.findViewById(R.id.tvAlamat);
            no = (TextView) v.findViewById(R.id.tvNo);

        }
    }
}

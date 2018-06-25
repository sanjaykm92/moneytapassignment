package com.example.sanjay.moneytapassignment.MainActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sanjay.moneytapassignment.MainActivity.model.Page;
import com.example.sanjay.moneytapassignment.R;
import com.example.sanjay.moneytapassignment.SecondActivity.SecondActivity;

import java.util.ArrayList;
import java.util.List;

public class MainAccAdapter {

    private MainPageAdpater mAdaptew;
    private Context context;
    private List<Page> data;

    public MainAccAdapter(Context contextForAdapter){
        this.context = contextForAdapter;
    }

    public void setData(List<Page> pages){
        if(data==null)
            data = new ArrayList<>();
        data.clear();
        data.addAll(pages);
    }

    public RecyclerView.Adapter getAdpter() {
        if (mAdaptew == null)
            mAdaptew = new MainPageAdpater();
        return mAdaptew;
    }

    private class MainPageAdpater extends RecyclerView.Adapter<MainPageAdapViewHolder>{

        @Override
        public MainPageAdapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainPageAdapViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_content, parent, false));
        }

        @Override
        public void onBindViewHolder(MainPageAdapViewHolder holder, int position) {

            holder.mTitle.setText(data.get(position).getTitle());
            holder.mDesc.setText(data.get(position).getTerms().getDescription().get(0));

            if (data.get(position).getThumbnail() != null
                    && data.get(position).getThumbnail().getSource()!=null
                    && !data.get(position).getThumbnail().getSource().isEmpty())
                Glide.with(context).load(data.get(position).getThumbnail().getSource())
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.no_image)
                        .into(holder.mImageview);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class MainPageAdapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitle, mDesc;
        private ImageView mImageview;
        private LinearLayout ll1,ll2,ll3;

        public MainPageAdapViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.main_rv_cont_title);
            mDesc = (TextView) itemView.findViewById(R.id.main_rv_cont_desc);
            mImageview = (ImageView) itemView.findViewById(R.id.main_rv_cont_iv);
            ll1 = (LinearLayout) itemView.findViewById(R.id.main_rv_cont_ll);
            ll2 = (LinearLayout) itemView.findViewById(R.id.main_rv_cont_ll1);
            ll3 = (LinearLayout) itemView.findViewById(R.id.main_rv_cont_ll2);

            ll1.setOnClickListener(this);
            ll2.setOnClickListener(this);
            ll3.setOnClickListener(this);

            mTitle.setOnClickListener(this);
            mDesc.setOnClickListener(this);
            mImageview.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("link",data.get(getAdapterPosition()).getTitle());
            context.startActivity(intent);
        }
    }

}

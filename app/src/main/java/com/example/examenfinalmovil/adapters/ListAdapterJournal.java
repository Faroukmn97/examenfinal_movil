package com.example.examenfinalmovil.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examenfinalmovil.R;
import com.example.examenfinalmovil.models.JournalModel;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterJournal extends RecyclerView.Adapter<ListAdapterJournal.ViewHolder> implements  View.OnClickListener{

    private List<JournalModel> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    public ListAdapterJournal(List<JournalModel> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ListAdapterJournal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_product,null,false);
        View view = mInflater.inflate(R.layout.element_list_journal, parent,false);
        view.setOnClickListener(this);
        return new ListAdapterJournal.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterJournal.ViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.description.setText(mData.get(position).getDescription());

        Glide.with(context).load("https://as.com/diarioas/imagenes/2021/01/31/actualidad/1612128752_898054_1612128997_noticia_normal_recorte1.jpg").into(holder.portada);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView portada;
        TextView journal_id, abbreviation, description,journalThumbnail,name;

        ViewHolder (View itemView){
            super(itemView);
            portada = itemView.findViewById(R.id.journalmageView);
            name = itemView.findViewById(R.id.journalmname);
            description = itemView.findViewById(R.id.journaldescription);
        }
    }

    public void filter(ArrayList<JournalModel> filterPpharmacy){
        this.mData = filterPpharmacy;
        notifyDataSetChanged();
    }
}

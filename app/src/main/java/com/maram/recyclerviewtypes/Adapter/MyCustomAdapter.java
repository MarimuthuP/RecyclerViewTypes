package com.maram.recyclerviewtypes.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.maram.recyclerviewtypes.R;
import com.maram.recyclerviewtypes.View.IMainCommunicator;

import java.util.ArrayList;

/**
 * Created by Marimuthu on 9/27/17.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> contentNameList;
    ArrayList<String> subContentNameList;
    IMainCommunicator iMainCommunicator;

    public MyCustomAdapter(Context context, ArrayList<String> arrayListContent, ArrayList<String> arrayListSubContent, IMainCommunicator iMainCommunicator) {
        this.context = context;
        contentNameList = arrayListContent;
        subContentNameList = arrayListSubContent;
        this.iMainCommunicator = iMainCommunicator;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_grid_layout,parent,false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.rowTitle.setText(contentNameList.get(position));
        holder.rowSubTitle.setText(subContentNameList.get(position));
        holder.rowTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked - "+holder.rowTitle.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rowTitle;
        TextView rowSubTitle;
        ImageView imageViewRemove;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowTitle = (TextView)itemView.findViewById(R.id.text_content);
            rowSubTitle = (TextView)itemView.findViewById(R.id.text_sub_title);
            imageViewRemove = (ImageView) itemView.findViewById(R.id.image_delete);
        }
    }

    public void showItemInList(){
        Toast.makeText(context, "List Menu Clicked", Toast.LENGTH_SHORT).show();
    }

    public void showItemInGrid(){
        Toast.makeText(context, "Grid Menu Clicked", Toast.LENGTH_SHORT).show();
    }
}

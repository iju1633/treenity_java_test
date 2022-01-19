package com.example.treenity_java_test.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.treenity_java_test.R;
import com.example.treenity_java_test.model.TreeModel;


import java.util.List;

public class TreeListAdapter extends RecyclerView.Adapter<TreeListAdapter.MyViewHolder> {

    private Context context;
    private List<TreeModel> treeList;
    private ItemClickListener clickListener;

    public TreeListAdapter(Context context, List<TreeModel> treeList, ItemClickListener clickListener) {
        this.context = context;
        this.treeList = treeList;
        this.clickListener = clickListener;
    }

    public void setTreeList(List<TreeModel> treeList) {
        this.treeList = treeList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TreeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    // position 과 관련된 이슈는 https://stackoverflow.com/questions/34942840/lint-error-do-not-treat-position-as-fixed-only-use-immediately 참고
    public void onBindViewHolder(@NonNull TreeListAdapter.MyViewHolder holder, int position) {
        holder.treeName.setText(this.treeList.get(position).getName().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onTreeClick(treeList.get(holder.getBindingAdapterPosition()));
            }
        });

        Glide.with(context)
                .load(this.treeList.get(position)
                        .getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.treeList != null) {
            return this.treeList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView treeName;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            treeName = (TextView) itemView.findViewById(R.id.nameView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public interface ItemClickListener {
        public void onTreeClick(TreeModel tree);
    }
}


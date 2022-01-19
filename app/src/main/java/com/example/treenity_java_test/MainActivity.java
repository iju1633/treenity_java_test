package com.example.treenity_java_test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.treenity_java_test.adapter.TreeListAdapter;
import com.example.treenity_java_test.model.TreeModel;
import com.example.treenity_java_test.viewmodel.TreeListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TreeListAdapter.ItemClickListener{

    private List<TreeModel> treeModelList;
    private TreeListAdapter adapter;
    private TreeListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noResult = findViewById(R.id.noResultTree);

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TreeListAdapter(this, treeModelList, this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TreeListViewModel.class);
        viewModel.getTreeListObserver().observe(this, new Observer<List<TreeModel>>() {
            @Override
            public void onChanged(List<TreeModel> treeModels) {
                if(treeModels != null) {
                    treeModelList = treeModels;
                    adapter.setTreeList(treeModels);
                    noResult.setVisibility(View.GONE);
                } else {
                    noResult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onTreeClick(TreeModel tree) {
        Toast.makeText(this, "Clicked tree name is " + tree.getName(), Toast.LENGTH_SHORT).show();
        // 에뮬레이터에서는 toast 가 안 뜨는 현상 발생, 안드로이드 기기에서는 잘 뜸
    }
}


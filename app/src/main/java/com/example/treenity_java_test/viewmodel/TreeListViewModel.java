package com.example.treenity_java_test.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.treenity_java_test.model.TreeModel;
import com.example.treenity_java_test.network.APIService;
import com.example.treenity_java_test.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TreeListViewModel extends ViewModel {

    private MutableLiveData<List<TreeModel>> treeList;

    public TreeListViewModel() {
        treeList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TreeModel>> getTreeListObserver() {
        return treeList;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<TreeModel>> call = apiService.getTreeList();
        call.enqueue(new Callback<List<TreeModel>>() {
            @Override
            public void onResponse(Call<List<TreeModel>> call, Response<List<TreeModel>> response) {
                treeList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TreeModel>> call, Throwable t) { // 내용이 없다면 아무것도 출력시키지 않음
                treeList.postValue(null);
            }
        });
    }
}


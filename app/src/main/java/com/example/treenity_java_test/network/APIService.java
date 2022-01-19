package com.example.treenity_java_test.network;



import com.example.treenity_java_test.model.TreeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("tree_data.json")
    Call<List<TreeModel>> getTreeList();
}

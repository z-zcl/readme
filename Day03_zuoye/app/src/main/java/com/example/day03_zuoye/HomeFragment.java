package com.example.day03_zuoye;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView recycler;
    private ArrayList<InfoBean.DataBean.DatasBean> datasBeans;
    int page=1;
    private RecyclerAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(getView());
        initUrl();
    }

    private void initUrl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiec.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServiec serviec = retrofit.create(ApiServiec.class);
        Call<InfoBean> data = serviec.getData(page);
        data.enqueue(new Callback<InfoBean>() {
            @Override
            public void onResponse(Call<InfoBean> call, Response<InfoBean> response) {
                InfoBean body = response.body();
                List<InfoBean.DataBean.DatasBean> datas = body.getData().getDatas();
                datasBeans.addAll(datas);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<InfoBean> call, Throwable t) {

            }
        });

    }

    private void initData(View view) {
        recycler = view.findViewById(R.id.reycycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        datasBeans = new ArrayList<>();
        adapter = new RecyclerAdapter(getActivity(), datasBeans);
        recycler.setAdapter(adapter);
        adapter.setOnClickItem(new RecyclerAdapter.OnClickItem() {
            @Override
            public void Click(View view, final int i) {
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popuwind, null);
                final EditText edit = inflate.findViewById(R.id.edit);
                Button delete = inflate.findViewById(R.id.delete);
                final Button add = inflate.findViewById(R.id.add);
                RelativeLayout rel = inflate.findViewById(R.id.rel);
                final PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                window.setFocusable(true);//弹出对话框可输入
                window.showAtLocation(recycler, Gravity.CENTER,0,0);
                rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.dismiss();
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s = edit.getText().toString();
                        InfoBean.DataBean.DatasBean bean = datasBeans.get(i);
                        bean.setTitle(s);
                        adapter.notifyDataSetChanged();
                        window.dismiss();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datasBeans.remove(i);
                        adapter.notifyDataSetChanged();
                        window.dismiss();
                    }
                });
            }

        });
    }
}

package com.bangkukayu.tolongrek.searchwithinputtext;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bangkukayu.tolongrek.searchwithinputtext.api.Client;
import com.bangkukayu.tolongrek.searchwithinputtext.api.Endpoint;
import com.bangkukayu.tolongrek.searchwithinputtext.model.Service;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements Intercator {
    @BindView(R.id.edittext_cari)
    EditText editTextCari;
    @BindView(R.id.text_no_data)
    TextView textViewNoData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapterService recyclerAdapterService;
    ArrayList<Service> services = new ArrayList<>();
    Endpoint client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapterService = new RecyclerAdapterService(this, services, this);
        recyclerView.setAdapter(recyclerAdapterService);
        client = Client.request().create(Endpoint.class);

        editTextCari.addTextChangedListener(new TextWatcher() {
            long lastChange = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new Handler().postDelayed(() -> {
                    if (System.currentTimeMillis() - lastChange >= 300) {
                        //send request
                        Call<ArrayList<Service>> call = client.getService(s.toString());
                        call.enqueue(new Callback<ArrayList<Service>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Service>> call, Response<ArrayList<Service>> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().size() > 0) {
                                        recyclerView.setVisibility(View.VISIBLE);
                                        textViewNoData.setVisibility(View.GONE);
                                        services.clear();
                                        services.addAll(response.body());
                                        Log.d("TAG", "onResponse: " + services.get(0).getName_service());
                                        recyclerAdapterService.notifyDataSetChanged();
                                    } else {
                                        textViewNoData.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Service>> call, Throwable t) {

                            }
                        });
                    }
                }, 300);
                lastChange = System.currentTimeMillis();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onSelected(Service service) {
        Intent data = new Intent();
        data.putExtra("service", service);
        setResult(RESULT_OK, data);
        finish();
    }
}

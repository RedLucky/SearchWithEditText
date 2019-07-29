package com.bangkukayu.tolongrek.searchwithinputtext;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bangkukayu.tolongrek.searchwithinputtext.model.Service;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final int SEARCH_CODE = 1;
    @BindView(R.id.text_hasil)
    TextView editTexthasil;
    @BindView(R.id.button_cari)
    Button buttonCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button_cari)
    public void click() {
        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
        startActivityForResult(i, SEARCH_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_CODE) {
            if (resultCode == RESULT_OK) {
                Service service = (Service) data.getSerializableExtra("service");
                editTexthasil.setText(service.getName_service());
            }
        }
    }
}

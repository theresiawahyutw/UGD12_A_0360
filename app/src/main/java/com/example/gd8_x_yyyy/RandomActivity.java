package com.example.gd8_x_yyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gd8_x_yyyy.api.ApiClient;
import com.example.gd8_x_yyyy.api.ApiInterface;
import com.example.gd8_x_yyyy.models.Random;
import com.example.gd8_x_yyyy.models.RandomResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomActivity extends AppCompatActivity {

    private ApiInterface apiService;
    private EditText etEmail, etPassword, etKotaAsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etKotaAsal = findViewById(R.id.etKotaAsal);

        Button btnSave = findViewById(R.id.btn_save);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(R.string.tambah_data);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRandom();
            }
        });

    }

    private void createRandom() {

        Random random = new Random(
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etKotaAsal.getText().toString());

        Call<RandomResponse> call = apiService.createRandom(random);
        call.enqueue(new Callback<RandomResponse>() {
            @Override
            public void onResponse(Call<RandomResponse> call,
                                   Response<RandomResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RandomActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(RandomActivity.this,
                                jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RandomActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<RandomResponse> call, Throwable t) {
                Toast.makeText(RandomActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
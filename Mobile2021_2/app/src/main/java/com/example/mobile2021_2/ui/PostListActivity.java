package com.example.mobile2021_2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobile2021_2.R;

import org.json.JSONArray;
import org.json.JSONException;

public class PostListActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        Button option1 = findViewById(R.id.button4);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlbumListActivity.class);
                startActivity(intent);
            }
        });
        Button option2 = findViewById(R.id.button6);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodoListActivity.class);
                startActivity(intent);
            }
        });



        //Aqui começa o Volley
        RequestQueue queue = Volley.newRequestQueue(this);

        //todos, posts, albums

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",
                null, this, this);
        queue.add(requisicao);
    }


    @Override
    public void onResponse(JSONArray response) {
        Button botao = findViewById(R.id.button5);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tView1 = findViewById(R.id.tView);
                TextView tView2 = findViewById(R.id.tView4);
                TextView tView3 = findViewById(R.id.tView5);
                TextView tView4 = findViewById(R.id.tView6);
                EditText editText = findViewById(R.id.editText);
                int indice = Integer.parseInt(String.valueOf(editText.getText())) - 1;
                if (indice>=1){
                    try {
                        tView1.setText("ID: " + response.getJSONObject(indice).getString("id"));
                        tView2.setText("Titulo: " + response.getJSONObject(indice).getString("title"));
                        tView4.setText("Corpo: " + response.getJSONObject(indice).getString("body"));
                        tView3.setText("ID do usuario: " + response.getJSONObject(indice).getString("userId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "deu erro"+error.getMessage(), Toast.LENGTH_LONG).show();
    }
}
package com.example.paperscanner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public ImageButton imageButton;
    public static final String EXTRA_MESSAGE = "Activity under production...";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
    }
    public void PdfButton(View view) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("first", EXTRA_MESSAGE);
        startActivity(intent);
    }

}
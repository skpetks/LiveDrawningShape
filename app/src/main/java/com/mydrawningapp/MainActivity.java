package com.mydrawningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private float smallBrush, mediumBrush, largeBrush;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
    }

//    public void paintClicked(View view){
//        //use chosen color
//
//        //set erase false
//        drawView.setErase(false);
//        drawView.setBrushSize(drawView.getLastBrushSize());
//
//        if(view!=currPaint){
//            ImageButton imgView = (ImageButton)view;
//            String color = view.getTag().toString();
//            drawView.setColor(color);
//            //update ui
//            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
//            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
//            currPaint=(ImageButton)view;
//        }
//    }

}
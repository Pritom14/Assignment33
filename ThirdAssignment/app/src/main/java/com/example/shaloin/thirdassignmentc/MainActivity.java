package com.example.shaloin.thirdassignmentc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    private static final int GALLERY=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load(View view){
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivity(gallery);
        startActivityForResult(gallery,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
                Uri image = data.getData();
                String[] filepath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(image, filepath, null, null, null);


                cursor.moveToFirst();
                int index = cursor.getColumnIndex(filepath[0]);
                String string = cursor.getString(index);
                cursor.close();
                ImageView imageView = (ImageView) findViewById(R.id.imageID);
                imageView.setImageBitmap(BitmapFactory.decodeFile(string));
            } else {
                Toast.makeText(getApplicationContext(), "You haven't selected", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this,"Wrong",Toast.LENGTH_LONG).show();
        }


    }
}

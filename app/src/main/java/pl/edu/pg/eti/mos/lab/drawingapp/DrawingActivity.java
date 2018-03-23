package pl.edu.pg.eti.mos.lab.drawingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class DrawingActivity extends AppCompatActivity {
    private DrawingField placeForDrawingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        placeForDrawingImage = findViewById(R.id.drawingField);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Would you like to get back to previous menu?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent returnIntent = new Intent();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        placeForDrawingImage.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        returnIntent.putExtra("image", byteArray);  //Prepare image(byteArray) to sent with key "image"
                        setResult(MainActivity.RESULT_OK, returnIntent);    //Send intent with image to MainActivity
                        finish();   //Close DrawingActivity
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Do nothing
                    }
                });
        alertDialogBuilder.create().show();
    }
}

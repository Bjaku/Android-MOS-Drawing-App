package pl.edu.pg.eti.mos.lab.drawingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button goDrawButton;
    private final static int IMAGE_GET_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent drawingActivity = new Intent(this, DrawingActivity.class );
        goDrawButton = findViewById(R.id.goDrawButton);
        goDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivityForResult(drawingActivity, MainActivity.IMAGE_GET_REQUEST);  //redirect to DrawingActivity (MainActivity will wait for feedback data - image)
            }
        });
    }

    @Override
    public void onBackPressed() {
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
       alertDialogBuilder.setTitle("Would you like to close the application?");
       alertDialogBuilder.setPositiveButton("Yes",
               new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       MainActivity.super.onBackPressed();  //Standard response for closing the app
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.IMAGE_GET_REQUEST) {    //Recognize type of message
            if(resultCode == MainActivity.RESULT_OK){
                byte[] imageAsByteArray = data.getByteArrayExtra("image");  //Image is being sent as a byte array
                Bitmap imageAsBitmap = BitmapFactory.decodeByteArray(imageAsByteArray, 0, imageAsByteArray.length); //Create bitmap
                ImageView placeForDisplayingImage = findViewById(R.id.placeForImage);
                placeForDisplayingImage.setImageBitmap(imageAsBitmap);  //Display image(bitmap)
            }
            if (resultCode == MainActivity.RESULT_CANCELED) {
                //Do nothing
            }
        }
    }
}

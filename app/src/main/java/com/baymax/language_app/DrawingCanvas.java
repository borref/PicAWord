package com.baymax.language_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.UUID;


public class DrawingCanvas extends Activity implements OnClickListener {

    private DrawingView drawView;
    private ImageButton currentColorPaint, drawBtn, eraseBtn, newBtn, saveBtn;
    private LinearLayout brush_chooser;
    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);

        currentColorPaint = (ImageButton)paintLayout.getChildAt(0); // Set the current color to the palette's first color
        currentColorPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        brush_chooser = (LinearLayout)findViewById(R.id.brush_chooser);
        brush_chooser.setVisibility(View.INVISIBLE);

        drawBtn = (ImageButton)findViewById(R.id.draw_button);
        drawBtn.setOnClickListener(this);

        drawView.setBrushSize(mediumBrush);

        eraseBtn = (ImageButton)findViewById(R.id.erase_button);
        eraseBtn.setOnClickListener(this);

        newBtn = (ImageButton)findViewById(R.id.new_button);
        newBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.save_button);
        saveBtn.setOnClickListener(this);
    }

    public void paintClicked(View view){
        //user chosen color
        if(view != currentColorPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);

            // Update the UI to reflect the new chosen color
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currentColorPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currentColorPaint = (ImageButton)view;

            // Set the last brush size if the user wants to draw again
            //drawView.setErase(false);
            drawView.setLastBrushSize(drawView.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View view) {
        // Responds to clicks

        if (view.getId() == R.id.draw_button) { // Draw button clicked
            brush_chooser.setVisibility(View.VISIBLE);
            /*final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size");
            brushDialog.setContentView(R.layout.brush_chooser);*/
            ImageButton smallBtn = (ImageButton)brush_chooser.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(false);
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    brush_chooser.setVisibility(View.INVISIBLE);
                }
            });

            ImageButton mediumBtn = (ImageButton)brush_chooser.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(false);
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    brush_chooser.setVisibility(View.INVISIBLE);
                }
            });

            ImageButton largeBtn = (ImageButton)brush_chooser.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(false);
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    brush_chooser.setVisibility(View.INVISIBLE);
                }
            });

            //brushDialog.show();
        } else if (view.getId() == R.id.erase_button) { // Erase button clicked
            brush_chooser.setVisibility(View.VISIBLE);
            /*final Dialog eraseDialog = new Dialog(this);
            eraseDialog.setTitle("Eraser size");
            eraseDialog.setContentView(R.layout.brush_chooser);*/

            ImageButton smallBtn = (ImageButton)brush_chooser.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brush_chooser.setVisibility(View.INVISIBLE);
                }
            });

            ImageButton mediumBtn = (ImageButton)brush_chooser.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brush_chooser.setVisibility(View.INVISIBLE);
                }
            });

            ImageButton largeBtn = (ImageButton)brush_chooser.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brush_chooser.setVisibility(View.INVISIBLE);
                }
            });

            //eraseDialog.show();
        } else if (view.getId() == R.id.new_button) { // New button clicked
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    drawView.setErase(false);
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        } else if (view.getId() == R.id.save_button) { // Save button clicked
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save drawing");
            saveDialog.setMessage("Save drawing to device Gallery?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //save drawing
                    drawView.setDrawingCacheEnabled(true);
                    String imgToSave = MediaStore.Images.Media.insertImage(
                            getContentResolver(), drawView.getDrawingCache(),
                            UUID.randomUUID().toString()+".png", "drawing");

                    String message = imgToSave != null ? "Drawing saved to Gallery!" : "Oops! Image could not be saved";

                    Toast messageToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    messageToast.show();
                    drawView.destroyDrawingCache();
                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }
    }
}

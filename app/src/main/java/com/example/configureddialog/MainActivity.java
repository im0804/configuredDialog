package com.example.configureddialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * @author inbar menahem
 * @version 1.0
 * @since 17/1/2023
 * the activity is making alarts in different situations.
 */
public class MainActivity extends AppCompatActivity {

    int rnd;
    AlertDialog.Builder adb;
    final String[] colors = {"Red", "Green", "Blue"};
    int[] color;
    LinearLayout linlay;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        linlay = findViewById(R.id.linlay);
        si = new Intent(this, creditsAct.class);
    }

    /**
     * First click.
     * change the background to one of the rgb color that was selected
     *
     * @param view the view
     * @return it changes the background color
     */
    public void firstClick(View view) {
        color = new int[] {0, 0, 0};
        adb.setTitle("list of colors - one choice");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                color[which] = 255;
                linlay.setBackgroundColor(Color.rgb(color[0], color[1],color[2]));
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Sec click.
     * it changes the background color when 'ok' is clicked
     *
     * @param view the view
     * @return the background color changes
     */
    public void secClick(View view) {
        adb.setTitle("change color");
        adb.setMessage("click 'OK' to change color");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                rnd = (int)(Math.random() * (3));
                if (rnd == 0){
                    linlay.setBackgroundColor(Color.RED);
                }
                else if (rnd == 1){
                    linlay.setBackgroundColor(Color.BLUE);
                }
                else {
                    linlay.setBackgroundColor(Color.GREEN);
                }
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Third click.
     * reset the background color back to white
     *
     * @param view the view
     */
    public void thirdClick(View view) {
        linlay.setBackgroundColor(Color.WHITE);
    }

    /**
     * Forth click.
     * you type any text you want and when 'copy' is clicked it shows it again as a toast
     *
     * @param view the view
     */
    public void forthClick(View view) {
        adb.setTitle("edit text dialog");
        final EditText eT = new EditText(this);
        eT.setHint("type text here");
        adb.setView(eT);
        adb.setPositiveButton("copy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String str = eT.getText().toString();
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.credits){
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String hexcode="#FFFFFF";
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playMusic(R.raw.background_music);
        ImageView ll = findViewById(R.id.body);
        TextView display = findViewById(R.id.display);
        Drawable backgroundDrawable = DrawableCompat.wrap(ll.getBackground()).mutate();
        display.setText(hexcode);
        SeekBar bar_r = findViewById(R.id.color_r);
        bar_r.setMax(255);
        bar_r.setProgress(255);
        bar_r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s =Integer.toHexString(i);
                if(s.length()==1)
                    s="0"+s;
                hexcode=hexcode.substring(0,1)+s+hexcode.substring(3,7);
                DrawableCompat.setTint(backgroundDrawable, Color.parseColor(hexcode));
                display.setText(hexcode.toUpperCase(Locale.ROOT));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SeekBar bar_g = findViewById(R.id.color_g);
        bar_g.setMax(255);
        bar_g.setProgress(255);
        bar_g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s =Integer.toHexString(i);
                if(s.length()==1)
                    s="0"+s;
                hexcode=hexcode.substring(0,3)+s+hexcode.substring(5,7);
                DrawableCompat.setTint(backgroundDrawable, Color.parseColor(hexcode));
                display.setText(hexcode.toUpperCase(Locale.ROOT));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SeekBar bar_b = findViewById(R.id.color_b);
        bar_b.setMax(255);
        bar_b.setProgress(255);
        bar_b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                s =Integer.toHexString(i);
                if(s.length()==1)
                    s="0"+s;
                hexcode=hexcode.substring(0,5)+s;
                DrawableCompat.setTint(backgroundDrawable, Color.parseColor(hexcode));
                display.setText(hexcode.toUpperCase(Locale.ROOT));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void copy(View v)
    {
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("HexCode", hexcode);
        manager.setPrimaryClip(clipData);
        Toast.makeText(this, "HexCode saved to clipboard", Toast.LENGTH_SHORT).show();
    }

    public static MediaPlayer music;
    public void playMusic(int id)
    {
        music = MediaPlayer.create(MainActivity.this, id);
        music.setLooping(true);
        music.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        music.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        music.start();
    }
}
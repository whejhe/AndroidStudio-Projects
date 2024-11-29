package com.dam.registro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sonidos extends AppCompatActivity {

    Button play;
    SoundPool sp;
    int sonidoDeRepro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sonidos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.logo_app);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#388E3C")));
        }

        play = findViewById(R.id.button_play);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        sp = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        sonidoDeRepro = sp.load(this, R.raw.sound_short, 1);
    }

    public void audioSoundPool(View view) {
        sp.play(sonidoDeRepro, 1, 1, 1, 0, 0);
    }

    public void audioMediaPlayer(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_long);
        mp.start();
    }

    public void irAtras(View view) {
        finish();
    }

    public void irMulti(View view) {
        Intent intent = new Intent(this, Multilenguaje.class);
        startActivity(intent);
    }
}
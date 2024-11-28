package com.dam.imagenes;

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

public class MainActivity extends AppCompatActivity {

    Button play, button3;
    SoundPool sp;
    int sonidoDeReproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.button_play);
        button3 = findViewById(R.id.button3);

        // Configurar SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        sp = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        sonidoDeReproduccion = sp.load(this, R.raw.sound_short, 1);

        EdgeToEdge.enable(this);

        // Configurar ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF333333")));
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asociar eventos
        play.setOnClickListener(this::audioSoundPool);
        button3.setOnClickListener(this::audioMediaPlayer);
    }

    public void audioSoundPool(View view) {
        if (sonidoDeReproduccion != 0) {
            sp.play(sonidoDeReproduccion, 1, 1, 0, 0, 1);
        }
    }

    public void audioMediaPlayer(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_long);

        if (mp != null) {
            mp.setOnCompletionListener(MediaPlayer::release); // Liberar recursos al finalizar
            mp.start();
        }
    }
}

package com.foo.accel.drum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Liza on 22.12.2016.
 */

public class Sequencer extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SoundPool sp;
    int soundIdfloortom;
    int soundIdcrashcimbal;
    int soundIdsnaredrumunmuffled;
    int soundIdtom12;
    int soundIdtom8;
    int soundIdhihatclosed;
    int soundIdlatintriangle;

    final int MAX_STREAMS = 6;

    SeekBar seekbarDuration, seekbarBPM;

    int duration=4;
    int bpm=120;

    AlertDialog dialog;

    String[] label = {"0","1","1/2", "1/4", "1/8", "1/16", "1/32", "1/64", "1/128"};
    int[] schetbpm = {0,1,2, 4, 8, 16, 32, 64, 128};

    int[] instruments = new int[20];
    int[] durations = new int[20];
    int counter = 0;
    boolean run = false;
    boolean flag = true;


    public void hat (View view) {
        if(counter<20) instruments[counter]=3;
    }

    public void work (View view) {
        if(counter<20) instruments[counter]=2;
    }

    public void crash (View view) {
        if(counter<20) instruments[counter]=1;
    }

    public void bass (View view) {
        if(counter<20) instruments[counter]=4;
    }

    public void tri (View view) {
        if(counter<20) instruments[counter]=5;
    }

    public void tom (View view) {
        if(counter<20) instruments[counter]=6;
    }

    public void mpause (View view) {
        if(counter<20) instruments[counter]=7;
    }

    public void stop (View view){
        run = false;

        for (int i = 0; i < counter; i++){
            durations[i]=0;
            instruments[i]=0;
        }
        counter = 0;

        TextView textView = (TextView)findViewById(R.id.textView11);
        textView.setText(" ");

        TextView textView2 = (TextView)findViewById(R.id.textView13);
        textView2.setText(" ");
    }

    public void pause (View view){run = false;}

    public void play (View view){

        if(flag) {
            flag = false;
            Thread myThready = new Thread(new Runnable() {
                public void run() //Этот метод будет выполняться в побочном потоке
                {
                    run = true;
                    go();
                    flag = true;
                }
            });
            myThready.start();    //Запуск потока

        }
    }


    @Override
    protected void onPause(){
// unregister listener
        super.onPause();
        run = false;
    }



    private void go() {


        while(run) {


            for (int i = 0; i < counter; i++) {
                if (instruments[i] == 1) {

                    sp.play(soundIdcrashcimbal, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                if (instruments[i] == 2) {

                    sp.play(soundIdsnaredrumunmuffled, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                if (instruments[i] == 3) {

                    sp.play(soundIdhihatclosed, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                if (instruments[i] == 4) {

                    sp.play(soundIdfloortom, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }


                if (instruments[i] == 5) {

                    sp.play(soundIdlatintriangle, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                if (instruments[i] == 6) {

                    sp.play(soundIdtom12, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                if (instruments[i] == 7) {

                    //sp.play(soundIdtom8, 1, 1, 0, 0, 1);

                    if(schetbpm[durations[i]] != 0) {
                        try {
                            Thread.sleep((240000 / bpm) / schetbpm[durations[i]]);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }



            }
        }
    }


    public void add(View view) {

        if(counter<20)
        {

            durations[counter] = seekbarDuration.getProgress();

            TextView textView = (TextView)findViewById(R.id.textView11);
            textView.append(instruments[counter] + " ");

            TextView textView2 = (TextView)findViewById(R.id.textView13);
            textView2.append(label[durations[counter]] + " ");

        counter++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sequencer, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        Intent intent;

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.play:

                intent = new Intent(Sequencer.this, SensorTestActivity.class);
                startActivity(intent);
                finish();

                return true;

            case R.id.help:
                dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("Help");
                dialog.setMessage(" 1. Select the first tool is" + "\n" + " 2. '+' To add it to memory" + "\n" + " 3. Repeat step 1-2 to add new instruments, the 'play' to play " + "\n" + " 4. You can also choose the duration for a specific tool. For simultaneous sound additional sounds to choose the duration equal to 0" + "\n" + " 1. Выбрать первый инструмент" + "\n" + " 2. '+' Для добавления его в память" + "\n" + " 3. Повторить п.1-2 для добавления новых инструментов, 'play' для воспроизведения " + "\n" + " 4. Также можно выбрать длительность звучания для отдельного инструмента. Для одновременного звучания дополнительным звукам выбрать длительность равной 0");
                dialog.setCancelable(true);
                dialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;

            case R.id.devs:

                dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("DEVS");
                dialog.setMessage("If the program is working fine, it created by Gulyaev Sergey and Risovanij Kolja, and if not, then I do not know who."+ "\n" + "Если прога работает нормально, ее создали Гуляев Сергей и Рисованый Коля, а если нет, то не знаю кто");
                dialog.setCancelable(true);
                dialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        update();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }


    private void update()
    {

        duration = seekbarDuration.getProgress();
        bpm = seekbarBPM.getProgress()+10;

        durations[counter]=duration;


        TextView textView3 = (TextView)findViewById(R.id.textView6);
        textView3.setText(label[duration] + " ");

        TextView textView4 = (TextView)findViewById(R.id.textView8);
        textView4.setText(bpm + " ");

    }





    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sequencer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        //sp.setOnLoadCompleteListener(this);
        soundIdfloortom = sp.load(this, R.raw.floortom, 1);
        soundIdcrashcimbal = sp.load(this, R.raw.crashcymbal, 1);
        soundIdsnaredrumunmuffled = sp.load(this, R.raw.snaredrumunmuffled, 1);
        soundIdtom12 = sp.load(this, R.raw.tom12, 1);
        soundIdtom8 = sp.load(this, R.raw.tom8, 1);
        soundIdhihatclosed = sp.load(this, R.raw.hihatclosed, 1);
        soundIdlatintriangle = sp.load(this, R.raw.latintriangle, 1);


        seekbarDuration = (SeekBar)findViewById(R.id.seekBarDuration);
        seekbarDuration.setOnSeekBarChangeListener(this);

        seekbarBPM = (SeekBar)findViewById(R.id.seekBarBPM);
        seekbarBPM.setOnSeekBarChangeListener(this);



    }






}

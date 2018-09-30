package com.foo.accel.drum;

import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;

import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Liza on 11.05.2017.
 */

public class Screen4  extends AppCompatActivity implements SensorEventListener {

    private View view;//Ссылка на TextView
    //private SensorManager sensorManager;
    //private long lastUpdate;//Время последнего изменения состояния датчика

    SoundPool sp;
    int soundIdfloortom;
    int soundIdcrashcimbal;
    int soundIdsnaredrumunmuffled;
    int soundIdsnaredrummuffled;
    int soundIdhihatclosed;
    int soundIdlatintriangle;
    int soundIdbassdrum;
    int soundIdtom12;
    int soundIdhihatopen;
    int soundIdtom8;

    int sidTatar;
    int sidUdo;
    int sidBongo;
    int sidTamtam;

    int sidCast;

    final int MAX_STREAMS = 6;


    AlertDialog dialog;


    int mWindowWidth;
    int mWindowHeight;

    //int[] x = new int[10];
    //int[] y = new int[10];
    //int PointerCount = 0;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_screen, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        // Операции для выбранного пункта меню
        switch (id) {

            case R.id.accel:
                Intent intent = new Intent(Screen4.this, SensorTestActivity.class);
                startActivity(intent);
                finish();
                return true;


            case R.id.min:

                Intent intent2 = new Intent(Screen4.this, Screen.class);
                startActivity(intent2);
                finish();

                return true;
            case R.id.symphonic:


                return true;
            case R.id.rock:
                Intent intent4 = new Intent(Screen4.this, Screen3.class);
                startActivity(intent4);
                finish();


                return true;
            case R.id.folk:
                Intent intent3 = new Intent(Screen4.this, Screen2.class);
                startActivity(intent3);
                finish();


                return true;
            case R.id.advanced:
                Intent intent5 = new Intent(Screen4.this, Sequencer.class);
                startActivity(intent5);
                //finish();

                return true;

            case R.id.help:
                dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("Help");
                dialog.setMessage("Clicking on the image of tools, get the appropriate sound. "+ "\n" + " Нажимая на изображения инструментов, получайте соответствующее звучание");
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







    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void onSensorChanged (SensorEvent event){}



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int id = event.getActionIndex();
        float x = event.getX(id);// Запоминаем координаты
        float y = event.getY(id);


        switch (event.getActionMasked() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN: // нажатие


                if(x>0.25*mWindowWidth && x<0.7*mWindowWidth && y<0.35*mWindowHeight)
                {
                    sp.play(sidTatar, 1, 1, 0, 0, 1);
                }


                if(x>0.25*mWindowWidth && x<0.5*mWindowWidth && y>0.35*mWindowHeight && y<0.6*mWindowHeight)
                {
                    sp.play(sidUdo, 1, 1, 0, 0, 1);
                }

                if(x<0.25*mWindowWidth  && y>0.75*mWindowHeight)
                {
                    sp.play(sidBongo, 1, 1, 0, 0, 1);
                }

                if(x>0.25*mWindowWidth && x<0.5*mWindowWidth && y>0.75*mWindowHeight)
                {
                    sp.play(sidTamtam, 1, 1, 0, 0, 1);
                }


                if(x>0.7*mWindowWidth && y<0.35*mWindowHeight)
                {
                    sp.play(soundIdcrashcimbal, 1, 1, 0, 0, 1);
                }

                if(x>0.5*mWindowWidth && y>0.45*mWindowHeight && y<0.6*mWindowHeight )
                {
                    sp.play(soundIdsnaredrummuffled, 1, 1, 0, 0, 1);
                }

                if(x>0.75*mWindowWidth && y>0.75*mWindowHeight)
                {
                    sp.play(soundIdlatintriangle, 1, 1, 0, 0, 1);
                }


                if(x<0.25*mWindowWidth && y>0.65*mWindowHeight && y<0.75*mWindowHeight )
                {
                    sp.play(soundIdfloortom, 1, 1, 0, 0, 1);
                }


                if(x>0.5*mWindowWidth && y>0.2*mWindowHeight && y<0.45*mWindowHeight)
                {
                    sp.play(soundIdhihatopen, 1, 1, 0, 0, 1);
                }


                if(x<0.25*mWindowWidth && y>0.45*mWindowHeight && y<0.65*mWindowHeight )
                {
                    sp.play(soundIdhihatclosed, 1, 1, 0, 0, 1);
                }



                if(x>0.5*mWindowWidth && x<0.75*mWindowWidth && y>0.75*mWindowHeight)
                {
                    sp.play(soundIdsnaredrumunmuffled, 1, 1, 0, 0, 1);
                }


                if(x>0.25*mWindowWidth && x<0.7*mWindowWidth && y>0.6*mWindowHeight && y<0.75*mWindowHeight)
                {
                    sp.play(soundIdtom12, 1, 1, 0, 0, 1);
                }


                if(x<0.25*mWindowWidth && y<0.35*mWindowHeight)
                {
                    sp.play(soundIdtom8, 1, 1, 0, 0, 1);
                }


                if(x>0.7*mWindowWidth && y>0.6*mWindowHeight && y<0.75*mWindowHeight)
                {
                    sp.play(soundIdbassdrum, 1, 1, 0, 0, 1);
                }


                if(x<0.25*mWindowWidth && y>0.35*mWindowHeight && y<0.45*mWindowHeight)
                {
                    sp.play(sidCast, 1, 1, 0, 0, 1);
                }



                break;
        }

        // TextView textView = (TextView)findViewById(R.id.textView2);
        // textView.setText("x" + x + "\n" + "y" + y);
        return true;
    }


    public void onButton2(View view) {
        Intent intent = new Intent(Screen4.this, SensorTestActivity.class);
        startActivity(intent);
        finish();
    }




    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Point size = new Point();
        WindowManager w = getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            w.getDefaultDisplay().getSize(size);

            mWindowWidth = size.x;
            mWindowHeight = size.y;
        } else {
            Display d = w.getDefaultDisplay();
            mWindowWidth = d.getWidth();
            mWindowHeight = d.getHeight();
        }

        view= findViewById(R.id.imageView);
        //view.setOnTouchListener(this);
        view.setBackgroundResource(R.drawable.sympho);

//Создаем объект, для работы с датчиками
        //sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
//Регистрируем класс, где будет реализован метод, вызываевый при изменении
//состояния датчика.
        //sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        //lastUpdate=System.currentTimeMillis();

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        //sp.setOnLoadCompleteListener(this);
        soundIdfloortom = sp.load(this, R.raw.sympho_bell, 1);
        soundIdcrashcimbal = sp.load(this, R.raw.sympho_big, 1);
        soundIdsnaredrumunmuffled = sp.load(this, R.raw.sympho_bubn, 1);
        soundIdsnaredrummuffled = sp.load(this, R.raw.sympho_gong_big, 1);
        soundIdhihatclosed = sp.load(this, R.raw.sympho_gong_min, 1);
        soundIdlatintriangle = sp.load(this, R.raw.sympho_litavr1, 1);
        soundIdbassdrum = sp.load(this, R.raw.sympho_litavr2, 1);
        soundIdtom12 = sp.load(this, R.raw.sympho_maracas, 1);
        soundIdhihatopen = sp.load(this, R.raw.sympho_pohod, 1);
        soundIdtom8 = sp.load(this, R.raw.sympho_tamb, 1);

        sidTatar  = sp.load(this, R.raw.sympho_tarelki, 1);
        sidUdo  = sp.load(this, R.raw.sympho_treschtki, 1);
        sidBongo = sp.load(this, R.raw.sympho_triangel, 1);
        sidTamtam = sp.load(this, R.raw.rock_snare, 1);
        sidCast = sp.load(this, R.raw.casts, 1);

    }


}

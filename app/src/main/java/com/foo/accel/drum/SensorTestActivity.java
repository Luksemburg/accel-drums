package com.foo.accel.drum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.util.TimeUnit;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.lang.Object;

/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
*/

public class SensorTestActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, SensorEventListener, SoundPool.OnLoadCompleteListener, View.OnTouchListener {

    private SensorManager sensorManager;//Объект для работы с датчиком
    private boolean color=false;//Индикатор текущего цвета: false-зеленый, true - красный.
    private View view;//Ссылка на TextView
    private long lastUpdate;//Время последнего изменения состояния датчика

    SoundPool sp;
    int soundIdfloortom;
    int soundIdcrashcimbal;
    int soundIdsnaredrumunmuffled;
    int soundIdsnaredrummuffled;
    int soundIdhihatclosed;
    int soundIdlatintriangle;

    boolean flag = false;

    final int MAX_STREAMS = 6;

    int delay = 160;
    float shake = 1.66f;

    AlertDialog dialog;


    SeekBar seekbarShake, seekbarDelay;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sensortestactivity, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        Intent intent;
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.minimal:

                return true;
            case R.id.spain:

                intent = new Intent(SensorTestActivity.this, SensorSpain.class);
                startActivity(intent);
                finish();

                return true;
            case R.id.ambient:

                intent = new Intent(SensorTestActivity.this, Ambient.class);
                startActivity(intent);
                finish();

                return true;

            case R.id.auto:
                Intent intent5 = new Intent(SensorTestActivity.this, Sequencer.class);
                startActivity(intent5);
                //finish();

                return true;

            case R.id.help:
                dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("Help");
                dialog.setMessage("Сотрясая устройство в разных направлениях, получайте соответствующее звучание, удерживая палец на экране, получите другое звучание");
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
        shake = 0.02f*seekbarShake.getProgress() + 1.4f;
        delay = 5*seekbarDelay.getProgress() + 70;
    }



    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                flag = true;
                break;
            case MotionEvent.ACTION_UP: // отпускание
                flag = false;
                break;
        }
        return true;
    }





    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        view= findViewById(R.id.textView);
        //view.setBackgroundColor(Color.GREEN);
        view.setBackgroundResource(R.drawable.minnn);
        view.setOnTouchListener(this);
        //setContentView(view);
//Создаем объект, для работы с датчиками
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
//Регистрируем класс, где будет реализован метод, вызываевый при изменении
//состояния датчика.
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        lastUpdate=System.currentTimeMillis();

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);
        soundIdfloortom = sp.load(this, R.raw.floortom, 1);
        soundIdcrashcimbal = sp.load(this, R.raw.crashcymbal, 1);
        soundIdsnaredrumunmuffled = sp.load(this, R.raw.snaredrumunmuffled, 1);
        soundIdsnaredrummuffled = sp.load(this, R.raw.snaredrummuffled, 1);
        soundIdhihatclosed = sp.load(this, R.raw.hihatclosed, 1);
        soundIdlatintriangle = sp.load(this, R.raw.latintriangle, 1);


        seekbarShake = (SeekBar)findViewById(R.id.seekBarShake);
        seekbarShake.setOnSeekBarChangeListener(this);

        seekbarDelay = (SeekBar)findViewById(R.id.seekBarDelay);
        seekbarDelay.setOnSeekBarChangeListener(this);






    }



    @Override
    protected void onResume(){
        super.onResume();
// register this class as a listener for the orientation and
// accelerometer sensors
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause(){
// unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }






        public void onSensorChanged (SensorEvent event){
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                float[] values = event.values;
// проекции ускорения на оси системы координат
                float x = values[0];
                float y = values[1];
                float z = values[2];



// квадрат модуля ускорения телефона, деленный на квадрат
//ускорения свободного падения
                    float accelationSquareRoot = (x * x)
                            / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

                    if (accelationSquareRoot > 4) {
                        accelationSquareRoot = 4;
                    }


                    float accelationSquareRoot2 = (y * y)
                            / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

                    if (accelationSquareRoot2 > 4) {
                        accelationSquareRoot2 = 4;
                    }


                    float accelationSquareRoot3 = (z * z)
                            / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

                    if (accelationSquareRoot3 > 4) {
                        accelationSquareRoot3 = 4;
                    }


//Текущее время
                    long actualTime = System.currentTimeMillis();

                    if (accelationSquareRoot >= shake && x > 0 && !flag)//Если тряска сильная
                    {
                        if (actualTime - lastUpdate < delay) {
//Если с момента начала тряски прошло меньше 200
// миллисекунд - выходим из обработчика
                            return;
                        }
                        lastUpdate = actualTime;

                        //
                        sp.play(soundIdsnaredrumunmuffled, 0.24f * accelationSquareRoot, 0.24f * accelationSquareRoot, 0, 0, 0.35f * accelationSquareRoot);

                    }


                    if (accelationSquareRoot2 >= shake && y > 0 && !flag)//Если тряска сильная
                    {
                        if (actualTime - lastUpdate < delay) {
//Если с момента начала тряски прошло меньше 200
// миллисекунд - выходим из обработчика
                            return;
                        }
                        lastUpdate = actualTime;

                        //
                        sp.play(soundIdfloortom, 0.24f * accelationSquareRoot2, 0.24f * accelationSquareRoot2, 0, 0, 0.5f * accelationSquareRoot2);

                    }


                    if (accelationSquareRoot3 >= shake && z > 0 && flag)//Если тряска сильная
                    {
                        if (actualTime - lastUpdate < delay) {
//Если с момента начала тряски прошло меньше 200
// миллисекунд - выходим из обработчика
                            return;
                        }
                        lastUpdate = actualTime;

                        //
                        sp.play(soundIdcrashcimbal, 0.24f * accelationSquareRoot3, 0.24f * accelationSquareRoot3, 0, 0, 0.5f * accelationSquareRoot3);

                    }









                if (accelationSquareRoot >= shake && x > 0 && flag)//Если тряска сильная
                {
                    if (actualTime - lastUpdate < delay) {
//Если с момента начала тряски прошло меньше 200
// миллисекунд - выходим из обработчика
                        return;
                    }
                    lastUpdate = actualTime;

                    //
                    sp.play(soundIdsnaredrummuffled, 0.24f * accelationSquareRoot, 0.24f * accelationSquareRoot, 0, 0, 0.35f * accelationSquareRoot);

                }


                if (accelationSquareRoot2 >= shake && y > 0 && flag)//Если тряска сильная
                {
                    if (actualTime - lastUpdate < delay) {
//Если с момента начала тряски прошло меньше 200
// миллисекунд - выходим из обработчика
                        return;
                    }
                    lastUpdate = actualTime;

                    //
                    sp.play(soundIdlatintriangle, 0.24f * accelationSquareRoot2, 0.24f * accelationSquareRoot2, 0, 0, 0.5f * accelationSquareRoot2);

                }


                if (accelationSquareRoot3 >= shake && z > 0 && !flag)//Если тряска сильная
                {
                    if (actualTime - lastUpdate < delay) {
//Если с момента начала тряски прошло меньше 200
// миллисекунд - выходим из обработчика
                        return;
                    }
                    lastUpdate = actualTime;

                    //
                    sp.play(soundIdhihatclosed, 0.24f * accelationSquareRoot3, 0.24f * accelationSquareRoot3, 0, 0, 0.5f * accelationSquareRoot3);

                }








            }

        }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
    {

    }


    public void onButton(View view){

        Intent intent = new Intent(SensorTestActivity.this, Screen.class);
        startActivity(intent);
        finish();

    }



}

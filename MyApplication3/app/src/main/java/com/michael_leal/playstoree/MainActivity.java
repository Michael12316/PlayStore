package com.michael_leal.playstoree;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView txtsensores;
    private TextView txtluz;
    private TextView txtacelerometro;

    private SensorManager sensorManager;
    private List<Sensor> sensores;

    private Sensor sensorL, sensorM;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtsensores = (TextView) findViewById(R.id.txt_sensores);
        txtluz = (TextView) findViewById(R.id.vluz);
        txtacelerometro = (TextView) findViewById(R.id.vacelerometro);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        int i = 1;
        for (Iterator<Sensor> it = sensores.iterator(); it.hasNext(); i++) {
            Sensor sensor = it.next();
            txtsensores.append(String.format("%d: %s, %d, %s\n", i, sensor.getName(), sensor.getType(), sensor.getVendor()));
        }

        sensorL= (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this,sensorL,sensorManager.SENSOR_DELAY_NORMAL);

        sensorM= (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensorM,sensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                  txtluz.setText(String.format("%f",sensorEvent.values[0]));
                break;

            case Sensor.TYPE_ACCELEROMETER:
                txtacelerometro.setText(String.format("%f",sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
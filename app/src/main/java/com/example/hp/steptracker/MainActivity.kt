package com.example.hp.steptracker

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , SensorEventListener {

    val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    var totalsteps=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stepCount.setText(totalsteps.toString())
    }


    override fun onStart() {
        super.onStart()
       val stepCounter= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        sensorManager.registerListener(this , stepCounter ,100)
    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {

          val values =it.values

            if(values.get(0).equals(1)) {
                totalsteps = totalsteps + 1
                stepCount.setText("${totalsteps}")
            }


        }
    }
}

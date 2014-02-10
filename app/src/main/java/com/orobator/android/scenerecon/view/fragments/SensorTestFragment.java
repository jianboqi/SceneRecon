package com.orobator.android.scenerecon.view.fragments;import android.app.Fragment;import android.content.Context;import android.hardware.Sensor;import android.hardware.SensorEvent;import android.hardware.SensorEventListener;import android.hardware.SensorManager;import android.os.Bundle;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import com.orobator.android.scenerecon.R;public class SensorTestFragment extends Fragment implements SensorEventListener {    private SensorManager mSensorManager;    private Sensor mGravitySensor;    private TextView xAxisGravityTextView;    private TextView yAxisGravityTextView;    private TextView zAxisGravityTextView;    public SensorTestFragment(Context context) {        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);        mGravitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);    }    @Override    public void onResume() {        super.onResume();        mSensorManager.registerListener(this, mGravitySensor, SensorManager.SENSOR_DELAY_NORMAL);    }    @Override    public void onPause() {        super.onPause();        // Unregister the listener to avoid leaking the sensor and wasting        // battery life        mSensorManager.unregisterListener(this);    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        View rootView = inflater.inflate(R.layout.fragment_sensor_test, container, false);        xAxisGravityTextView = (TextView) rootView.findViewById(R.id.xAxisGravity_textView);        yAxisGravityTextView = (TextView) rootView.findViewById(R.id.yAxisGravity_textView);        zAxisGravityTextView = (TextView) rootView.findViewById(R.id.zAxisGravity_textView);        return rootView;    }    @Override    public void onSensorChanged(SensorEvent event) {        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {            // units in m/s^2            double xAxisGravity = event.values[0];            double yAxisGravity = event.values[1];            double zAxisGravity = event.values[2];            String xAxis = getString(R.string.x_axis_gravity, xAxisGravity);            String yAxis = getString(R.string.y_axis_gravity, yAxisGravity);            String zAxis = getString(R.string.z_axis_gravity, zAxisGravity);            xAxisGravityTextView.setText(xAxis);            yAxisGravityTextView.setText(yAxis);            zAxisGravityTextView.setText(zAxis);        }    }    @Override    public void onAccuracyChanged(Sensor sensor, int accuracy) {    }}
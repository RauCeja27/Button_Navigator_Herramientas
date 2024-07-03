package com.example.button_navigator_herramientas;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNivel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNivel extends Fragment implements SensorEventListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView tvnivel2,btnNivel;
    View vista;
    private SensorManager sensorManager;
    private Sensor sensor;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentNivel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNivel.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNivel newInstance(String param1, String param2) {
        FragmentNivel fragment = new FragmentNivel();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista =inflater.inflate(R.layout.fragment_nivel, container, false);
        btnNivel=(TextView)vista.findViewById(R.id.btnNivel);
        tvnivel2=(TextView)vista.findViewById(R.id.tvnivel2);
        sensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        tvnivel2.setVisibility(vista.INVISIBLE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        btnNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable nivelDrawable = getResources().getDrawable(R.drawable.nivel);
                Drawable nivel2Drawable = getResources().getDrawable(R.drawable.nivel2);

                if (btnNivel.getBackground().getConstantState().equals(nivelDrawable.getConstantState())) {
                    btnNivel.setBackground(nivel2Drawable);
                    tvnivel2.setVisibility(View.VISIBLE);
                } else {
                    btnNivel.setBackground(nivelDrawable);
                    tvnivel2.setVisibility(View.INVISIBLE);
                }
            }
        });

        return vista;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float gyroX = event.values[0];
            float gyroY = event.values[1];
            float gyroZ = event.values[2];

            double nivel = Math.sqrt(gyroX * gyroX + gyroY * gyroY + gyroZ * gyroZ);
            int nivelInt = (int) nivel;
            tvnivel2.setText(String.format(" "+ nivelInt));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onResume() {
        super.onResume();
        // Registrar el listener del giroscopio
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Desregistrar el listener del giroscopio para conservar la batería
        sensorManager.unregisterListener(this);
    }

}
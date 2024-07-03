package com.example.button_navigator_herramientas;

import android.content.Context;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLinterna#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLinterna extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView btnLinterna;
    View vista;
    private boolean isFlashOn = false;
    private Camera camera;
    private CameraManager cameraManager;
    private String cameraId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLinterna() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLinterna.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLinterna newInstance(String param1, String param2) {
        FragmentLinterna fragment = new FragmentLinterna();
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
        vista =inflater.inflate(R.layout.fragment_linterna, container, false);
        btnLinterna = (TextView) vista.findViewById(R.id.btnLinterna);
        cameraManager = (CameraManager) requireActivity().getSystemService(Context.CAMERA_SERVICE);

        btnLinterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isFlashOn) {
                        turnOffFlash();
                    } else {
                        turnOnFlash();
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return vista;
    }
    private void turnOnFlash() throws CameraAccessException {
        cameraId = cameraManager.getCameraIdList()[0]; // asume que el primer índice es la cámara trasera

        if (cameraId != null) {
            cameraManager.setTorchMode(cameraId, true);
            isFlashOn = true;
            btnLinterna.setBackgroundResource(R.drawable.linterna2);
        }
    }

    // Método para apagar la linterna
    private void turnOffFlash() throws CameraAccessException {
        if (cameraId != null) {
            cameraManager.setTorchMode(cameraId, false);
            isFlashOn = false;
            btnLinterna.setBackgroundResource(R.drawable.linterna);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        try {
            // Apagar la linterna al pausar la actividad o fragmento
            if (isFlashOn) {
                turnOffFlash();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            // Reabrir la linterna si estaba encendida antes de pausar
            if (isFlashOn) {
                turnOnFlash();
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

}
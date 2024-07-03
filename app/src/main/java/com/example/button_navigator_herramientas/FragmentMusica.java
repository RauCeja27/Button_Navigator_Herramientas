package com.example.button_navigator_herramientas;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMusica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMusica extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnplay,btnpause,btnstop,btnnext,btnlast;
    TextView btnMusica;
    View vista;
    private ArrayList<Integer> listaMusica = new ArrayList<>();
    private int currentIndex = 0; // índice de la pista actual
    private MediaPlayer mediaPlayer;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMusica() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMusica.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMusica newInstance(String param1, String param2) {
        FragmentMusica fragment = new FragmentMusica();
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
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_musica, container, false);
        btnplay = (Button) vista.findViewById(R.id.btnplay);
        btnpause = (Button) vista.findViewById(R.id.btnpause);
        btnstop = (Button) vista.findViewById(R.id.btnstop);
        btnnext = (Button) vista.findViewById(R.id.btnnext);
        btnlast = (Button) vista.findViewById(R.id.btnlast);
        btnMusica = (TextView) vista.findViewById(R.id.btnMusica);

        listaMusica.add(R.raw.musica_1);
        listaMusica.add(R.raw.musica_2);
        listaMusica.add(R.raw.musica_3);
        listaMusica.add(R.raw.musica_4);
        listaMusica.add(R.raw.musica_5);
        listaMusica.add(R.raw.musica_6);
        listaMusica.add(R.raw.musica_7);


        btnplay.setVisibility(vista.INVISIBLE);
        btnpause.setVisibility(vista.INVISIBLE);
        btnstop.setVisibility(vista.INVISIBLE);
        btnnext.setVisibility(vista.INVISIBLE);
        btnlast.setVisibility(vista.INVISIBLE);

        btnMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable nivelDrawable = getResources().getDrawable(R.drawable.musica);
                Drawable nivel2Drawable = getResources().getDrawable(R.drawable.musica2);

                if (btnMusica.getBackground().getConstantState().equals(nivelDrawable.getConstantState())) {
                    btnMusica.setBackground(nivel2Drawable);
                    btnlast.setVisibility(View.VISIBLE);
                    btnnext.setVisibility(View.VISIBLE);
                    btnplay.setVisibility(View.VISIBLE);
                    btnpause.setVisibility(View.VISIBLE);
                    btnstop.setVisibility(View.VISIBLE);
                } else {
                    btnMusica.setBackground(nivelDrawable);
                    btnplay.setVisibility(vista.INVISIBLE);
                    btnpause.setVisibility(vista.INVISIBLE);
                    btnstop.setVisibility(vista.INVISIBLE);
                    btnnext.setVisibility(vista.INVISIBLE);
                    btnlast.setVisibility(vista.INVISIBLE);
                    stopMusica();
                }
            }
        });  // Configurar botones
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusica();
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusica();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusica();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteMusica();
            }
        });

        btnlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anteriorMusica();
            }
        });

        return vista;
    }

    private void playMusica() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getContext(), listaMusica.get(currentIndex));
        }
        mediaPlayer.start();
    }

    private void pauseMusica() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void stopMusica() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void siguienteMusica() {
        if (currentIndex < listaMusica.size() - 1) {
            currentIndex++;
            stopMusica();
            playMusica();
        }else {
            currentIndex = 0;
            stopMusica();
            playMusica();
        }
    }

    private void anteriorMusica() {
        if (currentIndex > 0) {
            currentIndex--;
            stopMusica();
            playMusica();
        }else {
            currentIndex = listaMusica.size() - 1;
            stopMusica();
            playMusica();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
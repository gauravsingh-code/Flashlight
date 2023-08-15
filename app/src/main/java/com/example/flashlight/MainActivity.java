package com.example.flashlight;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.flashlight.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate xml layout to binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getSupportActionBar().hide();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.button.getText().toString().equals("Turn On")){
                    binding.button.setText("Turn Off");
                    binding.imageView2.setImageResource(R.drawable.tochoff);
                    Toast.makeText(MainActivity.this, "FlashLight is now on.", Toast.LENGTH_SHORT).show();
                    changeLightState(true);

                }
                else{
                    binding.button.setText("Turn On");
                    binding.imageView2.setImageResource(R.drawable.torchon);
                    Toast.makeText(MainActivity.this, "FlashLight is now closed.", Toast.LENGTH_SHORT).show();
                    changeLightState(false);
                }
            }
        });
    }

    private void changeLightState(boolean state) {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            CameraManager cameraManager=(CameraManager) getSystemService(CAMERA_SERVICE);
            String camId=null;
            try {
                camId=cameraManager.getCameraIdList()[0];//0-for back Camera & 1-for Front camera
                cameraManager.setTorchMode(camId , state);
            } catch(CameraAccessException e){
                e.printStackTrace();
            }

        }
    }
}
package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;

import com.example.registerpage.Constants;
import com.example.registerpage.R;
import com.example.registerpage.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {

    String url;
    private ActivityVideoBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        url = getIntent().getStringExtra(Constants.VIDEO_URL_KEY);

        binding.videoView.setVideoPath(url);
        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        binding.videoView.setMediaController(mediaController);
        binding.videoView.setOnInfoListener(onInfoToPlayStateListener);
        binding.videoView.start();
    }

    private final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
                //spinnerView.setVisibility(View.GONE);
                progressDialog.dismiss();
            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
                //spinnerView.setVisibility(View.VISIBLE);
                progressDialog.show();
            }
            if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                //sp//innerView.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
            }
            return false;
        }
    };
}
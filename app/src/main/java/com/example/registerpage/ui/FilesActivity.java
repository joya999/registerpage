package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.registerpage.R;

public class FilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
    }

     /* private void testPdf() {
        startActivity(

                // Opening pdf from assets folder

                PdfViewerActivity.Companion.launchPdfFromUrl(
                        this,
                        url,
                        "Pdf title/name",
                        "",
                        true
                )
        );
    }*/
}
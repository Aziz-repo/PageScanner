package com.example.paperscanner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfActivity extends AppCompatActivity {
    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;
    String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Button captureButton = (Button)findViewById(R.id.Picture);
        Intent intent = getIntent();
        //Setting the camera
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);

    }
    // variable that return the image after taken it and write to a file
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File picture_file = getOutputMediaFile();
            if (picture_file == null) {
                return ;
            }else {
                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(data);
                    fos.close();

                    camera.startPreview();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    public  void capturePicture(View view) {
        if (camera != null) {
            camera.takePicture(null,null, mPictureCallback);
        }
    }
    private File getOutputMediaFile() {
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }else {
            File folder_gui = new File(Environment.getExternalStorageDirectory()+ File.separator + "GUI");
            if (!folder_gui.exists()) {
                folder_gui.mkdirs();
            }
            File outputFile = new File(folder_gui, "temp.jpg");
            mCurrentPhotoPath = folder_gui.getAbsolutePath();
            return outputFile;
        }
    }

    public void ReturnButton(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}
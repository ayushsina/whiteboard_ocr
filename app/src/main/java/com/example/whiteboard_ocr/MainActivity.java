package com.example.whiteboard_ocr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class MainActivity extends AppCompatActivity {

        DrawableView drawableView;
        Button undo,convert;
        DrawableViewConfig config;
        TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawableView=(DrawableView)findViewById(R.id.paintView);
        undo=(Button)findViewById(R.id.undo);
        convert=findViewById(R.id.convert);



        config=new DrawableViewConfig();

        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setShowCanvasBounds(true);

        config.setStrokeWidth(20.0f);
        config.setMinZoom(2.0f);

        config.setMaxZoom(3f);

        config.setCanvasHeight(1080);
        config.setCanvasWidth(2000);
        drawableView.setConfig(config);

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawableView.undo();
            }
        });


        textView=findViewById(R.id.TextView);






    }



    public  void  convert (View v)
    {
        Bitmap bitmap = BitmapFactory.decodeResource();
        TextRecognizer textRecognizer=new TextRecognizer.Builder(getApplicationContext()).build();
        if(!textRecognizer.isOperational())
        {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
        }
        else {

            Frame frame =new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items= textRecognizer.detect(frame);

            StringBuilder sb=new StringBuilder();

                for (int i=0;i<items.size();++i)
                {
                    TextBlock myItem=items.valueAt(i);
                    sb.append(myItem.getValue());
                    sb.append("\n");

                }

                textView.setText(sb.toString());

        }
    }
}
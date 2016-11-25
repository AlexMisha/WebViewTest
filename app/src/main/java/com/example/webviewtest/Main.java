package com.example.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class Main extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) this.findViewById(R.id.urlString);

        mTextView = (TextView) this.findViewById(R.id.htmlView);
        mTextView.setMovementMethod(new ScrollingMovementMethod());

        mButton = (Button) this.findViewById(R.id.button);
        initClickListeners();
    }

    public void initClickListeners(){
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String urlString = mEditText.getText().toString();
                        WebViewer webViewer = new WebViewer();
                        try {
                            String htmlCode = webViewer.getHtml(urlString);
                            mTextView.setText(htmlCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}

package com.example.webviewtest;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.ExecutionException;

@EActivity(R.layout.activity_main)
public class MainActivity_ extends AppCompatActivity {
    @ViewById(R.id.userInput)
    EditText userInput;

    @ViewById(R.id.button)
    Button button;

    @ViewById(R.id.htmlView)
    TextView htmlView;

    private String urlString;

    @AfterViews
    void initListeners(){
        htmlView.setMovementMethod(new ScrollingMovementMethod());
        button.setOnClickListener(
                new View.OnClickListener()
                {
                    @CallSuper
                    public void onClick(View view)
                    {
                        urlString = userInput.getText().toString();
                        WebViewer webViewer = new WebViewer();
                        webViewer.setUrlString(urlString);
                        try {
                            webViewer.execute().get();
                            String htmlCode = webViewer.getHtmlCode();
                            htmlView.setText(htmlCode);
                        }
                        catch(InterruptedException | ExecutionException e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}

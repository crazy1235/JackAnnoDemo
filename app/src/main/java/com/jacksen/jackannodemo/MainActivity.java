package com.jacksen.jackannodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jacksen.jackanno.JackAnno;
import com.jacksen.jackanno.OnClick;
import com.jacksen.jackanno.ViewInject;

/**
 * test jackanno
 *
 * @author jacksen
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.textView)
    private TextView textView;

    @ViewInject(R.id.button)
    private Button btn;

    @ViewInject(R.id.checkBox)
    private CheckBox checkBox;

    @ViewInject(R.id.linear_layout)
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JackAnno.inject(this);

    }

    @OnClick({R.id.button, R.id.linear_layout})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
                break;
            case R.id.linear_layout:
                Toast.makeText(this, "linearlayout has been clicked.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * @param view
     */
    @OnClick(R.id.textView)
    public void onTextViewClick(View view) {
        Toast.makeText(this, "textView has been clicked", Toast.LENGTH_SHORT).show();
    }
}

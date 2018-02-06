package com.example.juice.lab2;

        import android.content.res.Configuration;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_landscape);
        } else{
            setContentView(R.layout.activity_portrait);
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}

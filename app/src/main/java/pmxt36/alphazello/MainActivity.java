package pmxt36.alphazello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

/*
NOTE: You need to have zello work app (not the one from appstore) installed using this link
 http://mugun88.zellowork.com/app
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all available widgets
        Button pttButton = (Button) findViewById(R.id.ptt_button);
        assert pttButton != null;

        Button selectButton = (Button) findViewById(R.id.select_button);
        assert selectButton !=null;

        
    }
}

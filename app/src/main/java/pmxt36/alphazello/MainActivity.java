package pmxt36.alphazello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zello.sdk.Tab;
import com.zello.sdk.Theme;
import com.zello.sdk.Zello;

/*
NOTE: You need to have zello work app (not the one from appstore) installed using this link
 http://mugun88.zellowork.com/app
 */

/*
* Required API
*
* int Init(void)
* void StartPtt(string userNameToCall)
* void StopPtt(void)
*
* */

public class MainActivity extends AppCompatActivity {

    boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Zello
        Zello.getInstance().configure("net.loudtalks", this);
        Zello.getInstance().setSelectedChannelOrGroup("Everyone");
        // Find all available widgets

        Button pttButton = (Button) findViewById(R.id.ptt_button);
        pttButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Initiate call using the account signed in on Zello Work app
                // Todo: set contact programatically using result from image processing on first responder
                // Sample : set target contact to shuyang's iphone
                // Zello.getInstance().setSelectedUserOrGateway("yang");

                // Sample : broadcast to everyone
                // Zello.getInstance().setSelectedChannelOrGroup("Everyone");

                int action = event.getAction();
                if (action == MotionEvent. ACTION_DOWN ) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Transmitting...", Toast.LENGTH_SHORT);
                    toast.show();
                    Zello.getInstance().beginMessage();
                } else if (action == MotionEvent. ACTION_UP || action == MotionEvent. ACTION_CANCEL ) {
                    Zello.getInstance().endMessage();
                    Toast toast = Toast.makeText(getApplicationContext(), "Voice message sent.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return false;
            }
        });

        Button selectButton = (Button) findViewById(R.id.select_button);
        selectButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Let user select contacts
                // TODO: Optional or let set programatically from code behind
                Zello.getInstance().selectContact("Select a contact", new Tab[]{Tab.RECENTS,
                        Tab.USERS, Tab.CHANNELS}, Tab.RECENTS, Theme.DARK);
                return false;
            }
        });

        // Connect channel
        final String channelName = "ONEMERIDIAN";
        final Button connectButton = (Button) findViewById(R.id.connect);
        connectButton.setText(String.format("Connect to %s", channelName));

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected){
                    Zello.getInstance().disconnectChannel(channelName);
                }else {
                    Zello.getInstance().connectChannel(channelName);
                    Zello.getInstance().setSelectedChannelOrGroup(channelName);
                }

                isConnected = !isConnected;

                if (isConnected){
                    connectButton.setText(String.format("Disconnect from %s", channelName));
                }else {
                    connectButton.setText(String.format("Connect to %s", channelName));
                }
            }
        });
    }
}

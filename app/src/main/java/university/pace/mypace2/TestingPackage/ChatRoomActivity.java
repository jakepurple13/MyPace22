package university.pace.mypace2.TestingPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.chat.QBChatService;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.model.QBUser;

import university.pace.mypace2.R;

public class ChatRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //Log.wtf("TEST", "Hello");
        //"http://stackoverflow.com/search?q=[js]+"e.message;


        //try {


        // Initialise Chat service
        final QBChatService chatService = QBChatService.getInstance();

        final QBUser user = new QBUser("garrysantos", "garrysantospass");
        QBAuth.createSession(user, new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession session, Bundle params) {
                // success, login to chat

                user.setId(session.getUserId());

                chatService.login(user, new QBEntityCallback() {

                    @Override
                    public void onSuccess(Object o, Bundle bundle) {

                    }

                    @Override
                    public void onError(QBResponseException errors) {

                    }
                });
            }

            @Override
            public void onError(QBResponseException errors) {

            }
        });


        QBChatService.setDebugEnabled(true); // enable chat logging
        QBChatService.setDefaultAutoSendPresenceInterval(60); //enable sending online status every 60 sec to keep connection alive


        QBChatService.ConfigurationBuilder chatServiceConfigurationBuilder = new QBChatService.ConfigurationBuilder();
        chatServiceConfigurationBuilder.setSocketTimeout(60); //Sets chat socket's read timeout in seconds
        chatServiceConfigurationBuilder.setKeepAlive(true); //Sets connection socket's keepAlive option.

        QBChatService.setConfigurationBuilder(chatServiceConfigurationBuilder);


        /*} catch(Exception e) {
            Log.e("Nope!", "http://stackoverflow.com/search?q=[android]+"+e.getMessage());
        }*/

    }
}

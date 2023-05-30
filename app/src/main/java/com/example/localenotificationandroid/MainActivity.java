package com.example.localenotificationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

    NotificationManager mNotificationManager;

    AppCompatButton incomingCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incomingCall=findViewById(R.id.btnCreateNotification);

        incomingCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification(v,"Incoming Call From 8128618176","Ringing...");
            }
        });

    }
    public void createNotification (View view,String caller,String state) {


        mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity. this, default_notification_channel_id ) ;
        mBuilder.setContentTitle(caller);
        mBuilder.setContentText( state ) ;
        mBuilder.setTicker( "CSK Won The Final - 2023" ) ;
        mBuilder.setSmallIcon(R.drawable. ic_call );
        mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        mBuilder.setCategory(NotificationCompat.CATEGORY_CALL);
        mBuilder.setAutoCancel( false ) ;
        mBuilder.setOngoing(true) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;

        mNotificationManager.cancel(Integer.parseInt(NOTIFICATION_CHANNEL_ID));
    }

    public void removeNotification (View view) {
//        int notificationId=Integer.parseInt(NOTIFICATION_CHANNEL_ID);
//        if ( notificationId != 0 )
//            mNotificationManager .cancel( notificationId ) ;
        mNotificationManager.cancelAll();

        createNotification(view,"Call Ongoing","incoming call ongoing...");
    }

}
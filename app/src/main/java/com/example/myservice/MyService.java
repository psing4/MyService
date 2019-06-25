package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String Tag = "MyService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tag,"onCreate 실행");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(Tag,"onStartCommand 실행");
        if(intent != null){
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(Tag, "전달받은 데이터 : " + command + ", " + name);

        try {
            Thread.sleep(5000);

        } catch (Exception e) { }
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //화면이 없는 곳에서 화면을 뜨위게 하는건 불가능 그래서 Flags로 Task를 띄운다.
        //FLAG_ACTIVITY_SINGLE_TOP 기존의 화면 쓴다.
        //FLAG_ACTIVITY_CLEAR_TOP 위에 뭐가 있다면 없앤다.
        showIntent.putExtra("com","show");
        showIntent.putExtra("name",name+"From Service");
        startActivity(showIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}


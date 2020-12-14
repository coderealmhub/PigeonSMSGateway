package br.com.coderealm.pigeon.helps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.coderealm.pigeon.api.services.SendSMS;

public class ServiceGateway extends Service {

    private List<Worker> workerList = new ArrayList<Worker>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Worker worker = new Worker(startId);
        worker.start();
        workerList.add(worker);
        return (super.onStartCommand(intent, flags, startId));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (int i = 0, tam = workerList.size(); i < tam; i++) {
            workerList.get(i);
        }
    }

    class Worker extends Thread {
        private int startId;
        private SessionManager sessionManager;

        public Worker(int startId) {
            this.startId = startId;
            sessionManager = new SessionManager(getApplicationContext());
        }

        public void run() {
            while (sessionManager.getKeyStatusGateway()) {
                try {
                    Thread.sleep(Integer.valueOf(sessionManager.getKeyInterval()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SendSMS(getApplicationContext()).execute();
            }
            stopSelf(startId);
        }
    }

}

package dibu.bus.driver.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dibu.bus.driver.R;
import dibu.bus.driver.mqttserver.IGetMessageCallBack;
import dibu.bus.driver.mqttserver.MQTTService;
import dibu.bus.driver.mqttserver.MyServiceConnection;
import dibu.bus.driver.ui.activity.MainActivity;

/**
 * https://blog.csdn.net/asjqkkkk/article/details/80714234   参考地址  mqtt android客户端（百度出来的）
 * https://www.jianshu.com/p/73436a5cf855  这个地址也是，貌似用的阿里云上面的mqtt
 */
public class MqttTestActivity extends AppCompatActivity implements IGetMessageCallBack {
    private TextView textView;
    private Button button;
    private MyServiceConnection serviceConnection;
    private MQTTService mqttService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = (TextView) findViewById(R.id.text);
//        button = (Button) findViewById(R.id.test);

        serviceConnection = new MyServiceConnection();
        serviceConnection.setIGetMessageCallBack(MqttTestActivity.this);

        Intent intent = new Intent(this, MQTTService.class);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MQTTService.publish("测试一下子");
            }
        });
    }

    @Override
    public void setMessage(String message) {
        textView.setText(message);
        mqttService = serviceConnection.getMqttService();
        mqttService.toCreateNotification(message);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}

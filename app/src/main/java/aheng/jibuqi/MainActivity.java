package aheng.jibuqi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import aheng.jibuqi.view.JiBuQi;

/**
 * @author Dev_Heng
 * @date 2022.12.7
 */
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        JiBuQi jiBuQi = (JiBuQi) findViewById(R.id.jibuqi);
        jiBuQi.setShangSweepAngle(00);
        
    }
}
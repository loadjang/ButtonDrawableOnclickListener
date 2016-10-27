package kr.buttondrawableonclicklistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CButtonDrawableClick button = (CButtonDrawableClick)findViewById(R.id.button);

        button.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher,0,R.mipmap.ic_launcher,0);

        button.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {

                switch(target){

                    case RIGHT:
                        Toast.makeText(getApplicationContext(),"RIGHT",Toast.LENGTH_SHORT).show();
                        break;
                    case LEFT:
                        Toast.makeText(getApplicationContext(),"LEFT",Toast.LENGTH_SHORT).show();
                        break;
                    case OTHER:
                        Toast.makeText(getApplicationContext(),"BUTTON",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

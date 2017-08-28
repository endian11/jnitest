package cppt.phone.lzw.com.cpptes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        JniTest jniTest = new JniTest();
        TextView tv = (TextView) findViewById(R.id.sample_text);
        Person person = new Person();
        person.setAge(18);
        person.setName("lzw");
        Person p =jniTest.addPerson(person);
        int sub= jniTest.addSub(34,6);
        tv.setText(jniTest.stringFromJNI()+String.valueOf(sub)+"person:"+p);
    }


}

package cppt.phone.lzw.com.cpptes;

/**
 * Created by lizhanwei on 17/8/25.
 */

public class JniTest {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int addSub(int x,int y);

    public native Person addPerson(Person person);

}

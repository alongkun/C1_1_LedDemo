package HardLib;

/**
 * Created by alongkun on 17-9-9.
 */

public class HardControl {
    static {
        try {
            System.loadLibrary("hardcontrol");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static native int ledCtrl(int led, int status);
    public static native int ledOpen();
    public static native void ledClose();
}

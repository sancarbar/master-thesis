package org.tomdroid.test;

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;
import com.tool.androidmonkey.Monkey;
import org.tomdroid.ui.Tomdroid;

/**
 * Created by santiagocarrillo on 02/05/14.
 */
public class MonkeyTomdroidTest
    extends ActivityInstrumentationTestCase2<Tomdroid>
{

    private int NUM_EVENTS = 500;

    private int EVENT_PAUSE = 100;

    private static final String packageToTest = "org.tomdroid";


    @TargetApi( Build.VERSION_CODES.FROYO )
    public MonkeyTomdroidTest()
    {
        super( Tomdroid.class );
    }


    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        setActivityInitialTouchMode( false );

    }

    public void testMonkeyEvents()
    {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Instrumentation inst = getInstrumentation();
        PackageManager pm = getActivity().getPackageManager();

        Monkey monkey = new Monkey(display, packageToTest, inst, pm);

        for (int i = 0; i < NUM_EVENTS; i++){
            try
            {
                Thread.currentThread().sleep( EVENT_PAUSE );
            }
            catch ( InterruptedException e ){}
            monkey.nextRandomEvent();
        }

    }
}
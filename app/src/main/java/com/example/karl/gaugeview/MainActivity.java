package com.example.karl.gaugeview;

        import android.app.Activity;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.widget.LinearLayout;
        import android.widget.SeekBar;
        import android.widget.TextView;

public class MainActivity extends Activity {

    private SeekBar rpmSeekBar = null;
    private TextView rpmTextView = null;
    private SeekBar mpSeekBar = null;
    private TextView mpTextView = null;
    private int mValue = 0;
    SampleView rpmView;
    SampleView mpView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        rpmView = new SampleView( this, 4, 0, 2700, 225.0f);

        rpmView.insertSegment(0, 0, 2100, Color.GREEN);
        rpmView.insertSegment(1, 2100, 2200, Color.YELLOW);
        rpmView.insertSegment(2, 2200, 2650, Color.GREEN);
        rpmView.insertSegment(3, 2650, 2700, Color.RED);

        mpView = new SampleView( this, 3, 0, 36, 225.0f );

        mpView.insertSegment( 0, 0, 32, Color.GREEN);
        mpView.insertSegment( 1, 32, 35, Color.YELLOW);
        mpView.insertSegment(2, 35, 36, Color.RED);
        */

        setContentView(R.layout.activity_main);
        //RelativeLayout mainLayout = (RelativeLayout)getWindow().getDecorView().getRootView();
        LinearLayout g1l = (LinearLayout)findViewById( R.id.guage1);
        LinearLayout g2l = (LinearLayout)findViewById( R.id.guage2);
        LinearLayout bg1l = (LinearLayout)findViewById( R.id.bargraph );

        g1l.addView(rpmView);
        g2l.addView(mpView);

        rpmTextView = (TextView)findViewById(R.id.rpmtext);
        rpmSeekBar = (SeekBar)findViewById(R.id.rpmseek);
        mpTextView = (TextView)findViewById(R.id.mptext);
        mpSeekBar = (SeekBar)findViewById(R.id.mpseek);


        rpmSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rpmTextView.setText(Integer.toString(progress));
                mValue = progress;
                rpmView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mpSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mpTextView.setText(Integer.toString(progress));
                mValue = progress;
                mpView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}



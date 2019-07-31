package http.req.bd.navigationgeoinstitut;

import android.content.pm.ActivityInfo;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class Kartenansicht extends AppCompatActivity implements View.OnClickListener{

    String aktuelle_position = "";
    ImageView grundriss_imageview;
    Button zu_etage_eg, zu_etage_1og, zu_etage_2og, zu_etage_3og;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_kartenansicht);
        Toolbar toolbar = findViewById(R.id.toolbar);
        grundriss_imageview = findViewById(R.id.grundriss_imageview);
        grundriss_imageview.setOnClickListener(this);
        zu_etage_eg = findViewById(R.id.zu_etage_eg);
        zu_etage_eg.setOnClickListener(this);
        zu_etage_1og = findViewById(R.id.zu_etage_1og);
        zu_etage_1og.setOnClickListener(this);
        zu_etage_2og = findViewById(R.id.zu_etage_2og);
        zu_etage_2og.setOnClickListener(this);
        zu_etage_3og = findViewById(R.id.zu_etage_3og);
        zu_etage_3og.setOnClickListener(this);
        setSupportActionBar(toolbar);
        final SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.grundriss2og));
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                    // ...
                }
                return true;
            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        // ... or ...
        //imageView.setImage(ImageSource.asset("map.png"));
        // ... or ...
        //imageView.setImage(ImageSource.uri("/sdcard/DCIM/DSCM00123.JPG"));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        String position = getIntent().getStringExtra("position");
        if (position != null) {
            aktuelle_position = position;
            getSupportActionBar().setSubtitle(position);
            findeRaumAufKarte();
        }

    }

    private void findeRaumAufKarte() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zu_etage_eg:
                break;
            case R.id.zu_etage_1og:
                grundriss_imageview.setImageDrawable(getResources().getDrawable(R.drawable.grundriss1og));
                break;
            case R.id.zu_etage_2og:
                grundriss_imageview.setImageDrawable(getResources().getDrawable(R.drawable.grundriss2og));
                break;
            case R.id.zu_etage_3og:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }


        return super.onOptionsItemSelected(item);
    }


}

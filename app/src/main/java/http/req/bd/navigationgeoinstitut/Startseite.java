package http.req.bd.navigationgeoinstitut;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Startseite extends AppCompatActivity {

    TextView main_text;

    LinearLayout menü_für_raum;
    LinearLayout menü_allgemein;

    Button btn_aktuelle_position_anzeigen, btn_zu_raum_navigieren, btn_raum_suchen;
    Context mContext = this;

    String aktuelle_position = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_startseite);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main_text = findViewById(R.id.main_text);
        menü_für_raum = findViewById(R.id.menü_für_raum);
        menü_allgemein = findViewById(R.id.menü_allgemein);
        handleIntent();
        btn_aktuelle_position_anzeigen = findViewById(R.id.btn_aktuelle_position_anzeigen);
        btn_aktuelle_position_anzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zur_karte = new Intent(mContext, Kartenansicht.class);
                zur_karte.putExtra("position", aktuelle_position); // die Raumnummer übergeben
                startActivity(zur_karte);
            }
        });
        btn_zu_raum_navigieren = findViewById(R.id.btn_zu_raum_navigieren);
        btn_zu_raum_navigieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_raum_suchen = findViewById(R.id.btn_raum_suchen);
        btn_raum_suchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Kartenansicht.class));
            }
        });

    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        if (appLinkData != null) {
            String room_number = appLinkData.getLastPathSegment();
            aktuelle_position = room_number;
            main_text.setText("Herzlich willkommen im Geo-Institut!\n\nSie befinden sich gerade vor Raum " + room_number + ".");
            menü_allgemein.setVisibility(View.GONE);
        } else {
            // keine Raumnummer
            menü_für_raum.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.informationen_zur_app) {
            //startActivity(new Intent(this, )); //TODO: zu Informationen zur App weiterleiten
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

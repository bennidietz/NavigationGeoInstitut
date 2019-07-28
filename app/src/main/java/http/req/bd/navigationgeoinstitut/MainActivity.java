package http.req.bd.navigationgeoinstitut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView main_text;

    LinearLayout menü_für_raum;
    LinearLayout menü_allgemein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main_text = findViewById(R.id.main_text);
        menü_für_raum = findViewById(R.id.menü_für_raum);
        menü_allgemein = findViewById(R.id.menü_allgemein);
        handleIntent();

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

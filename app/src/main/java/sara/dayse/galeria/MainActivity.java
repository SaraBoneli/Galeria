package sara.dayse.galeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tbMain);//obtém o elemento tbMain
        setSupportActionBar(toolbar);//indica que tbMain (ToolBar) deve ser considerado como a ActionBar
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();//cria "inflador de menu"
        inflater.inflate(R.menu.main_activity_tb,menu);
        return true;
    }
    public boolean onOptionsItenmSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opCamera://caso icone de câmera for clicado
                dispatchTakePictureIntent();//executa código que dispara a câmera do celular
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    private void dispatchTakePictureIntent() {
    }

    public void startPhotoActivity(String s) {
    }
}
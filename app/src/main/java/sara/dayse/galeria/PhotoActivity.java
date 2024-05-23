package sara.dayse.galeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Toolbar toolbar = findViewById(R.id.tbPhoto);//obtém o elemento tbMain
        setSupportActionBar(toolbar);//indica que tbMain (ToolBar) deve ser considerado como a ActionBar

        ActionBar actionBar = getSupportActionBar();//obtém da Activity a ActionBar padrão
        actionBar.setDisplayHomeAsUpEnabled(true);//habilita o botão de voltar na ActionBar
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();// cria "inflador de menu"
        inflater.inflate(R.menu.main_activity_tb,menu);
        return true;
    }
    public boolean onOptionItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opShare://caso icone de compartilhamento for clicado
                sharePhoto();//executa código que compartilha a foto
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sharePhoto() {
    }
}
package sara.dayse.galeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {

    String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Toolbar toolbar = findViewById(R.id.tbPhoto);//obtém o elemento tbMain
        setSupportActionBar(toolbar);//indica que tbMain (ToolBar) deve ser considerado como a ActionBar

        ActionBar actionBar = getSupportActionBar();//obtém da Activity a ActionBar padrão
        actionBar.setDisplayHomeAsUpEnabled(true);//habilita o botão de voltar na ActionBar

        Intent i = getIntent();//cria intent
        photoPath = i.getStringExtra("photo_path");//obtém caminho da foto que enviada via  o Intent de criação

        Bitmap bitmap = Util.getBitmap(photoPath);//carrega a foto em um bitmap
        ImageView imPhoto = findViewById(R.id.imPhoto);//seta o Bitmap no ImageView
        imPhoto.setImageBitmap(bitmap);//seta o Bitmap no ImageView
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
        //código para compartilhar a foto
        Uri photoUri = FileProvider.getUriForFile(PhotoActivity.this,"sara.dayse.galeria.fileprovider", new File(photoPath));
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_STREAM, photoUri);
        i.setType("image/jpeg");
        startActivity(i);
    }
}
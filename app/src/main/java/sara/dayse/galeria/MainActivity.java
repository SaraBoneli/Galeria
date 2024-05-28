package sara.dayse.galeria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int RESULT_TAKE_PICTURE = 1;

    String currentPhotoPath;

    List<String> photos = new ArrayList<>();

    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//acessa diretório Pictures(
        File[] files = dir.listFiles();//acessa diretório Pictures(leem a lista de fotos já salvas)
        for(int i = 0; i < files.length; i++) {
            photos.add(files[i].getAbsolutePath());
        }//acessa diretório Pictures(adicionam na lista de fotos)

        mainAdapter = new MainAdapter(MainActivity.this,photos);// cria o MainAdapter

        RecyclerView rvGallery = findViewById(R.id.rvGallery);//seta no RecycleView
        rvGallery.setAdapter(mainAdapter);//seta no RecycleView

        float w = getResources().getDimension(R.dimen.itemWidth);

        int numberOfColumns = Util.calculateNoOfColumns(MainActivity.this, w);//calcula quantas colunas de fotos cabem na tela do
        //celular
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, numberOfColumns);//configura o RecycleView para exibir as fotos em GRID
        rvGallery.setLayoutManager(gridLayoutManager);//respeita o número máximo de colunas calculado

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
        if (item.getItemId() == R.id.opCamera) {//caso icone de câmera for clicado
            dispatchTakePictureIntent();//executa código que dispara a câmera do celular
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        File f = null;
        try {
            f = createImageFile();
        }
        catch (IOException e) {
            Toast.makeText(MainActivity.this, "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
            return;
        }
        currentPhotoPath = f.getAbsolutePath();

        if (f != null) {
            Uri fUri = FileProvider.getUriForFile(MainActivity.this,"sara.dayse.galeria.fileprovider", f);
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            i.putExtra(MediaStore.EXTRA_OUTPUT, fUri);
            startActivityForResult(i, RESULT_TAKE_PICTURE);
        }
    }

    private  File createImageFile() throws  IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(imageFileName, ".jpg",storageDir);
        return f;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_TAKE_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                photos.add(currentPhotoPath);
                mainAdapter.notifyItemInserted(photos.size()-1);
            }
            else {
                File f = new File(currentPhotoPath);
                f.delete();
            }
        }
    }

    public void startPhotoActivity(String photoPath) { //foto que deverá ser aberta por PhotoActivity
        Intent i = new Intent(MainActivity.this,PhotoActivity.class);// cria Intent
        i.putExtra("photo_path", photoPath);//caminho da foto sendo passado para PhotoActivity via Intent
        startActivity(i);
    }

}
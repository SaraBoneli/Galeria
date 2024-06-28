package sara.dayse.galeria;

import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<String>photos;

    public MainAdapter(MainActivity mainActivity, List<String>photos) {
        this.mainActivity = mainActivity;
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);//obtém inflador de layouts
        View v = inflater.inflate(R.layout.list_item,parent,false);//cria elementos de interface referentes a um item
        // e guarda dentro de um objeto do tipo View
        return new MyViewHolder(v);//guarda objeto do tipo View dentro de um objeto do tipo MyViewHolder
        // ,que é retornado pela função
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ImageView imPhoto = holder.itemView.findViewById(R.id.imItem);
        int w = (int)
                mainActivity.getResources().getDimension(R.dimen.itemWidth);
        int h = (int)
                mainActivity.getResources().getDimension(R.dimen.itemHeight);
        //obtém as dimensões que a imagem vai ter na lista


        Bitmap bitmap = Util.getBitmap(photos.get(position), w, h);
        //carrega a imagem em um Bitmap ao mesmo tempo em que a foto
        // é escalada para casar com os
        //tamanhos definidos para o ImageView
        imPhoto.setImageBitmap(bitmap);// o Bitmap é setado no ImageView
        //codigos abaixo definem o que acontece quando o usuário clica em cima de uma imagem
        imPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                mainActivity.startPhotoActivity(photos.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}

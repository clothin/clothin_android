package si.cit.clothingorigin;

import android.support.v7.widget.RecyclerView;

import si.cit.clothingorigin.Interfaces.RecyclerAdapterItemClickListener;


/**
 * Created by Anže Kožar on 1.12.2016.
 */

abstract public class RecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>{

    RecyclerAdapterItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(RecyclerAdapterItemClickListener listener){
        this.onItemClickListener = listener;
    }
}

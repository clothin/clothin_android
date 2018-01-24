package si.cit.clothingorigin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

import si.cit.clothingorigin.Objects.Producer;
import si.cit.clothingorigin.views.FontView;

/**
 *
 */

public class ProductionChainAdapter extends RecyclerViewAdapter<RecyclerView.ViewHolder>{

    //Context to use when retrieving resources such as drawables,...
    private Context mContext;
    //Dataset
    private List<Producer> mDataset = new ArrayList<>();
    //This tag is used to identify the adapter in interface callbacks
    // where multiple adapters are reporting to a single callback method
    public String adapterTAG;

    /** Provide a reference to the views for each data item
     *  Complex data items may need more than one view per item, and
     *  you provide access to all the views for a data item in a view holder
     */
    private static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View mView;
        FontView name_text, country_text, service_text;
        ViewHolder(View v) {
            super(v);
            mView = v;
            name_text = v.findViewById(R.id.producer_name);
            country_text = v.findViewById(R.id.producer_country);
            service_text = v.findViewById(R.id.producer_service);
        }
    }

    /**
     * Construct a new adapter
     * @param context Context for resource retrieval
     * @param books Books to populate the list with
     * @param tag String TAG to identify the adapter in interface callbacks
     */
    public ProductionChainAdapter(Context context, List<Producer> productionChain, String tag) {
        mDataset = productionChain;
        mContext = context;
        adapterTAG = tag;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_producer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolder) {
            final ViewHolder vh = (ViewHolder)holder;
            final Producer producer = mDataset.get(position);
            vh.name_text.setText(producer.name);
            vh.country_text.setText(producer.country);
            vh.service_text.setText(producer.service);

            if(this.onItemClickListener !=null){
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onListItemClick(adapterTAG,position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
            return mDataset.size();
    }
}
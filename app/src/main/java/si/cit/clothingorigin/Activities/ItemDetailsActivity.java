package si.cit.clothingorigin.Activities;

import android.os.Bundle;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import si.cit.clothingorigin.Objects.Product;
import si.cit.clothingorigin.R;
import si.cit.clothingorigin.views.FontView;

/**
 * Created by Anže Kožar on 22.1.2018.
 * nzkozar@gmail.com
 */

public class ItemDetailsActivity extends BaseActivity {

    public static final int ACTIVITY_REQUEST_CODE_PRODUCT = 2;

    @BindView(R.id.product_picture)
    ImageView productPicture;

    @BindView(R.id.product_title)
    FontView productTitle;

    @BindView(R.id.product_price)
    FontView productPrice;

    @BindView(R.id.product_size)
    FontView productSize;

    @BindView(R.id.product_color)
    FontView productColor;

    @BindView(R.id.product_materials)
    FontView productMaterials;

    @BindView(R.id.product_soldBy)
    FontView productSeler;

    private Product product = Product.fakeProduct0();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ButterKnife.bind(this);

        String productId = getIntent().getStringExtra("product_id");

        //TODO get real product data from BC

        init();
    }

    private void init(){
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .downloader(new OkHttp3Downloader(getApplicationContext()))
                .build();

        picasso.load(product.picture_url)
                .into(productPicture);

        productTitle.setText(product.title);
        productPrice.setText(product.price);
        productSize.setText(product.size);
        productColor.setText(product.color);
        productMaterials.setText(product.materials);
        productSeler.setText(product.sold_by);
    }
}

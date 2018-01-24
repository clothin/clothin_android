package si.cit.clothingorigin.Objects;

import com.google.gson.Gson;

import org.web3j.tuples.generated.Tuple2;

import java.util.ArrayList;
import java.util.List;

import si.cit.clothingorigin.CitApp;
import si.cit.clothingorigin.Interfaces.ContractResultListener;
import si.cit.clothingorigin.Interfaces.ObjectDataChangeListener;
import timber.log.Timber;

/**
 * Created by Anže Kožar on 22.1.2018.
 * nzkozar@gmail.com
 */

public class Product {

    public static final int REQUEST_CODE_PRODUCTION_CHAIN_FETCH = 0;

    public long id;
    public String title, size, color, price, picture_url, sold_by, materials;
    public List<Producer> production_chain = new ArrayList<>();

    public void fetchProductionChain(final ObjectDataChangeListener listener){
        CitApp.getInstance().getBlockchainConnector().getProductProductionChainInfo(this.id, new ContractResultListener() {
            @Override
            public void onContractResult(Object resultObject, boolean success) {
                if(success) {
                    Tuple2 tuple2 = (Tuple2) resultObject;
                    String trailString = (String)tuple2.getValue1();
                    String[] trailNodeStrings = trailString.split(";");
                    for (String s:trailNodeStrings) {
                        String[] producerValues = s.split(":");
                        Producer producer = new Producer(
                                producerValues[1],
                                producerValues[0],
                                producerValues[2],
                                producerValues[4]+":"+producerValues[5]);
                        production_chain.add(producer);
                        Gson gson = new Gson();
                        Timber.i("Product trail node: " + gson.toJson(producer));
                    }
                    //Gson gson = new Gson();
                    //Timber.i("Product trail result: " + gson.toJson(tuple2));
                    listener.onDataChange(this);
                }else{
                    Timber.i("Product trail result failed!");
                }
            }
        });
    }

    public static Product fakeProduct0(){
        Product product = new Product();
        product.title = "V-neck shirt";
        product.size = "M";
        product.color = "White";
        product.price = "8.99€";
        product.sold_by = "H&M";
        product.materials = "Cotton (98%), Polyester (2%)";
        product.picture_url = "http://lp.hm.com/hmprod?set=source%5B%2Fenvironment%2F2017%2F10J_0636_003R.jpg%5D%2Cmedia_type%5BFASHION_FRONT%5D%2Ctshirt_size%5BL%5D%2Cquality%5BH%5D%2Csr_x%5B0%5D%2Csr_y%5B-1%5D%2Csr_height%5B4114%5D%2Csr_width%5B3519%5D%2Chmver%5B0%5D&call=url%5Bfile%3A%2Flegacy%2Fv1%2Fproduct.chain%5D";

        product.production_chain.add(Producer.fakeProducer(0));
        product.production_chain.add(Producer.fakeProducer(1));
        product.production_chain.add(Producer.fakeProducer(2));
        product.production_chain.add(Producer.fakeProducer(3));
        product.production_chain.add(Producer.fakeProducer(4));

        return product;
    }

}

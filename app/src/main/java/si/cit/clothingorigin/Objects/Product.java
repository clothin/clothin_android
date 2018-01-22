package si.cit.clothingorigin.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anže Kožar on 22.1.2018.
 * nzkozar@gmail.com
 */

public class Product {

    public long id;
    public String title, size, color, price, picture_url, sold_by, materials, weight;
    public List<Producer> production_chain;

    public static Product fakeProduct0(){
        Product product = new Product();
        product.title = "V-neck shirt";
        product.size = "M";
        product.color = "White";
        product.price = "8.99€";
        product.sold_by = "H&M";
        product.materials = "Cotton (98%), Polyester (2%)";
        product.picture_url = "http://lp.hm.com/hmprod?set=source%5B%2Fenvironment%2F2017%2F10J_0636_003R.jpg%5D%2Cmedia_type%5BFASHION_FRONT%5D%2Ctshirt_size%5BL%5D%2Cquality%5BH%5D%2Csr_x%5B0%5D%2Csr_y%5B-1%5D%2Csr_height%5B4114%5D%2Csr_width%5B3519%5D%2Chmver%5B0%5D&call=url%5Bfile%3A%2Flegacy%2Fv1%2Fproduct.chain%5D";

        List<Producer> producerChain = new ArrayList<>();
        producerChain.add(Producer.fakeProducer(0));
        producerChain.add(Producer.fakeProducer(1));
        producerChain.add(Producer.fakeProducer(2));
        producerChain.add(Producer.fakeProducer(3));
        producerChain.add(Producer.fakeProducer(4));

        return product;
    }

}

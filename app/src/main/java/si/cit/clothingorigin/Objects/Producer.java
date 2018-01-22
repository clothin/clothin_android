package si.cit.clothingorigin.Objects;

/**
 * Created by Anže Kožar on 22.1.2018.
 * nzkozar@gmail.com
 */

public class Producer {

    public long id;
    public String name, country, service;

    public static Producer fakeProducer(int index){
        String[] fakeCountries = {"China","Iran","USA","Philipines","Slovenia"};
        String[] fakeServices = {"Sewing","Cotton manufacturing","Coloring & Printing","Packaging","Retail"};

        if(index<0 || index>fakeCountries.length-1)index = 0;
        Producer producer = new Producer();
        producer.name = "Producer "+index;
        producer.country = fakeCountries[index];
        producer.service = fakeServices[index];

        return producer;
    }

}

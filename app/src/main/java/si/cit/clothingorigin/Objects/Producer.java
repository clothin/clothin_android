package si.cit.clothingorigin.Objects;

/**
 * Created by Anže Kožar on 22.1.2018.
 * nzkozar@gmail.com
 */

public class Producer {

    public long id;
    public String name, country, service, maps_url;

    public Producer(){}

    public Producer(String name,String country,String service,String maps_url){
        this.name = name;
        this.country = country;
        this.service = service;
        this.maps_url = maps_url;
    }

    public static Producer fakeProducer(int index){
        String[] fakeCountries = {"China","Iran","USA","Philipines","Slovenia"};
        String[] fakeServices = {"Sewing","Cotton manufacturing","Coloring & Printing","Packaging","Retail"};
        if(index<0 || index>fakeCountries.length-1)index = 0;

        return new Producer("Producer "+index,fakeCountries[index],fakeServices[index],"");
    }

}

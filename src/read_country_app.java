

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class read_country_app {
    public List<Country> reader (String countryinfo) throws IOException {

        List<Country> countries = new ArrayList<>();
        BufferedReader read= new BufferedReader(new FileReader(countryinfo));
        String[] parts = new String[0];
        Country co;
        String Line = read.readLine();
        do{
            Line=read.readLine();
            if (Line!=null){
                //System.out.println(Line);
                parts = Line.split(",");
                co=new Country(parts[0],parts[1],parts[2],Double.parseDouble(parts[4]),(int)Float.parseFloat(parts[3]),Double.parseDouble(parts[5]),Integer.parseInt(parts[6]));
                countries.add(co);
            }

        } while(Line!=null);
        read.close();
        return countries;
    }



}

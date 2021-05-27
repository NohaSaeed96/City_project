import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CityReader implements CityInterface{
    List<City> cities;
    public CityReader(){
        cities = new ArrayList<City>();
        int n = 0;
        try {

            FileReader fr = new FileReader("D:\\Work\\AI_ITI\\Week6_Java\\Day4\\Day4\\CSV_files\\Cities.csv");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String [] parts;
            City tempCity;
            //line = br.readLine();  //we don't have header

            do{
                line = br.readLine();
                //System.out.println(line);
                if(line != null) {
                    parts = line.split(",");
                    if(parts[0]=="")
                        parts[7]="0";     //handle missing values
                    if(parts[2]=="")
                        parts[7]="0";     //handle missing values
                    tempCity = new City(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), parts[3]);
                    cities.add(tempCity);
                    n++;
                }


            }while (line != null);
            br.close();
            fr.close();
            //System.out.println(n);

        }
        catch (Exception e){
            System.out.println("IO Error");
            System.out.println(n);
        }

    }
    @Override
    public List<City> getAllCites(){
        return cities;
    }

    @Override
    public City getCity(int index){
        return cities.get(index);
    }


}

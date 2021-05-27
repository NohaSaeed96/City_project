import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CityTempMain {
    public static void main(String[] args){
        //read cities
        List<City> cities;
        CityInterface city = new CityReader();
        cities = new ArrayList<City>();
        cities = city.getAllCites();

        //read countries
        List<Country> countries = new ArrayList<Country>();
        read_country_app country_file = new read_country_app();
        try {
            countries = country_file.reader("D:\\Work\\AI_ITI\\Week6_Java\\Day4\\Day4\\CSV_files\\Countries.csv");
        }
        catch (Exception e){
            System.out.println("IO Error");
        }

        List<Country> sorted_countries = countries.stream().sorted(Comparator.comparingInt(Country::getPopulation))
                .collect(Collectors.toList());

        List<Integer> countriesPop = countries.stream().map(Country::getPopulation).collect(Collectors.toList());
        System.out.println("\ncountries population");
        System.out.println(countriesPop);

        //Integer avg = IntStream.of(countries.stream().mapToInt(Country.)).average().getAsDouble());
        double avg = countries.stream().collect(Collectors.averagingDouble(Country::getPopulation));
        System.out.println("\nGet the average countries population");
        System.out.println(avg);

        Country countryTemp = countries.stream()
                .max(Comparator.comparingInt(Country::getPopulation))
                .get();
        System.out.println("\nGet the maximum country population");
        System.out.println(countryTemp.getName());
        System.out.println(countryTemp.getPopulation());


        //create countries to codes map and countries to capital id map
        Map<String, String> countriesToCodes = new LinkedHashMap<>();
        Map<Integer, String> countriesToCapitalId = new LinkedHashMap<>();
        for(Country co:sorted_countries) {
            String countryCode = co.getCode().trim();
            String countryName = co.getName();
            Integer capitalId = co.getCapital();
            countriesToCodes.put(countryCode,countryName);
            countriesToCapitalId.put(capitalId, countryName);
        }

        //getHighestCountries(countriesToCapitalId);
        //double max = countries.stream().collect(Collectors.maxBy(Comparator.comparingInt(City::getPopulation)));
       // Comparator<Country> cmp = Comparator.comparing(Country::getPopulation);
        //System.out.println(cmp.g);



        //store all cities according to its population
        List<City> sorted_cities = cities.stream().sorted(Comparator.comparingInt(City::getPopulation))
                .collect(Collectors.toList());


        //Create a map that contains each country code and its cities which already sorted in the previous step
        Map<String, List<String>> citiesByCode = new LinkedHashMap<>();
        Map<String, Integer> capitalPopulation = new LinkedHashMap<>();
        for(City c:sorted_cities){
            String countryCode = c.getCountryCode().trim();
            //System.out.println(countryCode.length());
            Integer cityId = c.getId();
            Integer pop = c.getPopulation();
            String temp = countriesToCapitalId.get(cityId);
            if(temp!=null)
                capitalPopulation.put(c.getName(),pop);

            List<String> cityList = citiesByCode.get(countryCode);
            if(cityList == null)
                cityList = new ArrayList<String>();

            cityList.add(c.getName());

            //System.out.println(countryCode);
            citiesByCode.put(countryCode,cityList);

        }


        //display Highest population capital
        //System.out.println(capitalPopulation);
        System.out.println("\nHighest population capital");
        getHighestCapital(capitalPopulation);


        //display Highest population city of each country
        System.out.println("\nHighest population city of each country: ");
        Map<String, String> highestCity = getHighestPopulationCity(citiesByCode, countriesToCodes);

        //get user input and display the sorted cities
        //System.out.println(citiesByCode.get(" AFG"));
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("\nInsert the Country Code");
            String name = reader.readLine();

            // Printing the read line
            System.out.println(citiesByCode.get(name));
        }
        catch (Exception e){
            System.out.println("IO Error");

        }


    }

    public static Map<String, String> getHighestPopulationCity(Map<String, List<String>> citiesByCode, Map<String, String> countriesToCodes){
        Map<String, String> highestCity = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : citiesByCode.entrySet()) {
            String key = entry.getKey();

            List<String> value = entry.getValue();
            key = countriesToCodes.get(key);
            //the highest city is the last city since we already sort them
            highestCity.put(key,value.get(value.size()-1));

        }
        System.out.println((highestCity));
        //System.out.println((highestCity.get(" AFG")));
        return highestCity;


    }


    public static String getHighestCapital(Map<String, Integer> capitalPopulation){
        int count = 1;

        for (Map.Entry<String, Integer> it :
                capitalPopulation.entrySet()) {

            if (count == capitalPopulation.size()) {

                System.out.println(it.getKey());
                System.out.println(it.getValue());
                return it.getKey();
            }
            count++;
        }

        return "";
    }


    /*
    public static void getHighestCountries(Map<Integer, String> countriesToCapitalId){
        int count = 1;

        for (Map.Entry<Integer, String> it :
                countriesToCapitalId.entrySet()) {

            if (count >= countriesToCapitalId.size()-4) {

                //System.out.println(it.getKey());
                System.out.println(it.getValue());

            }
            count++;
        }


    }*/
}

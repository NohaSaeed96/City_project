public class City {
    private int id;
    private String name;
    private int population;
    private String countryCode;

    public City(int i, String n, int p, String c){
        id = i;
        name = n;
        population = p;
        countryCode = c;
    }

    public int getId(){
        return id;
    }

    public void setId(int i){
        id = i;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public int getPopulation(){
        return population;
    }

    public void setPopulation(int p){
        population = p;
    }


    public String getCountryCode(){
        return countryCode;
    }

    public void setCountryCode(String c){
        countryCode = c;
    }




}

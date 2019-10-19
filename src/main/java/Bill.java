public class Bill {
//    only good values
//    this is created after validation
    private final String price;
    private final String people;

    public Bill(String price, String people){
        this.price = price;
        this.people = people;
    }

    public String getPrice(){
        return this.price;
    }

    public String getPeople(){
        return this.people;
    }
}

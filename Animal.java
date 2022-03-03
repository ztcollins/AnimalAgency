interface AnimalInterface {
    String getID();
    boolean getExistence();
    void setExistence(Boolean existence);
    String getName();
    String getSpecies();
    int getAge();
    int getArrivalYear();
    int compareTo(Animal a);
}

public class Animal implements AnimalInterface {
    String name;
    String species;
    int age;
    int arrivalYear;
    String ID;
    boolean existence = true;

    public Animal(String name, String species, int age, int arrivalYear) {
        this.name = name.toLowerCase();
        this.species = species.toLowerCase();
        this.age = age;
        this.arrivalYear = arrivalYear;
        ID = species + age + name;
    }


    @Override public void setExistence(Boolean existence) {
        this.existence = existence;
    }

    @Override public boolean getExistence() { return existence; }
    @Override public String getID() { return ID; }
    @Override public String getName() { return name; }
    @Override public String getSpecies() { return species; }
    @Override public int getAge() { return age; }
    @Override public int getArrivalYear() { return arrivalYear; }

    public int compareTo(Animal a) {
        return ID.compareTo(a.getID());
    }
}


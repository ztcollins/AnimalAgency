import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
interface AnimalLoaderInterface {
    List<AnimalInterface> loadAnimals(String filePath) throws IOException; //returns list of Animal objects from raw data
}

public class AnimalLoader implements AnimalLoaderInterface {

    @Override
    public List<AnimalInterface> loadAnimals(String filePath) throws IOException {
        List<AnimalInterface> animals = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] header = reader.readLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (!header[0].equals("name") || !header[1].equals("species") || !header[2].equals("age") || !header[3].equals("arrivalYear") || header.length != 4) {
            return null;
        }
        String rawAnimalData = reader.readLine();
        while (rawAnimalData != null) {
            String[] animalData = rawAnimalData.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            AnimalInterface animal = new Animal(animalData[0], animalData[1], Integer.parseInt(animalData[2]), Integer.parseInt(animalData[3]));
            animals.add(animal);
            rawAnimalData = reader.readLine();
        }
        return animals;
    }
}

class AnimalLoaderPlaceholder implements AnimalLoaderInterface {
    @Override
    public List<AnimalInterface> loadAnimals(String filePath) {
        Animal jacob = new Animal("jacob", "human", 21, 2000);
        Animal artanis = new Animal("artanis", "cat", 4, 2018);
        Animal raynor = new Animal("raynor", "cat", 2, 2021);
        List<AnimalInterface> list = new LinkedList<>();
        list.add(jacob);
        list.add(artanis);
        list.add(raynor);
        return list;
    }
}

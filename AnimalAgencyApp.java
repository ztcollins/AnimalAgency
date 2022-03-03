// --== CS400 Project Two File Header ==--
// Name: Xindi Tang
// Email: xtang89@wisc.edu
// Team: Red
// Group: CH
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.List;
import java.util.LinkedList;

public class AnimalAgencyApp {

  public static void main(String[] args) throws Exception {
    // data wrangler: Animal.java implements AnimalInterface, AnimalLoader.java
    List<AnimalInterface> animals =
        new AnimalLoader().loadAnimals("/scp/data.csv");

    // backend developer: AnimalAgencyBackend.java, implements AnimalAgencyBackendInterface.java
    AnimalAgencyBackendInterface<AnimalInterface> engine = new AnimalAgencyBackend();

    for (AnimalInterface animal : animals) {
      engine.add(animal);
    }

    // frontend developer: AnimalAgencyFrontEnd.java implements AnimalAgencyFrontEnd
    AnimalAgencyFrontEnd ui = new AnimalAgencyFrontEnd();

    ui.run(engine);
  }

}


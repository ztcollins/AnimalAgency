//--== CS400 Project Two File Header ==--
//Name: AiJing Wu
//Email: awu53@wisc.edu
//Team: Red
//Group: CH
//TA: Harper
//Lecturer: Florian Heimerl
//Notes to Grader: <optional extra notes>

import java.time.Year;
import java.util.LinkedList;
import java.util.Scanner;

interface AnimalAgencyFrontEndInterface {    
	public void run(AnimalAgencyBackendInterface backEngine);
		// AnimalAgency Menu:
		// 1. Overview
		// 2. Search for animal species to adopt
		// 3. Decide to adopt
		// 4. Found a new animal to live in the agency
		// 5. Quit
	}

class AnimalAgencyFrontEndPlaceholder implements AnimalAgencyFrontEndInterface {
	public void run(AnimalAgencyBackendInterface backEngine) {
		System.out.println("This front end has not been implemented yet.");
	}    
}

public class AnimalAgencyFrontEnd implements AnimalAgencyFrontEndInterface {

	boolean status = true; //true for running, false for stopping
	
	//@Override
	public void run(AnimalAgencyBackendInterface backEngine) {
		Scanner inOverall = new Scanner(System.in);
		while (status) {
			printMenu();
			String input = inOverall.next();
			switch (input) {
			case "1":
				overview(backEngine);
				break;
			case "2":
				searchBySpecies(backEngine);
				break;
			case "3":
				adoptionDecision(backEngine);
				break;
			case "4":
				addNewAnimal(backEngine);
				break;
			case "5":
				exit();
				break;
			default:
				System.out.println("Sorry, we cannot process your requirement.");
				System.out.println("Please choose from the following options again.");
				System.out.println("Use the index number only to signal your choice (eg. 1).");
				break;
			}
		}
		inOverall.close();
	}

	private void printMenu() {
		System.out.println("Welcome to Animal Agency!");
		System.out.println("Animal Agency Menu: (please enter the index number of your selection, eg. 1)");
		System.out.println("1. Overview");
		System.out.println("2. Search for animal species to adopt");
		System.out.println("3. Decide to adopt");
		System.out.println("4. Found a new animal to live in the agency");
		System.out.println("5. Quit");
	}

	private void overview(AnimalAgencyBackendInterface backEngine) {
		System.out.println("We have had " + backEngine.size() + " animals with us in total.");
		if (backEngine.isEmpty()) {
			System.out.println("We currently do not have any animals.");
		}
		else {
			System.out.println("We have different animals now.");
		}
		System.out.println("We welcome new members to join in us.");
	}

	private void printAnimal(Animal animal) {
		System.out.println(animal.name + ", " + animal.age + " years old, came here in " + animal.arrivalYear);
	}
	
	private void searchBySpecies(AnimalAgencyBackendInterface backEngine) {
		Scanner in = new Scanner(System.in);
		String species = in.next();
		LinkedList<Animal> searchResult = backEngine.search(species);
		if (!searchResult.isEmpty()) {
			System.out.println("We have the following animals living here: ");
			for (int i=0; i<searchResult.size(); i++) {
				printAnimal(searchResult.get(i));
			}
		}
		else {
			System.out.println("Sorry, this species does not live here.");
		}
		in.close();
	}

	private void adoptionDecision(AnimalAgencyBackendInterface backEngine) {
		System.out.println("Do you like any of these lovely animals? I hope you do.");
		System.out.println("Would you like to adopt one? (Yes or No)");
		Scanner in = new Scanner(System.in);
		String input = in.next();

		if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("Y")) {
			System.out.println("Please enter the name of the animal you'd like to adopt: ");
			String name = in.next();
			System.out.println("Please enter the age of the animal you'd like to adopt: ");
			int age = in.nextInt();
			System.out.println("Please enter the species of the animal you'd like to adopt: ");
			String species = in.next();
			
			if (backEngine.adopt(species, age, name)) {
				System.out.println("Thank you for your kindness!!!");
			}
			else {
				System.out.println("Sorry, this animal doesn't live here. Could you please double check the information you just entered?");
			}
		}
		else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("N")) {
			System.out.println("We totally understand your choice.");
		}
		else {
			System.out.println("Sorry, we cannot understand your input.");
		}

		in.close();
	}

	private void addNewAnimal(AnimalAgencyBackendInterface backEngine) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the name of the animal: ");
		String name = in.next();
		System.out.println("Please enter the species of the animal: ");
		String species = in.next();
		System.out.println("Please enter the age of the animal (in integer): ");
		int age = 0;
		try {
			age = in.nextInt();
		} catch (Exception e) {
			System.out.println("Please enter an integer.");
		}
		Animal animal = new Animal(name, species, age, Year.now().getValue());
		backEngine.add(animal);
		System.out.println("We will take care of her/him!");
		in.close();
	}

	private void exit() {
		status = false;
		System.out.println("Thank you for visiting Animal Agency!");
	}

}


// --== CS400 Project One File Header ==--
// Name: Zachary Collins
// Email: ztcollins@wisc.edu
// Team: Red
// Group: CH
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: <test class>
import java.time.Year;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalAgencyBackendTester {

	public static void main(String[] args) {
		
		System.out.println(BackEndDeveloper_TestSizeAndEmpty());
		System.out.println(BackEndDeveloper_TestAdd());
		System.out.println(BackEndDeveloper_TestSearch());
		System.out.println(BackEndDeveloper_TestRotationAndProperties());
		System.out.println(BackEndDeveloper_TestAdopt());
		

	}
	@Test
	public static boolean BackEndDeveloper_TestAdd() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		
		if(backend.root.data.getExistence() != true || backend.root.data.getName() != "albert") {
			return false;
		}
		return true;
	}
	@Test
	public static boolean BackEndDeveloper_TestRotationAndProperties() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		
		if(backend.root.data.getName() != "brad") {
			return false;
		}
		
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("albert2", "cat", 2, Year.now().getValue()));
		
		if(backend.root.leftChild.data.getName() != "albert") {
			return false;
		}
		if(backend.root.leftChild.rightChild.data.getName() != "albert2") {
			return false;
		}
		return true;
		
	}
	@Test
	public static boolean BackEndDeveloper_TestSearch() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		LinkedList<AnimalInterface> searchList = backend.search("snake");
		
		if(searchList.size() != 2) {
			return false;
		}
		if(searchList.get(0).getName() != "chad" && searchList.get(1).getName() != "charlie") {
			return false;
		}
		return true;
	}
	@Test
	public static boolean BackEndDeveloper_TestSizeAndEmpty() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		if(!backend.isEmpty()) {
			return false;
		}
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		if(backend.size() != 4) {
			return false;
		}
		return true;
		
	}
	@Test
	public static boolean BackEndDeveloper_TestAdopt() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		backend.adopt("dog", 3, "brad");
		if(backend.root.data.getExistence() != false) {
			return false;
		}
		backend.adopt("dawg", 3, "brad");
		return true;
		
	}
	
}

import java.time.Year;
import java.util.LinkedList;

public class AnimalAgencyTests {

    public static void main(String[] args) throws Exception {
        // Run All Tests
    }

    // Data Wrangler Code Tests

    // Back End Developer Tests
	
    	@Test
	public static void BackEndDeveloper_TestAdd() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		
		if(backend.root.data.existence != true || backend.root.data.name != "albert") {
			Assertions.fail();
		}
		Assertions.assertTrue(true);
	}
	@Test
	public static void BackEndDeveloper_TestRotationAndProperties() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		
		if(backend.root.data.name != "brad") {
			Assertions.fail();
		}
		
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("albert2", "cat", 2, Year.now().getValue()));
		
		if(backend.root.leftChild.data.name != "albert") {
			Assertions.fail();
		}
		if(backend.root.leftChild.rightChild.data.name != "albert2") {
			Assertions.fail();
		}
		Assertions.assertTrue(true);
		
	}
	@Test
	public static void BackEndDeveloper_TestSearch() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		LinkedList<Animal> searchList = backend.search("snake");
		
		if(searchList.size() != 2) {
			Assertions.fail();
		}
		if(searchList.get(0).name != "chad" && searchList.get(1).name != "charlie") {
			Assertions.fail();
		}
		Assertions.assertTrue(true);
	}
	@Test
	public static void BackEndDeveloper_TestSizeAndEmpty() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		if(!backend.isEmpty()) {
			Assertions.fail();
		}
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		if(backend.size() != 4) {
			Assertions.fail();
		}
		Assertions.assertTrue(true);
		
	}
	@Test
	public static void BackEndDeveloper_TestAdopt() {
		AnimalAgencyBackend backend = new AnimalAgencyBackend();
		backend.add(new Animal("albert", "cat", 23, Year.now().getValue()));
		backend.add(new Animal("brad", "dog", 3, Year.now().getValue()));
		backend.add(new Animal("chad", "snake", 14, Year.now().getValue()));
		backend.add(new Animal("charlie", "snake", 14, Year.now().getValue()));
		backend.adopt("dog", 3, "brad");
		if(backend.root.data.existence != false) {
			Assertions.fail();
		}
		Assertions.assertTrue(true);
		
	}

    // Front End Developer Tests
	@Test
	public void FrontEndDeveloper_TestMenuAndOverview() {
		TextUITester tester = new TextUITester("1\n5\n");
        run();
        String output = tester.checkOutput();
        Assert.assertTrue(output.startsWith("Welcome to Animal Agency!") && output.contains("We welcome new members to join in us.") && output.endsWith("Thank you for visiting Animal Agency!"));
	}
	
	@Test
	public void FrontEndDeveloper_TestAddAnimal() {
        TextUITester tester = new TextUITester("4\nDotty\ndog\n7\n");
        run();
        String output = tester.checkOutput();
        Assert.assertTrue(output.contains("We will take care of her/him!"));
	}
	
	@Test
	public void FrontEndDeveloper_TestSearchAndAdopt() {
		TextUITester tester = new TextUITester("2\ndog\n3\ny\nDotty\n7\ndog\n");
        run();
        String output = tester.checkOutput();
        Assert.assertTrue(output.contains("Thank you for your kindness!!!"));
	}
	
    // Integration Manager Tests


}

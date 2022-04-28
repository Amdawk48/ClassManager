package orange.amd.main;

import orange.amd.service.ClassManager;

public class RunApplication {
	
	public static void main(String[] args) {
		ClassManager classManager = new ClassManager();
		classManager.generateClass("ClassA.csv", "ClassA");
		classManager.generateClass("ClassB.csv", "ClassB");
		classManager.generateClass("ClassC.csv", "ClassC");
		classManager.generateClassFile("ClassInfo");
	}
	
}

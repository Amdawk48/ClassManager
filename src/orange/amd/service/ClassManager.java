package orange.amd.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import orange.amd.models.*;

public class ClassManager {
	private static String BASE_URL = "src/";
	
	private Map<String, MyClass> classes = new TreeMap<>();
	private MyClass bestClass;
	
	public Map<String, MyClass> getClasses() {
		return classes;
	}

	public void generateClass(String fileName, String className) {
		List<Student> students  = new ArrayList<Student>();
		List<String> rows = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(BASE_URL + fileName))){
			String line;
			while((line = br.readLine()) != null) {
				rows.add(line);
				
			}

			for (int i = 1; i < rows.size(); i++) {
				String[] values = rows.get(i).split(",");
				Student student = new Student(values[0], Double.valueOf(values[1]).doubleValue());
				students.add(student);
			}
			
			classes.put(className, new MyClass(className, students));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void generateClassFile(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(BASE_URL + fileName, false));
			getClassWithHighestAverage();
			for(String cName : classes.keySet()) {
				writer.append(classes.get(cName).displayClassInfo());
				writer.append("\n");
			}
			writer.append("======================================================================================== \n");
			writer.append(String.format("The highest average was in %s with an average of %s", bestClass.getName(), bestClass.getClassAverage()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private MyClass getClassWithHighestAverage() {
		for(String cName : classes.keySet()) {
			if(bestClass == null) {
				bestClass = classes.get(cName);
				continue;
			}
			if(classes.get(cName).getClassAverage() > bestClass.getClassAverage()) {
				bestClass = classes.get(cName);
			}
		}
		return bestClass;
	}
	
}

package Lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Clazz {
private String name;
private String year;
private ArrayList<Student> students = new ArrayList<Student>();
public Clazz(String name, String year) {
	this.name = name;
	this.year = year;
}

public Clazz(ArrayList<Student> students) {
	this.students = new ArrayList<Student>();
}

 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public ArrayList<Student> getStudents() {
	return students;
}
public void setStudents(ArrayList<Student> students) {
	this.students = students;
}
@Override
public String toString() {
	return "" + name + "," + year + "," + students + "";
}
public Clazz() {
}

//Task 1. load all students from filename into the list of students
public void loadStudents(String fileName) {
	    try {
			
	    	FileInputStream input = new FileInputStream(fileName);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(input));
	    	String line = br.readLine();
	    	while(line!= null) {
	    		System.out.println(line);
	    		String[] part = line.split("\t");
	    		String id = part[0];
	    		String firstName= part[1];
	    		String lastName = part[2];
	    		LocalDate localdate =LocalDate.parse(part[3]);
	    		double gpa = Double.parseDouble(part[4]);
	    		Student st = new Student(id, firstName, lastName, localdate, gpa);
	    		this.students.add(st);
	    		line= br.readLine();
	    		
	    	}
	    	for(int i = 0;i<this.students.size();i++) {
	    		System.out.println(this.students.get(i));
	    	}
	    	
	    	
		} catch (FileNotFoundException e ) {
			System.out.println(e.getMessage());
		}
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	}
 
//Task 2. sort students according to the given comparator c
public void sortStudents(Comparator<Student> c) {
 Collections.sort(this.students,c);
}


//task3

public List<Student> getTopNStudents(int n) {
	List<Student > result = new ArrayList<Student>();
	Comparator<Student> c = new Comparator<Student>() {
		
		@Override
		public int compare(Student o1, Student o2) {
		Double d1 = o1.getGPA();
		Double d2= o2.getGPA();
			return d2.compareTo(d1);
		}
	};
	this.sortStudents(c);
	for(int i = 0; i<n;i++) {
		result.add(this.students.get(i));
		
	}
	return result;
	}
public ArrayList<Student> getRandomNStudents(int n) {
	ArrayList<Student > result = new ArrayList<Student>();
this.shuff(this.students);
for(int i =0; i<n;i++) {
	result.add(this.students.get(i));
}
	return result;
	}

private void shuff(ArrayList<Student> students2) {
	Random rd = new Random();
	int rd1 = 0;
	int rd2 = 0;
	for(int i= 0; i<10;i++) {
		while(rd1==rd2) {
			rd1 = rd.nextInt(this.students.size());
			rd2 = rd.nextInt(this.students.size());
		}
		this.swap(rd1, rd2);
	}
	
}

private void swap(int rd1, int rd2) {
	Student tmp = this.students.get(rd1);
		 students.set(rd1, this.students.get(rd2));
		 students.set(rd2, tmp);
	
}
public boolean removeStudent(String id) {
	for (Student student : students) {
		if(student.getId().equalsIgnoreCase(id)) {
			students.remove(student);
return true;
		}
	}
	return false;

}
//Task 6. get all students who were born in a given birth year.
public ArrayList<Student> getStudentByBirthYear(int birthYear) {
	LocalDate by = LocalDate.parse(birthYear+"");
	ArrayList<Student> s1 = new ArrayList<Student>();
 for (Student student : students) {
	if(student.getBirthday().equals(by)) {
		s1.add(student);
		 
	}
}
return s1;
}
//Task 7. get all students who have birthdays in the current month (i.e., November).

public ArrayList<Student> getStudentsHavingBrithdaysInCurrentMonth(int curr){
	ArrayList<Student> s1 = new ArrayList<Student>();
	for (Student student : students) {
		if(student.getBirthday().getMonth().equals(curr)) {
			s1.add(student);
			 
		}
	}
	return s1;
}
//Task 9. get the oldest student based on the birth year.
public Student getOldestStudent() {
  Student max = this.students.get(0);
  for(int i =1; i<this.students.size();i++) {
	  Student x = students.get(i);
	  if(x.getBirthday().getYear()> max.getBirthday().getYear()) {
		  max = x;
	  }
  }
return max;
	 
}
//Task 10. get the student having the highest GPA based on a given admission year.
public Student getHighestGPAStudent(int year) {
	List<Student> list = new ArrayList<Student>();
	 for (Student student : students) {
		if(student.getBirthday().getYear()== year) {
			list.add(student);
		}
	}
	 Student max =  list.get(0);
	  for(int i =1; i<list.size();i++) {
		  Student x = students.get(i);
		  if(x.getGPA()> max.getGPA()) {
			  max = x;
		  }
	  }
	return max;
}
public static void main(String[] args) {
	String file = "src/data/students.txt";
	Clazz clazz = new Clazz();
	clazz.loadStudents(file);
	
	System.out.println("\nTask 2: Sort students by GPA:");
 // Sắp xếp sinh viên theo GPA giảm dần
	Comparator<Student> gpaComparator = new Comparator<Student>() {
	    @Override
	    public int compare(Student s1, Student s2) {
	        // So sánh GPA của sinh viên s2 với s1 để sắp xếp theo thứ tự giảm dần
	        return Double.compare(s2.getGPA(), s1.getGPA());
	    }
	};
	clazz.sortStudents(gpaComparator);
    for (Student st : clazz.getStudents()) {
		System.out.println(st);
	}
    System.out.println("Task3");
    List<Student> topNStudents = clazz.getTopNStudents(3);
    for (Student student : topNStudents) {
		System.out.println(student);
	}
    System.out.println("Task4");
    List<Student>ranDomStudent = clazz.getRandomNStudents(4);
    for (Student student :ranDomStudent) {
		System.out.println(student);
	}
    
}


}

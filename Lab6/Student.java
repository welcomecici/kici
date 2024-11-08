package Lab6;

import java.time.LocalDate;

public class Student{
private String id, firstName,lastName;
private LocalDate birthday;
private double GPA;
public Student(String id, String firstName, String lastName, LocalDate birthday, double gPA) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.birthday = birthday;
	GPA = gPA;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public LocalDate getBirthday() {
	return birthday;
}

public void setBirthday(LocalDate birthday) {
	this.birthday = birthday;
}

public double getGPA() {
	return GPA;
}

public void setGPA(double gPA) {
	GPA = gPA;
}

@Override
public String toString() {
	return "" + id + "," + firstName + "," + lastName + "," + birthday
			+ "," + GPA + "";
}

 public boolean compareTo(Student s1 ) {
	 return this.birthday.getYear()<s1.getBirthday().getYear();
 }
}

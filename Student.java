public abstract class Student {
    private String id;
    private String firstName;
    private String lastName;
    private double gpa;
    private String department;
    private Address address;

    
    public Student(String id, String firstName, String lastName,
                   double gpa, String department, Address address) throws InvalidGPAException {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        setGpa(gpa);
        this.department = department;
        this.address = address;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) throws InvalidGPAException {
        if (gpa < 0.0 || gpa > 4.0) throw new InvalidGPAException("Invalid GPA: " + gpa);
        this.gpa = gpa;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public abstract String getLevelDescription();

    public String getAcademicStanding() {
        if (gpa >= 3.5) return "Excellent";
        if (gpa >= 3.0) return "Good";
        if (gpa >= 2.0) return "Satisfactory";
        return "At risk";
    }

    @Override
    public String toString() {
        return String.format("%s %s (ID:%s) - Dept: %s, GPA: %.2f, Level: %s, Address: [%s]",
                firstName, lastName, id, department, gpa, getLevelDescription(), address);
    }
}

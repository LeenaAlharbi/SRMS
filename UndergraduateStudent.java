public class UndergraduateStudent extends Student {
    private int year;

    public UndergraduateStudent(String id, String firstName, String lastName,
                                double gpa, String department, Address address, int year)
            throws InvalidGPAException {
        super(id, firstName, lastName, gpa, department, address);
        this.year = year;
    }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String getLevelDescription() {
        return "Undergraduate (Year " + year + ")";
    }

    @Override
    public String getAcademicStanding() {
        if (getGpa() >= 3.6) return "Excellent";
        if (getGpa() >= 3.2) return "Good";
        return super.getAcademicStanding();
    }
}

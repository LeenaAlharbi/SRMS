public class GraduateStudent extends Student {
    private String program;
    private String supervisor;

    public GraduateStudent(String id, String firstName, String lastName,
                           double gpa, String department, Address address,
                           String program, String supervisor) throws InvalidGPAException {
        super(id, firstName, lastName, gpa, department, address);
        this.program = program;
        this.supervisor = supervisor;
    }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getSupervisor() { return supervisor; }
    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }

    @Override
    public String getLevelDescription() {
        return "Graduate (" + program + ")";
    }

    @Override
    public String getAcademicStanding() {
        if (getGpa() >= 3.7) return "Outstanding";
        return super.getAcademicStanding();
    }
}

package Entity;

public class Run {
    private int id;
    private String name;
    private int membersLimit;

    public Run() {
    }

    public Run(int id, String name, int membersLimit) {
        this.id = id;
        this.name = name;
        this.membersLimit = membersLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }
}

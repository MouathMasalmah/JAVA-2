package Activity3;

public abstract class Studens {
    private int id;
    private String name;
    private char gender;
    private String school;

    Studens() {
    }

    public Studens(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Studens(int id, String name, char gender, String school) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.school = school;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

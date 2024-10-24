package Activity3;

public class Subject {
    private String title;
    private String type;
    private int mark;
    private final String M = "Mandatory", EI = "ElectiveI", EII = "ElectiveII";
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if (title != null)
            this.title = title;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        if (type.equals(M) || type.equals(EI) || type.equals(EII))
            this.type = type;
    }
    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        if (mark >= 0 || mark <= 200)
            this.mark = mark;
    }
    public Subject(String title, String type, int mark) {
        this.title= title;
        this.type= type;
        this.mark=mark;
    }
}
package Activity3;

import java.util.*;

public abstract class Tawjihi extends Studens {
    private int seatingNum;
    private int year = 2022;
    private ArrayList<Subject> subjects = new ArrayList<>();

    public Tawjihi() {

    }

    public Tawjihi(int ID, String name, char gender, String school) {
        super(ID, name, gender, school);
    }

    public Tawjihi(int seatingNum, int ID, String name, char gender, String school) {
        super(ID, name, gender, school);
        this.seatingNum = seatingNum;
        
    }

    public abstract ArrayList<String> subjects();

    public int getSeatingNumber() {
        return seatingNum;
    }

    public void setSeatingNumber(int seatingNum) {
        if (seatingNum >= 0)
            this.seatingNum = seatingNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 0)
            this.year = year;
    }

    public ArrayList<Subject> getSubjects() {
        ArrayList<Subject> s = subjects;
        return s;
    }


    public abstract double calculateAverage();

    @Override
    public abstract String toString();

    public void addSubject(Subject s) {
        if (s != null)
            subjects.add(s);
    }

    public Subject getSubject(String title) {
        if (title != null)
            for (int i = 0; i < subjects.size(); i++)
                if (subjects.get(i).getTitle().equals(title))
                    return subjects.get(i);
        return null;
    }

    public void setSubjectMark(String subjectName, int newMark) {
        for (int i = 0; i < subjects.size(); i++)
            if (subjects.get(i).getTitle().equals(subjectName)) {
                subjects.get(i).setMark(newMark);
                break;
            }
    }
}
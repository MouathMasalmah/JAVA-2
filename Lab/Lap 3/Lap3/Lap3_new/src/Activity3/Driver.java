package Activity3;

public class Driver {
    public static void main(String[] args) {
    
        Scientific scientific1 = new Scientific(1222, "Mohammad", 'M', "Ramallah");
        Literary literal1 = new Literary(1222, "Aseel", 'G', "Bet lahem");
        Subject subject1 = new Subject("Eng","Mandatory" ,75);
        Subject subject2 = new Subject("Math","Elective" ,80);
        scientific1.addSubject(subject1);
        scientific1.addSubject(subject2);
        Subject subject3 = new Subject("Math","Mandatory", 90);
        Subject subject4 = new Subject("Arabic","Elective" ,75 );
        literal1.addSubject(subject4);
        literal1.addSubject(subject3);
        System.out.println(scientific1.calculateAverage());
        System.out.println(literal1.calculateAverage());
    }
}

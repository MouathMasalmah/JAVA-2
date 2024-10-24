
public class Seat {

    private int seatNumber;
    private String passengerName;

    public Seat(int seatNumber) {

        this.seatNumber = seatNumber;
    }

    Seat(int seatNumber, String passengerName) {
        this.seatNumber = seatNumber;
        this.passengerName = passengerName;
    }

    public static boolean isValid(int seatNumber) {
        return seatNumber >= 1 && seatNumber <= 92&&seatNumber !=3 &&seatNumber !=4&&seatNumber !=91&&seatNumber !=92;
    }

    public static int getRow(int seatNumber) {
        if (isValid(seatNumber)) {
            return (seatNumber - 1) / 4 + 1;
        } else {
            return -1;
        }
    }

    public static int getColumn(int seatNumber) {
        if (isValid(seatNumber)) {
            return (seatNumber - 1) % 4 + 1;
        } else {
            return -1;
        }
    }

    public int getSeatNumber() {

        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {

        this.seatNumber = seatNumber;
    }

    public String getPassengerName() {

        return passengerName;
    }

    public void setPassengerName(String passengerName) {

        this.passengerName = passengerName;
    }

    public boolean isEmpty() {

        return passengerName == " " || seatNumber == -1;
    }

    public String toString() {

        return "Seat Number: " + seatNumber + ", Passenger Name: " +  passengerName;
    }
}

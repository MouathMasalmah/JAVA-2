
class Train {
    private Seat[][] trainSeats = new Seat[24][];

    Train() {
        trainSeats = new Seat[24][4];
        trainSeats[0] = new Seat[3];
        for (int i = 1; i <= 21; i++) {
            trainSeats[i] = new Seat[5];
        }
        trainSeats[23] = new Seat[3];
    }

    public boolean reserveSeat(int seatNumber, String passengerName) {
        if (Seat.isValid(seatNumber)) {
            int row = Seat.getRow(seatNumber) - 1;
            int column = Seat.getColumn(seatNumber) - 1;
            if (trainSeats[row][column] == null) {
                trainSeats[row][column] = new Seat(seatNumber, passengerName);
                return true;
            } else if (trainSeats[row][column].isEmpty()) {
                trainSeats[row][column].setPassengerName(passengerName);
                trainSeats[row][column].setSeatNumber(seatNumber);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSeat(int seatNumber) {
        if (Seat.isValid(seatNumber)) {
            int row = Seat.getRow(seatNumber) - 1;
            int column = Seat.getColumn(seatNumber) - 1;
            if (trainSeats[row][column] != null) {
                trainSeats[row][column].setPassengerName(null);
                return true;

            }
        }
        return false;
    }

    public void deleteAllSeats() {
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < trainSeats[i].length; j++) {
                if (trainSeats[i][j] != null) {
                    trainSeats[i][j] = null;
                }
            }
        }
    }
}

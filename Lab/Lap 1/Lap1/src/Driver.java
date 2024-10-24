
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Train train = new Train();
        Scanner scan = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println("Menu:");
            System.out.println("1. Read the file");
            System.out.println("2. Reserve");
            System.out.println("3. Delete seat");
            System.out.println("4. Delete all seats");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            //scan.nextLine();

            switch (choice) {
                case 1:
                    readPassengersFile(train);
                    break;
                case 2:
                    System.out.print("Enter seat number: ");
                    int seatNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter passenger name: ");
                    String passengerName = scan.nextLine();
                    boolean reservationResult = train.reserveSeat(seatNumber, passengerName);
                    if (reservationResult) {
                        System.out.println("Seat " + seatNumber + " reserved for " + passengerName);
                    } else {
                        System.out.println("Seat " + seatNumber + " is reserved ");
                    }
                    break;
                case 3:
                    System.out.print("Enter seat number to delete: ");
                    int seatToDelete = scan.nextInt();
                    scan.nextLine();
                    boolean deletionResult = train.deleteSeat(seatToDelete);
                    if (deletionResult) {
                        System.out.println("Seat " + seatToDelete + " has been deleted.");
                    } else {
                        System.out.println("Seat " + seatToDelete + " is invalid.");
                    }
                    break;
                case 4:
                    train.deleteAllSeats();
                    System.out.println("All reserved seats deleted.");
                    break;
                case 5:
                    System.out.println("Good Bye");
                    on = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println("\n");
        }
    }

    private static void readPassengersFile(Train train) {
        File file = new File("Passenger.txt");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(" ");
                int seatNumber = Integer.parseInt(parts[0]);
                String passengerName = parts[1];
                if (Seat.isValid(seatNumber)) {
                    if (train.reserveSeat(seatNumber, passengerName)) {
                        System.out.println("seat: " + seatNumber + "  passenger: " + passengerName);
                    } else {
                        System.out.println("Seat " + seatNumber + " is already reserved or invalid.");
                    }
                } else {
                    System.out.println("Invalid seat number: " + seatNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



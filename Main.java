import java.util.ArrayList;
import java.util.Scanner;

class Car {
    String id, brand, model;
    double pricePerDay;
    boolean isAvailable;

    public Car(String id, String brand, String model, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public double calculatePrice(int days) {
        return pricePerDay * days;
    }
}

class Customer {
    String id, name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Rental {
    Car car;
    Customer customer;
    int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }
}

class CarRentalSystem {
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Rental> rentals = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addCar(Car car) {
        cars.add(car);
    }

    private void showAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            if (car.isAvailable) {
                System.out.println(car.id + ": " + car.brand + " " + car.model + " ($" + car.pricePerDay + "/day)");
            }
        }
    }

    private void rentCar() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your ID: ");
        String customerId = scanner.nextLine();
        Customer customer = new Customer(customerId, name);

        showAvailableCars();
        System.out.print("\nEnter Car ID to rent: ");
        String carId = scanner.nextLine();
        System.out.print("Enter rental days: ");
        int days = scanner.nextInt();
        scanner.nextLine();

        for (Car car : cars) {
            if (car.id.equalsIgnoreCase(carId) && car.isAvailable) {
                car.isAvailable = false;
                rentals.add(new Rental(car, customer, days));
                System.out.println("\nCar rented successfully!");
                System.out.println("Customer: " + customer.name + " (ID: " + customer.id + ")");
                System.out.println("Car: " + car.brand + " " + car.model);
                System.out.println("Rental Days: " + days);
                System.out.println("Total Cost: $" + car.calculatePrice(days));
                return;
            }
        }
        System.out.println("Car not available or invalid ID.");
    }

    private void returnCar() {
        System.out.print("\nEnter Car ID to return: ");
        String carId = scanner.nextLine();

        for (Rental rental : rentals) {
            if (rental.car.id.equalsIgnoreCase(carId) && !rental.car.isAvailable) {
                rental.car.isAvailable = true;
                rentals.remove(rental);
                System.out.println("Car returned successfully by " + rental.customer.name);
                return;
            }
        }
        System.out.println("Invalid Car ID or car not rented.");
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== Car Rental System ===");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> rentCar();
                case 2 -> returnCar();
                case 3 -> {
                    System.out.println("Thank you for using the Car Rental System!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem system = new CarRentalSystem();
        system.addCar(new Car("C001", "Toyota", "Camry", 60));
        system.addCar(new Car("C002", "Honda", "Accord", 70));
        system.addCar(new Car("C003", "Mahindra", "Thar", 150));
        system.addCar(new Car("C004", "Ford", "Mustang", 120));
        system.addCar(new Car("C005", "BMW", "X5", 200));
        system.addCar(new Car("C006", "Mercedes", "C-Class", 180));
        system.addCar(new Car("C007", "Audi", "A6", 190));
        system.addCar(new Car("C008", "Tesla", "Model 3", 250));
        system.addCar(new Car("C009", "Jeep", "Wrangler", 140));
        system.addCar(new Car("C010", "Hyundai", "Creta", 80));
        system.addCar(new Car("C011", "Kia", "Seltos", 85));

        system.menu();
    }
}

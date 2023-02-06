package org.example;

import org.example.cli.AdminMenu;
import org.example.cli.InterfaceMenu;
import org.example.cli.MainMenu;
import org.example.models.Customer;

import org.example.models.Reservation;
import org.example.models.Room;
import org.example.models.enums.RoomType;
import org.example.models.interfaces.IRoom;
import org.example.resources.AdminResorces;
import org.example.resources.HotelResources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.spi.LocaleServiceProvider;

public class Main {


    public static void main(String[] args) {
        cli();
    }

    public static void cli() {
        boolean isUp = true;
        MainMenu main = new MainMenu();
        AdminMenu admin = new AdminMenu();
        InterfaceMenu menu = main;
        Scanner scanner = new Scanner(System.in);
        int index;


        while (isUp) {
            menu.displayMenu();
            System.out.println("Please select a option from menu");
            index = scanner.nextInt();

            switch (index) {
                case 1:
                    option1(menu, scanner);
                    break;
                case 2:
                    option2(menu, scanner);
                    break;
                case 3:
                    option3(menu, scanner);
                    break;
                case 4:
                    menu = option4(menu, scanner, admin);
                    break;
                case 5:
                    if(isMainMenu(menu)) {
                        isUp = false;
                        break;
                    }
                    menu = option5(menu, main);
                    break;
                case 6:
                    if(!isMainMenu(menu))
                        isUp = false;
                    else
                        System.out.println("not valid key");
                    break;
                default:
                    System.out.println("not valid key");
            }
        }
    }

    private static void option1(InterfaceMenu menu, Scanner scanner){
        scanner.nextLine();
        if(isMainMenu(menu)){
            try {
                Customer c = emailMethod(scanner);
                System.out.println("\ncheckin date: format -----");
                String checkin = scanner.nextLine();
                System.out.println("\ncheckout date: format -----");
                String checkout = scanner.nextLine();
                List<LocalDate> dates = convertDates(checkin, checkout);
                LocalDate dateCheckIn = dates.get(0);
                LocalDate dateCheckOut = dates.get(1);
                Map<String, Room> rooms = HotelResources.findRoom(dates.get(0), dates.get(1));
                System.out.println("avaible rooms for the dates: \n"+ rooms);
                if(rooms.isEmpty()){
                    System.out.println("not avaible rooms for the dates, try these dates instead: \n");
                    Integer plusDaysToAdd = 7;
                    while(rooms.isEmpty()){
                        dateCheckIn = dateCheckIn.plusDays(plusDaysToAdd);
                        dateCheckOut = dateCheckOut.plusDays(plusDaysToAdd);
                        rooms = HotelResources.findRoom(dateCheckIn, dateCheckOut);
                        plusDaysToAdd += 7;
                    }
                    System.out.println(rooms + "\nnew checkIn: " +dateCheckIn+ "\nnew checkOut: " + dateCheckOut);
                }
                System.out.println("\ndo you want to proceed? y/n");
                String option = scanner.nextLine();

                if(!option.equals("y")){
                    System.out.println("operation cancelled");
                    return;
                }

                System.out.println("\nselect the room number to book:");
                String number = scanner.nextLine();
                while(rooms.get(number) == null) {
                    System.out.println("\nnumber "+ number + " not found, try again");
                    number = scanner.nextLine();
                }
                Reservation r = HotelResources.bookRoom(c, rooms.get(number), dateCheckIn, dateCheckOut);
                System.out.println(r);
                return;
            }catch(DateTimeParseException ex){
                System.out.println("\ndate format are incorrect");
                return;
            }catch (NoSuchElementException ex){
                System.out.println(ex.getLocalizedMessage());
                return;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }

        }
        System.out.println("\nAll the customers: \n");
        System.out.println(AdminResorces.getAllCustomers());
    }

    private static List<LocalDate> convertDates(String checkin, String checkout) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkIn =LocalDate.parse(checkin, formatter);
        LocalDate checkOut =LocalDate.parse(checkout, formatter);
        if(((LocalDate.now().compareTo(checkIn) > 1 || LocalDate.now().compareTo(checkOut) > 1) || checkIn.compareTo(checkOut) > 1)){
            throw new Exception("Dates are invalid");
        }
        return List.of(checkIn,checkOut);
    }

    private static void option2(InterfaceMenu menu, Scanner scanner){
        scanner.nextLine();
        if(isMainMenu(menu)){
            try {
                Customer c = emailMethod(scanner);
               System.out.println(HotelResources.getCustomerReservation(c));
                return;
            }catch (Exception ex){
                System.out.println(ex.getLocalizedMessage());
                return;
            }
        }
        System.out.println("\nAll the rooms: \n");
        AdminResorces.getAllRooms();
    }
    private static void option3(InterfaceMenu menu, Scanner scanner){
        scanner.nextLine();
        String email;
        if(isMainMenu(menu)){
            System.out.println("\nEnter email format: email@email.com");
            email = scanner.nextLine();

            while(!HotelResources.validEmailFormat(email)){
                System.out.println("\ninvalid format, try again");
                email = scanner.nextLine();
            }

            if(HotelResources.getCustomer(email) != null){
                System.out.println("you already have an account");
                return;
            }
            System.out.println("\nFirst Name");
            String firstName = scanner.nextLine();
            System.out.println("\nLast Name");
            String lastName = scanner.nextLine();
            HotelResources.createCustomer(email, firstName, lastName);
            return;
        }

        AdminResorces.displayAllReservations();
    }
    private static InterfaceMenu option4(InterfaceMenu menu, Scanner scanner, AdminMenu adminMenu){
        scanner.nextLine();
        if(isMainMenu(menu)){
            menu = adminMenu;
            return menu;
        }

        try {
            System.out.println("\nroom number: ");
            String roomNumber = scanner.nextLine();

            IRoom room = HotelResources.findRoom(roomNumber);
            while(room != null){
                System.out.println("this room exist, try another number: ");
                roomNumber = scanner.nextLine();
                room = HotelResources.findRoom(roomNumber);
            }

            System.out.println("\nroom price: ");
            Double price = scanner.nextDouble();

            System.out.println("\nroom type: ");
            for(RoomType x : RoomType.values()){
                System.out.println(x.getRoomId() + " - " + x.getDesc());
            }

            RoomType roomType = RoomType.valueOf(scanner.nextInt());
            AdminResorces.addRoom(new Room(roomNumber, price, roomType));
            return menu;
        }catch (Exception ex){
            System.out.println("invalid option");
            scanner.nextLine();
            return menu;
        }
    }
    private static InterfaceMenu option5(InterfaceMenu menu, MainMenu mainMenu){
        return mainMenu;
    }

    private static boolean isMainMenu(InterfaceMenu menu){
        return menu.getClass() == MainMenu.class;
    }
    private static Customer emailMethod(Scanner scanner) throws Exception {
        System.out.println("\nenter your email: ");
        String email = scanner.nextLine();
        while(!HotelResources.validEmailFormat(email)){
            System.out.println("\ninvalid format, try again");
            email = scanner.nextLine();
        }
        Customer c = HotelResources.getCustomer(email);
        if(c == null){
            throw new Exception("\ncustomer not found, create an account first");
        }
        return c;
    }
}
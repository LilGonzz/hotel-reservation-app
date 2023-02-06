package org.example.cli;

public class AdminMenu implements InterfaceMenu{
    private String charArrays = "---------------------------";
    @Override
    public void displayMenu() {
        System.out.println(charArrays +"\n1 - See all customers\n"+
                "2 - See all rooms\n"+
                "3 - See all reservations\n"+
                "4 - Add a room\n" +
                "5 - Back to main menu\n"+
                "6 - Exit\n"+charArrays);
    }
}

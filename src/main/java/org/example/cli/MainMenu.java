package org.example.cli;

public class MainMenu implements InterfaceMenu{
    private String charArrays = "---------------------------";
    @Override
    public void displayMenu() {
        System.out.println(charArrays +
                "\n1 - Find and reserve a room\n"+
                "2 - See my reservations\n"+
                "3 - create an account\n"+
                "4 - Admin menu\n" +
                "5 - Exit\n"+
                charArrays);
    }
}

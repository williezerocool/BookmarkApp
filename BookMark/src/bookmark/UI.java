/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmark;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kendrabooker
 */
public class UI {
    
    public static final int MIN_MENU_ITEM = 1;
    public static final int MAX_MENU_ITEM = 6;

    public static final int MENU_LIST = 1;
    public static final int MENU_ADD = 2;
    public static final int MENU_DELETE = 3;
    public static final int MENU_EDIT = 4;
    public static final int MENU_SEARCH = 5;
    public static int COUNT = 0;

    private Scanner scanner = new Scanner(System.in);

    public void displayWelcome() {
        printLine("===============================");
        printLine("");
        printLine("Start Your Bookmark List Today");
        printLine("");
        printLine("===============================");
    }

    public void displayNoItemsFound() {
        printLine("");
        printLine("***");
        printLine("No Bookmark Found.");
        printLine("***");
        pressEnterToContinue();
    }
    
    public void displayBookmark(List<BookMarks> bookmark) {
        // TODO: update this code to make it display each task's status:
        // complete or not.
        printLine("");
        printLine("Bookmarks:");
        printLine("===========");
        for (BookMarks mark : bookmark) {
             printLine(mark.getTitle() + "\n" + mark.getUrl());
        }
        pressEnterToContinue();
    }
    
    public void displayResponse(Response response) {
        printLine("");
        printLine("***");
        if (response.isSuccess()) {
            printLine("Success!");
        } else {
            printLine(response.getMessage());
        }
        printLine("***");
        pressEnterToContinue();
    }
    
    public void displayGoodbye() {
        printLine("");
        printLine("==========");
        printLine("");
        printLine("Goodbye...");
        printLine("");
        printLine("==========");
    }
    
    public void printLine(String message) {
        print(message + "\n");
    }
    
    public int getMenuSelection() {
        printMenu();
        return readInt("Choose an option (1 - 6):");
    }
    
    public BookMarks createBookmark() {
        printLine("");
        printLine("Add a Bookmark");
        printLine("============");

        BookMarks item = new BookMarks();
        item.setTitle(readLine("Enter Bookmark title: "));
        item.setUrl(readLine("Enter Bookmark url: "));
        item.setTag(readLine("Enter Bookmark tags: "));
        item.setCreateDate(LocalDate.now());
        item.setId(COUNT);
        COUNT++;
        return item;
    }
    
    private void printMenu() {
        printLine("");
        printLine("===========");
        printLine("Main Menu:");
        printLine("1. List All Bookmarks");
        printLine("2. Add a Bookmark");
        printLine("3. Delete a Bookmark");
        printLine("4. Edit a Bookmark");
        printLine("5. Search for a Bookmark");
        printLine("6. Exit");
    }
    
    private void pressEnterToContinue() {
        printLine("");
        readLine("Press <enter> to continue.");
    }
    
    private void print(String message) {
        System.out.print(message);
    }
    
    private String readLine(String message) {
        print(message);
        return scanner.nextLine();
    }
    
    private int readInt(String message) {
        int result = Integer.MIN_VALUE;
        while (result == Integer.MIN_VALUE) {
            try {
                result = Integer.parseInt(readLine(message));
            } catch (NumberFormatException ex) {
            }

        }
        return result;
    }
    
    private int readInt(String message, int min, int max) {
        int result = Integer.MIN_VALUE;
        while (result < min || result > max) {
            result = readInt(message);
        }
        return result;
    }
    
    public String getBookmarkTitle() {
        String title = readLine("Enter Bookmark title: ");
        return title;
    }
    
    public String areaToEdit() {
        String edit = readLine("Enter the area of the bookmark you want to edit(title, url, or tag): ");
        return edit;
    }
    
    public BookMarks makingEdit(BookMarks bookmark, String edit) {
        
        if(edit.equalsIgnoreCase("title") ) {
            bookmark.setTitle(readLine("Edit title: ") );
        }else if(edit.equalsIgnoreCase("url")) {
            bookmark.setUrl(readLine("Edit url: "));
        }else if(edit.equalsIgnoreCase("tag") ) {
            bookmark.setTag(readLine("Edit tag: "));
        }
        return bookmark;
    }
    
    public String search() {
        String search = readLine("Enter the title, url or tag of the bookmark you are searching for:  ");
        return search;
    }
    
}

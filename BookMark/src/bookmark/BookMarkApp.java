/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kendrabooker
 */
public class BookMarkApp {
    
    private UI ui = new UI();
    private BookMarkDataStore data = new BookMarkDataStore();
    
    public void run() {

        ui.displayWelcome();

        int selection = UI.MIN_MENU_ITEM;
        while (selection >= UI.MIN_MENU_ITEM && selection < UI.MAX_MENU_ITEM) {

            selection = ui.getMenuSelection();

            switch (selection) {
                case UI.MENU_LIST:
                    list();
                    break;
                case UI.MENU_ADD:
                    add();
                    break;
                case UI.MENU_DELETE:
                    delete();
                    break;
                case UI.MENU_EDIT:
                    edit();
                    break;
                case UI.MENU_SEARCH:
                    search();
                    break;
            }
        }

        ui.displayGoodbye();
    }
    
    private void list() {
        List<BookMarks> bookmark = data.all();
        if (bookmark.size() == 0) {
            ui.displayNoItemsFound();
        } else {
            ui.displayBookmark(bookmark);
        }
    }
    
    private void add() {
        BookMarks task = ui.createBookmark();
        Response response = data.add(task);
        ui.displayResponse(response);
    }
    
    private void delete() {
        String bookMarkTitle = ui.getBookmarkTitle();
        BookMarks bookmark = data.deleteBookmark(bookMarkTitle);
        response(bookmark);
        ui.printLine("Deleting Bookmark!");
    }
    
   
    
    private void edit(){
        BookMarks bookmark = data.getBookMarkByTitle();
        String edit = ui.areaToEdit();
        BookMarks a = bookmark;
        BookMarks newBookmark = ui.makingEdit(bookmark, edit);
        data.deleteBookmark(a.getTitle());
        List<BookMarks> all = data.all();
        all.add(newBookmark);
        data.write(all);
        response(newBookmark);
        ui.printLine("Editing Bookmark!");
    }
    
    private void search() {
        String search = ui.search();
        List<BookMarks> bookmark = new ArrayList<>();
        for(BookMarks mark : data.all() ) {
            if(mark.getTitle().equalsIgnoreCase(search) ) {
                bookmark.add(mark);
                ui.displayBookmark(bookmark);
            }else if(mark.getUrl().equalsIgnoreCase(search) ) {
                bookmark.add(mark);
                ui.displayBookmark(bookmark);
            }else if(mark.getTag().equalsIgnoreCase(search) ) {
                bookmark.add(mark);
                ui.displayBookmark(bookmark);
            }
        }
        
        
    }
    
    private void response(BookMarks bookmark) {
        Response response = new Response();
        if(bookmark == null) {
            ui.displayNoItemsFound();
        }else {
            response.setSuccess(true);
            response.setMessage("Error! no task found by that name");
            if(!response.isSuccess() ){
                ui.printLine(response.getMessage());
            }else {
                ui.printLine("Success!");
            }
        }
    }
    
    
}

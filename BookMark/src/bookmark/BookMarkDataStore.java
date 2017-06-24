/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmark;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kendrabooker
 */
public class BookMarkDataStore {
   
       private static final String DATA_FILE_PATH = "BookMarkData.txt";

    public List<BookMarks> all() {
        return read();
    }

    public Response add(BookMarks bookmark) {

        // validate the data
        if (bookmark.getUrl() == null || bookmark.getTitle().trim().length() == 0) {
            Response response = new Response();
            response.setMessage("Bookmark title and URL is required!");
            return response;
        }
        
        List<BookMarks> all = read();
        all.add(bookmark);
        return write(all);
        
    }

    public List<BookMarks> read() {
        List<BookMarks> result = new ArrayList<>();

        File file = new File(DATA_FILE_PATH);
        if (!file.exists()) {
            return result;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length == 5) {
                    BookMarks bookmark = new BookMarks();
                    bookmark.setId(Integer.parseInt(tokens[0]));
                    bookmark.setTitle(tokens[1]);
                    bookmark.setUrl(tokens[2]);
                    bookmark.setTag(tokens[3]);
                    bookmark.setCreateDate(LocalDate.parse(tokens[4]));
                    result.add(bookmark);
                }

            }
            reader.close();

        } catch (IOException ex) {
            System.out.printf("ERROR!: %s", ex.getMessage());
        }

        return result;
    }

    public Response write(List<BookMarks> bookmark) {

        Response result = new Response();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(DATA_FILE_PATH);
            for (BookMarks mark : bookmark) {
                writer.write(String.format(
                        "%s|%s|%s|%s|%s\n",
                        mark.getId(),
                        mark.getTitle(),
                        mark.getUrl(),
                        mark.getTag(),
                        mark.getCreateDate()));
            }
            writer.flush();
            result.setSuccess(true);
        } catch (FileNotFoundException ex) {
            result.setMessage(ex.getMessage());
        } finally {
            writer.close();
        }

        return result;
    }
    
    public BookMarks getBookMarkByTitle() {
        UI ui = new UI();
        String title = ui.getBookmarkTitle();
        BookMarks bookmark = null;
        for(BookMarks mark : all() ) {
            if(mark.getTitle().equalsIgnoreCase(title)) {
                bookmark = mark;
            }
        }
        return bookmark;
    }
    
    public BookMarks getBookmarkByUrl(String search) {
        BookMarks bookmark = null;
        for(BookMarks mark : all() ) {
            if(mark.getUrl().equalsIgnoreCase(search)) {
                bookmark = mark;
            }
        }
        return bookmark;
    }
    
    public BookMarks getBookmarkByTag(String search) {
        BookMarks bookmark = null;
        for(BookMarks mark : all() ) {
            if(mark.getTag().equalsIgnoreCase(search)) {
                bookmark = mark;
            }
        }
        return bookmark;
    }
    
    public void writeTodo(BookMarks bookmark) {
        List<BookMarks> mark = new ArrayList<>();
        mark.add(bookmark);
        write(mark);
    }
    
    
    
   public BookMarks deleteBookmark(String title) {
       List<BookMarks> mark = new ArrayList<>();
        BookMarks bookmark = null;
        for(BookMarks markTo : all() ) {
            if(markTo.getTitle().equalsIgnoreCase(title) ) {
                
                bookmark = markTo;
                
            }
            mark.add(markTo);
            mark.remove(bookmark);
            
        }
        write(mark);
        return bookmark;
   }

    
}

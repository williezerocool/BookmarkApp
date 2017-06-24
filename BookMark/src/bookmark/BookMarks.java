/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmark;

import java.time.LocalDate;
/**
 *
 * @author kendrabooker
 */
public class BookMarks {
    
    private String url, title, tag;
    private int id;
    private LocalDate createDate = LocalDate.now();
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate date) {
        this.createDate = date;
    }

   
    
}

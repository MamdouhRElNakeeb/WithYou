package com.ma33a.withyou.network.model;

/**
 * Created by khaled on 31/07/17.
 */

public class Book {
    String title ;
    String pages ;
    String fileName ;
    String fileLink ;

    public Book(String title, String pages, String fileName, String fileLink) {
        this.title = title;
        this.pages = pages;
        this.fileName = fileName;
        this.fileLink = fileLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}

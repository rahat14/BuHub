package com.metacodersbd.myapplication.PdfDownloaderSection;

public class modelForPdfUpload {
    String title , link , writer ;

    public modelForPdfUpload() {

    }

    public modelForPdfUpload(String title, String link, String writer) {
        this.title = title;
        this.link = link;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}

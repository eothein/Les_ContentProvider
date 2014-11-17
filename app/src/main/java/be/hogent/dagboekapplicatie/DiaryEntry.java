package be.hogent.dagboekapplicatie;

/**
 * Created by jbuy519 on 26/10/2014.
 */
public class DiaryEntry {

    private String title;
    private String content;
    private String recordedDate;

    public DiaryEntry(String title, String content, String recordedDate) {
        this.title = title;
        this.content = content;
        this.recordedDate = recordedDate;
    }

    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

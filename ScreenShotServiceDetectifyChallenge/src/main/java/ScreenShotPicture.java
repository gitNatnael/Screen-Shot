import com.sun.istack.internal.NotNull;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by natna on 1/9/2017.
 */
@Entity
public class ScreenShotPicture {
    @Id
    @GeneratedValue
    @NotNull
    private long fileID;

    @NotNull
    private String url;
    @Unique
    @NotNull
    private String fileName;
    private Date dateOfTakenShot;

    public ScreenShotPicture(String url, String fileName, Date dateOfTakenShot) {
        this.url = url;
        this.fileName = fileName;
        this.dateOfTakenShot = dateOfTakenShot;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateOfTakenShot() {
        return dateOfTakenShot;
    }

    public void setDateOfTakenShot(Date dateOfTakenShot) {
        this.dateOfTakenShot = dateOfTakenShot;
    }
}

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by natna on 1/9/2017.
 */
public class DatabaseManipulation {


    //save screen shot entity in to objectDB
    public void saveFile(ScreenShotPicture file) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/ScreenShotDB/file-name-address-Test.odb");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(file);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

    }


    //search image file by file name
    public ScreenShotPicture getScreenShotImageBYFileName(String fileName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/ScreenShotDB/file-name-address-Test.odb");
        EntityManager em = emf.createEntityManager();
        Query getFile = em.createQuery("SELECT p FROM ScreenShotPicture p  WHERE p.fileName = '" + fileName + "'", ScreenShotPicture.class);
        ScreenShotPicture result = (ScreenShotPicture) getFile.getSingleResult();
        return result;
    }


    //search image by url
    public ScreenShotPicture getScreenShotImageByURL(String url) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/ScreenShotDB/file-name-address-Test.odb");
        EntityManager em = emf.createEntityManager();
        Query getFile = em.createQuery("SELECT p FROM ScreenShotPicture p  WHERE p.url = '" + url + "'", ScreenShotPicture.class);
        ScreenShotPicture result = (ScreenShotPicture) getFile.getSingleResult();
        return result;
    }

    ///Delete image from objectDb and file pictures
    public void deleteImageFromDB(String searchByFileName) {
    }


    //Search Image list by file name, or date,....
    public List<ScreenShotPicture> getListOFScreenShotImage(String byDate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/ScreenShotDB/file-name-address-Test.odb");
        EntityManager em = emf.createEntityManager();
        TypedQuery<ScreenShotPicture> query = em.createQuery("SELECT p FROM ScreenShotPicture p  WHERE p.Date<byDate", ScreenShotPicture.class);
        List<ScreenShotPicture> result = query.getResultList();
        return result;
    }

}

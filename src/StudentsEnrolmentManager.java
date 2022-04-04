import java.io.IOException;

public interface StudentsEnrolmentManager {
     void Add(String sID);
     void Update();
     void Delete(String sID) throws IOException;
     void GetOne() throws IOException;
     void GetAll();
}

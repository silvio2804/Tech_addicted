package Model.tag;

import java.util.ArrayList;
import java.util.Optional;

public interface TagDao <E extends Exception>{

    ArrayList<Tag> fetchTags() throws E;

    Optional<Tag> fetchTag(int id)  throws E;

    boolean creaTag(int id, String tag) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaTag(int id) throws E;
}

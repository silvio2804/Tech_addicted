package Model.tag;

import Model.search.Paginator;

import java.util.ArrayList;
import java.util.Optional;

public interface TagDao <E extends Exception>{

    ArrayList<Tag> fetchTags(Paginator paginator) throws E;

    Optional<Tag> fetchTag(int id)  throws E;

    boolean createTag(int id, String tag) throws E ; //mettiamo boolean al posto di integer

    boolean deleteTag(int id) throws E;
}

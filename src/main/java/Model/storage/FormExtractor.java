package Model.storage;

import javax.servlet.http.HttpServletRequest;

public interface FormExtractor <E>{

    E extract(HttpServletRequest request,boolean update);
}

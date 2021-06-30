package Model.tag;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;

public class TagFormExtractor implements FormExtractor<Tag> {
    @Override
    public Tag extract(HttpServletRequest request, boolean update) {
        Tag tag = new Tag();
        if(update){
            tag.setTagId(Integer.parseInt(request.getParameter("tagId")));
        }
        tag.setWord(request.getParameter("word"));
        return tag;
    }
}

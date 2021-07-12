package Model.http;

import netscape.javascript.JSObject;
import org.json.JSONObject;

public interface JsonSerializable {

    JSONObject toJson();
}

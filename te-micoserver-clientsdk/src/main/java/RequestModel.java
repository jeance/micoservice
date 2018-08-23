import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxj4842 on 2018/7/12.
 */
public class RequestModel<T> {

    private HashMap<String,Object> PostData;

    private static Map<String, String> serviceIp = new HashMap<>();

    private T requestEntity;

    public T getRequestEntity() {
        return requestEntity;
    }

    public void setRequestEntity(T requestEntity) {
        this.requestEntity = requestEntity;
    }

    public HashMap<String,Object> getPostData() {
        return PostData;
    }

    public void setPostData(HashMap<String,Object> postData) {
        PostData = postData;
    }

    public static Map<String, String> serviceIp() {
        return serviceIp;
    }

    public static void serviceIp(Map<String, String> serviceIp) {
        serviceIp = serviceIp;
    }
}

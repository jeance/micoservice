

/**
 * Created by cxj4842 on 2018/7/12.
 */
public class ResponseModel<T> {
    private String responseData;

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    private T responseModel;

    public T getResponseModel() {
        return responseModel;
    }

    public void setResponseModel(T responseModel) {
        this.responseModel = responseModel;
    }



    public T getResponseModel(String json, Class<T> type) {
     //   T response = JsonUtils.jsonToModel(json, type);
     //   return response;
        return null;
    }
}

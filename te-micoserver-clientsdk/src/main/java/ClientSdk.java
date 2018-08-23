
import com.te.micoservice.common.definition.PublicConsts;
import com.te.micoservice.common.utils.HttpUtils;
import com.te.micoservice.common.utils.IpHelper;
import com.te.micoservice.model.Micoserviceregistry;
import com.te.micoservice.service.MicoSerRegistryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cxj4842 on 2018/7/12.
 */
@Component
public class ClientSdk {

    @Autowired
    MicoSerRegistryImpl micoSerRegistry;

    private HashMap<String, Object> serIpList;

    private String requestData;

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public <T> T httpProxy(String serName, String serMethod, Class<T> type) {
        String micoSerKey = serName.concat(serMethod);
        ConsistentHashing consistentHashing = null;
        if (!serIpList.containsKey(micoSerKey)) {
            return null;
        }
        consistentHashing = (ConsistentHashing) serIpList.get(micoSerKey);
        String consistenHashingKey= getDateTimeNow();
        Object ip= consistentHashing.getNode(consistenHashingKey);
        String requestUrl=String.format("%s/%s",ip,serName.concat("/").concat(serMethod));
        HttpUtils httpUtils=new HttpUtils(requestUrl);
        httpUtils.setSentData(requestData);
        Map<HttpUtils.HttpEnum, String> response=  httpUtils.sendRequest();
        return (T)response.get(HttpUtils.HttpEnum.VeryGood);
      //  return null;
    }

    public <T> void init(String serName, String serMethod, Class<T> type, Hashtable<String, Object> req) {
        List<Micoserviceregistry> model = getSerModels(serName, serMethod);
        List<String> ipList = getIpList(model);
        ConsistentHashing consistentHashing = new ConsistentHashing(PublicConsts.MicoSer.virtualNum, ipList);
        String micoSerKey = serName.concat(serMethod);
        serIpList.put(micoSerKey, consistentHashing);
    }

    private List<String> getIpList(List<Micoserviceregistry> modelList) {
      //  List<String> ips = modelList.stream().map(Micoserviceregistry::getMsrserviceip).collect(Collectors.toList());
        List<String> ips = modelList.stream().map( (a) -> {
       return   a.getMsrserviceip().concat(":"+a.getPort()); })
                .collect(Collectors.toList());


        return ips;
    }

    private List<Micoserviceregistry> getSerModels(String serName, String serMethod) {
        Micoserviceregistry micoserviceregistry = new Micoserviceregistry();
        micoserviceregistry.setMsrservicename(serName);
        micoserviceregistry.setMsrservicemethod(serMethod);
        List<Micoserviceregistry> modelList = micoSerRegistry.selectByPrimaryKey(micoserviceregistry);
        modelList = modelList.stream().sorted((a, b) -> {
            return a.getCreatedtime().compareTo(b.getCreatedtime());
        }).filter(u -> u.getMsrservicename().equals(serName)
                && u.getMsrservicemethod().equals(serMethod)).collect(Collectors.toList());
        return modelList;
    }

    private String getDateTimeNow() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String date = df.format(new Date());
        return date;
    }

}

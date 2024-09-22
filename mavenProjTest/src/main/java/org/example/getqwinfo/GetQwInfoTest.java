package org.example.getqwinfo;

import org.springframework.stereotype.Component;

//如果需要其他类可以使用Resource注入，则这里必需要加上Component/Service注解
//如果引用GetQwInfoTest对象的类没有使用Resouce注解，则该类里面的对象也没法使用Resouce注解
@Component
public class GetQwInfoTest {
    /*@Resource
    private TagClient tagClient; //可直接调用微信的接口

    public WxworkApiExchange<CorpTagListResponse> getQwTagListTest(String corpId, String agentId) {
        CorpTagListRequest request = CorpTagListRequest.builder().build();
        WxworkApiExchange<CorpTagListResponse> rsp =
                tagClient.getCorpTagList(request).forCorp(corpId).withAgentId(agentId).bySelfBuild();
        String getQwTags = String.format(
                "CorpTagListResponse-cropid:%s, agentid:%s, rsp size:%d, rsp info:%s",
                corpId, agentId, rsp.fetch().getTagGroup().size(), rsp);
        System.out.println(getQwTags);
        return rsp;
    }*/
}

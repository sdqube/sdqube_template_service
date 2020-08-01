package com.sdqube.template;

import com.sdqube.entities.AuthenticationPb;
import com.sdqube.entities.AuthenticationRpcGrpc;
import com.sdqube.entities.CommonsPb;
import com.sdqube.entities.TemplatePb;
import com.sdqube.service.grpc.GrpcServiceCall;
import com.sdqube.service.servicecall.AuthenticationGrpcServiceCall;
import com.sdqube.service.utils.SDQubeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by >Sagar Duwal<
 * Github: @sagarduwal
 * Date: 7/26/20 3:50 AM
 */
public class TemplateServiceTest {
//    private AuthenticationRpcGrpc.AuthenticationRpcBlockingStub authServiceCall;

    private GrpcServiceCall<AuthenticationRpcGrpc.AuthenticationRpcBlockingStub> authServiceCall;
    private TemplateService templateService;

    @Before
    public void setup() {
        authServiceCall = new AuthenticationGrpcServiceCall();

//        SqlDataSource provSqlDataSource = new SqlDataSource(ProvisionDbConnection.getInstance());
//        provRepo = new ProvisionRepo(provSqlDataSource);
//        authenticationService = new AuthenticationServiceImpl(provRepo);
        templateService = new TemplateServiceImpl(authServiceCall.getService());
    }

    @Test
    public void testing() {

        TemplatePb.TemplateBaseResponse templateBaseResponse = templateService.generateTemplate(
                AuthenticationPb.GAuthorization.newBuilder()
                        .setToken(SDQubeUtils.uuid())
                        .build(),
                TemplatePb.TemplateRequest.newBuilder()
                        .setTemplateName("Sagar")
                        .setTemplateData("Duwal")
                        .build(),
                CommonsPb.Debug.newBuilder()
                        .setDebugId(SDQubeUtils.uuid())
                        .build());
        System.out.println("templateBaseResponse = " + templateBaseResponse);
    }
}

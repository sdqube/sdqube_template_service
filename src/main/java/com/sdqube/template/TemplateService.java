package com.sdqube.template;

import com.sdqube.entities.AuthenticationPb;
import com.sdqube.entities.AuthenticationRpcGrpc;
import com.sdqube.entities.CommonsPb;
import com.sdqube.entities.TemplatePb;
import com.sdqube.service.exception.AuthException;
import com.sdqube.service.exception.PermissionException;
import com.sdqube.service.grpc.GrpcServiceAbstract;
import com.sdqube.service.logger.SDQubeLogger;

/**
 * Created by >Sagar Duwal<
 * Github: @sagarduwal
 * Date: 7/26/20 2:54 AM
 */
public abstract class TemplateService extends GrpcServiceAbstract<TemplatePb.TemplateBaseResponse> {
    private static final SDQubeLogger logger = SDQubeLogger.getLogger(TemplateService.class);

    public TemplateService(AuthenticationRpcGrpc.AuthenticationRpcBlockingStub authServiceCall) {
        super(authServiceCall);
    }

    abstract TemplatePb.TemplateBaseResponse generateTemplate(AuthenticationPb.GAuthorization authorization, TemplatePb.TemplateRequest templateRequest, CommonsPb.Debug debug);



    @Override
    public TemplatePb.TemplateBaseResponse error(CommonsPb.ErrorCode errorCode, String s) {
        return TemplatePb.TemplateBaseResponse.newBuilder()
                .setMsg("Error in Template Service ")
                .setError(true)
                .setSuccess(false)
                .build();
    }
}

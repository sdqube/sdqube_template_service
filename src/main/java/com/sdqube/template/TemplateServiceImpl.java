package com.sdqube.template;

import com.sdqube.entities.AuthenticationPb;
import com.sdqube.entities.AuthenticationRpcGrpc;
import com.sdqube.entities.CommonsPb;
import com.sdqube.entities.TemplatePb;
import com.sdqube.service.exception.AuthException;
import com.sdqube.service.exception.PermissionException;
import com.sdqube.service.logger.SDQubeLogger;

/**
 * Created by >Sagar Duwal<
 * Github: @sagarduwal
 * Date: 8/1/20 9:12 PM
 */
public class TemplateServiceImpl extends TemplateService {
    private static final SDQubeLogger logger = SDQubeLogger.getLogger(TemplateService.class);

    public TemplateServiceImpl(AuthenticationRpcGrpc.AuthenticationRpcBlockingStub authenticationServiceCall) {
        super(authenticationServiceCall);
    }

    @Override
    TemplatePb.TemplateBaseResponse generateTemplate(AuthenticationPb.GAuthorization authorization, TemplatePb.TemplateRequest templateRequest, CommonsPb.Debug debug) {
        try {
            AuthenticationPb.AuthResponse authorize = authorize(authorization, debug, "generate-template");
            AuthenticationPb.User user = authorize.getSession().getUser();
            logger.debug("User: {}", user);
            return TemplatePb.TemplateBaseResponse.newBuilder()
                    .setDebug(debug)
                    .setError(false)
                    .setSuccess(true)
                    .setTemplateName(templateRequest.getTemplateName())
                    .setTemplateData(templateRequest.getTemplateData())
                    .build();
        } catch (AuthException | PermissionException e) {
            e.printStackTrace();
        }
        return TemplatePb.TemplateBaseResponse.newBuilder()
                .setDebug(debug)
                .setError(true)
                .build();
    }
}

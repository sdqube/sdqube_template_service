package com.sdqube.template;

import com.sdqube.entities.TemplatePb;
import com.sdqube.entities.TemplateRpcGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Created by >Sagar Duwal<
 * Github: @sagarduwal
 * Date: 8/1/20 9:16 PM
 */
public class TemplateRpcImpl extends TemplateRpcGrpc.TemplateRpcImplBase {

    private final TemplateService templateService;

    public TemplateRpcImpl(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Override
    public void generateTemplate(TemplatePb.TemplateBaseRequest request, StreamObserver<TemplatePb.TemplateBaseResponse> responseObserver) {
        responseObserver.onNext(templateService.generateTemplate(request.getAuthorization(), request.getTemplateRequest(), request.getDebug()));
        responseObserver.onCompleted();
    }
}

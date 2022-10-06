package bsf.digital.bsffinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-06T18:37:37.996902+02:00[Africa/Cairo]")

@Controller
@RequestMapping("${openapi.bSFFinance.base-path:/api}")
public class TransferApiController implements TransferApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransferApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}

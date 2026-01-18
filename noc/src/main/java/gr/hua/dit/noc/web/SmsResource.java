package gr.hua.dit.noc.web;

import gr.hua.dit.noc.core.PhoneNumberService;
import gr.hua.dit.noc.core.SmsService;
import gr.hua.dit.noc.core.model.SendSmsRequest;
import gr.hua.dit.noc.core.model.SendSmsResult;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/sms", produces = MediaType.APPLICATION_JSON_VALUE)
public class SmsResource {

    private final SmsService smsService;
    private final PhoneNumberService phoneNumberService;

    public SmsResource(SmsService smsService, PhoneNumberService phoneNumberService) {
        this.smsService = smsService;
        this.phoneNumberService = phoneNumberService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SendSmsResult> sendSms(@RequestBody @Valid SendSmsRequest request) {
        var validation = phoneNumberService.validatePhoneNumber(request.e164());

        if (!validation.valid()) {
            return ResponseEntity.badRequest().body(new SendSmsResult(false));
        }

        SendSmsRequest formattedRequest = new SendSmsRequest(validation.e164(), request.content());

        return ResponseEntity.ok(this.smsService.send(formattedRequest));
    }
}
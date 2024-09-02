package com.nocountry.telemedicina.services.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationSMSImpl {

    @Value("${account.sid.sms}")
    private String ACCOUNT_SID;

    @Value("${auth.token.sms}")
    private String AUTH_TOKEN;

    @Value("${message.service.sid}")
    private String MESSAGING_SERVICE_SID;

    public void sendSMS(String toWhoNumber, String textMessage){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(toWhoNumber),
                MESSAGING_SERVICE_SID, textMessage).create();
        System.out.println(message.getSid());
    }
}

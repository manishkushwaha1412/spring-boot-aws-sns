package com.aws.sns.boot.config;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSSNSController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    @Value("${cloud.aws.end-point.uri}")
    private String uri;

    @GetMapping("subscribe/{email}")
    public String addSubscription(@PathVariable String email){
        SubscribeRequest subscribeRequest = new SubscribeRequest(uri, "email", email);
        amazonSNSClient.subscribe(subscribeRequest);
        return "Subscribed Successfully!!!, please check your email"+email;
    }

    @GetMapping("notifysubscriber")
    public String publishedMessageToAWSTopic(){
        PublishRequest publishRequest = new PublishRequest(uri, "Please subscribe today to get attractive offers", "Offers: subscribe today and get offers");
        amazonSNSClient.publish(publishRequest);
        return "Notification send successfully.....";
    }


}

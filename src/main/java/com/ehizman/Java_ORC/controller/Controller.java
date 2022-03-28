package com.ehizman.Java_ORC.controller;

import com.ehizman.Java_ORC.services.ObjectRecognitionService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping
public class Controller {
    @Autowired
    private ObjectRecognitionService objectRecognitionService;

    @RequestMapping(value = "/webhook", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public void reply(@Valid @NotNull @RequestBody MultiValueMap<String, String> userResponse){
        String imageUrl = null;
        String receiverPhoneNumber = userResponse.getOrDefault("To", null).get(0).trim();
        String senderPhoneNumber = userResponse.getOrDefault("From", null).get(0).trim();
        // if the user does not send in an image
        if (!userResponse.get("NumMedia").get(0).equals("1")){
            sendMessage("Please send in a picture of the image that you want to track\nPreferably, you can " +
                    "send a picture that has a label on it", senderPhoneNumber, receiverPhoneNumber);
            return;
        }
        // else if the user sends in an image, get the imageUrl and perform image analysis on the image.
        else{
            if (userResponse.get("NumMedia").get(0).equals("1")){
                try{
                    imageUrl = userResponse.get("MediaUrl0").get(0);
                } catch (Exception e){
                    log.info("Exception ---> {}", e.getMessage());
                    sendMessage("Please send in a image of an item", senderPhoneNumber, receiverPhoneNumber);
                }
                sendMessage("Image received successfully", senderPhoneNumber, receiverPhoneNumber);
            }
        }

        Map<String, Float> imageLabels = objectRecognitionService.extractLabelsFromImage(imageUrl);
        String textFromImage = objectRecognitionService.extractTextFromImage(imageUrl);
        Map<String, Object> responseToUser = new HashMap<>();
        responseToUser.put("Labels", imageLabels);
        responseToUser.put("Text in image", textFromImage);
        sendMessage(String.valueOf(responseToUser), senderPhoneNumber, receiverPhoneNumber);

    }

    //This method sends a message back to the user
    private void sendMessage(String messageToSend, String receiverPhoneNumber, String senderPhoneNumber) {
        Message.creator(
                        new PhoneNumber(receiverPhoneNumber),
                        new PhoneNumber(senderPhoneNumber), messageToSend)
                .create();
    }
}

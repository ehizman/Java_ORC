package com.ehizman.Java_ORC.services;

import java.util.Map;

public interface ObjectRecognitionService {
    Map<String, Float> extractLabelsFromImage(String imageURL);
    String extractTextFromImage(String imageURL);
}

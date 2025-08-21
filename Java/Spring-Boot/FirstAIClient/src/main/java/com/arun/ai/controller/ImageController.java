//package com.arun.ai.controller;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.model.ChatModel;
//import org.springframework.ai.image.ImageModel;
//import org.springframework.ai.image.ImageOptionsBuilder;
//import org.springframework.ai.image.ImagePrompt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.util.MimeTypeUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("image")
//public class ImageController {
//
//    @Autowired
//    ChatModel chatModel;
//
//    @Autowired
//    ImageModel imageModel;
//
//    @GetMapping("imageToText")
//    public String imageToText() {
//        return ChatClient.create(chatModel)
//                .prompt().user(userSpec ->
//                        userSpec.text("Explain me what you see in this image")
//                                .media(MimeTypeUtils.IMAGE_JPEG, new ClassPathResource("images/abc.jpg"))
//                ).call().content();
//    }
//
//    @GetMapping("/generateImage")
//    public String generateImage(@RequestParam String prompt) {
//        return imageModel.call(new ImagePrompt(prompt, ImageOptionsBuilder.builder()
//                .N(1).height(1024).width(1024).build())).getResult().getOutput().getUrl();
//    }
//
//}

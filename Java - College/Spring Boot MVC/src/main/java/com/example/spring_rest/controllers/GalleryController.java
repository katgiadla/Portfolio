package com.example.spring_rest.controllers;

import com.example.spring_rest.data.ToolsPicture;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@Component
@PropertySource("classpath:application.properties")
public class GalleryController implements InitializingBean{

    @Autowired
    private ToolsPicture toolsPicture;

    @Override
    public void afterPropertiesSet() throws IOException, URISyntaxException {
        toolsPicture.prepareImagesList();
    }

    @GetMapping("/")
    public String returnHome(){
        return "index";
    }

    @GetMapping(value="/gallery/pictures")
    @ResponseBody
    public String getAllPictures(){
        return toolsPicture.getAllPictures();
    }

    @GetMapping(value = "/gallery/picture/{index}")
    public void getSinglePicture(@PathVariable("index") int index, HttpServletResponse response) throws IOException, URISyntaxException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        toolsPicture.printSinglePicture(response,index);
    }

    @DeleteMapping(value = "/gallery/delete/{index}")
    @ResponseBody
    public String deleteSinglePicture(@PathVariable("index") int index, HttpServletResponse response){
        return toolsPicture.deletePicture(index);
    }

    @GetMapping(value = "/gallery/upload")
    public String uploadSinglePicture(){
      return "upload";//toolsPicture.sendSingleImage();
    }

    @PostMapping(value = "/gallery/doUpload")
    @ResponseBody
    public String uploadAnswer(@RequestParam("file") MultipartFile image) throws IOException {
        return toolsPicture.sendSingleImage(image);
    }
}

package com.tchorek.springbootextended.controllers;

import com.tchorek.springbootextended.data.ToolsPicture;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@Component
@PropertySource("classpath:application.properties")
public class GalleryController implements InitializingBean {

    @Autowired
    private ToolsPicture toolsPicture;

    @Override
    public void afterPropertiesSet() throws IOException, URISyntaxException {
        toolsPicture.prepareImagesList();
    }

    @GetMapping("/")
    public String returnHome() {
        return "index";
    }

    @GetMapping(value = "/gallery")
    public String getAllPictures(ModelMap modelMap, Model model) {
        model.addAttribute("images", toolsPicture.getImagesList());
        modelMap.put("images", toolsPicture.getImagesList());
        return "allImages";
    }

    @GetMapping(value = "/gallery/panel")
    public String galleryPanel(ModelMap modelMap, Model model) {
        //toolsPicture.deletePicture();
        model.addAttribute("login", false);
        modelMap.put("login", false);

        model.addAttribute("images", toolsPicture.getImagesList());
        modelMap.put("images", toolsPicture.getImagesList());
        return "panel";
    }

    @PostMapping(value = "/gallery/panel")
    public String deletePicture(ModelMap modelMap, Model model, @RequestParam(name = "image.name") String imageName) {
        toolsPicture.deletePicture(imageName);

        model.addAttribute("images", toolsPicture.getImagesList());
        modelMap.put("images", toolsPicture.getImagesList());

        if (!toolsPicture.isLogged()) {
            model.addAttribute("login", false);
            modelMap.put("login", false);
            return "panel";
        }
            model.addAttribute("login",true);
            modelMap.put("login",true);
            return "panelAdmin";

    }

    @GetMapping(value = "/gallery/picture/{index}")
    public String getSinglePicture(@PathVariable("index") int index, ModelMap modelMap, Model model) throws IOException, URISyntaxException {
        model.addAttribute("image", toolsPicture.getSingleImage(index));
        modelMap.put("image", toolsPicture.getSingleImage(index));
        return "singlePicture";
    }
}


//------------- TRASH BIN -------------

/*
    //@ResponseBody
    *//*toolsPicture.getAllPicturesJsonString()*//*
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
      return "upload";//toolsPicture.sendSingleImageJson();
    }

    @PostMapping(value = "/gallery/doUpload")
    @ResponseBody
    public String uploadImages(@RequestParam("file") MultipartFile image) throws IOException {
        return toolsPicture.sendSingleImageJson(image);
    }*/
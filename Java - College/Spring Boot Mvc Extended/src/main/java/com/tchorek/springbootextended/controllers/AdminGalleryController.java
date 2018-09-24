package com.tchorek.springbootextended.controllers;

import com.tchorek.springbootextended.data.Image;
import com.tchorek.springbootextended.data.ToolsPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@Component
@PropertySource("classpath:application.properties")
public class AdminGalleryController {

    @Autowired
    ToolsPicture toolsPicture;

    @Value("${Admin_login}")
    private String adminLogin;

    @Value("${Admin_password}")
    private String adminPassword;

    @PostMapping("/gallery/login")
    public String loginUser(@RequestParam(name = "adminLogin", required = true) String adminLoginInput, @RequestParam(name = "adminPassword", required = true) String adminPasswordInput, ModelMap modelMap, Model model) {
        model.addAttribute("images", toolsPicture.getImagesList());
        modelMap.put("images", toolsPicture.getImagesList());
        if( !this.adminLogin.equals(adminLoginInput) || !this.adminPassword.equals(adminPasswordInput))
        {
            toolsPicture.setLogged(false);
            model.addAttribute("login", false);
            modelMap.put("login", false);
            return "panel";
        }
        if (toolsPicture.isLogged() || this.adminLogin.equals(adminLoginInput) && this.adminPassword.equals(adminPasswordInput)) {
            toolsPicture.setLogged(true);
            model.addAttribute("login", true);
            modelMap.put("login", true);
            return "panelAdmin";
        }
        else {
            toolsPicture.setLogged(false);
            model.addAttribute("login", false);
            modelMap.put("login", false);
            return "panel";
        }
    }

    @PostMapping("/gallery/logout")
    public String logoutUser(ModelMap modelMap, Model model) {

        model.addAttribute("images", toolsPicture.getImagesList());
        modelMap.put("images", toolsPicture.getImagesList());
        model.addAttribute("login", false);
        modelMap.put("login", false);

        if (toolsPicture.isLogged())
            toolsPicture.setLogged(false);

        return "panel";
    }

    @PostMapping(value = "/gallery/upload")
    public String uploadImages(Model model, ModelMap modelMap, @RequestParam("files") MultipartFile[] images) throws IOException {

        toolsPicture.uploadImages(images);

        model.addAttribute("images",toolsPicture.getImagesList()); //updated ImagesList with new pictures
        modelMap.put("images",toolsPicture.getImagesList());

        if(!toolsPicture.isLogged()) {
            model.addAttribute("login", false);
            modelMap.put("login", false);
            return "panel";
        }
        model.addAttribute("login", true);
        modelMap.put("login", true);
        return "panelAdmin";
        }
}
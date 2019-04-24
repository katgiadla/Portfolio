package com.tchorek.springbootextended.data;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ToolsPicture {

    @Value("${relative_path}")
    private String RELATIVE_PATH;
    @Value("${absolute_path}")
    private String ABSOLUTE_PATH_FOLDER;

    private File tmpFile;
    private BufferedImage tmpImage;

    private int index = 0;
    private final String[] TYPES_OF_PHOTOS = {".jpg", ".png", ".gif", ".bmp",".jpeg"};

    private HashMap<Integer, Image> imagesCollection = new HashMap<>();
    private List<Image> imagesList = new ArrayList<>();

    private boolean logged = false;

    public List<Image> getImagesList() {
        return imagesList;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    private List<Path> getFileNamesFromFolder(String typeOfPhoto) throws IOException, URISyntaxException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Path configFilePath = Paths.get(classLoader.getResource(RELATIVE_PATH).toURI());

        return Files.walk(configFilePath)
                .filter(s -> s.toString().endsWith(typeOfPhoto))
                .map(Path::getFileName)
                .sorted()
                .collect(toList());
    }

    private List<Path> getNameFromFolder(int inputIndex) throws IOException, URISyntaxException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Path configFilePath = Paths.get(classLoader.getResource(RELATIVE_PATH).toURI());

        return Files.walk(configFilePath)
                .filter(s -> s.toString().contains(String.valueOf(inputIndex)))
                .map(Path::getFileName)
                .sorted()
                .collect(toList());
    }

    public String getAllPicturesJsonString() {
        return imagesCollection.toString();
    }


    public void prepareImagesList() throws IOException, URISyntaxException {

        for (String typeOfPhoto : TYPES_OF_PHOTOS) {
            List<Path> atrackFileNames = getFileNamesFromFolder(typeOfPhoto);

            for (Path singleFileName : atrackFileNames) {
                tmpFile = new File(ABSOLUTE_PATH_FOLDER + singleFileName);
                tmpImage = ImageIO.read(tmpFile);

                BasicFileAttributes myFileAttributes = Files.readAttributes(tmpFile.toPath(), BasicFileAttributes.class);

                Image localImage = new Image(tmpFile.getName(), myFileAttributes.size(),
                        tmpImage.getWidth() + "x" + tmpImage.getHeight(), myFileAttributes.creationTime());

                imagesCollection.put(index++,localImage );
                imagesList.add(localImage);
            }
        }
    }

    public void deletePicture(int index) {
        if (imagesCollection.containsKey(index))
            imagesCollection.remove(index);

    }

    public void deletePicture(String imageName) {
            this.deleteKeyFromCollection(imageName);
    }

    public void deleteKeyFromCollection(String key){
        for(Integer index : imagesCollection.keySet())
        {
            if(imagesCollection.get(index).getName().equals(key)) {

                imagesList.remove(imagesCollection.get(index));
                imagesCollection.remove(index);

                break;
            }
        }
    }

    public Image getSingleImage(int index) throws IOException, URISyntaxException {
        tmpFile = getNameFromFolder(index).isEmpty()? new File(ABSOLUTE_PATH_FOLDER+"error.jpg"): new File(ABSOLUTE_PATH_FOLDER+getNameFromFolder(index).get(0));
        tmpImage = ImageIO.read(tmpFile);

        BasicFileAttributes fileAttributes = Files.readAttributes(tmpFile.toPath(),BasicFileAttributes.class);
        return new Image(tmpFile.getName(), fileAttributes.size(),
                tmpImage.getWidth() + "x" + tmpImage.getHeight(), fileAttributes.creationTime());
    }



    public void uploadImages(MultipartFile[] listOfInputImages) throws IOException {
        if(listOfInputImages.length == 0)
            return;

        for(MultipartFile inputImage : listOfInputImages)
        {
            if(new File(ABSOLUTE_PATH_FOLDER+inputImage.getOriginalFilename()).exists())  //no duplicates
                continue;

            //create image file in images folder
            Files.write(Paths.get(ABSOLUTE_PATH_FOLDER + inputImage.getOriginalFilename()), inputImage.getBytes());


            //create new file, then image and get all necessary parameters
            tmpFile = new File(ABSOLUTE_PATH_FOLDER + inputImage.getOriginalFilename());
            tmpImage = ImageIO.read(tmpFile);

            BasicFileAttributes myFileAttributes = Files.readAttributes(tmpFile.toPath(), BasicFileAttributes.class);

            Image localImage = new Image(tmpFile.getName(), myFileAttributes.size(),
                    tmpImage.getWidth() + "x" + tmpImage.getHeight(), myFileAttributes.creationTime());

            imagesCollection.put(index++,localImage ); //add new image file to list that is later in controller
            imagesList.add(localImage);
        }
    }
}
package com.example.spring_rest.data;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
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
    private final String[] TYPES_OF_PHOTOS = {".jpg", ".png", ".gif", ".bmp"};
    private HashMap<Integer, Image> imagesList = new HashMap<>();

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

    public String getAllPictures() {
        return imagesList.toString();
    }

    public void prepareImagesList() throws IOException, URISyntaxException {


        for (String typeOfPhoto : TYPES_OF_PHOTOS) {
            List<Path> atrackFileNames = getFileNamesFromFolder(typeOfPhoto);

            for (Path singleFileName : atrackFileNames) {
                tmpFile = new File(ABSOLUTE_PATH_FOLDER + singleFileName);
                tmpImage = ImageIO.read(tmpFile);

                BasicFileAttributes myFileAttributes = Files.readAttributes(tmpFile.toPath(), BasicFileAttributes.class);
                imagesList.put(index++, new Image(tmpFile.getName(), myFileAttributes.size(),
                        tmpImage.getWidth() + "x" + tmpImage.getHeight(), myFileAttributes.creationTime()));
            }
        }
    }

    public String deletePicture(int index) {
        if (imagesList.containsKey(index)) {
            imagesList.remove(index);
            return new JSONObject().put("result", true).toString();
        } else
            return new JSONObject().put("result", false).toString();
    }

    public void printSinglePicture(HttpServletResponse response, int index) throws IOException, URISyntaxException {
        List<Path> listOfFilesFromFolder = getNameFromFolder(index);
        Path filePath;
        if (listOfFilesFromFolder.isEmpty())
            IOUtils.copy(new ClassPathResource("static/images/error.jpg").getInputStream(), response.getOutputStream());
        else {
            filePath = listOfFilesFromFolder.get(0);
            /*ClassPathResource imgFile = new ClassPathResource( RELATIVE_PATH+filePath).getInputStream();
             * Both ClassPathResource and InputStream works  */
            IOUtils.copy(new FileInputStream(ABSOLUTE_PATH_FOLDER + filePath), response.getOutputStream());
        }
    }

    public String sendSingleImage(MultipartFile inputImage) throws IOException {
        if (!inputImage.isEmpty()) {
            String fileName = inputImage.getOriginalFilename();

            if (new File(ABSOLUTE_PATH_FOLDER + fileName).exists())
                return new JSONObject().put("result - exist ", false).toString();

            //Files.copy(inputStream,, StandardCopyOption.REPLACE_EXISTING);
            Files.write(Paths.get(ABSOLUTE_PATH_FOLDER + fileName), inputImage.getBytes());

            return new JSONObject().put("result - uploaded ", true).toString();
        } else
            return new JSONObject().put("result - no file", false).toString();

    }
}

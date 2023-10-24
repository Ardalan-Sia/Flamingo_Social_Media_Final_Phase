package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.User;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class UserDB implements DBSet<User> {
    String USERS_DIRECTORY = "src\\main\\resources\\Users";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public User get(int id) {
        File dir = new File(USERS_DIRECTORY);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            for (File child : UserDirectoryListing) {
                if (FilenameUtils.removeExtension(child.getName()).equals(String.valueOf(id))) {
                    try {

                        User user = objectMapper.readValue(child, new TypeReference<User>() {
                        });
                        return user;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public LinkedList<User> all() {
        LinkedList<User> all = new LinkedList<>();
        File dir = new File(USERS_DIRECTORY);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            for (File child : UserDirectoryListing) {
                try {

                    User user = objectMapper.readValue(child, new TypeReference<User>() {
                    });
                    all.add(user);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        all.sort(Comparator.comparingInt(User::getId));
        return all;
    }

    @Override
    public void add(User user) {
        if (all().isEmpty())
            user.setId(0);
        else{
            user.setId(all().getLast().getId()+1);
        }

        try {

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(USERS_DIRECTORY + "/" + user.getId() + ".json"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void update(User user) {
        try {

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(USERS_DIRECTORY + "/" + user.getId() + ".json"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPhoto(User user, BufferedImage image) {


        int width = 963;    //width of the image
        int height = 640;   //height of the image

        try
        {
            // Output file path
            File output_file = new File("src\\main\\resources\\ProfilePhotos\\"+user.getId()+".png");


            // Writing to file taking type and path as
            ImageIO.write(image, "png", output_file);
            user.setProfilePhotoPath(output_file.getPath());
            update(user);
            image.flush();
            System.out.println("Writing complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }
    }


}

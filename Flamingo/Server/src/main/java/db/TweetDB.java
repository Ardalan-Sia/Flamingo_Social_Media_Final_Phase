package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Tweet;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

import static models.Commons.TWEETS_DIRECTORY;

public class TweetDB implements DBSet<Tweet>  {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Tweet get(int id) {
        File dir = new File(TWEETS_DIRECTORY+"\\"+id);
        if (dir.exists()){
            try {
                Tweet tweet = objectMapper.readValue(new File(dir.getPath()+"\\tweet.json"), new TypeReference<Tweet>() {
                });
                return tweet;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Tweet> all() {
        LinkedList<Tweet> all = new LinkedList<>();
        File dir = new File(TWEETS_DIRECTORY);
        File[] TweetDirectoryListing = dir.listFiles();
        if (TweetDirectoryListing != null) {
            for (File child : TweetDirectoryListing) {
                try {
                    File file = new File(child.getPath()+"\\tweet.json");
                    Tweet tweet = objectMapper.readValue(file, new TypeReference<Tweet>() {
                    });
                    all.add(tweet);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        all.sort(Comparator.comparingInt(Tweet::getId));
        return all;
    }

    @Override
    public void add(Tweet tweet) {
        if (all().isEmpty())
            tweet.setId(0);
        else tweet.setId(all().getLast().getId()+1);


        File file = new File("src\\main\\resources\\Tweets\\"+tweet.getId());
        file.mkdir();

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file.getPath()+"\\"+"tweet.json"),tweet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Tweet tweet) {
        File file = new File(TWEETS_DIRECTORY+"\\"+tweet.getId());
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tweet tweet) {
        File file = new File("src\\main\\resources\\Tweets\\"+tweet.getId());

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file.getPath()+"\\"+"tweet.json"),tweet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPhoto(Tweet tweet, BufferedImage image) {
        int width = 963;    //width of the image
        int height = 640;   //height of the image

        try
        {
            // Output file path
            File output_file = new File(TWEETS_DIRECTORY+"\\"+tweet.getId()+"\\tweet.png");



            // Writing to file taking type and path as
            ImageIO.write(image, "png", output_file);
            tweet.setPhotoPath(output_file.getPath());
            update(tweet);
            image.flush();
            System.out.println("Writing complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }

    }


}

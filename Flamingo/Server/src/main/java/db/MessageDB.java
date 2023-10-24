package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Message;
import models.Commons;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class MessageDB implements DBSet<Message>, Commons {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message get(int id) {
        File dir = new File(MESSAGES_DIRECTORY + "\\" + id);
        if (dir.exists()) {
            try {
                Message message = objectMapper.readValue(new File(dir.getPath() + "\\message.json"), new TypeReference<Message>() {
                });
                return message;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Message> all() {
        LinkedList<Message> all = new LinkedList<>();
        File dir = new File(MESSAGES_DIRECTORY);
        File[] MessageDirectoryListing = dir.listFiles();
        if (MessageDirectoryListing != null) {
            for (File child : MessageDirectoryListing) {
                try {
                    File file = new File(child.getPath() + "\\message.json");
                    Message message = objectMapper.readValue(file, new TypeReference<Message>() {
                    });
                    all.add(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        all.sort(Comparator.comparingInt(Message::getId));
        return all;
    }

    @Override
    public void add(Message message) {
        System.out.println("valaasd");
        if (all().isEmpty())
            message.setId(0);
        else {
            message.setId(all().getLast().getId()+ 1);
        }

        File file = new File("src\\main\\resources\\Messages\\" + message.getId());
        file.mkdir();

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file.getPath() + "\\" + "message.json"), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Message message) {
        File file = new File(MESSAGES_DIRECTORY + "\\" + message.getId());
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        File file = new File("src\\main\\resources\\Messages\\" + message.getId());

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file.getPath() + "\\" + "message.json"), message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setPhoto(Message message, BufferedImage image) {

    }
}

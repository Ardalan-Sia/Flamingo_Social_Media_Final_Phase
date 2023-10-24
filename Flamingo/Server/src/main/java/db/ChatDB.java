package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Chat;
import models.Commons;
import org.apache.commons.io.FileUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class ChatDB implements DBSet<Chat> , Commons {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Chat get(int id) {
        File dir = new File(CHATS_DIRECTORY+"\\"+id);
        if (dir.exists()){
            try {
                Chat chat = objectMapper.readValue(new File(dir.getPath()+"\\chat.json"), new TypeReference<Chat>() {
                });
                return chat;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Chat> all() {
        LinkedList<Chat> all = new LinkedList<>();
        File dir = new File(CHATS_DIRECTORY);
        File[] chatDirectoryListing = dir.listFiles();
        if (chatDirectoryListing != null) {
            for (File child : chatDirectoryListing) {
                try {
                    File file = new File(child.getPath()+"\\chat.json");
                    Chat chat = objectMapper.readValue(file, new TypeReference<Chat>() {
                    });
                    all.add(chat);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        all.sort(Comparator.comparingInt(Chat::getId));
        return all;
    }

    @Override
    public void add(Chat chat) {
        if (all().isEmpty())
            chat.setId(0);
        else chat.setId(all().getLast().getId()+1);
//        Controller.owner.getProfile().getChats().add(chat.getId());

//        User temp = new Context().Users.get(chat.getOther(Controller.owner.getId()));
//        temp.getProfile().getChats().add(chat.getId());
//        new Context().Users.update(temp);

        File file = new File("src\\main\\resources\\Chats\\"+chat.getId());
        file.mkdir();

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file.getPath()+"\\"+"chat.json"),chat);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Chat chat) {
        File file = new File(CHATS_DIRECTORY+"\\"+chat.getId());
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Controller.owner.getProfile().getChats().remove(chat.getId());

    }

    @Override
    public void update(Chat chat) {
        File file = new File("src\\main\\resources\\Chats\\"+chat.getId());

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new     FileWriter(file.getPath()+"\\"+"chat.json"),chat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPhoto(Chat chat, BufferedImage image) {

    }

}

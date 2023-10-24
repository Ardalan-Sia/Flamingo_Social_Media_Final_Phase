package db;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Commons;
import org.apache.commons.io.FileUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class GroupDB implements DBSet<Group> , Commons {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Group get(int id) {
        File dir = new File(GROUP_DIRECTORY+"\\"+id);
        if (dir.exists()){
            try {
                Group group = objectMapper.readValue(new File(dir.getPath()+"\\group.json"), new TypeReference<Group>() {
                });
                return group;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public LinkedList<Group> all() {
        LinkedList<Group> all = new LinkedList<>();
        File dir = new File(GROUP_DIRECTORY);
        File[] groupDirectoryListing = dir.listFiles();
        if (groupDirectoryListing != null) {
            for (File child : groupDirectoryListing) {
                try {
                    File file = new File(child.getPath()+"\\group.json");
                    Group group = objectMapper.readValue(file, new TypeReference<Group>() {
                    });
                    all.add(group);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        all.sort(Comparator.comparingInt(Group::getId));
        return all;
    }

    @Override
    public void add(Group group) {
        if (all().isEmpty())
            group.setId(0);
        else group.setId(all().getLast().getId()+1);
//        Controller.owner.getProfile().getGroups().add(group.getId());

        File file = new File("src\\main\\resources\\Groups\\"+group.getId());
        file.mkdir();

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file.getPath()+"\\"+"group.json"),group);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Group group) {
        File file = new File(GROUP_DIRECTORY+"\\"+group.getId());
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Controller.owner.getProfile().getGroups().remove(group.getId());

    }

    @Override
    public void update(Group group) {
        File file = new File("src\\main\\resources\\Groups\\"+group.getId());

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new     FileWriter(file.getPath()+"\\"+"group.json"),group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPhoto(Group group, BufferedImage image) {

    }


}

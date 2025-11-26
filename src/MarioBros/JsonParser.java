package MarioBros;

import Doctrina.CollidableRepository;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;


import java.io.File;
import java.io.IOException;


public class JsonParser {
    private CollidableRepository collidableRepository;
    private ObjectMapper mapper;
    private final File JSON_FILE = new File("/Users/chab/Library/CloudStorage/OneDrive-CegepdeSorel-Tracy/Technique_informatique/Java_project/POO_2/TP_Final_POO2/resources/json/World_1-1.json");
    private JsonNode jsonObj;
    private int chunks[];

    public JsonParser() {
        this.mapper = new ObjectMapper();
    }

    public void loadFile() throws IOException {
        jsonObj = mapper.readTree(JSON_FILE);
        System.out.println(jsonObj.toString());
    }

    public void loadFile(String filePath) throws IOException {
        jsonObj = mapper.readTree(new File(filePath));
        System.out.println(jsonObj.toString());
    }

    public JsonNode getJsonNode(String filePath) throws IOException {
        return mapper.readTree(new File(filePath));
    }


    private void initiateNewEntity() {
        Brick b = new Brick(1,1);
    }


}


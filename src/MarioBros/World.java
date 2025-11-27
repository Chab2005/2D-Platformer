package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

public class World extends StaticEntity {

    private JsonParser jsonParser;
    private String filePath = "resources/json/World_1-1.json";
    private String mapPath = "images/tilesheet/tilesheet.png";
    private Color backgroundColor;
    private Image background;
    private JsonNode jsonObj;
    private ObjectMapper objectMapper;
    private CollidableRepository collidableRepository;
    private Renderer renderer;
    private Layer layers;

    public World() {
        layers = new Layer();
        jsonParser = new JsonParser();
        objectMapper = new ObjectMapper();
        collidableRepository = collidableRepository.getInstance();
        x = 0;
        y = 0;
    }

    public void load() {

        loadMap();

        // This is temporary ↓↓↓
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(mapPath)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackgroundColor();
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background,x,y);
    }

    private void loadMap() {
        initMap();
        setMap();
        loadEntities();
    }

    private void loadEntities() {
        initEntites();
        System.out.println(jsonObj.toString());
    }

    private void initEntites(){
        SpriteLoader spriteLoader = new SpriteLoader();
        for (Chunks chunk : layers.getChunks()) {
            int arrayChunk[] =  chunk.getData();
            for(int i = 0; i < arrayChunk.length; i++){

                    Brick brick = new Brick(chunk.getX()*16, chunk.getY()*16, spriteLoader.loadSpriteSheet());
                    RenderingRepository.getInstance().registerEntities(brick);


            }
        }
    }

    private void initMap() {
        try {
            jsonObj = jsonParser.getJsonNode(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setMap() {

        JsonNode layerArrayNode =  jsonObj.get("layers");
        try {
            for (JsonNode layerNode : layerArrayNode) {
                Chunks chunk = objectMapper.treeToValue(layerNode, Chunks.class);
                layers.add(chunk);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private void setBackgroundColor() {
        String colorCode = jsonObj.get("backgroundcolor").asText();
        backgroundColor = Color.decode(colorCode);
        RenderingEngine.getInstance().setBackgroundColor(backgroundColor);
    }
}

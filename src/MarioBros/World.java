package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World extends StaticEntity {

    private JsonParser jsonParser;
    private static String JSON_FILE_PATH = "resources/json/TEMP4BG.json";
    private static String MAP_PATH = "images/tilesheet/tilesheet.png";
    private Color backgroundColor;
    private Image background;
    private JsonNode jsonObj;
    private ObjectMapper objectMapper;
    private CollidableRepository collidableRepository;
    private Renderer renderer;
    private Layer layers;
    private BlockTypeService blockTypeService;


    public World() {
        blockTypeService = new BlockTypeService();

        layers = new Layer();
        jsonParser = new JsonParser();
        objectMapper = new ObjectMapper();
        collidableRepository = collidableRepository.getInstance();
        x = 0;
        y = 0;
    }

    public void load() {

        loadMap();
        setBackgroundColor();
    }

    public void draw(Canvas canvas) {
        //canvas.drawRectangle(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE, Color.CYAN);
        //canvas.drawImage(background,x,y);
    }

    private void loadMap() {
        initMap();
        setMap();
        loadEntities();
    }

    private void loadEntities() {
        initEntites();
    }

    private void initEntites(){
        SpriteLoader spriteLoader = new SpriteLoader();
        BufferedImage spriteSheet = spriteLoader.loadSpriteSheet(MAP_PATH);

        for (Chunks chunk : layers.getChunks()) {
            int arrayChunk[] =  chunk.getData();
            int rowWidth = chunk.getWidth(); // 30
            int colHeight = chunk.getHeight(); // 300
            System.out.println(rowWidth + " " + colHeight);

            int x = 0;
            int y = 0;
            for (int i = 0; i < arrayChunk.length; i++) {
                x++;
                if(i%300 == 0) {
                    x = 0;
                    y++;
                }

                if (!blockTypeService.isAir(arrayChunk[i])) {
                    System.out.println(arrayChunk[i]);
                    Brick brick = new Brick(
                            getRightBlockPosition(x),
                            getRightBlockPosition(y),
                            getBrickSprite(spriteSheet,arrayChunk[i])
                    );

                    if (!blockTypeService.isDecoration(arrayChunk[i])) {
                        collidableRepository.registerEntity(brick);
                    }

                    RenderingRepository.getInstance().registerEntities(brick);
                }
            }


        }
    }


    private int getRightBlockPosition(int position) {
        return Math.abs(position * 16);
    }

    private Image getBrickSprite(BufferedImage spriteSheet,int index){
        int positionX = blockTypeService.getPosX(index);

        int positionY = blockTypeService.getPosY(index);

        return spriteSheet.getSubimage(positionX,positionY,16,16);
    }

    private void initMap() {
        try {
            jsonObj = jsonParser.getJsonNode(JSON_FILE_PATH);
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

    public void setBackgroundColor() {
        String colorCode = jsonObj.get("backgroundcolor").asText();
        backgroundColor = Color.decode(colorCode);
        RenderingEngine.getInstance().setBackgroundColor(backgroundColor);
    }
}

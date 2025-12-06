package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World extends StaticEntity {

    private JsonParser jsonParser;
    private static String JSON_FILE_PATH = "resources/json/TEMP4BG.json";
    private static String MAP_PATH = "images/tilesheet/tilesheet.png";
    private Color backgroundColor;

    private BackGround backGround;
    private JsonNode jsonObj;
    private ObjectMapper objectMapper;
    private CollidableRepository collidableRepository;
    //private Renderer renderer;
    private Layer layers;
    private BlockTypeService blockTypeService;
    private BufferedImage spriteSheet;


    public World() {
        blockTypeService = new BlockTypeService();
        backGround = new BackGround();
        layers = new Layer();
        jsonParser = new JsonParser();
        objectMapper = new ObjectMapper();
        collidableRepository = collidableRepository.getInstance();
    }

    public void load() {
        loadMap();
        setBackgroundColor();
    }

    public void draw(Canvas canvas) {


    }

    private void loadMap() {
        spriteSheet = loadSpriteSheet();
        initMap();
        setMap();
        loadEntities();
    }

    private void loadEntities() {
        initEntites();
    }

    private void initEntites(){
        for (Chunks chunk : layers.getChunks()) {
            int arrayChunk[] =  chunk.getData();
            initChunk(chunk, arrayChunk);
        }
    }

    private void initChunk(Chunks chunk, int[] arrayChunk) {
        int positionX = 0;
        int positionY = 0;
        for (int i = 0; i < arrayChunk.length; i++) {
            positionX++;
            if(isNextLineChunk(i, chunk.getHeight())) {
                positionX = 0;
                positionY++;
            }

            if (!blockTypeService.isAir(arrayChunk[i])) {
                initEntity(arrayChunk[i], positionX, positionY);
            }
        }
    }


    private boolean isNextLineChunk(int index,int widthLine) {
        return index % widthLine == 0;
    }

    private BufferedImage loadSpriteSheet() {
        SpriteLoader spriteLoader = new SpriteLoader();
        return spriteLoader.loadSpriteSheet(MAP_PATH);
    }

    private void initEntity(int index, int positionX, int positionY) {

        Brick brick = new Brick(
                getRightBlockPosition(positionX),
                getRightBlockPosition(positionY),
                getBrickSprite(spriteSheet,index)
        );

        if (!blockTypeService.isDecoration(index)) {
            collidableRepository.registerEntity(brick);
        }

        RenderingRepository.getInstance().registerEntities(brick);
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
        JsonNode layerArrayNode = jsonObj.get("layers");
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

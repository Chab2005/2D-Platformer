package MarioBros;

import Doctrina.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    private static final int POSITION_ADJUST = 392;
    private static final int SPRITE_DIMENSION = 32;
    private JsonParser jsonParser;
    private static String JSON_FILE_PATH = "resources/json/TEMP4BG.json";
    private static String MAP_PATH = "images/tilesheet/tilesheet_scaled_2x_pngcrushed.png";
    private Color backgroundColor;
    private JsonNode jsonObj;
    private ObjectMapper objectMapper;
    private CollidableRepository collidableRepository;
    private Layer layers;
    private BlockTypeService blockTypeService;
    private BufferedImage spriteSheet;


    public World() {
        blockTypeService = new BlockTypeService();
        layers = new Layer();
        jsonParser = new  JsonParser();
        objectMapper = new ObjectMapper();
        collidableRepository = collidableRepository.getInstance();
    }

    public void load() {
        loadMap();
        setBackgroundColor();
    }

    private void loadMap() {
        spriteSheet = loadSpriteSheet();
        initMap();
        setMap();
        initBlocks();
    }

    private void initBlocks(){
        for (Chunk chunk : layers.getChunks()) {
            int arrayChunk[] =  chunk.getData();
            initChunk(chunk, arrayChunk);
        }
    }

    private void initChunk(Chunk chunk, int[] arrayChunk) {
        int positionX = 0;
        int positionY = 0;
        for (int i = 0; i < arrayChunk.length; i++) {
            positionX++;

            if(isNextLineChunk(i, chunk.getHeight())) {
                positionX = 0;
                positionY++;
            }

            if (!blockTypeService.isAir(arrayChunk[i])) {
                initBlock(arrayChunk[i], positionX, positionY);
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

    private void initBlock(int index, int positionX, int positionY) {

        Brick brick = new Brick(
                getRightBlockPositionX(positionX),
                getRightBlockPositionY(positionY),
                getBrickSprite(spriteSheet,index)
        );

        if (!blockTypeService.isDecoration(index)) {
            collidableRepository.registerEntity(brick);
        }

        RenderingRepository.getInstance().registerEntities(brick);
    }

    private int getRightBlockPositionX(int position) {
        return Math.abs(position)*SPRITE_DIMENSION;
    }
    private int getRightBlockPositionY(int position) {
        return (Math.abs(position)*SPRITE_DIMENSION)- POSITION_ADJUST;
    }

    private Image getBrickSprite(BufferedImage spriteSheet,int index){
        int positionX = blockTypeService.getPosX(index);
        int positionY = blockTypeService.getPosY(index);
        return spriteSheet.getSubimage(positionX,positionY,SPRITE_DIMENSION,SPRITE_DIMENSION);
    }

    private void initMap() {
        jsonObj = jsonParser.getJsonObj(JSON_FILE_PATH);
    }

    private void setMap() {
        JsonNode layerArrayNode = jsonObj.get("layers");
        try {
            for (JsonNode layerNode : layerArrayNode) {
                Chunk chunk = objectMapper.treeToValue(layerNode, Chunk.class);
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

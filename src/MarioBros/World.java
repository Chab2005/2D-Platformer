package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

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

        /*try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(mapPath)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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

                if (arrayChunk[i] != 0) {
                    Brick brick = new Brick(
                            Math.abs(x*16),
                            Math.abs(y)*16,
                            spriteSheet.getSubimage(0,0,16,16)
                    );
                    collidableRepository.registerEntity(brick);
                    RenderingRepository.getInstance().registerEntities(brick);
                }
            }


        }
    }


    private Image getBrickSprite(BufferedImage spriteSheet,int posistionSprite){
        int temp = posistionSprite;
        int ammountTileWidth = spriteSheet.getWidth()/16;
        int ammountTileHeight = spriteSheet.getHeight()/16;



        int SearchTileRow = posistionSprite/ammountTileWidth;
        int searchTileCol =0;
        while (temp >= ammountTileHeight) {
            temp -= ammountTileHeight;
        }







        return spriteSheet.getSubimage(temp*16,SearchTileRow*16,16,16);
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

package MarioBros;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.StaticEntity;
import com.fasterxml.jackson.databind.JsonNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World extends StaticEntity {

    private JsonParser jsonParser;
    private String filePath = "resources/json/World_1-1.json";
    private String mapPath = "images/tilesheet/tilesheet.png";
    private Image background;
    private JsonNode jsonObj;
    private CollidableRepository collidableRepository;

    public World() {
        jsonParser = new JsonParser();
        collidableRepository = collidableRepository.getInstance();
        x = 0;
        y = 0;
    }

    public void load() {

        loadMap();
        loadEntities();
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(mapPath)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background,x,y);
    }


    private void loadMap() {
        try {
            jsonObj = jsonParser.getJsonNode(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEntities() {
        System.out.println(jsonObj.toString());
    }

}

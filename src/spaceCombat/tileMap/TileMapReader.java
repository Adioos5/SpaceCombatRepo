package spaceCombat.tileMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TileMapReader {

    private static final String GAME_MAP = "tile_map.txt";
    private static final String MENU_MAP = "menu.txt";
    private int type;

    public TileMapReader(int type) {
        this.type = type;
    }

    public int[][] readMap() {
        List<String> lines = readFile();
        return convertListToArray(lines);
    }

    private List<String> readFile() {
        List<String> lines = new ArrayList<>();
        URL mapUrl = null;
        if (type == 1) {
            mapUrl = ClassLoader.getSystemResource(MENU_MAP);
        }
        if (type == 2) {
            mapUrl = ClassLoader.getSystemResource(GAME_MAP);

        }

        try {
            lines = Files.readAllLines(Paths.get(mapUrl.toURI()));
        } catch (IOException e) {
            System.err.println(e);
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        return lines;
    }

    private int[][] convertListToArray(List<String> lines) {
        int maxSize = getMaxSize(lines);
        int[][] map = new int[maxSize][lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            char[] charArray = lines.get(i).toCharArray();

            for (int k = 0; k < maxSize; k++) {
                if (k >= charArray.length) {
                    map[k][i] = 6;
                } else {
                    map[k][i] = Character.getNumericValue(charArray[k]);
                }
            }
        }
        return map;
    }

    private int getMaxSize(List<String> lines) {
        int maxSize = 0;

        for (String line : lines) {
            if (line.length() > maxSize) {
                maxSize = line.length();
            }
        }
        return maxSize;
    }
}

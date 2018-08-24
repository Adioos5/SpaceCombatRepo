package spaceCombat.tileMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TileMapReader {

    private static final String MARS_MAP = "tile_map.txt";
    private static final String MOON_MAP = "moon.txt";
    private static final String MENU_MAP = "menu.txt";
    private static final String GREEN_MAP = "greenPlanet.txt";
    private static final String SATURN_MAP = "saturn.txt";

    
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
        InputStream mapUrl = null;
        if (type == 1) {
            mapUrl = ClassLoader.getSystemResourceAsStream(MENU_MAP);
        }
        if (type == 2) {
            mapUrl = ClassLoader.getSystemResourceAsStream(MARS_MAP);

        }
        
        if (type == 3) {
            mapUrl = ClassLoader.getSystemResourceAsStream(MOON_MAP);

        }
        if (type == 4) {
            mapUrl = ClassLoader.getSystemResourceAsStream(GREEN_MAP);

        }
        if (type == 5) {
            mapUrl = ClassLoader.getSystemResourceAsStream(SATURN_MAP);

        }

        lines =  new BufferedReader(new InputStreamReader(mapUrl,
                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

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

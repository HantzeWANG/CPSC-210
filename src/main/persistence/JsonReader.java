package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Goods;
import model.User;
import model.Warehouse;
import org.json.*;

// Represents a reader that reads a user from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses a user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        User user = new User(name,email,password);
        addWarehouses(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses warehouses from JSON object and adds them to user
    private void addWarehouses(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("warehouses");
        for (Object json : jsonArray) {
            JSONObject nextWarehouse = (JSONObject) json;
            addWarehouse(user, nextWarehouse);
        }
    }

    private void addGoods(Warehouse wh, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Goods goods = new Goods(name);
        int quantityInStock = jsonObject.getInt("quantityInStock");
        double averageCost = jsonObject.getDouble("averageCost");
        double totalCostInStock = jsonObject.getDouble("totalCostInStock");
        goods.setAverageCost(averageCost);
        goods.setQuantityInStock(quantityInStock);
        goods.setTotalCostInStock(totalCostInStock);
        wh.addGoods(goods);

    }

    // MODIFIES: user
    // EFFECTS: parses warehouse from JSON object and adds it to user
    private void addWarehouse(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray transactionRecords = jsonObject.getJSONArray("transactionRecords");
        JSONArray goodsInWarehouseMenu = jsonObject.getJSONArray("goodsInWarehouseMenu");

        Warehouse wh = new Warehouse(name);

        for (Object tr : transactionRecords) {
            wh.addTransactionRecords(tr.toString());
        }

        for (Object goods : goodsInWarehouseMenu) {
            JSONObject nextGoods = (JSONObject) goods;
            addGoods(wh,nextGoods);
        }


        user.addWarehouse(wh);
    }
}


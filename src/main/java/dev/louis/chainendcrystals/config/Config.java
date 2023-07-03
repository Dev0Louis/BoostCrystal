package dev.louis.chainendcrystals.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.nio.file.Files;

public class Config {
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 
    public int multiplier = 3;

    public static void writeConfig(Config config) {
        var path = FabricLoader.getInstance().getConfigDir().resolve("boostcrystal_config.json");
        try {
            Files.writeString(path, GSON.toJson(config));
        }
        catch (IOException e) {
            System.out.println("Error in writing Config! " + e.getMessage());
        }
    }
 
    public static Config readConfig() {
        var path = FabricLoader.getInstance().getConfigDir().resolve("boostcrystal_config.json");
 
        Config config = null;
        try {
            if(!path.toFile().exists())writeConfig(new Config());
            config = GSON.fromJson(Files.readString(path), Config.class);
        } catch (IOException e) {
            writeConfig(new Config());
            System.out.println("Error in reading Config! Resetting it! " + e.getMessage());
        }
 
        return config;
    }
}
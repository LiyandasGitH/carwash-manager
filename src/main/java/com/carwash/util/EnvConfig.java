package com.carwash.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class EnvConfig {

    private static boolean loaded = false;

    private EnvConfig() {}


    /**
     * Reads a .env file located at the project root and sets key-value pairs
     * into JVM System properties if not already loaded.
     */
    private static synchronized void load() {
        if (loaded) return;

        File envFile = new File(".env");
        if (envFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty() && !line.startsWith("#") && line.contains("=")) {
                        String[] parts = line.split("=", 2);
                        System.setProperty(parts[0].trim(), parts[1].trim());
                    }
                }
            } catch (Exception ignored) {}
        }
        loaded = true;
    }

    /**
     * Resolves an environment key prioritizing OS env vars, then .env properties, then fallback default.
     */
    public static String getEnvOrProp(String key, String def) {
        load();

        String val = System.getenv(key);
        if (val != null && !val.isEmpty()) {
            return val;
        }
        val = System.getProperty(key);
        return (val != null && !val.isEmpty()) ? val : def;
    }
}

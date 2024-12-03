package edu.fiuba.algo3.repositorios;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AbridorDeJson {
    public JSONObject abrirJson(String archivo) throws IOException, ParseException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(archivo+".json");
        if (inputStream == null) {
            throw new FileNotFoundException("Archivo " + archivo +".json no encontrado en src/main/resources/json");
        }
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new InputStreamReader(inputStream));
    }
}


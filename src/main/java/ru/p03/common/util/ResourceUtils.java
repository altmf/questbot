/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.p03.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timofeevan
 */
public class ResourceUtils {
    
    public static String resource_asString(Class parentClass, String path, String encoding){
        String str = "";
        try (InputStream stream = parentClass.getResourceAsStream(path); 
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, encoding))){            
            String s = "";
            while((s = br.readLine()) != null){
                str += (s + "\n");
            }           
        } catch (UnsupportedEncodingException ex ) {
            Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex ) {
            Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    
    public static String resource_asString(Class parentClass, String path){
        return resource_asString(parentClass, path, "UTF8");       
    }
    
    public static String resource_asString(String path){
        String str = "";
        try (InputStream stream = ResourceUtils.class.getResourceAsStream(path); 
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF8"))){                  
            String s = "";
            while((s = br.readLine()) != null){
                str += (s + "\n");
            }           
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return str;
    }
    
    public static String resource_asString(URL url){
        String str = "";
        try (InputStream stream = url.openStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF8"))){                  
            String s = "";
            while((s = br.readLine()) != null){
                str += (s + "\n");
            }           
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResourceUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return str;
    }
    
    public static String readFile(String filePath) throws IOException{
        Charset encoding = Charset.forName("utf-8");
        return readFile(filePath, encoding);
    }
    
    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
    public static Path writeFile(String text, String path) throws IOException{
        Charset encoding = Charset.forName("utf-8");
        return writeFile(text, path, encoding);
    }
    
    private static Path writeFile(String text, String path, Charset encoding) throws IOException {
        byte[] encoded = text.getBytes(encoding);
        return Files.write(Paths.get(path), encoded);
    }

//    public static String getAsFamiliaIO(Long idUser){
//        User finded = QueriesEngine.single(Queries.user_findById(idUser));
//        return ClientConversionUtils.getAsFamiliaIO(finded);
//    }  

}

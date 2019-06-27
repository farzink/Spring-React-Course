package com.jmelon.onlinecourse.Util;

import com.jmelon.onlinecourse.model.AuthUser;
import jdk.dynalink.StandardOperation;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class SimpleStorageManager {
    final String SEPARATOR = ";";
    final String NEWLINE = "\n";
    final String fileName = "users";
    final String hashSecret = "sdfughslkdjfhgwpouerytsdfjkgh";

    public boolean exists() {
        File file = new File(fileName);
        return file.exists();
    }

    public void init() {
        try {
            if (!exists()) {
                File file = new File(fileName);
                file.createNewFile();
            }
        } catch(Exception ex){
            System.out.println("error during storage creation");
        }
    }

    public AuthUser getUserByToken(String token) {
        try {
            List<AuthUser> users = getAllUsers();
            for (var user:users
            ) {
                if(user.getToken().equals(token))
                    return user;
            }
            return null;
        } catch(Exception ex) {
            System.out.println("error during file operation - userexists");
            return null;
        }
    }

    public boolean userExists(AuthUser model) {
        try {
            List<AuthUser> users = getAllUsers();
            for (var user:users
                 ) {
                if(user.getEmail().equals(model.getEmail()))
                    return true;
            }
            return false;
        } catch(Exception ex) {
            System.out.println("error during file operation - userexists");
            return true;
        }
    }

    public void registerToken(AuthUser model) {
        try {
            List<AuthUser> users = getAllUsers();
            for (var user:users
            ) {
                if(user.getEmail().equals(model.getEmail()))
                    user.setToken(model.getToken());
            }
            dump(users);
        } catch(Exception ex) {
            System.out.println("error during file operation - registerToken");

        }
    }

    public void deregisterToken(AuthUser model) {
        try {
            List<AuthUser> users = getAllUsers();
            for (var user:users
            ) {
                if(user.getEmail().equals(model.getEmail()))
                    user.setToken(" ");
            }
            dump(users);
        } catch(Exception ex) {
            System.out.println("error during file operation - registerToken");

        }
    }

    void dump(List<AuthUser> users) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file, false);
            for (var user : users
            ) {
                var token = user.getToken() != " " ? user.getToken(): " ";
                writer.write(user.getId() + SEPARATOR + user.getEmail() + SEPARATOR + user.getPassword() + SEPARATOR + token + SEPARATOR +  NEWLINE);
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            System.out.println("error during file operation - dump");
        }
    }




    List<AuthUser> getAllUsers() {
        try {
            List<AuthUser> users = new ArrayList<>();
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                var parts = scanner.nextLine().split(Pattern.quote(SEPARATOR));
                users.add(new AuthUser(Long.valueOf(parts[0]), String.valueOf(parts[1]), String.valueOf(parts[2]), String.valueOf(parts[3])));
            }
            return users;
        } catch (Exception ex) {
            System.out.println("error during file operation - getallusers");
            return null;
        }

    }


    public boolean addUser(AuthUser model) {
        try {
            File file = new File(fileName);
            List<AuthUser> users = getAllUsers();
            FileWriter writer = new FileWriter(file, false);
            users.add(new AuthUser(model.getId(), model.getEmail(), generateHash(model.getPassword()), model.getToken()));
        for (var user : users
        ) {
            writer.write(user.getId() + SEPARATOR + user.getEmail() + SEPARATOR + user.getPassword() + SEPARATOR + " " + SEPARATOR +  NEWLINE);
        }
        writer.flush();
        writer.close();
        return true;
    } catch (Exception ex) {
        System.out.println("error during file operation - adduser");
        return false;
    }

    }
    public AuthUser findUserWithEmailAndPassword(AuthUser model) {
        try {
            List<AuthUser> users = getAllUsers();
            for (var user:users
            ) {
                if(user.getEmail().equals(model.getEmail()) && user.getPassword().equals(generateHash(model.getPassword())))
                    return user;
            }
            return null;
        } catch(Exception ex) {
            System.out.println("error during file operation - findUser");
            return null;
        }
    }

    String generateHash(String password){
        String passwordToHash = password;
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            return generatedPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}

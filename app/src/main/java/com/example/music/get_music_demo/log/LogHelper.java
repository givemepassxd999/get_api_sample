package com.example.music.get_music_demo.log;

public class LogHelper {
    public static void print(String s){
        if(s.length() > 4000) {
            int time = s.length() / 4000;
            for (int i = 0; i < time; i++) {
                if (i * 4000 > s.length()) {
                    System.out.println("!!<" + s.substring(i * 4000));
                } else {
                    System.out.println("!!<" + s.substring(i * 4000, i * 4000 + 3999));
                }
            }
        } else{
            System.out.println("!!<" + s);
        }
    }
}

package com.xxj.model;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 背景音乐
 */
public class Music {
    public void music() throws FileNotFoundException, JavaLayerException {
        Player player;
        String str = "C:\\Users\\Administrator\\Desktop\\exercise\\music" + "\\music.mp3";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        player = new Player(bufferedInputStream);

        player.play();
    }
}

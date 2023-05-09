package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label songLabel;
    @FXML
    private Button fileChooser, playNPause, stop, seekBack, minusTen, plusTen;
    @FXML
    private Slider durationLeft;

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerTets;

    private File file;

    private String path;

    @FXML
    private MediaView teeHee;

    @FXML
    private ImageView maybework;



    Image test = new Image("G:\\Bruh\\javafx-sdk-20.0.1\\lib\\demo\\src\\main\\resources\\neco-arc-dance.gif");

    public void chooseFile(){
        FileChooser fileChooser1 = new FileChooser();
        File monkey = fileChooser1.showOpenDialog(null);
        path = monkey.toURI().toString();
        String daComputaPath = monkey.getName();

        if(path != null){
            if(mediaPlayerTets != null){
                mediaPlayerTets.dispose();
                maybework.setVisible(false);
            }
            Media daMedia = new Media(path);
            System.out.println(daComputaPath.substring(daComputaPath.lastIndexOf("."), daComputaPath.length()));
            if(daComputaPath.substring(daComputaPath.lastIndexOf("."), daComputaPath.length()).equals(".mp3")){
                mediaPlayerTets = new MediaPlayer(daMedia);
                maybework.setImage(test);
                maybework.setVisible(true);
            }
            else {
                mediaPlayerTets = new MediaPlayer(daMedia);
                teeHee.setMediaPlayer(mediaPlayerTets);
            }

            songLabel.setText(monkey.getName());

            mediaPlayerTets.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                       durationLeft.setValue(t1.toSeconds());
                }
            });

            durationLeft.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayerTets.seek(Duration.seconds(durationLeft.getValue()));
                }
            });

            durationLeft.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayerTets.seek(Duration.seconds(durationLeft.getValue()));
                }
            });

            mediaPlayerTets.setOnReady(new Runnable() {
                @Override
                public void run() {
                   Duration total = daMedia.getDuration();
                   durationLeft.setMax(total.toSeconds());
                }
            });

            mediaPlayerTets.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maybework.setVisible(false);
        file = new File("G:\\Bruh\\javafx-sdk-20.0.1\\lib\\demo\\Y2Mate.is - [60fps Full] PoPiPo ぽっぴっぽー - Hatsune Miku 初音ミク DIVA Dreamy theater ドリーミーシアター English Romaji-TNf3GPizM58-720p-1658982275565.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void playMedia(){
        mediaPlayerTets.play();
    }
    public void pauseMedia(){
        mediaPlayerTets.pause();
    }

    public void stopMedia(){
        mediaPlayerTets.stop();
    }

    public void plusTenSec(){
        mediaPlayerTets.seek(mediaPlayerTets.getCurrentTime().add(Duration.seconds(10)));
    }

    public void minusTenSec(){
        mediaPlayerTets.seek(mediaPlayerTets.getCurrentTime().add(Duration.seconds(-10)));
    }

}
package ui.main;

import database.DatabaseHandler;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.LibraryAssistantUtil;


public class Main extends Application {

    private double OffsetX = 0;
    private double OffsetY = 0;

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        new Thread(() -> {

            DatabaseHandler.getInstance();
        }).start();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/login/login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            OffsetX = event.getSceneX();
            OffsetY = event.getSceneY();
        });
        root.setOnMouseDragged((javafx.scene.input.MouseEvent event) -> {
                    stage.setX(event.getScreenX() - OffsetX);
                    stage.setY(event.getScreenY() - OffsetY);
                }
        );
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Library Assistant Login");

        LibraryAssistantUtil.setStageIcon(stage);


    }

}

package team07.vikingwars;

import team07.vikingwars.Controller.FXMLDocumentController;
import team07.vikingwars.Controller.Music;
import team07.vikingwars.Database.Database;
import team07.vikingwars.Model.Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

/**
 * Viking wars entrypoint.
 * This class is used to start all the necessary threads.
 * Also handles shutdown.
 *
 */
public class App extends Application {
    private FXMLDocumentController controller;
    Game g;
    Music m;

    /**
     * Start gets called by the super constructor.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        controller = loader.getController();
        g = new Game(controller);
        g.start();
        m = new Music();
        m.playMusic();
        controller.setGameRef(g);
        stage.setFullScreen(true);
        stage.setTitle("VIKING WARS MMORPG FREE TO PLAY");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Called when the last JavaFX Window is closed. Use to cleanup resources and
     * save state if nessesary.
     */
    @Override
    public void stop() throws Exception {
        // Lets the game thread exit its while loops.
        g.prepareExit();
        m.stopMusic();
        super.stop();
    }

    /**
     * Starts Database instance and launches the JavaFX module.
     * @param args
     */
    public static void main(String[] args) {
        Database db = Database.instance();
        launch(args);
    }
}

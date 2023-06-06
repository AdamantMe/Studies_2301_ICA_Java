package ica.chatviewer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * This class provides helper functions for the ChatViewer application.
 * It's responsible for handling files and images in the application.
 */
public class HelperFunctions {

    /**
     * Prompts the user to select a file to open.
     * The user will only be able to select files with a ".msg" extension.
     *
     * @return the selected file, or null if the user did not select a file
     */
    public static File OpenFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MSG files (*.msg)", "*.msg");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(Main.CurrentStage);
        return selectedFile;
    }

    public static Image ImageHappy;
    public static Image ImageSad;

    /**
     * Initializes the images (emojis) that can be displayed in the chat messages.
     *
     * @param originalClass the class from which this method is called. Used to get the class loader to load the images.
     */
    public static void InitImages(Class originalClass) {
        ImageHappy = new Image(originalClass.getResourceAsStream("smile_happy.gif"));
        ImageSad = new Image(originalClass.getResourceAsStream("smile_sad.gif"));
    }

    /**
     * Gets an ImageView object representing an emoji.
     * The emoji will be happy or sad depending on the isHappy parameter.
     *
     * @param isHappy if true, returns a happy emoji; otherwise, returns a sad emoji
     * @return an ImageView object representing the emoji
     */
    public static ImageView GetEmoji(boolean isHappy) {
        return isHappy ? new ImageView(ImageHappy) : new ImageView(ImageSad);
    }
}

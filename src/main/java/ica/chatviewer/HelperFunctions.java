package ica.chatviewer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class HelperFunctions {

    public static File OpenFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MSG files (*.msg)", "*.msg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("D:\\Studies\\2301_Java\\ICA-Appendix\\example_msg_files"));
//        fileChooser.setInitialDirectory(new File("C:\\Studies\\2301_Java\\ICA-Appendix\\example_msg_files"));

        File selectedFile = fileChooser.showOpenDialog(Main.CurrentStage);
        return selectedFile;
    }

    public static Image ImageHappy;
    public static Image ImageSad;

    public static void InitImages(Class originalClass) {
        ImageHappy = new Image(originalClass.getResourceAsStream("smile_happy.gif"));
        ImageSad = new Image(originalClass.getResourceAsStream("smile_sad.gif"));
    }

    public static ImageView GetEmoji(boolean isHappy) {
        return isHappy ? new ImageView(ImageHappy) : new ImageView(ImageSad);
    }
}

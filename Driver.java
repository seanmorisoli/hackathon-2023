import javax.swing.*;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
        new FileUpload();
        JFrame frame = new layout();
        frame.pack();
        frame.setVisible(true);
    }
}
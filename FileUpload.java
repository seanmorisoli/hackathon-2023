import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class FileUpload implements ActionListener {

    JButton fileUploadButton;
    static File audioFile;
    static JFrame frame;

    public FileUpload() {
        frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Choose your .mp3 file to upload: ");

        fileUploadButton = new JButton("Upload File");
        fileUploadButton.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(label);
        panel.add(fileUploadButton);


        frame.add(panel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("File Upload");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fileUploadButton) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {
                File audioFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                //Send file to ansels code
            }

            frame.dispose();

        }
            
    }


}

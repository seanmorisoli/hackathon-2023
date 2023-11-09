import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sectionEditor {
    private JPanel editor;
    private int j;
    private boolean a = false;
    private JButton save,closeButton;
    private JFrame section;
    public sectionEditor(int l){
        j = l;
    }
    public boolean isOpen(){
        return a;
    }
    public void open(){
        section = new JFrame("Section: "+j); //Sets the layout for one audio section
        editor = new JPanel();
        editor.setLayout(null);
        editor.setPreferredSize(new Dimension());

        saveButtonSetup();
        closeButtonSetup();

        section.add(editor,BorderLayout.CENTER);
        section.setPreferredSize(new Dimension(500,500));
        section.pack();
        section.setVisible(true);
        a = true;
        ButtonListener press = new ButtonListener();
        save.addActionListener(press);
        closeButton.addActionListener(press);
    }
    class ButtonListener implements ActionListener { //What to do if a button is clicked
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if(source.equals(save)){ //Save button setup does nothing right now

            }
            if(source.equals(closeButton)){ //Close button. Just makes window invisible
                section.setVisible(false);
                a = false;
            } //I was unable to set it up so if you close the window by hitting the red "X" it actually closes the section
        }
    }
    public void saveButtonSetup(){ //Sets up the save button
        save = new JButton("Save");
        save.setFont(new Font("Arial", Font.BOLD, 12));
        save.setBounds(420,424,80,50);
        editor.add(save);
    }
    public void closeButtonSetup(){ //Sets up the close button
        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 12));
        closeButton.setBounds(340,424,80,50);
        editor.add(closeButton);
    }
}

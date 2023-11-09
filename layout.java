import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class layout extends JFrame implements ChangeListener {
    private sectionEditor[] allSections = new sectionEditor[8];
    private JPanel gridiron;
    private int startTime, endTime;
    private JPanel field;
    private JSlider vol;
    private JButton[] audioButton;
    private JLabel volText, opened, notTime;
    private JTextField start, end;
    private JButton playButton;
    private boolean p;
    private Image placeHolder;
    private ImageIcon wavePhoto;
    int turn = 0;

    public layout() throws IOException {
        super("Audio Editor");
        createField();
    }

    public void createField() throws IOException {
        gridiron = new JPanel();
        gridiron.setLayout(new BorderLayout()); //Border

        field = new JPanel(); //Everything inside Border
        field.setLayout(null);
        field.setPreferredSize(new Dimension());

        volSetup();
        audioSetup();
        playSetup();
        timeSetup();

        gridiron.add(field, BorderLayout.CENTER);
        gridiron.setPreferredSize(new Dimension(1000, 500));
        setContentPane(gridiron);

        ButtonListener play = new ButtonListener();



        for (int c = 0; c < audioButton.length; c++) {
            audioButton[c].addActionListener(play);
        }
        playButton.addActionListener(play);
        start.addActionListener(play);
        end.addActionListener(play);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void timeSetup(){  //Start and end time inputs
        start = new JTextField("Start");
        start.setBounds(1,1,60,30);
        end = new JTextField("End");
        end.setBounds(60,1,60,30);
        field.add(start);
        field.add(end);
    }
    /*
    I was unable to set up how to read this. If you are able to please add that
     */
    public void playSetup(){ //Play button setup
        playButton = new JButton("▶");
        playButton.setFont(new Font("Arial", Font.BOLD, 12));
        playButton.setBounds(950, 1, 50, 50);
        field.add(playButton);
    }

    public void volSetup() { //Volume slider and text

        vol = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); //All for slider
        vol.setBounds(800, 30, 150, 50);
        vol.setMajorTickSpacing(25);
        vol.setMinorTickSpacing(5);
        vol.setPaintTicks(true);
        vol.setPaintLabels(true);
        vol.addChangeListener(this);

        volText = new JLabel("Volume: " + vol.getValue());
        volText.setSize(90, 40);
        volText.setLocation(835, 3);

        field.add(volText);
        field.add(vol);
    }
    public void audioSetup() throws IOException { //Audio Buttons setup
        BufferedImage l = ImageIO.read(new File("/Users/ryanliang/Documents/CMPINF401/Assingments/HACKATHON/Audio_Editor/src/sfef.png"));
        placeHolder = l.getScaledInstance(126, 126, Image.SCALE_DEFAULT);
        wavePhoto = new ImageIcon(placeHolder);
        //I was only able to set the image up like this. If you have a different way of doing it please do so

        audioButton = new JButton[8];
        int x = 10;
        int y = 225;
        int a=1;
        for (int i = 0; i < this.audioButton.length; i++,a++) { //Sets all 8 sections/buttons
            audioButton[i] = new JButton(wavePhoto);
            audioButton[i].setBounds(x, y, 126, 126);
            audioButton[i].setBorderPainted(false);
            allSections[i] = new sectionEditor(a);
            field.add(audioButton[i]);
            x += 122;

        }
    }

    public void stateChanged(ChangeEvent e) {  //Checks to see if volume slider was touched
        volText.setText("Volume: " + vol.getValue());
    }

    public void playStop() { //If volume button is pressed changes the play button icon
        if (p == false) {
            playButton.setText("⏸");
            playButton.setFont(new Font("Arial", Font.BOLD, 19));
            p = true;
        } else if (p == true) {
            playButton.setText("▶");
            playButton.setFont(new Font("Arial", Font.BOLD, 12));
            p = false;
        }
    }

    class ButtonListener implements ActionListener {
        /*Setting the actions for each of the buttons*/

        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.equals(playButton)) {
                playStop();
            }
            for (int c = 0; c < audioButton.length; c++) {
                if (source.equals(audioButton[c])) {
                    int d = c + 1;
                    if (!allSections[c].isOpen()) { //Checks to make sure that section isn't open
                        allSections[c].open();
                    } else {
                        opened = new JLabel("Section is already open"); //Opens a section
                        opened.setSize(90, 40);
                        opened.setLocation(450, 200);
                        field.add(opened);
                    }
                }
            }

//            if(source.equals(start)){
//                try {
//                    startTime = Integer.parseInt(start.getText());
//                    notTime.setText("");
//                }catch(NumberFormatException nfe){
//                    notTime = new JLabel("Enter an actual time");
//                    notTime.setSize(90, 40);
//                    notTime.setLocation(130, 1);
//                    field.add(notTime);
//                }
            //My attempt at reading the text box inputs. But obviously since I tried to read it in the button method it didn't work


        }
    }
}

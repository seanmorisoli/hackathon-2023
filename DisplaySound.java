import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DisplaySound {
public void displaySoundWave(byte[] audioData) {
    AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
    try {
        // Create an AudioInputStream from the byte array
        AudioInputStream audioInputStream = new AudioInputStream(
            new ByteArrayInputStream(audioData), format, audioData.length / format.getFrameSize());

        // Create a Clip to play the audio
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        // Create a JFrame for displaying the sound wave
        JFrame frame = new JFrame("Sound Wave");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Create a JPanel to draw the waveform
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                int[] samples = new int[width];

                for (int i = 0; i < width; i++) {
                    int sampleIndex = (int) (i * (audioData.length / (double) width));
                    samples[i] = audioData[sampleIndex] * 128; // Scale for visualization
                }

                for (int i = 0; i < width - 1; i++) {
                    g.drawLine(i, height / 2 - samples[i], i + 1, height / 2 - samples[i + 1]);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);

        // Start playing the audio
        clip.start();

    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    // Load your audio data into a byte array
    byte[] audioData = // put audio path here

    SwingUtilities.invokeLater(() -> {
        new YourClassName().displaySoundWave(audioData);
    });
}

}

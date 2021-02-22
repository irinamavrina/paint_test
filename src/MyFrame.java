import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class MyFrame extends JFrame {

    public MyFrame() {
        Container c = getContentPane();
        c.add(buttonPanel, BorderLayout.SOUTH);
        c.add(paintPanel, BorderLayout.CENTER);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(buttonClear);
        buttonPanel.add(buttonSave);
        buttonPanel.add(buttonOpen);

        scroll = new JScrollPane(paintPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        c.add(scroll);
        setContentPane(c);

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                paintPanel.clear();
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                paintPanel.setColorOfLines(Color.GREEN);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                paintPanel.setColorOfLines(Color.ORANGE);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                paintPanel.setColorOfLines(Color.BLUE);
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    saveImageToFile();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MyFrame.this, ex, "error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    openImageFromFile();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MyFrame.this, ex, "error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    private void saveImageToFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("saving");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            ImageIO.write(paintPanel.getImage(), "png", fileChooser.getSelectedFile());
            JOptionPane.showMessageDialog(this, "file" + fileChooser.getSelectedFile() + "saved");
        } else if (fileChooser.showSaveDialog(this) == JFileChooser.CANCEL_OPTION)
            setVisible(false);
    }

    private void openImageFromFile() throws IOException {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        chooser.showOpenDialog(this);
        File selectedFile;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (.png)", "png");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            selectedFile = chooser.getSelectedFile();
        else
            selectedFile = null;
        Image image = ImageIO.read(selectedFile);
        paintPanel.setImage(image);
    }

    private JButton button1 = new JButton("green");
    private JButton button2 = new JButton("orange");
    private JButton button3 = new JButton("blue");
    private JButton buttonClear = new JButton("clear");
    private JButton buttonSave = new JButton("save");
    private JButton buttonOpen = new JButton("open");

    private PaintPanel paintPanel = new PaintPanel();
    private JPanel buttonPanel = new JPanel();
    private JScrollPane scroll;
}
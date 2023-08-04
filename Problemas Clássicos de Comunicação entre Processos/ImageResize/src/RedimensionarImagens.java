import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class RedimensionarImagens {
    private static final int MAX_THREADS = 4;
    private static final double REDUCTION_PERCENTAGE = 0.35;

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Selecione a pasta que contém as imagens");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            System.out.println("Nenhuma pasta selecionada. O programa será encerrado.");
            return;
        }

        File inputFolder = fileChooser.getSelectedFile();

        File outputFolder = new File(inputFolder, "ImagensRedimensionadas");
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }

        File[] imageFiles = inputFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg")
                || name.toLowerCase().endsWith(".jpeg")
                || name.toLowerCase().endsWith(".png")
                || name.toLowerCase().endsWith(".gif"));

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        for (File imageFile : imageFiles) {
            executor.execute(() -> redimensionarImagem(imageFile, outputFolder));
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            // Aguarda todas as threads terminarem
        }

        System.out.println("Redimensionamento concluído!");
    }

    private static void redimensionarImagem(File imageFile, File outputFolder) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            int newWidth = (int) (originalImage.getWidth() * REDUCTION_PERCENTAGE);
            int newHeight = (int) (originalImage.getHeight() * REDUCTION_PERCENTAGE);

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            String outputFilePath = outputFolder.getAbsolutePath() + File.separator + imageFile.getName();
            ImageIO.write(resizedImage, "png", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

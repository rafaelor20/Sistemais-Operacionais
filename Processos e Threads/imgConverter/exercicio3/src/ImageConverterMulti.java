import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageConverterMulti {
    public static void main(String[] args) {
        // Pasta contendo as imagens
        String pastaImagens = "/home/rafael/Downloads/Wallpapers/";

        // Lista de arquivos na pasta
        File folder = new File(pastaImagens);
        File[] files = folder.listFiles();

        // Criar uma thread para cada imagem e iniciar a conversão
        for (File file : files) {
            if (file.isFile() && isSupportedImage(file)) {
                Thread thread = new Thread(() -> convertToGrayScale(file));
                thread.start();
            }
        }

        System.out.println("Conversão iniciada!");
    }

    private static boolean isSupportedImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    private static void convertToGrayScale(File file) {
        try {
            BufferedImage originalImage = ImageIO.read(file);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            // Criar uma nova imagem em escala de cinza
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Color color = new Color(originalImage.getRGB(i, j));
                    int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    int grayPixel = new Color(grayValue, grayValue, grayValue).getRGB();
                    grayImage.setRGB(i, j, grayPixel);
                }
            }

            // Salvar a nova imagem em escala de cinza
            String newFileName = file.getName().substring(0, file.getName().lastIndexOf(".")) + "_pb.jpg";
            File newFile = new File(file.getParent(), newFileName);
            ImageIO.write(grayImage, "jpg", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

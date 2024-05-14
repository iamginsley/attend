package com.example.application.views.teacher.Modal;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    private Image qrCodeImage;
    private String text;
    private int width;
    private int height;

    public QRCodeGenerator(String text, int width, int height) {
        qrCodeImage = new Image();
        this.text = text;
        this.width = width;
        this.height = height;
        try {
            generateQRCodeImage(text, width, height);
        } catch (WriterException | IOException e) {
            System.out.println("Could not generate QR Code: " + e.getMessage());
        }
    }
    public Image getQrCodeImage() {
        return qrCodeImage;
    }

    private void generateQRCodeImage(String text, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Convert BufferedImage to StreamResource
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        StreamResource resource = new StreamResource("qr.png", () -> new ByteArrayInputStream(os.toByteArray()));

        // Set StreamResource as Image source
        qrCodeImage.setSrc(resource);
    }
}
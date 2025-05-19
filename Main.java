package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    private static final int START_X = 145, START_Y = 589, DX = 72;
    private static final int RED_THRESH = 20;
    private static final Region SUIT = new Region(5, 29, 20, 17);
    private static final Region RANK = new Region(0, 0, 35, 26);
    private static final int CX = 15 - SUIT.x, CY = 38 - SUIT.y;
    private static final int FP_W = 12, FP_H = 12;

    private static final List<Map.Entry<String, BigInteger>> RANK_MASK_LIST =
            Map.ofEntries(
                    Map.entry("2",  new BigInteger("f80180100200600400800800d8070000000", 16)), Map.entry("3",  new BigInteger("700d80800800c00600200400c00f8000000", 16)),
                    Map.entry("4",  new BigInteger("800801f81f80980900a00e00c00c0000000", 16)), Map.entry("5",  new BigInteger("780d80800800800f80080080180f8000000", 16)),
                    Map.entry("6",  new BigInteger("f00980880880980f80080180900f0000000", 16)), Map.entry("7",  new BigInteger("100100200200200400400800800f8000000", 16)),
                    Map.entry("8",  new BigInteger("700d80880880d80700880880d8070000000", 16)), Map.entry("9",  new BigInteger("780d80800800f00d80880880d8070000000", 16)),
                    Map.entry("10", new BigInteger("3c824866842842842842866824c3cc000000", 16)), Map.entry("A",  new BigInteger("10410c1f80f80880d8050050070020000000", 16)),
                    Map.entry("J",  new BigInteger("38068040040040040040040040040000000", 16)), Map.entry("K",  new BigInteger("1081880880d80580680480c8088188000000", 16)),
                    Map.entry("Q",  new BigInteger("3e03b03982882882082083181b01e0000000", 16))
            ).entrySet().stream().toList();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: run.bat <images_folder>");
            System.exit(1);
        }
        File dir = new File(args[0]);
        for (File f : Objects.requireNonNull(dir.listFiles((d, n) -> n.endsWith(".png")))) {
            String res = recognize(ImageIO.read(f));
            System.out.printf("%s - %s%n", f.getName(), res);
        }
    }

    static String recognize(BufferedImage img) {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 5; i++) {
            int x = START_X + i * DX, y = START_Y;
            BufferedImage suit = img.getSubimage(x + SUIT.x, y + SUIT.y, SUIT.w, SUIT.h);
            if (countBlack(suit) == SUIT.w * SUIT.h) break;
            BufferedImage rank = img.getSubimage(x + RANK.x, y + RANK.y, RANK.w, RANK.h);
            sb.append(matchRank(rank)).append(matchSuit(suit));
        }
        return sb.toString();
    }

    private static int countBlack(BufferedImage img) {
        int cnt = 0;
        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++)
                if (gray(img.getRGB(x, y)) < 120) cnt++;
        return cnt;
    }

    private static int gray(int rgb) {
        int r = (rgb >> 16) & 0xFF, g = (rgb >> 8) & 0xFF, b = rgb & 0xFF;
        return (77 * r + 150 * g + 29 * b) >> 8;
    }

    private static String matchRank(BufferedImage img) {
        BigInteger bits = BigInteger.ZERO;
        int w = img.getWidth(), h = img.getHeight();
        for (int i = 0; i < FP_W * FP_H; i++) {
            int px = (i % FP_W) * w / FP_W;
            int py = (i / FP_W) * h / FP_H;
            if (gray(img.getRGB(px, py)) < 120) bits = bits.setBit(i);
        }
        String best = "?";
        int minDist = Integer.MAX_VALUE;
        for (Map.Entry<String, BigInteger> e : RANK_MASK_LIST) {
            int d = bits.xor(e.getValue()).bitCount();
            if (d < minDist) {
                minDist = d;
                best = e.getKey();
            }
        }
        return best;
    }

    private static String matchSuit(BufferedImage img) {
        int p = img.getRGB(CX, CY);
        boolean isRed = ((p >> 16) & 0xFF) > (((p >> 8) & 0xFF) + RED_THRESH);
        int black = countBlack(img);
        if (isRed) {
            return (black < 95 || (black > 115 && black < 135)) ? "d" : "h";
        } else {
            return (black < 110 || (black > 140 && black < 155)) ? "c" : "s";
        }
    }

    record Region(int x, int y, int w, int h) {}
}
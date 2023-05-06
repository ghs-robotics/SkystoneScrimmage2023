package org.firstinspires.ftc.teamcode.cv;

import com.acmerobotics.dashboard.config.Config;

@Config
public class ColorFilterConstants {
    // Camera display pixel resolution
    public static int PIXEL_HEIGHT = 240;
    public static int PIXEL_WIDTH = 320;

    public static int FILTER_TYPE = 0;
    public static boolean FILTER = true;
    public static boolean CANNY = false;

    // found HSV constants but need to find optimal difference
    public static int BLOCK_DARK_H = 155;
    public static int BLOCK_LIGHT_H = 100;

    public static int BLOCK_DARK_S = 110;
    public static int BLOCK_LIGHT_S = 87;

    public static int BLOCK_DARK_V = 255;
    public static int BLOCK_LIGHT_V = 218;


    // preliminary RGB constants - needs testing
    public static int LOWER_R = 228;
    public static int UPPER_R = 233;

    public static int LOWER_G = 170;
    public static int UPPER_G = 185;

    public static int LOWER_B = 100;
    public static int UPPER_B = 115;


    // grayscale constants
    public static int GRAY_LOWER = 101;
    public static int GRAY_UPPER = 139;

    public static int[] HSV_DIFF = {BLOCK_DARK_H - BLOCK_LIGHT_H, BLOCK_DARK_H - BLOCK_LIGHT_H, BLOCK_DARK_V - BLOCK_LIGHT_V};
}

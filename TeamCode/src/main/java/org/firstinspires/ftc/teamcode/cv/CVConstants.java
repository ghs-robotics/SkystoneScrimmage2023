package org.firstinspires.ftc.teamcode.cv;

import com.acmerobotics.dashboard.config.Config;

@Config
public class CVConstants {
    // Camera display pixel resolution
    public static int PIXEL_HEIGHT = 240;
    public static int PIXEL_WIDTH = 320;

    public static int FILTER_TYPE = 0;
    public static boolean FILTER = true;

    // HSV constants
    public static int DARK_H = 170;
    public static int LIGHT_H = 30;

    public static int DARK_S = 150;
    public static int LIGHT_S = 40;

    public static int DARK_V = 255;
    public static int LIGHT_V = 218;


    // RGB constants
    public static int LOWER_R = 0;
    public static int UPPER_R = 255;

    public static int LOWER_G = 0;
    public static int UPPER_G = 255;

    public static int LOWER_B = 0;
    public static int UPPER_B = 255;


    // grayscale constants
    public static int GRAY_LOWER = 101;
    public static int GRAY_UPPER = 139;
}

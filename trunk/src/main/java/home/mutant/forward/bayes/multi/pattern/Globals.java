package home.mutant.forward.bayes.multi.pattern;

public class Globals {
	public enum FILL_TYPE{UNIFORM,ROUND, SQUARE}
	public static final int NO_TRAIN = 6000;
	public static final int NO_TEST = 1000;
	public static int SUBIMG_SIZE = 25;
	public static int NO_COLUMNS = 400;
	public static FILL_TYPE FILL = FILL_TYPE.ROUND;
	public static double SIMILARITY = 0.78;
	public static final double RADIUS = 3;
	public static int MAX_PIXEL_VALUE = 255;
	public static int SUB_LIST_NEURONS = 1600;
}

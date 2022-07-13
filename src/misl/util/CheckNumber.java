package misl.util;

public class CheckNumber {

	public static Integer formatNull(int val) {
		if(val != Integer.MIN_VALUE) {
			return val;
		}
		return null;
	}
	
	public static Long formatNull(long val) {
		if(val != Long.MIN_VALUE) {
			return val;
		}
		return null;
	}
	
	public static Short formatNull(short val) {
		if(val != Short.MIN_VALUE) {
			return val;
		}
		return null;
	}
	
	public static Byte formatNull(byte val) {
		if(val != Byte.MIN_VALUE) {
			return val;
		}
		return null;
	}
	
	public static Double formatNull(double val) {
		if(val != Double.MIN_VALUE) {
			return val;
		}
		return null;
	}
	
	public static Float formatNull(float val) {
		if(val != Float.MIN_VALUE) {
			return val;
		}
		return null;
	}
	
}

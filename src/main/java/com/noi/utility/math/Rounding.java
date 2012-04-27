package com.noi.utility.math;

public class Rounding {
	public static float roundFloat(float Rval, int Rpl) {
		float p = (float) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (float) tmp / p;
	}
}

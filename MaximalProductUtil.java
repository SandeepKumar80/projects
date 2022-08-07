package com.org.service.user.util;

public class MaximalProductUtil {

	private static int maximalProduct(int number) {
		if (number < 0) {
			return 0;
		}
		if (number == 2 || number == 3)
			return (number - 1);
		int temp = 1;
		while (number > 4) {
			number = number - 3;
			temp = temp * 3;
		}
		return (number * temp);
	}

	public static void main(String[] args) {
		int number = 8;
		System.out.println("Maximal Product of integer: " + number + " is " + maximalProduct(number));
	}
}
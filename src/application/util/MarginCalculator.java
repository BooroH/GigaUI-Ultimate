package application.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MarginCalculator {
	 public static double calc(double defaultMargin, double defaultWidth, double nodeWidth, double marginDiff, double targetWidth) {
	        BigDecimal v_defaultMargin      = new BigDecimal(defaultMargin);
	        BigDecimal v_marginDiff = new BigDecimal(marginDiff);
	        BigDecimal v_defaultWidth       = new BigDecimal(defaultWidth);
	        BigDecimal v_nodeWidth       = new BigDecimal(nodeWidth);
	        BigDecimal x          = new BigDecimal(String.valueOf(targetWidth));

	        // (346 - 230) = 116
	        BigDecimal denom = v_defaultWidth.subtract(v_nodeWidth);

	        // (346 - x)
	        BigDecimal numer = v_defaultWidth.subtract(x);

	        // 2.6 / 116 * (346 - x)  →  정밀도 손실 없이 계산
	        BigDecimal result = v_defaultMargin.subtract(
	        		v_marginDiff
	                .divide(denom, 10, RoundingMode.HALF_UP)  // 중간 계산은 소수점 10자리 유지
	                .multiply(numer)
	        );

	        // 최종 소수점 첫째 자리 반올림
	        return result.setScale(1, RoundingMode.HALF_UP).doubleValue();
	    }
}

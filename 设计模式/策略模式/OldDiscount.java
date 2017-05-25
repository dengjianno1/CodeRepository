package djsoft;

public class OldDiscount implements DiscountStrategy {
	// 重写getDiscount()方法，提供旧书打折算法
	public double getDiscount(double originPrice) {
		System.out.println("使用旧书折扣...");
		return originPrice * 0.7;
	}
}

package djsoft;

public class VipDiscount implements DiscountStrategy {
	// 重写getDiscount()方法，提供VIP打折算法
	public double getDiscount(double originPrice) {
		System.out.println("使用VIP折扣...");
		return originPrice * 0.5;
	}
}

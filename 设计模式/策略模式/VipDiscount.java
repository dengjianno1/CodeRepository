package djsoft;

public class VipDiscount implements DiscountStrategy {
	// ��дgetDiscount()�������ṩVIP�����㷨
	public double getDiscount(double originPrice) {
		System.out.println("ʹ��VIP�ۿ�...");
		return originPrice * 0.5;
	}
}

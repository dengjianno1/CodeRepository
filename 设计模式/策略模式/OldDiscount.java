package djsoft;

public class OldDiscount implements DiscountStrategy {
	// ��дgetDiscount()�������ṩ��������㷨
	public double getDiscount(double originPrice) {
		System.out.println("ʹ�þ����ۿ�...");
		return originPrice * 0.7;
	}
}

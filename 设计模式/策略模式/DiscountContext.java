package djsoft;

public class DiscountContext {
	// ���һ��DiscountStrategy����
	private DiscountStrategy strategy;

	// ������������һ��DiscountStrategy����
	public DiscountContext(DiscountStrategy strategy) {
		this.strategy = strategy;
	}

	// ����ʵ����ʹ�õ�DiscountStrategy����õ��ۿۼ�
	public double getDiscountPrice(double price) {
		// ���strategyΪnull��ϵͳ�Զ�ѡ��OldDiscount��
		if (strategy == null) {
			strategy = new OldDiscount();
		}
		return this.strategy.getDiscount(price);
	}

	// �ṩ�л��㷨�ķ���
	public void setDiscount(DiscountStrategy strategy) {
		this.strategy = strategy;
	}
}

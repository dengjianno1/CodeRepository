package djsoft;

public class Test {
	public static void main(String[] args) {
		// �ͻ���û��ѡ����۲�����
		DiscountContext dc = new DiscountContext(null);
		double price1 = 79;
		// ʹ��Ĭ�ϵĴ��۲���
		System.out.println("79Ԫ����Ĭ�ϴ��ۺ�ļ۸��ǣ�" + dc.getDiscountPrice(price1));
		// �ͻ���ѡ����ʵ�VIP���۲���
		dc.setDiscount(new VipDiscount());
		double price2 = 89;
		// ʹ��VIP���۵õ����ۼ۸�
		System.out.println("89Ԫ�����VIP�û��ļ۸��ǣ�" + dc.getDiscountPrice(price2));
	}
}

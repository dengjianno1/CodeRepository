package djsoft;

public interface DiscountStrategy {
	//定义一个用于计算打折价的方法
	double getDiscount(double originPrice); 
}

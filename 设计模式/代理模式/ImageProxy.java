package djsoft;

public class ImageProxy implements Image {
	private Image image;
	public ImageProxy(Image image) {
		this.image=image;
	}
	@Override
	public void show() {
		onCreate();
		image.show();
		onDestory();
	}
	public void onCreate(){
		System.out.println("׼��չʾͼƬ��");
	}
	public void onDestory(){
		System.out.println("ͼƬչʾ��ɣ�");
	}
}

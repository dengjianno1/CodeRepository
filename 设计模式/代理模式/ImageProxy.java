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
		System.out.println("准备展示图片！");
	}
	public void onDestory(){
		System.out.println("图片展示完成！");
	}
}

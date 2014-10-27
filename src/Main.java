import controller.AbstractController;
import controller.Controller;
import model.AbstractModel;
import model.Model;
import view.WinFrame;

public class Main {

	public static void main(String[] args) {
		AbstractModel model = new Model();
		AbstractController controller = new Controller(model);
		
		WinFrame win = new WinFrame(controller);
		model.addObserver(win);
	}
}
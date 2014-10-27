package controller;

import model.AbstractModel;

public class Controller extends AbstractController {

	public Controller(AbstractModel model) {
		super(model);
	}

	@Override
	void control() {
		// TODO Auto-generated method stub
		switch (this.action) {
		case "start":
			this.model.run();
			break;
		case "stop":
			this.model.stop();
			break;
		default:
			System.out.println("Error");
		}

	}

}

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
			this.model.recuit();
			break;
		case "stop":
			this.model.reset();
			break;
		default:
			System.out.println("Error");
		}

	}

}

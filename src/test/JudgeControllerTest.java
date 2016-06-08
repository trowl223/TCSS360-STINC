package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stinc.Controller;
import stinc.Model;

public class JudgeControllerTest {

	private Controller myController;

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
		myController.login("test", "test");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

package erd;

import org.junit.Test;

import erd.controller.Main;

public class MainTest {

	@Test
	public void testMain() {
		Main n = new Main();
		n.formatAS400ProgramParameters();
		System.out.println(n.toString());
	}

}

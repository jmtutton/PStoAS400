package erd;

import static org.junit.Assert.*;

import org.junit.Test;

import erd.controller.Main;
import erd.model.PsDiversityEthnicity;

public class MainTest {

	@Test
	public void testMain() {
		Main n = new Main();
		n.formatAS400ProgramParameters();
		System.out.println(n.toString());
	}

}

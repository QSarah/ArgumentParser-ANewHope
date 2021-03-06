package edu.jsu.mcis;

import static org.junit.Assert.*;
import org.junit.*;


public class ArgumentParserTest {
	private ArgumentParser pa;
	private Argument ga;
	
	
	@Before
	public void setUp(){
		pa = new ArgumentParser();
		ga = new Argument();
	}
	
	@Test
	public void testArgument(){
		ga.setName("length");
		ga.setDescription("box length");
		ga.setType(Argument.Type.INTEGER);
		ga.setValue("7");
		assertEquals("length", ga.getName());
		assertEquals("box length", ga.getDescription());
		assertEquals(Argument.Type.INTEGER, ga.getType());
		assertEquals(7, ga.getValue());
	}
	
	@Test	
	public void testAddArgMultArguments(){
		pa.addArg("Parser", "", Argument.Type.INTEGER);
		assertEquals(pa.getNumArguments(), 1);
		pa.addArg("Parser2", "", Argument.Type.INTEGER);
		assertEquals(pa.getNumArguments(), 2);
	}
	@Test
	public void testAddArgArgument(){
		ga.setName("length");
		pa.addArg(ga);
		assertEquals(pa.getNumArguments(), 1);
		ga.setName("width");
		pa.addArg(ga);
		assertEquals(pa.getNumArguments(), 2);
	}
	@Test
	public void testParse(){
		Argument ga2 = new Argument();
		ga2.setName("Parser2");
		ga.setName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1", "val2"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getValue());
		Argument temp2 = pa.getArg("Parser2");
		assertEquals("val2", temp2.getValue());
	}
	@Test (expected= IllegalArgumentException.class)
	public void testTooManyArgs(){
		Argument ga2 = new Argument();
		ga2.setName("Parser2");
		ga.setName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1", "val2", "val3"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getValue());
		Argument temp2 = pa.getArg("Parser2");
		assertEquals("val2", temp2.getValue());
		Argument temp3 = pa.getArg("Parser2");
		assertEquals("val3", temp3.getValue());
	}	
	@Test (expected= IllegalArgumentException.class)
	public void testTooFewArgs(){
		Argument ga2 = new Argument();
		ga2.setName("Parser2");
		ga.setName("length");
		pa.addArg(ga);
		pa.addArg(ga2);
		String[] args = {"val1"};
		pa.parse(args);
		Argument temp = new Argument();
		temp = pa.getArg("length");
		assertEquals("val1", temp.getValue());
	}	
}

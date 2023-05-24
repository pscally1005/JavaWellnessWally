import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TimePaceTest {

	@Test
	public void testDefaultConstructor() {
		TimePace tp = new TimePace();
		assertNotNull(tp);
		assertTrue(tp.isTime());
		assertEquals(0, tp.getHours());
		assertEquals(0,tp.getMinutes());
		assertEquals(0,tp.getSeconds());
		assertEquals("00:00:00",tp.toString());
	}
	
	@Test
	public void testTimeWithNumbers() {
		TimePace tp = new TimePace(11,30,59);
		assertNotNull(tp);
		assertTrue(tp.isTime());
		assertEquals(11, tp.getHours());
		assertEquals(30,tp.getMinutes());
		assertEquals(59,tp.getSeconds());
		assertEquals("11:30:59",tp.toString());
	}
	
	@Test
	public void testTimeWithString() {
		TimePace tp = new TimePace(true,"12:34:56");
		assertNotNull(tp);
		assertTrue(tp.isTime());
		assertEquals(12, tp.getHours());
		assertEquals(34,tp.getMinutes());
		assertEquals(56,tp.getSeconds());
		assertEquals("12:34:56",tp.toString());
		
		new TimePace(true,"00:19:59");
		new TimePace(true,"01:59:59");
		new TimePace(true,"01:00:00");
		new TimePace(true,"99:59:59");
	}
	
	@Test
	public void testPaceWithNumbers() {
		TimePace tp = new TimePace(7,59);
		assertNotNull(tp);
		assertFalse(tp.isTime());
		assertEquals(0, tp.getHours());
		assertEquals(7,tp.getMinutes());
		assertEquals(59,tp.getSeconds());
		assertEquals("07:59",tp.toString());
	}
	
	@Test
	public void testPaceWithString() {
		TimePace tp = new TimePace(false,"34:56");
		assertNotNull(tp);
		assertFalse(tp.isTime());
		assertEquals(0, tp.getHours());
		assertEquals(34,tp.getMinutes());
		assertEquals(56,tp.getSeconds());
		assertEquals("34:56",tp.toString());
		
		new TimePace(false,"07:30");
		new TimePace(false,"00:59");
		new TimePace(false,"59:00");
		new TimePace(false,"59:59");
	}
	
	@Test
	public void testTimeInvalidNums() {
		assertThrows(IllegalArgumentException.class, () -> new TimePace(100,0,0));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(0,60,0));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(0,0,60));
		
		assertThrows(IllegalArgumentException.class, () -> new TimePace(-1,0,0));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(0,-1,0));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(0,0,-1));
	}
	
	@Test
	public void testPaceInvalidNums() {
		assertThrows(IllegalArgumentException.class, () -> new TimePace(60,0));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(0,60));
		
		assertThrows(IllegalArgumentException.class, () -> new TimePace(-1,0));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(0,-1));
	}
	
	@Test
	public void testTimeInvalidString() {
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"-1:00:00"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"00:-1:00"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"00:00:-1"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"08:00"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"ab:cd:ef"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"-abcdefg"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"        "));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,""));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true," "));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,null));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"100:00:00"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(true,"00:00:00"));
	}
	
	@Test
	public void testPaceInvalidString() {
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"00:07:30"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"07:60"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"60:30"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"99:99"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"abcde"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,""));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"     "));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false," "));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"12 34"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"98;76"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"-1:59"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"07:-1"));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,null));
		assertThrows(IllegalArgumentException.class, () -> new TimePace(false,"00:00"));
	}
	
	@Test
	public void testDistCalc() {
		assertEquals(8.0, TimePace.calcDist(false, new TimePace(1,4,00), new TimePace(8,0)), BmiTest.EPSILON);
		assertEquals(5.0, TimePace.calcDist(true, new TimePace(0,20,00), new TimePace(4,0)), BmiTest.EPSILON);
		
		assertNotEquals(6, TimePace.calcDist(false, new TimePace(0,40,0), new TimePace(8,0)));
		assertEquals(5, TimePace.calcDist(false, new TimePace(0,40,0), new TimePace(8,0)), BmiTest.EPSILON);
	}
	
	@Test
	public void testTimeCalc() {
		assertEquals(new TimePace(1,4,0), TimePace.calcTime(false, 8.0, new TimePace(8,0)));
		assertEquals(new TimePace(0,20,0), TimePace.calcTime(true, 5.0, new TimePace(4,0)));
		
		assertNotEquals(new TimePace(0,40,0), TimePace.calcTime(false, 6.0, new TimePace(8,0)));
		assertEquals(new TimePace(0,40,0), TimePace.calcTime(false, 5.0, new TimePace(8,0)));
	}
	
	@Test
	public void testPaceCalc() {
		assertEquals(new TimePace(8,0), TimePace.calcPace(false, 8.0, new TimePace(1,4,0)));
		assertEquals(new TimePace(4,0), TimePace.calcPace(true, 5.0, new TimePace(0,20,0)));
	
		assertNotEquals(new TimePace(8,0), TimePace.calcPace(true, 6.0, new TimePace(0,40,0)));
		assertEquals(new TimePace(8,0), TimePace.calcPace(true, 5.0, new TimePace(0,40,0)));
	}
	
	@Test
	public void testEquals() {
		TimePace tp1 = new TimePace(1,1,1);
		assertEquals(tp1,tp1);
		
		TimePace tp2 = new TimePace(1,1,1);
		assertEquals(tp1,tp2);
		assertEquals(tp2,tp1);
		
		TimePace tp3 = new TimePace(1,1,0);
		assertNotEquals(tp1,tp3);
		assertNotEquals(tp3,tp1);
		
		TimePace tp4 = new TimePace(1,0,1);
		assertNotEquals(tp1,tp4);
		assertNotEquals(tp4,tp1);
		
		TimePace tp5 = new TimePace(0,1,1);
		assertNotEquals(tp1,tp5);
		assertNotEquals(tp5,tp1);
		
		String str = "hi";
		assertNotEquals(tp1,str);
		assertNotEquals(str,tp1);
		
		assertNotEquals(tp1,null);
		assertNotEquals(null,tp1);
		
		TimePace tpT = new TimePace(true,"00:01:01");
		TimePace tpP = new TimePace(false,"01:01");
		assertNotEquals(tpT,tpP);
		assertNotEquals(tpP,tpT);
		
		assertEquals(tpT,tp5);
		assertEquals(tp5,tpT);
		
		assertNotEquals(tpP,tp5);
		assertNotEquals(tp5,tpP);
	}
	
	@Test
	public void testHash() {
		TimePace tp1 = new TimePace(1,1,1);
		TimePace tp2 = new TimePace(1,1,1);
		TimePace tp3 = new TimePace(true,"01:01:01");
		assertEquals(tp1.hashCode(),tp2.hashCode());
		assertEquals(tp1.hashCode(),tp3.hashCode());
	}
}

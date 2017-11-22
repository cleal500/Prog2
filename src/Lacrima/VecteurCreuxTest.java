package Lacrima;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class VecteurCreuxTest {

	@Test
	public void testVecteurCreuxInt_1() {
		VecteurCreux v = new VecteurCreux( 1 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 1, v.taille() );
	}

	@Test
	public void testVecteurCreuxInt_2() {
		VecteurCreux v = new VecteurCreux( 5 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 5, v.taille() );
	}

	@Test
	public void testVecteurCreuxInt_3() {
		VecteurCreux v = new VecteurCreux( 50000000 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 50000000, v.taille() );
	}

	@Test
	public void testVecteurCreuxDoubleArray_1() {
		double [] t = { 5 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 1, v.nbrElementNonZero() );
		assertEquals( 1, v.taille() );
	}

	@Test
	public void testVecteurCreuxDoubleArray_2() {
		double [] t = { 0 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 1, v.taille() );
	}

	@Test
	public void testVecteurCreuxDoubleArray_3() {
		double [] t = { 1, 2, 3, 4, 5 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 5, v.nbrElementNonZero() );
		assertEquals( 5, v.taille() );
	}

	@Test
	public void testVecteurCreuxDoubleArray_4() {
		double [] t = { 0, 2, 0, 0, 5 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 5, v.taille() );
	}

	@Test
	public void testVecteurCreuxDoubleArray_5() {
		double [] t = { 0, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 5, v.taille() );
	}

	@Test
	public void testAddition_1() {
		double [] t1 = {1};
		double [] t2 = {1};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.addition( v2 );
		assertEquals( 1, r.nbrElementNonZero() );
		assertEquals( 1, r.taille() );
		assertEquals( 2, r.get(1), 0.0000001 );
	}

	@Test
	public void testAddition_2() {
		double [] t1 = {1, 2};
		double [] t2 = {1, 3};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.addition( v2 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 2, r.taille() );
		assertEquals( 2, r.get(1), 0.0000001 );
		assertEquals( 5, r.get(2), 0.0000001 );
	}

	@Test
	public void testAddition_3() {
		double [] t1 = {1, 2, 0, 0, 0};
		double [] t2 = {1, 3, 0, 0, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.addition( v2 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( 2, r.get(1), 0.0000001 );
		assertEquals( 5, r.get(2), 0.0000001 );
		assertEquals( 0, r.get(3), 0.0000001 );
		assertEquals( 0, r.get(4), 0.0000001 );
		assertEquals( 0, r.get(5), 0.0000001 );
	}
	
	@Test
	public void testAddition_4() {
		double [] t1 = {1, 4, 0, 0, 0};
		double [] t2 = {1, 0, 0, 3, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.addition( v2 );
		assertEquals( 3, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( 2, r.get(1), 0.0000001 );
		assertEquals( 4, r.get(2), 0.0000001 );
		assertEquals( 0, r.get(3), 0.0000001 );
		assertEquals( 3, r.get(4), 0.0000001 );
		assertEquals( 0, r.get(5), 0.0000001 );
	}

	@Test
	public void testAddition_5() {
		double [] t1 = {1, -4, 0, 0, 0};
		double [] t2 = {-1, 0, 0, -3, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.addition( v2 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( 0, r.get(1), 0.0000001 );
		assertEquals( -4, r.get(2), 0.0000001 );
		assertEquals( 0, r.get(3), 0.0000001 );
		assertEquals( -3, r.get(4), 0.0000001 );
		assertEquals( 0, r.get(5), 0.0000001 );
	}

	@Test
	public void testAddition_6() {
		double [] t1 = {1, -4, 0, 0, 0};
		double [] t2 = {-1, 0, 0, -3, 0, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		try {
			v1.addition( v2 );
			fail( "devrait lancer l'exception." );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testAgrandir_1() {
		VecteurCreux v = new VecteurCreux( 1 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 2 );
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 1, r.taille() );
	}

	@Test
	public void testAgrandir_2() {
		VecteurCreux v = new VecteurCreux( 2 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 2 );
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 2, r.taille() );
	}

	@Test
	public void testAgrandir_3() {
		VecteurCreux v = new VecteurCreux( 100 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 2 );
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 100, r.taille() );
	}

	@Test
	public void testAgrandir_4() {
		double [] t = { 1, 1, 1, 1, 1 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 5, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 2 );
		assertEquals( 5, v.nbrElementNonZero() );
		assertEquals( 1, v.get( 1 ), 0.0000001 );
		assertEquals( 5, r.nbrElementNonZero() );
		assertEquals( 2, r.get( 1 ), 0.0000001 );
		assertEquals( 5, r.taille() );
	}

	@Test
	public void testAgrandir_5() {
		double [] t = { 0, 0, 1, 0, 1 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 2, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 2 );
		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, v.get( 2 ), 0.0000001 );
		assertEquals( 1, v.get( 3 ), 0.0000001 );
		assertEquals( 0, v.get( 4 ), 0.0000001 );
		assertEquals( 1, v.get( 5 ), 0.0000001 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 0, r.get( 2 ), 0.0000001 );
		assertEquals( 2, r.get( 3 ), 0.0000001 );
		assertEquals( 0, r.get( 4 ), 0.0000001 );
		assertEquals( 2, r.get( 5 ), 0.0000001 );
		assertEquals( 5, r.taille() );
	}

	@Test
	public void testAgrandir_6() {
		double [] t = { 0, 0, 1, 0, 1 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 2, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( -2 );
		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, v.get( 2 ), 0.0000001 );
		assertEquals( 1, v.get( 3 ), 0.0000001 );
		assertEquals( 0, v.get( 4 ), 0.0000001 );
		assertEquals( 1, v.get( 5 ), 0.0000001 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 0, r.get( 2 ), 0.0000001 );
		assertEquals( -2, r.get( 3 ), 0.0000001 );
		assertEquals( 0, r.get( 4 ), 0.0000001 );
		assertEquals( -2, r.get( 5 ), 0.0000001 );
		assertEquals( 5, r.taille() );
	}

	@Test
	public void testAgrandir_7() {
		double [] t = { 0, 0, 1, 0, 1 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 2, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 0 );
		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, v.get( 2 ), 0.0000001 );
		assertEquals( 1, v.get( 3 ), 0.0000001 );
		assertEquals( 0, v.get( 4 ), 0.0000001 );
		assertEquals( 1, v.get( 5 ), 0.0000001 );
		assertEquals( 0, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 0, r.get( 2 ), 0.0000001 );
		assertEquals( 0, r.get( 3 ), 0.0000001 );
		assertEquals( 0, r.get( 4 ), 0.0000001 );
		assertEquals( 0, r.get( 5 ), 0.0000001 );
		assertEquals( 5, r.taille() );
	}

	@Test
	public void testAgrandir_8() {
		double [] t = { 2, 3, 4, 5, 6 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 5, v.nbrElementNonZero() );
		VecteurCreux r = v.agrandir( 0 );
		assertEquals( 5, v.nbrElementNonZero() );
		assertEquals( 2, v.get( 1 ), 0.0000001 );
		assertEquals( 3, v.get( 2 ), 0.0000001 );
		assertEquals( 4, v.get( 3 ), 0.0000001 );
		assertEquals( 5, v.get( 4 ), 0.0000001 );
		assertEquals( 6, v.get( 5 ), 0.0000001 );
		assertEquals( 0, r.nbrElementNonZero() );
		assertEquals( 0, r.get( 1 ), 0.0000001 );
		assertEquals( 0, r.get( 2 ), 0.0000001 );
		assertEquals( 0, r.get( 3 ), 0.0000001 );
		assertEquals( 0, r.get( 4 ), 0.0000001 );
		assertEquals( 0, r.get( 5 ), 0.0000001 );
		assertEquals( 5, r.taille() );
	}

	@Test
	public void testEqualsObject_1() {
		VecteurCreux v1 = new VecteurCreux( 100 );
		
		assertFalse( v1.equals( null ) );
	}

	@Test
	public void testEqualsObject_2() {
		VecteurCreux v1 = new VecteurCreux( 100 );
		VecteurCreux v2 = new VecteurCreux( 10 );
		
		assertFalse( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_3() {
		VecteurCreux v1 = new VecteurCreux( 100 );
		VecteurCreux v2 = new VecteurCreux( 100 );
		
		assertTrue( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_4() {
		double [] t1 = { 2, 3, 4, 5, 6 };
		double [] t2 = { 2, 3, 4, 5, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_5() {
		double [] t1 = { 2, 0, 0, 5, 6 };
		double [] t2 = { 2, 0, 0, 5, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_6() {
		double [] t1 = { 2, 0, 0, 5, 6 };
		double [] t2 = { 2, 0, 5, 6, 0 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_7() {
		double [] t1 = { 2, 0, 0, 5, 6 };
		double [] t2 = { 2, 0, 5, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_8() {
		double [] t1 = { 0, 2, 0, 0, 5, 6, 0 };
		double [] t2 = { 0, 2, 0, 0, 5, 6, 0 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.equals( v2 ) );
	}

	@Test
	public void testEqualsObject_9() {
		double [] t1 = { 0, 2, 0, 0, 5, 6 };
		double [] t2 = { 0, 2, 0, 5, 6, 0 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.equals( v2 ) );
	}

	@Test
	public void testEstAntiParallelA_1() {
		double [] t1 = { 2, 3, 4, 5, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { -2, -3, -4, -5, -6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_2() {
		double [] t1 = { 0, -3, 0, 0, -6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 3, 0, 0, 6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_3() {
		double [] t1 = { 0, 2, 0, 0, -6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, -1, 0, 0, 3 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_4() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, -2, 0, 0, -3 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_5() {
		double [] t1 = { 0, 0, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, -2, 0, 0, -6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_6() {
		double [] t1 = { 0, 0, 0, 0, -6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 2, 0, 0, 0 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_7() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 2, 0, 0, 6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estAntiParallelA( v2 ) );
	}
	
	@Test
	public void testEstAntiParallelA_8() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 0, -2, -6, 0 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estAntiParallelA( v2 ) );
	}

	@Test
	public void testEstOpposeA_1() {
		double [] t1 = { 2, 3, 4, 5, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { -2, -3, -4, -5, -6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estOpposeA( v2 ) );
	}

	@Test
	public void testEstOpposeA_2() {
		double [] t1 = { 0, 3, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, -3, 0, 0, -6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estOpposeA( v2 ) );
	}

	@Test
	public void testEstOpposeA_3() {
		double [] t1 = { 0, 4, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, -2, 0, 0, -3 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estOpposeA( v2 ) );
	}

	@Test
	public void testEstOpposeA_4() {
		double [] t1 = { 0, 4, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 4, 0, 0, 6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estOpposeA( v2 ) );
	}

	@Test
	public void testEstParallelA_1() {
		double [] t1 = { 2, 3, 4, 5, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 2, 3, 4, 5, 6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_2() {
		double [] t1 = { 0, 3, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 3, 0, 0, 6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_3() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 1, 0, 0, 3 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertTrue( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_4() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 2, 0, 0, 3 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_5() {
		double [] t1 = { 0, 0, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 2, 0, 0, 6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_6() {
		double [] t1 = { 0, 0, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 2, 0, 0, 0 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_7() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, -2, 0, 0, -6 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estParallelA( v2 ) );
	}

	@Test
	public void testEstParallelA_8() {
		double [] t1 = { 0, 2, 0, 0, 6 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		double [] t2 = { 0, 0, 2, 6, 0 };
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertFalse( v1.estParallelA( v2 ) );
	}

	@Test
	public void testGet_1() {
		double [] t = { 0, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 0, v.get( 1 ), 0.0000001 );
	}

	@Test
	public void testGet_2() {
		double [] t = { 0, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 0, v.get( 4 ), 0.0000001 );
	}

	@Test
	public void testGet_3() {
		double [] t = { 2, 4, 6, 8, 10 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 2, v.get( 1 ), 0.0000001 );
	}

	@Test
	public void testGet_4() {
		double [] t = { 2, 4, 6, 8, 10 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 8, v.get( 4 ), 0.0000001 );
	}

	@Test
	public void testGet_5() {
		double [] t = { 0, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		try {
			assertEquals( 0, v.get( 0 ), 0.0000001 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testGet_6() {
		double [] t = { 0, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		try {
			assertEquals( 0, v.get( -1 ), 0.0000001 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testGet_7() {
		double [] t = { 0, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		try {
			assertEquals( 0, v.get( 6 ), 0.0000001 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testGet_8() {
		double [] t = { 0, 6, 0, 0, 10 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 0, v.get( 4 ), 0.0000001 );
	}

	@Test
	public void testGet_9() {
		double [] t = { 0, 6, 0, 0, 10 };
		VecteurCreux v = new VecteurCreux( t );

		assertEquals( 6, v.get( 2 ), 0.0000001 );
	}

	@Test
	public void testIterator_1() {
		VecteurCreux v = new VecteurCreux( 5 );

		Iterator< Double > it = v.iterator();
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertFalse( it.hasNext() );
	}

	@Test
	public void testIterator_2() {
		double [] t = { 1, 3, 5, 7, 9 };
		VecteurCreux v = new VecteurCreux( t );

		Iterator< Double > it = v.iterator();
		assertTrue( it.hasNext() );
		assertEquals( 1, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 3, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 5, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 7, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 9, it.next(), 0.0000001 );
		assertFalse( it.hasNext() );
	}

	@Test
	public void testIterator_3() {
		double [] t = { 1, 0, 5, 0, 9 };
		VecteurCreux v = new VecteurCreux( t );

		Iterator< Double > it = v.iterator();
		assertTrue( it.hasNext() );
		assertEquals( 1, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 5, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 9, it.next(), 0.0000001 );
		assertFalse( it.hasNext() );
	}

	@Test
	public void testIterator_4() {
		double [] t = { 0, 0, 5, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );

		Iterator< Double > it = v.iterator();
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 5, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertTrue( it.hasNext() );
		assertEquals( 0, it.next(), 0.0000001 );
		assertFalse( it.hasNext() );
	}

	@Test
	public void testMultiplicationScalaire_1() {
		VecteurCreux v1 = new VecteurCreux( 10 );
		VecteurCreux v2 = new VecteurCreux( 10 );
		
		assertEquals( 0.0, v1.multiplicationScalaire(v2), 0.0000001 );
	}

	@Test
	public void testMultiplicationScalaire_2() {
		double [] t1 = { 2, 4, 6 };
		double [] t2 = { 1, 3, 5 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertEquals( 44, v1.multiplicationScalaire(v2), 0.0000001 );
	}

	@Test
	public void testMultiplicationScalaire_3() {
		double [] t1 = { 0, 4, 0 };
		double [] t2 = { 1, 3, 5 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertEquals( 12, v1.multiplicationScalaire(v2), 0.0000001 );
	}

	@Test
	public void testMultiplicationScalaire_4() {
		double [] t1 = { 0, 4, 0 };
		double [] t2 = { 1, 0, 5 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		assertEquals( 0, v1.multiplicationScalaire(v2), 0.0000001 );
	}

	@Test
	public void testMultiplicationScalaire_5() {
		double [] t1 = { 0, 4, 0, 0 };
		double [] t2 = { 1, 0, 5 };
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		try {
			v1.multiplicationScalaire(v2);
			fail( "devrait lancer une exception." );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testNbrElementNonZero() {
		// tester implicitement dans les autres methodes.
	}

	@Test
	public void testNormeEucledienne_1() {
		VecteurCreux v = new VecteurCreux( 10 );
		
		assertEquals( 0.0, v.normeEucledienne(), 0.0000001 );
	}

	@Test
	public void testNormeEucledienne_2() {
		double [] t = { 1 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 1.0, v.normeEucledienne(), 0.0000001 );
	}

	@Test
	public void testNormeEucledienne_3() {
		double [] t = { 3, 4 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 5.0, v.normeEucledienne(), 0.0000001 );
	}

	@Test
	public void testNormeEucledienne_4() {
		double [] t = { 0, 3, 0, 4, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 5.0, v.normeEucledienne(), 0.0000001 );
	}

	@Test
	public void testOppose_1() {
		VecteurCreux v = new VecteurCreux( 100 );

		VecteurCreux r = v.oppose();
		
		assertEquals( 0, r.nbrElementNonZero() );
		assertEquals( 100, r.taille() );
	}

	@Test
	public void testOppose_2() {
		double [] t = { 1 };
		VecteurCreux v = new VecteurCreux( t );

		VecteurCreux r = v.oppose();
		
		assertEquals( 1, r.nbrElementNonZero() );
		assertEquals( 1, r.taille() );
		assertEquals( -1, r.get( 1 ), 0.0000001 );
	}

	@Test
	public void testOppose_3() {
		double [] t = { 1, 2, 3, 4, 5 };
		VecteurCreux v = new VecteurCreux( t );

		VecteurCreux r = v.oppose();
		
		assertEquals( 5, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( -1, r.get( 1 ), 0.0000001 );
		assertEquals( -2, r.get( 2 ), 0.0000001 );
		assertEquals( -3, r.get( 3 ), 0.0000001 );
		assertEquals( -4, r.get( 4 ), 0.0000001 );
		assertEquals( -5, r.get( 5 ), 0.0000001 );
	}

	@Test
	public void testOppose_4() {
		double [] t = { 1, -2, 0, 0, 5 };
		VecteurCreux v = new VecteurCreux( t );

		VecteurCreux r = v.oppose();
		
		assertEquals( 3, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( -1, r.get( 1 ), 0.0000001 );
		assertEquals( 2, r.get( 2 ), 0.0000001 );
		assertEquals( 0, r.get( 3 ), 0.0000001 );
		assertEquals( 0, r.get( 4 ), 0.0000001 );
		assertEquals( -5, r.get( 5 ), 0.0000001 );
	}

	@Test
	public void testSet_1() {
		VecteurCreux v = new VecteurCreux( 10 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		v.set( 1, 4.0 );
		assertEquals( 1, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 1 ), 0.0000001 );
		assertEquals( 0, v.get( 2 ), 0.0000001 );
	}

	@Test
	public void testSet_2() {
		VecteurCreux v = new VecteurCreux( 10 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 2 ), 0.0000001 );
		v.set( 2, 4.0 );
		assertEquals( 1, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		assertEquals( 0, v.get( 3 ), 0.0000001 );
	}

	@Test
	public void testSet_3() {
		VecteurCreux v = new VecteurCreux( 10 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 10 ), 0.0000001 );
		v.set( 10, 4.0 );
		assertEquals( 1, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 9 ), 0.0000001 );
		assertEquals( 4, v.get( 10 ), 0.0000001 );
	}

	@Test
	public void testSet_4() {
		VecteurCreux v = new VecteurCreux( 10 );
		
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 10 ), 0.0000001 );
		v.set( 10, 0.0 );
		assertEquals( 0, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 9 ), 0.0000001 );
		assertEquals( 0, v.get( 10 ), 0.0000001 );
	}

	@Test
	public void testSet_5() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 2, v.get( 1 ), 0.0000001 );
		v.set( 1, 4.0 );
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 1 ), 0.0000001 );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
	}

	@Test
	public void testSet_6() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		v.set( 2, 7.0 );
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 2, v.get( 1 ), 0.0000001 );
		assertEquals( 7, v.get( 2 ), 0.0000001 );
		assertEquals( 6, v.get( 3 ), 0.0000001 );
	}

	@Test
	public void testSet_7() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 20, v.get( 10 ), 0.0000001 );
		v.set( 10, 5.0 );
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 18, v.get( 9 ), 0.0000001 );
		assertEquals( 5, v.get( 10 ), 0.0000001 );
	}

	@Test
	public void testSet_8() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 2, v.get( 1 ), 0.0000001 );
		v.set( 1, 0.0 );
		assertEquals( 9, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		
		assertEquals( 10, v.taille() );
	}

	@Test
	public void testSet_9() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 20, v.get( 10 ), 0.0000001 );
		v.set( 10, 0.0 );
		assertEquals( 9, v.nbrElementNonZero() );
		assertEquals( 18, v.get( 9 ), 0.0000001 );
		assertEquals( 0, v.get( 10 ), 0.0000001 );
		
		assertEquals( 10, v.taille() );
	}

	@Test
	public void testSet_10() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 10, v.get( 5 ), 0.0000001 );
		v.set( 5, 0.0 );
		assertEquals( 9, v.nbrElementNonZero() );
		assertEquals( 8, v.get( 4 ), 0.0000001 );
		assertEquals( 0, v.get( 5 ), 0.0000001 );
		assertEquals( 12, v.get( 6 ), 0.0000001 );
		
		assertEquals( 10, v.taille() );
	}

	@Test
	public void testSet_11() {
		double [] t = { 2, 0, 0, 0, 10, 0, 0, 0, 0, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 2, v.get( 1 ), 0.0000001 );
		v.set( 1, 0.0 );
		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		assertEquals( 0, v.get( 2 ), 0.0000001 );
		
		assertEquals( 10, v.taille() );
	}

	@Test
	public void testSet_12() {
		double [] t = { 2, 0, 0, 0, 10, 0, 0, 0, 0, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 20, v.get( 10 ), 0.0000001 );
		v.set( 10, 0.0 );
		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 9 ), 0.0000001 );
		assertEquals( 0, v.get( 10 ), 0.0000001 );
		
		assertEquals( 10, v.taille() );
	}

	@Test
	public void testSet_13() {
		double [] t = { 2, 0, 0, 0, 10, 0, 0, 0, 0, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 10, v.get( 5 ), 0.0000001 );
		v.set( 5, 0.0 );
		assertEquals( 2, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 4 ), 0.0000001 );
		assertEquals( 0, v.get( 5 ), 0.0000001 );
		assertEquals( 0, v.get( 6 ), 0.0000001 );
		
		assertEquals( 10, v.taille() );
	}

	@Test
	public void testSet_14() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( 0, 1 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_15() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( -1, 1 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_16() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( 11, 1 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_17() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( 500, 1 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_18() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( 0, 0 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_19() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( -1, 0 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_20() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( 11, 0 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_21() {
		VecteurCreux v = new VecteurCreux( 10 );

		try {
			v.set( 500, 0 );
			fail( "Devrait lancer l'exception" );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testSet_22() {
		double [] t = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		v.set( 2, -7.0 );
		assertEquals( 10, v.nbrElementNonZero() );
		assertEquals( 2, v.get( 1 ), 0.0000001 );
		assertEquals( -7, v.get( 2 ), 0.0000001 );
		assertEquals( 6, v.get( 3 ), 0.0000001 );
	}

	@Test
	public void testSet_23() {
		double [] t = { 0, 4, 0, 0, 0, 12, 0, 0, 18, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 5 ), 0.0000001 );
		v.set( 5, 1 );
		assertEquals( 4, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		assertEquals( 0, v.get( 4 ), 0.0000001 );
		assertEquals( 1, v.get( 5 ), 0.0000001 );
		assertEquals( 12, v.get( 6 ), 0.0000001 );
		assertEquals( 18, v.get( 9 ), 0.0000001 );
	}

	@Test
	public void testSet_24() {
		double [] t = { 0, 4, 0, 0, 0, 12, 0, 0, 18, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 7 ), 0.0000001 );
		v.set( 7, 1 );
		assertEquals( 4, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		assertEquals( 12, v.get( 6 ), 0.0000001 );
		assertEquals( 1, v.get( 7 ), 0.0000001 );
		assertEquals( 0, v.get( 8 ), 0.0000001 );
		assertEquals( 18, v.get( 9 ), 0.0000001 );
	}

	@Test
	public void testSet_25() {
		double [] t = { 0, 4, 0, 0, 0, 12, 0, 0, 18, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 1 ), 0.0000001 );
		v.set( 1, 1 );
		assertEquals( 4, v.nbrElementNonZero() );
		assertEquals( 1, v.get( 1 ), 0.0000001 );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		assertEquals( 12, v.get( 6 ), 0.0000001 );
		assertEquals( 18, v.get( 9 ), 0.0000001 );
	}

	@Test
	public void testSet_26() {
		double [] t = { 0, 4, 0, 0, 0, 12, 0, 0, 18, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		assertEquals( 3, v.nbrElementNonZero() );
		assertEquals( 0, v.get( 10 ), 0.0000001 );
		v.set( 10, 1 );
		assertEquals( 4, v.nbrElementNonZero() );
		assertEquals( 4, v.get( 2 ), 0.0000001 );
		assertEquals( 12, v.get( 6 ), 0.0000001 );
		assertEquals( 18, v.get( 9 ), 0.0000001 );
		assertEquals( 1, v.get( 10 ), 0.0000001 );
	}

	@Test
	public void testSoustraction_1() {
		double [] t1 = {2};
		double [] t2 = {1};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.soustraction( v2 );
		assertEquals( 1, r.nbrElementNonZero() );
		assertEquals( 1, r.taille() );
		assertEquals( 1, r.get(1), 0.0000001 );
	}

	@Test
	public void testSoustraction_2() {
		double [] t1 = {2, 2};
		double [] t2 = {1, 3};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.soustraction( v2 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 2, r.taille() );
		assertEquals( 1, r.get(1), 0.0000001 );
		assertEquals( -1, r.get(2), 0.0000001 );
	}

	@Test
	public void testSoustraction_3() {
		double [] t1 = {2, 2, 0, 0, 0};
		double [] t2 = {1, 3, 0, 0, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.soustraction( v2 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( 1, r.get(1), 0.0000001 );
		assertEquals( -1, r.get(2), 0.0000001 );
		assertEquals( 0, r.get(3), 0.0000001 );
		assertEquals( 0, r.get(4), 0.0000001 );
		assertEquals( 0, r.get(5), 0.0000001 );
	}

	@Test
	public void testSoustraction_4() {
		double [] t1 = {2, 4, 0, 0, 0};
		double [] t2 = {1, 0, 0, 3, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.soustraction( v2 );
		assertEquals( 3, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( 1, r.get(1), 0.0000001 );
		assertEquals( 4, r.get(2), 0.0000001 );
		assertEquals( 0, r.get(3), 0.0000001 );
		assertEquals( -3, r.get(4), 0.0000001 );
		assertEquals( 0, r.get(5), 0.0000001 );
	}

	@Test
	public void testSoustraction_5() {
		double [] t1 = {1, -4, 0, 0, 0};
		double [] t2 = {1, 0, 0, -3, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		VecteurCreux r = v1.soustraction( v2 );
		assertEquals( 2, r.nbrElementNonZero() );
		assertEquals( 5, r.taille() );
		assertEquals( 0, r.get(1), 0.0000001 );
		assertEquals( -4, r.get(2), 0.0000001 );
		assertEquals( 0, r.get(3), 0.0000001 );
		assertEquals( 3, r.get(4), 0.0000001 );
		assertEquals( 0, r.get(5), 0.0000001 );
	}

	@Test
	public void testSoustraction_6() {
		double [] t1 = {1, -4, 0, 0, 0};
		double [] t2 = {-1, 0, 0, -3, 0, 0};
		VecteurCreux v1 = new VecteurCreux( t1 );
		VecteurCreux v2 = new VecteurCreux( t2 );
		
		try {
			v1.soustraction( v2 );
			fail( "devrait lancer l'exception." );
		} catch( IndexOutOfBoundsException e ) {
		}
	}

	@Test
	public void testTaille() {
		// teste implicitement dans les autres methodes.
	}

	@Test
	public void testUnitaire_1() {
		double [] t = { 1 };
		VecteurCreux v = new VecteurCreux( t );
		
		VecteurCreux u = v.unitaire();
		assertEquals( 1, u.nbrElementNonZero() );
		assertEquals( 1, u.taille() );
		assertEquals( 1.0, u.get( 1 ), 0.0000001 );
	}

	@Test
	public void testUnitaire_2() {
		double [] t = { 1, 0, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		VecteurCreux u = v.unitaire();
		assertEquals( 1, u.nbrElementNonZero() );
		assertEquals( 5, u.taille() );
		assertEquals( 1.0, u.get( 1 ), 0.0000001 );
		assertEquals( 0.0, u.get( 2 ), 0.0000001 );
		assertEquals( 0.0, u.get( 3 ), 0.0000001 );
		assertEquals( 0.0, u.get( 4 ), 0.0000001 );
		assertEquals( 0.0, u.get( 5 ), 0.0000001 );
	}

	@Test
	public void testUnitaire_3() {
		double [] t = { 0, 5, 0, 0, 0 };
		VecteurCreux v = new VecteurCreux( t );
		
		VecteurCreux u = v.unitaire();
		assertEquals( 1, u.nbrElementNonZero() );
		assertEquals( 5, u.taille() );
		assertEquals( 0.0, u.get( 1 ), 0.0000001 );
		assertEquals( 1.0, u.get( 2 ), 0.0000001 );
		assertEquals( 0.0, u.get( 3 ), 0.0000001 );
		assertEquals( 0.0, u.get( 4 ), 0.0000001 );
		assertEquals( 0.0, u.get( 5 ), 0.0000001 );
	}

	@Test
	public void testUnitaire_4() {
		double [] t = { 0, 1, 0, 1, 0 };
		VecteurCreux v = new VecteurCreux( t );
		double r = 1.0 / Math.sqrt( 2.0 );
		
		VecteurCreux u = v.unitaire();
		assertEquals( 2, u.nbrElementNonZero() );
		assertEquals( 5, u.taille() );
		assertEquals( 0.0, u.get( 1 ), 0.0000001 );
		assertEquals( r, u.get( 2 ), 0.0000001 );
		assertEquals( 0.0, u.get( 3 ), 0.0000001 );
		assertEquals( r, u.get( 4 ), 0.0000001 );
		assertEquals( 0.0, u.get( 5 ), 0.0000001 );
	}
}

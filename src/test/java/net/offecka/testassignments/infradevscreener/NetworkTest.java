package net.offecka.testassignments.infradevscreener;

import org.junit.Assert;
import org.junit.Test;

public class NetworkTest {
	@Test
	public void testConstructor() {
		try {
			new Network(-1);
			Assert.fail();
		} catch (final IllegalArgumentException ex) {
			//As expected
		}

		try {
			new Network(0);
			Assert.fail();
		} catch (final IllegalArgumentException ex) {
			//As expected
		}

		Assert.assertEquals(1, new Network(1).size);
	}

	@Test
	public void testBadConnect() {
		Network network = new Network(10);

		try {
			network.connect(10, 11);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(-1, -2);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(-1, 10);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(10, -1);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(9, -1);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(9, 10);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(10, 9);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.connect(-1, 10);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}
	}

	@Test
	public void testBadQuery() {
		Network network = new Network(10);

		try {
			network.query(10, 11);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(-1, -2);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(-1, 10);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(10, -1);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(9, -1);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(9, 10);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(10, 9);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}

		try {
			network.query(-1, 10);
			Assert.fail();
		} catch (final Exception ex) {
			//As expected
		}
	}

	@Test
	public void testConnect() {
		Network network = new Network(10);
		Assert.assertTrue(network.adjacentMatrix.isEmpty());

		network.connect(5, 9);

		Assert.assertEquals(2, network.adjacentMatrix.size());
		Assert.assertEquals(1, network.adjacentMatrix.get(5).size());
		Assert.assertEquals(1, network.adjacentMatrix.get(9).size());
		Assert.assertTrue(network.adjacentMatrix.get(5).contains(9));
		Assert.assertTrue(network.adjacentMatrix.get(9).contains(5));

		network.connect(9, 5);

		Assert.assertEquals(2, network.adjacentMatrix.size());
		Assert.assertEquals(1, network.adjacentMatrix.get(5).size());
		Assert.assertEquals(1, network.adjacentMatrix.get(9).size());
		Assert.assertTrue(network.adjacentMatrix.get(5).contains(9));
		Assert.assertTrue(network.adjacentMatrix.get(9).contains(5));

		network.connect(8, 8);

		Assert.assertEquals(2, network.adjacentMatrix.size());
		Assert.assertEquals(1, network.adjacentMatrix.get(5).size());
		Assert.assertEquals(1, network.adjacentMatrix.get(9).size());
		Assert.assertTrue(network.adjacentMatrix.get(5).contains(9));
		Assert.assertTrue(network.adjacentMatrix.get(9).contains(5));

		network.connect(2, 1);

		Assert.assertEquals(4, network.adjacentMatrix.size());
		Assert.assertEquals(1, network.adjacentMatrix.get(5).size());
		Assert.assertEquals(1, network.adjacentMatrix.get(9).size());
		Assert.assertTrue(network.adjacentMatrix.get(5).contains(9));
		Assert.assertTrue(network.adjacentMatrix.get(9).contains(5));
		Assert.assertEquals(1, network.adjacentMatrix.get(2).size());
		Assert.assertEquals(1, network.adjacentMatrix.get(1).size());
		Assert.assertTrue(network.adjacentMatrix.get(2).contains(1));
		Assert.assertTrue(network.adjacentMatrix.get(1).contains(2));

		network.connect(5, 1);

		Assert.assertEquals(4, network.adjacentMatrix.size());
		Assert.assertEquals(2, network.adjacentMatrix.get(5).size());
		Assert.assertTrue(network.adjacentMatrix.get(5).contains(9));
		Assert.assertTrue(network.adjacentMatrix.get(5).contains(1));
		Assert.assertEquals(1, network.adjacentMatrix.get(9).size());
		Assert.assertTrue(network.adjacentMatrix.get(9).contains(5));
		Assert.assertEquals(2, network.adjacentMatrix.get(1).size());
		Assert.assertTrue(network.adjacentMatrix.get(1).contains(2));
		Assert.assertTrue(network.adjacentMatrix.get(1).contains(5));
		Assert.assertEquals(1, network.adjacentMatrix.get(2).size());
		Assert.assertTrue(network.adjacentMatrix.get(2).contains(1));
	}

	@Test
	public void testQuery() {
		Network network = new Network(8);
		network.connect(0, 5);
		network.connect(0, 1);
		network.connect(5, 1);
		network.connect(1, 3);
		network.connect(4, 7);

		int[][] edges = new int[][] {
			new int[]{0, 1},
			new int[]{0, 3},
			new int[]{0, 5},
			new int[]{1, 3},
			new int[]{1, 5},
			new int[]{3, 5},
			new int[]{4, 7}
		};

		this.testQueries(network, edges);
	}

	@Test
	public void testBigCycle() {
		Network network = new Network(8);
		network.connect(0, 1);
		network.connect(1, 3);
		network.connect(3, 7);
		network.connect(7, 4);
		network.connect(4, 5);
		network.connect(5, 0);

		int[][] edges = new int[][] {
			new int[]{0, 1},
			new int[]{0, 3},
			new int[]{0, 7},
			new int[]{0, 4},
			new int[]{0, 5},

			new int[]{1, 3},
			new int[]{1, 7},
			new int[]{1, 4},
			new int[]{1, 5},
			new int[]{1, 0},

			new int[]{3, 7},
			new int[]{3, 4},
			new int[]{3, 5},
			new int[]{3, 0},
			new int[]{3, 1},

			new int[]{7, 4},
			new int[]{7, 5},
			new int[]{7, 0},
			new int[]{7, 1},
			new int[]{7, 3},

			new int[]{4, 5},
			new int[]{4, 0},
			new int[]{4, 1},
			new int[]{4, 3},
			new int[]{4, 7},

			new int[]{5, 0},
			new int[]{5, 1},
			new int[]{5, 3},
			new int[]{5, 7},
			new int[]{5, 4},
		};

		this.testQueries(network, edges);
	}

	private void testQueries(final Network network, final int[][] expectations) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boolean expectation = i == j;
				if (!expectation) {
					for (final int[] edge : expectations) {
						if (edge[0] == i && edge[1] == j || edge[0] == j && edge[1] == i) {
							expectation = true;
							break;
						}
					}
				}

				boolean result = network.query(i, j);
				Assert.assertEquals(i + " and " + j + " should be " + (expectation ? "connected" : "disconnected"), expectation, result);
			}
		}
	}
}

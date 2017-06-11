package net.offecka.testassignments.infradevscreener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The implementation is NOT thread-safe.
 */
public class Network implements Graph {
	public final int size;

	/*
	 * Several options of the graph model implementation were considered.
	 * This adjacent matrix is not performance optimal for searches,
	 * and since the graph is undirectional it will contain duplicates.
	 *
	 * The duplicates help to simplify searches of parent nodes.
	 * For example, if we have two connected nodes 1 and 2,
	 * storing only the half of the matrix
	 * would cause additional efforts to find out if 2 is joined to 1.
   *
   * An array based half-matrix would be more performance optimal,
   * but may consume memory that otherwise would be free for missing connections.
	 */
	final Map<Integer, Set<Integer>> adjacentMatrix = new HashMap<>();

	/**
	 * Initializes the graph.
	 *
	 * @param size - amount of nodes in the graph.
	 */
	public Network(final int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("The graph size should be a positive integer, that is greater than 0");
		}
		this.size = size;
	}

	public void connect(final int nodeId, final int otherNodeId) {
		this.checkNodeId(nodeId);
		this.checkNodeId(otherNodeId);

		if (nodeId != otherNodeId) {
			this.adjacentMatrix.computeIfAbsent(nodeId, k -> new HashSet<>()).add(otherNodeId);
			/*
			 * This is not memory usage optimal,
			 * but simplifies the implementation.
			 */
			this.adjacentMatrix.computeIfAbsent(otherNodeId, k -> new HashSet<>()).add(nodeId);
		}
	}

	@Override
	public boolean query(final int nodeId, final int otherNodeId) {
		this.checkNodeId(nodeId);
		this.checkNodeId(otherNodeId);

		boolean found = nodeId == otherNodeId;
		if (!found) {
			/*
	     * This additional list allows using immutable node models.
	     * (In this simple case, just integers.)
	     * Otherwise we would need to store the visited flag somehow.
	     * Besides, the visited flag should be cleared at the end,
	     * and to avoid the traversal across all nodes,
	     * we would anyway need to store a list of visited nodes.
	     */
			ArrayList<Integer> visitedNodeIds = new ArrayList<>();
			LinkedList<Integer> stack = new LinkedList<>();

			visitedNodeIds.add(nodeId);
			stack.add(nodeId);

			while (!stack.isEmpty()) {
				int topNodeId = stack.getLast();

				Integer childNodeId = this.findNotVisitedChild(topNodeId, visitedNodeIds);
				if (childNodeId == null) {
					stack.removeLast();
				} else {
					if (childNodeId == otherNodeId) {
						found = true;
						break;
					}

					visitedNodeIds.add(childNodeId);
					stack.addLast(childNodeId);
				}
			}
		}

		return found;
	}

	private Integer findNotVisitedChild(final int topNodeId, final List<Integer> visitedNodeIds) {
		Integer childNodeId = null;

		Set<Integer> ids = this.adjacentMatrix.get(topNodeId);
		if (ids != null) {
			for (final int id : ids) {
				if (!visitedNodeIds.contains(id)) {
					childNodeId = id;
					break;
				}
			}
		}

		return childNodeId;
	}

	private void checkNodeId(final int nodeId) {
		if (nodeId < 0) {
			throw new IllegalArgumentException("Invalid node id " + nodeId);
		}
		if (nodeId >= this.size) {
			throw new IllegalArgumentException("No node with id " + nodeId + " found in the graph of " + this.size);
		}
	}
}

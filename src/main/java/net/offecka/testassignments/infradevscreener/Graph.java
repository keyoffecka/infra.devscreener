package net.offecka.testassignments.infradevscreener;

/**
 * Represents an undirectional graph with nodes identified by ids, where
 * 1. id is an integer and 0 <= id < n;
 * 2. n is the number of nodes in the graph and 0 < n <= MAX_VALUE
 *
 * Any two nodes may be connected any number of times,
 * connecting already joined nodes is valid but makes no additional effect.
 *
 * Connecting a node to itself is valid, and more,
 * any node is considered to be always connected to itself,
 * disregarding whether it was connected explicitly or not.
 *
 */
public interface Graph {
	/**
	 * Connects two nodes by their ids.
	 *
	 * @throws IllegalArgumentException if the ids are not valid within the graph.
	 */
	void connect(int nodeId, int otherNodeId);

	/**
	 * Checks if two nodes are connected.
	 *
	 * @return true if the nodes are connected; always returns true if the node ids are the same.
	 *
	 * @throws IllegalArgumentException if the ids are not valid within the graph.
	 */
	boolean query(int nodeId, int otherNodeId);
}

import java.util.HashMap;

class Trie {
	Node rootNode = new Node();

	void insert(String str) {
		Node node = this.rootNode;

		for (int i = 0; i < str.length(); i++) {
			node = node.childNode.computeIfAbsent(str.charAt(i), Key -> new Node());
		}
		node.endWord = true;
	}

	boolean search(String str) {
		Node node = this.rootNode;

		for (int i = 0; i < str.length(); i++) {
			node = node.childNode.getOrDefault(str.charAt(i), null);
			if (node == null) {
				return false;
			}
		}

		return node.endWord;
	}

	private static class Node {
		HashMap<Character, Node> childNode = new HashMap<>();
		boolean endWord = false;
	}
}
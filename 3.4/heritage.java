/*
ID: yuzhou.1
LANG: JAVA
TASK: heritage
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class heritage
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		
		String inOrder = in.readLine().trim();
		String preOrder = in.readLine().trim();
		int len = inOrder.length();
		in.close();
		
		Node root = genTree(inOrder, preOrder, 0, 0, len);
		
		// System.out.println(root.inOrder());
		// System.out.println(root.preOrder());
		out.println(root.postOrder());
		out.close();
	}
	
	public static Node genTree(String inOrder, String preOrder, int inStart, int preStart, int len)
	{
		if (len <= 0 || inStart >= inOrder.length() || preStart >= preOrder.length()) return null;
		Node root = new Node(preOrder.charAt(preStart));
		int rootIdx = inOrder.indexOf(root.name);
		
		Node left = genTree(inOrder, preOrder, inStart, preStart + 1, rootIdx - inStart);
		Node right = genTree(inOrder, preOrder, rootIdx + 1, preStart + 1 + rootIdx - inStart, inStart + len - rootIdx - 1);
		
		root.addLeft(left);
		root.addRight(right);
		return root;
	}
	
	static class Node
	{
		public Node left;
		public Node right;
		public Node parent;
		public char name;
		
		public Node(char n)
		{
			left = null;
			right = null;
			name = n;
		}
		
		public void addLeft(Node l)
		{
			if (l == null) return;
			left = l;
			l.parent = this;
			// System.out.println(l.name + " left " + name);
		}
		
		public void addRight(Node r)
		{
			if (r == null) return;
			right = r;
			r.parent = this;
			// System.out.println(r.name + " right " + name);
		}
		
		public String inOrder()
		{
			String toReturn = "";
			if (left != null) toReturn += left.inOrder();
			toReturn += String.valueOf(name);
			if (right != null) toReturn += right.inOrder();
			return toReturn;
		}
		
		public String preOrder()
		{
			String toReturn = String.valueOf(name);
			if (left != null) toReturn += left.preOrder();
			if (right != null) toReturn += right.preOrder();
			return toReturn;
		}
		
		public String postOrder()
		{
			String toReturn = "";
			if (left != null) toReturn += left.postOrder();
			if (right != null) toReturn += right.postOrder();
			return toReturn + String.valueOf(name);
		}
	}
}

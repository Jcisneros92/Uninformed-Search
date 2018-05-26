import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class find_route {
	
	//private static String pathname = "/home/j/jx/jxc6527/testing";
	private static String textFile;
	private static String [] values;
	
	
	private static void checkText (ArrayList<String> visited, ArrayList<Node> nodeList, String currentNodeName, Node currentNode) throws FileNotFoundException,IOException {
		//File infile1 = new File(pathname+"/"+textFile);
		String line = "";
		BufferedReader br = new BufferedReader (new FileReader(textFile));
		
		while ((line = br.readLine()) != null) {
			values = line.split(" ");
			
			if((values[0].equals("END")) && (values[1].equals("OF")) && (values[2].equals("INPUT")))
			{
				break;
			}
			
			if(values[0].equals(currentNodeName)) {
				
				boolean found = false;
				
				for(int i = 0; i < visited.size(); i++) {
					if(visited.get(i).equals(values[1])) {
						found = true;
					}
				}
				
				if(found == true) {
				}
				
				else {
					int tempTot = currentNode.getTot() + Integer.parseInt(values[2]);
					int tempDepth = currentNode.getDepth();
					Node tempNode = new Node(values[1], tempTot, tempDepth+1);
					tempNode.prevNode = currentNode;
					addNodeToList(nodeList, tempNode);
					
				}
				
			}
			
			else if(values[1].equals(currentNodeName)) {
				
				boolean found = false;
				
				for(int i = 0; i < visited.size(); i++) {
					if(visited.get(i).equals(values[0])) {
						found = true;
					}
				}
				
				if(found == true) {
				}
				
				else {
					int tempTot = currentNode.getTot() + Integer.parseInt(values[2]);
					int tempDepth = currentNode.getDepth();
					Node tempNode = new Node(values[0], tempTot, tempDepth+1);
					tempNode.prevNode = currentNode;
					addNodeToList(nodeList, tempNode);
					
				}
			}
			
			else {
				continue;
			}
		}
		br.close();
	}
	
	public static void addNodeToList(ArrayList<Node> nodeList, Node tempNode) {
		
		nodeList.add(tempNode);
		bubbleSort(nodeList);
	}
	
    public static void bubbleSort(ArrayList<Node> nodeList)
    {
        int n = nodeList.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (nodeList.get(j).tot > nodeList.get(j+1).tot) {         
                    Collections.swap(nodeList, j, j+1);
                }
    }
    
    public static void printPath(Node node) throws FileNotFoundException, IOException {
    	Node tempNode = node;
    	int tempDepth = tempNode.getDepth();
    	int tempTot = tempNode.getTot();
    	int counterDown = tempDepth;
    	int counterUp = 0;
    	String [] names = new String [tempDepth+1];
    	
    	while(counterDown >= 0) {
    		names[counterDown] = tempNode.getName();
    		tempNode = tempNode.prevNode;
    		counterDown--;
    	}
    	
    	System.out.printf("distance: %d km\n", tempTot);
    	System.out.printf("route:\n");
    	
    	while(counterUp < tempDepth) {
    		//Node tempNextNode = tempNode.getNextNode();
    		int edgeCost = getEdge(names[counterUp], names[counterUp+1]);
        	System.out.printf("%s to %s, %d km\n", names[counterUp], names[counterUp+1], edgeCost);
        	counterUp++;
    	}
    }
    
    public static int getEdge(String node1, String node2) throws FileNotFoundException,IOException{
		//File infile1 = new File(pathname+"/"+textFile);
		String line = "";
		BufferedReader br = new BufferedReader (new FileReader(textFile));
		
		while ((line = br.readLine()) != null) {
			values = line.split(" ");
			
			if((values[0].equals(node1)) && (values[1].equals(node2))) {
				br.close();
				return Integer.parseInt(values[2]);
			}
			
			else if((values[0].equals(node2)) && (values[1].equals(node1))) {
				br.close();
				return Integer.parseInt(values[2]);
			}
		}
		br.close();
		return 0;
    }

	public static void main(String[] args) throws FileNotFoundException,IOException{
		Node startNode = new Node(args[1], 0, 0);
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		nodeList.add(startNode);
		textFile = args[0];
		
		boolean foundGoal = false;
		
		while((nodeList.isEmpty() == false) && (foundGoal==false))
		{	
			Node tempNode = nodeList.get(0);
			
			if(tempNode.getName().equals(args[2])) {
				printPath(tempNode);
				foundGoal = true;
				break;
			}
			
			visited.add(nodeList.get(0).getName());
			nodeList.remove(0);
			String currentNodeName = tempNode.getName();
			
			checkText(visited, nodeList, currentNodeName, tempNode);
			
			
		}
		
		if(foundGoal == false) {
	    	System.out.printf("distance: infinity\n");
	    	System.out.printf("route:\nnone");
		}

	}
	
}


class Node {
	String name;
	Node prevNode;
	Node nextNode;
	int tot;
	int depth;
	
	public Node (String name, int tot, int depth) {
		this.name = name;
		this.tot = tot;
		this.depth = depth;
	}
	
	void setName(String name)
	{
		this.name = name;
	}
	
	String getName()
	{
		return this.name;
	}
	
	void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}
	
	Node getPrevNode() {
		return this.prevNode;
	}
	
	void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	Node getNextNode() {
		return this.nextNode;
	}
	
	void setDepth(int depth) {
		this.depth = depth;
	}
	
	int getDepth() {
		return this.depth;
	}
	
	void setTot(int tot) {
		this.tot = tot;
	}
	
	int getTot() {
		return this.tot;
	}
	
	
	
}
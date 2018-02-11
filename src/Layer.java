import java.util.ArrayList;

public class Layer {
    ArrayList<Node> nodes;

    Layer(int totalNodes) throws IllegalArgumentException {
        // A layer needs at least 1 node to work.
        if (totalNodes <= 0){
            throw new IllegalArgumentException("A layer cannot contain 0 or less nodes");
        }

        // Makes node list, and adds nodes to the list.
        this.nodes = new ArrayList<>(totalNodes);

        for (int i = 0; i < totalNodes; i++){
            this.nodes.add(new Node());
        }
    }

    // Method to update all nodes in the layer (Feed forward)
    boolean updateLayer(){
        int totalNodes = nodes.size();
        boolean success = false;

        // For each node, update it.
        for (int i = 0; i < totalNodes; i++){
            success = nodes.get(i).updateNode();

            if (!success){
                break;
            }
        }

        return success;
    }

    // Method to connect one layer to another.
    boolean connectTo(Layer nextLayer){
        boolean success = false;
        int totalNodes = nodes.size();
        int totalNextNodes = nextLayer.nodes.size();
        Node thisNode, nextNode;

        outerLoop:
        // For every node in this layer
        for (int thisNodeID = 0; thisNodeID < totalNodes; thisNodeID++){
            // To every node in the next layer
            for (int nextNodeID = 0; nextNodeID < totalNextNodes; nextNodeID++){
                thisNode = this.nodes.get(thisNodeID);
                nextNode = nextLayer.nodes.get(nextNodeID);

                // Connect them with a weight.
                success = nextNode.appendWeight(new Weight(thisNode));

                if (!success){
                    break outerLoop;
                }
            }
        }

        return success;
    }

    // Method to return all the values of the current layer.
    double[] nodeValues(){
        double[] values = new double[nodes.size()];

        for (int i = 0; i < nodes.size(); i++){
            values[i] = nodes.get(i).value;
        }

        return values;
    }
}

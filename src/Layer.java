import java.util.ArrayList;

public class Layer {
    ArrayList<Node> nodes;

    Layer(int totalNodes)throws IllegalArgumentException {
        if (totalNodes <= 0){
            throw new IllegalArgumentException("A layer cannot contain 0 or less nodes");
        }

        this.nodes = new ArrayList<>(totalNodes);

        for (int i = 0; i < totalNodes; i++){
            this.nodes.add(new Node());
        }
    }

    boolean updateLayer(){
        int totalNodes = nodes.size();
        boolean success = false;

        for (int i = 0; i < totalNodes; i++){
            success = nodes.get(i).updateNode();

            if (!success){
                break;
            }
        }

        return success;
    }

    boolean connectTo(Layer nextLayer){
        boolean success = false;
        int totalNodes = nodes.size();
        int totalNextNodes = nextLayer.nodes.size();
        Node thisNode, nextNode;

        outerLoop:
        for (int thisNodeID = 0; thisNodeID < totalNodes; thisNodeID++){
            for (int nextNodeID = 0; nextNodeID < totalNextNodes; nextNodeID++){
                thisNode = this.nodes.get(thisNodeID);
                nextNode = nextLayer.nodes.get(nextNodeID);

                success = nextNode.appendWeight(new Weight(thisNode));

                if (!success){
                    break outerLoop;
                }
            }
        }

        return success;
    }

    double[] nodeValues(){
        double[] values = new double[nodes.size()];

        for (int i = 0; i < nodes.size(); i++){
            values[i] = nodes.get(i).value;
        }

        return values;
    }
}

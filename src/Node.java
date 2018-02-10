import java.util.ArrayList;

public class Node {
    double value;
    ArrayList<Weight> weights = new ArrayList<>();

    // Appends a weight to a node.
    boolean appendWeight(Weight w){
        return this.weights.add(w);
    }

    // Method to update this node (Feed forward)
    boolean updateNode(){
        Weight currentWeight;
        double sumValues = 0;
        int totalWeights = this.weights.size();

        // For every weight going into this node, update the sum input value for this node
        for (int i = 0; i < totalWeights; i++){
            currentWeight = this.weights.get(i);
            sumValues += currentWeight.startNode.value * currentWeight.weight;
        }

        // Then apply the activation function
        this.value = this.sigmoid(sumValues);

        boolean anyWeights = totalWeights != 0;

        // Returns false if the result is Nan, infinite or if no weights exists.
        return anyWeights && Double.isFinite(this.value);
    }

    // The sigmoid activation function
    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}

import java.util.ArrayList;

public class Node {
    double value;
    ArrayList<Weight> weights = new ArrayList<>();

    boolean appendWeight(Weight w){
        return this.weights.add(w);
    }

    boolean updateNode(){
        Weight currentWeight;
        double sumValues = 0;
        int totalWeights = this.weights.size();

        for (int i = 0; i < totalWeights; i++){
            currentWeight = this.weights.get(i);
            sumValues += currentWeight.startNode.value * currentWeight.weight;
        }

        this.value = this.sigmoid(sumValues);

        boolean anyWeights = totalWeights != 0;

        // Retunerer false hvis resultatet er NaN eller infinite eller hvis ingen vægte findes.
        return anyWeights && Double.isFinite(this.value);
    }

    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}

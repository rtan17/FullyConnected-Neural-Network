import java.util.Random;

public class Weight {
    Node startNode;
    double weight;

    // Constructor if you want a random weight value.
    Weight(Node startNode){
        this.startNode = startNode;

        Random r = new Random();
        this.weight = r.nextDouble();    // Random value between [0 -> 1]
    }

    // Constructor if you want a specific weight value.
    Weight(Node startNode, double weight){
        this(startNode);
        this.weight = weight;
    }
}

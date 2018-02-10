import java.util.Random;

public class Weight {
    Node startNode;
    double weight;

    Weight(Node startNode){
        this.startNode = startNode;

        Random r = new Random();
        this.weight = r.nextDouble();    // Random value between 0 - 1
    }

    Weight(Node startNode, double weight){
        this(startNode);
        this.weight = weight;
    }
}

import java.util.Random;

public class Weight {
    Node startNode;
    double weight;
    double updateValue = 0; // Used to store a temporary new weight - Maybe another solution is needed

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

    void updateWeight(){
        this.weight = this.updateValue;
    }
}

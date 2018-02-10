import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

    // Tests the constructor
    @Test
    public void constructor01(){
        ANN network = new ANN(1,1,1,1);
        Trainer trainer = new Trainer(network);

        assertEquals(network, trainer.network);
    }

    // Tests the train function
    @Test
    public void train01(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ANN network = new ANN(1,1,1,1);
            Trainer trainer = new Trainer(network);

            TrainingData[] data = new TrainingData[1];
            double[] inputs = {1,0};
            double[] outputs = {1};

            data[0] = new TrainingData(inputs, outputs);

            trainer.train(data, 0.5);
        });
    }

    @Test
    public void train02(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ANN network = new ANN(1,1,1,1);
            Trainer trainer = new Trainer(network);

            TrainingData[] data = new TrainingData[1];
            double[] inputs = {1};
            double[] outputs = {1,0};

            data[0] = new TrainingData(inputs, outputs);

            trainer.train(data, 0.5);
        });
    }

    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
    private double sigmoidPrime(double x){
        return x * (1 - x);
    }

    // 2 layers: 1 input & 2 outputs
    @Test
    public void train03(){
        ANN network = new ANN(1,2,0,0);
        Weight W1 = network.layers.get(1).nodes.get(0).weights.get(0);
        Weight W2 = network.layers.get(1).nodes.get(1).weights.get(0);
        W1.weight = 0.5;
        W2.weight = 0.5;
        Trainer trainer = new Trainer(network);

        TrainingData[] data = new TrainingData[1];
        double[] inputs = {1};
        double[] outputs = {1,1};
        data[0] = new TrainingData(inputs, outputs);

        trainer.train(data, 0.5);

        double sigmoidOutput = sigmoid(0.5);
        double expectedOutput = 0.5 - 0.5 * (sigmoidOutput - 1) * sigmoidPrime(sigmoidOutput) * (1);

        assertEquals(expectedOutput, W1.weight);
        assertEquals(expectedOutput, W2.weight);
    }

    // 2 layers: 2 input & 1 outputs
    @Test
    public void train04(){
        ANN network = new ANN(2,1,0,0);
        Weight W1 = network.layers.get(1).nodes.get(0).weights.get(0);
        Weight W2 = network.layers.get(1).nodes.get(0).weights.get(1);
        W1.weight = 0.5;
        W2.weight = 0.5;
        Trainer trainer = new Trainer(network);

        TrainingData[] data = new TrainingData[1];
        double[] inputs = {1,1};
        double[] outputs = {1};
        data[0] = new TrainingData(inputs, outputs);

        trainer.train(data, 0.5);

        double sigmoidOutput = sigmoid(1);
        double expectedOutput = 0.5 - 0.5 * (sigmoidOutput - 1) * sigmoidPrime(sigmoidOutput) * (1);

        assertEquals(expectedOutput, W1.weight);
        assertEquals(expectedOutput, W2.weight);
    }

}
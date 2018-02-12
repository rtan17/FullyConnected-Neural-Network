public class Main {

    public static void main(String[] args) {

        Node bias = new Node();
        bias.updateNode();

        // Initialize network
        ANN network = new ANN(2,1,0,2);
        TrainingData[] data = new TrainingData[4];

        // Data
        double[] input1 = {0,0};
        double[] output1 = {0};
        data[0] = new TrainingData(input1, output1);

        double[] input2 = {0,1};
        double[] output2 = {1};
        data[1] = new TrainingData(input2, output2);

        double[] input3 = {1,0};
        double[] output3 = {1};
        data[2] = new TrainingData(input3, output3);

        double[] input4 = {1,1};
        double[] output4 = {1};
        data[3] = new TrainingData(input4, output4);

        // Train
        Trainer trainer = new Trainer(network);
        trainer.train(data, 1,2000);

        // Get new predictions:
        int totalLayers = network.layers.size();
        for (int d = 0; d < 4; d++){
            double[] outputs = network.evaluateInputs(data[d].inputs);

            for (int l = 0; l < network.layers.get(totalLayers-1).nodes.size(); l++){
                System.out.println("[" + data[d].inputs[0] + " & " + data[d].inputs[1] + "]" + ": " + outputs[l]);
            }
        }

    }
}


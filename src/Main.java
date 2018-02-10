public class Main {

    public static void main(String[] args) {

        ANN network = new ANN(2,1,0,0);
        TrainingData[] data = new TrainingData[4];

        // Data
        double[] input1 = {-0.5,-0.5};
        double[] output1 = {0};
        data[0] = new TrainingData(input1, output1);

        double[] input2 = {-0.5,0.5};
        double[] output2 = {0};
        data[1] = new TrainingData(input2, output2);

        double[] input3 = {0.5,-0.5};
        double[] output3 = {0};
        data[2] = new TrainingData(input3, output3);

        double[] input4 = {0.5,0.5};
        double[] output4 = {1};
        data[3] = new TrainingData(input4, output4);

        // Train
        Trainer trainer = new Trainer(network);

        for (int i = 0; i < 100000; i++){
            trainer.train(data, 0.5);
        }

        // Predictions
        int totalLayers = network.layers.size();
        for (int i = 0; i < 4; i++){

            double[] outputs = network.evaluateInputs(data[i].inputs);

            for (int l = 0; l < network.layers.get(totalLayers-1).nodes.size(); l++){
                System.out.println(outputs[l]);
            }
            System.out.println("-----------");
        }

    }
}


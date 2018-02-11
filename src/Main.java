public class Main {

    public static void main(String[] args) {

        ANN network = new ANN(2,1,2,2);
        TrainingData[] data = new TrainingData[4];

        // Data
        double[] input1 = {-1,-1};
        double[] output1 = {0};
        data[0] = new TrainingData(input1, output1);

        double[] input2 = {-1,1};
        double[] output2 = {1};
        data[1] = new TrainingData(input2, output2);

        double[] input3 = {1,-1};
        double[] output3 = {1};
        data[2] = new TrainingData(input3, output3);

        double[] input4 = {1,1};
        double[] output4 = {1};
        data[3] = new TrainingData(input4, output4);

        // Train
        Trainer trainer = new Trainer(network);

        int totalLayers = network.layers.size();
        for (int i = 0; i < 100000; i++){
            trainer.train(data, 1);

            // Predictions
            double avgError = 0;
            for (int d = 0; d < 4; d++){

                double[] outputs = network.evaluateInputs(data[d].inputs);
                avgError += Math.pow(outputs[0] - data[d].outputs[0], 2);

            }

            if (i % 200 == 0){
                System.out.println(avgError / 4);
                System.out.println("-----------");
            }

        }

        for (int d = 0; d < 4; d++){
            double[] outputs = network.evaluateInputs(data[d].inputs);

            for (int l = 0; l < network.layers.get(totalLayers-1).nodes.size(); l++){

                System.out.println(outputs[l]);
            }
        }





    }
}


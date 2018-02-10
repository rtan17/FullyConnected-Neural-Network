public class Main {

    public static void main(String[] args) {

        ANN network = new ANN(2,1,1,0);

        double[] inputs = {1.0,0.5};
        double[] outputs;
        int totalLayers = network.layers.size();

        outputs = network.evaluateInputs(inputs);

        for (int i = 0; i < network.layers.get(totalLayers-1).nodes.size(); i++){
            System.out.println(outputs[i] + " ");
        }
    }
}


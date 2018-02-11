import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingDataTest {

    @Test
    public void setValues01(){
        double[] inputs = {1,0};
        double[] output = {1};

        TrainingData data = new TrainingData(inputs, output);

        assertArrayEquals(inputs, data.inputs);
    }

    @Test
    public void setValues02(){
        double[] inputs = {1,0};
        double[] output = {1};

        TrainingData data = new TrainingData(inputs, output);

        assertArrayEquals(output, data.outputs);
    }
}
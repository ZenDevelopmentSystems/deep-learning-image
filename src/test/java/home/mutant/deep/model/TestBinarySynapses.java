package home.mutant.deep.model;

import home.mutant.deep.neurons.BinaryNeuron;
import home.mutant.deep.ui.Image;

import org.junit.Test;

import junit.framework.Assert;

public class TestBinarySynapses 
{
	@Test
	public void testOutput()
	{
		BinaryNeuron syn = new BinaryNeuron(56);
		syn.weights = new long[1];
		syn.weights[0] = (long)-1;
		
		long[] inputs = new long[1];
		inputs[0] = (long)-1;

		Assert.assertEquals(56, syn.calculateOutput(new Image(inputs,7,8)));
		inputs[0] = 255L;
		Assert.assertEquals(0, syn.calculateOutput(new Image(inputs,7,8)));
	}
	@Test
	public void testMem()
	{
		BinaryNeuron[] neurons = new BinaryNeuron[100000];
		for (int i = 0; i < neurons.length; i++) {
			neurons[i] = new BinaryNeuron(28*28);
		}
		Assert.assertEquals(100000, neurons.length);
	}
}

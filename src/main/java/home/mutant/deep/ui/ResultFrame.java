package home.mutant.deep.ui;

import home.mutant.deep.model.ModelTestResult;
import home.mutant.deep.networks.TwoFullConnectedLayers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ResultFrame extends JFrame
{
	private int width = 800;
    private int height = 600;
    
    RasterPanel drawingPanel;
    
    public ResultFrame(int width, int height)
    {
    	this.width = width;
    	this.height = height;
    	setSize(this.width+20, this.height+50);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	drawingPanel = new RasterPanel (this.width,this.height);
        add (drawingPanel);
        drawingPanel.init();
        setVisible(true);
    }

	public void buildFrame()
	{
		repaint();
	}
	
	public ModelTestResult showOutput(TwoFullConnectedLayers model, Image image, List<Integer> labels)
	{
		return showImage(new Image(model.forwardStep(image,true), 300, 300), labels);
	}
	
	public ModelTestResult showImage(Image image, List<Integer> labels)
	{
		Map<Integer,Integer> maps = new HashMap<Integer, Integer>();
		//drawingPanel.empty();
		int indexLabel=0;
		for (int y=0;y<image.imageY;y++)
		{
			for (int x=0;x<image.imageX;x++)
			{
				if (image.getPixel(x, y)!=0)
				{
					//drawingPanel.setPixel(x, y);
					if(indexLabel<labels.size())
					{
						Integer count = maps.get(labels.get(indexLabel));
						if (count==null)
						{
							count=0;
						}
						count++;
						maps.put(labels.get(indexLabel),count);
					}
				}
				indexLabel++;
			}
		}
		//repaint();
		int max = 0;
		int maxKey=-1;
		int total=0;
		for (Integer key : maps.keySet()) 
		{
			Integer value = maps.get(key);
			if (value>max)
			{
				max = value;
				maxKey = key;
			}
			total+=value;
			//System.out.println("     " +key+ " :"+maps.get(key) );
		}
		return new ModelTestResult(maxKey, max, max*100./total);
	}
	
	public void showImage(Image image)
	{
		drawingPanel.empty();
		putImage(image, 0, 0);
		repaint();
	}
	public void showMnist(List<byte[][]> images, int index)
	{
		drawingPanel.empty();
		for (int n1=0;n1<20;n1++)
		{
			for (int i =0;i<28;i++)
			{
				for (int j=0;j<28;j++)
				{
					drawingPanel.setPixel(i+n1*28,j,images.get(index+n1)[i][j]);
				}
			}
		}
		repaint();
	}
	
	public void showMnist2(List<byte[]> images, int index)
	{
		drawingPanel.empty();
		for (int n1=0;n1<20;n1++)
		{
			int offset = 0;
			for (int i =0;i<28;i++)
			{
				for (int j=0;j<28;j++)
				{
					byte color = images.get(index+n1)[offset++];
					if (color ==1)
					{
						color = (byte)255;
					}
					drawingPanel.setPixel(j+n1*28,i,color);
				}
			}
		}
		repaint();
	}
	
	public void showImages(List<Image> images, int index)
	{
		drawingPanel.empty();
		for (int n1=0;n1<20;n1++)
		{
			byte[][] image = images.get(index+n1).getDataTwoDimensional();
			for (int i =0;i<28;i++)
			{
				for (int j=0;j<28;j++)
				{
					drawingPanel.setPixel(j+n1*28,i,image[i][j]);
				}
			}
		}
		repaint();
	}

	public void showImages(List<Image> images)
	{
		showImages(images, 0);
	}
	
	public void showBinaryColumn(TwoFullConnectedLayers column)
	{
		drawingPanel.empty();
		for (int index=0;index<256;index++)
		{
			putImage(column.neurons[index].generateSample(), index*5, 3);
		}
		repaint();
	}
	
	public void putImage(Image image, int xOffset, int yOffset)
	{
		for (int x=0;x<image.imageX;x++)
		{
			for (int y=0;y<image.imageY;y++)
			{
				drawingPanel.setPixel(xOffset+x,yOffset+y,image.getPixel(x, y));
			}
		}
	}
}
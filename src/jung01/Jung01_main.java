package jung01;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Jung01_main {
	
	public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int MARGIN = 50;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Jung01_main().run();

	}
	
    public void run(){
        showGraph(makeGraph(5));
    }
    Graph<Integer,String> makeGraph(int size) {
        Graph<Integer, String> g = new SparseGraph<Integer, String>();
        for(int i=0;i<size;++i)g.addVertex(i);
        int num = 0;
        for(int i=0;i<size;++i)for(int j=0;j<i;++j){
                g.addEdge(""+num++, i, j);
        }
        return g;
    }
    void showGraph(Graph<Integer, String> g){
        Layout<Integer, String> layout = new CircleLayout<Integer, String>(g);
        layout.setSize(new Dimension(WIDTH,HEIGHT));
        BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(WIDTH+MARGIN,HEIGHT+MARGIN));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Integer,String>());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        JFrame frame = new JFrame("Jung Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

}

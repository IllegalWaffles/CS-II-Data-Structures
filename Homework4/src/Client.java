import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Kuba Gasiorowski, kgasiorowski, 109776237, kuba.gasiorowski@sbu.edu
 * 
 * Homework #4
 * Recitation 03
 * Rec TA: Ka Wing Fong
 * Grading Ta: Zhichuang Sun 
 * 
 * The driving class behind this program.
 * 
 * Contains a javafx GUI. Uses one main Stage
 * and two scenes - one for initial input and
 * one for simulation output.
 * 
 * @author Kuba Gasiorowski
 *
 */
public class Client extends Application{
	
	//Main window of the GUI
	Stage window;
	
	//Labels found at the top of each scene
	Label mainLabel, mainSimulationLabel;
	
	//Labels found on the initial scene
	Label intRoutersLabel, probPacketLabel, bufferSizeLabel, 
		  minPacketLabel, maxPacketLabel, bandwidthLabel, 
		  durationLabel, numSimulationsLabel;
	
	//The output area for the simulation
	TextArea simOutput;
	
	//The input fields for the simulation
	TextField intRoutersField, probPacketField, bufferSizeField, 
			  minPacketField, maxPacketField, bandwidthField, 
			  durationField, numSimulationsField;
	
	//The buttons that create actions in the program
	Button startButton, exitButton;

	//The two main scenes of the GUI
	Scene start, simulationScene;
	
	/**
	 * Initializes, organizes, and runs the GUI program.
	 * 
	 * Called when launch(args) is called in main.
	 * 
	 * @param primaryStage
	 * 		the main stage of the program
	 */
	@Override
	public void start(Stage primaryStage)
	{
		
		window = primaryStage;
		window.setTitle("Network Simulator 1.0");
		window.setResizable(false);
		
		initializeComponents();
		
		setActions();
		
		setUpStartingScene();
		
		setUpSimulationScene();
		
		window.setScene(start);
		window.show();
		
	}
	
	/**
	 * Helper method to initialize all of
	 * the components of the GUI.
	 */
	private void initializeComponents()
	{
		
		mainLabel = new Label("Simulator 1.0");
		
		mainSimulationLabel = new Label("Simulating...");
		
		intRoutersLabel = new Label("Number of intermediate routers: ");
		probPacketLabel = new Label("Probability of a new packet arriving: ");
		bufferSizeLabel = new Label("Maximum buffer size: ");
		minPacketLabel = new Label("Minimum packet size: ");
		maxPacketLabel = new Label("Maximum packet size: ");
		bandwidthLabel = new Label("Bandwidth: ");
		durationLabel = new Label("Simulation duration: ");
		numSimulationsLabel = new Label("Number of simulations: ");
		
		intRoutersField = new TextField("0");
		intRoutersField.setMaxWidth(60);
		
		probPacketField = new TextField("0.0");
		probPacketField.setMaxWidth(60);
		
		bufferSizeField = new TextField("0");
		bufferSizeField.setMaxWidth(60);
		
		minPacketField = new TextField("0");
		minPacketField.setMaxWidth(60);
		
		maxPacketField = new TextField("0");
		maxPacketField.setMaxWidth(60);
		
		bandwidthField = new TextField("0");
		bandwidthField.setMaxWidth(60);
		
		durationField = new TextField("0");
		durationField.setMaxWidth(60);
		
		numSimulationsField = new TextField("1");
		numSimulationsField.setMaxWidth(60);
		
		startButton = new Button("Start Simulation");
		
		simOutput = new TextArea();
		simOutput.setEditable(false);
		simOutput.setMaxSize(500, 575);
		simOutput.setMinSize(500, 575);
		
		exitButton = new Button("Exit");
		
	}
	
	/**
	 * Helper method to set up which button
	 * does what.
	 */
	private void setActions()
	{
		
		startButton.setOnAction(e -> {
			
			window.setScene(simulationScene);
			startSimulation();	
			
		});
		
		exitButton.setOnAction(e -> {
			
			window.close();
			System.exit(0);
			
		});
		
	}
	
	/**
	 * Helper method to set up the initial
	 * starting scene.
	 */
	private void setUpStartingScene()
	{
		
		HBox hb;
		VBox vb;
		
		hb = new HBox();
		vb = new VBox(20);
		
		vb.getChildren().add(mainLabel);
		vb.setAlignment(Pos.CENTER);
		
		hb.getChildren().addAll(intRoutersLabel, intRoutersField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(probPacketLabel, probPacketField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(bufferSizeLabel, bufferSizeField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(minPacketLabel, minPacketField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(maxPacketLabel, maxPacketField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(bandwidthLabel, bandwidthField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(durationLabel, durationField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		hb = new HBox();
		hb.getChildren().addAll(numSimulationsLabel, numSimulationsField);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);
		
		vb.getChildren().add(startButton);
		
		VBox startLayout = new VBox();
		startLayout.getChildren().add(vb);
		
		start = new Scene(startLayout, 300, 450);
		
	}
	
	/**
	 * Helper method to set up the simulation 
	 * scene.
	 */
	private void setUpSimulationScene()
	{
		
		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(mainSimulationLabel,simOutput,exitButton);
		
		simulationScene = new Scene(vb, 600, 650);
		
	}
	
	/**
	 * Starts the simulation based upon the input
	 * currently held in the field boxes in the 
	 * main initial scene.
	 * 
	 * This method can be edited to meet the
	 * user's needs. This is one of many ways that
	 * the simulator can be used.
	 */
	private void startSimulation()
	{
		
		try
		{
			
			int numIntRouters = Integer.parseInt(intRoutersField.getText().trim());
			double arrivalProb = Double.parseDouble(probPacketField.getText().trim());
			int maxBufferSize = Integer.parseInt(bufferSizeField.getText().trim());
			int minPacketSize = Integer.parseInt(minPacketField.getText().trim());
			int maxPacketSize = Integer.parseInt(maxPacketField.getText().trim());
			int bandwidth = Integer.parseInt(bandwidthField.getText().trim());
			int duration = Integer.parseInt(durationField.getText().trim());
			int numSimulations = Integer.parseInt(numSimulationsField.getText().trim());
			
			if(numIntRouters <= 0)
				throw new IllegalArgumentException("Error: Must be 1 or more intermediate routers");
				
			if(arrivalProb < 0)
				throw new IllegalArgumentException("Error: Probability must be greater than zero");
				
			if(minPacketSize > maxPacketSize || minPacketSize < 0 || maxPacketSize < 0)
				throw new IllegalArgumentException("Error: Invalid packet size specifications");
			
			if(bandwidth < 0)
				throw new IllegalArgumentException("Error: Bandwidth cannot be less than or equal to zero");
			
			if(duration < 0)
				throw new IllegalArgumentException("Error: Duration must be greater than zero");
			
			if(numSimulations <= 0)
				throw new IllegalArgumentException("Error: Cannot simulate zero times");
			else if(numSimulations == 1){
				
				//Creates a new simulator which only outputs to "simOutput"
				Simulator sim = new Simulator(numIntRouters, arrivalProb, maxBufferSize, minPacketSize, maxPacketSize, bandwidth, duration, false, simOutput);
				sim.simulate();
			
			}
			else
			{
				
				double sum = 0;
				//Creates a new simulator which does not output to anywhere
				Simulator sim = new Simulator(numIntRouters, arrivalProb, maxBufferSize, minPacketSize, maxPacketSize, bandwidth, duration, false, null);
				
				for(int i = 1; i <= numSimulations; i++)
				{
					
					sum += sim.simulate();
					if(i%10000 == 0)
						System.out.println(i + " Simulations executed.");
						
				}
				
				double average = sum/((double)numSimulations);
				
				simOutput.appendText("Total simulations run: " + numSimulations);
				simOutput.appendText("Average service time per packet per simulation: " + average + "\n");
				
			}
			
		}
		catch(NumberFormatException e)
		{
			System.out.println("Error: Bad input. Try again");
			simOutput.appendText("Error: Bad input. Try running the program again with different input.");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			simOutput.appendText(e.getMessage());
			simOutput.appendText("\nTry running the program again with different input.");
			
		}
	
		
		
	}
	
	/**
	 * Begins the whole program.
	 * 
	 * @param args
	 * 		standard string arguments
	 */
	public static void main(String args[])
	{
		
		//Launches the GUI
		launch(args);
		
	}
	
}

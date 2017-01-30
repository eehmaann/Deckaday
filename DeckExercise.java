import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.*;

public class DeckExercise extends JFrame implements ActionListener{
	CardLayout cl = new CardLayout(); // Layout for the who program
	ArrayList<String> deck = new ArrayList<String>(); // represents a 52 card deck
	private boolean setDone; //boolean that tells whether the clock should be running
	private final double REPS= 104.0; // This is the number of times one does an exercise 
	private static final int HEIGHT=350;
	private static final int WIDTH= 300;
	private final int DELAY = 1000; // One second delay between timer increasing
	private int bestTime; //  Will hold best time the workout was completed in
	private int counter; // counts where to store workout in array
	private int currentDif; // The difficulty that chosen
	private int currentTime;  // Currently the best stored time for movement	
	private int endTime; // tracks when each individual exercise ended
	private int moveTime; // Total time of the the movement through the circuit
	private int number; // the number of repetitions on the card
	private int seconds; // the number of seconds the workout is taking
    private int startTime; // tracks when each individual exercise started
	private int[][]times= new int[4][2]; // This will hold the exercise number and time
    private JButton next = new JButton("Next"); // draws next card or goes to results
	private JButton restart= new JButton("Do another deck?");// allows the next circuit
	private JButton start = new JButton("Start"); // Begins the circuit
	private JButton [] options= new JButton[16]; // buttons so person can choose workout
	private JComboBox setting; // This will let user choose difficulty
	private JFrame frame = new JFrame("CardLayout demo"); // frame for the whole program
	private JPanel displayScreen = new JPanel(); // container for all of the screens
	private JPanel selectionScreen; // Screen where the exercise circuit is setup
	private JPanel exerciseScreen = new JPanel(); // Screeen shows the drawn exercise
	private JPanel resultsScreen = new JPanel(); // Screen where it shows results	
	private JTextField command; // states what the current exercise is
	private JTextField currentCard; // states what the current card is
	private JTextField overallTime;  // will show total time fo workout
	private JTextField time; // displays the increasing time the circuit is taking
	private JTextField [] finishes= new JTextField [4];// hold times per movement type
	private String disAveTime;//  String holding formated average time, movement : second
	private String drawnCard; // The current card that is drawn 
	private String myTime; //  Will show how long user has being working on the deck
	private String translateSuit; // String will tell what the movement the suit refers to
	private String[]circuit = new String [4]; // the current circuit
	private String[][]movement= new String[16][4];// Shows all the options for the workout    
    private Timer timer; // A timer measuring the how long the deck takes to do

	public DeckExercise() {
		clear();
		// options for people to choose their exercise
		movement[0][0]= "Air Squats";
    	movement[1][0]= "Burpees";
    	movement[2][0]= "Butt Bridges";
    	movement[3][0]= "Bicycle Kicks";
    	movement[4][0]= "Broad Jumps";
    	movement[5][0]= "Crunches";
    	movement[6][0]= "Flutter Kicks";
    	movement[7][0]= "Jumping Jacks";
    	movement[8][0]= "Lunges";
    	movement[9][0]= "Leg Lifts";
    	movement[10][0]= "Mnt. Climbers";
    	movement[11][0]= "Pistols";
    	movement[12][0]= "Push Ups";
    	movement[13][0]= "Sit-up";
    	movement[14][0]= "Squats";
    	movement[15][0]= "Tricep Dips";
		
		// When class is launched it will give all of the times a blank cell
		for(int row=0; row<16; row++){
			for(int col=1; col<4; col++){
				movement[row][col]="";
			}
		}
		// set up times for the exercise set to be 0
		setTimes();
		
		displayScreen.setLayout(cl);
		
		//First panel in which people will choose their exercises and difficulty		
		JPanel  selectionScreen = new JPanel ();
		selectionScreen.setLayout (new GridLayout (5, 4) );			
			
		// buttons for choosing the exercises
        for(int i=0; i<16; i++){
            options[i]= new JButton (movement[i][0]);
            options[i].setFont (new Font ("Times", Font.BOLD, 12));
            options[i].setHorizontalAlignment(JButton.CENTER);
            options[i].setPreferredSize(new Dimension(100,100));
            options[i].addActionListener ( DeckExercise.this);
            selectionScreen.add(options[i]);
        }
        // set up choosing difficulty
        JTextField dif = new JTextField ("Difficulty");
        dif.setEditable(false);
        selectionScreen.add(dif);
        dif.setBackground(Color.WHITE);
        String [] challenge = { " ", "1" , "2", "3"};
		setting = new JComboBox ( challenge );
		setting.setEditable(false);
		selectionScreen.add(setting);
		
		// blank label so start is in the right corner.
		JLabel blank = new JLabel ("");
		selectionScreen.add(blank);
		
		//start button
		start.addActionListener ( DeckExercise.this);
		selectionScreen.add(start);

		exerciseScreen.setLayout (new BorderLayout());
		//places next button to go through cards
		next.addActionListener (DeckExercise.this);
		exerciseScreen.add(next, BorderLayout.EAST);
		time= new JTextField ("0:00");  //starts clock at zero to avoid dividing by 0
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont (new Font ("Ariel", Font.BOLD, 20));
		exerciseScreen.add(time, BorderLayout.NORTH); //place time at top
		command = new JTextField (" 14 Mountain Climbers "); // shows current exercise
		command.setHorizontalAlignment(SwingConstants.CENTER);
		command.setFont (new Font ("Ariel", Font.BOLD, 26));
		command.setEditable(false);
		exerciseScreen.add(command, BorderLayout.SOUTH); //place at bottom of screen
		currentCard = new JTextField (" Red Joker ");  // gives the current card
		currentCard.setHorizontalAlignment(SwingConstants.CENTER);
		currentCard.setFont(new Font ("Ariel", Font.BOLD, 30));
		currentCard.setEditable(false);
		exerciseScreen.add(currentCard, BorderLayout.CENTER); //Card is in the center
		
		resultsScreen.setLayout (new GridLayout (7,1));
		JTextField congrats= new JTextField("Congratulations! You are Done!");
		congrats.setEditable(false);  
		resultsScreen.add(congrats);
		overallTime= new JTextField("Overall time: ");
		overallTime.setEditable(false);
		resultsScreen.add(overallTime);
		for(int i=0; i<4; i++){
			finishes[i]= new JTextField("Test" +i);
			finishes[i].setEditable(false);
			resultsScreen.add(finishes[i]);
		}
		restart.addActionListener(DeckExercise.this);
		resultsScreen.add(restart);
		
		selectionScreen.setBackground(Color.RED);  // decorates selection screen
		exerciseScreen.setBackground(Color.GREEN); //decorates exercise screen
		resultsScreen.setBackground(Color.YELLOW);  // decorate results screen

		displayScreen.add(selectionScreen, "1"); // puts first screen on display screen
		displayScreen.add(exerciseScreen, "2"); // puts second screen on display screen
		displayScreen.add(resultsScreen, "3"); // puts third screen on display screen
		cl.show(displayScreen, "1"); // shows all screens, starting with first one
			
		frame.add(displayScreen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
		/**
			* This method set the beginning time for each movement in the deck
			* As the user has spent no time doing the exercise to begin with, it is
			* set to zero.
			*
			*/
		public void setTimes(){
			int col=1;
			for	(int row=0; row<4; row++)
			{
				times[row][col]=0;
			}
		}
		/**
			*  This method provides instructions for what the button should do,
			*	as well as give instructions for when the button shouldn't work
			*/
		
		public void actionPerformed( ActionEvent e )
    	{
    		/* The option butttons set up the circuit that is going to be used.  
    		*	The user must select four movements, one for each suit.  However,
    		*	the user is able to select the same movement multiple times
    		*/
    		for (int i=0; i<16; i++){ 
         		if (e.getSource()== options[i] && counter<4){ 
         			circuit[counter]=movement[i][0];
         			times[counter][0]=i;
         			System.out.println(circuit[counter] + " : " +counter);
         			counter++;		
         		}        	
        	}
        	
        	/* the start button will display an message, explaining what went 
        	* 	what is required if set up isn't complete, else
        	*	it will let the user begin the workout.
        	*/
        	
        	if (e.getSource()==start){
        		if (counter<4 || (setting.getSelectedItem().equals(" "))){
        			JOptionPane.showMessageDialog(DeckExercise.this, 
        						"Cannot proceed. You must select 4 moves and difficulty"+
        						" must be set.  Currently you have selected " +counter+
        						" moves and/or Difficulty is not set." +  
        						setting.getSelectedItem());
        		}
        		else{
        			cl.next(displayScreen);
        			currentDif=Integer.parseInt((setting.getSelectedItem().toString()));
        			startTime=seconds = 0;      //beginning seconds on clock
        			drawCard();
        			//sets up timer
        			timer = new Timer(DELAY, this);
        			timer.setInitialDelay(0);
        			timer.start();
        			setDone=false; // must be false for the timer to work.
        		}
        	}
        	// next button will either show the results, or draw the next card
        	if (e.getSource()==next){
        		addTime();  //pressing next will always calculate time last task took
        		if(deck.isEmpty()){
        			cl.next(displayScreen);
        			setDone=true;
        		}
        		else{
        			drawCard();
        			
        		}
        	}
        	// restart button returns to the start of the program.  Saving only times
        	if(e.getSource()==restart){
        		cl.show(displayScreen, "1");
        		clear();
        	}
        	// the timer will stop when the circuit is done, or will add seconds
        	if(setDone)
            { 
                timer.stop();
                showResults();
            }
            else
            {
                seconds++;
                time.setText(displayTime());
                
            }
        }
    
    /***
    	* This method creates the display for time.  It will display in mm:ss format
    	* but not include hours as even beginning athletes can expect to finish this
    	* in under an hour.
    	*/
    public String displayTime()
    {
    	
    	if(seconds%60<10)
        {
            myTime=(""+seconds/60 +":0"+seconds%60); 
            //this ensures seconds is two digit number.
        }
        else{
                myTime=(""+seconds/60 +":"+seconds%60);
            }
        return myTime;
    } 
    /**
    	* This method will be add time to the exercise type being performed.  Thus making
    	* it possible to calculate averages at the end of the workout. 
    	*
    	*/
     public void addTime(){
     	endTime=seconds;
     	int totalTime= endTime-startTime;
     	for(int i=0; i<4; i++){
     		if(command.getText().contains(circuit[i])){
     			times[i][1]+=totalTime;
     		}     	
     	}
     	startTime=seconds;
     }
     
    /**
    	* This method will do all the actions that associated with drawing a new card
    	* It will randomly choose the next card to draw, get the number on the card
    	* and use that information to call for the current exercise to change,
    	* and for the card being shown to change, which might lead to it be formated
    	* last it removes the card from the possible cards that be chosen
    	*/    
    	
    public void drawCard()
    {
    	int numCard = (int) (deck.size() * Math.random()); //random number from cards left
    	drawnCard=deck.get(numCard); //get the next card
    	number=Integer.parseInt(drawnCard.replaceAll("[\\D]", "")); //get number on card
    	changeCommand(drawnCard, number);
    	changeCurrentCard(drawnCard, number);
    	deck.remove(numCard);
    }
    /**
    	* This method will change what the current exercise is to a new exercise.
    	* It does that by translating the card symbol to the corresponding exercise
    	*
    	* @param card the randomly selected card showing suit and numeric value
    	* @param value the numeric value on the card
    	*/
    public void changeCommand(String card, int value)
    {
    	if(card.contains("clubs")){
    	translateSuit=circuit[0];
    	}
    	else if(card.contains("diamonds")){
    	translateSuit=circuit[1];
    	}
    	else if(card.contains("hearts")){
    	translateSuit=circuit[2];
    	}
    	else if(card.contains("spades")){
    	translateSuit=circuit[3];
    	}
    	command.setText("Do " + value + " " + translateSuit);
    }
    /**
    	* This method will change the screen to display the next card
    	*  It will also translate the numeric value into the corresponding face.
    	*  
    	* @param card the current randomly chosen card
    	* @param value the numeric value on the card
    	*/
    public void changeCurrentCard(String card, int value)
    {
    	if(value<11){
    		currentCard.setText(card);
    	}
    	//If it is an eleven the face value is a Jack.
    	else if(value==11){
    		currentCard.setText(card.substring(0, (card.length()-2))+"J");
    	}
    	//If it is an eleven the face value is a Queen.
    	else if(value==12){
    		currentCard.setText(card.substring(0, (card.length()-2))+"Q");
    	}
    	//If it is an eleven the face value is a King.
    	else if(value==13){
    		currentCard.setText(card.substring(0, (card.length()-2))+"K");
    	}
    	//If it is an eleven the face value is an Ace.
    	else if(value==14){
    		currentCard.setText(card.substring(0, (card.length()-2))+"A");
		}
	}
	
	/***
		*	This method builds the results page with the stats from through the workout
		*
		*/
	    
	public void showResults()
	{
		for(int i = 0; i<4; i++){
			moveTime=times[i][1];
			double aveTime=moveTime/REPS;
			disAveTime=""+aveTime;
			if(disAveTime.length()>5){
				disAveTime=disAveTime.substring(0,6);
			}
			bestTime=compareMovement(i);			
			finishes[i].setText(circuit[i]+ " Your time: "+ moveTime + 
								" average time: " + disAveTime +" best time:" + bestTime);
		}
		overallTime.setText(displayTime());	
			
	}
	/***
		*	This new method checks what the former best time to complete the exercise was
		*	if it is faster, it will be come the new time
		*
		*/
	public int compareMovement(int index)
	{
		int i=index;
		if((movement[times[i][0]][currentDif]).equals("")){
			movement[times[i][0]][currentDif]=(""+ times[i][1]); 
		}
		currentTime=Integer.parseInt(movement[times[i][0]][currentDif]);
		
		if (currentTime> times[i][1]) // checks if new time is faster
		{
			//if it is faster than replace it
			movement[times[i][0]][currentDif] =  (""+ times[i][1]); 
			return times[i][1];
		}
		else{
			return currentTime;
			}
	}
	/***
		*  This method will reset the counter, allowing the participant to create a
		*  new workout set, it will create a new deck, and it will set all the times
		*  for the individual back to 0.  When starting again, the only thing that will
		*  be kept is the stored data for best times to complete 104 reps of a movement
		*  at a particular difficulty
		*/ 
		
	public void clear()
	{
		counter = 0;
		
		//creates a deck of cards, has the numeric value rather than face name
		for(int set=2; set<15; set++){
			deck.add("clubs " + set);
			deck.add("diamonds " + set);
			deck.add("hearts " + set);
			deck.add("spades " + set);
		}
		setTimes();
	}
	public static void main(String[] args) {
		DeckExercise DE = new DeckExercise();
	}

}

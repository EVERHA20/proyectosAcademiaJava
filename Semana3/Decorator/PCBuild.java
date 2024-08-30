
public class PCBuild {

	public static void main(String[] args) {
		
        Component computer = new Cabinet();
        System.out.println(computer.getDescription() + " $" + computer.getTotalCost());

        Component motherBoardComputer = new MotherBoard(computer);
        System.out.println(motherBoardComputer.getDescription() + " $" + motherBoardComputer.getTotalCost());
        
        Component graphicsCardComputer = new GraphicsCard(motherBoardComputer);
        System.out.println(graphicsCardComputer.getDescription() + " $" + graphicsCardComputer.getTotalCost());

        Component fullyLoadedComputer = new CoolingFan(graphicsCardComputer);
        System.out.println(fullyLoadedComputer.getDescription() + " $" + fullyLoadedComputer.getTotalCost());
    }
}

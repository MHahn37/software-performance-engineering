public class InstrumentationDemo {

	public static void main(String[] args) throws Exception { 
				
		long startTime = System.nanoTime();
		Instrumentation ins = Instrumentation.getInstance();
		ins.activate(true);
		ins.startTiming("overhead test");
		ins.comment("comment in overhead test");
		ins.stopTiming("overhead test");
		ins.dump();
		long endTime = System.nanoTime();
		System.out.println("Overhead: "+(endTime - startTime) + " ns"); 

	}

}

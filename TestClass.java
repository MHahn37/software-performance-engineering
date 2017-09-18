import java.util.concurrent.ThreadLocalRandom;

public class InstrumentationDemo {

	public static void main(String[] args) throws Exception { 

		Instrumentation ins = Instrumentation.getInstance();
		ins.activate(true);
		
		ins.startTiming("populate array");
		int[] bubbleArray = populateArray();
		ins.stopTiming("populate array");
		int[] quickArray = populateArray();

		ins.startTiming("bubble sort");
		bubbleSort(bubbleArray);
		ins.stopTiming("bubble sort");
		
		ins.startTiming("quick sort");
		quickSort(quickArray);
		ins.stopTiming("quick sort");

		ins.dump();
	}
	
	public static int[] populateArray() {
		int[] array = new int[10000];
		for (int i =0; i < array.length; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		}
		return array;
	}
	
	public static void bubbleSort(int[] a) throws Exception {
		BubbleSort bubbleTest = new BubbleSort();
		bubbleTest.sort(a);
	}
	
	public static void quickSort(int[] a) throws Exception {
		QuickSort quickTest = new QuickSort();
		quickTest.sort(a);
	}

}

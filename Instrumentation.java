/*
SOFT 437 Project 3
Matt Hahn, 10104772, 13mjh1
3/20/2017
*/

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Instrumentation {

	// private attributes
	private boolean activationFlag = false;
	private String log = "";
	private long totalTime = 0;
	private Stack<Long> timers = new Stack<Long>(); // global stack to manage the start/stop timing pairs
	private Instrumentation() {} // constructor is private so the class cannot be instantiated.
	private static Instrumentation instance = new Instrumentation(); // create a single instrumentation object

	/*
	 * Accessor to get the single instrumentation object
	 */
	public static Instrumentation getInstance() {
		return instance;
	}

	/*
	 * Start timing a method, or block of code
	 */
	public void startTiming(String comment) {
		if (activationFlag == true) {
			long startTime = System.nanoTime();
			timers.push(startTime);
			if (timers.size() > 1) {
				for(int i = 0; i < timers.size() - 1; i++) {
					log += "|\t";
				}
			}
			log += "START TIMING: " + comment + "\n";
		}
	}

	/*
	 * Stop timing a method, or block of code
	 */
	public void stopTiming(String comment) {
		if (activationFlag == true) {
			long stopTime = System.nanoTime(); // record the time in nanoseconds
			long startTime = (long) timers.peek();
			long duration = (stopTime - startTime) / 1000000; // compute the duration in milliseconds
			if (timers.size() > 1) {
				for(int i = 0; i < timers.size() - 1; i++) {
					log += "|\t";
				}
			}
			else {
				// a stack of size 1 means there are no nested timing calls.
				// this non-nested stack call is added to the totalTime.
				totalTime += duration;
			}
			timers.pop();
			log += "STOP TIMING: " + comment + " " + duration + "ms\n";
		}
	}

	/*
	 * Place an additional comment in the output of the instrumentation log file
	 */
	public void comment(String comment) {
		if (activationFlag == true) {
			if (timers.size() > 1) {
				for(int i = 0; i < timers.size() - 1; i++) {
					log += "|\t";
				}
			}
			log += "COMMENT: " + comment + "\n";
		}
	}

	/*
	 * Activates/deactivates instrumentation
	 */
	public void activate(boolean onoff) {
		if (onoff == false) activationFlag = false;
		else activationFlag = true;
	}

	/* 
	 * dump() writes formatted/indented pairs of startTiming/stopTiming statements to a human 
	 * readable log file. 
	 */
	public void dump() {
		String timeStamp = new SimpleDateFormat("ddyyMMhhmmss").format(Calendar.getInstance().getTime());
		String filename = "instrumentation" + timeStamp + ".log";
		log += "TOTAL TIME: " + totalTime + "ms";
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.write(log);
		    writer.close();
		} catch (IOException e) {
		   System.out.println(e);
		}
	}

	public void dump(String filename) {
		log += "TOTAL TIME: " + totalTime + "ms";
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.write(log);
		    writer.close();
		} catch (IOException e) {
		   System.out.println(e);
		}
	}

}

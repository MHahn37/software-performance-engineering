// Bubble sort with instrumentation

class BubbleSort extends SortAlgorithm {
	void sort(int a[]) throws Exception {
		
		Instrumentation bubble = Instrumentation.getInstance();
		bubble.startTiming("inside method bubble sort");
		for (int i = a.length; --i>=0; ) {
			boolean flipped = false;
			for (int j = 0; j<i; j++) {
				
				if (a[j] > a[j+1]) {
					int T = a[j];
					a[j] = a[j+1];
					a[j+1] = T;
					flipped = true;
				}

			}
			if (!flipped) {

				bubble.stopTiming("inside method bubble sort");
				return;
			}
		}
	}
}
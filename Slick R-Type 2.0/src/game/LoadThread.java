package game;

public class LoadThread  implements Runnable {
	private Vue vue = Vue.getInstance();
	@Override
	public void run() {
		go();
	}

	public void go () {
		vue.loadNext();
		
		System.out.println("Je suis fini "+Thread.currentThread().getName());

	}
}

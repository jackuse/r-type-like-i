package states;

import game.LoadThread;
import game.Main;
import game.ResourceManager;
import game.Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.Transition;

public class Chargement extends BasicGameState{
	int stateID = -1;
	private Vue vue = Vue.getInstance();
	private ResourceManager rm = ResourceManager.getInstance();
	boolean ok= true;
	int nbProc = Runtime.getRuntime().availableProcessors();
	private Transition t[];
//	private boolean threadFini= false;
//	Runnable tache;
//	Thread T1;
//	Thread T2;
//	Thread T3;
//	Thread T4;

	public Chargement(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		vue.chargemementGame();
//		tache = new LoadThread();
//		T1 = new Thread(tache);
//		T1. setName (" Caroline ");
//		T2 = new Thread(tache);
//		T2. setName (" pierre ");
//		T3 = new Thread(tache);
//		T4 = new Thread(tache);
		
		t = new Transition[2];
		
		try {
			t[0] = FadeOutTransition.class.newInstance();
			t[1] = FadeInTransition.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		//vue.renderChargement(gc, gr);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {


		if(Main.etatprecedent == -1){

			if(ok){
				if (vue.nextResource != null) { 
					vue.loadNext();
//					System.out.println("kikou "+vue.nextResource.getDescription());
					//vue.nextResource.load(); 
					/*Runnable tache = new LoadThread ();
					Thread monThread = new Thread (tache);
					monThread.start();
					 */
/*
					System.out.println("thread 1 en vie "+T1.isAlive());
					System.out.println("thread 2 en vie "+T2.isAlive());
					System.out.println("thread 3 en vie "+T3.isAlive());
					System.out.println("thread 4 en vie "+T4.isAlive());
*/
//					if(!T1.isAlive())
//					{
//						T1.run();
////						System.out.println("thread 1 lancer");
//					}else if(nbProc > 1 && !T2.isAlive())
//					{
//						T2.run();
////						System.out.println("thread 2 lancer");
//					}else if(nbProc > 2 && !T3.isAlive())
//					{
//						T3.run();
////						System.out.println("thread 3 lancer");
//					}else if(nbProc > 3 && !T4.isAlive())
//					{
//						T4.run();
////						System.out.println("thread 4 lancer");
//					}
					/*
					System.out.println("thread 1 en vie "+T1.isAlive());
					System.out.println("thread 2 en vie "+T2.isAlive());
					System.out.println("thread 3 en vie "+T3.isAlive());
					System.out.println("thread 4 en vie "+T4.isAlive());*/


					// slow down loading for example purposes 
					//try { Thread.sleep(50); } catch (Exception e) {}  
//					if( vue.nextResource.getDescription().contentEquals("data\\songs\\Asterix and the Great Rescue Mega Drive Title Music.ogg"))
//						ok=false;
					vue.nextResource = null; 
				} 

			}


			if (LoadingList.get().getRemainingResources() > 0) { 
				vue.nextResource = LoadingList.get().getNext(); 
			} else { 
//				if(!T1.isAlive()&&!T2.isAlive()&&!T3.isAlive()&&!T4.isAlive())
//					threadFini = true;
//				if(threadFini){
					sbg.enterState(Main.MENUSTATE,t[0],t[1]);
//					sbg.enterState(Main.HISTORYSTATE);
					System.out.println("Fin chargement");
//				}
				//qdvue.setMusic(1);
			}

		}




		/*
		else{
			if (vue.nextResource != null) { 
				System.out.println("kikou "+vue.nextResource.getDescription());
				try { 
					vue.nextResource.load(); 
					// slow down loading for example purposes 
					//try { Thread.sleep(50); } catch (Exception e) {} 
				} catch (IOException e) { 
					try {
						throw new SlickException("Failed to load: "+vue.nextResource.getDescription(), e);
					} catch (SlickException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				} 

				vue.nextResource = null; 
			} 
		}*/

	}

	private void nextRessource() throws InterruptedException{
		System.out.println("nbproc "+nbProc);
		//for(int i=0;i<nbProc;i++){
		Runnable tache = new LoadThread ();
		Thread monThread = new Thread (tache);
		monThread.start();
		monThread.isAlive();
		//}
	}

	@Override
	public int getID() {
		return stateID;
	}
}

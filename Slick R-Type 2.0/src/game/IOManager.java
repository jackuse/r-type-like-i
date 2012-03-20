package game;

import org.newdawn.slick.Input;


/**
 * Class IOManager
 * Singleton
 * @author Etienne Grandier-Vazeille
 *
 * Permet de récupéré les entrées clavier
 */
public class IOManager {

	private static IOManager _instance = new IOManager();

	public final static IOManager getInstance(){
		return _instance;
	}

	public static char getChar(Input input){
		if(input.isKeyPressed(Input.KEY_A))
			return 'a';
		else if(input.isKeyPressed(Input.KEY_B))
			return 'b';
		else if(input.isKeyPressed(Input.KEY_C))
			return 'c';
		else if(input.isKeyPressed(Input.KEY_D))
			return 'd';
		else if(input.isKeyPressed(Input.KEY_E))
			return 'e';
		else if(input.isKeyPressed(Input.KEY_F))
			return 'f';
		else if(input.isKeyPressed(Input.KEY_G))
			return 'g';
		else if(input.isKeyPressed(Input.KEY_H))
			return 'h';
		else if(input.isKeyPressed(Input.KEY_I))
			return 'i';
		else if(input.isKeyPressed(Input.KEY_J))
			return 'j';
		else if(input.isKeyPressed(Input.KEY_K))
			return 'k';
		else if(input.isKeyPressed(Input.KEY_L))
			return 'l';
		else if(input.isKeyPressed(Input.KEY_M))
			return 'm';
		else if(input.isKeyPressed(Input.KEY_N))
			return 'n';
		else if(input.isKeyPressed(Input.KEY_O))
			return 'o';
		else if(input.isKeyPressed(Input.KEY_P))
			return 'p';
		else if(input.isKeyPressed(Input.KEY_Q))
			return 'q';
		else if(input.isKeyPressed(Input.KEY_R))
			return 'r';
		else if(input.isKeyPressed(Input.KEY_S))
			return 's';
		else if(input.isKeyPressed(Input.KEY_T))
			return 't';
		else if(input.isKeyPressed(Input.KEY_U))
			return 'u';
		else if(input.isKeyPressed(Input.KEY_V))
			return 'v';
		else if(input.isKeyPressed(Input.KEY_W))
			return 'w';
		else if(input.isKeyPressed(Input.KEY_X))
			return 'x';
		else if(input.isKeyPressed(Input.KEY_Y))
			return 'y';
		else if(input.isKeyPressed(Input.KEY_Z))
			return 'z';
		else if(input.isKeyPressed(Input.KEY_0) || input.isKeyPressed(Input.KEY_NUMPAD0))
			return '0';
		else if(input.isKeyPressed(Input.KEY_1) || input.isKeyPressed(Input.KEY_NUMPAD1))
			return '1';
		else if(input.isKeyPressed(Input.KEY_2) || input.isKeyPressed(Input.KEY_NUMPAD2))
			return '2';
		else if(input.isKeyPressed(Input.KEY_3) || input.isKeyPressed(Input.KEY_NUMPAD3))
			return '3';
		else if(input.isKeyPressed(Input.KEY_4) || input.isKeyPressed(Input.KEY_NUMPAD4))
			return '4';
		else if(input.isKeyPressed(Input.KEY_5) || input.isKeyPressed(Input.KEY_NUMPAD5))
			return '5';
		else if(input.isKeyPressed(Input.KEY_6) || input.isKeyPressed(Input.KEY_NUMPAD6))
			return '6';
		else if(input.isKeyPressed(Input.KEY_7) || input.isKeyPressed(Input.KEY_NUMPAD7))
			return '7';
		else if(input.isKeyPressed(Input.KEY_8) || input.isKeyPressed(Input.KEY_NUMPAD8))
			return '8';
		else if(input.isKeyPressed(Input.KEY_9) || input.isKeyPressed(Input.KEY_NUMPAD9))
			return '9';
		else if(input.isKeyPressed(Input.KEY_SPACE))
			return '\7';
		else
			return '\0';

	}


	public String getKeyString(Input input, String s,int maxLength){
		if (input.isKeyPressed(input.KEY_BACK)){
			if(s.length()>0)
				s =s.substring(0, s.length()-1); 
		}
		char c = this.getChar(input);
		if(c != '\0' && s.length() < maxLength){;
		s+=c;
		}
		return s;
	}
	
//	public boolean[] makeControlKey(Input input, int maxItem,int kB){
//		boolean[] inside = new boolean[maxItem];	
//		if (input.isKeyPressed(Input.KEY_Q)){
//			inside[kB] = false;
//			kB--;
//			if(kB<0)
//				kB=maxItem-1;
//			inside[kB] = true;
//		}
//
//		if (input.isKeyPressed(Input.KEY_D)){
//			inside[kB] = false;
//			kB++;
//			if(kB>maxItem-1)
//				kB=0;
//			inside[kB] = true;
//			System.out.println("kB = "+kB);
//		}
//		return inside;
//	}
	
}

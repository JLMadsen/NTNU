public class Konverter{
	public static char intToChar(int t){  // array til "tegn"
		char tegn = 0;
		if(t<26 && t>=0) {  // 0 - 25 vanlige bokstaver | 26 - 28 æøå | 29 andre ting
			tegn = (char)(t+97);
		}else if(t==26){
			tegn = (char)(t+204);
		}else if(t==27){
			tegn = (char)(t+221);
		}else if(t==28){
			tegn = (char)(t+169);
		}return tegn;
	}
	public static int charToInt(int h){ // unicode til array
		if(h>96 && h<123) {  // små bokstaver
			h=h-97;	}else if(h>64 && h<91) {  // store bokstaver
			h=h-65;
		}else if(h>43 && h<47 || h==32) {  // andre tegn
			h=29;
		}else if(h==230 || h==198){  // æ Æ
			h=26;
		}else if(h==248 || h==216){  // ø Ø
			h=27;
		}else if(h==229 || h==197){  // å Å
			h=28;
		}else {
			throw new IllegalArgumentException("Feil input.");
		}return h;
	}
}
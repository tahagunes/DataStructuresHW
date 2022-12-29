package Data1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	static HashTable hashtable=new HashTable();
	public static void main(String[] args) throws IOException, InterruptedException {
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// I added %50 , %70 , YHF and PAF option with command line 
		////////////////////////////////////	
		
		//long startTime = System.currentTimeMillis();
		int column=0;//words count	
		String[][] wAc=new String[2100][2];
		String[] searchwords=new String[200];
		BufferedReader oku= new BufferedReader(new FileReader("story.txt"));
			// Ã�Ã§eriÃ°i okuma iÃ¾lemi yapÃ½lÃ½r
			while (true) {								
			    String yazi = oku.readLine();
			    
			    if (yazi!=null&&yazi!=" ") { //metin sonu olup olmadÃ½Ã°Ã½ kontrol ediliyor	
			    	String a=yazi.toLowerCase();
			    	String[] check=a.split(" ");
			    	for (int i = 0; i < check.length; i++) {//her bir kelime iÃ§in
			    		boolean door=true;			    		
			    		if(check[i].equals("�"))check[i]="I";
			    		else if(check[i].length()>0&&check[i]!=null)check[i].replace('�', 'i');
						for (int j = 0; j < wAc.length; j++) {//daha Ã¶nce bulunmuÃ¾ mu bakÃ½yoruz
							if(wAc[j][0]!="-"&&wAc[j][0]!=null&&!wAc[j][0].equals(" ")&&check[i].equals(wAc[j][0])) {								
								int trans=Integer.parseInt(wAc[j][1]);
								trans++;
								wAc[j][1]=Integer.toString(trans);
								check[i]="";
								door =false;
								break;
							}							
						}
						if(check[i].isEmpty()) {
							//System.out.println(check[i]);
						}
						else if(door) {//eÃ°er kelime daha Ã¶nce bulunmadÃ½ysa yenisini oluÃ¾turuyoruz
							wAc[column][0]=check[i];
							wAc[column][1]=Integer.toString(1);
							column++;
							check[i]="";
						}
					}
			    }
			    else if(yazi==null){
			    	break;
			    }
			}
			oku.close();
			FileReader fileReader = new FileReader("search.txt");
			String line;

			BufferedReader br = new BufferedReader(fileReader);
			int searchwordcounter=0;
			while ((line = br.readLine()) != null) {
				if(!line.isEmpty()) {
					searchwords[searchwordcounter]=line;
					searchwordcounter++;
				}
			   // System.out.println(line);

			}
			br.close();
			//long endTime = System.currentTimeMillis();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    // Add keys and values (words, count)
			for (int i = 0; i < wAc.length; i++) {
				if(wAc[i][0]!=" ") {					
					Keys keyss=new Keys(wAc[i][0],0);
					hashtable.put(keyss,Integer.parseInt(wAc[i][1]));
					//System.out.println(wAc[i][0]+" "+wAc[i][1]);
				}
				if(wAc[i+1][0]==null)break;//kelimeler bitince dursun
			}
			int countt=0;
			for (int i = 0; i < hashtable.TABLE_SIZE; i++) {
				if(hashtable.table[i]!=null)countt++;
			}
			//System.out.println(countt);
			//System.out.println(hashtable.TABLE_SIZE);
		//	System.out.println(hashtable.CollisionCount());
			//hashtable.searchword("frog");
			
			double maxsearch=0,minsearch=99999,average=0,avcount=0;
			for (int i = 0; i < searchwords.length; i++) {				
				if(searchwords[i]!=null) {
					avcount++;
					long startTime = System.currentTimeMillis();
					hashtable.searchword(searchwords[i]);
					long endTime = System.currentTimeMillis();
					long estimatedTime = endTime - startTime; // Ge�en s�reyi milisaniye cinsinden elde ediyoruz
					double seconds = (double)estimatedTime/1000; // saniyeye �evirmek i�in 1000'e b�l�yoruz.
					average=(average+seconds);
					
				 if(seconds>maxsearch&&seconds!=0) {maxsearch=seconds; System.out.println("newmax ="+maxsearch);}
				 else if(seconds<minsearch&&seconds!=0) {minsearch=seconds;System.out.println("newmin ="+minsearch);}
				 
				}
			}
			average=average/avcount;
			String sayi = String.valueOf(String.format("%.20f", average));
			String sayi1 = String.valueOf(String.format("%.20f", minsearch));
			String sayi2 = String.valueOf(String.format("%.20f", maxsearch));
			System.out.println("av  :"+sayi);
			System.out.println("min :"+sayi1);
			System.out.println("max :"+sayi2);
			 // ... zaman alan baz� i�lemler ... 
			 
			// long estimatedTime = endTime - startTime; // Ge�en s�reyi milisaniye cinsinden elde ediyoruz
		//	 double seconds = (double)estimatedTime/1000; // saniyeye �evirmek i�in 1000'e b�l�yoruz.
			// System.out.println("Time : "+seconds);
			 System.out.println("Collision count : "+hashtable.CollisionCount());
	}

}

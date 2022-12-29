package Data1;

public class HashTable {
	

	public int TABLE_SIZE = 997;
	 private int checkpercent=0;
    HashEntry[] table;
    HashEntry[] table2;
    int collision=0;
    boolean resize=false;
    int keyyy=0;

    public HashTable() {
          table = new HashEntry[TABLE_SIZE];
          table2 = new HashEntry[TABLE_SIZE];
          for (int i = 0; i < TABLE_SIZE; i++) {
                table[i] = null;
          		table2[i] = null;
    }}
    
    public int hashFunction(String key) {
    	int hash =0; 
    	int prime=31;
    	int keyy=0;
    	
    	double n =(int)key.length();
    	for(int i=0;i<key.length();i++)
    	{
    		int asciikod=key.charAt(i);
    		asciikod=asciikod-96;
    	    hash +=(int) ( (Math.pow(prime, (n-1)))*asciikod);
    		n--;
    				
    	}
    	/*
    	// Create a hash function that performs mod by table size
    			char [] letters = new char[key.length()];
    			key.getChars(0, key.length(), letters, 0);
    			int toplam = 0;
    			int uzunluk = letters.length;
    			for (int i = 0; i < uzunluk; i++) {
    			toplam = toplam + letters[i];
    			}
    		//	System.out.println("Toplam Asci toplam : "+toplam);
    			
       hash = (toplam % TABLE_SIZE);*/
    	if(hash<0)hash=hash*-1;
    	keyyy=hash;
    	hash=(hash%TABLE_SIZE);
    	
    	return hash;
    	
    }

    public int get(String key) {
          
    	  int hash = hashFunction(key); // Calculate hash value
          
          if (table[hash] == null)
                return -1;
          else
                return table[hash].getValue();
          
    }

    public void put(Keys key, int value) { 
    	//System.out.println(key.getDip());
    	//System.out.println("put giriþ");
    	Keys searchkey=key;
    	int searchvalue=value;
    	int hash = hashFunction(searchkey.getKey()); // Calculate hash value 	
        for (int i = hash; i < table.length; i++) { 
        	searchkey.setDip(i-hash);
        	if(table[i]==null) {        		
        		table[i] = new HashEntry(searchkey, searchvalue);        		
        		//System.out.println(table[i].getKey().getKey());
        		break;
        		}  //index boþsa yerleþ
			else if(table[i]!=null&&table[i].getKey().getDip()<searchkey.getDip()) {	
					//System.out.println("deneme1");
					HashEntry temp=new HashEntry(table[i].getKey(),table[i].getValue());//yeri değişecek kayıt edildi					
					table[i]=new HashEntry(searchkey,searchvalue);//yerleştirildi					
					searchkey=temp.getKey();
					searchvalue=temp.getValue();
					hash=hashFunction(searchkey.getKey());
					i=hash;									
					collision++;
				}			
        }    
        checkpercent=0;
    	for (int i = 0; i < TABLE_SIZE; i++) {
			if(table[i] != null) checkpercent++;
		}
    	//if(checkpercent+1>=((TABLE_SIZE/100)*50)) {//if want to check %50
    	if(checkpercent+1>=((TABLE_SIZE/100)*70)) {//if want to check %70
    		table2 = new HashEntry[TABLE_SIZE];
       		table2 = table;     	
        	TABLE_SIZE=TABLE_SIZE*2;
        	resize=true;    		
    		table = new HashEntry[TABLE_SIZE];
    		//tÃ¼m table yeniden iÃ¾lenecek
    	}
    	
    	
    	if(resize) {
    		for (int i = 0; i < table2.length; i++) {
    			if(table2[i]!=null) {    				
    				hash = hashFunction(table2[i].getKey().getKey()); // Calculate hash value     				
        			searchkey=table2[i].getKey();
        			searchvalue=table2[i].getValue();
        			for (int i1 = hash; i1 < table.length; i1++) {
        	        	searchkey.setDip(i1-hash);
        	        	if(table[i1]==null) {        	        		
        	        		table[i1] = new HashEntry(searchkey, searchvalue);
        	        		//System.out.println(table[i].getKey().getKey());
        	        		break;
        	        		}  //index boþsa yerleþ
        				else if(table[i1]!=null&&table[i1].getKey().getDip()<searchkey.getDip()) {  
        						System.out.println("deneme2");
        						HashEntry temp=new HashEntry(table[i1].getKey(),table[i1].getValue());
        						table[i1]=new HashEntry(searchkey,searchvalue);
        						searchkey=temp.getKey();
        						searchvalue=temp.getValue();  
        						hash=hashFunction(searchkey.getKey());
        						i1=hash;
        						collision++;
        					}
        				
        				
        				
        			}  
    			}
			}
    		resize=false;
    	}//resize sonu
    }
    public void print(Keys key) {
    	System.out.println("key : "+key.getKey()+" dip : "+key.getDip()+" index : "+hashFunction(key.getKey()) );
    }
    public void searchword(String word) {
    	for (int i = 0; i < table.length; i++) {
			if(table[i]!=null&&word.equals(table[i].getKey().getKey())) {
				hashFunction(table[i].getKey().getKey());
				System.out.println("WORD : "+table[i].getKey().getKey()+" DIP : "+table[i].getKey().getDip()+" COUNT : "+table[i].getValue()+" KEY : "+keyyy);
				break;
			}
			if(i==table.length-1)System.out.println(word+" not found!");
		}
    	
    }
    public int CollisionCount() {
    	return collision;
    }
}


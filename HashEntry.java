package Data1;
public class HashEntry {
	
    private Keys key;
    private int value=0;

    HashEntry(Keys key, int value) {
          this.key = key;
          this.value = value;
    }     

    public Keys getKey() {
          return key;
    }

    public int getValue() {
          return value;
    }
}

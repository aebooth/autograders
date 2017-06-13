package LetterInventory;

/**
 * Created by Alex on 6/11/17.
 */
public class LetterInventory {
    private int[] inventory;
    private int size;

    public LetterInventory(){
        this("");
    }

    public LetterInventory(String data){
        inventory = new int[26];
        size = 0;
        for(char c:data.toLowerCase().toCharArray()){
            if(c >= 'a' && c <= 'z'){
                inventory[c - 'a']++;
                size++;
            }
        }

    }

    private char charToLowerCase(char c) {
        return String.valueOf(c).toLowerCase().charAt(0);
    }

    public int get(char c){
        c = charToLowerCase(c);
        if(c < 'a' || c > 'z'){
            throw new IllegalArgumentException("Input must be a letter");
        }

        return inventory[c - 'a'];
    }

    public void set(char c, int value){
        c = charToLowerCase(c);
        if(c < 'a' || c > 'z'){
            throw new IllegalArgumentException("Input must be a letter");
        }else if(value < 0){
            throw new IllegalArgumentException("Cannot have negative number of letters");
        }else{
            size -= inventory[c - 'a'];
            inventory[c - 'a'] = value;
            size += value;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public String toString(){
        String res = "[";
        for(int ndx = 0; ndx < inventory.length; ndx++){
            for(int count = 0; count < inventory[ndx]; count++){
               res += (char)('a' + ndx);
            }
        }
        return res + "]";
    }

    public LetterInventory add(LetterInventory other){
        LetterInventory res = new LetterInventory();
        for(int ndx = 0; ndx < inventory.length; ndx++){
            res.set((char)('a' + ndx),this.inventory[ndx] + other.inventory[ndx]);
        }
        return res;
    }

    public LetterInventory subtract(LetterInventory other){
        LetterInventory res = new LetterInventory();
        for(int ndx = 0; ndx < inventory.length; ndx++){
            if(this.inventory[ndx] - other.inventory[ndx] < 0) return null;
            res.set((char)('a' + ndx),this.inventory[ndx] - other.inventory[ndx]);
        }
        return res;
    }

    public double getLetterPercentage(char c){
        c = charToLowerCase(c);
        if(c < 'a' || c > 'z'){
            throw new IllegalArgumentException("Input must be a letter");
        }

        return inventory[c - 'a'] / size;
    }
}

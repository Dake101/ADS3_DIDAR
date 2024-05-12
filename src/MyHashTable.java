
public class MyHashTable<K, V> {
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value){
            this.key = key; // assigning key
            this.value = value; // assigning value
        }

        @Override
        public String toString(){   // to string returns everything as a string
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; // number of buckets
    private int size; // number of the hash table's key-value pairs
    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int computeHashCode(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int hash = 1;
        for (int i = 0; i < arr.length; i++) {
            hash += 51 * hash + arr[i];
        }

        return hash;
    }



    private int computeHashCode(String s) {
        if (s == null) {
            return 0;
        }

        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * 31 + s.charAt(i);
        }
        return hash;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    private int hash(int[] arr) {
        return Math.abs(computeHashCode(arr)) % M;
    }

    private int hash(String s) {
        return Math.abs(computeHashCode(s)) % M;
    }

    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if(chainArray[index] == null){ // Place the new node at the index immediately if there isn't already one there
            chainArray[index] = newNode;
            size++;
            return;
        }

        HashNode<K, V> current = chainArray[index]; // Alternatively, follows the chain to locate the final node or the key
        HashNode<K, V> lastNode = null;
        while(current != null){
            if(current.key.equals(key)){
                current.value = value; // If a key is found, the value will be updated
                return;
            }
            lastNode = current;
            current = current.next;
        }

        if(lastNode != null){ // Adds the new node at the end of the list if the key cannot be found
            lastNode.next = newNode;
            size++;
        }

    }

    public V get(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        HashNode<K,V> lastNode = null;
        while(current != null){
            if(current.key.equals(key)){
                if(lastNode != null){
                    lastNode.next = current.next;
                }else{
                    chainArray[index] = current.next;
                }
                size--;
                return current.value;
            }
            lastNode = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value){ // VVerifies whether value is present in the chainArray that returns true if it is true and vice versa
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K,V> current = chainArray[i];
            while(current != null){
                if(current.value.equals(value)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public K getKey(V value){

        for(int i = 0; i < chainArray.length; i++){

            HashNode<K,V> current = chainArray[i];

            while(current != null){
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }


        }

        return null;
    }

    public int size(){ // Returns the size
        return size;
    }

    public void printBucketCounts() {
        System.out.println("Bucket distribution:");
        for (int i = 0; i < chainArray.length; i++) {
            int count = 0;
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                count++;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }


}

/************************************************************************************
 * @file LinHashMap.java
 *
 * @author  John Miller
 */

import java.io.*;
import java.lang.reflect.Array;
import static java.lang.System.out;
import java.util.*;

/************************************************************************************
 * This class provides hash maps that use the Linear Hashing algorithm.
 * A hash table is created that is an expandable array-list of buckets.
 */
public class LinHashMap <K, V>
       extends AbstractMap <K, V>
       implements Serializable, Cloneable, Map <K, V>
{
    /** The debug flag
     */
    private static final boolean DEBUG = true;

    /** The number of slots (for key-value pairs) per bucket.
     */
    private static final int SLOTS = 4;

    /** The threshold/upper bound on the load factor
     */
    private static final double THRESHOLD = 1.2;

    /** The class for type K.
     */
    private final Class <K> classK;

    /** The class for type V.
     */
    private final Class <V> classV;

    /********************************************************************************
     * This inner class defines buckets that are stored in the hash table.
     */
    private class Bucket
    {
        int    nKeys;
        K []   key;
        V []   value;
        Bucket next;

        @SuppressWarnings("unchecked")
        Bucket ()
        {
            nKeys = 0;
            key   = (K []) Array.newInstance (classK, SLOTS);
            value = (V []) Array.newInstance (classV, SLOTS);
            next  = null;
        } // constructor

        V find (K k)
        {
            for (var j = 0; j < nKeys; j++) if (key[j].equals (k)) return value[j];
            return null;
        } // find

        void add (K k, V v)
        {
            key[nKeys]   = k;
            value[nKeys] = v;
            nKeys++;
        } // add

        void print ()
        {
            out.print ("[ " );
            for (var j = 0; j < nKeys; j++) out.print (key[j] + " . ");
            out.println ("]" );
        } // print

    } // Bucket inner class

    /** The list of buckets making up the hash table.
     */
    private final List <Bucket> hTable;

    /** The modulus for low resolution hashing
     */
    private int mod1;

    /** The modulus for high resolution hashing
     */
    private int mod2;

    /** The index of the next bucket to split.
     */
    private int isplit = 0;

    /** Counter for the number buckets accessed (for performance testing).
     */
    private int count = 0;

    /** The counter for the total number of keys in the LinHash Map
     */
    private int keyCount = 0;

    /********************************************************************************
     * Construct a hash table that uses Linear Hashing.
     * @param classK  the class for keys (K)
     * @param classV  the class for values (V)
     */
    public LinHashMap (Class <K> _classK, Class <V> _classV)
    {
        classK = _classK;
        classV = _classV;
        mod1   = 4;                                                          // initial size
        mod2   = 2 * mod1;
        hTable = new ArrayList <> ();
        for (var i = 0; i < mod1; i++) hTable.add (new Bucket ());
    } // constructor

    /********************************************************************************
     * Return a set containing all the entries as pairs of keys and values.
     * @return  the set view of the map
     */
    public Set <Map.Entry <K, V>> entrySet ()
    {
        var enSet = new HashSet <Map.Entry <K, V>> ();

        //  T O   B E   I M P L E M E N T E D
        for(int i = 0; i< hTable.size(); i++){
            for( var j = hTable.get(i); j!=null;   j = j.next){
                for (var k = 0; k< j.nKeys; k++){
//                    out.println(j.key[k] + " " + j.value[k]);
                    Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(j.key[k], j.value[k]);
                    enSet.add(entry);
                }
            }
        }
//        var ensize = enSet.size();
//        for(int j=0; j<ensize; j++){
//            if(! enSet.contains(j)){
//                Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(, null);
//                enSet.add(entry);
//            }
//        }
        out.println(enSet);
        return enSet;
    } // entrySet

    /********************************************************************************
     * Given the key, look up the value in the hash table.
     * @param key  the key used for look up
     * @return  the value associated with the key
     */
    @SuppressWarnings("unchecked")
    public V getold (Object key)
    {
        var i = h (key);
        return find ((K) key, hTable.get (i), true);
    } // get
    @SuppressWarnings("unchecked")
    public V get (Object key)
    {
        var i    = h (key);
        // Modification to adhere to algorithm in video lecture
        if(i < isplit) {
            i = h2(key);
        }                                                                    // hash to i-th bucket chain
        // end modification
        return find ((K) key, hTable.get (i), true);
    } // get

    /********************************************************************************
     * Put the key-value pair in the hash table.  Split the 'isplit' bucket chain
     * when the load factor is exceeded.
     * @param key    the key to insert
     * @param value  the value to insert
     * @return  the old/previous value, null if none
     */
    public V put (K key, V value)
    {
        var i    = h (key);
        // Modification to adhere to algorithm in video lecture
        if(i < isplit) {
            i = h2(key);
        }                                                                    // hash to i-th bucket chain
        // end modification
        var bh   = hTable.get (i);                                           // start with home bucket
        var oldV = find (key, bh, false);                                    // find old value associated with key
        out.println ("LinearHashMap.put: key = " + key + ", h() = " + i + ", value = " + value);

        keyCount++;                                                          // increment the key count
        var lf = loadFactor ();                                              // compute the load factor
        if (DEBUG) out.println ("put: load factor = " + lf);
        // MODIFIED to ensure that the key and value are placed in the correct bucket after split
        if (lf > THRESHOLD) {
            split ();
            i = h(key);
            if(i < isplit) {
                i = h2(key);
            } // recalculates i and bh
            bh   = hTable.get (i); // needed as the split might remove the home bucket originally assigned
        };                                        // split beyond THRESHOLD
        // END MODIFICATION

        var b = bh;
        while (true)  {
            if (b.nKeys < SLOTS) { b.add (key, value); return oldV; }
            if (b.next != null) b = b.next; else break;
        } // while

        var bn = new Bucket ();
        bn.add (key, value);
        b.next = bn;                                                         // add new bucket at end of chain
        return oldV;
    } // put
    public V putmine (K key, V value)
    {
        var i    = h (key);                                                  // hash to i-th bucket chain
        var splitexists = false;
        var bh   = hTable.get (i);                                           // start with home bucket
        var oldV = find (key, bh, false);                                    // find old value associated with key
//        out.println ("LinearHashMap.put: key = " + key + ", h() = " + i + ", value = " + value);

        keyCount++;                                                          // increment the key count
        var lf = loadFactor ();                                              // compute the load factor
        if (DEBUG) out.println ("put: load factor = " + lf);
//        hTable.get(1).print();
//        if(hTable.get(1).next != null) hTable.get(1).next.print();
        if (lf > THRESHOLD) split ();                                        // split beyond THRESHOLD
        try {
//            out.println("inside try");
            var nb   = hTable.get (i+mod1);
//            out.println("split bucket exists, putting h2 keys in split bucket");
        }catch (IndexOutOfBoundsException e){
            splitexists = false;
//            out.println("Split bucket does not exists for current bucket, putting all keys in current bucket");
        }
        if(splitexists && h(key) != h2(key)){
            var nb   = hTable.get (i+mod1);
            var b = nb;
            while (true)  {
                if (b.nKeys < SLOTS) { b.add (key, value); return oldV; }
                if (b.next != null) b = b.next; else break;
            } // while
            var bn = new Bucket ();
            bn.add (key, value);
            b.next = bn;

        }
        else{
            var b = bh;
            while (true)  {
                if (b.nKeys < SLOTS) { b.add (key, value); return oldV; }
                if (b.next != null) b = b.next; else break;
            } // while
            var bn = new Bucket ();
            bn.add (key, value);
            b.next = bn;                                                         // add new bucket at end of chain
        }
        return oldV;
    } // put

    /********************************************************************************
     * Print the hash table.
     */
    public void print ()
    {
        out.println ("LinHashMap");
        out.println ("-------------------------------------------");

        for (var i = 0; i < hTable.size (); i++) {
            out.print ("Bucket [ " + i + " ] = ");
            var j = 0;
            for (var b = hTable.get (i); b != null; b = b.next) {
                if (j > 0) out.print (" \t\t --> ");
                b.print ();
                j++;
            } // for
        } // for

        out.println ("-------------------------------------------");
    } // print
 
    /********************************************************************************
     * Return the size (SLOTS * number of home buckets) of the hash table. 
     * @return  the size of the hash table
     */
    public int size ()
    {
        return SLOTS * (mod1 + isplit);
    } // size

    /********************************************************************************
     * Split bucket chain 'isplit' by creating a new bucket chain at the end of the
     * hash table and redistributing the keys according to the high resolution hash
     * function 'h2'.  Increment 'isplit'.  If current split phase is complete,
     * reset 'isplit' to zero, and update the hash functions.
     */
    private void split ()
    {
//        out.println("print before");
//        this.print();
        out.println ("split: bucket chain " + isplit);

        //  T O   B E   I M P L E M E N T E D
//        out.println("here is current bucket");
        hTable.add(new Bucket());
        var newBucket = hTable.get(hTable.size()-1);
        List allkeys = new ArrayList();
        List allvals = new ArrayList();
        var currentBucket = hTable.get(isplit);
        for(; currentBucket != null; currentBucket = currentBucket.next){
            for(var i = 0; i< currentBucket.nKeys; i++){
                allkeys.add(currentBucket.key[i]);
                allvals.add(currentBucket.value[i]);
            }
        }
        currentBucket = new Bucket();
        for(var i = 0; allkeys.size()> 0 && i < allkeys.size(); i++){
            if(h2(allkeys.get(i)) != isplit){
//                out.println("moving to new bucket");
//                out.println("key = "+allkeys.get(i) + "key's hash = " + h2(allkeys.get(i)) + "isplit value = " + isplit);
                if(newBucket.nKeys < SLOTS) {
                    newBucket.add((K) allkeys.get(i), (V) allvals.get(i));
                }
                else{
                    var nb = new Bucket ();
                    nb.add ((K) allkeys.get(i), (V) allvals.get(i));
                    newBucket.next = nb;
                }
            }
            else{
//                out.println("moving to current bucket");
//                out.println("key = "+allkeys.get(i) + "key's hash = " + h2(allkeys.get(i)) + "isplit value = " + isplit);
                if(currentBucket.nKeys < SLOTS) {
                    currentBucket.add((K) allkeys.get(i), (V) allvals.get(i));
                }
                else{
                    var cn = new Bucket ();
                    cn.add ((K) allkeys.get(i), (V) allvals.get(i));
                    currentBucket.next = cn;
                }
            }
        }
        hTable.set(isplit, currentBucket);
//        currentBucket.print();
        if((isplit + 1) == mod1){
            isplit = 0;
            mod1 = 2*mod1;
            mod2 = 2*mod2;
        }
        else isplit++;
//        this.print();
//        out.println("print after");
    } // split

    /********************************************************************************
     * Return the load factor for the hash table.
     * @return  the load factor
     */
    private double loadFactor ()
    {
        return keyCount / (double) size ();
    } // loadFactor

    /********************************************************************************
     * Find the key in the bucket chain that starts with home bucket bh.
     * @param key     the key to find
     * @param bh      the given home bucket
     * @param by_get  whether 'find' is called from 'get' (performance monitored)
     * @return  the current value stored stored for the key
     */
    private V find (K key, Bucket bh, boolean by_get)
    {
        for (var b = bh; b != null; b = b.next) {
            if (by_get) count++;
            V result = b.find (key);
            if (result != null) return result;
        } // for
        return null;
    } // find

    /********************************************************************************
     * Hash the key using the low resolution hash function.
     * @param key  the key to hash
     * @return  the location of the bucket chain containing the key-value pair
     */
    private int h (Object key)
    {
        return key.hashCode () % mod1;
    } // h

    /********************************************************************************
     * Hash the key using the high resolution hash function.
     * @param key  the key to hash
     * @return  the location of the bucket chain containing the key-value pair
     */
    private int h2 (Object key)
    {
        return key.hashCode () % mod2;
    } // h2

    /********************************************************************************
     * The main method used for testing.
     * @param  the command-line arguments (args [0] gives number of keys to insert)
     */
    public static void main (String [] args)
    {
        var totalKeys = 500;
        var RANDOMLY  = true;

        LinHashMap <Integer, Integer> ht = new LinHashMap <> (Integer.class, Integer.class);
        if (args.length == 1) totalKeys = Integer.valueOf (args [0]);

        if (RANDOMLY) {
            var rng = new Random ();
            for (var i = 1; i <= totalKeys; i += 2) ht.put (rng.nextInt (2 * totalKeys), i * i);
        } else {
            for (var i = 1; i <= totalKeys; i += 2) ht.put (i, i * i);
        } // if

        ht.print ();
//        for (var i = 0; i <= totalKeys; i++) {
//            out.println ("key = " + i + " value = " + ht.get (i));
//        } // for
        out.println ("-------------------------------------------");
        out.println ("Average number of buckets accessed = " + ht.count / (double) totalKeys);
//        ht.entrySet(); //print the entire set
    } // main

} // LinHashMap class

